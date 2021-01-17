/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package oca;


// TODO: Auto-generated Javadoc
/**
 * Clase CasillaPosada.
 */
public class CasillaPosada extends CasillaEspera {

	private static final int RONDAS_ESPERA = 2; 
	
	/* (non-Javadoc)
	 * @see oca.CasillaEspera#setJugador( Jugador jugador )
	 */
	@Override
	public void entrar( Jugador jugador ) {				
		super.entrar( jugador );
		System.out.println( "Bienvenido a la posada." );
	}
		
	/**
	 * Instancia una nueva casilla posada.
	 *
	 * @param tablero tablero del juego
	 * @param indice índice asignado a esta casilla
	 */
	public CasillaPosada( Tablero tablero, int indice ) {
		super( tablero, indice, RONDAS_ESPERA );
	}

}