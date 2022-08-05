/**
* 2014, Antonio Jes�s Arquillos �lvarez
* M�ster en Dise�o y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educaci�n a Distancia
*/

package modelo;


// TODO: Auto-generated Javadoc
/**
 * Clase CasillaPuente.
 */
public class CasillaPuente extends CasillaDesplazamiento {

	/**
	 * Instancia una nueva casilla puente.
	 *
	 * @param tablero Tablero de juego
	 * @param indice �ndice de la casilla en el tablero
	 * @param indiceDestino casilla a la que se avanza o retrocede (de tipo puente) 
	 */
	public CasillaPuente( Tablero tablero, int indice, int indiceDestino ) {
		super( tablero, indice, indiceDestino );
	}

	/* (non-Javadoc)
	 * @see modelo.CasillaDesplazamiento#entrar( Jugador jugador )
	 */
	@Override
	public void entrar( Jugador jugador ) {				
		super.entrar( jugador );
		OcaEnRed.getInterfaz().mostrarEvento( "De puente a puente tiro porque me lleva la corriente" );
	}
		
}