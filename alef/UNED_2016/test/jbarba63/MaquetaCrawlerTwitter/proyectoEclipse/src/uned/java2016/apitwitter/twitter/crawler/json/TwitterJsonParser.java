package uned.java2016.apitwitter.twitter.crawler.json;

import javax.json.*;
import javax.json.stream.*;

import uned.java2016.apitwitter.twitter.crawler.model.Entities;
import uned.java2016.apitwitter.twitter.crawler.model.Hashtag;
import uned.java2016.apitwitter.twitter.crawler.model.Tweet;
import uned.java2016.apitwitter.twitter.crawler.model.Url;
import uned.java2016.apitwitter.twitter.crawler.model.User;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TweetSearchResult;

import java.io.StringReader;
import java.util.*;
/**
 * Clase utilidad para parsear tweets recuperados vía Json
 * @author default
 *
 */
public class TwitterJsonParser {
   
	public TwitterJsonParser(){}
	
	public TweetSearchResult marshallSearchResult(String json){
		TweetSearchResult ret=null;
		JsonParser parser=Json.createParser(new StringReader(json));
		// start_object
		parser.next();			
		int k=0;		
		while(k>=0)
		{
			JsonParser.Event event = parser.next();
			switch(event)
			{
			  case START_OBJECT:
				  k++;
				  break;
			  case END_OBJECT:
				  k--;
				  break;
			  case KEY_NAME:
				  if(k==0)
				  {
					 String key=parser.getString();					 
					 switch(key)
					 {
					 case "search_metadata":
						 parser.next();
						 ret=this.parseTweetSearchResult(parser);
						 break;
					 }
				  }			 
				  break;
			}
		}		
		return ret;
	}
	
	protected TweetSearchResult parseTweetSearchResult(JsonParser parser){						
		TweetSearchResult ret=new TweetSearchResult();		
		int k=0;		
		while(k>=0)
		{
			JsonParser.Event event = parser.next();
			switch(event)
			{
			  case START_OBJECT:
				  k++;
				  break;
			  case END_OBJECT:
				  k--;
				  break;
			  case KEY_NAME:
				  if(k==0)
				  {
					 String key=parser.getString();					 
					 switch(key)
					 {
					 case "next_results":
						 parser.next();
						 ret.setNextResults(parser.getString());
						 break;
					 }
				  }			 
				  break;
			}
		}
		return ret;					
	}
	
	
	public Tweet[] marshallTweets(String json){
		Tweet[] ret=null;
		JsonParser parser=Json.createParser(new StringReader(json));
		ArrayList<Tweet> list=null;
		while(parser.hasNext())
		{
			JsonParser.Event event = parser.next();
			switch(event){
			  case START_OBJECT:
				  break;
			  case KEY_NAME:
				  if(parser.getString().equals("statuses"))
				   {
					   // start array
					  parser.next();
					  list=this.parseTweetArray(parser);
				   }
				  break;
			}
		}		
		int size=-1;
		if(list!=null && (size=list.size())>0)
		{
			ret=new Tweet[size];
			for(int i=0;i<size;i++)
				ret[i]=list.get(i);
		}
		return ret;
	}
	
	protected ArrayList<Tweet> parseTweetArray(JsonParser parser){		
		ArrayList<Tweet> ret=null;
		JsonParser.Event event=parser.next();
		if(!event.equals(javax.json.stream.JsonParser.Event.END_ARRAY))
		{
			ret=new ArrayList<Tweet>();
			while(!event.equals(javax.json.stream.JsonParser.Event.END_ARRAY))
			{										
				switch(event)
				{
				  case START_OBJECT:
					  ret.add(this.parseTweet(parser));
					  break;
				}
				event = parser.next();
			}			
		}		   	    	
		return ret;
	}
	
	protected Tweet parseTweet(JsonParser parser){
		Tweet ret=new Tweet();		
		int k=0;		
		while(k>=0)
		{
			JsonParser.Event event = parser.next();
			switch(event)
			{
			  case START_OBJECT:
				  k++;
				  break;
			  case END_OBJECT:
				  k--;
				  break;
			  case KEY_NAME:
				  if(k==0)
				  {
					 String key=parser.getString();					 
					 switch(key)
					 {
					 case "text":
						 parser.next();
						 ret.setText(parser.getString());
						 break;
					 case "id":
						 parser.next();
						  ret.setId(parser.getLong());
						 break;
					 case "user":
						 parser.next();
						 ret.setUser(this.parseUser(parser));
						 break;
					 case "entities":
						 parser.next();
						 ret.setEntities(this.parseEntities(parser));
						 break;
					 }
				  }			 
				  break;
			}
		}
		return ret;
	}
	
	protected Entities parseEntities(JsonParser parser){
		Entities ret=new Entities();		
		int k=0;		
		while(k>=0)
		{
			JsonParser.Event event = parser.next();
			switch(event)
			{
			  case START_OBJECT:
				  k++;
				  break;
			  case END_OBJECT:
				  k--;
				  break;
			  case KEY_NAME:
				  if(k==0)
				  {
					 String key=parser.getString();					 
					 switch(key)
					 {
					 case "hashtags":
						 parser.next();
						 ret.setHashtags(this.parseHashtags(parser));
						 break;		
					 case "urls":
						 parser.next();
						 ret.setUrls(this.parseUrls(parser));
						 break;						 
					 }
				  }			 
				  break;
			}
		}
		return ret;				
	}
	
	protected Url[] parseUrls(JsonParser parser){
		Url[] ret=null;
		ArrayList<Url> list=null;
		JsonParser.Event event=parser.next();
		if(!event.equals(javax.json.stream.JsonParser.Event.END_ARRAY))
		{
			list=new ArrayList<Url>();
			while(!event.equals(javax.json.stream.JsonParser.Event.END_ARRAY))
			{										
				switch(event)
				{
				  case START_OBJECT:
					  list.add(this.parseUrl(parser));
					  break;
				}
				event = parser.next();
			}			
		}
		int size=-1;
		if(list!=null && (size=list.size())>0)
		{
			ret=new Url[size];
			for(int i=0;i<size;i++)
				ret[i]=list.get(i);
		}
		return ret;				
	}
	
	protected Url parseUrl(JsonParser parser){
		Url ret=new Url();
		int k=0;		
		while(k>=0)
		{
			JsonParser.Event event = parser.next();
			switch(event)
			{
			  case START_OBJECT:
				  k++;
				  break;
			  case END_OBJECT:
				  k--;
				  break;
			  case KEY_NAME:
				  if(k==0)
				  {
					 String key=parser.getString();					 
					 switch(key)
					 {
					 case "url":
						 parser.next();
						 ret.setUrl(parser.getString());
						 break;
					 case "expanded_url":
						 parser.next();
						 ret.setExpandedUrl(parser.getString());
						 break;								 
					 }
				  }			 
				  break;
			}
		}
		return ret;
	}	
	
	protected Hashtag[] parseHashtags(JsonParser parser){
		Hashtag[] ret=null;
		ArrayList<Hashtag> list=null;
		JsonParser.Event event=parser.next();
		if(!event.equals(javax.json.stream.JsonParser.Event.END_ARRAY))
		{
			list=new ArrayList<Hashtag>();
			while(!event.equals(javax.json.stream.JsonParser.Event.END_ARRAY))
			{										
				switch(event)
				{
				  case START_OBJECT:
					  list.add(this.parseHashtag(parser));
					  break;
				}
				event = parser.next();
			}			
		}
		int size=-1;
		if(list!=null && (size=list.size())>0)
		{
			ret=new Hashtag[size];
			for(int i=0;i<size;i++)
				ret[i]=list.get(i);
		}
		return ret;		
	}
	
	protected Hashtag parseHashtag(JsonParser parser){
		Hashtag ret=new Hashtag();
		int k=0;		
		while(k>=0)
		{
			JsonParser.Event event = parser.next();
			switch(event)
			{
			  case START_OBJECT:
				  k++;
				  break;
			  case END_OBJECT:
				  k--;
				  break;
			  case KEY_NAME:
				  if(k==0)
				  {
					 String key=parser.getString();					 
					 switch(key)
					 {
					 case "text":
						 parser.next();
						 ret.setText(parser.getString());
						 break;				
					 }
				  }			 
				  break;
			}
		}
		return ret;
	}
	
	protected User parseUser(JsonParser parser){
		User ret=new User();
		int k=0;		
		while(k>=0)
		{
			JsonParser.Event event = parser.next();
			switch(event)
			{
			  case START_OBJECT:
				  k++;
				  break;
			  case END_OBJECT:
				  k--;
				  break;
			  case KEY_NAME:
				  if(k==0)
				  {
					 String key=parser.getString();					 
					 switch(key)
					 {
					 case "profile_image_url_https":
						 parser.next();
						 ret.setProfileImageUrl(parser.getString());
						 break;
					 case "screen_name":
						 parser.next();
						 ret.setScreenName(parser.getString());
						 break;
					 case "name":
						 parser.next();
						 ret.setName(parser.getString());
						 break;
					 case "id":
						 parser.next();
						  ret.setId(parser.getLong());
						 break;
					 }
				  }			 
				  break;
			}
		}
		return ret;
	}
}
