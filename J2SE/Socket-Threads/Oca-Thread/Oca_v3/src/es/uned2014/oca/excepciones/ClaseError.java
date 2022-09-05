package es.uned2014.oca.excepciones;

/**
 * Se define una clase ClaseError que hereda de la clase RuntimeException (java.lang) 
 * para tratar de manera controlada los errores que se produzcan en la aplicaci�n.
 * @author	Alef UNED 2014
 * @version	Oca 2.0
 * @fecha 	Abril 2014
 */
public class ClaseError extends RuntimeException{
	
	/**
	 * M�todo constructor: utiliza el m�todo constructor de RuntimeException.
	 * @param	null
	 * @return	null
	 * @throws	null
	 */
	public ClaseError (){
		super("Se ha producido un error.");
	}
	
	/**
	 * M�todo constructor: utiliza el m�todo constructor de RuntimeException.
	 * @param	Sring que describe el error que se ha producido.
	 * @return	null
	 * @throws	null
	 */
	public ClaseError (String s){
		super("Se ha producido un error: " + s);
	}
}
