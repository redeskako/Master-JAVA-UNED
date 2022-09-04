
package org.listas;

public class PilaParException extends RuntimeException {
	public PilaParException(){
		super("\n\tPilaParException");
	}
	public PilaParException(String err){
		super("\n\tPilaParException"+err);
	}
}
