/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package oca;

/**
 * casilla del tablero en el juego de la oca. <br/>Como máximo sólo 
 * puede haber un jugador por casilla en un momento dado, a excepción 
 * de la casilla de inicio.
 */
public abstract class Casilla {

	/** Índice. */
	protected int indice;

	/**
	 * devuelve el índice correspondiente a la casilla en el tablero
	 *
	 * @return el indice
	 */
	public int getIndice(){
		return indice;
	}

	/** Índice. */
	protected int indiceDestino;

	/**
	 * devuelve el índice al que se redirige al jugador
	 *
	 * @return el índice
	 */
	public int getIndiceDestino(){
		return indiceDestino;
	}
	
	/** Jugador. */
	protected Jugador jugador;

	/**
	 * Método al que llama el jugador que cae en la casilla.
	 * Como resultado, la casilla actualiza la referencia al jugador que va a ocuparla
	 * así como el estado del jugador en función del tipo de casilla (normal, castigo o premio)  
	 * @param  jugador jugador que cae en la casilla
	 * @uml.property  name="jugador" 
	 */
	public void setJugador( Jugador jugador ) {
		this.jugador = jugador;		
	}
		
	/**
	 * devuelve el jugador en esta casilla <tt>null</tt> si vacía.
	 *
	 * @return jugador el jugador
	 * @uml.property name="jugador"
	 * @uml.associationEnd
	 */
	public Jugador getJugador() {
		return this.jugador;
	}

	public void entrar( Jugador jugador ) {
		if ( jugador != null ) {
			Casilla casillaOrigen = jugador.getCasilla();
			casillaOrigen.salir( jugador );
			if ( this.estaOcupada() ) {
				// el jugador debe retroceder a la casilla de partida del jugador que llega
				System.out.println( this.getJugador() + " de vuelta a " +  casillaOrigen );
				casillaOrigen.setJugador( this.getJugador() );
				this.getJugador().setCasilla( casillaOrigen );
				this.getJugador().setRondasSinJugar( 0 );
				this.getJugador().setRepiteTurno( false );
			}
			// actualizamos jugador asociado ...
			this.jugador = jugador;
			jugador.setCasilla( this );
			if ( this.getIndice() != this.getIndiceDestino() ) {
				// desplazamiento
				jugador.desplazar( this.getTablero().getCasilla( this.getIndiceDestino() ) );
			}
		}
	}
	
	public void salir( Jugador jugador ) {
		if( this.jugador != jugador ) {
			System.out.println( this.getTablero().comoTexto() );
			throw new RuntimeException();
		}
		this.setJugador( null );		
	}
		
	/** Tablero. */
	protected Tablero tablero;

	/**
	 * Devuelve el tablero en que se sitúa la casilla.
	 *
	 * @return <tt>Tablero</tt> Devuelve el tablero.
	 * @uml.property name="tablero"
	 */
	public Tablero getTablero() {
		return tablero;
	}
	
	/**
	 * Instancia una nueva casilla.
	 *
	 * @param tablero el tablero
	 * @param indice el índice en el tablero
	 */
	public Casilla( Tablero tablero, int indice, int indiceDestino ) {
		this.tablero = tablero;
		this.indice = indice;
		this.indiceDestino = indiceDestino;
	}
	
	/**
	 * devuelve <tt>true</tt> si un jugador está en esta casilla
	 *
	 * @return true, si ocupada
	 */
	public boolean estaOcupada() {
		return this.jugador != null;
	}
	
	public String toString() {
		return "(" + indice + ")";
	}
	
		
} // Casilla
