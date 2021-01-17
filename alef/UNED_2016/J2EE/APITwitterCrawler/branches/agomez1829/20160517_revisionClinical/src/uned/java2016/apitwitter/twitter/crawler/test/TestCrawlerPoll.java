package uned.java2016.apitwitter.twitter.crawler.test;

import  java.util.*;

import uned.java2016.apitwitter.twitter.crawler.Poll;
import uned.java2016.apitwitter.twitter.crawler.json.TwitterJsonParser;
import uned.java2016.apitwitter.twitter.crawler.model.Hashtag;
import uned.java2016.apitwitter.twitter.crawler.model.Tweet;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterCredentials;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterRestAPIClient;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterSearchRequest;


public class TestCrawlerPoll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// creamos credenciales
		TwitterCredentials c=new TwitterCredentials(
				"SZUPw862Fly3HqGRh8aootkWs", // apiKey
				"ifbFch7TL04vTDJIsZ78TJUdKX5O1PeYKBQwJVtUf8H5jkeALy", //apiSecret
				"222586918-93mKQ4kbhhYfrRLHbjhb5U3X0hpWRt2PjNgKnQSl", //tokenKey
				"WKhHhcA6dk8IzGfifDD70ev7PS2V3GFDSKzlD9VkgUC2g" //tokenSecret
		);
		// creamos el cliente
		
		TwitterRestAPIClient client=new TwitterRestAPIClient();
		try{client.configureCredentials(c);}catch(Exception e){System.out.println("Error configurando cliente");}
		String jsonResponse=null;
		TwitterSearchRequest searchRequest=new TwitterSearchRequest();
		searchRequest.setCount(2);
		
		
		Poll poll=new Poll();
		Hashtag h=new Hashtag("Diabetes");
		// añadimos el hash al sondeo
		poll.addHashtag(h);
		 
		 
		 
		// obtenemos el iterador sobre las claves
		//Iterator<Map.Entry<Hashtag, HashSet<Tweet>>> it=poll.getEntryIterator();		
		Iterator<Hashtag> it=poll.getHashtagIterator();
		
		
		Tweet[] tts=null;
		TwitterJsonParser ttParser=new TwitterJsonParser();		
		while(it.hasNext())
		{
		   h=it.next();
		   // solo procesamos si no lo hemos hecho ya
		   if(!h.isProcessed())
		   {
			   // no hay teets, asi q hay q sacarlos
			   // ...
			   searchRequest.setQuery("#"+h.getText());
			   try{
			   jsonResponse=client.callApi(searchRequest);
			   }catch(Exception e){
				   System.out.println("Problemas llamando a la API:"+e.getMessage());
				   continue;
			   }
			   System.out.println(searchRequest.buildUrl());
			   tts=ttParser.marshallTweets(jsonResponse);
			   poll.addTweet(h, tts);			   
			   h.setProcessed(true);			   
			   try{Thread.sleep(3000);}catch(Exception e){System.out.println("Problemas durmiendo");}			   
			   // ...
			   // volvemos a genera el iterador al haber tocado el mapa
			   it=poll.getHashtagIterator();
		   }
		   			   				   		   		
		}
		
		Iterator<Map.Entry<Hashtag, HashSet<Tweet>>> it2=poll.getMap().entrySet().iterator();
	    while(it2.hasNext())
	    {
	    	Map.Entry<Hashtag,HashSet<Tweet>> entry=it2.next();
	    	Hashtag t=entry.getKey();	    	
	    	System.out.println(t.getText());
	    	if(t.getNeighbourgOf()!=null)
	    	{
	    		System.out.println("|-----(vecino de)---> "+t.getNeighbourgOf().getText());
	    	}
	    }
	}

}
