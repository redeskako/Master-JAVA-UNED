package uned.java2016.apitwitter.twitter.crawler.test;

import java.util.HashSet;

import java.util.Iterator;
import java.util.Map;

import uned.java2016.apitwitter.twitter.crawler.Crawler;
import uned.java2016.apitwitter.twitter.crawler.json.TwitterJsonParser;
import uned.java2016.apitwitter.twitter.crawler.model.Hashtag;
import uned.java2016.apitwitter.twitter.crawler.model.Tweet;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterCredentials;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterRestAPIClient;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterSearchRequest;
import uned.java2016.apitwitter.twitter.crawler.*;

public class TestCrawler {

	public static void main(String[] args) {		
		// creamos el crawler
		Crawler cr=new Crawler();
		ExecutionResult result=null;
		try{
		// configuramos el crawler	
		ExecutionRequest er=new ExecutionRequest();
		er.loadConfigurationFromFile("conf/crawler-twitter.properties");
		er.setHashtagList("diabetes#disease");
		// vamos a sacar el limite inferior de la busqueda
		   TwitterSearchRequest searchRequest=new TwitterSearchRequest();
		   searchRequest.setQuery("#diabetes");
		   searchRequest.setCount(2);
		   // creamos un cliente
		   TwitterRestAPIClient client=new TwitterRestAPIClient();
		   client.configureCredentials(er.createCredentials());
		   TwitterJsonParser parser=new TwitterJsonParser();
		   Tweet[] tts=parser.marshallTweets(client.callApi(searchRequest));
		   long idMasReciente=tts[0].getId();
		   System.out.println("Id recuperado "+idMasReciente);
		 // al tweet mas actual le quitamos 100 para que nos entre algun tweet en la prueba-
		 er.setLowerPollLimitId(idMasReciente-50000);
		 cr.doConfigure(er);
		 // lanzamos el sondeo
		 result=cr.doProcess(er);
		 }catch(Exception e){
			 System.out.println(e.getMessage());
		 } finally{
			cr.doFinalize();
		}
		
		// y ahora vamos a presentar los resultados de la busqueda
		Iterator<Map.Entry<Hashtag, HashSet<Tweet>>> it2=result.getPoll().getMap().entrySet().iterator();
		Tweet[] tts=null;
	    while(it2.hasNext())
	    {
	    	Map.Entry<Hashtag,HashSet<Tweet>> entry=it2.next();
	    	Hashtag t=entry.getKey();	    	
	    	System.out.println(t.getText());
	    	if(t.getNeighbourgOf()!=null)
	    	{
	    		System.out.println("|-----(vecino de)---> "+t.getNeighbourgOf().getText());
	    	}
	    	tts=result.getPoll().getTweets(t);
	    	if(tts!=null && tts.length>0)
	    	{
	    		for(int i=0;i<tts.length;i++)
	    		  System.out.println("   |---"+tts[i].getId()+":"+tts[i].getText().replace('\n', ' ').replace('\r',' '));
	    	}
	    }	    
	}

}
