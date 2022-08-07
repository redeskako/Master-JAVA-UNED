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
 * En todos los casos de parseo la estrategia es la misma:
 *   - Se parte de q el cursor del parser esta posicionado al principio del objeto a parsear
 *   - Se recorre el cursor hasta llegar al final del objeto a parsear.
 *   - Para saber cuando hemos terminado se usa un entero a modo de pila:
 *       - incrementamos al descender un nivel (el objeto tiene un objeto dentro, pero NO estamos interesados en procesarlo)
 *       - decrementamos al ascender un nivel (el cursor se ha posicionado fuera del objeto de nuestro interes)
 *       
 * @author Jose Barba Martinez (jbarba63)
 *
 */
public class TwitterJsonParser {
   
	/**
	 * Constructor por defecto
	 */
	public TwitterJsonParser(){}
	
	/**
	 * Parsea los criterios de búsqueda de una consulta a la API. Util para ir paginando lso resultados de forma sencilla.
	 * Este método trabaja a nivel raiz del objeto json, con lo q crea su propia instancia del parser.
	 * @param json Cadnea json con la respuesta de la API
	 * @return Objeto TweetSearchResult con los datos de la siguiente página de búsqueda
	 */
	public TweetSearchResult marshallSearchResult(String json){
		TweetSearchResult ret=null;
		JsonParser parser=Json.createParser(new StringReader(json));
		// Nos ponemos en la marca START_OBJECT
		parser.next();			
		int k=0;
		// saldremos cuando k valga -1 (terminamos sin encontrar lo buscado)
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
					 // solo nos interesan objetos a nivel raiz del json 
					 String key=parser.getString();					 
					 switch(key)
					 {
					 // estamos en el objeto 'search_metadata' q es el que queremos
					 case "search_metadata":
						 parser.next();
						 // parseamos y construimos el objeto 'search_metadata'
						 ret=this.parseTweetSearchResult(parser);
						 break;
					 }
				  }			 
				  break;
			}
		}		
		return ret;
	}
	
	/**
	 * Parseamos y craemos un objeto 'search_metadata' partiendo del parser 'parser'. Se asume que éste está justo en el primer campo
	 * del objeto 'search_result'. Este método se llama desde 'marshallSearchResult'
	 * @param parser
	 * @return
	 */
	protected TweetSearchResult parseTweetSearchResult(JsonParser parser){						
		TweetSearchResult ret=new TweetSearchResult();		
		int k=0;
		// saldremos cuando k valga -1, es decir, cuando estemos FUERA del objeto 'search_request' en el que actualmente
		// esta posicionado el parser. 
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
	
	/**
	 * Creamos y parseamos las instancias de Tweet,User,Url,Entities, etc partiendo de la cadena 'json' con el resultado de la llamada
	 * a la API de twitter. Este metodo realmente sólo posiciona el parser correctamente al principio del array de tweets recuperados
	 * y llama a 'parseTweetArray' para el parseo del array de tweets, y convierte su resultado en un array estatico
	 * @param json JSon recuperado directamente de twitter
	 * @return Array de tweets incluidos en la cadena Json de entrada. 
	 */
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
	
	/**
	 * Con 'parser' posicionado en la marca 'START_ARRAY', se va llamando a 'parseTweet' posicionando el cursor del parser al principio
	 * de cada tweet. Cada tweet recuperado se añade a la lista enlazada q se devuelve como resultado
	 * @param parser Parser con el cursos al principio del array.
	 * @return Lista con los tweet's encontrados.
	 */
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
	
	/**
	 * Recuperamos un tweet usando el parser. Éste estará posicionado justo en la marca 'START_OBJETC'.
	 * Desde aqui se lanza el parseo de los elementos de un tweet:
	 *   - Entities
	 *   - User
	 * @param parser Parser con la cadena JSON y el cursor apuntando al principio del tweet
	 * @return Instancia de Tweet con sus datos propios más las instancias de clases asociadas (Entities y User).
	 */
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
					 case "id_str":
						 parser.next();
						  ret.setId(Long.parseLong(parser.getString()));
						 break;
					 case "user":
						 parser.next();
						 // estamos en el objeto anidado 'user', lo parseamos
						 ret.setUser(this.parseUser(parser));
						 break;
					 case "entities":
						 parser.next();
						 // estamos en el objeto anidado 'entities', lo parseamos.
						 ret.setEntities(this.parseEntities(parser));
						 break;
					 }
				  }			 
				  break;
			}
		}
		return ret;
	}
	
	/**
	 * Parseamos el objeto 'entities', asumiendo q 'parser' está en la marca START_OBJECT de dicho objeto json.
	 * Desde aqui se parsean los objetos Hasthag y Url
	 * @param parser Parser apountando al principio del objeto json 'entities'
	 * @return Inscnaid e Entities con sus referencias a Hasthag y Url creadas y con datos.
	 */
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
						 // estamos en el array 'hasthag', lo parseamos
						 ret.setHashtags(this.parseHashtags(parser));
						 break;		
					 case "urls":
						 parser.next();
						 // estamos en el array 'urls', lo parseamos
						 ret.setUrls(this.parseUrls(parser));
						 break;						 
					 }
				  }			 
				  break;
			}
		}
		return ret;				
	}
	
	/**
	 * Parseamos las 'url's encontradas en el objeto json 'entities'. Se asume el parser al principio del array.
	 * Para recuperar cada URL se llama a 'parseUrl'. Se usa una lista de forma intermedia para guardar las url's parseadsas,
	 * q finalmente se convierten a un array estático para su devolucion.
	 * @param parser Parser con el cursor al principio del array.
	 * @return Array con las URL's en este array json.
	 */
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
					  // hemos encontrado una URL, la parseamos y añadimos a la lista.
					  list.add(this.parseUrl(parser));
					  break;
				}
				event = parser.next();
			}			
		}
		// convertimos en array.
		int size=-1;
		if(list!=null && (size=list.size())>0)
		{
			ret=new Url[size];
			for(int i=0;i<size;i++)
				ret[i]=list.get(i);
		}
		return ret;				
	}
	
	/**
	 * Parseamos el objeto json 'url'. Se asume q 'parser' está en la marca 'START_OBJECT' de la cadena json.
	 * @param parser Parser con el cursor al principio
	 * @return Instancia de Url con los datos parseados del json.
	 */
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
	
	/**
	 * Devuelve un array de hashtag's del objeto 'json'. Para ello el parser 'parser' debe tener su cursor al principio del array.
	 * Usa una lista de forma temporal para guardar los Hasthag's recuperados llamando al método 'parseHashtag', para finalmente
	 * convertirlo en un array estático como retorno.
	 * @param parser Parser con el cursor apuntando al principio del array.
	 * @return Array estatico con los hasshtag's encontrados.
	 */
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
					  // parseamos y añadimos a la lista el hasthag encontrado.
					  list.add(this.parseHashtag(parser));
					  break;
				}
				event = parser.next();
			}			
		}
		// convertimos lista en array.
		int size=-1;
		if(list!=null && (size=list.size())>0)
		{
			ret=new Hashtag[size];
			for(int i=0;i<size;i++)
				ret[i]=list.get(i);
		}
		return ret;		
	}
	
	/**
	 * Parseamos el Hasthag al que apunta el cursor del parser 'parser'. Se asume que esta en la marca 'START_OBJECT'.
	 * @param parser Parser apuntando al principio del hasthag.
	 * @return Instancia de Hashtag con el texto relleno, partiendo del json.
	 */
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
	
	/**
	 * Parseamos el objeto json 'user' a cuyo principio (START_OBJECT) apunta el parser 'parser'.
	 * @param parser Parser con el cursor al principio del objeto json 'user'
	 * @return Instancia de User con sus datos rellenos.
	 */
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
