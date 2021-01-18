/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package oca;

// TODO: Auto-generated Javadoc
/**
 * Clase CasillaEspera.
 */
public class CasillaEspera extends Casilla {

	/** Rondas de penalización. 
	 * @uml.property name="rondasEspera" */
	private int rondasEspera;
		
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
	 * @see oca.Casilla#setJugador(int)
	 */
	public void entrar( Jugador jugador ) {
		super.entrar( jugador );		
		// turnos de penalización
		jugador.setRondasSinJugar( rondasEspera );
		System.out.print( this.getRondasEspera() + " rondas sin tirar" );
		// vuelve a tirar
		jugador.setRepiteTurno( false );		
	}
			
	/**
	 * Instancia una nueva casilla de espera.
	 *
	 * @param juego el juego
	 * @param indice índice casilla
	 * @param turnosEspera turnos penalización
	 */
	public CasillaEspera( Tablero tablero, int indice, int rondasEspera ) {
		super( tablero, indice, indice );
		this.rondasEspera = rondasEspera;
	}		
}
