package uned.java2016.apitwitter.twitter.crawler.net.restful;

/**
 * 
 * Clase para almacenar los criterios de búsqueda recuperados en las llamadas a la API. Sus datos se extraen del json de respuesta
 * de la API al paginar resultados.
 * 
 * @author Jose Barba Martinez (jbarba63)
 *
 */
public class TweetSearchResult {

	/**
	 * Criterio de busqueda para la siguiente ventana.
	 */
	protected String nextResults=null;
	  public void setNextResults(String a){this.nextResults=a;}
	  public String getNextResults(){return this.nextResults;}
	
	/**
	 * Constructor por defecto
	 */
	public TweetSearchResult(){}
}
