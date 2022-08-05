/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class JugadorRemoto extends Jugador {
	
	/** 
	 * Socket para la comunicación con el jugador
     * @uml.property name="socket" 
     */	
	private Socket socket;

	/** Stream de entrada asociado al socket 
     */	
	private BufferedReader in;
	
	/** Stream de salida asociado al socket
     */	
	private PrintWriter out;
	
    /**
     * Devuelve el socket del jugador.
     *
     * @return <tt>Socket</tt> socket para la comuniciación con este jugador 
     * @uml.property name="socket"
     */
    public Socket getSocket() { 
       return this.socket; 
    }
	
    /**
     * Establece el socket del jugador.
     *
     * @param socket socket para la comuniciación con este jugador 
     * @throws IOException 
     * @uml.property name="socket"
     */
    public void setSocket( Socket socket ) throws IOException {
    	if( socket != null ) {
    		this.socket = socket;
    		this.in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
    		this.out = new PrintWriter( new OutputStreamWriter( socket.getOutputStream() ) );
    	}
    }
    
    /**
     * Consulta el estado del socket del jugador
     *
     * @return true si las comunicaciones están abiertas, false en caso contrario 
     * @uml.property name="isActivo"
     */
    @Override
    public boolean isActivo() {
    	return ( this.socket != null && !this.socket.isClosed() );
    }

    private boolean presentado;
    
    /**
     * Consulta si el jugador remoto se ha presentado al resto de jugadores de la partida
     *
     * @return true si el jugador remoto se ha presentado al resto 
     * @uml.property name="isPresentado"
     */
    public synchronized boolean isPresentado() {
    	return ( presentado );
    }
    
    private boolean haTirado;
    
    public synchronized void setHaTirado( boolean haTirado ) {
    	this.haTirado = haTirado;
    }
        
    /**
     * Envía mensaje al jugador a través de su socket
     *
     * @param mensaje mensaje que se envía al jugador 
     */
     @Override
    public void enviarMensaje( String mensaje ) throws Exception {
    	if( this.socket != null && !this.socket.isClosed() ) {
    		out.println( mensaje );
    	}
    }

    /**
     * Recibe mensaje del jugador a través de su socket
     *
     * @return mensaje leído 
     * @throws IOException 
     */
    @Override
    public String recibirMensaje( ) throws IOException {
    	if( this.socket != null && !this.socket.isClosed() ) {
    		return in.readLine();
    	}
    	return null;
    }

	        
	public JugadorRemoto( 
			String nombre, 
			int turno, 
			Casilla casilla,
			String ip,
			int puerto, 
			Socket socket, 
			BufferedReader in, 
			PrintWriter out ) throws IOException {
		
		super( nombre, turno, casilla );
		setDireccionIP( ip );
		setPuertoLocal( puerto );
		this.presentado = false;
		this.socket = socket;
		this.in = in;
		this.out = out;
		// Lanza la ejecucion del hilo mediante el metodo run()
		start();                		
	}
	
	@Override
	public void jugarTurno() throws Exception {		
		while( !this.haTirado ) { 
			if ( OcaEnRed.getPartida().isCancelada() ) {
				return;
			};
		}
		int tirada1 = OcaEnRed.getPartida().getDados()[ 0 ].getUltimaTirada();
		int tirada2 = OcaEnRed.getPartida().getDados()[ 1 ].getUltimaTirada();
    	this.haTirado = false;
    	OcaEnRed.getInterfaz().lanzarDados( new int[] { tirada1, tirada2 } );
    	OcaEnRed.getInterfaz().mostrarEvento( "Resultado de los dados: " + String.valueOf( tirada1 + tirada2 ) );
    	getCasilla().getTablero().getCasilla( getCasilla(), tirada1 + tirada2 ).entrar( this );    	
	}
	
	@Override
	public void run() {
		try {
			// Si el socket esta abierto y esta asignado
			if( socket != null ) {
				while( !socket.isClosed() ) {
					// Lee el mensaje de este jugador (en el otro extremo)
					String mensaje = recibirMensaje();
					// Mensaje de peticion de comienzo de partida
					if( mensaje.equals( Protocolo.INICIO ) ) {
						OcaEnRed.getPartida().setIniciada( true );
					}
					// Mensaje de peticion de recepcion de tirada
					if( mensaje.equals( Protocolo.TIRADA ) ) {
						OcaEnRed.getPartida().getDados()[ 0 ].setUltimaTirada( Integer.parseInt( recibirMensaje() ) );
						OcaEnRed.getPartida().getDados()[ 1 ].setUltimaTirada( Integer.parseInt( recibirMensaje() ) );
						this.setHaTirado( true );
					}
					// Mensaje de peticion de recepcion de tirada
					if( mensaje.equals( Protocolo.PRESENTADO ) ) {
						this.presentado = true;
					}
				}
			}
			// Si ha habido error
		} catch( Exception e1 ) {
			// Mensaje de cierre de comunicaciones
			OcaEnRed.getInterfaz().mostrarError( "Perdida la comunicación con " + getNombre() + "\nerror: " + e1.getMessage() );
			OcaEnRed.getInterfaz().mostrarError( "Cerrando conexión" );
		} 
		finally {
			// Cerrar las comunicaciones (socket, streams)
			try {
				in.close();
			    out.close();
				if ( !socket.isClosed() ) socket.close();
				OcaEnRed.getPartida().removeJugador( this );
			} catch( Exception e2 ) {
				// Error al cerrar la comunicacion
				OcaEnRed.getInterfaz().mostrarError( "Imposible cerrar comunicacion con el jugador." );
			}
		}		
	}
	
	public String toString() {
		return getNombre() + " [ " + getDireccionIP() + ":" + String.valueOf( getPuertoLocal() + "] " + "Turno " + getTurno() );
	}
}
