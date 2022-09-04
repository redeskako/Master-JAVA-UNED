/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Partida extends Observable {
	
	private String nombreJugadorLocal;
	// comunicaciones
	private String IPServidor;
	private int puertoServidor;
	private int puertoLocal;
	
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

	private JugadorLocal jugadorLocal;
	
	/** Jugadores. @uml.property name="jugadores" */
	private List< Jugador > jugadores = new ArrayList< Jugador >();
	
	/**
	 * Método getter de la propiedad <tt>jugadores</tt>.
	 * Devuelve la lista de los jugadores participantes.
	 *
	 * @return Devuelve los jugadores.
	 * @uml.property name="jugadores"
	 */
	public List< Jugador > getJugadores() {
		List< Jugador > listaJugadores = new ArrayList< Jugador >();
		for( Jugador jugador : jugadores ) {			
			if( jugador.isActivo() ) {
				listaJugadores.add( jugador );
			}
		}
		jugadores = listaJugadores;
		return jugadores;
	}

	/**
	 * Obtiene al jugador local de esta partida.
	 * @return Jugador jugador local
	 */
	public Jugador getJugadorLocal() {
		return jugadorLocal;
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
	public synchronized void addJugador( Jugador jugador ) {
		this.jugadores.add( jugador );
		OcaEnRed.getInterfaz().getTablero().addFicha( jugador.getTurno() );
		OcaEnRed.getInterfaz().mostrarJugadores();		
	}

	/**
	 * Elimina jugador.
	 *
	 * @param jugador jugador que va a eliminarse
	 */
	public synchronized void removeJugador( Jugador jugador ) {
		// this.jugadores.remove( jugador );
		OcaEnRed.getInterfaz().mostrarJugadores();
	}
	
	/**
	 * Espera un tiempo máximo conexiones entrantes al socket servidor.
	 * Dos posibilidades:
	 * NUEVO : Un nuevo jugador se quiere conectar, para eso:
	 * 	 Se leen parámetros de conexión del jugador conectado.
	 * 	 Se envian parámetros de conexión de anfitrión.
	 *   Se envian parámetros de conexión de los jugadores ya conectados
	 *   Se crea al jugador que se acaba de conectar asignándole el nuevo turno.
	 * INVITADO : Un jugador conecta con los jugadores que le ha pasado el anfitrión
	 * 	 Se lee la informacion del jugador conectado.
	 *   Se crea al jugador asignándole el turno adecuado.
	 */
	private Jugador esperarJugador( ServerSocket socketServidor ) throws Exception {
		JugadorRemoto nuevoJugador = null;
		try {
			// conexiones entrantes
			Socket socket = ( Socket ) socketServidor.accept();
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
			PrintWriter out = new PrintWriter( socket.getOutputStream(), true );
			// protocolo comunicaciones: comunicación entrante 
			String mensaje = in.readLine();
			// Nuevo jugador			
			if( mensaje.equals( Protocolo.NUEVO ) ) {
				// Si somos el anfitrión
				if( getJugadorLocal().isAnfitrion() ) {
					OcaEnRed.getInterfaz().mostrarEvento( "Se está conectando un nuevo jugador ..." );
					String nombre = in.readLine();
					String ip = in.readLine();
					int puerto = Integer.parseInt( in.readLine() );
					
					OcaEnRed.getInterfaz().mostrarEvento( "Enviando informacion de mi jugador.." );
					out.println( getJugadorLocal().getNombre() );
					out.println( getJugadorLocal().getDireccionIP() );
					out.println( String.valueOf( getJugadorLocal().getPuertoLocal() ) );
					out.println( String.valueOf( getJugadorLocal().getTurno() ) );
					
					OcaEnRed.getInterfaz().mostrarEvento( "Enviando informacion resto jugadores ..." );					
					out.println( String.valueOf( jugadores.size() - 1 ) );
					// Enviamos informacion de todos los jugadores conectados
					for( Jugador jugador : jugadores ) {
						if( jugador.isAnfitrion() ) continue;  
						out.println( jugador.getNombre() );
						out.println( jugador.getDireccionIP() );
						out.println( String.valueOf( jugador.getPuertoLocal() ) );
						out.println( String.valueOf( jugador.getTurno() ) );						
					}
					// Creamos al nuevo jugador
					nuevoJugador = new JugadorRemoto( 
							nombre, jugadores.size(), this.getTablero().getCasillaInicio(), 
							ip, puerto, socket,
							in, out );
					addJugador( nuevoJugador );					
					
					OcaEnRed.getInterfaz().mostrarEvento( "Esperando a que el jugador se ponga\nen contacto con los demas jugadores..." );
					while( !in.readLine().equals( Protocolo.PRESENTADO ) ) {}
					OcaEnRed.getInterfaz().mostrarEvento( "Apuntado un nuevo jugador" );
				} 
				else {
					// mensaje de nuevo jugador y no somos el anfitrión
					out.println( Protocolo.KO );
				}
			}
			// Jugador que ha conectado con anfitrión 
			if( mensaje.equals( Protocolo.INVITADO ) ) {
				// este mensaje no puede recibirlo el jugador anfitrión
				if( !getJugadorLocal().isAnfitrion() ) {				
					OcaEnRed.getInterfaz().mostrarEvento( "Conectando nuevo jugador invitado..." );
					String nombre = in.readLine();
					String ip = in.readLine();
					int puerto = Integer.parseInt( in.readLine() );
					int turno = Integer.parseInt( in.readLine() );
					
					// Creamos al nuevo jugador
					addJugador( nuevoJugador = 
							new JugadorRemoto( 
									nombre,	turno, this.getTablero().getCasillaInicio(),
									ip,	puerto,	socket, 
									in, out ) 
					);										
					OcaEnRed.getInterfaz().mostrarEvento( "El jugador ha entrado a la partida!" );
				}
				else {
					// somos el anfitrión y mensaje de jugador invitado
					out.println( Protocolo.KO );					
				}
			}
		} catch( SocketTimeoutException ste ) {
			// timeout comunicación
			return null;
		}
		// Devuelve al jugador que se acaba de conectar
		return nuevoJugador;
	}
	
	/**
	 * Envía a todos los jugadores de esta partida un mensaje (String)
	 * @param String mensaje a enviar a todos los jugadores.
	 */
	public void enviarATodos( String mensaje ) throws Exception {
		// Para todos los jugadores activos en la partida
		for( Jugador jugador : jugadores) {
			if( jugador instanceof JugadorLocal ) continue;
			if( jugador.isActivo() ) {
				jugador.enviarMensaje( mensaje );
			}				
		}
	}

	private volatile boolean iniciada;

	/**
	 * La partida ha comenzado.
	 *
	 * @param iniciada true si ha comenzado
	 */
	public void setIniciada( boolean iniciada ) {
		this.iniciada = iniciada;
	}
	
	/**
	 * La partida ha comenzado.
	 *
	 * @return true, si ha comenzado la partida
	 */
	public boolean isIniciada() {
		return iniciada;
	}
	
	/**
	 * La partida ha terminado.
	 *
	 * @return true, si se ha terminado la partida
	 */
	public boolean isTerminada() {
		for( Jugador jugador : jugadores ) {
			if( jugador.isGanador() ) return true;
		}
		return false;
	}

	private volatile boolean cancelada;

	/**
	 * La partida se ha cancelado.
	 *
	 * @param cancelada true si se ha cancelado
	 */
	public void setCancelada( boolean cancelada ) {
		this.cancelada = cancelada;
	}
	
	/**
	 * La partida se ha cancelado.
	 *
	 * @return true, si se ha cancelado la partida
	 */
	public boolean isCancelada() {
		return cancelada;
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
	 * @throws Exception 
	 */
	private void jugarRonda() throws Exception {
		ronda++;
		for( Jugador jugador : this.getJugadores() ) {
			OcaEnRed.getInterfaz().mostrarJugadores();
			OcaEnRed.getInterfaz().mostrarEvento( "Turno: " + jugador.getNombre() );
			if ( jugador.getRondasSinJugar() == 0 ) {
				do {
					jugador.jugarTurno();									
				} while( jugador.getRepiteTurno() && !jugador.isGanador() );
				if( jugador.isGanador() ) return;
			} 
			else {
				OcaEnRed.getInterfaz().mostrarEvento( "Turno sin tirar..." );				
				jugador.setRondasSinJugar( jugador.getRondasSinJugar() - 1 );				
			}			
		}
	}	

	public void finalizar() {
		for( Jugador jugador : jugadores ) {
			if( jugador.getSocket() != null ) {
				if( !jugador.getSocket().isClosed() )
					try {
						jugador.getSocket().close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}			
	}
	
	/**
	 * Jugar.
	 * @throws Exception 
	 */
	public void jugar() throws Exception{
		// mensaje de inicio de la partida
		OcaEnRed.getInterfaz().mostrarEvento( "Empezamos nueva partida!" );		
		while( !isTerminada() && !isCancelada() ) {
			OcaEnRed.getInterfaz().enableBotonDados( false );
			jugarRonda();			
		}
		for( Jugador jugador: getJugadores() ) {
			if( jugador.isGanador() ) {
				OcaEnRed.getInterfaz().mostrarEvento( jugador + " ha ganado" );
			}
		}		
	}
	
	public void inicializar() throws Exception {

		try {
			jugadorLocal = new JugadorLocal( this.nombreJugadorLocal, 0, this.getTablero().getCasillaInicio(), puertoLocal );
		} catch ( UnknownHostException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if( this.IPServidor != null ) {
			// protocolo comunicación: conectar con anfitrión
			conectarConAnfitrion();
			// Avisa al jugador de que se ha conectado a la partida			
			OcaEnRed.getInterfaz().mostrarEvento( "Protocolo finalizado. Partida creada." );
		}
		this.addJugador( jugadorLocal );		

		OcaEnRed.getInterfaz().mostrarJugadores();
		OcaEnRed.getInterfaz().enableBotonInicio( jugadorLocal.isAnfitrion() );
		
		try {
			ServerSocket socketServidor = new ServerSocket( puertoLocal );
			try {
				// Espera la conexion de un jugador durante 100 milisegundos
				socketServidor.setSoTimeout( 100 );
				while( !this.isIniciada() && !this.isCancelada() ) { 
					Jugador nuevoJugador = this.esperarJugador( socketServidor );
					if( nuevoJugador != null ) {
						notifyObservers( this );
					}
				}				
			} 
			finally {
				socketServidor.close();		
			}
		}
		catch ( IOException ioException ) {
			// TODO Auto-generated catch block
			ioException.printStackTrace();
		}
		
		if( jugadorLocal.isAnfitrion() && !this.isCancelada() ) {
			OcaEnRed.getInterfaz().enableBotonInicio( false );
			this.enviarATodos( Protocolo.INICIO );
		}
	}
		
	public Partida( 
			 String nombre, 
			 int puertoLocal, 
			 String IPConexion, 
			 int puertoConexion ) throws IOException {
				
		this.nombreJugadorLocal = nombre;
		this.puertoLocal = puertoLocal;
		this.IPServidor = IPConexion;
		this.puertoServidor = puertoConexion;

		this.tablero = new TableroOca();		
		this.tablero.inicializarTablero();
		this.tablero.setPartida( this );		
	}
	
	private void conectarConAnfitrion() throws IOException {		
		
		// Escribe mensaje por la interfaz
		OcaEnRed.getInterfaz().mostrarEvento( "Conectando con la direccion: " + IPServidor );
		Socket socket = new Socket( IPServidor, puertoServidor );
        BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
		PrintWriter out = new PrintWriter( socket.getOutputStream(), true );
		
		// protocolo comunicación: enviar información de jugador
		OcaEnRed.getInterfaz().mostrarEvento( "Enviando informacion a anfitrión ..." );						
		out.println( Protocolo.NUEVO );
		out.println( getJugadorLocal().getNombre() );
		out.println( getJugadorLocal().getDireccionIP() );
		out.println( String.valueOf( getJugadorLocal().getPuertoLocal() ) );

		OcaEnRed.getInterfaz().mostrarEvento( "Recibiendo informacion de anfitrión..." );
		// Recibe informacion del anfitrión
		String id = in.readLine();
		String dir = in.readLine();
		int puerto = Integer.parseInt( in.readLine() );
		int turno = Integer.parseInt( in.readLine() );		
		
		List< Jugador > invitados = getJugadoresInvitados( in );
		
		addJugador( new JugadorRemoto( 
				id, turno, this.getTablero().getCasillaInicio(), 
				dir, puerto, socket, 
				in, out )
		);					
		
		for( Jugador j: invitados ) addJugador( j );

		// protocolo comunicación: avisados resto de jugadores de partida
		out.println( Protocolo.PRESENTADO );
	}
	
	private List< Jugador > getJugadoresInvitados( BufferedReader in ) throws IOException {		
		// protocolo comunicación: recibir información de jugadores apuntados
		int jugadores = Integer.parseInt( in.readLine() );
		// Establecemos el turno, que será el último
		getJugadorLocal().setTurno( jugadores + 1 );

		OcaEnRed.getInterfaz().mostrarEvento( "Recibiendo lista de jugadores..." );
		ArrayList< Jugador > ju = new ArrayList< Jugador >();
		Socket socketParticipante = null;
		for( int j = 0 ; j < jugadores; j++ ) {
			// datos del siguiente jugador 
			String id = in.readLine();
			String dir = in.readLine();
			int puerto = Integer.parseInt( in.readLine() );
			int turno = Integer.parseInt( in.readLine() );
			// Establece un socket hacia el jugador
			try {
				socketParticipante = new Socket( dir, puerto );
				// Se crean los streams de comunicacion
				BufferedReader inParticipante = new BufferedReader( new InputStreamReader( socketParticipante.getInputStream() ) );				
				PrintWriter outParticipante = new PrintWriter( socketParticipante.getOutputStream(), true );													
				// protocolo comunicación: enviar información jugador local  
				outParticipante.println( Protocolo.INVITADO );
				outParticipante.println( getJugadorLocal().getNombre() );
				outParticipante.println( getJugadorLocal().getDireccionIP() );
				outParticipante.println( String.valueOf( getJugadorLocal().getPuertoLocal() ) );
				outParticipante.println( String.valueOf( getJugadorLocal().getTurno() ) );
				// Creamos al nuevo jugador remoto
				ju.add( new JugadorRemoto( 
								id, turno, this.getTablero().getCasillaInicio(), 
								dir, puerto, socketParticipante, 
								inParticipante, outParticipante ) 
				);									
			}
			catch ( Exception e ) {
				e.printStackTrace();
			}
		}
		return ju;
	}
	
}
