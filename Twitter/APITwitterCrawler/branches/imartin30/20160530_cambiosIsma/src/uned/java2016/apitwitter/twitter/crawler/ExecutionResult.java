package uned.java2016.apitwitter.twitter.crawler;

/**
 * Representa el resultado de una ejecución del crawler. Un resultado de ejecución siempre es relativo a una solicitud de ejecucion,
 * de ahí q un ExecutionResult requiera una instancia de ExecutionRequest al q esta ligado.
 * 
 * @author Jose Barba Martinez (jbarba63)
 */
public class ExecutionResult {

	/**
	 * ExecutionRequest correspondiente a este ExecutionResult
	 */
	protected ExecutionRequest request=null;
	  public void setRequest(ExecutionRequest r){this.request=r;}
	  public ExecutionRequest getRequest(){return this.request;}
	  
	/**
	 * Sondeo utilizado durante la ejecución y q contiene el resultado de la búsqueda.  
	 */
	public Poll poll=null;
	  public void setPoll(Poll p){this.poll=p;}
	  public Poll getPoll(){return this.poll;}
	/**
	 * Limite superior de la búsqueda de tweets.
	 */
	public long maxId=-1;
	  public void setMaxId(long l){this.maxId=l;}
	  public long getMaxId(){return this.maxId;}
	  
	/**
	 * Constructor   
	 * @param init Instancia de ExecutionRequest q origino este ExecutionResult
	 */
	ExecutionResult(ExecutionRequest init){
		this.setRequest(init);
	}
}
