/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

// TODO: Auto-generated Javadoc
/**
 * Clase base de la jerarquía de herencia de casillas.
 * Hereda de la clase abstracta Casilla
 */
public class CasillaNormal extends Casilla {

	/**
	 * Instancia una nueva casilla normal.
	 *
	 * @param tablero Tablero de juego
	 * @param indice índice de la casilla en el tablero
	 */
	public CasillaNormal( Tablero tablero, int indice ) {
		super( tablero, indice, indice );		
	}
			
	/**
	 * sitúa un jugador en la casilla.
	 *
	 * @param jugador jugador que llega a la casilla
	 * @uml.property name="jugador"
	 */
	public void entrar( Jugador jugador ) {		
		super.entrar( jugador );
		// turnos de penalización ( ninguno )
		jugador.setRondasSinJugar( 0 );
		// vuelve a tirar ( falso )
		jugador.setRepiteTurno( false );
	}
			
} // Casilla
