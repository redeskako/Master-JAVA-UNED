package uned.java2016.apitwitter.clinical.crawler.net;

/**
 * @author Alberto Gomez Gonzalez
 * 
 * Clase encargada de gestionar las excepciones provocadas por la clase ClinicalAPICLient
 */
public class ClinicalAPIException extends Exception {

	private static final long serialVersionUID = 1L;

	public ClinicalAPIException(Exception e) {
		super(e);
	}

	public ClinicalAPIException(String e) {
		super(e);
	}

	public ClinicalAPIException(String s, Exception e) {
		super(s, e);
	}
}
