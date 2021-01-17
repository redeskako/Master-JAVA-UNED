package uned.java2016.apitwitter.twitter.crawler;

public class ExecutionResult {

	protected ExecutionRequest request=null;
	  public void setRequest(ExecutionRequest r){this.request=r;}
	  public ExecutionRequest getRequest(){return this.request;}
	  
	  
	public Poll poll=null;
	  public void setPoll(Poll p){this.poll=p;}
	  public Poll getPoll(){return this.poll;}
	
	public long maxId=-1;
	  public void setMaxId(long l){this.maxId=l;}
	  public long getMaxId(){return this.maxId;}
	  
	  
	ExecutionResult(ExecutionRequest init){
		this.setRequest(init);
	}
}
