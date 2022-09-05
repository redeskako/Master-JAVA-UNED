package es.uned.master.java.healthworldbank.servidor;

/**
 * Exception para capturar errores en el Thread del servidor
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
