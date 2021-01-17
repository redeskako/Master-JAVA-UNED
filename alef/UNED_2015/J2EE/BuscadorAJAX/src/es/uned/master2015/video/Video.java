/**
 * Clase que encapsula los datos de un video.
 */

package es.uned.master2015.video;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import es.uned.master2015.bd.ConexionBD;
import es.uned.master2015.excepcion.Excepcion;

public class Video implements Comparable<Object>
{
	// Propiedades.
	private String id;
	private String titulo;

	/**
	 * Constructor.
	 */
	public Video()
	{
		id = "";
		titulo = "";
	}

	/**
	 * Constructor.
	 * @param id identificador de video.
	 * @param titulo título del video.
	 */
	public Video(String id, String titulo)
	{
		this.id = id;
		this.titulo = titulo;
	}

	/**
	 * Obtiene el id.
	 * @return id.
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Ajusta el id.
	 * @param id.
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Obtiene el título.
	 * @return titulo.
	 */
	public String getTitulo()
	{
		return titulo;
	}

	/**
	 * ajusta el título.
	 * @param titulo.
	 */
	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}
	
	/**
	 * Obtiene los vídeos correspondientes al título especificado.
	 * @param titulo título a buscar.
	 * @return lista de videos.
	 */
	public List<Video> buscaVideos(String titulo)
	{
		// Lista de videos a devolver.
		List<Video> lstVideos = new ArrayList<Video>();
		
		// Abre la conexión.
		ConexionBD cnx = new ConexionBD();
		cnx.abre();	
				
		// Compone la consulta.
		String sql = "SELECT * FROM videos ";
		if (titulo != null && !titulo.equals(""))
			sql += "WHERE titulo LIKE '%" + titulo + "%' ";
		
		// Ejecuta la consulta.
		ResultSet result = cnx.consulta(sql);
		
		// Compone la lista de videos.
		try
		{
			while (result.next())
				lstVideos.add(new Video(result.getString("id"), result.getString("titulo")));
		}
		catch (SQLException e)
		{
			throw new Excepcion("Error recuperando datos de la consulta: " + e.getMessage() );
		}

		// Cierra la conexión.
		cnx.cierra();
		
		return lstVideos;
	}
		
	public String toString()
	{
		return "Id: " + id + "; Título: " + titulo;
	}

	/**
	 * La comparación de los vídeos se basa en sus ids.
	 */
	public int compareTo(Object obj)
	{
		Video video = (Video) obj;
		return id.compareTo(video.id);
	}

	/**
	 * Dos videos son iguales si sus ids lo son.
	 */
	public boolean equals(Object obj)
	{
		Video video = (Video) obj;
		return id == video.id;
	}
}