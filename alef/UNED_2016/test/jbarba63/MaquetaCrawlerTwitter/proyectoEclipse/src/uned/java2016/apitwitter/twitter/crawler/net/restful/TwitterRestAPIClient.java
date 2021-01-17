package uned.java2016.apitwitter.twitter.crawler.net.restful;

import com.github.scribejava.core.*;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.*;

import uned.java2016.apitwitter.twitter.crawler.ITwitterRestURLBuilder;

/**
 * Implementa el acceso a la API Rest de Twitter, permitiendo consumir los WS expuestos en esa API
 * @author default
 *
 */

public class TwitterRestAPIClient {

	protected OAuth1AccessToken accessToken=null;
	  public void setAccessToken(OAuth1AccessToken token){this.accessToken=token;}
	  public OAuth1AccessToken getAccessToken(){return this.accessToken;}
	
	protected OAuth10aService service=null;
	  public void setService(OAuth10aService service){this.service=service;}
	  public OAuth10aService getService(){return this.service;}
	  
	public TwitterRestAPIClient(){}
	
	/**
	 * Configura este cliente para el acceso a la API segun las credenciales presentes en 'cred'
	 * @param cred
	 */
	public void configureCredentials(TwitterCredentials cred)
			throws TwitterRestAPIException{
		this.setAccessToken(cred.createAccessToken());
		this.setService(cred.createService());
		if(this.getAccessToken()==null && this.getService()==null)
		{
			throw new TwitterRestAPIException("Las credenciales facilitadas NO permiten acceso a Twitter");
		}
	}
	
	/**
	 * Realiza la llamada a la API según la URL indicada en el parámetro URL
	 * @param URL url contra la que hacer la llamada
	 * @return Payload JSon con la respuesta a la llamada
	 * @throws TwitterRestAPIException En caso de problemas llamando se lanzará una exceptión 
	 */
	public String callApi(String URL)
	    throws TwitterRestAPIException {
		String ret=null;
		// comprobamos si hay token y servicio
		if(this.getAccessToken()!=null && this.getService()!=null)
		{
			// cremamos el request
			OAuthRequest request=this.createRequest(this.getAccessToken(), URL,this.getService());
			// e intentamos acceder al mismo
			Response response=null;
			try{
			response=request.send();
			ret=response.getBody();
			}catch(Exception e){
			 throw new TwitterRestAPIException("Problemas con la conexion:"+e.getMessage(),e);	
			}finally{
				// comprobamos si hay errores en la comunicacion
			if(response==null || ret==null || response.getCode()!=200)
				throw new TwitterRestAPIException("Problemas con la conexion. ResponseCode:"+response.getCode());
			}
			
		}
		return ret;
	}
	
	// sobrecarga del método para incluir URL's generadas mediante la interfaz ITwitterRestURLBuilder
	public String callApi(ITwitterRestURLBuilder url)
	   throws TwitterRestAPIException{
		return this.callApi(url.buildUrl());
	}
	
	// creamos el request para llamar al servicio
	protected OAuthRequest createRequest(OAuth1AccessToken token,String URL,OAuth10aService service){
		OAuthRequest ret=null;
		// creamos el request
		ret=new OAuthRequest(Verb.GET,URL, service);
		// e intentamos autorizarlo
		service.signRequest(token,ret);
		return ret;
	}
	
}
