package uned.java2016.apitwitter.twitter.crawler.model;

public class Tweet {
   
	protected long id=-1;
	  public void setId(long id){this.id=id;}
	  public long getId(){return this.id;}
	  
	protected String text=null;
	  public void setText(String text){this.text=text;}
	  public String getText(){return this.text;}
	  
	protected User user=null;
	  public void setUser(User u){this.user=u;}
	  public User getUser(){return this.user;}
	  
	protected Entities entities=null;
	  public void setEntities(Entities e){this.entities=e;}
	  public Entities getEntities(){return this.entities;}
	  
	public Tweet(){
	
	}
	
	// dos tweets son iguales si tienen el mismo id
	public boolean equals(Object a){
		boolean ret=false;
		if(a instanceof Tweet)
			ret=((Tweet) a).getId()==this.getId();
		return ret;
	}
}
