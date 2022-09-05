package uned.java2016.apitwitter.clinical.crawler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uned.java2016.apitwitter.clinical.crawler.model.ClinicalStudy;
import uned.java2016.apitwitter.clinical.crawler.model.Hashtag;
import uned.java2016.apitwitter.clinical.crawler.net.ClinicalAPIClient;
import uned.java2016.apitwitter.clinical.crawler.net.ClinicalSearchRequest;

public class Launcher {

	/**
	 * Instancia del crawler a lanzar
	 */
	protected Crawler crawler = null;

	public void setCrawler(Crawler a) {
		this.crawler = a;
	}

	public Crawler getCrawler() {
		return this.crawler;
	}

	/**
	 * Lista de hashtag sobre los q recuperar los estudios
	 */
	protected String hashtagList = null;

	public void setHashtagList(String l) {
		this.hashtagList = l;
	}

	public String getHashtagList() {
		return this.hashtagList;
	}

	/**
	 * Referencia est�tica al logger q se usar� en esta ejecuci�n
	 */
	private static final Logger logger = LogManager.getLogger(Launcher.class);

	/**
	 * Constructor
	 * 
	 * @param crawler
	 *            Instancia del crawler a lanzar
	 */
	public Launcher(Crawler crawler) {
		this.setCrawler(crawler);
	}

	// conectar con la base de datos, recuperar fichero de configuracion, etc
	/**
	 * Obtenemos lo necesario para lanzar el crawler, concretamente - conectamos
	 * con la base de datos. - recuperamos la lista de hashtag's para las
	 * b�squedas
	 * 
	 * @throws Exception
	 *             Problemas durante la recopilacion de datos
	 */
	protected void prepare() throws Exception {

		this.setHashtagList("typhus#carbuncle#ebola");
		ExecutionRequest er = new ExecutionRequest();
		ClinicalSearchRequest searchRequest = new ClinicalSearchRequest();
		searchRequest.setQuery("typhus");
		ClinicalAPIClient client = new ClinicalAPIClient();
		client.callApi(searchRequest.buildUrl());
		XMLParser parser = new XMLParser();
		ClinicalStudy[] studies = parser.marshalClinicaStudies(client.getListaXML());
	}

	/**
	 * Se liberan recursos
	 * 
	 * @throws Exception
	 */
	protected void end() throws Exception {
	}

	protected void launch() throws Exception {
		// creamos el crawler
		Crawler cr = this.getCrawler();
		ExecutionResult result = null;
		try {
			// configuramos el crawler
			ExecutionRequest er = new ExecutionRequest();

			er.setHashtagList(this.getHashtagList());

			cr.doConfigure(er);
			// lanzamos el sondeo
			result = cr.doProcess(er);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			cr.doFinalize();
		}
		// persistimos a base de datos lo obtenido
		this.toDataBase(result.getPoll());
	}

	/**
	 * Guardamos en base de datos los resultados del sondeo, especificados en la
	 * instancia de Poll
	 * 
	 * @param poll
	 *            Sondeo resultado de la ejecuci�n del crawler
	 * @throws Exception
	 *             Problemas encontrados durante el guardado en base de datos
	 */
	protected void toDataBase(Poll poll) throws Exception {
		// Se muestran los datos extraidos
		Iterator<Map.Entry<Hashtag, HashSet<ClinicalStudy>>> itResulados = poll.getMap().entrySet().iterator();
		ClinicalStudy[] cs = null;
		while (itResulados.hasNext()) {
			Map.Entry<Hashtag, HashSet<ClinicalStudy>> entry = itResulados.next();
			Hashtag t = entry.getKey();
			System.out.println(t.getText());

			cs = poll.getClinicalStudies(t);
			if (cs != null && cs.length > 0) {
				for (int i = 0; i < cs.length; i++)
					System.out.println("   |---" + cs[i].getNctId());
			}
		}
	}

	/**
	 * Implementamos el ciclo de vida de este lanzador.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger log = Launcher.logger;
		log.info("Lanzando crawler");
		Launcher launcher = new Launcher(new Crawler());
		try {
			// configuramos
			log.info("Configurando....");
			launcher.prepare();
			// lanzamos
			log.info("Lanzando........");
			launcher.launch();
			log.info("Proceso finalizado sin errores.....");
		} catch (Exception e) {
			log.error("Problemas lanzando el crawler-clinical");
			log.error(e);
		} finally {
			try {
				launcher.end();
			} catch (Exception e) {
				log.error("Problemas liberando recursos");
				log.error(e);
			}
		}

	}

}
