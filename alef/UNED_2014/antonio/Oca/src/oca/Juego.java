/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package oca;

import java.util.List;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * Clase Juego.
 * 
 * Mantiene la información de la partida en juego
 */
public class Juego {

	/** Dados con los que se juega una partida. 
     * @uml.property name="dados"
     * @uml.associationEnd */
    private Dado[] dados = { new Dado( 6 ), new Dado( 6 ) };    

    /**
     * Devuelve los dados del juego.
     *
     * @return <tt>Dado[]</tt> Dados del juego
     * @uml.property name="dados"
     */
    public Dado[] getDados() { 
       return this.dados ; 
    }    
	
	/** Tablero. @uml.property name="tablero" */
	private Tablero tablero;
	
	/**
	 * Método getter de la propiedad <tt>tablero</tt>.
	 *
	 * @return Devuelve el tablero.
	 * @uml.property name="tablero"
	 */
	public Tablero getTablero() {
		return tablero;
	}

	/**
	 * Método setter de la propiedad <tt>tablero</tt>.
	 *
	 * @param tablero Objeto tablero que se asigna.
	 * @uml.property name="tablero"
	 */
	public void setTablero( Tablero tablero ) {
		this.tablero = tablero;
	}

	/** Jugadores. @uml.property name="jugadores" */
	private List< Jugador > jugadores = new ArrayList< Jugador >();
	
	/**
	 * Método getter de la propiedad <tt>jugadores</tt>.
	 * Devuelve la lista de los jugadores participantes.
	 *
	 * @return Devuelve los jugadores.
	 * @uml.property name="jugadores"
	 */
	public List<Jugador> getJugadores() {
		return jugadores;
	}

	/**
	 * Método setter de la propiedad <tt>jugadores</tt>.
	 *
	 * @param jugadores Conjunto de jugadores que se asigna.
	 * @uml.property name="jugadores"
	 */
	public void setJugadores( List< Jugador > jugadores ) {
		this.jugadores = jugadores;
	}
	
	/**
	 * Añade jugador.
	 *
	 * @param jugador nuevo jugador
	 */
	public void addJugador( Jugador jugador ) {
		this.jugadores.add( jugador );
	}
	
	/**
	 * Añade jugador por nombre.
	 *
	 * @param nombre nombre del jugador
	 */
	public void addJugadorPorNombre( String nombre ) {
		this.jugadores.add( new Jugador( nombre, this.getJugadores().size() + 1, this.getTablero().getCasillaInicio() ) );
	}	

	/**
	 * El juego ha terminado.
	 *
	 * @return true, si se ha terminado el juego
	 */
	private boolean juegoTerminado() {
		for( Jugador jugador : jugadores ) {
			if( jugador.esGanador() ) return true;
		}
		return false;
	}
	
	/** Ronda. @uml.property name="ronda" */
	private int ronda = 0;

	/**
	 * Método getter de la propiedad <tt>ronda</tt>.
	 *
	 * @return Devuelve ronda actual.
	 * @uml.property name="ronda"
	 */
	public int getRonda() {
		return ronda;
	}
			
	/**
	 * Jugar ronda.
	 */
	private void jugarRonda() {
		ronda++;
		System.out.println( "Nueva ronda: " + ronda );
		for( Jugador jugador : jugadores ) {
			if ( jugador.getRondasSinJugar() == 0 ) {
				do {
					jugador.jugarTurno();
				} while( jugador.getRepiteTurno() && !jugador.esGanador() );
			} 
			else {
				jugador.setRondasSinJugar( jugador.getRondasSinJugar() - 1 );
			}
		}
		this.actualizar();
	}
	
	/**
	 * Jugar.
	 */
	public void jugar(){
		while( !this.juegoTerminado() && ( ronda < 100 ) ) {
			this.jugarRonda();
		}
		Casilla[] casillas = this.getTablero().getCasillas();
		System.out.println( casillas[ casillas.length - 1 ].getJugador() + " ha ganado" );
	}
	
		
	/**
	 * Instancia un nuevo juego.
	 * Inicializa el array de jugadores de la partida, en principio vacios.
	 * @param tablero tablero
	 */
	public Juego( Tablero tablero ) {		
		this.tablero = tablero;		
		this.tablero.inicializarTablero();
		tablero.setJuego( this );
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String cadena = "";
		cadena += "Jugadores:\n";
		for ( Jugador jugador : jugadores ){ cadena += jugador + "\n"; }
		
		cadena += "Tablero:\n";
		cadena += this.tablero.toString();
		return cadena;
	}
	
	/**
	 * Ascii ui.
	 *
	 * @return representación de tablero como texto
	 */
	public String asciiUI() {
		return tablero.comoTexto();
	}
	
	/**
	 * Actualizar.
	 */
	public void actualizar() {
		System.out.println( this.asciiUI() );
	}
	
	/**
	 * Método main.
	 *
	 * @param params parámetros
	 */
	public static void main( String[] params ) {		
		Tablero tablero = new TableroOca();
		Juego juego = new Juego( tablero );
		juego.addJugadorPorNombre( "A" );
		juego.addJugadorPorNombre( "B" );
		juego.addJugadorPorNombre( "C" );
		juego.addJugadorPorNombre( "D" );
		juego.addJugadorPorNombre( "E" );
		System.out.println( juego.getJugadores() );
		System.out.println( juego.asciiUI() );
		juego.jugar();
	}
}