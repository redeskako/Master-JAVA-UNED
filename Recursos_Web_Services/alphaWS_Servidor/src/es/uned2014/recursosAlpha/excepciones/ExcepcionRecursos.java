package es.uned2014.recursosAlpha.excepciones;

/**
 * Representa los errores espec�ficos de la aplicaci�n de recursos.
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @since	Junio 2014
 */
public class ExcepcionRecursos extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExcepcionRecursos(){
		super("SE HA PRODUCIDO UN ERROR.");
		System.out.println("SE HA PRODUCIDO UN ERROR.");
	}
	
	public ExcepcionRecursos (String s){
		super("SE HA PRODUCIDO UN ERROR. " + s);
		System.out.println("SE HA PRODUCIDO UN ERROR. " + s);
	}
}
