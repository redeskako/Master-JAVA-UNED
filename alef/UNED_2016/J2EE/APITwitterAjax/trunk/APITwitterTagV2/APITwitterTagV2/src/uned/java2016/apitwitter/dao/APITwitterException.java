package uned.java2016.apitwitter.dao;

public class APITwitterException extends RuntimeException {
	public APITwitterException(){
		super();
	}
	public APITwitterException(String err){
		super("\nError en disconformidad:\n\t"+err); 
	}

}
