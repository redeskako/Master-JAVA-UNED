package uned.java2016.apitwitter.clinical.crawler.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import uned.java2016.apitwitter.clinical.crawler.Crawler;
import uned.java2016.apitwitter.clinical.crawler.ExecutionRequest;
import uned.java2016.apitwitter.clinical.crawler.ExecutionResult;
import uned.java2016.apitwitter.clinical.crawler.XMLParser;
//import uned.java2016.apitwitter.clinical.crawler.model.ClinicalStudy;
//import uned.java2016.apitwitter.clinical.crawler.model.Hashtag;
import uned.java2016.apitwitter.dao.*;
import uned.java2016.apitwitter.clinical.crawler.net.ClinicalAPIClient;
import uned.java2016.apitwitter.clinical.crawler.net.ClinicalSearchRequest;

public class Test {
	public static void main(String[] args) {
		Crawler cr = new Crawler();
		ExecutionResult result = null;
		ClinicalStudy[] studies;


		try {
			// configuramos el crawler y asignamos cadena de hashtags
			ExecutionRequest er = new ExecutionRequest();
			er.setHashtagList("typhus#carbuncle#ebola");

			//Realizamos prueba de Hashtag individual
			ClinicalSearchRequest searchRequest = new ClinicalSearchRequest();
			searchRequest.setQuery("typhus");
			ClinicalAPIClient client = new ClinicalAPIClient();
			client.callApi(searchRequest.buildUrl());
			XMLParser parser = new XMLParser();
			studies= parser.marshalClinicaStudies(client.getListaXML());
	    	System.out.println("PRUEBA INDIVIDUAL: RESULTADOS----------------------");			
	    	if(studies!=null && studies.length>0)
	    	{
	    		for(int i=0;i<studies.length;i++)
	    		  System.out.println("   |---"+studies[i].getNctId());
	    	}
	    	System.out.println("PRUEBA INDIVIDUAL: FIN------------------------------");
			
			//Se realiza prueba de sondeo
			cr.doConfigure(er);
			result=cr.doProcess(er);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			cr.doFinalize();
		}
		
		// Se muestran los datos extraidos
		Iterator<Map.Entry<Hashtag, HashSet<ClinicalStudy>>> itResulados=result.getPoll().getMap().entrySet().iterator();
		ClinicalStudy[] cs=null;
	    while(itResulados.hasNext())
	    {
	    	Map.Entry<Hashtag,HashSet<ClinicalStudy>> entry=itResulados.next();
	    	Hashtag t=entry.getKey();	    	
	    	System.out.println(t.getText());

	    	cs=result.getPoll().getClinicalStudies(t);
	    	if(cs!=null && cs.length>0)
	    	{
	    		for(int i=0;i<cs.length;i++)
	    		  System.out.println("   |---"+cs[i].getNctId());
	    	}
	    }
	}

}
