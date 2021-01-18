package es.uned.master.java.grajeGUI.capaGestion;
/**
 * Gestiona los errores que pueden producirse en la aplicación.
 * Extiende la clase RuntimeException. *
 * Version 1
 * @author jrequeijo2, Jose lopez
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