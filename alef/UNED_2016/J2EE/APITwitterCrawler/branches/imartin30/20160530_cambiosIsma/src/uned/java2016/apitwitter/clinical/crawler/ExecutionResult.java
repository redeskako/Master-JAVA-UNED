package uned.java2016.apitwitter.clinical.crawler;

/**
 * @author alber
 *	Clase que gestionar el manejo del sondeo realizado
 */
public class ExecutionResult {
	protected ExecutionRequest request=null;
	  public void setRequest(ExecutionRequest r){this.request=r;}
	  public ExecutionRequest getRequest(){return this.request;}
	  
	  
	public Poll poll=null;
	  public void setPoll(Poll p){this.poll=p;}
	  public Poll getPoll(){return this.poll;}
	
	ExecutionResult(ExecutionRequest init){
		this.setRequest(init);
	}
}
