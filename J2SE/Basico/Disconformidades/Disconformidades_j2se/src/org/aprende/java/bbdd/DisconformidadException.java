package org.aprende.java.bbdd;

public class DisconformidadException extends RuntimeException {
	public DisconformidadException(){
		super();
	}
	public DisconformidadException(String err){
		super("\nError en disconformidad:\n\t"+err); 
	}
}
