/*
 * Clase que generará los errores del juego del ahorcado
 */
package es.uned.master.java.juegoAhorcado.base;

public class AhorcadoException extends RuntimeException {
    public AhorcadoException(String mensaje) {
        super("Ahorcado Exception:" + mensaje);
    }
    public AhorcadoException() {
    	super("Ahorcado Exception...");
    }
}

