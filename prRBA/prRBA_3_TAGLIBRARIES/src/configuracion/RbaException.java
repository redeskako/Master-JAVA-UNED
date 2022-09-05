package configuracion;

public class RbaException extends RuntimeException{
	public RbaException(){
		super("ERROR ");
	}
	public RbaException(String str){
		super("ERROR "+str);
	}
}