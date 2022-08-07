package uned.java2016.apitwitter.twitter.crawler.model;

/**
 * Objeto de negocio Twitter 'Entities'. Se utiliza como colección para guardar referencias de un objeto Tweet contra otros objetos
 * como:
 *   User
 *   Url
 *   Hashtag.
 *   
 * @author Jose Barba Martinez (jbarba63)
 *
 */
public class Entities {
	
	/**
	 * Array de hasthags
	 */
	protected Hashtag[] hashtags=null;
	  public void setHashtags(Hashtag[] h){this.hashtags=h;}
	  public Hashtag[] getHashtags(){return this.hashtags;}
	  
	/**
	 * Array de Url's
	 */
	protected Url[] urls=null;
	  public void setUrls(Url[] u){this.urls=u;}
	  public Url[] getUrls(){return this.urls;}
	  
	
	/**
	 * Constructor por defecto.
	 */
	public Entities(){}
}
