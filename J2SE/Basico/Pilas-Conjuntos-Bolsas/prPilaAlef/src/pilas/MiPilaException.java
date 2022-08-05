package pilas;

public class MiPilaException extends RuntimeException{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public MiPilaException(){
		super("ErrorPila:\n");
	}
	public MiPilaException(String error){
		super("ErrorPila:\n"+error);
	}
}

