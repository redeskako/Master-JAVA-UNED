package es.miempresa.datos;


public class MiErrorBonitoException extends RuntimeException {
	public MiErrorBonitoException(){
		super("ErrorBontio:\n");
	}
	public MiErrorBonitoException(String error){
		super("ErrorBontio:\n"+error);
	}
}

