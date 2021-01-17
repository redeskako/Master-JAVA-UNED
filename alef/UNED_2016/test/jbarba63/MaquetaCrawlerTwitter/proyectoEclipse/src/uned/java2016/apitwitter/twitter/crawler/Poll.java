package uned.java2016.apitwitter.twitter.crawler;

import java.util.*;

import uned.java2016.apitwitter.twitter.crawler.model.Hashtag;
import uned.java2016.apitwitter.twitter.crawler.model.Tweet;
import org.apache.logging.log4j.Logger;

public class Poll {

	protected Logger logger=null;
	  public void setLogger(Logger logger){this.logger=logger;}
	  public Logger getLogger(){return this.logger;}
	
	protected long maxId=-1;
	  public void setMaxId(long id){this.maxId=id;}
	  public long getMaxId(){return this.maxId;}
	  
	protected LinkedHashMap<Hashtag,HashSet<Tweet>> map=null;
	  public LinkedHashMap<Hashtag,HashSet<Tweet>> getMap(){return this.map;}
	  
	public Poll(){
		this.map=new LinkedHashMap<Hashtag,HashSet<Tweet>>();
	}
	
	// añadimos un hashtag a la búsqueda de hash
	public void addHashtag(Hashtag hash){
		if(!this.getMap().containsKey(hash))
		{
			this.getMap().put(hash, new HashSet<Tweet>());
		}
	}
	
	//devuelve el set asociado al hashtag pasado por parametro
	public HashSet<Tweet> getTweetSet(Hashtag hash){
		HashSet<Tweet> ret=null;
		ret=this.getMap().get(hash);
		return ret;
	}
	
	// añadimos un tweet encontrado en un hashtag
	public void addTweet(Hashtag hash,Tweet tweet){
		HashSet<Tweet> set=null;
		if(!this.getMap().containsKey(hash))
		{
			set=new HashSet<Tweet>();
			this.getMap().put(hash,set);
		}
		else
			set=this.getMap().get(hash);
	    // comprobamos si el tweet tiene algún hashtag 'vecino'
		// SOLO comprobamos vecinos de nivel 1
		// y SOLO si el tweet NO está ya asociado a este hashtag.
		if(set.add(tweet) && hash.getNeighbourgOf()==null)
		{
			Hashtag[] tHashs=null;
			if(tweet.getEntities().getHashtags()!=null && (tHashs=tweet.getEntities().getHashtags()).length>1)
			{
				for(int i=0;i<tHashs.length;i++)
					// para los hashtags distintos a éste
					if(!tHashs[i].equals(hash))
					{
						// fijamos de quien es vecino el nuevo
						tHashs[i].setNeighbourOf(hash);
						// y lo incluimos para seguir buscando.						
						if(!this.getMap().containsKey(tHashs[i]))
						 {
						  this.getLogger().debug("Vecino encontrado! "+tHashs[i].getText());						  
						  this.addHashtag(tHashs[i]);
						 }
						else
						 {
						  this.getLogger().debug("Hashtag repetido!"+tHashs[i].getText());
						 }
					}
			}			
		}		
	}
	
	// aniadimos un array de tweets
	public void addTweet(Hashtag hash,Tweet[] tts){
	  if(tts!=null && tts.length>0)
	  {
		this.getLogger().info("Aniadiendo "+tts.length+" tweets...");		
	    for(int i=0;i<tts.length;i++)
	    	this.addTweet(hash, tts[i]);
	  }
	}
	
	
	// devuelve el array con todos los tweets recuperados y asociados a un hashtag concreto.
	public Tweet[] getTweets(Hashtag hash){
	  Tweet[] ret=null;
	  if(this.map.containsKey(hash))
	   {
		 HashSet set=this.map.get(hash);
		 if(set.size()>0)
		 {
			 ret=new Tweet[set.size()];
			 Iterator it=set.iterator();
			 int i=0;
			 while(it.hasNext())
			 {
			   ret[i]=(Tweet) it.next();
			   i++; 
		     }		 
		 }
	   }
	  return ret;
	}
	
	public Iterator<Hashtag> getHashtagIterator(){
		return this.map.keySet().iterator();
	}
	
	public Iterator<Map.Entry<Hashtag, HashSet<Tweet>>> getEntryIterator(){
		return this.getMap().entrySet().iterator();			   
	}
}
