package uned.java2016.apitwitter.dao;

public class Url {
	/** Atributos */
		/** Constantes */
		/** Variables */
    protected String url;
    protected String expandedUrl=null;
      
     /** Constructor por defecto, inicializa un objeto de tipo hashtag totalmente vac�o
  	 * @ in 
  	 * @ out no procede
  	 * @ error   
  	 */
    public Url(){	
    	this.url=null;
    }
    /** Constructor que carga cada URL con su valor
  	 * @ in 
  	 * 		String url con una URL
  	 * @ out no procede
  	 * @ error   
  	 */ 
    public Url(String url){
    	this.url=url;
    	this.expandedUrl=null;
    }
	/** M�todos de acceso I/O a los campos de la clase */
	/** El m�todo getUrl lee el campo url
	 * @ in 
	 * @ out String con url
	 * @ error   
	 */ 
    public String getUrl(){
    	return this.url;
    }
    /** El m�todo setUrl escribe el campo url
	 * @ in String a con la url
	 * @ out void
	 * @ error   
	 */
    public void setUrl(String a){
    	this.url=a;
    }
     
    public void setExpandedUrl(String a){this.expandedUrl=a;}
    public String getExpandedUrl(){return this.expandedUrl;}
}
