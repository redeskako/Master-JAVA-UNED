package antonio.j2ee.practica1Thinspo.excepciones;


/**
 * Clase que representa una Exception de Negocio,al interactuar con BBDD
 * @author  Antonio S�nchez Ant�n
 * @since  1.0
 */
public class SQLNegocioException extends NegocioException {
	private static final long serialVersionUID = -4280652452800985247L;

	public SQLNegocioException(Exception exc) {
		super(exc);
		
	}
	public SQLNegocioException(String mensaje) {
		super(mensaje);
	}
	
	public SQLNegocioException(String mensaje,Exception exc){
		super(mensaje,exc);
	}

	

}
