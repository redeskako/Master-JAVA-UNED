package uned.java2016.apitwitter.twitter.crawler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uned.java2016.apitwitter.twitter.crawler.json.TwitterJsonParser;
import uned.java2016.apitwitter.twitter.crawler.model.Hashtag;
import uned.java2016.apitwitter.twitter.crawler.model.Tweet;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterRestAPIClient;
import uned.java2016.apitwitter.twitter.crawler.net.restful.TwitterSearchRequest;

/**
 * Lanzador para el Crawler. Provee de información y recursos necesarios al Crawler para su correcta ejecución, y responsable
 * de lanzar y controlar su ciclo de vida. La responsabilidad del Crawler es obtener datos de Twitter, la responsabilidad del
 * Launcher es:
 *   - recoger la lista de hashtag's a recuperar
 *   - obtener el maximo id de tweet recuperado en el sondeo anterior (y fijar asi el límite inferior de las búsquedas)
 *   - Configurar y lanzar el crawler con la info anterior (facilitando a éste un logger)
 *   - Liberar los recursos del crawler
 *   - recoger los resultados de la ejecución del crawler e incluirlos en la base de datos.
 * 
 * Esta clase será la principal a la hora de ejecutar el crawler de twitter
 *   
 * @author Jose Barba Martinez (jbarba63)
 *
 */
public class Launcher {
    
	/**
	 * Instancia del crawler a lanzar
	 */
	protected Crawler crawler=null;
	  public void setCrawler(Crawler a){this.crawler=a;}
	  public Crawler getCrawler(){return this.crawler;}
	
	/**
	 * Maximo Id encontrado en el sondeo anterior y q será el límite inferior del siguiente lanzamiento  
	 */
	protected long lastMaxId=-1;
	  public void setLastMaxId(long m){this.lastMaxId=m;}
	  public long getLastMaxId(){return this.lastMaxId;}
	
	/**
	 * Litsa de hashtag sobre los q recuperar tweets y vecinos
	 */
	protected String hashtagList=null;
	  public void setHashtagList(String l){this.hashtagList=l;}
	  public String getHashtagList(){return this.hashtagList;}
	
	/**
	 * Referencia estática al logger q se usará en esta ejecución  
	 */
	private static final Logger logger = LogManager.getLogger(Launcher.class);  
	  
	/**
	 * Constructor
	 * @param crawler Instancia del crawler a lanzar
	 */
	public Launcher(Crawler crawler) {
		this.setCrawler(crawler);
	}
	
	// conectar con la base de datos, recuperar fichero de configuracion, etc
	/**
	 * Obtenemos lo necesario para lanzar el crawler, concretamente
	 *    - conectamos con la base de datos.
	 *    - recuperamos la lista de hashtag's para las búsquedas
	 *    - recuperamos el maximo id encontrado en la última ejecución del crawler
	 * @throws Exception Problemas durante la recopilacion de datos
	 */
	protected void prepare()
	  throws Exception {
		   this.setHashtagList("#disease");
		   // vamos a sacar el limite inferior de la busqueda
		   ExecutionRequest er=new ExecutionRequest();
		   er.loadConfigurationFromFile("conf/crawler-twitter.properties");
		   TwitterSearchRequest searchRequest=new TwitterSearchRequest();
		   searchRequest.setQuery(this.getHashtagList());
		   searchRequest.setCount(2);
		   // creamos un cliente
		   TwitterRestAPIClient client=new TwitterRestAPIClient();
		   client.configureCredentials(er.createCredentials());
		   TwitterJsonParser parser=new TwitterJsonParser();
		   Tweet[] tts=parser.marshallTweets(client.callApi(searchRequest));
		   long idMasReciente=tts[0].getId();
		   System.out.println("Id recuperado "+idMasReciente);
		   this.setLastMaxId(idMasReciente-50000l);
	}
	
	/**
	 * Se liberan recursos
	 * @throws Exception
	 */
	protected void end()
	  throws Exception {}
	
	/**
	 * Lanzador para el Crawler, ejecutando su ciclo de vida:
	 *    - creamos una instancia de ExecutionRequest, q cargamos del fichero de configuracion
	 *       - fijamos la ventana inferior de búsqueda
	 *       - fijamos la lista de hasthag's para buscar
	 *    - configuramos el proceso con la instancia anterior
	 *    - lanzamos el proceso recuperando la instancia de ExecutionResult
	 *    - Liberamos los recursos
	 *    - y finalmente, guardamos los resultados (encapsulados en una isntancia de Poll) contra la base de datos.
	 * @throws Exception Problemas durante la ejecución del crawler
	 */
	protected void launch()	
	  throws Exception {		
		 // creamos el crawler
		 Crawler cr=this.getCrawler();
		 ExecutionResult result=null;
		 try{
		 // configuramos el crawler	
		 ExecutionRequest er=new ExecutionRequest();
		 er.loadConfigurationFromFile("conf/crawler-twitter.properties");
		 er.setHashtagList(this.getHashtagList());

		 // al tweet mas actual le quitamos 100 para que nos entre algun tweet en la prueba-
		 er.setLowerPollLimitId(this.getLastMaxId());
		 cr.doConfigure(er);
		 // lanzamos el sondeo
		 result=cr.doProcess(er);
		 }catch(Exception e){
			 System.out.println(e.getMessage());
		 } finally{
			cr.doFinalize();
		}		
		// persistimos a base de datos lo obtenido
		this.toDataBase(result.getPoll());
	}
	
	/**
	 * Guardamos en base de datos los resultados del sondeo, especificados en la instancia de Poll
	 * @param poll Sondeo resultado de la ejecución del crawler
	 * @throws Exception Problemas encontrados durante el guardado en base de datos
	 */
	protected void toDataBase(Poll poll)
	  throws Exception{
		// y ahora vamos a presentar los resultados de la busqueda
		Iterator<Map.Entry<Hashtag, HashSet<Tweet>>> it2=poll.getMap().entrySet().iterator();
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
	    	tts=poll.getTweets(t);
	    	if(tts!=null && tts.length>0)
	    	{
	    		for(int i=0;i<tts.length;i++)
	    		  System.out.println("   |---"+tts[i].getId()+":"+tts[i].getText().replace('\n', ' ').replace('\r',' '));
	    	}
	    }			
	}
	
	/**
	 * Implementamos el ciclo de vida de este lanzador.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Logger log=Launcher.logger;
        log.info("Lanzando crawler");
        Launcher launcher=new Launcher(new Crawler(log));         
        try{
        // configuramos
        log.info("Configurando....");        	
        launcher.prepare();
        // lanzamos
        log.info("Lanzando........");
        launcher.launch();
        log.info("Proceso finalizado sin errores.....");
        }catch(Exception e){
          log.error("Problemas lanzando el crawler-twitter");
          log.error(e);
        }finally{
        	try{launcher.end();}catch(Exception e){log.error("Problemas liberando recursos");log.error(e);}
        }
        
        
	}

}
