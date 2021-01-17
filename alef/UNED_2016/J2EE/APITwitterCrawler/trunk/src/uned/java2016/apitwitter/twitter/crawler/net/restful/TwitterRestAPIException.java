package uned.java2016.apitwitter.twitter.crawler.net.restful;

/**
 * Representa problemas en el acceso al API de twitter.
 * 
 * @author Jose Barba Martinez (jbarba63)
 *
 */
public class TwitterRestAPIException extends Exception {
   
	/**
	 * Guarda el codigo HTTP de respuesta asociado a la excepcion
	 */
	protected int httpResponseCode=-1;
	  public void setHttpResponseCode(int a){this.httpResponseCode=a;}
	  public int getHttpResponseCode(){return this.httpResponseCode;}
	  
	public TwitterRestAPIException(Exception e){
		super(e);
	}
	
	public TwitterRestAPIException(String e){
		super(e);
	}
	
	public TwitterRestAPIException(String s,Exception e){
		super(s,e);
	}
}
