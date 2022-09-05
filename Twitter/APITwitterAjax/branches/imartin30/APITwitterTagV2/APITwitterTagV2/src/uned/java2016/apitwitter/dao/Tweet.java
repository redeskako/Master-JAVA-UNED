/** Tweet es la clase que encapsula los tweets
 * @ autor equipo UNED 2016
 * @ version 1.0
 * @ fecha 2016/05/16
 * @ licencia
 */
package uned.java2016.apitwitter.dao;

public class Tweet {
	/** Atributos */
		/** Constantes */
		/** Variables */
	protected long tweet_id;  
	protected String text;  
	protected User user; 
	protected Entities entities;
	protected Url miUrl;
	
	/** Constructor por defecto, inicializa un objeto de tipo tweet totalmente vac�o
	 * @ in 
	 * @ out no procede
	 * @ error   
	 */  
	public Tweet(){
		this.tweet_id=-1;
		this.text=null;
		this.user=null;
		this.entities=null;
		this.miUrl=null;
	}
	/** Constructor que carga cada tweet con los valores de la BBDD
	 * @ in 
	 * 		String tweet_id con el identificador de cada tweet
	 * 		String text con el texto del tweet
	 * 		String userName con el nombre del usuario
	 * @ out no procede
	 * @ error   
	 */  
	public Tweet(long tweet_id,String text,String userName,String url){
		this.tweet_id=tweet_id;
		this.text=text;
		this.user=new User(userName);
		this.miUrl=new Url(url);
		this.entities=null; // Yo no uso este objeto en mis lecturas
	}
	/** M�todos de acceso I/O a los campos de la clase */
	/** El m�todo getTweet_id lee el campo hashtag_id
	 * @ in 
	 * @ out String con hashtag_id
	 * @ error   
	 */
	public long getId(){
		return this.tweet_id;
	}
	/** El m�todo getText lee el campo text
	 * @ in 
	 * @ out String con hashtag_id
	 * @ error   
	 */
	public String getText(){
		return this.text;
	}
	/** El m�todo getText lee el objeto user
	 * @ in 
	 * @ out String con hashtag_id
	 * @ error   
	 */
	public User getUser(){
		return this.user;
	}
	/** El m�todo getEntities lee el objeto entities
	 * @ in 
	 * @ out String con hashtag_id
	 * @ error   
	 */
	public Entities getEntities(){
		return this.entities;
	}
	/** El m�todo getUrl lee el objeto url
	 * @ in 
	 * @ out String con url
	 * @ error   
	 */
	public Url getUrl(){
		return this.miUrl;
	}
	/** El m�todo setTweet_id escribe el campo hashtag_id
	 * @ in long tweet_id
	 * @ out void
	 * @ error   
	 */
	public void setId(long tweet_id){
		this.tweet_id=tweet_id;
	}
	/** El m�todo setText escribe el campo text
	 * @ in String text
	 * @ out void
	 * @ error   
	 */
	public void setText(String text){
		this.text=text;
	}
	/** El m�todo setText escribe el objeto user
	 * @ in User u
	 * @ out void
	 * @ error   
	 */
	public void setUser(User u){
		this.user=u;
	}
	/** El m�todo setText escribe el entities 
	 * @ in Entities e
	 * @ out void
	 * @ error   
	 */
	public void setEntities(Entities e){
		this.entities=e;
	}
	/** El m�todo setText escribe el entities 
	 * @ in Entities e
	 * @ out void
	 * @ error   
	 */
	public void setUrl(Url url){
		this.miUrl=url;
	}
	
	// dos tweets son iguales si tienen el mismo id
	public boolean equals(Object a){
		boolean ret=false;
		if(a instanceof Tweet)
			ret=((Tweet) a).getId()==this.getId();
		return ret;
	}
}
