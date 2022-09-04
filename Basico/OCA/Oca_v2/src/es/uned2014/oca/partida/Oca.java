package es.uned2014.oca.partida;

/**
 * Esta clase contiene los métodos para poder jugar al Juego de la Oca, desde la 
 * configuración inicial hasta la finalización del juego.
 * 
 * @author	Alef UNED 2014
 * @version	Oca 2.0
 * @fecha 	Abril 2014
 *
 */

import es.uned2014.oca.jugador.*; // importa todo el package es.uned2014.oca.jugador
import es.uned2014.oca.tablero.*; // importa todo el package es.uned2014.oca.tablero
import es.uned2014.oca.excepciones.*; // importa clase Error

import java.util.*; // importa clase java.util, usaremos Scanner, TreeSet, ...

public class Oca {
	private int numeroJugadores = 0; // numero de jugadores que participan en el juego
	private int tiradaJugador [] = new int[6]; 
	private ArrayList<Jugador> jugadoresArray;	
	private ArrayList<Casilla> tableroOca;
	
	private static boolean juegoTerminado = false;
	
	private HashMap<Jugador, Casilla> jugadorCasilla = new HashMap<Jugador, Casilla>();
	
	/**
	 * Metodo construtor Oca para configurar la partida
	 * @param null
	 */
	public Oca () {
		this.numeroJugadores = 0;		
		this.jugadoresArray = new ArrayList<Jugador>(numeroJugadores);
		
	} // fin contructor Oca
	
	
	/**
	 * Obitiene numeroJugadores
	 * @return the numeroJugadores
	 */
	public int getNumeroJugadores() {
		return numeroJugadores;
	} // fin getNumeroJugadores


	/**
	 * Estabelecer juegoTerminado
	 * @param terminado
	 */
	public static void setJuegoTerminado(boolean terminado){
		juegoTerminado = terminado;
	} // setJuegoTerminado
	
	/**
	 * Obitiene juegoTerminado
	 * @return
	 */
	public static boolean getJuegoTerminado(){
		return juegoTerminado;
	}
	
	/**
	 * Establece numeroJugadores
	 * @param numeroJugadores the numeroJugadores to set
	 */
	public void setNumeroJugadores(int numeroJugadores) {
		if (numeroJugadores<2 || numeroJugadores>6){
			throw new ClaseError("El valor del numeroJugadores tiene que estar comprendido entre 2 y 6."); 
		}else{
			this.numeroJugadores = numeroJugadores;
		} // fin if else
			
	} // fin set setNumeroJugadores

	/**
	 * Obiente jugadoresArray
	 * @return the jugadoresArray
	 */
	public ArrayList<Jugador> getJugadoresArray() {
		return jugadoresArray;
	} // fin getJugadoresArray


	/**
	 * Establece jugadoresArray
	 * @param jugadoresArray the jugadoresArray to set
	 */
	public void setJugadoresArray(ArrayList<Jugador> jugadoresArray) {
		this.jugadoresArray = jugadoresArray;
	} // fin setJugadoresArray
	

	/**
	 * Obiente jugadorCasilla
	 * @return the jugadorCasilla
	 */
	public HashMap<Jugador, Casilla> getJugadorCasilla() {
		return jugadorCasilla;
	} // fin getJugadorCasilla


	/**
	 * Establece jugadorCasilla
	 * @param jugadorCasilla the jugadorCasilla to set
	 */
	public void setJugadorCasilla(HashMap<Jugador, Casilla> jugadorCasilla) {
		this.jugadorCasilla = jugadorCasilla;
	} // setJugadorCasilla


	/**
	 * Muestra el mensaje de bienvenida al usuario
	 * 
	 * @param null
	 */
	public void bienvenidoOca(){
		boolean respuesta = false;
		
		// Muestra el mensaje de bienvenida
		System.out.println( "*** BIENVENIDO AL JUEGO DE LA OCA ***" + "\n"); // mensaje de bienvenida
		
		// Pregunta al usuario si quiere o no jugar
		while( !respuesta) {
			System.out.println( "¿Quieres jugar? S/N "); // indicador
	        Scanner entrada = new Scanner( System.in); // crea objeto Scanner entrada
	        String st = entrada.next();
	        
	        // Si la respuesta es en minusculas la iguala a mayuscula
	        if(st.toUpperCase().equals("S")){
		        break;
	        } else if( st.toUpperCase().equals("N")) {
	        	mensajeDespedida();
	        	System.exit(0);
	        } else {
		        System.out.println( "Debes teclear S/N ");
		        //respuesta = true;
	        } // fin if else
		} // fin while
        
	} // fin bienvenidoOca
	
	/**
	 * Configura la partida en función del número de jugadores elegidos por el usuario
	 * 
	 * @param null
	 */
	public void configurarOca () {
		// Crea objeto Scanner
		Scanner entrada = new Scanner( System.in);
		
		// Obtiene el numero de jugadores
		System.out.print( "¿Cuántos jugadores sois? "); // indicador
		
		String st = entrada.next(); // lee el valor introducido
		int n = tryParse(st); // interpreta el valor introducido como un entero
		
		// Mientras el valor introducido no está entre 2 y 6, sigue pidiendo un número válido de jugadores.
		while (n<2 || n>6){
			System.out.println("El número de jugadores debe estar comprendido entre 2 y 6"); // muestra el numero de jugadores correcto
			System.out.print("¿Cuántos jugadores sois? "); // indicador
			st = entrada.next(); // lee el valor introducido
			n = tryParse(st); // interpreta el valor introducido como un entero
		} // fin while
		
		// Cuando el valor introducido está entre 2 y 6, se almacena en numeroJugadores.
		numeroJugadores = n; // establece el numero de jugadores
			
		//entrada.close(); // cierra objeto Scanner entrada
		
		// Crea el conjunto de jugadores para jugar
		Set<Jugador>tsJugador = new TreeSet<Jugador>(); // crea conjunto de jugadores
			
		Jugador j1 = new Jugador(Color.ROJO, "   ", 0); // crea jugador 1 de color rojo, sin alias ni castigos
		Jugador j2 = new Jugador(Color.AZUL, "   ", 0); // crea jugador 2 de color azul y sin castigos
		Jugador j3 = new Jugador(Color.AMARILLO, "   ", 0); // crea jugador 3 de color amarillo y sin castigos
		Jugador j4 = new Jugador(Color.VERDE, "   ", 0); // crea jugador 4 de color verde y sin castigos
		Jugador j5 = new Jugador(Color.NARANJA, "   ", 0); // crea jugador 5 de color naranja y sin castigos
		Jugador j6 = new Jugador(Color.VIOLETA, "   ", 0); // crea jugador 6 de color violeta y sin castigos
		
		Jugador[] jugador = {j1, j2, j3, j4, j5, j6}; // conjunto Jugador
		
		// Añade los jugadores elegidos por el usuario, con un minimo de 2 y un maximo de 6
		for ( int i = 0; i < numeroJugadores; i++) {
			
			boolean alias = false;
			
			while( !alias) {
				System.out.println( "\n" + "Introduce el nombre para el " + jugador[i].toString()); // inidcador
				Scanner sc = new Scanner( System.in); 
				String nombre = sc.nextLine();
				
				if( nombre.length() >= 3 && nombre.length() <= 15) {
					jugador[i].setAliasJugador(nombre);
					break;
				}
				else 
					System.out.println( "El nombre debe tener entre 3 y 15 caracteres");
				
			} // fin while
			
			tsJugador.add(jugador[i]); // añade jugador al conjunto
			
			// System.out.println( jugador[i].toString()); // muestra si se han creado bien, se borrara
		} // fin for
		
		// Establece orden aleatorio
		definirOrden(tsJugador);

		// Inicializa el tablero
		Tablero tab = new Tablero();
		tableroOca = tab.inicializarTablero();
			
	} // fin metodo configurarOca

	
	/**
	 * Asigna el orden de los jugadores a partir del TreeSet de jugadores
	 * @param tsJugador
	 */
	public void definirOrden ( Set<Jugador> tsJugador) {
		
		ArrayList<Jugador> jArray = new ArrayList<Jugador>(numeroJugadores); // crea jArray en función del numero de jugadores
		
		Iterator<Jugador> iter = tsJugador.iterator(); // itera en tsJugador
		
		// Añade a jArry los jugadores mientras estos existan 
		while (iter.hasNext()) {
			Jugador jug = iter.next();
			jArray.add(jug);
		} // fin while

		Collections.shuffle(jArray); // ordena aleatoriamente jArray
		
		setJugadoresArray(jArray); // establece jugadoresArray
		
		System.out.println( "\n" + "El orden de los turnos será el siguiente:");
		System.out.println( jugadoresArray.toString() + "\n"); // muestra si se han creado bien, se borrara	
		
	} // fin asignar turno
	
	// Empieza la partida
	public void jugarOca () {
		// Crea objeto Scanner
		Scanner entrada = new Scanner( System.in);
		
		Casilla c = new Casilla(1);
		
		for(int i=0; i<numeroJugadores; i++){
			jugadorCasilla.put(jugadoresArray.get(i), c);

		}
		
		Tablero t = new Tablero();
		t.inicializarTablero();
		
		Dado d = new Dado();
		int valor;
		
		// Mientras no se finaliza el juego, se repite este bucle.
		while(!juegoTerminado){
			
			// Se pasa el turno entre los jugadores, en el orden establecido en el ArrayList
			for(int i=0; i<numeroJugadores; i++){
				
				// Se selecciona jugador activo
				Jugador jug = jugadoresArray.get(i);	
												
				//Comprobar si el jugador no tiene castigo de turno
				if(jug.getCastigo() == 0){
					// Muestra mensaje al usuario para que tire el dado
					System.out.print( "\n" + jugadoresArray.get(i) + " tira el dado pulsando Enter "); // indicador
					
					// Espera hasta el jugador pulse intro
					Scanner intro = new Scanner( System.in);
					intro.nextLine();
					
					// El jugador tira el dado
					valor = d.tirarDado();
					System.out.println(jugadoresArray.get(i)+" has sacado un: "+valor);
					
					// Se comprueba en qué Casilla está:
					Casilla jugCasilla = jugadorCasilla.get(jug);
					
				    // Se analiza la tirada en la clase Tablero. 
					// El resultado es una Casilla modificada.
					Casilla casillaResultado = t.analizarTirada(jug, jugCasilla, valor);	

					// Se sustituye el valor (value) que corresponde al jugador activo (key): casillaResultado
					jugadorCasilla.put(jug, casillaResultado);
					
					// System.out.println("¿Repite turno?" + jug.getTiraOtraVez());
					
					// Mientras tenga que volver a tirar, repite turno:
					if (jug.getTiraOtraVez() == true){
						i--;
					} // fin if
					
				} else {
					// Comprobar cuál es el castigo anterior:
					int castigo = jug.getCastigo();
					jug.setCastigo(castigo-1);
					
					// Informa al jugador de sus castigos
					if (jug.getCastigo() == 0) {
						System.out.println( "\n" + jugadoresArray.get(i) + " no tiras el dado, en el siguiente turno tiras");
					} else if (jug.getCastigo() == 1) {
						System.out.println( "\n" + jugadoresArray.get(i) + " no tiras el dado, te queda " + jug.getCastigo() + " turno de castigo");
					} else
						System.out.println( "\n" + jugadoresArray.get(i) + " no tiras el dado, te quedan " + jug.getCastigo() + " turnos de castigo");


				} // fin if else
				
				// Comprueba si se ha terminado, si es cierto, se para el bucle
				if( juegoTerminado == true)
					break;
				
			} // fin for
			
			//entrada.close(); // cierra objeto Scanner entrada

		} // fin while
		
	} // fin jugarOca

	/**
	 * Finaliza la partida, mostrando el mensaje de despedida y da la opción al usuario de voler a 
	 * jugar otra partida
	 * 
	 * @param null;
	 */
	public void finOca() {	
		boolean respuesta = false;
		
		// Mensaje fin del juego
		System.out.println( "\n" + "*** FIN DEL JUEGO ***" + "\n");
		  
		// Pregunta al usuario si quiere o no jugar
		while( !respuesta) {
			System.out.println( "¿Quieres volver a jugar? S/N "); // indicador
	        Scanner entrada = new Scanner( System.in); // crea objeto Scanner entrada
	        String st = entrada.next();
	        
	        // Si la respuesta es en minusculas la iguala a mayuscula
	        if(st.toUpperCase().equals("S")){
	        	juegoTerminado = false;
	        	configurarOca();
		        jugarOca();
		        finOca();
	        } else if( st.toUpperCase().equals("N")) {
	        	mensajeDespedida();
	        	System.exit(0);
	        } else {
		        System.out.println( "Debes teclear S/N ");
		        //respuesta = true;
	        } // fin if else

		} // fin while

	} // fin finOca
	
	public void mensajeDespedida () {
		// Muestra el mensaje despedida
		System.out.println( "\n" + "¡Hasta la próxima!");
		
	} // fin mensajeDespedida
	
	/**
	 * Transformar String en Integer en caso se puede, si no devuelve valor 0
	 * 
	 * @return Integer
	 */
	public static Integer tryParse(String text) {
	  try {
	    return new Integer(text);
	  } catch (NumberFormatException e) {
	    return 0;
	  }
	}
	

} // fin clase Oca
