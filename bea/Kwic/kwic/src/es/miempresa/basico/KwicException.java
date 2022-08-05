package es.miempresa.basico;

public class KwicException extends RuntimeException{
	public KwicException(){
		super("Kwic Excepcion:\n");
	}
	public KwicException(String str){
		super("Kwic Excepcion:\n"+str);
	}

}
