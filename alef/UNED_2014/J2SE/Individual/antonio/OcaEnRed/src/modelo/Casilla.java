/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

/**
 * Clase abstracta que modela la casilla de un tablero del juego de la oca.
 * Como máximo sólo puede haber un jugador por casilla en un momento dado, 
 * a excepción de la casilla de inicio.
 */
public abstract class Casilla {
	
	private int indice; 		// Índice de la casilla en tablero
	private int indiceDestino;	// Índice de la casilla donde se redirige al jugador
	private Jugador jugador;	// Jugador que ocupa la casilla
	private Tablero tablero;	// Tablero

	/**
	 * Instancia una nueva casilla.
	 *
	 * @param tablero tablero que contiene a la casilla
	 * @param indice índice de la casilla en el tablero
	 * @param indiceDestino índice de la casilla a la que se redirige al jugador
	 */
	public Casilla( Tablero tablero, int indice, int indiceDestino ) {
		this.tablero = tablero;
		this.indice = indice;
		this.indiceDestino = indiceDestino;
	}		
	
	/**
	 * Devuelve el índice correspondiente a la casilla en el tablero
	 * 
	 * @return indice correspondiente a la casilla
	 */
	public int getIndice(){
		return indice;
	}

	/**
	 * Devuelve el índice de la casilla a la que se redirige al jugador
	 * 
	 * @return indice de la casilla a la que se redirige al jugador
	 */
	public int getIndiceDestino(){
		return indiceDestino;
	}
	
	/**
	 * Devuelve el jugador situado en la casilla, 
	 * <tt>null</tt> si la casilla está vacía.
	 *
	 * @return jugador situado en la casilla
	 * @uml.property name="jugador"
	 * @uml.associationEnd
	 */
	public Jugador getJugador() {
		return this.jugador;
	}

	/**
	 * Establece el jugador situado en la casilla
	 *
	 * @param jugador jugador situado en la casilla
	 * @uml.property name="jugador"
	 * @uml.associationEnd
	 */
	public void setJugador( Jugador jugador ) {
		this.jugador = jugador;		
	}		

	/**
	 * Devuelve el tablero que contiene a la casilla
	 *
	 * @return <tt>Tablero</tt> Devuelve el tablero.
	 * @uml.property name="tablero"
	 */
	public Tablero getTablero() {
		return tablero;
	}
	
	/**
	 * Devuelve <tt>true</tt> si hay un jugador en la casilla
	 *
	 * @return true, si ocupada
	 */
	public boolean isOcupada() {
		return this.jugador != null;
	}
	
	/**
	 * Método al que llama el jugador que cae en la casilla.
	 * Como resultado, la casilla actualiza la referencia al jugador que va a ocuparla
	 * así como el estado del jugador en función del tipo de casilla (normal, castigo o premio)
	 *   
	 * @param  jugador jugador que cae en la casilla
	 * @uml.property name="jugador" 
	 */
	public void entrar( Jugador jugador ) {
		if ( jugador != null ) {
			Casilla casillaOrigen = jugador.getCasilla();
			casillaOrigen.salir( jugador );
			if ( this.isOcupada() ) {
				// el jugador actual retrocede a la casilla de partida del jugador que llega
				OcaEnRed.getInterfaz().mostrarEvento( this.getJugador() + " de vuelta a " +  casillaOrigen );
				casillaOrigen.setJugador( this.getJugador() );
				OcaEnRed.getInterfaz().moverFicha( this.getJugador().getTurno(), casillaOrigen.getIndice(), 100 );					
				this.getJugador().setCasilla( casillaOrigen );
				this.getJugador().setRondasSinJugar( 0 );
				this.getJugador().setRepiteTurno( false );
			}
			// actualizamos jugador asociado ...
			this.jugador = jugador;
			jugador.setCasilla( this );
	    	OcaEnRed.getInterfaz().moverFicha( jugador.getTurno() , jugador.getCasilla().getIndice(), 100 );
			if ( this.getIndice() != this.getIndiceDestino() ) {
				// desplazamiento
				jugador.desplazar( this.getTablero().getCasilla( this.getIndiceDestino() ) );
				OcaEnRed.getInterfaz().moverFicha( jugador.getTurno(), this.getIndiceDestino(), 100 );
			}
		}
	}

	/**
	 * Método al que llama el jugador cuando abandona la casilla
	 * Como resultado, la casilla actualiza la referencia del jugador
	 *   
	 * @param jugador jugador que abandona la casilla
	 * @uml.property name="jugador" 
	 */	
	public void salir( Jugador jugador ) {
		if( this.jugador != jugador ) {
			OcaEnRed.getInterfaz().mostrarEvento( this.getTablero().comoTexto() );
			throw new RuntimeException();
		}
		this.setJugador( null );		
	}
		
	@Override
	public String toString() {
		return "(" + indice + ")";
	}		
} // Casilla
