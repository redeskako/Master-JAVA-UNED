/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class JugadorLocal extends Jugador {
	
	public JugadorLocal( String nombre, int turno, Casilla casilla, int puertoLocal ) throws UnknownHostException {
		super( nombre, turno, casilla );
		setDireccionIP( InetAddress.getLocalHost().getHostAddress() );
		setPuertoLocal( puertoLocal );
	}
	    
	/**
	 * Envío mensaje a jugador por medio de socket
	 * @param mensaje mensaje para la comunicación
	 */
    @Override
	public void enviarMensaje( String mensaje ) throws Exception {};
    
	/**
	 * Recepción de mensaje de jugador mediante socket
	 * @return String mensaje que envió el jugador
	 */
	@Override
	public String recibirMensaje() throws Exception { return null; };
	
    @Override
	public void jugarTurno() throws Exception {

    	OcaEnRed.getInterfaz().setBotonDadosPulsado( false );
    	OcaEnRed.getInterfaz().enableBotonDados( true );
    	OcaEnRed.getInterfaz().mostrarEvento( "Lanza los dados, por favor" );
    	
    	while( !OcaEnRed.getInterfaz().isBotonDadosPulsado() ) {
			if ( OcaEnRed.getPartida().isCancelada() ) {
				this.setRepiteTurno( false );
				return;
			};    		
    	}
    	
        OcaEnRed.getInterfaz().enableBotonDados( false );
        
    	int tirada1 = tirarDado();
    	int tirada2 = tirarDado();
    	    	
    	OcaEnRed.getPartida().enviarATodos( Protocolo.TIRADA  );
    	OcaEnRed.getPartida().enviarATodos( String.valueOf( tirada1 ) );
    	OcaEnRed.getPartida().enviarATodos( String.valueOf( tirada2 ) );
    	OcaEnRed.getInterfaz().lanzarDados( new int[] { tirada1, tirada2 } );
    	OcaEnRed.getInterfaz().mostrarEvento( "Resultado de los dados: " + String.valueOf( tirada1 + tirada2 ) );
    	
    	getCasilla().getTablero().getCasilla( getCasilla(), tirada1 + tirada2 ).entrar( this );    	
    }
    
    public void run() {    	
    }
    
	public String toString() {
		return getNombre() + " [" + getDireccionIP() + ":" + String.valueOf( getPuertoLocal() + "] " + "Turno " + getTurno() );
	}
    
}

