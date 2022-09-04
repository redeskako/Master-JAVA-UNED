/**
* 2014, Antonio Jes�s Arquillos �lvarez
* M�ster en Dise�o y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educaci�n a Distancia
*/

package modelo;

// TODO: Auto-generated Javadoc
/**
 * Clase base de la jerarqu�a de herencia de casillas.
 * Hereda de la clase abstracta Casilla
 */
public class CasillaNormal extends Casilla {

	/**
	 * Instancia una nueva casilla normal.
	 *
	 * @param tablero Tablero de juego
	 * @param indice �ndice de la casilla en el tablero
	 */
	public CasillaNormal( Tablero tablero, int indice ) {
		super( tablero, indice, indice );		
	}
			
	/**
	 * sit�a un jugador en la casilla.
	 *
	 * @param jugador jugador que llega a la casilla
	 * @uml.property name="jugador"
	 */
	public void entrar( Jugador jugador ) {		
		super.entrar( jugador );
		// turnos de penalizaci�n ( ninguno )
		jugador.setRondasSinJugar( 0 );
		// vuelve a tirar ( falso )
		jugador.setRepiteTurno( false );
	}
			
} // Casilla
