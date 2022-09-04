/**
* 2014, Antonio Jes�s Arquillos �lvarez
* M�ster en Dise�o y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educaci�n a Distancia
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
	 * @param indice �ndice de la casilla en el tablero
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
		OcaEnRed.getInterfaz().mostrarEvento( "Has ca�do en el pozo." );
	}
		
}