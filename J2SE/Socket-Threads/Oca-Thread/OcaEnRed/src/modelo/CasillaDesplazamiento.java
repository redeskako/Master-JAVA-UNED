/**
* 2014, Antonio Jes�s Arquillos �lvarez
* M�ster en Dise�o y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educaci�n a Distancia
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
	 * @param indice �ndice de la casilla en el tablero
	 * @param indiceDestino �ndice de la casilla destino
	 */
	public CasillaDesplazamiento( Tablero tablero, int indice, int indiceDestino ) {
		super( tablero, indice, indiceDestino ); 
	}
	
	/* (non-Javadoc)
	 * @see modelo.Casilla#entrar(int)
	 */
	public void entrar( Jugador jugador ) {
		super.entrar( jugador );
		// turnos de penalizaci�n ( ninguno ):
		jugador.setRondasSinJugar( 0 );
		// vuelve a tirar ( cierto ):
		jugador.setRepiteTurno( true );
	}

}