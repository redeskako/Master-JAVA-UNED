/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

// TODO: Auto-generated Javadoc
/**
 * Clase CasillaEspera.
 */
public class CasillaEspera extends Casilla {

	/** Rondas de penalización. 
	 * @uml.property name="rondasEspera" */
	private int rondasEspera;

	/**
	 * Instancia una nueva casilla de espera.
	 *
	 * @param tablero Tablero de juego
	 * @param indice índice de la casilla en el tablero
	 * @param rondasEspera rondas penalización
	 */
	public CasillaEspera( Tablero tablero, int indice, int rondasEspera ) {
		super( tablero, indice, indice );
		this.rondasEspera = rondasEspera;
	}		
	
	/**
	 * Método getter de la propiedad <tt>rondasEspera</tt>.
	 *
	 * @return Devuelve las rondas de penalización.
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
		// turnos de penalización:
		jugador.setRondasSinJugar( rondasEspera );
		OcaEnRed.getInterfaz().mostrarEvento( this.getRondasEspera() + " rondas sin tirar" );
		// vuelve a tirar:
		jugador.setRepiteTurno( false );		
	}
			
}
