/* Esta clase contiene todo lo referente a la configuracion previa para jugar
 * Como el mensaje de bienvenida y la eleccion del numero de jugadores
 */

package es.uned2014.oca;

import java.util.*;

public class ParametrosPartida {
	private int jugadores; // para el numero de jugadores a jugar
	
	// constructor Oca
	public ParametrosPartida() {
		this.jugadores = 0;
	} // fin constructor Oca

	// set&get jugadores
	public int getJugadores() {
		return jugadores;
	}

	public void setJugadores(int jugadores) {
		this.jugadores = jugadores;
	}
	
	// metodo para configurar la partida
	public void configuracionPartida () {
		// crea objeto Scanner
		Scanner entrada = new Scanner( System.in);
		
		// muestra el mensaje de bienvenida
		System.out.println( "*** BIENVENIDO AL JUEGO DE LA OCA ***" + "\n");
		
		// obtiene el numero de jugadores
		System.out.print( "¿Cuántos jugadores sois? "); // indicador
		int jugadores = entrada.nextInt(); // lee el numero de jugadores
		
		/**
		 * aqui habria que lanzar y capturar el posible error en la entrada del usuario
		 * pero no esta hecho
		 * @luis
		 */
		
		// asigna colores automaticamente en funcion del numero de jugadores
		/**
		 * No se si se podra, tampoco como poder construirlo correctamente, 
		 * pero seria mucho mas facil si con un for o while se pudiesen construir
		 * los objetos jugador, y el color fuese int y no un String, donde por ejemplo:
		 * 1 = "rojo", 2 = "amarillo", 3 = "naranja", 4 = "verde", 5 = "azul" y 6 = "violeta"
		 * Quedadno algo asi:
		 * 
		 * for ( int i = 1; i <= jugadores; i++)
		 * 		Jugador jugador(i) = new Jugador( i);
		 * 
		 * Creandose automaticamente todos los jugadores en funcion del numero de jugadores, siendo este
		 * el resultado:
		 * jugador1( 1) ---> jugador1( "rojo")
		 * jugador2( 2) ---> jugador2( "amarillo")
		 * ....
		 * @luis
		 */
		if ( jugadores == 2) {
			Jugador jugador1 = new Jugador( "Rojo");
			Jugador jugador2 = new Jugador( "Amarillo");
		}
		else if ( jugadores == 3) {
			Jugador jugador1 = new Jugador( "Rojo");
			Jugador jugador2 = new Jugador( "Amarillo");
			Jugador jugador3 = new Jugador( "Naranja");
		}
		else if ( jugadores == 4) {
			Jugador jugador1 = new Jugador( "Rojo");
			Jugador jugador2 = new Jugador( "Amarillo");
			Jugador jugador3 = new Jugador( "Naranja");
			Jugador jugador4 = new Jugador( "Verde");
		}
		else if ( jugadores == 5) {
			Jugador jugador1 = new Jugador( "Rojo");
			Jugador jugador2 = new Jugador( "Amarillo");
			Jugador jugador3 = new Jugador( "Naranja");
			Jugador jugador4 = new Jugador( "Verde");
			Jugador jugador5 = new Jugador( "Azul");
		}
		else if ( jugadores == 6) {
			Jugador jugador1 = new Jugador( "Rojo");
			Jugador jugador2 = new Jugador( "Amarillo");
			Jugador jugador3 = new Jugador( "Naranja");
			Jugador jugador4 = new Jugador( "Verde");
			Jugador jugador5 = new Jugador( "Azul");
			Jugador jugador6 = new Jugador( "Violeta");
		}
		
		
	} // fin configuracionPartida
	
	

} // fin Clase Oca
