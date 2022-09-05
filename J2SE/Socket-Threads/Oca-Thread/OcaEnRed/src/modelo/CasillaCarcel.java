/**
* 2014, Antonio Jes�s Arquillos �lvarez
* M�ster en Dise�o y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educaci�n a Distancia
*/

package modelo;

// TODO: Auto-generated Javadoc
/**
 * Clase CasillaCarcel.
 */
public class CasillaCarcel extends CasillaEspera {

	private static final int RONDAS_ESPERA = 3; 

	/**
	 * Instancia una nueva casilla c�rcel.
	 *
	 * @param tablero Tablero de juego
	 * @param indice �ndice de la casilla en el tablero
	 */
	public CasillaCarcel( Tablero tablero, int indice ) {
		super( tablero, indice, RONDAS_ESPERA );
	}
	
	/* (non-Javadoc)
	 * @see modelo.CasillaEspera#entrar( Jugador jugador )
	 */
	@Override
	public void entrar( Jugador jugador ) {
		OcaEnRed.getInterfaz().mostrarEvento( "C�rcel: a la sombra durante " + RONDAS_ESPERA + " rondas." );
		super.entrar( jugador );
	}
		
}