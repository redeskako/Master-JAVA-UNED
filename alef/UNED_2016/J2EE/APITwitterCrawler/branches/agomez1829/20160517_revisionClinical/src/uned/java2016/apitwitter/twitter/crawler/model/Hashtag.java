package uned.java2016.apitwitter.twitter.crawler.model;

/**
 * Objeto de negocio Twitter Hasthag. Se guarda el texto del hashtag, si esta procesado (util durante el sondeo para evitar busquedas 
 * duplicadas) y si es o no vecino de otro.
 * Un hashtag sin vecinos es un hasthag de búsqueda, esto es, indicado por el usuario en la lista de hashtags a buscar.
 * 
 * Se sobreescribe el metodo 'hashCode' y 'equals' para su correcto uso con HashMap's
 * 
 * @author Jose Barba Martinez (jbarba63)
 *
 */
public class Hashtag {

	/**
	 * Texto del hashtag
	 */
	protected String text=null;
	  public void setText(String hashtag){this.text=hashtag;}
	  public String getText(){return this.text;}
	
	/**
	 * Guarda la relacion de vecinidad de éste contra otro. Si es NULL, esta instancia de Hashtag es de búsqueda.
	 */
	protected Hashtag neighbourOf=null;
	  public void setNeighbourOf(Hashtag h){this.neighbourOf=h;}
	  public Hashtag getNeighbourgOf(){return this.neighbourOf;}
	
	/**
	 * Indica si esta procesado o no. Un hashtag procesado YA ha sido buscado en twitter.
	 */
	protected boolean processed=false;
	  public boolean isProcessed(){return this.processed;}
	  public void setProcessed(boolean t){this.processed=t;}
	
	/**
	 * Constructor por defecto.
	 */
    public Hashtag(){}
    
    /**
     * Fija el texto del hashtag (sin el simbolo almohadilla)
     * @param hashtag Texto del hashtag
     */
	public Hashtag(String hashtag){
		this.setText(hashtag);
	}
	

	/**
	 * Dos hashtag's son iguales si el texto es igual, sin incluir mayusculas/minusculas.
	 */
	@Override    
	public boolean equals(Object a){
		boolean ret=false;
		if(a instanceof Hashtag)		
		  ret=((Hashtag) a).getText().equalsIgnoreCase(this.getText());		  			
		return ret;
	}
	
	/**
	 * El hashcode de un Hashtag es el HashCode de su texto pasado a mayúsculas.
	 */
	@Override
	public int hashCode(){
		return this.getText().toUpperCase().hashCode();
	}
	
}
