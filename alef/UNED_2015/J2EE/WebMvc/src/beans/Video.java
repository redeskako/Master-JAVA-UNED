package beans;

public class Video
{
	private String id; // Id de video en Youtube.
	private String idCanal;
	
	private String titulo;
	private String descripcion;
	private String fechaPublicacion;
	private String thumbnail;

	/**
	 * Constructor.
	 */
	public Video()
	{	 
	}

	public Video(String id, String idCanal, String titulo, String descripcion, String fechaPublicacion, String thumbnail)
	{	 
		this.id = id;
		this.idCanal = idCanal;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaPublicacion = fechaPublicacion;
		this.thumbnail = thumbnail;
	}

	/**
	 * Obtiene el id del video.
	 * @return id del video.
	 */ 
	public String getId()
	{
		return id;
	}

	/**
	 * Asigna el id al video.
	 * @param id del video.
	 */ 	
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Obtiene el id del canal de youtube del video.
	 * @return id del canal de youtube del video.
	 */ 	
	public String getIdCanal()
	{
		return idCanal;
	}

	/**
	 * Asigna el id de canal de youtube al video.
	 * @param id de canal de youtube del video.
	 */ 	
	public void setIdCanal(String idCanal)
	{
		this.idCanal = idCanal;
	}	
	
	/**
	 * Obtiene el título del video.
	 * @return título del video.
	 */ 	
	public String getTitulo()
	{
		return titulo;
	}
	
	/**
	 * Asigna el título al video.
	 * @param título del video.
	 */ 		
	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	/**
	 * Obtiene la descripción del video.
	 * @return descripción del video.
	 */ 	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	/**
	 * Asigna la descripción al video.
	 * @param descripción del video.
	 */ 	
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene la fecha de publicación del video.
	 * @return fecha de publicación del video.
	 */ 	
	public String getFechaPublicacion()
	{
		return fechaPublicacion;
	}
	
	/**
	 * Asigna la fecha de publicación al video.
	 * @param fecha de publicación del video.
	 */
	public void setFechaPublicacion(String fechaPublicacion)
	{
		this.fechaPublicacion = fechaPublicacion;
	}

	/**
	 * Obtiene el thumbnail del video.
	 * @return thumbnail del video.
	 */ 
	public String getThumbnail()
	{
		return thumbnail;
	}

	/**
	 * Asigna el thumbnail al video.
	 * @param thumbnail del video.
	 */
	public void setThumbnail(String thumbnail)
	{
		this.thumbnail = thumbnail;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		// Comprobaciones básicas.
	    if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof Video)) return false;
	    
	    // Dos videos son iguales si su identificador de video es el mismo.
	    Video vid = (Video) obj;
	    return(vid.getId().equals(this.getId()));
	}
}