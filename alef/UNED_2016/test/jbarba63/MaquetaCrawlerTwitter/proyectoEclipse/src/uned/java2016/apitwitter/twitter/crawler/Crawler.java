package uned.java2016.apitwitter.twitter.crawler;

import java.util.*;

import uned.java2016.apitwitter.twitter.crawler.json.TwitterJsonParser;
import uned.java2016.apitwitter.twitter.crawler.model.Hashtag;
import uned.java2016.apitwitter.twitter.crawler.model.Tweet;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TweetSearchResult;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterCredentials;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterRestAPIClient;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterRestAPIException;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterSearchRequest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Implementa el crawler para sacar datos de twitter referentes a Hashtags
 * @author default
 *
 */
public class Crawler {

	private static final Logger logger = LogManager.getLogger(Crawler.class);
	
	  
	protected TwitterRestAPIClient apiClient=null;
	  public void setApiClient(TwitterRestAPIClient c){this.apiClient=c;}
	  public TwitterRestAPIClient getApiClient(){return this.apiClient;}
	  
	protected String hashtagList=null;
	  public void setHashtagList(String a){this.hashtagList=a;}
	  public String getHashtagList(){return this.hashtagList;}
	  
	protected Poll poll=null;
	  public Poll getPoll(){return this.poll;}
	  public void setPoll(Poll a){this.poll=a;}
	  
	protected TwitterSearchRequest searchRequest=null;
	  public void setSearchRequest(TwitterSearchRequest a){this.searchRequest=a;}
	  public TwitterSearchRequest getSearchRequest(){return this.searchRequest;}
	 	  	 
	public Crawler(){}
	
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
	
	// liberamos los recursos
	public void doFinalize(){
		this.setApiClient(null);
		this.setPoll(null);
		this.setSearchRequest(null);		
	}
	
	// procesamos el hashtag  en la cadena 'hashtag'
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
			   // no hay teets, asi q hay q sacarlos
			   // ...
			   this.getSearchRequest().setQuery("#"+h.getText());
			   try{
			   this.getAllTweets(poll, this.getSearchRequest(), h , ttParser);
			   }catch(Exception e){
				   this.logger.error("Problemas llamando a la API:"+e.getMessage(),e);				   				   
				   continue;
			   }
			   h.setProcessed(true);			   
			   try{Thread.sleep(3000);}catch(Exception e){System.out.println("Problemas durmiendo");}			   
			   // ...
			   // volvemos a genera el iterador al haber tocado el mapa
			   it=poll.getHashtagIterator();
		   }
		   			   				   		   		
		}		
	}
	
	// recuperamos todos los tweets correspondiente al hashtag 'hashtag', usando la paginacion definida en el 'request'.
	// se usa un limite superior por id de tweet igual al fijado en el poll. En el caso de paginacion será la propia
	// api quien fije el limite superior.
	// tambien se establece un limite inferior por id de tweet.
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
		 jsonResponse=this.getApiClient().callApi(request);
		 tts=parser.marshallTweets(jsonResponse);
		 // fijaremos el valor maximo para todo el sondeo
		 // en la primera consulta que devuelva valor
		 if(poll.getMaxId()<0 && tts!=null && tts.length>0)
			 poll.setMaxId(tts[0].getId());
		 poll.addTweet(hashtag,tts);
		 result=parser.marshallSearchResult(jsonResponse);
		 request.setCompleteQueryString(result.getNextResults());
		}while(result.getNextResults()!=null);		
	}
}
