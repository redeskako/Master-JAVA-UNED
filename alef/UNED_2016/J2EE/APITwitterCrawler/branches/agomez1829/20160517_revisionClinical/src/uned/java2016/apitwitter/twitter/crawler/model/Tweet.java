package uned.java2016.apitwitter.twitter.crawler.model;

/**
 * Objeto de negocio de Twitter: Tweet. Almacena referencias a Entities y User.
 * 
 * Dos tweets son iguales si sus atributos 'id' son idénticos
 * 
 * @author Jose Barba Martinez (jbarba63)
 *
 */
public class Tweet {
   
	/**
	 * Identificador del tweet.
	 */
	protected long id=-1;
	  public void setId(long id){this.id=id;}
	  public long getId(){return this.id;}
	/**
	 * Texto del tweet  
	 */
	protected String text=null;
	  public void setText(String text){this.text=text;}
	  public String getText(){return this.text;}
	
	/**
	 * Usuario autor del tweet
	 */
	protected User user=null;
	  public void setUser(User u){this.user=u;}
	  public User getUser(){return this.user;}
	  
	/**
	 * Entities asociado a este tweet
	 */
	protected Entities entities=null;
	  public void setEntities(Entities e){this.entities=e;}
	  public Entities getEntities(){return this.entities;}
	  
	/**
	 * Constructor por defecto
	 */
	public Tweet(){
	
	}
	
    /**
     * Dos tweets son iguales si lo son sus atributos 'id'
     */
	public boolean equals(Object a){
		boolean ret=false;
		if(a instanceof Tweet)
			ret=((Tweet) a).getId()==this.getId();
		return ret;
	}
}
