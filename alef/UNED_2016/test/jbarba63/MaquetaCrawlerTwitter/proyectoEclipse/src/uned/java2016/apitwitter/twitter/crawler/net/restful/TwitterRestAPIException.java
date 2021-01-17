package uned.java2016.apitwitter.twitter.crawler.net.restful;

public class TwitterRestAPIException extends Exception {
   
	public TwitterRestAPIException(Exception e){
		super(e);
	}
	
	public TwitterRestAPIException(String e){
		super(e);
	}
	
	public TwitterRestAPIException(String s,Exception e){
		super(s,e);
	}
}
