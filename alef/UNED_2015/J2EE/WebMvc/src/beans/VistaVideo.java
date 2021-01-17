/**
 * Este es el bean es el ayudante de vista a partir del cual se 
 * compone la página jsp donde se visualiza un video.
 */

package beans;

public class VistaVideo
{
	// Id del video.
	private String idVideo;

	// Nombre del video.
	private String nombreVideo;

	// Datos del video.
	private String datosVideo;
	
	/**
	 * Constructor.
	 */
	public VistaVideo()
	{	 
	}

	/**
	 * Obtiene el id del video.
	 * @return id del video.
	 */ 	
	public String getIdVideo()
	{
		return idVideo;
	}

	/**
	 * Asigna el id del video.
	 * @param id del video.
	 */ 	
	public void setIdVideo(String idVideo)
	{
		this.idVideo = idVideo;
	}	
	
	/**
	 * Obtiene el nombre del video.
	 * @return nombre del video.
	 */ 	
	public String getNombreVideo()
	{
		return nombreVideo;
	}

	/**
	 * Asigna el nombre del video.
	 * @param nombre del video.
	 */ 	
	public void setNombreVideo(String nombreVideo)
	{
		this.nombreVideo = nombreVideo;
	}	

	/**
	 * Obtiene una cadena con los datos del video maquetados para su presentación.
	 * @return datos del video.
	 */
	public String getDatosVideo()
	{
		return datosVideo;
	}

	/**
	 * Asigna a una cadena los datos del video maquetados para su presentación.
	 * @param datos del video.
	 */ 		
	public void setDatosVideo(String datosVideo)
	{
		this.datosVideo = datosVideo;
	}
}