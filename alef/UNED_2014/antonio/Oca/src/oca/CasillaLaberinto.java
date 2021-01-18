/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package oca;


// TODO: Auto-generated Javadoc
/**
 * Clase CasillaOca.
 */
public class CasillaLaberinto extends CasillaDesplazamiento {
	
	/* (non-Javadoc)
	 * @see oca.CasillaDesplazamiento#setJugador( Jugador jugador )
	 */
	@Override
	public void entrar( Jugador jugador ) {				
		super.entrar( jugador );
		System.out.println( "Vuelves a la casilla 30." );
	}
		
	/**
	 * Instancia una nueva casilla laberinto.
	 *
	 * @param tablero tablero del juego
	 * @param indice índice asignado a esta casilla
	 * @param destino casilla de destino
	 */
	public CasillaLaberinto( Tablero tablero, int indice, int indiceDestino ) {
		super( tablero, indice, indiceDestino );
	}
}