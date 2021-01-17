/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package oca;

// TODO: Auto-generated Javadoc
/**
 * Clase CasillaInicio.
 */
public class CasillaFinal extends Casilla {

	/** Índice. */
	protected int indice;

	/**
	 * devuelve el índice de la casilla.
	 *
	 * @return el indice
	 */
	public int getIndice() {
		return indice;
	}

	/** Jugador. */
	protected Jugador jugador;

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
			
	/**
	 * Instancia una nueva casilla final.
	 *
	 * @param tablero el tablero
	 * @param indice el índice en el tablero
	 */
	public CasillaFinal( Tablero tablero, int indice ) {
		super( tablero, indice, indice );		
	}		
}	