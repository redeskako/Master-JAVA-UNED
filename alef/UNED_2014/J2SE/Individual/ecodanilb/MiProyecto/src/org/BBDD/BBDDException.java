package org.BBDD;

public class BBDDException extends RuntimeException {
	public BBDDException(){
		super();
	}
	public BBDDException(String err){
		super("\nError en disconformidad:\n\t"+err); 
	}
}

