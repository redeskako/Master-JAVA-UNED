package pro.jbelo.utils;

/**
 * Exception para capturar errores del servidor
 * @author jbelo
 */
public class ServerRunningException extends Exception {

	public ServerRunningException() {
		super();
	}

	public ServerRunningException(String message){
	    super(message);
    }
	

}
