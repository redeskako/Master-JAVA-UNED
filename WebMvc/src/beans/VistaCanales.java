/**
 * Este es el bean es el ayudante de vista a partir del cual se 
 * compone la página jsp donde se visualiza la lista de canales.
 */

package beans;

public class VistaCanales
{

	// Cadena que contiene la lista de canales maquetada para su presentación mediante HTML.
	private String lista;
	
	/**
	 * Constructor.
	 */
	public VistaCanales()
	{	 
	}
	
	

	/**
	 * Obtiene una cadena con la lista de canales maquetada para su presentación.
	 * @return cadena.
	 */
	public String getLista()
	{
		return lista;
	}

	/**
	 * Asigna a una cadena la lista de canales maquetada para su presentación.
	 * @param cadena.
	 */ 		
	public void setLista(String lista)
	{
		this.lista = lista;
	}
}