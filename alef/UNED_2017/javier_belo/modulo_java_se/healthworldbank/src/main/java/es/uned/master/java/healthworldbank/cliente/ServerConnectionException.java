package es.uned.master.java.healthworldbank.cliente;

/**
 * Clase para captura de errores con el servidor
 *@jbelo
 */
public class ServerConnectionException extends Exception {

    public ServerConnectionException() {
        super();
    }

    public ServerConnectionException(String e) {
		super(e);
	}

	
}
