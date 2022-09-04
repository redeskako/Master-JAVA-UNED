package es.uned2013.parchis.ui;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.rmi.RemoteException;

import es.uned2013.parchis.Casilla;
import es.uned2013.parchis.Colores;
import es.uned2013.parchis.Ficha;
import es.uned2013.parchis.Tablero;
import es.uned2013.parchis.rmi.server.ParchisServer;

/**
 * Gestiona la presentación de mensajes por consola e interacción con los jugadores
 * Al implementar de ParchisUI, por la propia estructura, es necesario que todos los métodos
 * estén definidos, incluso cuando no se utilicen.
 */
public class ParchisConsola implements ParchisUI {

	 private LineNumberReader lnReader = new LineNumberReader(new InputStreamReader(System.in));
	 private ParchisServer parchis= null;
	 
	 /**
	  * Constructor de la clase. Inicializa parchis
	  * @param parchis
	  */
	 public ParchisConsola(ParchisServer parchis){
		 this.parchis = parchis;
	 }
	
	 /*
	  * Todos los override en métodos en esta clase sobrecargan a los métodos de la clase
	  * ParchisUI.
	  */
	 /**
	  * Se encarga de mostrar el mensaje de entrada.
	  * @param mensaje Mensaje a mostrar
	  */
	@Override
	public void mostrarMensaje(String mensaje) throws RemoteException {
		System.out.println(mensaje);
	}
	
	/**
	  * Se encarga de mostrar el mensaje de entrada y una cadena de entrada aparte.
	  * Este método se crea por la necesidad de independizar el interfaz ParchisUI de la 
	  * presentación final. Independizar el mensaje a mostrar con la forma en que se presenta.
	  * @param mensaje Mensaje a mostrar
	  * @param cadena cadena de caracteres a añadir al mensaje
	  */
	@Override
	public void mostrarMensajeString(String mensaje, String cadena)  throws RemoteException{
		System.out.println(mensaje + " " + cadena + ".");
	}
	
	/**
	  * Se encarga de mostrar el mensaje de entrada y un entero aparte.
	  * Este método se crea por la necesidad de independizar el interfaz ParchisUI de la 
	  * presentación final. Independizar el mensaje a mostrar con la forma en que se presenta.
	  * @param mensaje Mensaje a mostrar
	  * @param numero entero a añadir al mensaje
	  */
	@Override
	public void mostrarMensajeInteger(String mensaje, int numero)  throws RemoteException{
		System.out.println(mensaje + " " + numero + ".");
	}
	
	/**
	  * Gestiona la recepción de valores enteros por pantalla y posibles excepciones en la
	  * entrada.
	  * @param mensaje Mensaje a mostrar para pedir valor a introducir
	  * @return entero resultado válido de la comprobación
	  */
	@Override
	public int solicitarEntero(String mensaje)  throws RemoteException {
		int out = -1;
		
		System.out.print(mensaje);
		
		boolean valido = false;
		
		while(!valido){
			try{
			String leido = lnReader.readLine();
			out = Integer.parseInt(leido);
			valido = true;
			}catch(Exception e){
				mostrarMensaje(mensaje);
			}
		}
		
		return out;
	}
	
	/**
	  * Gestiona la recepción de cadenas por pantalla. Se utilizaría en el caso de solicitar
	  * el nombre de los jugadores por pantalla, por ejemplo.
	  * @param mensaje Mensaje a leer del usuario
	  * @return String resultado válido de la comprobación.
	  */
	public String solicitarCadena(String mensaje)  throws RemoteException{
		String out= "";
		
		System.out.println(mensaje);
		
		try{
			out = lnReader.readLine();
		}
		catch(Exception e){
			//TODO:Mostrar error.
			mostrarMensaje("Error al leer.");
		}
		
		return out;
	}
	
	/**
	  * Muestra la tirada de cada jugador por pantalla.
	  * @param nombre Nombre del jugador
	  * @param tirada Valor de la tirada
	  */
	@Override
	public void mostrarTirada(String nombre, int tirada)  throws RemoteException{
		mostrarMensaje(nombre + " " + tirada + ".");
	}
	
	/**
	  * Muestra la situación del tablero para todos los jugadores de forma tabulada.
	  * @param Tablero tablero de juego
	  */
	@Override
	public void mostrarTablero(Tablero tablero)  throws RemoteException {
		// No necesario en modo consola.
	}
	
	/**
	  * Muestra la información de la ficha de entrada.
	  * @param Ficha ficha a sacar por pantalla
	  */
	@Override
	public void mostrarFicha(Ficha ficha)  throws RemoteException {
		// No necesario en modo consola.
	}

	/**
	 * Presenta un mensaje informando de cómo se mueve una ficha.
	 * @param Casilla_old destino
	 * @param Ficha ficha
	 */
	@Override
	public void moverFicha(Casilla destino, Ficha ficha)  throws RemoteException{
		// No necesario en modo consola.
	}
	/**
	 * Presenta un mensaje informando de la eliminación de una ficha por otro jugador.
	 * @param Ficha ficha
	 */
	@Override
	public void comerFicha(Ficha ficha, Casilla destino)  throws RemoteException {
		// No necesario en modo consola.
	}

	/**
	 * Gestiona el cambio de turno.
	 * @param color
	 * @throws RemoteException
	 */
	@Override
	public void cambiarTurno(Colores color) throws RemoteException {
		// No necesario en modo consola.
	}
	
	/**
	 * Gestiona las acciones previas a realizar sobre la consola
	 * cuando comienza el juego del parchís.
	 * @throws RemoteException
	 */
	@Override
	public void inicioJuegoUI() throws RemoteException {
		// No necesario en modo consola.
	}
	
	/**
	 * Retorna el modo de interfaz (CONSOLE)
	 * @return ParchisUIMode.CONSOLE (ParchisUIMode)
	 * @throws RemoteException
	 */
	@Override
	public ParchisUIMode getUIMode() throws RemoteException {
		return ParchisUIMode.CONSOLE;
	}

	@Override
	public void abandonarPartida(Boolean esServidor, int index) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}
