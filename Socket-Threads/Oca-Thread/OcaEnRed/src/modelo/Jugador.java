/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

import java.net.Socket;

// TODO: Auto-generated Javadoc

/**
 * Clase que representa a cada jugador que participa en la partida.
 * Guarda los principales campos de información de identificación y estado
 * relativos al jugador, como su nombre, turno asignado y su situación en 
 * la partida.
 * De cara a una ampliación para juego en red distribuido puede pensarse
 * en los objetos jugador como representantes o proxies de los jugadores
 * físicos conectados a la misma partida, con campos relativos a la conexión
 * (dirección IP, puerto y socket con sus correspondientes streams de entrada 
 * y salida)  
 * Jugador.
 */
public abstract class Jugador extends Thread{    
	
	/**
	 * Nombre del jugador.
     * @uml.property name="nombre" 
     */	
    private String nombre;

    /**
     * Devuelve el nombre del jugador.
     *
     * @return <tt>String</tt> nombre del jugador
     * @uml.property name="nombre"
     */
    public String getNombre() { 
       return this.nombre; 
    }	

    /** Turno asignado al jugador en la partida.
     * @uml.property name="turno" 
     */	
    private int turno;

    /**
     * Devuelve el turno del jugador.
     *
     * @return <tt>int</tt> turno del jugador
     * @uml.property name="turno"
     */
    public int getTurno() { 
       return this.turno; 
    }

    /**
     * Establece el turno del jugador.
     *
     * @return <tt>int</tt> turno del jugador
     * @uml.property name="turno"
     */
    public void setTurno( int turno ) { 
       this.turno = turno; 
    }
    
	/** casilla donde se encuentra el jugador. 
     * @uml.property name="casilla"
     * @uml.associationEnd */
    private Casilla casilla;    

    /**
     * Devuelve la casilla donde se encuentra el jugador.
     *
     * @return <tt>Casilla</tt> Casilla actual del jugador
     * @uml.property name="casilla"
     */
    public Casilla getCasilla() { 
       return this.casilla ; 
    }

    /**
     * Establece la casilla en la que se va a situar el jugador.
     *
     * @param casilla casilla destino 
     * @uml.property name="casilla"
     */
    public void setCasilla( Casilla casilla ) { 
       this.casilla = casilla; 
    }    
    
    /** Rondas de penalización por caer en casilla castigo.
     * @uml.property  name="turnosSinJugar" 
     */	
    private int rondasSinJugar;

    /**
     * Devuelve el número de rondas que aún deberá esperar el jugador.
     *
     * @return <tt>int</tt> rondas que restan sin posesión de turno 
     * @uml.property name="rondasSinJugar"
     */
    public int getRondasSinJugar() { 
       return this.rondasSinJugar; 
    }

    /**
     * Establece el número de rondas sin turno.
     *
     * @param <tt>int</tt> rondas que restan sin posesión de turno 
     * @uml.property name="rondasSinJugar"
     */
    public void setRondasSinJugar( int rondasSinJugar ) { 
       this.rondasSinJugar = rondasSinJugar; 
    }
    
    /** Indicador de que el jugador debe seguir tirando.
     * @uml.property name="repiteTurno" 
     */	
    private boolean repiteTurno;

    /**
     * Devuelve si el jugador debe volver a lanzar los dados.
     *
     * @return <tt>boolean</tt> cierto si debe volver a tirar; falso en caso contrario 
     * @uml.property name="repiteTurno"
     */
    public boolean getRepiteTurno() { 
       return this.repiteTurno; 
    }

    /**
     * Actualiza indicador de volver a jugar.
     *
     * @param <tt>boolean</tt> cierto si debe volver a tirar; falso en caso contrario
     * @uml.property name="repiteTurno"
     */
    public void setRepiteTurno( boolean repiteTurno ) { 
       this.repiteTurno = repiteTurno; 
    }

    /** Dirección IP
     * @uml.property  name="dirección IP" 
     */	
    private String direccionIP;

    /**
     * Devuelve la dirección IP del jugador
     *
     * @return Dirección IP  
     * @uml.property name="dirección IP"
     */
    public String getDireccionIP() { 
       return this.direccionIP; 
    }

    /**
     * Establece la dirección IP del jugador
     *
     * @param Dirección IP
     * @uml.property name="dirección IP"
     */
    public void setDireccionIP( String direccionIP ) { 
       this.direccionIP = direccionIP; 
    }

    /** Puerto local
     * @uml.property name="puerto local" 
     */	
    private int puertoLocal;

    /**
     * Devuelve puerto local del jugador
     *
     * @return puerto local  
     * @uml.property name="puerto local"
     */
    public int getPuertoLocal() { 
       return this.puertoLocal; 
    }

    public Socket getSocket() {
    	return null;
    }
    /**
     * Establece puerto local del jugador
     *
     * @param puerto local
     * @uml.property name="puerto local"
     */
    public void setPuertoLocal( int puertoLocal ) { 
       this.puertoLocal = puertoLocal; 
    }
    
    /**
     * Envía mensaje al jugador
     *
     * @param mensaje mensaje que se envía al jugador 
     */
    public abstract void enviarMensaje( String mensaje ) throws Exception;

    /**
     * Recibe mensaje del jugador
     *
     * @return mensaje leído  
     */
    public abstract String recibirMensaje() throws Exception;

	public abstract void jugarTurno() throws Exception;    
    /**
     * Consulta si el jugador ha ganado la partida
     * @return <tt>boolean</tt> cierto si ha ganado la partida; falso en caso contrario 
     * @uml.property name="esGanador"
     */
    public boolean isGanador() { 
       return ( this.getCasilla() instanceof CasillaFinal ) ; 
    }
    
    public boolean isActivo() { return true; };            
	public boolean isAnfitrion() { return turno == 0; };

    
    /**
     * Constructor. Instancia un nuevo jugador.
     *
     * @param nombre nombre del jugador
     * @param turno turno asignado
     * @param casilla casilla de inicio del jugador 
     */
    public Jugador( String nombre, int turno, Casilla casilla ) {    
        this.nombre = nombre;
        this.turno = turno; 
        this.casilla = casilla;
        this.rondasSinJugar = 0;
        this.repiteTurno = false;
    }
    
    public int tirarDado() { 
		OcaEnRed.getPartida().getDados()[ 0 ].tirar();
		return OcaEnRed.getPartida().getDados()[ 0 ].getUltimaTirada();
    }
    
    /**
     * tirada de los dados.
     *
     * @return resultado aleatorio de tirar dos dados
     */ 	
    public int tirarDados() {
    	int resultado = 0; 
		for( Dado dado : OcaEnRed.getPartida().getDados() ){
			dado.tirar();
			resultado += dado.getUltimaTirada();
		}    	
        return resultado;
    }
    
    public void desplazar( Casilla casilla ) {
    	this.getCasilla().setJugador( null );
    	this.casilla = casilla;
    	casilla.setJugador( this );
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() { return nombre; }

	/**
	 * Recepción de mensajes
	 */
    @Override
	public abstract void run();
    
    
} // Jugador
