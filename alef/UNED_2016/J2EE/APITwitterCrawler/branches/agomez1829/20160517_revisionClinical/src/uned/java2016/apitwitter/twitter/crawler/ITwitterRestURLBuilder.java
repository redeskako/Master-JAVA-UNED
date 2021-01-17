package uned.java2016.apitwitter.twitter.crawler;

/**
 * Creador de URLs para realizar llamadas a la API RestFUL de Twitter
 * 
 * @author Jose Barba Martinez (jbarba63)
 *
 */
public interface ITwitterRestURLBuilder {

	/**
	 * Construye la URL para acceso REST a la API
	 * @return Cadena con la URL para acceder a la API
	 */
	public String buildUrl();
	
}
