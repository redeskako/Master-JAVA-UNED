package uned.java2016.apitwitter.twitter.crawler;

import java.util.*;
import uned.java2016.apitwitter.twitter.crawler.net.restful.*;
import java.io.*;


public class ExecutionRequest {
    protected final static String CREDENTIALS_APIKEY="credentials.apikey";
    protected final static String CREDENTIALS_APISECRET="credentials.apisecret";
    protected final static String CREDENTIALS_TOKENKEY="credentials.tokenkey";
    protected final static String CREDENTIALS_TOKENSECRET="credentials.tokensecret";
    protected final static String POLL_TWEETSPERCALL="poll.tweetsPerCall";
    
    
	protected Hashtable<String, String> configuration=null;
	  public void setConfiguration(Hashtable h){this.configuration=h;}
	  public Hashtable<String,String> getConfiguration(){return this.configuration;}
	
	protected long lowerPollLimitId=-1;
	  public void setLowerPollLimitId(long a){this.lowerPollLimitId=a;}
	  public long getLowerPollLimitId(){return this.lowerPollLimitId;}
	protected int tweetsPerCall=-1;
	  public void setTweetsPerCall(int a){this.tweetsPerCall=a;}
	  public int getTweetsPerCall(){return this.tweetsPerCall;}
	protected String hashtagList=null;
	  public void setHashtagList(String l){this.hashtagList=l;}
	  public String getHashtagList(){return this.hashtagList;}
	  
	public TwitterCredentials createCredentials()
		throws CrawlerException{
		    String apikey=this.getConfiguration().get(CREDENTIALS_APIKEY);
		    String apisecret=this.getConfiguration().get(CREDENTIALS_APISECRET);
		    String tokenkey=this.getConfiguration().get(CREDENTIALS_TOKENKEY);
		    String tokensecret=this.getConfiguration().get(CREDENTIALS_TOKENSECRET);
		    if(apikey ==null || apisecret==null || tokenkey==null || tokensecret==null)
		    	throw new CrawlerException("Faltan credenciales!!");
			return new TwitterCredentials(apikey,apisecret,tokenkey,tokensecret);
		}
	

    public void loadConfigurationFromFile(String fileName)
        throws CrawlerException {
    	Properties ret=null;
    	File f=null;
    	FileInputStream fi=null;
    	try{
    	f=new File(fileName);	
    	fi=new FileInputStream(f);
    	ret=new Properties();
    	ret.load(fi);
    	this.setTweetsPerCall(Integer.parseInt(ret.getProperty(POLL_TWEETSPERCALL)));
    	}catch(Exception e){
    		throw new CrawlerException("Problemas leyendo la configuracion del crawler:"+e.getMessage(),e);
    	}finally{
    		try{fi.close();}catch(Exception ee){}finally{fi=null;}
    	}
    	this.setConfiguration(ret);
    	
    }
}
