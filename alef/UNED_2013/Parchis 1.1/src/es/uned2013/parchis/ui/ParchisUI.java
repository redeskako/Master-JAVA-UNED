package es.uned2013.parchis.ui;

import java.rmi.Remote;
import java.rmi.RemoteException;

import es.uned2013.parchis.Casilla;
import es.uned2013.parchis.Ficha;
import es.uned2013.parchis.Tablero;
import es.uned2013.parchis.Colores;

/**
 * Clase interfaz de interacción con el jugador. Implementa el modelo vista controlador. 
 * Presenta los mensajes en el formato adecuado independientemente de si es por consola 
 * o por GUI, en inglés, español o el idioma elegido.
 * @author alef
 *
 */
public interface ParchisUI extends Remote{

	/**
	  * Se encarga de mostrar el mensaje de entrada según el método elegido
	  * @param mensaje Mensaje a mostrar
	  */
	public void mostrarMensaje(String mensaje)  throws RemoteException;
	
	/**
	  * Se encarga de mostrar el mensaje de entrada y una cadena de entrada aparte.
	  * @param mensaje Mensaje a mostrar
	  * @param cadena cadena de caracteres a añadir al mensaje
	  */
	public void mostrarMensajeString(String mensaje, String cadena)  throws RemoteException;
	
	/**
	  * Se encarga de mostrar el mensaje de entrada y un entero aparte.
	  * @param mensaje Mensaje a mostrar
	  * @param numero entero a añadir al mensaje
	  */
	public void mostrarMensajeInteger(String mensaje, int numero)  throws RemoteException;
	
	/**
	  * Gestiona la recepción de valores enteros por pantalla y posibles excepciones en la
	  * entrada.
	  * @param mensaje Mensaje a mostrar para pedir valor a introducir
	  * @return entero resultado válido de la comprobación
	  */
	public int solicitarEntero(String mensaje)  throws RemoteException;
	
	/**
	  * Gestiona la recepción de cadenas por pantalla. Se utilizaría en el caso de solicitar
	  * el nombre de los jugadores por pantalla, por ejemplo.
	  * @param mensaje Mensaje a leer del usuario
	  * @return String resultado válido de la comprobación.
	 * @throws InterruptedException 
	  */
	public String solicitarCadena(String mensaje)  throws RemoteException, InterruptedException;
	
	/**
	  * Muestra la tirada de cada jugador por pantalla.
	  * @param nombre Nombre del jugador
	  * @param tirada Valor de la tirada
	  */
	public void mostrarTirada(String nombre, int tirada)  throws RemoteException;
	
	/**
	  * Muestra la situación del tablero para todos los jugadores de forma tabulada.
	  * @param Tablero tablero de juego
	  */
	public void mostrarTablero(Tablero tablero)  throws RemoteException;
	
	/**
	  * Muestra la información de la ficha entrada.
	  * @param Ficha ficha a sacar por pantalla
	  */
	public void mostrarFicha(Ficha ficha)  throws RemoteException;
	
	/**
	 * Presenta un mensaje informando de cómo se mueve una ficha.
	 * @param Casilla_old destino
	 * @param Ficha ficha
	 */
	public void moverFicha(Casilla destino,Ficha ficha)  throws RemoteException;
	
	/**
	 * Presenta un mensaje informando de la eliminación de una ficha por otro jugador.
	 * @param Ficha ficha
	 */
	public void comerFicha(Ficha ficha, Casilla destino)  throws RemoteException;
	
	/**
	 * Gestiona el cambio de turno. En modo gráfico cambia el color de la etiqueta
	 * que identifica el color del jugador en turno.
	 * @param color
	 * @throws RemoteException
	 */
	public void cambiarTurno(Colores color) throws RemoteException;
	
	/**
	 * Gestiona las acciones previas a realizar sobre los controles del
	 * interfaz de usuario cuando comienza el juego del parchís.
	 * @throws RemoteException
	 */
	public void inicioJuegoUI() throws RemoteException;
	
	/**
	 * Retorna el modo de interfaz de usuario
	 * @return ParchisUIMode.GUI (ParchisUIMode)
	 * @throws RemoteException
	 */
	public ParchisUIMode getUIMode() throws RemoteException;
	
	/**
	 * Muestra un mensaje en una ventana emergente y finaliza el programa
	 * @param esServidor (Boolean) -> Indica si es el servidor el que ha cerrado el juego.
	 * @param index (int) -> Indice del cliente que abandona (-1 = servidor o LOCAL).
	 * @throws RemoteException
	 */
	public void abandonarPartida(Boolean esServidor, int index) throws RemoteException;
}
