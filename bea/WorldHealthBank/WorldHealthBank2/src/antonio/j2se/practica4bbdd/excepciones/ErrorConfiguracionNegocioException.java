package antonio.j2se.practica4bbdd.excepciones;
/**
 * Exception que se lanza cuando ocurre un error en la configuracion del cliente
 * @author  Antonio Sánchez Antón
 * @see antonio.j2se.practica4bbdd.excepciones.NegocioException
 * @since  1.0
 */
public class ErrorConfiguracionNegocioException extends NegocioException {
	private static final long serialVersionUID = 5569154220523386703L;

	public ErrorConfiguracionNegocioException(Exception exc) {
		super(exc);
	}

	public ErrorConfiguracionNegocioException(String mensaje) {
		super(mensaje);
	}
	
	public ErrorConfiguracionNegocioException(String mensaje,Exception exc){
		super(mensaje,exc);
	}

}
