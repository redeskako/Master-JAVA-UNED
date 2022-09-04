package es.uned2013.parchis.ui;

import javax.swing.JOptionPane;

import es.uned2013.parchis.Casilla;
import es.uned2013.parchis.Ficha;
import es.uned2013.parchis.Parchis;
import es.uned2013.parchis.Tablero;

/**
 * Gestiona la interacción con los jugadores en el modo GUI
 * Al implementar de ParchisUI, por la propia estructura, es necesario que todos los métodos
 * estén definidos, incluso cuando no se utilicen.
 * Preparada para una posible ampliación futura.
 */
public class ParchisGUI implements ParchisUI {

 private Parchis parchis= null;
	 
	 /**
	  * Constructor de la clase. Inicializa parchis
	  * @param parchis
	  */
	 public ParchisGUI(Parchis parchis){
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
	public void mostrarMensaje(String mensaje) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, mensaje);

	}
	
	/**
	  * Se encarga de mostrar el mensaje de entrada y una cadena de entrada aparte.
	  * Este método se crea por la necesidad de independizar el interfaz ParchisUI de la 
	  * presentación final. Independizar el mensaje a mostrar con la forma en que se presenta.
	  * @param mensaje Mensaje a mostrar
	  * @param cadena cadena de caracteres a añadir al mensaje
	  */
	@Override
	public void mostrarMensajeString(String mensaje, String cadena){
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, mensaje+cadena);
	}
	
	/**
	  * Se encarga de mostrar el mensaje de entrada y un entero aparte.
	  * Este método se crea por la necesidad de independizar el interfaz ParchisUI de la 
	  * presentación final. Independizar el mensaje a mostrar con la forma en que se presenta.
	  * @param mensaje Mensaje a mostrar
	  * @param numero entero a añadir al mensaje
	  */
	@Override
	public void mostrarMensajeInteger(String mensaje, int numero){
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, mensaje+numero);
	}
	
	/**
	  * Gestiona la recepción de valores enteros por pantalla y posibles excepciones en la
	  * entrada.
	  * @param mensaje Mensaje a mostrar para pedir valor a introducir
	  * @return entero resultado válido de la comprobación
	  */
	@Override
	public int solicitarEntero(String mensaje) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	  * Gestiona la recepción de cadenas por pantalla. Se utilizaría en el caso de solicitar
	  * el nombre de los jugadores por pantalla, por ejemplo.
	  * @param mensaje Mensaje a leer del usuario
	  * @return String resultado válido de la comprobación.
	  */
	@Override
	public String solicitarCadena(String mesaje) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	  * Muestra la tirada de cada jugador por pantalla.
	  * @param nombre Nombre del jugador
	  * @param tirada Valor de la tirada
	  */
	@Override 
	public void mostrarTirada(String nombre, int tirada){
		// TODO Auto-generated method stub
	}
	
	/**
	  * Muestra la situación del tablero para todos los jugadores de forma tabulada.
	  * @param Tablero tablero de juego
	  */
	@Override
	public void mostrarTablero(Tablero tablero) {
		// TODO Auto-generated method stub

	}

	/**
	  * Muestra la información de la ficha entrada.
	  * @param Ficha ficha a sacar por pantalla
	  */
	@Override
	public void mostrarFicha(Ficha ficha) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Presenta información de cómo se mueve una ficha.
	 * @param Casilla_old destino
	 * @param Ficha ficha
	 */
	@Override
	public void moverFicha(Casilla destino, Ficha ficha) {
		// TODO Auto-generated method stub

	}

	/**
	 * Presenta un mensaje informando de la eliminación de una ficha por otro jugador.
	 * @param Ficha ficha
	 */
	@Override
	public void comerFicha(Ficha ficha) {
		// TODO Auto-generated method stub

	}

}
