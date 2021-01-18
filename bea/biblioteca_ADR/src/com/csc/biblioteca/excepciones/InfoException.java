/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.1 - 12/12/2015
 */

package com.csc.biblioteca.excepciones;

/** Excepción no crítica con información. */
public class InfoException extends Exception {
    private final int errorCode;
    
    /**
     * Constructor de la excepción.
     * @param errorCode Código de error de la aplicación.
     * @param message Mensaje de error para el usuario.
     */
    public InfoException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Constructor de la excepción.
     * @param errorCode Código de error de la aplicación.
     * @param message Mensaje de error para el usuario.
     * @param vendorCode Código de error del fabricante.
     */
    public InfoException(int errorCode, String message, int vendorCode) {
        super(vendorCode+". "+message);
        this.errorCode = errorCode;
    }
    
    /**
     * Obtener el código de error de la excepción.
     * @return Código de error de la excepción.
     */
    public int getErrorCode() {
        return errorCode;
    }
}
