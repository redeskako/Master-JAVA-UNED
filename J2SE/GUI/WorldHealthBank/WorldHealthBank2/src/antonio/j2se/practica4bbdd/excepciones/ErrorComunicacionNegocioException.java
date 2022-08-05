package antonio.j2se.practica4bbdd.excepciones;


/**
 * Exception que se lanza cuando ocurre un error en la comunicacion entre cliente y Servidor
 * @author  Antonio Sánchez Antón
 * @see antonio.j2se.practica4bbdd.excepciones.NegocioException
 * @since  1.0
 */
public class ErrorComunicacionNegocioException extends NegocioException {
	private static final long serialVersionUID = 8085697179051109989L;

	public ErrorComunicacionNegocioException(Exception exc) {
		super(exc);
	}

	public ErrorComunicacionNegocioException(String mensaje) {
		super(mensaje);
	}
	
	public ErrorComunicacionNegocioException(String mensaje,Exception exc){
		super(mensaje,exc);
	}

}

