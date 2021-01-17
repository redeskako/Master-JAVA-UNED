package es.csc.biblioteca.exceptions;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class MandatoryFieldException extends Exception {

    public MandatoryFieldException() {
    }

    public MandatoryFieldException(String message) {
        super(message);
    }

    public MandatoryFieldException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
