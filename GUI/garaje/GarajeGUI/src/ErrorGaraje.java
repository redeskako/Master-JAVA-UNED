/**
 * Gestiona los errores que pueden producirse en la aplicación.
 * Extiende la clase RuntimeException.
 */


public class ErrorGaraje extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor sin parámetros.
	 * @param null
	 */
	public ErrorGaraje()
	{
		super("\nERROR");
	}

	/**
	 * Constructor con mensaje asociado.
	 * @param sMensaje: mensaje a mostrar.
	 */
	public ErrorGaraje(String sMensaje)
	{
		super("\nERROR: " + sMensaje);
	}
}