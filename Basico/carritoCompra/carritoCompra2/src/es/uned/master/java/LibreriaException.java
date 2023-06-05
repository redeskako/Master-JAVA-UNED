package es.uned.master.java;

public class LibreriaException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public LibreriaException(){
		super("Libreria Exception");
	}

	public LibreriaException(String err){
		super("Libreria Exception:\n\t" + err);
	}
}
