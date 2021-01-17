package pruebas;

public class ClaseError extends RuntimeException {
	
	public ClaseError(){
		super("Se ha producido un error." );
	}
	
	public ClaseError(String arg){
		super("Se ha producido un error: " + arg);
	}
	
	public ClaseError(String arg, Throwable arg2){
		super("Se ha producido un error: " + arg, arg2);
	}
	
}// fin clase ClaseError
