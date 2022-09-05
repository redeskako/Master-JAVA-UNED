package es.uned2014.oca.servidor;

import java.net.*;
import java.io.*;
import java.util.*;

import es.uned2014.oca.jugador.*;
import es.uned2014.oca.tablero.Casilla;
import es.uned2014.oca.comunicaciones.*;

/**
 * Se define una clase ClienteThread para que el Servidor se comunique a trav�s de conexiones Socket 
 * de manera simult�nea con los diferentes Clientes.
 * 
 * @author	Alef UNED 2014
 * @version	Oca 3.0
 * @fecha 	Mayo 2014
 */
public class ClienteThread extends Thread{
    // Variables necesarias para establecer una conexi�n Socket.
	private Socket socket;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	
	// Identificador del Cliente: el color del jugador
	private ColorJugador id;
	
	// Mensaje que puede enviar/recibir
	private Comunicacion comunicacion;
	
	// Servidor desde el que se va a gestionar la partida
	private Servidor servidor;
	
	// Jugador con el que participa en la partida
	private Jugador jugador;
	
	// Variable para controlar si el socket permanece a la escucha
	private boolean socketAbierto;

    /**
	 * M�todo constructor: cada hilo se construye con un �nico par�metro: la conexi�n socket del Cliente.
	 * @param Socket del Cliente que se acaba de conectar
	 * @return null
	 * @throws null
	 */
    public ClienteThread (Socket s, ColorJugador c, Servidor ser){
    	// Se asigna la conexi�n recibida como par�metro a la variable socket
    	this.socket = s;
    	// Se asigna el color como id de la conexi�n
    	this.id = c;
    	// Se asigna el Servidor desde el que se van a realizar las conexiones socket
    	this.servidor = ser;
    	
    	this.socketAbierto = true;
    	
    	// Canales de entrada y salida
    	// Se informa en la GUI:
    	String s1 = "\nJugador " + this.id +">>> Intentando establecer los canales de entrada y salida de datos.";
    	servidor.infoConexionesGUI(s1);
    	
    	// Se crean los canales:
    	try{
    		salida = new ObjectOutputStream(socket.getOutputStream());
    		entrada = new ObjectInputStream(socket.getInputStream());
    		
    		// Se informa en la GUI:
    		String s2 = "\nJugador " + this.id +">>> Se ha conectado correctamente con el Servidor.\n";
    		servidor.infoConexionesGUI(s2);
   
    	} catch (IOException e) {
    		servidor.infoConexionesGUI("Se ha producido un error al establecer "
    				+ "los canales de entrada y salida de datos.");
        }
    	
    	// Se a�ade el Jugador al TreeSet<Jugador> de la clase Oca
    	jugador = new Jugador(c);
		this.servidor.getOca().incluirJugador(jugador);		
    } 

    /**
	 * Se env�an unas comunicaciones iniciales al Cliente para configurar su estado inicial:
	 * 1� Se informa de que se ha conectado
	 * 2� Se env�a comunicacion NO_ES_TU_TURNO para que desactive el bot�n "Tirar dado"
	 * 3� Se env�a comunicacion COLOR_JUGADOR para que en la GUI Cliente quede reflejado
	 * @param null
	 * @return null
	 * @throws null
	 */
    public void iniciarCliente(){   	
		// Se env�a comunicacion INFO  de conexi�n correcta
    	String sConexion = "\nJugador " + this.id +">>> Te has conectado correctamente.";
    	Comunicacion comConexion = new Comunicacion(sConexion, TipoComunicacion.INFO);
    	this.enviar(comConexion);
    	
    	// Se env�a comunicacion NO_ES_TU_TURNO para que desactive el bot�n "Tirar dado"
		Comunicacion comNoTurno = new Comunicacion("", TipoComunicacion.NO_ES_TU_TURNO);
		this.enviar(comNoTurno);
		
		// Se env�a comunicacion COLOR_JUGADOR para que en la GUI Cliente quede reflejado
		Comunicacion comColor = new Comunicacion(id.toString(), TipoComunicacion.COLOR_JUGADOR);
		this.enviar(comColor);
    }

    /**
	 * La clase lee permanentemente el inputStream y analiza el tipo de Comunicacion recibida.
	 * Indica las acciones a realizar seg�n el tipo de Comunicacion.
	 * @param null
	 * @return null
	 * @throws Error al recibir Comunicacion por socket
	 */
    public void run(){
		
		while(socketAbierto){
    		try{
    			comunicacion = (Comunicacion) entrada.readObject();  			
    		} catch (Exception e){
    			servidor.infoConexionesGUI("\nSe ha producido un error al recibir una Comunicaci�n. " + e);
    		}  		
    		
    		switch (comunicacion.getTipo()){
    			
    		case TIRADA_DADO:
    			// Lo primero, se desactiva el bot�n para que no tire m�s veces el dado
    			this.enviar(new Comunicacion("", TipoComunicacion.NO_ES_TU_TURNO));
    			
    			// Si es la tirada del dado, se obtiene el int y se ejecuta el an�lisis de la tirada (Oca)
    			String dadoString = comunicacion.getMensaje();
    			int dado = new Integer(dadoString);
    			this.servidor.getOca().analizarTurno(dado);
    			break;
    			
    		case TERMINAR:
    			// Si es un mensaje que informa de que un cliente se ha desconectado:
    			// 1� Se pasa la variable socketAbiero a false para que pare el bucle
    			this.socketAbierto = false;
    			
    			// 2� se cierra el ClienteThread
    			this.cerrar();
    			
    			// 3� Se elimina el Cliente
    	    	this.eliminarCliente(); 	
    			
    		default:
    			// Si es de otro tipo, no se hace nada	
    		}	
    	}
    }
    
    /**
	 * Se cierra el socket y los canales de entrada y salida del mismo.
	 * Se elimina la conexi�n del HashMap del servidor.
	 * Se informa del cierre del cliente.
	 * Se comprueba si se puede seguir jugando. Si no, se cierra todo.
	 * @param null
	 * @return null
	 * @throws Error al cerrar la conexi�n Servidor-Cliente
	 */
    public void cerrar(){
    	this.socketAbierto = false;
    	
    	try{
	    	this.salida.close();
	    	servidor.infoConexionesGUI("\nSe ha cerrado la salida del cliente: "+ this.id);
    	} catch (IOException e){
    		servidor.infoConexionesGUI("\nSe ha producido un error al cerrar la salida del socket "
    				+ "con el Cliente " + this.id);
    	}
    	try{
	    	this.entrada.close();
	    	servidor.infoConexionesGUI("\nSe ha cerrado la entrada del cliente: "+ this.id);
    	} catch (IOException e){
    		servidor.infoConexionesGUI("\nSe ha producido un error al cerrar la entrada del socket "
    				+ "con el Cliente " + this.id);
    	}
    	try{
	    	this.socket.close();
	    	servidor.infoConexionesGUI("\nSe ha cerrado el socket del cliente: "+ this.id);
    	} catch (IOException e){
    		servidor.infoConexionesGUI("\nSe ha producido un error al cerrar el socket con el Cliente " 
    				+ this.id);
    	}
    }
    
    /**
	 * 1 Se elimina este Cliente del HashMap de Clientes que hay en Servidor.
	 * 2 Se informa de la desconexi�n.
	 * 3 Se comprueba el estado de la partida y las conexiones en el momento de recibir la Comunicacion
	 * para tomar las medidas oportunas
	 * se cierra todo
	 * @param null
	 * @return null
	 * @throws null
	 */
    private void eliminarCliente(){
    	// 1� Se elimina la conexi�n del Map<ColorJugador, ClienteThread> mapClientes
    	this.servidor.eliminarCliente(this.id);
    			
    	// 2� Se informa en la GUI:
		String sDes = "\nJugador " + this.id +">>> Se ha desconectado (conexiones actuales " 
				+ this.servidor.getMapClientes().size() + " de " + this.servidor.getOca().getNumeroJugadores() + ")\n";
		servidor.infoConexionesGUI(sDes);
    	
		// 3� Se comprueba el estado de la partida:

		// Si se ha pulsado el bot�n Desconectar de Servidor y se van recibiendo las Comunicaciones de desconexi�n:
		if (!this.servidor.getServidorGUI().getServidorConectadoBoton()){
			
			// Si la partida no est� comenzada, se cierra el ServerSocket cuando queda un �nico jugador
	    	if (!this.servidor.getOca().getPartidaComenzada()){
	    		this.servidor.comprobarClientes();
	    	}
	    	
	    	// Si la partida est� comenzada y no terminada (se desconecta en mitad de la partida):
	    	if (this.servidor.getOca().getPartidaComenzada()){
	    		
	    		// Se elimina el jugador de la partida
	    		this.eliminarJugador();
	    		
	    		// Se comprueba si es el �ltimo cliente, para cerrar el ServerSocket
	    		this.servidor.comprobarClientes();
	    	}	
		}
		// Si no se ha pulsado el bot�n Desconectar de Servidor
		else {
			// Si la partida no est� comenzada (un Cliente se ha ido antes de empezar a jugar)
	    	if (!this.servidor.getOca().getPartidaComenzada()){
	    		// Se informa a los dem�s clientes de la desconexi�n
	   			Comunicacion comDes = new Comunicacion(sDes, TipoComunicacion.INFO);
	    		this.servidor.enviarATodos(comDes);	
	    	}
	    	
			// Si la partida est� comenzada y no terminada (un Cliente se desconecta en mitad de la partida):
			if (this.servidor.getOca().getPartidaComenzada() && !this.servidor.getOca().getJuegoTerminado()){
				// Se informa a los dem�s clientes de la desconexi�n
				Comunicacion comDes = new Comunicacion(sDes, TipoComunicacion.INFO);
				this.servidor.enviarATodos(comDes);
				
				// Se elimina el jugador de la partida
				this.eliminarJugador();
				
				// Se comprueba si quedan suficientes
				this.servidor.comprobarJugadores();
			}
			
			// 3-3 Si la partida est� comenzada y terminada (final de la partida)
			if (this.servidor.getOca().getPartidaComenzada() && this.servidor.getOca().getJuegoTerminado()){
				// Se elimina el jugador de la partida
				this.eliminarJugador();
				
				// Si no quedan Clientes, se reinicia el servidor
				if (this.servidor.getMapClientes().size() == 0){
					// Si no se ha desconectado con el bot�n, vuelve a quedar a la espera
					this.servidor.reiniciarServidor();
				}
			}
		}			
    }
    
    /**
	 * Se elimina este Jugador de la partida de la Oca.
	 * 1� Si es el jugador activo, pasa el turno al siguiente.
	 * 2� Sea como sea, Se elimina del TreeSet<Jugador>, del ArrayList<Jugador> y del 
	 * HashMap<Jugador, Casilla>
	 * 3� Se resta un jugador al n�mero de jugadores de Oca.
	 * @param null
	 * @return null
	 * @throws null
	 */
    private void eliminarJugador(){
    	
    	// Se comprueba si es el jugador activo. Si es as� se pasa el turno
    	if (this.jugador == this.servidor.getOca().getJugActivo()){
    		// Se pasa el turno
    		this.servidor.getOca().comprobarJugadores();
    	}
    	
    	// Se elimina el jugador de la partida: se elimina el Jugador de tsJugador 
		// (TreeSet), de jugadoresArray (ArrayList) y de jugadorCasilla (HashMap) 
    	Set<Jugador> set = this.servidor.getOca().getTsJugador();
		set.remove(jugador);
		this.servidor.getOca().setTsJugador(set);
		
		ArrayList<Jugador> arr = this.servidor.getOca().getJugadoresArray();
		arr.remove(jugador);
		this.servidor.getOca().setJugadoresArray(arr);
		
		HashMap<Jugador, Casilla> map = this.servidor.getOca().getJugadorCasilla();
		map.remove(jugador);
		this.servidor.getOca().setJugadorCasilla(map);

		// Se resta 1 en el n�mero de jugadores
		int i = this.servidor.getOca().getNumeroJugadores();
		this.servidor.getOca().setNumeroJugadores(i--);
    }
    
    
    
    

	/** 
	 * M�todo para obtener el contenido de la variable salida.
	 * @param null
	 * @return Devuelve la variable salida
	 * @throws null
	 */
	public ObjectOutputStream getSalida() {
		return salida;
	}

	/** 
	 * M�todo para obtener el contenido de la variable entrada.
	 * @param null
	 * @return Devuelve la variable entrada
	 * @throws null
	 */
	public ObjectInputStream getEntrada() {
		return entrada;
	}

	/** 
	 * M�todo para obtener el contenido de la variable id (ColorJugador).
	 * @param null
	 * @return Devuelve la variable id (ColorJugador)
	 * @throws null
	 */
	public ColorJugador getColorId() {
		return id;
	}
	
	
	
	
	
	/**
	 * M�todo para enviar Comunicacion por socket a un cliente (identificado por su color)
	 * @param color
	 * @param comunicacion
	 * @return null
	 * @throws IOException si hay error al enviar objeto por socket
	 */
	public void enviar(Comunicacion comunicacion){
		try{
			this.salida.writeObject(comunicacion);
		} catch (IOException e){
			this.servidor.infoConexionesGUI("\nNo se ha podido enviar un mensaje. "
					+ "El socket del Cliente " + this.id + " est� cerrado.");
		}
	}

}
