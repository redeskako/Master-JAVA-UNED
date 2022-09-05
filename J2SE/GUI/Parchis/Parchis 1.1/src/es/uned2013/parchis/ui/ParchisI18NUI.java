package es.uned2013.parchis.ui;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import es.uned2013.parchis.Casilla;
import es.uned2013.parchis.Colores;
import es.uned2013.parchis.Ficha;
import es.uned2013.parchis.Jugador;
import es.uned2013.parchis.Tablero;
import es.uned2013.parchis.rmi.server.ParchisServer;

/**
 * Gestiona la presentación de mensajes por pantalla e interacción con el usuario haciendo 
 * uso de la internacionalización.
 * Implementa de ParchisUI.
 * @author alef
 */
public class ParchisI18NUI implements ParchisUI {

	private ParchisUI ui= null; // Referencia a la interfaz (CONSOLA o GUI).
	private ParchisServer parchis = null; // Referencia al servidor del parchís.
	private int index; // Indice del cliente.
	private ResourceBundle rb = null; // Referencia al fichero *.properties.
	 
	/**
	 * Clase constructora. Inicializa el resourceBundle, que se encarga de identificar con qué
	 * idioma estamos trabajando y presentar en pantalla los mensajes adecuados.
	 * @param ui -> define el método de presentación a usar (consola, GUI)
	 * @param loc -> idioma a utilizar
	 * @param parchis -> servidor del parchís
	 * @param index -> índice del cliente
	 */
	 public ParchisI18NUI(ParchisUI ui, Locale loc, ParchisServer parchis, 
			 int index){
		 this.ui = ui;
		 this.parchis = parchis;
		 this.index = index;
		 this.rb = ResourceBundle.getBundle("Mensajes", loc); //ataca al fichero de properties
		 													  //en cuestión.
	 }
	 /**
	  * Se encarga de mostrar el mensaje de entrada según el método elegido
	  * @param mensaje Mensaje a mostrar
	  */
	@Override
	public void mostrarMensaje(String mensaje) throws RemoteException {
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
	public void mostrarMensajeString(String mensaje, String cadena)  throws RemoteException{
		String str = rb.getString(mensaje);
		this.ui.mostrarMensajeString(str, cadena);
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
		String str = rb.getString(mensaje);
		this.ui.mostrarMensajeInteger(str, numero);
		
	}

	/**
	  * Gestiona la recepción de valores enteros por pantalla y posibles excepciones en la
	  * entrada.
	  * @param mensaje Mensaje a mostrar para pedir valor a introducir
	  * @return entero resultado válido de la comprobación
	  */
	@Override
	public int solicitarEntero(String mensaje) throws RemoteException {
		String str = rb.getString(mensaje);
		return this.ui.solicitarEntero(str);
	}

	/**
	  * Gestiona la recepción de cadenas por pantalla. Se utilizaría en el caso de solicitar
	  * el nombre de los jugadores por pantalla, por ejemplo.
	  * @param mensaje Mensaje a leer del usuario
	  * @return String resultado válido de la comprobación.
	 * @throws InterruptedException 
	  */
	@Override
	public String solicitarCadena(String mensaje)  throws RemoteException, InterruptedException{
		String str = rb.getString(mensaje);
		return this.ui.solicitarCadena(str);
	}

	/**
	  * Muestra la tirada de cada jugador por pantalla.
	  * @param nombre Nombre del jugador
	  * @param tirada Valor de la tirada
	  */
	@Override
	public void mostrarTirada(String nombre, int tirada) throws RemoteException{
		// A través del servidor del parchís, se obtiene
		// el jugador que tiene el turno.
		int jugadorTurno = parchis.getJugadorTurno();
			
		if (index != jugadorTurno)
			this.ui.mostrarMensajeInteger(rb.getString(ParchisI18Keys.ELJUGADOR) + " " +
					nombre + " " + rb.getString(ParchisI18Keys.HASACADO), tirada);
		else
			this.ui.mostrarMensajeInteger(rb.getString(ParchisI18Keys.HASACADO_ENTURNO), tirada);
		this.ui.mostrarTirada(nombre, tirada);
	} 
	
	/**
	 * Muestra la representación en forma de cadena de texto
	 * que corresponde a un número de casilla
	 * @param numCasilla -> Número de casilla
	 */
	private String numCasillaToString(int numCasilla) {
		String str = "";
		
		// Si no es una casilla normal,
		// examinamos el resto de dividir
		// el número de casilla por 100
		// para determinar qué tipo de
		// casilla es
		
		if (numCasilla >= 100) {
			switch (numCasilla % 100) {
				case 0:
					// Si es casilla de casa
					str = rb.getString(ParchisI18Keys.CASA);
					break;
				case 1: case 2: case 3: case 4: case 5: case 6: case 7:
					// Si es casilla de pasillo
					str = rb.getString(ParchisI18Keys.PASILLO) + (numCasilla % 100);
					break;
				case 8:
					// Si es casilla final
					str = rb.getString(ParchisI18Keys.FINAL);
					break;
			}
		}
		else {
			// Si es la casilla de salida de algún color
			if ((numCasilla == Colores.AMARILLO.getCasillaSalida()) ||
				(numCasilla == Colores.AZUL.getCasillaSalida()) ||
				(numCasilla == Colores.ROJO.getCasillaSalida()) ||
				(numCasilla == Colores.VERDE.getCasillaSalida()))
				str = rb.getString(ParchisI18Keys.SALIDA);
			else
				// Si es una casilla normal
				str = Integer.toString(numCasilla);
		}
		return str;	
	}
	
	/**
	  * Muestra la situación del tablero para todos los jugadores de forma tabulada.
	  * @param Tablero tablero de juego
	  */
	@Override
	public void mostrarTablero(Tablero tablero)  throws RemoteException{
		ArrayList<Jugador> jugadores = parchis.getJugadores();
		String str;
		int idFicha, numCasilla;
		
		// Sólo mostraremos el tablero como texto en modo consola.
		if (getUIMode().equals(ParchisUIMode.CONSOLE)) {
			str = "\n";
			//Imprimo los nombres de los jugadores
			for(Jugador jugador: jugadores){
				str += String.format("%11s", jugador.getNombre()) + "\t";
			}
			str += "\n";
			//Imprimo un separador para cada jugador
			for(int i = 0; i < jugadores.size(); i++){
				str = str + "-----------" + "\t";
			}
			str += "\n";
			
			//imprimo para cada jugador y ficha, los valores actuales de casilla
			for (int i = 0; i < 4; i++) {  
				for (Jugador jug: jugadores) {
					idFicha = jug.getFichas().get(i).getIdentificador();
					numCasilla = jug.getFichas().get(i).getCasillaActual();
					str += String.format("%3d  %6s", idFicha, 
							numCasillaToString(numCasilla)) + "\t";
				}
				str += "\n";
			}
			//presento resultado final por consola
			this.ui.mostrarMensaje(str);
		}
		this.ui.mostrarTablero(tablero);
	}

	/**
	  * Muestra la información de la ficha entrada.
	  * @param Ficha ficha a sacar por pantalla
	  */
	@Override
	public void mostrarFicha(Ficha ficha) throws RemoteException {
		this.ui.mostrarMensaje(String.format("%3d  %6s", ficha.getIdentificador(), 
				numCasillaToString(ficha.getCasillaActual())));
		this.ui.mostrarFicha(ficha);
	}
	
	/**
	 * Presenta un mensaje informando de cómo se mueve una ficha.
	 * @param Casilla_old destino
	 * @param Ficha ficha
	 */
	@Override
	public void moverFicha(Casilla destino, Ficha ficha) throws RemoteException {
		// A través del servidor del parchís, se obtiene el array de 
		// jugadores y el jugador que tiene el turno.
		ArrayList<Jugador> jugadores = parchis.getJugadores();
		int jugadorTurno = parchis.getJugadorTurno();
		String str;
		
		str = rb.getString(ParchisI18Keys.MOVIENDOFICHA) + " " +
				ficha.getIdentificador();
		// Si el jugador no es el que tiene el turno, se muestra el
		// nombre de este último
		if (index != jugadorTurno)
			str += " " + rb.getString(ParchisI18Keys.DELJUGADOR) + " " +
					jugadores.get(jugadorTurno).getNombre();
		str += " " + rb.getString(ParchisI18Keys.ACASILLA) + ": " + 
					numCasillaToString(destino.getNumero()) + ".";
		
		// Mostramos el mensaje y llamamos de nuevo a 'moverFicha()'
		// a través de su interfaz concreto (CONSOLA o GUI).
		this.ui.mostrarMensaje(str);
		
		this.ui.moverFicha(destino, ficha);
	}

	/**
	 * Presenta un mensaje informando de la eliminación de una ficha por otro jugador.
	 * @param Ficha ficha
	 */
	@Override
	public void comerFicha(Ficha ficha, Casilla destino)  throws RemoteException {
		// A través del servidor del parchís, se obtiene el array de 
		// jugadores y el jugador que tiene el turno.
		ArrayList<Jugador> jugadores = parchis.getJugadores();
		int jugadorTurno = parchis.getJugadorTurno();
		String str;
		
		if ((jugadorTurno != -1) && // Para evitar errores
			(!ficha.getColor().equals(jugadores.get(jugadorTurno).getColor()))) {
			// Si el color de la ficha comida no coincide con el del 
			// jugador que tiene el turno, es porque este último se ha 
			// comido la ficha de otro.
			str = rb.getString(ParchisI18Keys.FICHACOMIDA) + " " +
					ficha.getIdentificador();
			// Si el jugador no es al que le han comido su ficha
			// mostramos el nombre de este último
			if (index != ficha.getColor().getId())
				str += " " + rb.getString(ParchisI18Keys.DELJUGADOR) + " " +
						jugadores.get(ficha.getColor().getId()).getNombre();
		}
		else {
			// En caso contrario, es porque se obliga al jugador en turno
			// a devolver la ficha a casa por haber sacado un tercer seis.
			str = rb.getString(ParchisI18Keys.DEVUELVOCASA) + " " +
					ficha.getIdentificador();
			// Si el jugador no es el que tiene el turno, se muestra el
			// nombre de este último
			if (index != jugadorTurno)
				str += " " + rb.getString(ParchisI18Keys.DELJUGADOR) + " " +
						jugadores.get(jugadorTurno).getNombre();
		}
		str += ".";
		
		// Mostramos el mensaje y llamamos de nuevo a 'comerFicha()'
		// a través de su interfaz concreto (CONSOLA o GUI).
		this.ui.mostrarMensaje(str);
		this.ui.comerFicha(ficha, destino);
	}
	
	/**
	 * Muestra el jugador que tiene el turno en un mensaje traducido a 
	 * un idioma concreto, y vuelve a llamar al método cambiarTurno(),
	 * todo ello a través del UI correspondiente (CONSOLE o GUI).
	 * @param color
	 * @throws RemoteException
	 */
	@Override
	public void cambiarTurno(Colores color) throws RemoteException {
		// A través del servidor del parchís, se obtiene el array de 
		// jugadores y el jugador que tiene el turno.
		ArrayList<Jugador> jugadores = parchis.getJugadores();
		int jugadorTurno = parchis.getJugadorTurno();
				
		if (index != jugadorTurno)
			this.ui.mostrarMensajeString(rb.getString(ParchisI18Keys.JUGADORENTURNO),
					jugadores.get(jugadorTurno).getNombre());		
		else
			this.ui.mostrarMensajeString(rb.getString(ParchisI18Keys.JUGADORENTURNO_ENTURNO),
					jugadores.get(jugadorTurno).getNombre());
		this.ui.cambiarTurno(color);
	}
	
	/**
	 * Llama al método inicioJuegoUI() del interfaz de usuario
	 * correspondiente (CONSOLE o GUI), para gestionar las acciones
	 * previas sobre los controles del mismo.
	 * @throws RemoteException
	 */
	@Override
	public void inicioJuegoUI() throws RemoteException {
		this.ui.inicioJuegoUI();
	}
	
	/**
	 * Retorna el modo de interfaz del UI correspondiente (CONSOLE o GUI).
	 * @return this.ui.getUIMode() (ParchisUIMode)
	 * @throws RemoteException
	 */
	@Override
	public ParchisUIMode getUIMode() throws RemoteException {
		return this.ui.getUIMode();
	}
	
	
	@Override
	public void abandonarPartida(Boolean esServidor, int index) throws RemoteException {
		this.ui.abandonarPartida(esServidor, index);
	}
}
