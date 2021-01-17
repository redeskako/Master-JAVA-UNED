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
 * Clase CasillaInicio.
 */
public class CasillaInicio extends Casilla {

	/* (non-Javadoc)
	 * @see oca.Casilla#getIndice()
	 */
	public int getIndice(){
		return 1;
	}

	/** Jugadores. */
	private List< Jugador > jugadores = null;
	
	/**
	 * Jugadores.
	 *
	 * @return los jugadores
	 */
	public List< Jugador > getJugadores() {
		if( this.jugadores == null ) {
			this.jugadores = new ArrayList< Jugador >();
			for( Jugador jugador : this.getTablero().getJuego().getJugadores() ) {
				this.setJugador( jugador );
			}
			System.out.println( jugadores );
		}
		return this.jugadores;
	}

	/* (non-Javadoc)
	 * @see oca.Casilla#entrar( oca.Jugador )
	 */
	@Override
	public void entrar( Jugador jugador ) {
		if( !this.getJugadores().contains( jugador ) ) {
			this.getJugadores().add( jugador );
			// actualizamos jugador asociado y su estado ...
			jugador.setCasilla( this );
			// turnos de penalización ( ninguno )
			jugador.setRondasSinJugar( 0 );
			// vuelve a tirar ( falso )
			jugador.setRepiteTurno( false );			
		}				
	}
	
	/* (non-Javadoc)
	 * @see oca.Casilla#setJugador( oca.Jugador )
	 */
	@Override
	public void setJugador( Jugador jugador ) {
		if( !this.getJugadores().contains( jugador ) ) {
			this.getJugadores().add( jugador );
			jugador.setCasilla( this );
		}
	}
	
	/* (non-Javadoc)
	 * @see oca.Casilla#getJugador()
	 */
	@Override
	public Jugador getJugador() {
		if( this.getJugadores().size() == 0 ) return null;
		return jugadores.get( 0 );
	}

	/* (non-Javadoc)
	 * @see oca.Casilla#eliminarJugador(oca.Jugador)
	 */
	public void salir( Jugador jugador ) {
		if( !this.getJugadores().contains( jugador ) ) {
			throw new RuntimeException();
		}
		this.getJugadores().remove( jugador );
	}
		
	/**
	 * Instancia una nueva CasillaInicio
	 *
	 * @param tablero el tablero
	 */
	public CasillaInicio( Tablero tablero ) {
		super( tablero, 1, 1 );
	}	
	
	/* (non-Javadoc)
	 * @see oca.Casilla#estaOcupada()
	 */
	@Override
	public boolean estaOcupada() {
		return !this.getJugadores().isEmpty();
	}
}	