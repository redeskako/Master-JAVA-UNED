package es.uned.master.java;

public class LibreriaException extends RuntimeException{
	public LibreriaException(){
		super("Libreria Exception");
	}
	public LibreriaException(String err){
		super("Libreria Exception:\n\t"+err);
	}
}
