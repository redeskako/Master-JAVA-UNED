package uned.java2016.apitwitter.twitter.crawler;

/**
 * Representa los problemas encontrados durante una ejecución del Crawler
 * @author Jose Barba Martinez
 *
 */
public class CrawlerException extends Exception {
    /**
     * Constructor por defecot
     */
	public CrawlerException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor indicando mensaje de error
	 * @param message Error encontrado
	 */
	public CrawlerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor incluyendo una instancia de Throwable con la causa
	 * @param cause Causa
	 */
	public CrawlerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor indicando mensaje personalizado y excepción causante.
	 * @param message
	 * @param cause
	 */
	public CrawlerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CrawlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
