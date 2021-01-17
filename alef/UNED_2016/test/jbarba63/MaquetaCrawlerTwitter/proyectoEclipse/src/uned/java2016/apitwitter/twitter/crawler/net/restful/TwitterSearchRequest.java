package uned.java2016.apitwitter.twitter.crawler.net.restful;

import java.net.URLEncoder;

import uned.java2016.apitwitter.twitter.crawler.ITwitterRestURLBuilder;

public class TwitterSearchRequest implements ITwitterRestURLBuilder {

	static final String SEARCH_API_URL="https://api.twitter.com/1.1/search/tweets.json";
	
	protected String query=null;
	  public void setQuery(String query){this.query=query;}
	  public String getQuery(){return this.query;}
	  
	protected long count=-1;
	  public void setCount(long count){this.count=count;}
	  public long getCount(){return this.count;}
	  
	protected long sinceId=-1;
	  public void setSinceId(long sinceId){this.sinceId=sinceId;}
	  public long getSinceId(){return this.sinceId;}
	  
	protected long maxId=-1;
	  public void setMaxId(long maxId){this.maxId=maxId;}
	  public long getMaxId(){return this.maxId;}
	  
	protected String completeQueryString=null;
	  public void setCompleteQueryString(String a){this.completeQueryString=a;}
	  public String getCompleteQueryString(){return this.completeQueryString;}
	
	@Override
	public String buildUrl() {
	  String ret=null;
	  String temp=null;
	  if(this.getCompleteQueryString()!=null)
	  {
		  ret=this.SEARCH_API_URL+this.getCompleteQueryString();
	  }
	  else
	  {
		  ret=this.SEARCH_API_URL+this.buildQuery();
		  if((temp=this.buildCount())!=null)
			  ret+=temp;  
		  if((temp=this.buildMaxId())!=null)
			  ret+=temp;	  
	  }	  
	  if((temp=this.buildSinceId())!=null)
		  ret+=temp;	
	  return ret;
	}

	
	protected String buildQuery(){
		String ret=this.getQuery();
		try{
		ret="?q="+URLEncoder.encode(query,"UTF-8");			
		}catch(Exception e){throw new RuntimeException("Codificacion NO soportada",e);}
		return ret;
	}
	
	protected String buildCount(){
		String ret=null;
		if(this.getCount()>0)
			ret="&count="+Long.toString(this.getCount());
	    return ret;
	}
	
	protected String buildSinceId(){
		String ret=null;
		if(this.getSinceId()>0)
			ret="&since_id="+Long.toString(this.getSinceId());
	    return ret;		
	}
	
	protected String buildMaxId(){
		String ret=null;
		if(this.getMaxId()>0)
			ret="&max_id="+Long.toString(this.getMaxId());
	    return ret;		
	}	

}
