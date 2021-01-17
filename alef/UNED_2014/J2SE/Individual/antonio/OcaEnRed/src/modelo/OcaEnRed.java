/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

import java.net.ConnectException;

import vista.*;

// TODO: Auto-generated Javadoc
/**
 * Clase OcaEnRed.
 * 
 * Mantiene la información de la partida en juego, conjunto de jugadores participantes
 * jugador local, variables para establecer comunicaciones y recibir conexiones mediante
 * sockets y flags de estado de la partida.
 */
public class OcaEnRed {

	// puerto que se utiliza por defecto
	public static final int PUERTO_POR_DEFECTO = 8888;
	
	// jugador local
	private static String nombre = null;
	// puerto de conexion local
	private static int puertoLocal = PUERTO_POR_DEFECTO;	
	// direccion IP del jugador que inicialmente actúa como servidor
	private static String IPConexion = null;
	// puerto del jugador que inicialmente actúa como servidor
	private static int puertoConexion = PUERTO_POR_DEFECTO;
	
	// Interfaz del juego
	public static Interfaz interfaz;
	
	public static Interfaz getInterfaz() {
		return interfaz;
	}
	
	// partida
	public static Partida partida;

	public static Partida getPartida() {
		return partida;
	}
	
	/**
	 * Muestra la forma adecuada de ejecutar el juego en caso de error
	 * en los parámetros con que se invoca
	 * @param String mensaje error
	 */
	public static void parametrosJuego( String error ) {
		System.out.println( error );
		System.out.println( "OcaEnRed [ -ip dir_ip [ -r puerto ] ] [ -l puerto ] jugador" );
		System.out.println( "     jugador : Nuestra identificacion en la partida" );
		System.out.println( "         -ip : IP del anfitrion ( no se informa si somos nosotros" );
		System.out.println( "          -r : Puerto de conexion con el anfitrion" );
		System.out.println( "          -l : Puerto propio de escucha" );
	}
	
	public static String parseCommandLine( String[] args ) {		
		if( ( args == null ) || ( args.length == 0 ) ) {
			return "parametros insuficientes";
		}
		        
        int i = 0;		
		String arg;
        while ( i < args.length && args[ i ].startsWith( "-" ) ) {
            arg = args[ i++ ];    	    
            if ( arg.equals( "-help" ) ) {
                parametrosJuego( null );
    			System.exit(0);
            }
            else if ( arg.equals( "-ip" ) ) {
                if ( i < args.length ) {
            		// lee el siguiente parámetro ( dirección IP )
                    IPConexion = args[ i++ ].trim();
                }
                else {
                    return "se debe indicar direccion IP con la opcion -ip";
                }
            }            
            else if ( arg.equals( "-r" ) ) {
                if ( i < args.length ) {
            		// lee el siguiente parámetro ( puerto remoto )
                	puertoConexion = Integer.parseInt( args[ i++ ].trim() );
                }
                else {
                    return "se debe indicar puerto remoto con la opcion -r";
                }
            }
            else if ( arg.equals( "-l" ) ) {
                if ( i < args.length ) {
            		// lee el siguiente parámetro ( puerto local )
            		puertoLocal = Integer.parseInt( args[ i++ ].trim() );
                }
                else {
                    return "se debe indicar puerto local con la opcion -l";
                }
            }
        }
    	// nombre del jugador local
	    if ( i == args.length ) {
	    	return "se debe informar un nombre de jugador";
	    }
	    else {
	    	nombre = args[ i ].trim();
	    }
	    
		return null;	                	
	}
									
	/**
	 * Método main.
	 *
	 * @param params parámetros
	 */
	public static void main( String[] params ) throws Exception {
		// analiza argumentos de llamada
		String error = parseCommandLine( params );
		// en caso de error, mostramos mensaje con la manera de llamar al juego 
		if( error != null ) {
			parametrosJuego( error );
			System.exit(0);
		}
		
		try {
			// se crea la interfaz gráfica del juego
			interfaz = new Interfaz();
			// inicialización de partida
			partida = new Partida( nombre, puertoLocal, IPConexion, puertoConexion );
			partida.inicializar();
			if ( !partida.isCancelada() ) {
				// comienzo juego
				partida.jugar();
			}				
			if ( partida.isCancelada() ) {				
				partida.finalizar();
				System.exit(0);				
			}
		}
		catch ( ConnectException connectException ) {			
			getInterfaz().mostrarError( "Imposible conectar con Anfitrión.\nFinaliza la partida." );
		}
		catch ( Exception exception ) {		
			exception.printStackTrace();
		}				
	}
	
}