/*
 * Comentarios Docente
 * Aunque es una clase simple ha de venir indicada y documentada
 * Falta expresar año, author y demás información importante
 * Los métodos no expresan si da errores y los argumentos no se explicitan en comentarios
 */
package es.uned2014Oca;

public class ClaseError extends RuntimeException {
	
	public ClaseError()	{
		super("CLASE ERROR EXCEPTION");
		
	}
	
	public ClaseError(String argError)
	{
		super("CLASE ERROR EXCEPTION - "+argError);

	}
}