package kwic;

public class KWICException extends RuntimeException {

	public KWICException(){
		super("error");
	}

	public KWICException(String msg){
		super("error" + msg);
	}
}
