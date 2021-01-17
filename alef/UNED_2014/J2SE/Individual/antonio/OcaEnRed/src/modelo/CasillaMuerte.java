/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
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
	 * @param indice índice de la casilla en el tablero
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