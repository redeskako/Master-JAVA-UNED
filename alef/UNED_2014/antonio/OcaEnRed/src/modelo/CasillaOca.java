/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

/**
 * Clase CasillaOca.
 */
public class CasillaOca extends CasillaDesplazamiento {

	/**
	 * Instancia una nueva casilla oca.
	 *
	 * @param tablero Tablero de juego
	 * @param indice índice de la casilla en el tablero
	 * @param indiceDestino casilla de destino
	 */
	public CasillaOca( Tablero tablero, int indice, int indiceDestino ) {
		super( tablero, indice, indiceDestino );
	}

	/* (non-Javadoc)
	 * @see oca.CasillaDesplazamiento#entrada( Jugador jugador )
	 */
	@Override
	public void entrar( Jugador jugador ) {				
		super.entrar( jugador );
		OcaEnRed.getInterfaz().mostrarEvento( "De oca a oca y tiro porque me toca." );
	}
		
}