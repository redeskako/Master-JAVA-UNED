
package comercio.error;

public class ErrorComercioException extends RuntimeException {
	public ErrorComercioException(){
		super("\tErrorComercioException\n");
	}
	public ErrorComercioException(String err){
		super("ErrorComercioException\n"+err);
	}
}
