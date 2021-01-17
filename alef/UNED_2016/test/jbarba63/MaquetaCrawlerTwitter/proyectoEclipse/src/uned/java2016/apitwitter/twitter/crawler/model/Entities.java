package uned.java2016.apitwitter.twitter.crawler.model;

public class Entities {
	
	protected Hashtag[] hashtags=null;
	  public void setHashtags(Hashtag[] h){this.hashtags=h;}
	  public Hashtag[] getHashtags(){return this.hashtags;}
	  
	protected Url[] urls=null;
	  public void setUrls(Url[] u){this.urls=u;}
	  public Url[] getUrls(){return this.urls;}
	  
	  
	public Entities(){}
}
