package uned.java2016.apitwitter.clinical.crawler;

import java.io.File;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;

//import uned.java2016.apitwitter.clinical.crawler.model.ClinicalStudy;
//import uned.java2016.apitwitter.clinical.crawler.model.Hashtag;
import uned.java2016.apitwitter.dao.*;
import uned.java2016.apitwitter.clinical.crawler.net.ClinicalAPIClient;
import uned.java2016.apitwitter.clinical.crawler.net.ClinicalAPIException;
import uned.java2016.apitwitter.clinical.crawler.net.ClinicalSearchRequest;
import uned.java2016.apitwitter.clinical.crawler.util.Config;

import org.apache.logging.log4j.LogManager;

public class Crawler {
	private static final Logger logger = LogManager.getLogger(Crawler.class);
	protected static final Config config = Config.getInstance();

	String directorio_unzip = config.getProperty("directory_unzip", "unzip");
	protected String server = config.getProperty("result_file", "search_result.zip");

	protected ClinicalAPIClient apiClient = null;
	protected String hashtagList = null;
	protected Poll poll = null;
	protected ClinicalSearchRequest searchRequest = null;
	
	public void setApiClient(ClinicalAPIClient c) 	{		this.apiClient = c;		}
	public ClinicalAPIClient getApiClient() 		{		return this.apiClient;	}
	
	public void setHashtagList(String a) 			{		this.hashtagList = a;	}
	public String getHashtagList() 					{		return this.hashtagList;}

	public Poll getPoll() 							{		return this.poll;		}
	public void setPoll(Poll a) 					{		this.poll = a;			}

	public void setSearchRequest(ClinicalSearchRequest a) {		this.searchRequest = a;	}
	public ClinicalSearchRequest getSearchRequest() {		return this.searchRequest;	}

	public Crawler() {	}

	/**
	 * Metodo de configuracion del crawler
	 * @param request Peticion de ejecucion del crawler
	 * @throws ClinicalAPIException  Gestion de excepcion asociadas al crawler
	 */
	public void doConfigure(ExecutionRequest request) throws CrawlerException {
		try {
			this.setSearchRequest(new ClinicalSearchRequest());
			this.setHashtagList(request.getHashtagList());
		} catch (Exception e) {
			throw new CrawlerException("Problemas en doConfigure:" + e.getMessage(), e);
		}
	}

	/**
	 * Ejecucion del crawler, este metodo se el encargado de procesar cada uno de los hashtag e ir formando el objeto 
	 * LinkedHashMap, para ello ira "cortando" la cadena de Hashtag y procesandolos uno a uno 
	 * @param init Solicitud de ejecucion
	 * @return Resultado de la ejecucion
	 * @throws ClinicalAPIException  Gestion de excepcion asociadas al crawler
	 */
	public ExecutionResult doProcess(ExecutionRequest init) throws CrawlerException {
		ExecutionResult ret = new ExecutionResult(init);

		Poll poll = new Poll();
		poll.setLogger(Crawler.logger);
		this.setPoll(poll);

		StringTokenizer st = new StringTokenizer(this.getHashtagList(), "#");
		Crawler.logger.debug("***************************************************************************");
		Crawler.logger.debug("Lanzada ejecucion Crawler-Clinical");

		while (st.hasMoreTokens()) {
			this.processHashtag(poll, st.nextToken());
		}
		ret.setPoll(this.getPoll());
		return ret;
	}

	/**
	 * Liberacion de recursos 
	 */
	public void doFinalize() {
		this.setApiClient(null);
		this.setPoll(null);
		this.setSearchRequest(null);

	}


	/**
	 * Este metodo se encargara de procesar el hashtag enviado como parametro, desde aqui se construira 
	 * la llamada a la API, y a partir de los datos obtenidos se iran procsando los conjuntos de estudios
	 * 
	 * @param poll Sondeo sobre el cual se procesara el Hashtag
	 * @param hashtag Hashtag a procesar
	 */
	protected void processHashtag(Poll poll, String hashtag) {
		Hashtag h = new Hashtag(hashtag);
		poll.addHashtag(h);

		Iterator<Hashtag> it = poll.getHashtagIterator();
		XMLParser xmlParser = new XMLParser();
		while (it.hasNext()) {
			h = it.next();
			if (!h.isProcessed()) {
				this.getSearchRequest().setQuery(h.getText());
				try {
					this.getAllStudies(poll, this.getSearchRequest(), h, xmlParser);
				} catch (Exception e) {
					Crawler.logger.error("Problemas llamando a la API:" + e.getMessage(), e);
					continue;
				}
				h.setProcessed(true);
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					System.out.println("Problemas durmiendo");
				}
				it = poll.getHashtagIterator();

			}
		}
		File zip = new File(server);
		zip.delete();
	}

	/**
	 * Este metodo es el encargado de incluir en el sondeo todos estudios relacionados con 
	 * un Hashtag.
	 * 
	 * @param poll Sondeo sobre el que se va a trabajar
	 * @param request  Peticion de busqueda
	 * @param hashtag Hashtag a procesar
	 * @param parser	Objeto de parseo
	 * @throws ClinicalAPIException  Gestion de excepcion asociadas al crawler
	 */
	protected void getAllStudies(Poll poll, ClinicalSearchRequest request, Hashtag hashtag, XMLParser parser)
			throws ClinicalAPIException {

		ClinicalStudy[] cs = null;

		Crawler.logger.debug("Preguntando a la API:" + request.buildUrl());

		ClinicalAPIClient client = new ClinicalAPIClient();
		client.callApi(request.buildUrl());

		cs = parser.marshalClinicaStudies(client.getListaXML());
		poll.addClinicalStudy(hashtag, cs);

		File folder = new File(directorio_unzip);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				listOfFiles[i].delete();
			}
		}

	}

}
