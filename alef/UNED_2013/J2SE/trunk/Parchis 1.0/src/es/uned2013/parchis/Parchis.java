package es.uned2013.parchis;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Arrays;

import es.uned2013.parchis.ui.ParchisConsola;
import es.uned2013.parchis.ui.ParchisGUI;
import es.uned2013.parchis.ui.ParchisI18Keys;
import es.uned2013.parchis.ui.ParchisI18NUI;
import es.uned2013.parchis.ui.ParchisUI;
import es.uned2013.parchis.ui.ParchisUIMode;

/**
 * Clase principal del programa. Configura el juego y 
 * establece el valor de las variables principales
 * mediante un diálogo con el usuario a través de la
 * consola.
 * @author alef
 */

public class Parchis {
	
	private ArrayList<Ficha> fichasJuego = new ArrayList<Ficha>(); //Fichas de la partida actual.
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>(); //Jugadores de la partida actual.
	private Jugador jugadorTurno; //Jugador en turno. Va cambiando rotativamente a lo largo de la partida.
	private Tablero tablero; //Tablero de la partida actual. 
	private ArrayList<Colores> coloresJuego = new ArrayList<Colores>(); //Colores de la partida actual.
	
	//Variables para el test de integración de JUnit
	/*Public y Protected para evitar generar métodos únicamente validos para las pruebas
	 *Si la prueba se encontrase en el mismo paquete no sería necesaria ninguna variable 'public'
	 *De esta manera queda más claro el código y la estructura del proyecto. 
	 */
	protected int jugadas; //Nº de jugadas en la partida actual.
	private static Modo modo = Modo.NORMAL; //Modo de funcionamiento NORMAL o PRUEBA
	public static int jugadaLimitePrueba = -1; //El juego se parará en el nº de jugada especificado. (-1) no se para
	protected final static Iterator<Integer> listaTestTirada = Arrays.asList(5,4,3,2,4,5,6,3,1, //Tiradas realizadas en la partida Test
			5,4,5,5,3,5,5,5,6,4,5,5,5,6,4,3,5,4,3,4,5,6,6,4,5,1,4,1/*37*/,4,4,1,3,
			6,4,4,4,5,6,3,4,6,3,5/*52*/,5,6,2,6,6,1,5,4,6,6,6,5,6,6,5,6,2,5,6,5,6,6,3
			,2,4,3,4/*79*/,6,6,5,6,5,6,5,6,6,6/*89*/,6,6,5,2,2,6,6,5,3,5,6,4,6,5/*103*/
			,2,6,4,2,6,6,4,6,6,2,3,5,6,1/*117*/,6,6,1,5,5,5,6,4,6,6,5,2,5,3).iterator();
	protected final static Iterator<Integer> listaTestEligeFicha = Arrays.asList(1,6,1,2,1,10,14, //Fichas seleccionadas en la partida Test
			3,1,7,13,1,5,11,14,14,14,8,9,13/*37*/,3,6,9,14,3,3,5,12,11,10,3,3,10,10,14/*52*/
			,6,6,11,11,11,14,1,1,5,6,13,13,3,3,1,9,16,16,16,3,1,6,10,14,14/*79*/
			,1,3,4,2,11,15,14/*89*/,3,4,2,6,9,16,16,16,13,2,2,10,10,14,15/*103*/
			,4,4,5,5,10,10,15,15,15,3,3,2,4,6,16,15/*117*/,2,2,2,2,5,5,10,9).iterator();
	
	//Variable de interfaz
	private static ParchisUI ui = null;

	public Parchis(){

		/**
		 * CONSTRUCTOR. Según la clase Driver proporcionada, sin argumentos.
		 **/
	}
	
	/**
	 * Metodo para asignar el modo grafico.
	 */
	public void setUI(ParchisUIMode mode,Locale loc){
		ParchisUI inner =  new ParchisConsola(this);
		
		switch(mode){
			// interfaz con entrada/salida de datos por consola
			case CONSOLE:
				inner = new ParchisConsola(this); 
				break;
			// interfaz con entrada/salida de datos en modo gráfico
			case GUI:		
				inner = new ParchisGUI(this);
				break;
		}
		
		/* Si no hay información del contexto local del usuario
		 * se carga el idioma por defecto.
		 * Si la hubiera se busca un archivo 'properties' en el 
		 * idioma de su contexto local, si no estuviese disponible
		 * automáticamente se seleccionaría el idioma por defecto.
		 */
		if (loc == null) {
			ui = inner;
		}else{
			ui = new ParchisI18NUI(inner, loc);
		}
	}
	
	/**
	 * Getter de ui. Devuelve la interfaz de comunicación
	 * en la que se desarrolla el programa. Y a través de
	 * ella se mostraran los datos a través de consola o
	 * de manera gráfica.
	 * @return fichasJuego ArrayList de Ficha.
	 */
	public static ParchisUI getUI(){
		return ui;
	}
	
	/** 
	 * Establece el número de jugadores del juego, inicializa tablero,lanza la primera 
	 * tirada y elige el primer jugador. Cuando este método finaliza, ya está 
	 * establecido el entorno para poder jugar con normalidad.
	 * @param jugs número de jugadores
	 * 
	 */
	public int numJugadores(){
		int jugs=0;
		
		// muestra el mensaje de cabecera dando la bienvenida
		getUI().mostrarMensaje(ParchisI18Keys.BIENVENIDA);
		
		
		//obtiene el número de jugadores introducidos por consola
		jugs = getUI().solicitarEntero(ParchisI18Keys.PREGUNTARNUMERO);
		
		/* Si el número de jugadores no es 2,3 o 4 informa al 
		 * usuario y le pide de nuevo el número de jugadores.*/
		 while ((jugs < 2) || (jugs > 4)){
			//vuelvo a pedir el número de jugadores, pues es incorrecto
			getUI().mostrarMensaje(ParchisI18Keys.NUMEROINCORRECTO);
			jugs = getUI().solicitarEntero(ParchisI18Keys.PREGUNTARNUMERO);
		}
		
		return jugs;		
	}
	
	
	/**
	 * Se encarga de llamar a los distintos procedimientos para 
	 * gestionar el juego general.
	 */
	public void inicioJuego(int jugs){
	
		/* Selecciona un color para cada jugador y los almacena
		 * en la variable coloresJuego.
		 */
		this.coloresJuego.addAll(EnumSet.range(Colores.AMARILLO, Colores.values()[jugs - 1]));
	    
		 /*  
		 * Crea las fichas a partir de los colores o jugadores 
		 * en juego creando un identificador único para cada
		 * una de ellas. (1-AMARILLO,2-AMARILLO,3-AMARILLO,
		 * 4-AMARILLO,5-AZUL,6-AZUL,7-AZUL,8-AZUL,9-ROJO,10-ROJO,
		 * 11-ROJO,12-ROJO,13-VERDE,14-VERDE,15-VERDE,16-VERDE).
		 * y las almacena en el atributo fichasJuego.
		 */
		for (int i = 0; i < (coloresJuego.size()); i++) {
			for (int j = (4 * i); j < ((4 * i) + 4); j++) {
				if (j == (4 * i)){ // La 1ª ficha de cada color en la casilla de salida
					this.fichasJuego.add(new Ficha(Colores.values()[i],j + 1,
							Colores.values()[i].getCasillaSalida(),
							Colores.values()[i].getCasillaSalida(),
							Colores.values()[i].getCasillaCasa(),
							Colores.values()[i].getCasillaFinal()));
				}
				else{ // ...el resto en casita.
					this.fichasJuego.add(new Ficha(Colores.values()[i],j + 1,
							Colores.values()[i].getCasillaCasa(),
							Colores.values()[i].getCasillaSalida(),
							Colores.values()[i].getCasillaCasa(),
							Colores.values()[i].getCasillaFinal()));
				}
			}
		}
			
		 /*   
		 * Crea el tablero.
		 */
		this.tablero = new Tablero(fichasJuego);
		
		 /*  
		 * Crea los jugadores y les pasa sus fichas, el nombre
		 * del jugador puede ser el del color de sus fichas.
		 * También les pasa una referencia al tablero de juego
		 * actual.
		 */
		for (Colores c : this.coloresJuego){
			this.jugadores.add(new Jugador(this.tablero,c.toString(),c,fichasJuego));
		}
		 
		if (modo == Modo.NORMAL) 
			// En modo normal se elige quién empieza a través de una tirada inicial
			elegirPrimero();
		else
		   // En Modo PRUEBA empieza siempre el jugador AMARILLO
		   jugadorTurno = jugadores.get(0);
		
		getUI().mostrarMensajeString(ParchisI18Keys.EMPIEZATURNO, jugadorTurno.getNombre());
		
		jugar(); // Comienza el juego
	}
	
	/**
	 * Se encarga de llamar a los distintos procedimientos para 
	 * gestionar el juego general.
	 *
	 */
	public void jugar() {
		
	   // Mientras no finalice el juego
		while (!(tablero.juegoFinalizado) && 
				(jugadas != Parchis.jugadaLimitePrueba)) { // para modo PRUEBA	
			jugadorTurno.tirarDado(); // Se tira el dado
			
			if (Parchis.modo == Modo.PRUEBA) jugadas++; // Solo en modo PRUEBA
							
			do {
				getUI().mostrarTablero(tablero); // Se muestra la posición actual de todas las fichas.
				jugadorTurno.mover(); // Se mueve
		
			} while (jugadorTurno.getMovimientoAdicional()); // Si se ha comido o ha llegado a la casilla final alguna ficha se mueve otra vez
			
		   // Si no ha finalizado el juego y el jugador actual no tiene que tirar otra vez...
			if (!(tablero.juegoFinalizado) && !(jugadorTurno.getTiroOtraVez())) {
				elegirSiguiente(); // ... Se pasa el turno al siguiente jugador
			}	
		}
		
		/* Cuando el juego finaliza muestra un mensaje en consola
		 * indicando que el juego ha finalizado y el nombre del 
		 * jugador ganador.*/
		getUI().mostrarMensajeString(ParchisI18Keys.JUGADORGANADOR, 
				jugadores.get(jugadores.indexOf(jugadorTurno)).getNombre());
	}
	
	/**
	 * Elige al jugador que comienza la partida en función de 
	 * del valor de la tirada inicial.
	 */
	public void elegirPrimero(){

		boolean valorRepetido = false; // indica si el valor inicial se repite para más de un jugador
		int tirada; // variable para asignar la tidada de cada jugador
		int maximaPuntuacion; // almacena la tirada más alta de todos los jugadores
		int[] jugadoresPorPuntos = new int[6]; // array de 6 posiciones que almacena 
		   // la cantidad de jugadores que han alcanzado cada puntuación del dado
		
		do {
			// El bucle se repite mientras al menos 2 jugadores alcancen
			// la máxima puntuación de todas las tiradas del dado.
			valorRepetido = false;
			jugadorTurno = jugadores.get(0); // Por defecto, el AMARILLO
			maximaPuntuacion = 0;
			
			// Inicializa a 0 cada posición de 'jugadoresPorPuntos'
			Arrays.fill(jugadoresPorPuntos, 0);
				
			// Llama al método tirarDado() de cada jugador y muestra en consola el
			// resultado. En caso de que la puntuación que alcance un jugador
			// sea la máxima hasta el momento, se le da el turno y se almacena el
			// valor de su tirada en la variable 'maximaPuntuacion'. Por cada jugador,
			// se suma 1 a la posición del array 'jugadoresPorPuntos' correspondiente
			// a la puntuación que ha obtenido.
			for (Jugador jug: jugadores) {
				jug.tirarDado(true);
				tirada = jug.getTirada();
				if (tirada > maximaPuntuacion) {
					jugadorTurno = jug;
					maximaPuntuacion = tirada;
				}
				jugadoresPorPuntos[tirada - 1]++;
			}
			
			// Si más de un jugador ha alcanzado la máxima puntuación obtenida
			// en las tiradas del dado
			if (jugadoresPorPuntos[maximaPuntuacion - 1] > 1) {
				getUI().mostrarMensaje(ParchisI18Keys.VALORMASALTOREPETIDO);
				valorRepetido = true;
			}	
		}  while (valorRepetido);
	}
	
	/**
	 * Elige el jugador siguiente y se lo asigna a la variable 
	 * jugadorTurno, que será el siguiente jugador en turno.
	 * Si es el último del ArrayList, el turno pasará al primero.
	 * 
	 */
	public void elegirSiguiente(){
		
		getUI().mostrarMensaje(ParchisI18Keys.NOMASMOVIMIENTOS);
		if(jugadores.indexOf(jugadorTurno)==(jugadores.size()-1)){ // Si está al final, vuelve al primer elemento de la lista
			jugadorTurno = jugadores.get(0);
		}
		else{ //Si no está al final de la lista, asigna el siguiente jugador a jugadorTurno
			jugadorTurno = jugadores.get(jugadores.indexOf(jugadorTurno) + 1);
		}
	}
	
	/**
	 * Getter de jugadores. Devuelve el ArrayList de jugadores.
	 * @return jugadores ArrayList de Jugador. 
	 */
	public ArrayList<Jugador> getJugadores(){
		return jugadores;
	}

	/**
	 * Getter de fichasJuego. Devuelve el ArrayList de fichasJuego.
	 * @return fichasJuego ArrayList de Ficha.
	 */
	public ArrayList<Ficha> getFichasJuego() {
		return fichasJuego;
	}

	/**
	 * Getter de modo. Indica si el programa se encuenta en 
	 * modo NORMAL o PRUEBA
	 * @return Modo.
	 */
	public static Modo getModo() {
		return modo;
	}

	/**
	 * Establece el modo en el que se ejecutará el programa: NORMAL o PRUEBA
	 * @param modo
	 */
	public static void setModo(Modo modo) {
		Parchis.modo = modo;
	}
	
}