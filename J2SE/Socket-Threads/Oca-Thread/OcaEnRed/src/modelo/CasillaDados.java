/**
* 2014, Antonio Jes�s Arquillos �lvarez
* M�ster en Dise�o y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educaci�n a Distancia
*/

package modelo;

// TODO: Auto-generated Javadoc
/**
 * Clase CasillaDados.
 */
public class CasillaDados extends CasillaDesplazamiento {

	/**
	 * Instancia una nueva casilla dados.
	 *
	 * @param tablero Tablero de juego
	 * @param indice �ndice de la casilla en el tablero
	 * @param destino casilla donde se avanza o retrocede (de tipo dados)
	 */
	public CasillaDados( Tablero tablero, int indice, int indiceDestino ) {
		super( tablero, indice, indiceDestino );
	}
	
	/* (non-Javadoc)
	 * @see modelo.CasillaDesplazamiento#entrar( Jugador jugador )
	 */
	@Override
	public void entrar( Jugador jugador ) {				
		super.entrar( jugador );
		OcaEnRed.getInterfaz().mostrarEvento( "De dado a dado y tiro porque me ha tocado." );
	}
		
}