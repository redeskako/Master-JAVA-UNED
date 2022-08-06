package uned.java2016.apitwitter.services.rs.client;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.logging.LoggingFeature;

import uned.java2016.apitwitter.dao.*;
import uned.java2016.apitwitter.services.rs.client.entity.TweetMessageBodyReader;
import uned.java2016.apitwitter.services.rs.client.entity.TweetsMessageBodyReader;
import uned.java2016.apitwitter.services.rs.client.filters.AuthorizationFilter;
import uned.java2016.apitwitter.services.rs.jaxb.Tweets;


/**
 * Implementa el cliente que accede mediante Jersey (JAX-RS) contra el WS expuesto en el proyecto APITwitterTagV2.
 * Este WS parametriza los argumentos en la ruta, de manera que:
 *   <nombreOperacion>/<parametro1>/<parametro2>/.../<parametroN>
 * Para recuperacion de tweets por id:
 *   getById/<identificador de tweet>
 * Para recuperacion de tweets con un hashtag concreto:
 *   getBuHashtag/<texto del hashtag>/<numero de tweets a recuperar>
 *   
 * La clase hace uso de dos Entity Providers creados para poder de-serializar instancias de Tweet y de List<Tweet>,
 * que son TweetMessageBodyReader y TweetsMessageBodyReader.
 * 
 * Adicionamente, utiliza dos filtros, uno para logar el trafico (LogginFeature) y para autorizar la llamada al
 * WS (AuthorizationFilter).
 * @author José Barba Martínez (jbarba63)
 *
 */
public class JAXRSClient {
	
	/**
	 * Operaciones expuestas en el servicio web.
	 */
	public static final String OPERATION_GETBYID="tweets/getById";
	public static final String OPERATION_GETBYHASHTAG="tweets/getByHashtag";

	/**
	 * Instancia de cliente JAX-RS a utilizar
	 */
	protected Client rsClient = null;	  
	  protected Client getRsClient(){return this.rsClient;}
	  protected void setRsClient(Client c){this.rsClient=c;}
	  
	/**
	 * Logger para logar el trafico
	 */
	protected Logger logger=null;
	  protected Logger getLogger(){return this.logger;}
	  protected void setLogger(Logger log){this.logger=log;}
    /**
     * Handler para logar el trafico	  
     */
	protected StreamHandler handlerLogger=null;
	  protected StreamHandler getHandlerLogger(){return this.handlerLogger;}
	  protected void setHandlerLogger(StreamHandler h){this.handlerLogger=h;}
    /**
     * Buffer en el que el logger escribira el trafico	  
     */
	protected ByteArrayOutputStream buffer=null;
	  protected void setBuffer(ByteArrayOutputStream bof){this.buffer=bof;}
	  protected ByteArrayOutputStream getBuffer(){return this.buffer;}
	/**
	 * Usuario para acceso al WS
	 */
	protected String user=null;
	  public void setUser(String user){this.user=user;}
	  public String getUser(){return this.user;}
	/**
	 * Password para acceso al WS  
	 */
	protected String password=null;
	  public void setPassword(String p){this.password=p;}
	  public String getPassword(){return this.password;}
	  
	/**
	 * Crea e inicializa el cliente JAXRS para consumo del WS expuesto por APITwitter.
	 */
	public JAXRSClient(){
		this.initialize();
	}
	
	
	/**
	 * Inicializamos el cliente, creando la estructura de log necesaria y registrando los providers
	 * que nos permitan leer tweets. Incluye ademas configuracion para incluir las credenciales
	 * del usuario
	 */
	protected void initialize(){
		// inicializamos log
		Logger log=Logger.getAnonymousLogger();
		log.setLevel(Level.ALL);
		this.setLogger(log);
		// reseteamos el logger de trafico.
		this.resetTraffic();
		// inicializamos cliente 
		Client c=ClientBuilder.newBuilder()
				  // registramos filtro de autorizacion
				  .register(AuthorizationFilter.class)
				  // registramos un logger para poder volcar el trafico 
				  .register(new LoggingFeature(log,LoggingFeature.Verbosity.PAYLOAD_ANY))
				  // registramos un EntityReader especifico para las clases y asi evitar tener que anotarlas.
				  .register(TweetMessageBodyReader.class) // provider para instancias de Tweet
				  .register(TweetsMessageBodyReader.class) //provider para List<Tweet>
				  .build();
		this.setRsClient(c);		
	}
	
	/**
	 * implementa la llamada a la operacion 'getById' del servicio 'tweets'
	 * @param URI URL contra el servicio web 
	 * @param id identificador del tweet
	 * @return Instancia de tweet
	 */
	public Tweet getById(String URI,long id){
		Tweet ret=null;
		// empezamos a construir la llamada
		WebTarget t=this.getRsClient()
				  .target(URI)				  
				  .path(JAXRSClient.OPERATION_GETBYID)
				  .path(Long.toString(id));
		// realizamos la llamada y ercuperamos el tweet. Al indicar la clase 'Tweet', la infraestructura
		// utilizara una instancia de TweetMessageBodyReader para realizar la de-serializacion
		ret=(Tweet) this.invoke(t,Tweet.class);
		return ret;
	}
	
	
	/**
	 * Realiza la llamada al ws apuntado por 'target' e intenta parsear la respuesta como una instancia
	 * de la clase 'clase'. El metodo fija las credenciales para la llamada al WS-.
	 * @param target Puntero al WS
	 * @param clase Clase a recuperar en la llamada
	 * @return Instancia de la clase recuperada al parsear la respuesta.
	 */
	protected Object invoke(WebTarget target,Class clase){
		Object ret=null;		
		try{			
		ret=target.request(MediaType.APPLICATION_XML)
				   //pasamos credenciales al filtro
				  .property("HEADER-Authorization",this.getUser()+":"+this.getPassword())
				  // hacemos la llamada
				  .get(clase);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return ret;		
	}
	
	/**
	 * Invoca la operacion 'getByHashtag' del servicio 'tweets', indicando en 'hasthag' el hashtag
	 * por el que buscar y en 'count' el numero de tweets a devolver.
	 * @param URI URL contra el servicio 
	 * @param hashtag Hasthag por el que buscar tweets
	 * @param count numero de tweets a recuperar en la respuesta
	 * @return
	 */
	public Tweet[] getByHashtag(String URI,String hashtag,int count){
		Tweet[] ret=null;
		// empezamos a construir la llamada
		WebTarget t=this.getRsClient()
				  .target(URI)				  
				  .path(JAXRSClient.OPERATION_GETBYHASHTAG)
				  .path(hashtag)
				  .path(Integer.toString(count));
		// invocamos el WS para recuperar un tipo generico de tipo List<Tweet> que es el que devuelve
		// e la operacion getByHashTag
		List<Tweet> tt=(List<Tweet>) this.invoke(t,new GenericType<List<Tweet>>(){});
		// Convertimos la lista en array.
		ret=tt.toArray(new Tweet[0]);
		return ret;
	}	
	
	/**
	 * Llama al WS apuntado por 'target' e inenta parsear la respuesta como un tipo generico 'gen'
	 * En este caso lo usamos para recuperar un array de tweets
	 * @param target Puntero a la operacion
	 * @param gen Tipo generico a la que parsear la respuesta
	 * @return
	 */
	protected Object invoke(WebTarget target,GenericType<?> gen){
		Object ret=null;		
		try{			
		ret=target.request(MediaType.APPLICATION_XML)
				   //pasamos credenciales al filtro
				  .property("HEADER-Authorization",this.getUser()+":"+this.getPassword())
				  // hacemos la llamada especificando que queremos recuperar una instancia de 'gen'
				  // esta llamada provocara que una instancia de TweetsMessageBodyReader se haga cargo
				  // de la de-serializacion de los tweets.
				  .get(gen);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return ret;		
	}	
	
	
	/**
	 * Obtiene el tráfico actual del cliente JAXRS
	 * @return
	 */
	public String getTraffic(){
		this.getHandlerLogger().flush();
		return this.getBuffer().toString();
	}
	
	/**
	 * Reseteamos el trafico que logamos del cliente JAXRS
	 */
	public void resetTraffic(){
		// borramos el logger anterior (si lo hubiere)
		StreamHandler log=null;
		if((log=this.getHandlerLogger()) !=null )
		{
		  log.close();
		  this.getLogger().removeHandler(log);
		}
		// creamos el buffer
		this.setBuffer(new ByteArrayOutputStream());
		// creamos el handler
		log=new StreamHandler(this.getBuffer(),new SimpleFormatter());
		log.setLevel(Level.ALL);
		this.setHandlerLogger(log);
		// y lo añadimos al logger
		this.getLogger().addHandler(log);		
	}
}
