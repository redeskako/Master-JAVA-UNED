/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

// TODO: Auto-generated Javadoc
/**
 * Clase CasillaOca.
 */
public class CasillaLaberinto extends CasillaDesplazamiento {
	
	/**
	 * Instancia una nueva casilla laberinto.
	 *
	 * @param tablero Tablero de juego
	 * @param indice índice de la casilla en el tablero
	 * @param destino casilla de destino
	 */
	public CasillaLaberinto( Tablero tablero, int indice, int indiceDestino ) {
		super( tablero, indice, indiceDestino );
	}
	
	/* (non-Javadoc)
	 * @see modelo.CasillaDesplazamiento#entrar( Jugador jugador )
	 */
	@Override
	public void entrar( Jugador jugador ) {				
		super.entrar( jugador );
		OcaEnRed.getInterfaz().mostrarEvento( "¡Del laberinto al 30!" );
	}
	
}