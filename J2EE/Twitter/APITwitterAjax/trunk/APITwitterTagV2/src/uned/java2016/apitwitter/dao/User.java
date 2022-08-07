package uned.java2016.apitwitter.dao;

public class User {
	protected long id=-1;
	  public void setId(long l){this.id=l;}
	  public long getId(){return this.id;}
	  
	protected String name=null;
	  public void setName(String a){this.name=a;}
	  public String getName(){return this.name;}
	protected String screenName=null;
	  public void setScreenName(String a){this.screenName=a;}
	  public String getScreenName(){return this.screenName;}
	  
	protected String profileImageUrl=null;
	  public void setProfileImageUrl(String url){this.profileImageUrl=url;}
	  public String getProfuleImageUrl(){return this.profileImageUrl;}
	  
	  
	protected int friendsCount=-1;
	  public void setFriendsCount(int a){this.friendsCount=a;}
	  public int getFriendsCount(){return this.friendsCount;}
	  
	protected long followersCount=-1;
	  public void setFollowersCount(long s){this.followersCount=s;}
	  public long getFollowersCount(){return this.followersCount;}
	  
	public User(){}
	/** Constructor que carga cada usuario con los valores de la BBDD
	 * @ in 
	 * 		String name con el nombre del usuario
	 * @ out no procede
	 * @ error   
	 */ 
	public User(String name){
		this.id=-1;
		this.name=name;
		this.screenName=null;
		this.profileImageUrl=null;
		this.friendsCount=-1;
		this.followersCount=-1;
	}
}
