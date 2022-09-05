package es.uned2013.parchis.ui;

import java.util.Locale;
import java.util.ResourceBundle;

import es.uned2013.parchis.Casilla;
import es.uned2013.parchis.Ficha;
import es.uned2013.parchis.Tablero;

/**
 * Gestiona la presentación de mensajes por pantalla e interacción con el usuario haciendo 
 * uso de la internacionalización.
 * Implementa de ParchisUI.
 * @author alef
 */
public class ParchisI18NUI implements ParchisUI {

	private ParchisUI ui= null;
	private Locale loc = null;
	private ResourceBundle rb =null;
	 
	/**
	 * Clase constructora. Inicializa el resourceBundle, que se encarga de identificar con qué
	 * idioma estamos trabajando y presentar en pantalla los mensajes adecuados.
	 * @param ui define el método de presentación a usar (consola, GUI)
	 * @param loc idioma a utilizar
	 */
	 public ParchisI18NUI(ParchisUI ui,Locale loc){
		 this.ui = ui;
		 this.loc = loc;
		 this.rb = ResourceBundle.getBundle("Mensajes", loc); //ataca al fichero de properties
		 													  //en cuestión.
	 }
	 /**
	  * Se encarga de mostrar el mensaje de entrada según el método elegido
	  * @param mensaje Mensaje a mostrar
	  */
	@Override
	public void mostrarMensaje(String mensaje) {
		// TODO Auto-generated method stub

		String str = rb.getString(mensaje); //obtiene el mensaje de el fichero de properties
		this.ui.mostrarMensaje(str);
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
		String str = rb.getString(mensaje);
		this.ui.mostrarMensaje(str + cadena);
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
		String str = rb.getString(mensaje);
		this.ui.mostrarMensaje(str + numero);
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
		
		String str = rb.getString(mensaje);
		return this.ui.solicitarEntero(str);
		//return 0;
	}

	/**
	  * Gestiona la recepción de cadenas por pantalla. Se utilizaría en el caso de solicitar
	  * el nombre de los jugadores por pantalla, por ejemplo.
	  * @param mensaje Mensaje a leer del usuario
	  * @return String resultado válido de la comprobación.
	  */
	@Override
	public String solicitarCadena(String mensaje) {
		// TODO Auto-generated method stub
		String str = rb.getString(mensaje);
		this.ui.solicitarCadena(str);
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
		this.ui.mostrarTirada(rb.getString(ParchisI18Keys.ELJUGADOR) + nombre + " " + rb.getString(ParchisI18Keys.HASACADO), tirada);
	} 
	
	/**
	  * Muestra la situación del tablero para todos los jugadores de forma tabulada.
	  * @param Tablero tablero de juego
	  */
	@Override
	public void mostrarTablero(Tablero tablero) {
		// TODO Auto-generated method stub
		this.ui.mostrarTablero(tablero);
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
	 * Presenta un mensaje informando de cómo se mueve una ficha.
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
