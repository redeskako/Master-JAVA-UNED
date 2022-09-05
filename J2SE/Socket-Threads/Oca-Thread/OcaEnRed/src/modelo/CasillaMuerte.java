/**
* 2014, Antonio Jes�s Arquillos �lvarez
* M�ster en Dise�o y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educaci�n a Distancia
*/

package modelo;


// TODO: Auto-generated Javadoc
/**
 * Clase CasillaMuerte.
 */
public class CasillaMuerte extends CasillaDesplazamiento {

	/**
	 * Instancia una nueva casilla muerte.
	 *
	 * @param tablero Tablero de juego
	 * @param indice �ndice de la casilla en el tablero
	 */
	public CasillaMuerte( Tablero tablero, int indice ) {
		super( tablero, indice, 1 );
	}
		
	/* (non-Javadoc)
	 * @see oca.CasillaDesplazamiento#entrar( Jugador jugador )
	 */
	@Override
	public void entrar( Jugador jugador ) {				
		super.entrar( jugador );
		OcaEnRed.getInterfaz().mostrarEvento( "Muerte: vuelves a la casilla de inicio." );
		// vuelve a tirar ( falso )
		jugador.setRepiteTurno( false );		
	}
		
}