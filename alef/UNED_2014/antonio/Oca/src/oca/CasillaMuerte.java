/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Apicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package oca;


// TODO: Auto-generated Javadoc
/**
 * Clase CasillaOca.
 */
public class CasillaMuerte extends CasillaDesplazamiento {
	
	/* (non-Javadoc)
	 * @see oca.CasillaDesplazamiento#setJugador( Jugador jugador )
	 */
	@Override
	public void entrar( Jugador jugador ) {				
		super.entrar( jugador );
		System.out.println( "Vuelves a la casilla de inicio." );
		// vuelve a tirar ( falso )
		jugador.setRepiteTurno( false );		
	}
		
	/**
	 * Instancia una nueva casilla muerte.
	 *
	 * @param tablero tablero del juego
	 * @param indice índice asignado a esta casilla
	 * @param destino casilla de destino
	 */
	public CasillaMuerte( Tablero tablero, int indice ) {
		super( tablero, indice, 1 );
	}

}