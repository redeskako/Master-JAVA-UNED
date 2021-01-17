package uned.java2016.apitwitter.clinical.crawler.net;

import java.net.URLEncoder;

import uned.java2016.apitwitter.clinical.crawler.util.Config;

/**
 * @author Alberto Gomez Gonzalez
 *	Esta clase se encarga de construir la cadena de conexion contra la API
 */
public class ClinicalSearchRequest {
	protected static final Config config = Config.getInstance();
	static final String SEARCH_API_URL=config.getProperty("API_URL");
	
	protected String query=null;
	protected String completeQueryString=null;
	
	public void setQuery(String query){this.query=query;}
	public String getQuery(){return this.query;}

	public void setCompleteQueryString(String a){this.completeQueryString=a;}
	public String getCompleteQueryString(){return this.completeQueryString;}
	  
	/**
	 * Metodo que genera la cadena de conexion
	 * @return Cadena de conexion completamente construida
	 */
	public String buildUrl() {
		String ret=null;
		if(this.getCompleteQueryString()!=null)
		{	ret= ClinicalSearchRequest.SEARCH_API_URL+this.getCompleteQueryString();	}
		else			
		{	ret= ClinicalSearchRequest.SEARCH_API_URL+this.buildQuery();		  		}	  
		return ret;
	}
		
	/**
	 * Metodo que genera la consulta
	 * @return Subcadena que genera la pregunta a la API
	 */
	protected String buildQuery(){
		String ret=this.getQuery();
		try{
			ret=URLEncoder.encode(query,"UTF-8") +"&resultsxml=true";			
		}catch(Exception e){throw new RuntimeException("Codificacion NO soportada",e);}
		return ret;
	}
	  
}