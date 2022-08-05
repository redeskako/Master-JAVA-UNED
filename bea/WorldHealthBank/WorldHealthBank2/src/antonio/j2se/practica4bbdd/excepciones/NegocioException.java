package antonio.j2se.practica4bbdd.excepciones;

/**
 * Clase que representa una Exception de Negocio
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public class NegocioException extends Exception{
	private static final long serialVersionUID = 861165638676824700L;
	protected Exception exception;
	protected String mensaje;
	
	public NegocioException(Exception exc){
		super(exc.getMessage());
		this.exception=exc;
	}
	
	public NegocioException(String mensaje){
		super(mensaje);
	}
	
	public NegocioException(String mensaje,Exception e){
		super(e.getMessage());
		this.mensaje=mensaje;
		this.exception=e;
	}
	
	
	@Override
	public String getMessage() {
		if(mensaje!=null)
			return mensaje;
		else
			return super.getMessage();
	}

	@Override
	public Throwable getCause() {
		if (exception!=null)
			return exception;
		else
		    return super.getCause();
	}


	

}
