/**
 * Este es el bean es el ayudante de vista a partir del cual se 
 * compone la página jsp donde se visualiza la lista de videos de un canal.
 */

package beans;

public class VistaVideos
{
	// Nombre del canal de los videos.
	private String nombreCanal;
	
	// Cadena que contiene la lista de videos maquetada para su presentación mediante HTML.
	private String lista;
	
	// Índice de las páginas.
	private String indice;
	
	/**
	 * Constructor.
	 */
	public VistaVideos()
	{	 
	}

	/**
	 * Obtiene el nombre del canal de youtube de los videos.
	 * @return nombre del canal de youtube de los videos.
	 */ 	
	public String getNombreCanal()
	{
		return nombreCanal;
	}

	/**
	 * Asigna el nombre del canal de youtube de los videos.
	 * @param nombre del canal de youtube de los videos.
	 */ 	
	public void setNombreCanal(String nombreCanal)
	{
		this.nombreCanal = nombreCanal;
	}	
	
	/**
	 * Obtiene una cadena con la lista de videos maquetada para su presentación.
	 * @return cadena.
	 */
	public String getLista()
	{
		return lista;
	}

	/**
	 * Asigna a una cadena la lista de vídeos maquetada para su presentación.
	 * @param título del video.
	 */ 		
	public void setLista(String lista)
	{
		this.lista = lista;
	}

	/**
	 * Obtiene una cadena con el índice de páginas maquetado para su presentación.
	 * @return cadena.
	 */
	public String getIndice()
	{
		return indice;
	}

	/**
	 * Asigna a una cadena el índice de páginas maquetado para su presentación.
	 * @param título del video.
	 */ 		
	public void setIndice(String indice)
	{
		this.indice = indice;
	}
}