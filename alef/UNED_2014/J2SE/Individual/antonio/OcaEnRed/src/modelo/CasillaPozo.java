/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;


// TODO: Auto-generated Javadoc
/**
 * Clase CasillaPozo.
 */
public class CasillaPozo extends CasillaEspera {

	private static final int RONDAS_ESPERA = 2; 

	/**
	 * Instancia una nueva casilla pozo.
	 *
	 * @param tablero Tablero de juego
	 * @param indice índice de la casilla en el tablero
	 */
	public CasillaPozo( Tablero tablero, int indice ) {
		super( tablero, indice, RONDAS_ESPERA );
	}
	
	/* (non-Javadoc)
	 * @see oca.CasillaEspera#entrar( Jugador jugador )
	 */
	@Override
	public void entrar( Jugador jugador ) {				
		super.entrar( jugador );
		OcaEnRed.getInterfaz().mostrarEvento( "Has caído en el pozo." );
	}
		
}