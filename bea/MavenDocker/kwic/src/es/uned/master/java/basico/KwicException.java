package es.uned.master.java.basico;

public class KwicException extends RuntimeException{
	public KwicException(){
		super("Kwic Excepcion:\n");
	}
	public KwicException(String str){
		super("Kwic Excepcion:\n"+str);
	}

}
