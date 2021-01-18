/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package oca;

// TODO: Auto-generated Javadoc
/**
 * Clase CasillaTrampa.
 */
public class CasillaTrampa extends CasillaEspera {

	/**
	 * Instancia una nueva CasillaTrampa.
	 *
	 * @param juego referencia a juego
	 * @param indice índice de esta casilla
	 */
	public CasillaTrampa( Tablero tablero, int indice, int rondas ) {
		super( tablero, indice, rondas );
	}
	
	/* (non-Javadoc)
	 * @see oca.CasillaCastigo#setJugador(oca.Jugador)
	 */
	@Override
	public void entrar(Jugador jugador) {
		super.entrar( jugador );
		System.out.println( "caes en el vacío. Debes esperar a otro jugador para salir" );
	}
}