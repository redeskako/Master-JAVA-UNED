/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package oca;


// TODO: Auto-generated Javadoc
/**
 * Clase CasillaPuente.
 */
public class CasillaPuente extends CasillaDesplazamiento {

	/* (non-Javadoc)
	 * @see oca.CasillaDesplazamiento#setJugador( Jugador jugador )
	 */
	@Override
	public void entrar( Jugador jugador ) {				
		super.entrar( jugador );
		System.out.println( "De puente a puente tiro porque me lleva la corriente" );
	}
		
	/**
	 * Instancia una nueva casilla puente.
	 *
	 * @param tablero tablero del juego
	 * @param indice índice asignado a esta casilla
	 * @param destino casilla a la que se avanza o retrocede (de tipo puente) 
	 */
	public CasillaPuente( Tablero tablero, int indice, int indiceDestino ) {
		super( tablero, indice, indiceDestino );
	}
}