package uned.java2016.apitwitter.twitter.crawler;

import java.util.*;
import uned.java2016.apitwitter.twitter.crawler.net.restful.*;
import java.io.*;

/**
 * Representa una solicitud de ejecución para el crawler, agrupando la información necesaria para su correcta ejecución.
 * Incluye soporte para la lectura de un fichero de los parámetros de lanzamiento entre los q se incluyen
 *   - credenciales para acceso a twitter
 *   - anchura de la ventana de búsqueda contara la api (numero de tweets a recuperar por llamada a la API)
 *   
 * @author Jose Barba Martinez (jbarba63)
 *
 */
public class ExecutionRequest {
    protected final static String CREDENTIALS_APIKEY="credentials.apikey";
    protected final static String CREDENTIALS_APISECRET="credentials.apisecret";
    protected final static String CREDENTIALS_TOKENKEY="credentials.tokenkey";
    protected final static String CREDENTIALS_TOKENSECRET="credentials.tokensecret";
    protected final static String POLL_TWEETSPERCALL="poll.tweetsPerCall";
    
    /**
     * tabla de hash con los datos de configuracion
     */
	protected Hashtable<String, String> configuration=null;
	  public void setConfiguration(Hashtable h){this.configuration=h;}
	  public Hashtable<String,String> getConfiguration(){return this.configuration;}
	
	/**
	 * limite inferior para todas las busquedas a realizar usando este ExecutionRequest. Este limite inferior
	 * es el q se usara posteriormente en los sondeos
	 */
	protected long lowerPollLimitId=-1;
	  public void setLowerPollLimitId(long a){this.lowerPollLimitId=a;}
	  public long getLowerPollLimitId(){return this.lowerPollLimitId;}
	/**
	 * Numero de tweets q se recuperara como maximo en cada llamada a la API.
	 */
	protected int tweetsPerCall=-1;
	  public void setTweetsPerCall(int a){this.tweetsPerCall=a;}
	  public int getTweetsPerCall(){return this.tweetsPerCall;}
	
	/**
	 * Lista de hashtag's sobre los q buscar en twitter
	 */
	protected String hashtagList=null;
	  public void setHashtagList(String l){this.hashtagList=l;}
	  public String getHashtagList(){return this.hashtagList;}
	
	/**
	 * Crea una instancia de 'TwitterCredentials' partiendo de los datos presentes en el mapa 'configuration'
	 * @return Instancia de TwitterCredentials valido para acceso a twitter.
	 * @throws CrawlerException En caso de q falten datos de las credenciales
	 */
	public TwitterCredentials createCredentials()
		throws CrawlerException{
		    String apikey=this.getConfiguration().get(CREDENTIALS_APIKEY);
		    String apisecret=this.getConfiguration().get(CREDENTIALS_APISECRET);
		    String tokenkey=this.getConfiguration().get(CREDENTIALS_TOKENKEY);
		    String tokensecret=this.getConfiguration().get(CREDENTIALS_TOKENSECRET);
		    if(apikey ==null || apisecret==null || tokenkey==null || tokensecret==null)
		    	throw new CrawlerException("Faltan credenciales!!");
			return new TwitterCredentials(apikey,apisecret,tokenkey,tokensecret);
		}
	
    
	/**
	 * Metodo de carga del fichero de configuración contra el mapa 'configuration'
	 * @param fileName Ruta hasta el fichero de configuracion
	 * @throws CrawlerException Problemas durante la lectura del fichero
	 */
    public void loadConfigurationFromFile(String fileName)
        throws CrawlerException {
    	Properties ret=null;
    	File f=null;
    	FileInputStream fi=null;
    	try{
    	f=new File(fileName);	
    	fi=new FileInputStream(f);
    	ret=new Properties();
    	ret.load(fi);
    	this.setTweetsPerCall(Integer.parseInt(ret.getProperty(POLL_TWEETSPERCALL)));
    	}catch(Exception e){
    		throw new CrawlerException("Problemas leyendo la configuracion del crawler:"+e.getMessage(),e);
    	}finally{
    		try{fi.close();}catch(Exception ee){}finally{fi=null;}
    	}
    	this.setConfiguration(ret);
    	
    }
}
