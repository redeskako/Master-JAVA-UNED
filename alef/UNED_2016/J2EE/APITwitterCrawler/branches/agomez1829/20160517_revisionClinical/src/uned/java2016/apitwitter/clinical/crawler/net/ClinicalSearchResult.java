package uned.java2016.apitwitter.clinical.crawler.net;

/**
 * @author Alberto Gomez Gonzalez
 * 	Clase que gestiona los resultados de la busqueda
 *
 */
public class ClinicalSearchResult {

		protected String nextResults=null;
		  public void setNextResults(String a){this.nextResults=a;}
		  public String getNextResults(){return this.nextResults;}
		  
		public ClinicalSearchResult(){}
	}
