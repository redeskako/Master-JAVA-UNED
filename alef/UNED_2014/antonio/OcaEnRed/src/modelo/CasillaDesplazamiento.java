/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

// TODO: Auto-generated Javadoc
/**
 * Clase CasillaDesplazamiento.
 */
public class CasillaDesplazamiento extends Casilla {

	/**
	 * Instancia una nueva CasillaDesplazamiento.
	 *
	 * @param tablero Tablero de juego
	 * @param indice índice de la casilla en el tablero
	 * @param indiceDestino índice de la casilla destino
	 */
	public CasillaDesplazamiento( Tablero tablero, int indice, int indiceDestino ) {
		super( tablero, indice, indiceDestino ); 
	}
	
	/* (non-Javadoc)
	 * @see modelo.Casilla#entrar(int)
	 */
	public void entrar( Jugador jugador ) {
		super.entrar( jugador );
		// turnos de penalización ( ninguno ):
		jugador.setRondasSinJugar( 0 );
		// vuelve a tirar ( cierto ):
		jugador.setRepiteTurno( true );
	}

}