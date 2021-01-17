/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

// TODO: Auto-generated Javadoc
/**
 * Clase CasillaCarcel.
 */
public class CasillaCarcel extends CasillaEspera {

	private static final int RONDAS_ESPERA = 3; 

	/**
	 * Instancia una nueva casilla cárcel.
	 *
	 * @param tablero Tablero de juego
	 * @param indice índice de la casilla en el tablero
	 */
	public CasillaCarcel( Tablero tablero, int indice ) {
		super( tablero, indice, RONDAS_ESPERA );
	}
	
	/* (non-Javadoc)
	 * @see modelo.CasillaEspera#entrar( Jugador jugador )
	 */
	@Override
	public void entrar( Jugador jugador ) {
		OcaEnRed.getInterfaz().mostrarEvento( "Cárcel: a la sombra durante " + RONDAS_ESPERA + " rondas." );
		super.entrar( jugador );
	}
		
}