package uned.java2016.apitwitter.twitter.crawler;

import java.util.*;

import uned.java2016.apitwitter.twitter.crawler.json.TwitterJsonParser;
//Nuevo. Redirijo import hacia paquete DAO y no Model.
//import uned.java2016.apitwitter.twitter.crawler.model.Hashtag;
//import uned.java2016.apitwitter.twitter.crawler.model.Tweet;
import uned.java2016.apitwitter.dao.*;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TweetSearchResult;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterCredentials;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterRestAPIClient;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterRestAPIException;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterSearchRequest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Implementa el crawler para sacar datos de twitter referentes a Hashtags.
 * Para ello implementa un simple ciclo de vida consistente en:
 *   - reserva de recursos y configuracion (doPrepare)
 *   - ejecución de proceso (doProcess)
 *   - liberacion de recursos (doFinalize)
 * Se utiliza una instancia de 'ExecutionRequest' para agrupar la informacion necesaria para
 * la ejecución del crawler, y una instancia de 'ExecutionResult' para agrupar la información
 * resultado de la ejecución del crawler.
 * 
 * @author Jose Barba Martinez (jbarba63)
 *
 */
public class Crawler {

	/**
	 * Instancia del logger
	 */
	protected Logger logger=null;
	  public void setLogger(Logger l){this.logger=l;}
	  public Logger getLogger(){return this.logger;}
	  
	/**
	 * Cliente para llamar a la API RestFUL
	 */
	protected TwitterRestAPIClient apiClient=null;
	  public void setApiClient(TwitterRestAPIClient c){this.apiClient=c;}
	  public TwitterRestAPIClient getApiClient(){return this.apiClient;}
	
	  /**
	   * Lista de hashtags por los q buscar, separados por el símbolo #
	   */
	protected String hashtagList=null;
	  public void setHashtagList(String a){this.hashtagList=a;}
	  public String getHashtagList(){return this.hashtagList;}
	/**
	 * Referencia al sondeo  q se realizara en esta ejecución del crawler
	 */
	protected Poll poll=null;
	  public Poll getPoll(){return this.poll;}
	  public void setPoll(Poll a){this.poll=a;}
	  
	/**
	 * Referencia al objeto q creara la busqueda contra Twitter.
	 */
	protected TwitterSearchRequest searchRequest=null;
	  public void setSearchRequest(TwitterSearchRequest a){this.searchRequest=a;}
	  public TwitterSearchRequest getSearchRequest(){return this.searchRequest;}
	
	  /**
	   * Constructor por defecto. Requiere de un logger.
	   * @param log Instancia de logger.
	   */
	public Crawler(Logger log){
		this.setLogger(log);
	}
	
	/**
	 * Realiza el acopio de recursos y la parametrización necesaria para q el crawler pueda lanzar un sondeo.
	 * En esta fase se recuperan los criterios de búsqueda y se recuperan las credenciales de acceso a twitter
	 * @param request Datos necesarios para poder lanzar el sondeo.
	 * @throws CrawlerException En el caso de que haya problemas adquiriendo recuros o por falta de información requerida (credenciales)
	 */
	public void doConfigure(ExecutionRequest request)
	  throws CrawlerException{
	  try{
	  this.setSearchRequest(new TwitterSearchRequest());
	     this.getSearchRequest().setCount(request.getTweetsPerCall());
	     this.getSearchRequest().setSinceId(request.getLowerPollLimitId());
	  this.setApiClient(new TwitterRestAPIClient());
	  this.getApiClient().configureCredentials(request.createCredentials());
	  this.setHashtagList(request.getHashtagList());
	  }catch(Exception e){
		  throw new CrawlerException("Problemas en doConfigure:"+e.getMessage(),e);
	  }
	}
	
	/**
	 * Implementa la ejcución del sondeo contra Twitter. Una vez configurado y preparado el crawler, se realiza el sondeo
	 * contra Twitter. Se utiliza la instancia de ExecutionRequest que se usó para preparar (en doConfigure) como entrada
	 * al proceso. El resultado (en este caso, resultado de un sondeo o Poll) se encapsula en una instancia de ExecutionResult
	 * @param init Datos de entrada en forma de ExecutionRequest
	 * @return Instancia de ExecutionResult con los datos del sondeo realizado. Se incluye el sondeo, y el máximo id recuperado
	 * en dicho sondeo.
	 * @throws CrawlerException En caso de problemas no esperados durante el sondeo
	 */
	public ExecutionResult doProcess(ExecutionRequest init)
	  throws CrawlerException{
	  ExecutionResult ret=new ExecutionResult(init);
	  this.logger.info("***************************************************************************");
	  this.logger.info("Lanzada ejecucion Crawler-Twitter");
	  // creamos el poll para sondear a Twitter	
	  Poll poll=new Poll();
	  poll.setLogger(this.logger);
	  this.setPoll(poll);
	  // rompemos la cadena que contiene la lista de hashtags a buscar
	  StringTokenizer st=new StringTokenizer(this.getHashtagList(),"#");
	  while(st.hasMoreTokens())
	  {
		  // procesamos este hashtag		  
		  this.processHashtag(poll,st.nextToken());
	  }
	  ret.setPoll(this.getPoll());
	  ret.setMaxId(this.getPoll().getMaxId());
	  return ret;
	}
	
    /**
     * Liberamos los recursos adquiridos.
     */
	public void doFinalize(){
		this.setApiClient(null);
		this.setPoll(null);
		this.setSearchRequest(null);		
	}
	
	/**
	 * Realiza las llamadas necesarias a la API RestFUL de Twitter para obtener todos los tweets en los q aparezca el hashtag de entrada
	 * en 'hashtag', y guarda los resultados en 'Poll'. Se recuperan todos los tweets q contengan a 'hashtag', y en el caso de q se
	 * encuentren nuevos hashtags en esos tweets, también se añaden y procesan (recuperando sus tweets).
	 * Para ello nos apoyamos en la instancia de Poll q facilita:
	 *   - no permite duplicados de hashtags.
	 *   - permite asociar un grupo de tweets con un hashtag.
	 *   - en el proceso de asociar tweets con un hasthag concreto, 'poll' buscará vecinos de primer nivel q se añadiran
	 *   automáticamente al poll como hashtags no procesado.
	 * El metodo obtiene del poll un Iterator, y terminará cuando llegue al final del iterador.
	 * El proceso de búsqueda de tweets de un hashtag se realiza llamando a 'getAllTweets', q tiene en cuenta el paginado
	 * de resultados.
	 * Entre el procesamiento de un hashtag y cada uno de los vecinos se incluye un lapso de 3 segundos.
	 * @param poll Instancia de sondeo
	 * @param hashtag Hashtag sobre el que buscar.
	 */
	protected void processHashtag(Poll poll,String hashtag){
		Hashtag h=new Hashtag(hashtag);
		// añadimos el hash al sondeo
		poll.addHashtag(h);
		// obtenemos el iterador sobre las claves	
		Iterator<Hashtag> it=poll.getHashtagIterator();				
		TwitterJsonParser ttParser=new TwitterJsonParser();
		while(it.hasNext())
		{
		   h=it.next();
		   // solo procesamos si no lo hemos hecho ya
		   if(!h.isProcessed())
		   {
			   // fijamos como criterio de busqueda el hashtag a buscar
			   this.getSearchRequest().setQuery("#"+h.getText());
			   try{
			   // recuperamos los tweets. Ojo, q en el caso de q se recuperen hashtags vecinos se toca el mapa en Poll,
			   // con lo q habra q crear nuevamente el iterador
			   this.getAllTweets(poll, this.getSearchRequest(), h , ttParser);
			   }catch(Exception e){
				   this.logger.error("Problemas llamando a la API:"+e.getMessage(),e);
			   }
			   // marcamos este hashtag como procesado
			   h.setProcessed(true);			   
			   // dormimos 3 segundos
			   try{Thread.sleep(3000);}catch(Exception e){System.out.println("Problemas durmiendo");}			   
			   // ...
			   // volvemos a genera el iterador ya q posiblemente se haya añadido algun vecino al poll
			   // poll garantiza q los hashtags se devolveran en el mismo orden de inserción de hashtags en el poll
			   it=poll.getHashtagIterator();
		   }
		   			   				   		   		
		}		
	}
	
    /**
     * Realiza las llamadas necesarias a la API RestFUL de twitter para recuperar todos los tweets asociados al hasthag
     * 'hasthag'. Para ello usa la referencia 'apiClient', realizando la paginación de resultados, de manera q una llamada
     * a este método puede incluir N llamadas a la API.
     * Si es la primera llamada a la API q se realiza en este sondeo 'poll', se fijara el maximo id del sondeo con el id del
     * primer tweet recuperado.
     * En el caso de q con una llamada no se puedan recuperar todos los tweets del hasthag a procesar, se utilizara el objeto
     * json 'search_result' para ir paginando sobre los resultados 
     * @param poll instancia de sondeo a realizar
     * @param request Criterios de busqueda contra twitter
     * @param hashtag Hasthag sobre el que buscar
     * @param parser Parser para crear los objetos Tweets y dependientes partiendo de la respuesta Json de la api.
     * @throws TwitterRestAPIException
     */
	protected void getAllTweets(Poll poll,TwitterSearchRequest request,Hashtag hashtag,TwitterJsonParser parser)
	    throws TwitterRestAPIException{	
		
		// si hay rango superior en el sondeo, lo fijamos como criterio
		if(poll.getMaxId()>0)
			request.setMaxId(poll.getMaxId());
		String jsonResponse=null;
		TweetSearchResult result=null;
		Tweet[] tts=null;
		do{
		 this.logger.debug("Preguntando a la API:"+request.buildUrl());
		 // llamamos a la API RestFUL
		 jsonResponse=this.getApiClient().callApi(request);
		 tts=parser.marshallTweets(jsonResponse);
		 // fijaremos el valor maximo para todo el sondeo
		 // en la primera consulta que devuelva valor
		 if(poll.getMaxId()<0 && tts!=null && tts.length>0)
			 poll.setMaxId(tts[0].getId());
		 // añadimos los tweets recuperados
		 poll.addTweet(hashtag,tts);
		 // adquirimos el criterio de busqueda para el siguiente grupo de tweets
		 result=parser.marshallSearchResult(jsonResponse);
		 // actualizamos el criterio de búsqueda para recuperar el siguiente grupo de tweets.
		 request.setCompleteQueryString(result.getNextResults());
		}while(result.getNextResults()!=null);		
	}
}
