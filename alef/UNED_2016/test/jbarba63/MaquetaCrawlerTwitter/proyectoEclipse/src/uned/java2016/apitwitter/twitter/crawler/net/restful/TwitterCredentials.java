package uned.java2016.apitwitter.twitter.crawler.net.restful;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.*;


/**
 * Almacena las credenciales necesarias para el acceso a la API de twitter
 * @author default
 *
 */
public class TwitterCredentials {

	/**
	 * Valor Api Key para las credenciales de Twitter.
	 */
	protected String apiKey=null;
	  public void setApiKey(String apiKey){this.apiKey=apiKey;}
	  public String getApiKey(){return this.apiKey;}

	/**
	* Valor Api Secret para las credenciales de Twitter.
	*/  
	protected String apiSecret=null;
	  public void setApiSecret(String apiSecret){this.apiSecret=apiSecret;}
	  public String getApiSecret(){return this.apiSecret;}
	/**
	 * Valor tokenKey para las credenciales de Twitter  
	 */
	protected String tokenKey=null;
	  public void setTokenKey(String tokenKey){this.tokenKey=tokenKey;}
	  public String getTokenKey(){return this.tokenKey;}
	  
	/**
	 * Valor tokenSecret para las credenciales de Twitter
	 */
	protected String tokenSecret=null;
	  public void setTokenSecret(String tokenSecret){this.tokenSecret=tokenSecret;}
	  public String getTokenSecret(){return this.tokenSecret;}
	  
	  
	/**
	 * Contructor por defecto
	 */
	public TwitterCredentials(){}
	
	/**
	 * Constructor incluyendo valores para propiedades
	 * @param apiKey valor Api Key de credenciales de Twitter
	 * @param apiSecret valor Api Secret  de credenciales de Twitter
	 * @param tokenKey valor Token Key  de credenciales de Twitter
	 * @param tokenSecret valor Token Secret  de credenciales de Twitter
	 */
	public TwitterCredentials(String apiKey,String apiSecret,String tokenKey,String tokenSecret){
		this.apiKey=apiKey;
		this.apiSecret=apiSecret;
		this.tokenKey=tokenKey;
		this.tokenSecret=tokenSecret;
	}
	
	/**
	 * crea un token de acceso partiendo de las credenciales presentes en las propiedades api* y token *
	 * Se devuelve 'null' si no se puede generar el token de acceso
	 * @return
	 */
	public OAuth1AccessToken createAccessToken(){
		OAuth1AccessToken ret=null;
		if(this.getTokenKey()!=null && this.getTokenSecret()!=null)
		{
			ret=new OAuth1AccessToken(this.getTokenKey(),this.getTokenSecret());
		}
		return ret;
	}
	
	/**
	 * Creamos el servicio para poder acceder de forma autorizada a la API de twitter.
	 * Se devuelve NULL si no puede crearse el servicio
	 * @return Instancia de servicio para poder llamar a la api
	 */
	public OAuth10aService createService(){
		OAuth10aService ret=null;
		if(this.getApiKey()!=null && this.getApiSecret()!=null)
		{
			ret=new ServiceBuilder()
					.apiKey(this.getApiKey())
					.apiSecret(this.getApiSecret())
					.build(TwitterApi.instance());
		}
		return ret;
	}
}
