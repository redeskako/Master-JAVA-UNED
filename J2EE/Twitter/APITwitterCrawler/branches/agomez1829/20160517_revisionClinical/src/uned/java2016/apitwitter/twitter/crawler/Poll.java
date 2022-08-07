package uned.java2016.apitwitter.twitter.crawler;

import java.util.*;

import uned.java2016.apitwitter.twitter.crawler.model.Hashtag;
import uned.java2016.apitwitter.twitter.crawler.model.Tweet;
import org.apache.logging.log4j.Logger;


/**
 * Representa un sondeo a realizar contra twitter, almacenando los resultados y realizando un cierto control sobre qué se añade
 * como resultado al sondeo.
 * 
 * Al sondeo:
 *    - se le puede indicar el id maximo de tweet recuperado en este sondeo.
 *    - se le pueden añadir hasthags. El poll tendra en cuenta si el hashtag ya existe, y si existe no lo añade
 *    - se le pueden añadir tweets y asociarlos a un hashtag. No prmitirá duplicados de tweets asociados al mismo hashtag.
 *    - mantendrá el orden de adicion de hasthags: Si el orden es H1,H2 y H3, el iterador recueprará en orden H1,H2 y H3
 *    - Al añadir tweets a un hashtag comprobará si hay vecinos, y si los hay Y SON NUEVOS los añadirá al sondeo.
 *    - Al encontra un vecino, se controla q SOLO los vecions de primer nivel (esto es, hasthag's encontrados en tweets recuperaods
 *    como consecuencia de buscar los hasthags indicados por el usuario -hashtag de busqueda). No se permiten vecinos de vecinos.
 * Utiliza una instancia de LinkedHashMap para guardar tweets y hashtags donde:
 *    - la clave sera una instancia de Hashtag. El orden de guardado se mantiene al ser un LinkedHashMap
 *    - el valor sera una instancia de HashSet con los Tweets asociados al hasthag de la clave. El uso de HashSet permite eliminar
 *      tweets duplicados (aunque NO evita q el mismo tweet este asociado a dos hashtags distintos).
 * Al sondeo se le puede pedir un iterador para que devuelva los hashtag's en el mismo orden en el que fueron incluidos en el
 * mapa. El crawler utiliza esta propiedad para procesar los vecinos de un hasthag
 * 
 * @author Jose Barba Martinez (jbarba63)
 *
 */

public class Poll {

	/**
	 * Logger para informacion,debug, etc
	 */
	protected Logger logger=null;
	  public void setLogger(Logger logger){this.logger=logger;}
	  public Logger getLogger(){return this.logger;}
	
	/**
	 * id maximo de tweet considerado en este sondeo. Establece un limite superior para la ventana de busqueda
	 */
	protected long maxId=-1;
	  public void setMaxId(long id){this.maxId=id;}
	  public long getMaxId(){return this.maxId;}
	  
	/**
	 * Coleccion de soporte para guardar hashtags y tweets
	 */
	protected LinkedHashMap<Hashtag,HashSet<Tweet>> map=null;
	  public LinkedHashMap<Hashtag,HashSet<Tweet>> getMap(){return this.map;}
	  
	public Poll(){
		this.map=new LinkedHashMap<Hashtag,HashSet<Tweet>>();
	}
	
	/**
	 * Aniadimos el hasthag si no existe, y si no existe incluimos como valor una instancia de HashSet
	 * @param hash Hasthag a incluir
	 */
	public void addHashtag(Hashtag hash){
		if(!this.getMap().containsKey(hash))
		{
			this.getMap().put(hash, new HashSet<Tweet>());
		}
	}
	
	/**
	 * Devuelve el conjunto de tweets encontrados en la busqueda por el hasthag 'hashtag'
	 * @param hash Hasthag de busqueda
	 * @return Conjunto de tweets encontrados con el hasthag 'hasthag'
	 */
	public HashSet<Tweet> getTweetSet(Hashtag hash){
		HashSet<Tweet> ret=null;
		ret=this.getMap().get(hash);
		return ret;
	}
	
	/**
	 * Añade el tweet 'tweet' encontrado al buscar por el hashtag 'hashtag'. En el caso de que el hashtag sea de los de búsqueda
	 * (de los que NO son vecinos de otro - propiedad HashTag.neighbourOf() es null) se busca en el tweet a los posibles vecinos.
	 * para cada uno encontrado, si no está en el mapa lo añadimos y fijamos q es vecino de 'hasthag'.
	 * Asi controlamos ciclos y no permitimos el registro de vecinos de nivel 2 (vecinos de vecinos). Solo se permiten se incluyen
	 * como vecinos los hasthag's encontrados en tweets asociados a hasthag's de búsqueda (los indicados por el usuario)
	 * @param hash  Hasthag al que asociar el tweet
	 * @param tweet Tweet que queremos guardar asociado al hasthag 'hashtag'
	 */
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
	
	/**
	 * Añadimos un array de tweets al hasthag 'hasthag', haciendo llamadas tweet a tweet al metodo 'addTweet'
	 * @param hash Hashtag la q asociar los tweets
	 * @param tts  Tweets a añadir
	 */	
	public void addTweet(Hashtag hash,Tweet[] tts){
	  if(tts!=null && tts.length>0)
	  {
		this.getLogger().info("Aniadiendo "+tts.length+" tweets...");		
	    for(int i=0;i<tts.length;i++)
	    	this.addTweet(hash, tts[i]);
	  }
	}
	
	
	/**
	 * Se deuvleve un array con los tweets encontrados durante la búsqueda del hasthag 'hasthag'.
	 * @param hash Hasthag del que queremos los tweets
	 * @return Tweets encontrados en el sondeo asociados al hasthag 'hashtag'.
	 */
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
	
	/**
	 * Iterador sobre los hashtag's encontrados(vecinos)/fijados(indicados por el usuario) en este sondeo. Se devuelven en orden
	 * estricto de insercion en esta instancia.
	 * @return Iterador con los hashtag de este sondeo.
	 */
	public Iterator<Hashtag> getHashtagIterator(){
		return this.getMap().keySet().iterator();
	}
}
