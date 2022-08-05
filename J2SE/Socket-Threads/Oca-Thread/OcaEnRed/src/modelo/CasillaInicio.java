/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

import java.util.List;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * Clase CasillaInicio.
 */
public class CasillaInicio extends Casilla {

	/**
	 * Instancia una nueva CasillaInicio
	 *
	 * @param tablero el tablero
	 */
	public CasillaInicio( Tablero tablero ) {
		super( tablero, 1, 1 );
	}	
	
	/* (non-Javadoc)
	 * @see modelo.Casilla#getIndice()
	 */
	@Override
	public int getIndice(){
		return 1;
	}

	/** Jugadores. */
	private List< Jugador > jugadores = null;
	
	/**
	 * Jugadores que están en casilla inicial
	 *
	 * @return los jugadores
	 */
	public List< Jugador > getJugadores() {
		if( this.jugadores == null ) {
			this.jugadores = new ArrayList< Jugador >();
			for( Jugador jugador : OcaEnRed.getPartida().getJugadores() ) {
				this.setJugador( jugador );
			}
			System.out.println( jugadores );
		}
		return this.jugadores;
	}

	/* (non-Javadoc)
	 * @see modelo.Casilla#entrar( modelo.Jugador )
	 */
	@Override
	public void entrar( Jugador jugador ) {
		if( !this.getJugadores().contains( jugador ) ) {
			this.getJugadores().add( jugador );
			// actualizamos jugador asociado y su estado ...
			jugador.setCasilla( this );
			// turnos de penalización ( ninguno ):
			jugador.setRondasSinJugar( 0 );
			// vuelve a tirar ( falso ):
			jugador.setRepiteTurno( false );			
		}				
	}
	
	/* (non-Javadoc)
	 * @see modelo.Casilla#setJugador( modeo.Jugador )
	 */
	@Override
	public void setJugador( Jugador jugador ) {
		if( !this.getJugadores().contains( jugador ) ) {
			this.getJugadores().add( jugador );
			jugador.setCasilla( this );
		}
	}
	
	/* (non-Javadoc)
	 * @see modelo.Casilla#getJugador()
	 */
	@Override
	public Jugador getJugador() {
		if( this.getJugadores().size() == 0 ) 
			return null;
		return jugadores.get( 0 );
	}

	/* (non-Javadoc)
	 * @see oca.Casilla#salir(modelo.Jugador)
	 */
	public void salir( Jugador jugador ) {
		if( !this.getJugadores().contains( jugador ) ) {
			throw new RuntimeException();
		}
		this.getJugadores().remove( jugador );
	}
			
	/* (non-Javadoc)
	 * @see modelo.Casilla#isOcupada()
	 */
	@Override
	public boolean isOcupada() {
		return !this.getJugadores().isEmpty();
	}
}	