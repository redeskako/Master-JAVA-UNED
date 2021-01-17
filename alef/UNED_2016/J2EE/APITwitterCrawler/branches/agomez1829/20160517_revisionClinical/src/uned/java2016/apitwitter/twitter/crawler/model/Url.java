package uned.java2016.apitwitter.twitter.crawler.model;

/**
 * Ojbeto de negocio Twitter: Url
 * Representa una URL asociada a un tweet.
 * 
 * @author Jose Barba Martinez (jbarba63)
 *
 */

public class Url {
	/**
	 * Url encontrada en el tweet
	 */
    protected String url=null;
      public void setUrl(String a){this.url=a;}
      public String getUrl(){return this.url;}
    /**
     * Url completa encontrada en el tweet.
     */
    protected String expandedUrl=null;
      public void setExpandedUrl(String a){this.expandedUrl=a;}
      public String getExpandedUrl(){return this.expandedUrl;}
    
    /**
     * Constructor por defecto.
     */
    public Url(){}
    
}
