package es.uned2014.oca.servidor;

import java.net.*;
import java.io.*;
import java.util.*;

import es.uned2014.oca.partida.*;
import es.uned2014.oca.comunicaciones.*;
import es.uned2014.oca.drivers.*;
import es.uned2014.oca.jugador.ColorJugador;
import es.uned2014.oca.excepciones.*;

/**
 * Se define una clase Servidor en la que se desarrollan los métodos necesarios para definir un servidor
 * al que conectar los diferentes clientes y que ejecute la aplicación.
 * Se define la conexión ServerSocket para las conexiones Socket de los clientes.
 * 
 * @author	Alef UNED 2014
 * @version	Oca 3.0
 * @fecha 	Mayo 2014
 */

public class Servidor {
	// Variable DriverServidor que hace referencia al Driver del Servidor, que implementa GUI
	private DriverServidor servidorGUI;
	// Variable de tipo colección (ArrayList) que representa el conjunto de conexiones de Clientes
	private Map<ColorJugador, ClienteThread> mapClientes;
	// Variable de tipo entero que representa el puerto del Servidor para la conexión Cliente-Servidor
	private int puertoServidor;
	// Variable tipo booleano que será true mientras el Servidor esté conectado
	private boolean servidorConectado;
    
	// Variable de tipo entero que representa el número máximo de Clientes admitidos en cada partida
	private static int numeroJugadores;

	// Variable ServerSocket 
	private ServerSocket socketServidor;

	// Partida de la Oca
	private Oca oca;
	
	// Conjunto de Colores posibles (id de los Clientes)
	ColorJugador[] colores = {ColorJugador.ROJO, ColorJugador.AZUL, ColorJugador.AMARILLO, ColorJugador.VERDE, ColorJugador.NARANJA, ColorJugador.VIOLETA};
    
	/**
	 * Método constructor: se asignan los parámetros a numeroJugadores y servidorGUI. 
	 * Se inicializa el ArrayList<ClienteThread> y se asigna el número del puerto.
	 * @param Valor entero que se asigna al número de jugadores
	 * @param DriverServidor, que tiene la GUI desde la que el usuario interactúa con el Servidor
	 * @return null
	 * @throws null
	 */ 
	public Servidor(DriverServidor ds, int i){
		this.servidorGUI = ds; // No se usa this.servidorGUI por ser campo static
		
		// Se comprueba que el número de jugadores es correcto
		if(i<2 || i>6){
			this.infoConexionesGUI("\nEl número de jugadores debe estar comprendido entre 2 y 6."
					+ "\nNo se ha conectado el servidor.");
			throw new ClaseError("El número de jugadores debe estar comprendido entre 2 y 6.");
		} else {
			Servidor.numeroJugadores = i; // No se usa this.numeroJugadores por ser campo static
			
			this.puertoServidor = 8888;
			this.mapClientes = new TreeMap<ColorJugador, ClienteThread>();
			
			// Se inicia el servidor:
			this.iniciarServidor();
			
			ServidorEsperando sEspera = new ServidorEsperando(this, numeroJugadores);
			sEspera.start();
		}	
	}
    
	/**
	 * Se inicia la conexión ServerSocket.
	 * @param null
	 * @return null
	 * @throws Error en la creación del Servidor
	*/
	public void iniciarServidor(){
		
		// Se pone a true el valor booleano que controla si el Servidor está conectado o no
		this.servidorConectado = true;
		
		// Se muestra en la GUI
		this.infoConexionesGUI("\nConectando el Servidor...");
		
		try{
			// Se crea la conexión ServerSocket
			socketServidor = new ServerSocket(puertoServidor);			
		} catch (IOException e) {
			infoConexionesGUI("\nSe ha producido un error al iniciar el Servidor." + e);
		}
		
		// Se crea la partida de la oca
		this.oca = new Oca(numeroJugadores, this);	
		
		// Informa en su GUI
		this.infoConexionesGUI("\nServidor iniciado correctamente.");

	}
	
	/**
	 * Se realizan las acciones necesarias para que los Clientes jueguen una partida de la Oca.
	 * @param null
	 * @return null
	 * @throws null
	 */ 
	public void jugarOca(){
		// Se define el orden de los jugadores llamando a un método de la clase Oca
		oca.definirOrden();

		// Se comienza la partida
		oca.iniciarOca();
	}
	
	/**
	 * Comprueba si existen al menos dos Jugadores para seguir la partida.
	 * - Si queda 1, le informa de que cierre
	 * - Si quedan 0, se cierra el ServerSocket
	 * - Si quedan más de 1, se sigue jugando con un jugador menos 
	 * @param null
	 * @return null
	 * @throws null
	 */ 
	public void comprobarJugadores(){
		switch(this.mapClientes.size()){
		case 1:
			// Se informa en la GUI:
			String s1 = "\n\nNo hay suficientes jugadores. La partida ha terminado.\n";
			this.infoConexionesGUI(s1);

			// Se informa a todos los Clientes		
			Comunicacion c1 = new Comunicacion(s1, TipoComunicacion.INFO);
			this.enviarATodos(c1);
			Comunicacion c2 = new Comunicacion("", TipoComunicacion.FINAL);
			this.enviarATodos(c2);
			
			// Se da por terminada la partida
			this.oca.setJuegoTerminado(true);
			break;
			
		case 0:
			// Si el servidor ha sido desconectado con el botón, se cierra el ServerSocket
			if (!this.servidorGUI.getServidorConectadoBoton()){
				this.cerrarServidor();
			}		
			// Si no se ha desconectado con el botón, vuelve a quedar a la espera
			else {
				this.reiniciarServidor();
			}
			break;
			
		default:
			// Se informa en la GUI:
			String s3 = "\nHa habido una desconexión. La partida continúa con " 
					+ this.mapClientes.size() + " jugadores.";
			this.infoConexionesGUI(s3);
						
			// Se informa a todos los Clientes		
			Comunicacion c3 = new Comunicacion(s3, TipoComunicacion.INFO);
			this.enviarATodos(c3);	
		}
	}
	
	/**
	 * Este método se utiliza cuando se ha pulsado el botón desconectar de GUI Servidor
	 * Comprueba si existen alguna coneción de Cliente. Si no quedan, cierra el ServerSocket.
	 * @param null
	 * @return null
	 * @throws null
	 */ 
	public void comprobarClientes(){
		if (this.mapClientes.size() == 0) {
			this.cerrarServidor();
		}
	}
	
	/**
	 * Se elimina del map de Clientes aquel cuyo color es el indicado como parámetro.
	 * @param ColorJugador identificativo del Cliente a eliminar
	 * @return null
	 * @throws null
	 */ 
	public void eliminarCliente (ColorJugador color){
    	mapClientes.remove(color);
	}
	
	/**
	 * Se envía a todos los Clientes conectador una Comunicacion tipo FINAL para que finalicen
	 * sus conexiones.
	 * @param null
	 * @return null
	 * @throws null
	 */ 
	public void cerrarTodo(){
		
		Comunicacion c1 = new Comunicacion("El servidor se va a cerrar o reiniciar: desconectando Clientes.", TipoComunicacion.INFO);
		this.enviarATodos(c1);
		Comunicacion c2 = new Comunicacion("", TipoComunicacion.FINAL);
		this.enviarATodos(c2);
	}
	
	/**
	 * Se reinicia el servidor:
	 * - Se pone en espera de conexiones de clientes.
	 * - Se recuperan los valores iniciales para una nueva partida.
	 * @param null
	 * @return null
	 * @throws null
	 */ 
	public void reiniciarServidor(){	
		
		try{
			this.socketServidor.close();
		} catch (IOException e) {
			this.infoConexionesGUI("Se ha producido un error al intentar cerrar el Servidor");
		}
		
		try{
			// Se crea la conexión ServerSocket
			socketServidor = new ServerSocket(puertoServidor);			
		} catch (IOException e) {
			infoConexionesGUI("\nSe ha producido un error al iniciar el Servidor." + e);
		}
		
	
		
		ServidorEsperando sEspera = new ServidorEsperando(this, numeroJugadores);
		sEspera.start();
		
		this.oca = new Oca(numeroJugadores, this);
	}
	
	/**
	 * Elige el siguiente color disponible para la siguiente conexión de Cliente
	 * @param null
	 * @return ColorJugador disponible (no existe conexión con ese color)
	 * @throws null
	 */ 
	public ColorJugador elegirColor(){
		int i = 0;
		// Mientras exista Conexión con ese color, sigue el bucle
		while(this.mapClientes.containsKey(colores[i])){
			i++;
		}
		 return colores[i];		
	}
	
	/**
	 * Se cierra la conexión ServerSocket y las conexiones Socket con CLientes (ClienteThread).
	 * @param null
	 * @return null
	 * @throws Error al cerrar el servidor
	 */ 
	public void cerrarServidor(){
		servidorConectado = false;
		
		// Se muestra en la GUI
		this.infoConexionesGUI("\n\nDesconectando el Servidor...");
		
		// Se cierra el ServerSocket
		try{
			socketServidor.close();
			this.infoConexionesGUI("\nSe ha cerrado el ServerSocket correctamente.");
		} catch (IOException e) {
			infoConexionesGUI("\nSe ha producido un error al cerrar el Servidor. " + e);
		}
    	
		// Se cierran todas las conexiones con Clientes
		for (ClienteThread i : this.mapClientes.values()){
			i.cerrar();
		}
		
		// Se reinicia el servidor en el servidorGUI
		this.servidorGUI.setTextoBoton("Conectar");
		this.servidorGUI.eliminarServidor();
	}
	
	
	
	
	
	/** 
	 * Método para obtener el contenido de la variable servidorGUI.
	 * @param null
	 * @return Devuelve el servidorGUI
	 * @throws null
	 */
	public DriverServidor getServidorGUI() {
		return this.servidorGUI;
	}
	
	/** 
	 * Método para obtener el contenido de la variable oca.
	 * @param null
	 * @return Devuelve la variable oca
	 * @throws null
	 */
	public Oca getOca() {
		return this.oca;
	}
	
	/** 
	 * Método para obtener el contenido de la variable mapClientes.
	 * @param null
	 * @return Devuelve la variable mapClientes
	 * @throws null
	 */
	public Map<ColorJugador, ClienteThread> getMapClientes() {
		return this.mapClientes;
	}
	
	/** 
	 * Método para establecer un nuevo valor para la variable mapClientes.
	 * @param nuevo valor para la variable mapClientes
	 * @return null
	 * @throws null
	 */
	public void setMapClientes(Map<ColorJugador, ClienteThread> m) {
		this.mapClientes = m;
	}	
	
	/** 
	 * Método para obtener el contenido de la variable servidorConectado.
	 * @param null
	 * @return Devuelve la variable servidorConectado
	 * @throws null
	 */
	public boolean getServidorConectado(){
		return this.servidorConectado;
	}
	
	/** 
	 * Método para obtener el contenido de la variable socketServidor.
	 * @param null
	 * @return Devuelve la variable socketServidor
	 * @throws null
	 */
	public ServerSocket getSocketServidor(){
		return this.socketServidor;
	}

	
	
	
	/**
	 * Método para enviar Comunicacion por socket a un cliente (identificado por su color)
	 * @param color
	 * @param comunicacion
	 * @return null
	 * @throws IOException si hay error al enviar objeto por socket
	 */
	public void enviarA(ColorJugador color, Comunicacion comunicacion){
		ClienteThread i = this.mapClientes.get(color);
		i.enviar(comunicacion);
	}
	
	/**
	 * Método para enviar Comunicacion por socket a todos los clientes
	 * @param comunicacion
	 * @return null
	 * @throws IOException si hay error al enviar objeto por socket
	 */
	public void enviarATodos(Comunicacion comunicacion){
		for (ClienteThread i : this.mapClientes.values()){
			i.enviar(comunicacion);
		}
	}
	
	/**
	 * Método para mostrar un mensaje en la GUI del propio servidor: apartado conexiones
	 * @param String con el mensaje
	 * @return null
	 * @throws null
	 */
	public void infoConexionesGUI(String s){
		servidorGUI.infoConexiones(s);
	}
	
	/**
	 * Método para mostrar un mensaje en la GUI del propio servidor: apartado partida
	 * @param String con el mensaje
	 * @return null
	 * @throws null
	 */
	public void infoPartidaGUI(String s){
		servidorGUI.infoPartida(s);
	}	
}
