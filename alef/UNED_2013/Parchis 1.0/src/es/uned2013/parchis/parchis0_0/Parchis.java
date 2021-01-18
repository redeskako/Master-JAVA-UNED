package es.uned2013.parchis.parchis0_0;

import java.util.*;
import es.uned2013.parchis.parchis0_1.Casilla;
import es.uned2013.parchis.parchis0_1.Ficha;
import es.uned2013.parchis.parchis0_1.Colores;

/**
 * Clase principal del programa. Configura el juego y 
 * establece el valor de las variables principales
 * mediante un diálogo con el usuario a través de la
 * consola.
 * @author alef
 *
 */
/*NO EN REQUISITOS ACTUALES. POSIBLES MEJORAS.
enum Idioma { INGLES, ESPANOL }
enum Modo { NORMAL, PRUEBA }
*/
public class Parchis {
	/*NO EN REQUISITOS ACTUALES. POSIBLES MEJORAS.
	Idioma idioma = Idioma.ESPANOL; //Por defecto en español.
	Modo modo = Modo.NORMAL; //Por defecto modo NORMAL.
	*/
	private int jugadoresJuego; //Nº de jugadores de la partida actual.
	private ArrayList<Ficha> fichasJuego = new ArrayList<Ficha>(); //Fichas de la partida actual.
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>(); //Jugadores de la partida actual.
	private Jugador jugadorTurno; //Jugador en turno. Va cambiando rotativamente a lo largo de la partida.
	private Tablero tablero; //Tablero de la partida actual. 
	private ArrayList<Colores> coloresJuego = new ArrayList<Colores>(); //Colores de la partida actual.
	
	public Parchis(){
		/**
		 * CONSTRUCTOR. Según la clase Driver sin argumentos.
		 * Muestra una imagen en consola (formada por caracteres),
		 * presentando el juego.
		 * Según la estructura de la clase Driver proporcionada para la
		 * práctica debería mostrar en consola un mensaje 
		 * solicitando el número de jugadores.
		 */
		
		System.out.println("OOOOOO  OOOOOOOO OOOOOOO  OOOOOOOO OO    OO OO  OOOOO    OOOO  OOOOO  OOO  OOOO ");
		System.out.println("OO   OO OO    OO OO    OO OO    OO OO    OO OO OO    O  O   OO O   O O OO O    O");
		System.out.println("OOOOOO  OOOOOOOO OOOOOOO  OO       OOOOOOOO OO   OOO       OO  O   O   OO    OO ");
		System.out.println("OO      OO    OO OO   OO  OO    OO OO    OO OO O    OO   OO    O   O   OO O    O");
		System.out.println("OO      OO    OO OO    OO OOOOOOOO OO    OO OO  OOOOO   OOOOOO OOOOO   OO  OOOO ");
		System.out.println("");
		System.out.println("");
		System.out.println("Bienvenido al juego del Parchís UNED 2013.");
		System.out.println("¿Cuántos jugadores participarán? (2,3,4).");
		
	}
	
	/* NO EN REQUISITOS ACTUALES. POSIBLES MEJORAS.
	public void eligeModo(){
		
	}
	
	public void eligeIdioma(){
		
	}
	*/
	
	public void numJugadores(int jugs){
		/** 
		 * Establece el número de jugadores del juego.
		 * @param jugs número de jugadores
		 * 
		 */
		
		/* Si el número de jugadores no es 2,3 o 4 informa al 
		 * usuario y le pide de nuevo el número de jugadores.*/
		while ((jugs < 2) || (jugs > 4)){
			
			Scanner entrada = new Scanner (System.in);
			
			System.out.println("El número de participantes no es correcto.");
			System.out.println("¿Cuántos jugadores participarán? (2,3,4).");
			
			try {
				jugs = entrada.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("No es un número válido");
			}
			finally{
				entrada.close(); 
			}
		}
		
		this.jugadoresJuego = jugs;
				
		 /* Selecciona un color para cada jugador y los almacena
		 * en la variable coloresJuego.
		 */
		this.coloresJuego.addAll(EnumSet.range(Colores.AMARILLO, Colores.values()[jugs - 1]));
	    
		 /* ficha(int id, Color color) ->
		 * 
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
		/* COMPRUEBO
		for (int i = 0; i < fichasJuego.size(); i++) {
			System.out.println("Color: " + fichasJuego.get(i).getColor());
			System.out.println("Identificador: " + fichasJuego.get(i).getIdentificador());
			System.out.println("Casilla actual: " + fichasJuego.get(i).getCasillaActual());
			System.out.println("Casilla de salida: " + fichasJuego.get(i).getCasillaSalida());
			System.out.println("Casilla casa: " + fichasJuego.get(i).getCasillaCasa());
			System.out.println("Casilla final: " + fichasJuego.get(i).getCasillaFinal());
		}*/
		
		 /*  
		  * 
		 * tablero(col[] Color) ->
		 * 
		 * Crea el tablero.
		 */
		this.tablero = new Tablero(fichasJuego);
		
		 /* 
		 * jugador(tab Tablero,col Color, nombre String, fic Fichas[]) ->
		 * 
		 * Crea los jugadores y les pasa sus fichas, el nombre
		 * del jugador puede ser el del color de sus fichas.
		 * También les pasa una referencia al tablero de juego
		 * actual.
		 */
		for (Colores c : this.coloresJuego){
			this.jugadores.add(new Jugador(this.tablero,c.toString(),c,fichasJuego));
		}
		 /* 
		 * Llama a elegirPrimero()
		 */
		elegirPrimero();
		 /* Llama a jugar()
		 */
		jugar();
	}
	
	public void jugar(){
		/**
		 * Se encarga de llamar a los distintos procedimientos para 
		 * gestionar el juego general.
		 *
		 */
		
		while(tablero.juegoFinalizado==false){
			
			jugadorTurno.tirarDado();
			System.out.println("El jugador " + jugadorTurno.getNombre() + " ha sacado un " + jugadorTurno.getTirada());
			
			do{
						
				jugadorTurno.mover();
				
				//jugadorTurno.hayMovimientoAdicional();
				
			}while(jugadorTurno.getMovimientoAdicional());
			
			if(!jugadorTurno.getTiroOtraVez() && tablero.juegoFinalizado==false){
			
				elegirSiguiente();
				
			}	
		}
		
		/*Cuando el juego finaliza muestra un mensaje en consola
		 * indicando que el juego ha finalizado y el nombre del 
		 * jugador ganador.*/
		System.out.println("El jugador " + jugadorTurno.getNombre() + " ha ganado.");
	}
	
	public void elegirPrimero(){
		/**
		 * Elige al primer jugador en función de la primera tirada.
		 * El que tenga el mayo valor será el primero en jugar.
		 *
		 */
		
		boolean valorRepetido = false;//indica si el valor inicial se repite para más de un jugador
		boolean esInicio = true; //indica que es la primera tirada
		
		//Llama al método tirarDado():int de caja jugador y muestra en consola el resultado de cada tirada.
		do{
			valorRepetido = false;
			
			for( int i = 0 ; i < jugadores.size() ; i++ ){
				jugadores.get(i).tirarDado(esInicio);
				//System.out.println("El jugador " + jugadores.get(i).getNombre() + " ha sacado un " + jugadores.get(i).getTirada());
			}
			
			/*Ordena los jugadores en función de sus tiradas según el método de ordenación
			definido en la clase Jugador*/
			Collections.sort(jugadores); 
			
			/*Una vez ordenados, si el primer jugador y el segundo han sacado el mismo valor, 
			repetir tirada*/
			//for( int i = 0 ; i < jugadores.size()-1 ; i++ ){
				if (jugadores.get(0).getTirada()==jugadores.get(1).getTirada()) {
					System.out.println("Valor más alto para más de un jugador, se repite la tirada inicial");
					valorRepetido=true;
				}
			//}
			
		}while(valorRepetido==true);
		
		//Asigna el turno al jugador con la puntuación más alta
		jugadorTurno = jugadores.get(0);
		System.out.println("Empieza el turno jugando el jugador "+ jugadorTurno.getNombre());
	}
	
	public void elegirSiguiente(){
		/**
		 * Elige el jugador siguiente y se lo asigna a la variable 
		 * jugadorTurno, que será el siguiente jugador en turno.
		 * Si es el último del ArrayList, el turno pasará al primero.
		 * 
		 */
		
		/*Iterator<Jugador> it = jugadores.iterator();

		if (it.hasNext()){ //Si no está al final de la lista, asigna el siguiente jugador a jugadorTurno
			jugadorTurno=it.next();
		}
		else{ // Si está al final, vuelve al primer elemento de la lista
			jugadorTurno = jugadores.get(0);
		}*/
		
		if(jugadores.indexOf(jugadorTurno)==(jugadores.size()-1)){ // Si está al final, vuelve al primer elemento de la lista
			System.out.println("Jugador  "+ jugadorTurno.getNombre() + " no puede realizar más movimientos. Pasa el turno a jugador " + jugadores.get(0).getNombre());
			jugadorTurno = jugadores.get(0);
		}
		else{ //Si no está al final de la lista, asigna el siguiente jugador a jugadorTurno
			System.out.println("Jugador  "+ jugadorTurno.getNombre() + " no puede realizar más movimientos. Pasa el turno a jugador " + jugadores.get(jugadores.indexOf(jugadorTurno) + 1).getNombre());
			jugadorTurno = jugadores.get(jugadores.indexOf(jugadorTurno) + 1);
		}
	}
	
	//para pruebas
	/*public static void main(String args[]){
		Parchis parchis = new Parchis();
		parchis.numJugadores(3);
	}*/
	
	public ArrayList<Jugador> getJugadores(){
		/**
		 * Getter de jugadores. Devuelve el ArrayList de jugadores.
		 * @return jugadores ArrayList de jugadores.
		 * 
		 */
		return jugadores;
	}
	
}