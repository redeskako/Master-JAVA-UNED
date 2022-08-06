package beans;

public class HealthtopicException extends RuntimeException {
	public HealthtopicException(){
		super();
	}
	public HealthtopicException(String err){
		super("\nError en healthtopic:\n\t"+err); 
	}
}
