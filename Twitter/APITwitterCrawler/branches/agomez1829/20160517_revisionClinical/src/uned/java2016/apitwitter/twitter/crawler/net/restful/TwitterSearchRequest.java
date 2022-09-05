package uned.java2016.apitwitter.twitter.crawler.net.restful;

import java.net.URLEncoder;

import uned.java2016.apitwitter.twitter.crawler.ITwitterRestURLBuilder;

/**
 * Esta clase genera la URL con la que preguntar a Twitter, teniendo en cuenta los criterios de búsqueda.
 * si la propiodad 'completeQueryString' tiene valor, SOLO se incluye a ésta el valor de 'sinceId' (fijando asi el limite inferior de busquda)
 * Si es nula, se incluyen el resto de valores.
 * 
 * Poll utiliza este funcionamiento para poder utilizar la paginación que la API de Twitter devuelve en el objeto json
 * 'search_result'
 * 
 * @author Jose Barba Martinez (jbarba63)
 *
 */
public class TwitterSearchRequest implements ITwitterRestURLBuilder {

	/**
	 * URL de acceso a la API RestFULL usado en esta clase.
	 */
	static final String SEARCH_API_URL="https://api.twitter.com/1.1/search/tweets.json";
	
	/**
	 * Query de busqueda
	 */
	protected String query=null;
	  public void setQuery(String query){this.query=query;}
	  public String getQuery(){return this.query;}
	
	/**
	 * Paginacion de los resultados
	 */
	protected long count=-1;
	  public void setCount(long count){this.count=count;}
	  public long getCount(){return this.count;}
	/**
	 * Limite inferior de busqueda en este criterio  
	 */
	protected long sinceId=-1;
	  public void setSinceId(long sinceId){this.sinceId=sinceId;}
	  public long getSinceId(){return this.sinceId;}
	/**
	 * Limite superior de busqueda en este criterio  
	 */
	protected long maxId=-1;
	  public void setMaxId(long maxId){this.maxId=maxId;}
	  public long getMaxId(){return this.maxId;}
	/**
	 * Cadena completa de busqueda. Este valor lo fija la API de twitter en su objeto 'search_result' de respuesta en el json.
	 */
	protected String completeQueryString=null;
	  public void setCompleteQueryString(String a){this.completeQueryString=a;}
	  public String getCompleteQueryString(){return this.completeQueryString;}
	
    /**
     * Construimos la URL para preguntar a la API de Twitter.
     * Si 'completeQuerystring' tiene valor, sólo añadimos 'buildSinceId' si tiene valor.
     * En caso contrario se construye la cadena de búsqueda con los criterios que si tengan valor.
     */
	@Override
	public String buildUrl() {
	  String ret=null;
	  String temp=null;
	  if(this.getCompleteQueryString()!=null)
	  {
		  ret=this.SEARCH_API_URL+this.getCompleteQueryString();
	  }
	  else
	  {
		  ret=this.SEARCH_API_URL+this.buildQuery();
		  if((temp=this.buildCount())!=null)
			  ret+=temp;  
		  if((temp=this.buildMaxId())!=null)
			  ret+=temp;	  
	  }	  
	  if((temp=this.buildSinceId())!=null)
		  ret+=temp;	
	  return ret;
	}

	/**
	 * Consturimos la cadena de busqueda escapando los caracters NO permitidos en la URL
	 * @return Cadena de busqueda
	 */
	protected String buildQuery(){
		String ret=this.getQuery();
		try{
		ret="?q="+URLEncoder.encode(query,"UTF-8");			
		}catch(Exception e){throw new RuntimeException("Codificacion NO soportada",e);}
		return ret;
	}
	
	/**
	 * Incluimos el criterio de busqueda 'count'
	 * @return Inclusion del criterio 'count'
	 */
	protected String buildCount(){
		String ret=null;
		if(this.getCount()>0)
			ret="&count="+Long.toString(this.getCount());
	    return ret;
	}
	
	/**
	 * Incluimos el criterio de busqueda 'sinceId'
	 * @return Cadena de busqudeda para el criterio 'sinceId'
	 */
	protected String buildSinceId(){
		String ret=null;
		if(this.getSinceId()>0)
			ret="&since_id="+Long.toString(this.getSinceId());
	    return ret;		
	}
	
	/**
	 * Incluimos el cirterio 'maxId' en la cadena de busqueda
	 * @return Cadena q incluye el criterio 'maxId'
	 */
	protected String buildMaxId(){
		String ret=null;
		if(this.getMaxId()>0)
			ret="&max_id="+Long.toString(this.getMaxId());
	    return ret;		
	}	

}
