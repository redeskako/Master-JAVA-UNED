/**
* 2014, Antonio Jes�s Arquillos �lvarez
* M�ster en Dise�o y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educaci�n a Distancia
*/

package modelo;

// TODO: Auto-generated Javadoc
/**
 * Clase CasillaEspera.
 */
public class CasillaEspera extends Casilla {

	/** Rondas de penalizaci�n. 
	 * @uml.property name="rondasEspera" */
	private int rondasEspera;

	/**
	 * Instancia una nueva casilla de espera.
	 *
	 * @param tablero Tablero de juego
	 * @param indice �ndice de la casilla en el tablero
	 * @param rondasEspera rondas penalizaci�n
	 */
	public CasillaEspera( Tablero tablero, int indice, int rondasEspera ) {
		super( tablero, indice, indice );
		this.rondasEspera = rondasEspera;
	}		
	
	/**
	 * M�todo getter de la propiedad <tt>rondasEspera</tt>.
	 *
	 * @return Devuelve las rondas de penalizaci�n.
	 * @uml.property name="rondasEspera"
	 */
	public int getRondasEspera() {
		return rondasEspera;
	}
	
	/* (non-Javadoc)
	 * @see modelo.Casilla#entrar(jugador)
	 */
	public void entrar( Jugador jugador ) {
		super.entrar( jugador );		
		// turnos de penalizaci�n:
		jugador.setRondasSinJugar( rondasEspera );
		OcaEnRed.getInterfaz().mostrarEvento( this.getRondasEspera() + " rondas sin tirar" );
		// vuelve a tirar:
		jugador.setRepiteTurno( false );		
	}
			
}
