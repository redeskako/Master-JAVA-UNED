package uned.java2016.apitwitter.twitter.crawler.net.restful;

import com.github.scribejava.core.*;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.*;

import uned.java2016.apitwitter.twitter.crawler.ITwitterRestURLBuilder;

/**
 * Implementa el acceso a la API Rest de Twitter, permitiendo consumir los WS expuestos en esa API.
 * Utilizamos la libreria de autorizacion 'scribejava'
 * 
 * @author Jose Barba Martinez (jbarba63)
 *
 */

public class TwitterRestAPIClient {

	/**
	 * Token de acceso.
	 */
	protected OAuth1AccessToken accessToken=null;
	  public void setAccessToken(OAuth1AccessToken token){this.accessToken=token;}
	  public OAuth1AccessToken getAccessToken(){return this.accessToken;}
	
	/**
	 * Instancia de servicio contra la API
	 */
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
			 {
				TwitterRestAPIException ex=new TwitterRestAPIException("Problemas con la conexion.\n Llamando a:"+URL
						   + "\nResponseCode:"+response.getCode()
						   + "\n"+(response==null ? "respuestaNula" : response.toString())
						   + "\n"+(ret == null ? "cuerpoNulo" : ret)
						);
				// fijamos el codigo de respuesta en la excepcion
				ex.setHttpResponseCode(response.getCode());
				throw ex;
			 }
			
			// DEBUG: provocamos el error
			    /*
				java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyyMMddHHmm");
				java.util.Date flag=null;
				try{
				flag=sdf.parse("201606142337");
				}catch(Exception e){}
				if(flag.before(new java.util.Date()))
				{
					TwitterRestAPIException ex=new TwitterRestAPIException("ERROR PROVOCADO.\n Llamando a:"+URL
							   + "\nResponseCode:"+response.getCode()
							   + "\n"+(response==null ? "respuestaNula" : response.toString())
							   + "\n"+(ret == null ? "cuerpoNulo" : ret)
							);
					ex.setHttpResponseCode(429);
					throw ex;
				}
				*/
					
			}
			
		}
		return ret;
	}
	
	/**
	 * sobrecarga del método para incluir URL's generadas mediante la interfaz ITwitterRestURLBuilder
	 * @param url Builder para la URL
	 * @return Resultado de llamar a la API
	 * @throws TwitterRestAPIException consultar metodo sobrecargado 'callApi'
	 */
	public String callApi(ITwitterRestURLBuilder url)
	   throws TwitterRestAPIException{
		return this.callApi(url.buildUrl());
	}
	
	/**
	 * Crea una solicitud de acceso a la API, en forma de OAuthRequest de la libreria scribejava
	 * @param token Token de acceso a utilizar
	 * @param URL Url a la que acceder con el request.
	 * @param service Servicio al que acceder con la URL
	 * @return Instancia de solicitud OAuthRequest firmada segun el token facilitado.
	 */
	protected OAuthRequest createRequest(OAuth1AccessToken token,String URL,OAuth10aService service){
		OAuthRequest ret=null;
		// creamos el request
		ret=new OAuthRequest(Verb.GET,URL, service);
		// e intentamos autorizarlo
		service.signRequest(token,ret);
		return ret;
	}
	
}
