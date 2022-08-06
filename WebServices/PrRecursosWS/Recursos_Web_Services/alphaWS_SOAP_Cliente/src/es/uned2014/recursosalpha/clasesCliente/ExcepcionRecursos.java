package es.uned2014.recursosalpha.clasesCliente;

/**
 * Representa los errores específicos de la aplicación de recursos.
 * 
 * @author Alpha UNED 2014
 * @version RecursosWS 1.0
 * @since Septiembre 2014
 */
public class ExcepcionRecursos extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExcepcionRecursos() {
		super("SE HA PRODUCIDO UN ERROR.");
		System.out.println("SE HA PRODUCIDO UN ERROR.");
	}

	public ExcepcionRecursos(String s) {
		super("SE HA PRODUCIDO UN ERROR. " + s);
		System.out.println("SE HA PRODUCIDO UN ERROR. " + s);
	}
}
