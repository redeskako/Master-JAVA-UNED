/**
 * Extiende RuntimeException para representar errores específicos.
 */

package es.uned.master2015.excepcion;

public class Excepcion extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public Excepcion()
	{
		super("Se ha producido un error.");
		System.err.println("Se ha producido un error.");
	}
	
	public Excepcion(String s)
	{
		super("Se ha producido un error: " + s);
		System.err.println("Se ha producido un error: " + s);
	}
}