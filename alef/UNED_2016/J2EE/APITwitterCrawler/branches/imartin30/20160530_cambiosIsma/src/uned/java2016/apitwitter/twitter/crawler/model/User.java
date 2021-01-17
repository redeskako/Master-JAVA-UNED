package uned.java2016.apitwitter.twitter.crawler.model;

/**
 * Objeto de negocio Twitter: User
 * 
 * Representa a un usuario que ha escrito un tweet.
 * 
 * @author Jose Barba Martinez (jbarba63)
 *
 */

public class User {
	/**
	 * id del usuario en twitter
	 */
	protected long id=-1;
	  public void setId(long l){this.id=l;}
	  public long getId(){return this.id;}
	
	/**
	 * Nombre del usuario
	 */
	protected String name=null;
	  public void setName(String a){this.name=a;}
	  public String getName(){return this.name;}
	  
	/**
	 * Nombre en twitter para el usuario
	 */
	protected String screenName=null;
	  public void setScreenName(String a){this.screenName=a;}
	  public String getScreenName(){return this.screenName;}
	  
	/**
	 * Url a la Imagen de perfil
	 */
	protected String profileImageUrl=null;
	  public void setProfileImageUrl(String url){this.profileImageUrl=url;}
	  public String getProfuleImageUrl(){return this.profileImageUrl;}
	  
	/**
	 * Numero de amigos del usuario  
	 */
	protected int friendsCount=-1;
	  public void setFriendsCount(int a){this.friendsCount=a;}
	  public int getFriendsCount(){return this.friendsCount;}
	  
    /**
     * Numero de seguidores del usuario	  
     */
	protected long followersCount=-1;
	  public void setFollowersCount(long s){this.followersCount=s;}
	  public long getFollowersCount(){return this.followersCount;}
	
	/**
	 * Constructor por defecto.
	 */
	public User(){}
}
