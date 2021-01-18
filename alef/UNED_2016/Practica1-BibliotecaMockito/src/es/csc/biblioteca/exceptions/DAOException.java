package es.csc.biblioteca.exceptions;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class DAOException extends Exception {

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
