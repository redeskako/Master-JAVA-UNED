package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.Video;

public class VideoDao
{
	/*
	 * Esta clase asume la existencia de una tabla de nombre videos con los siguientes campos:
	 * 
	 *		id (texto): identificador del video según la nomenclatura de Youtube.
	 *		id_canal (texto): identificador del canal según la nomenclatura de Youtube.
	 *		titulo (texto): título del video.
	 *		descripcion (texto): descripción del video.
	 *		fecha_publicacion (texto): fecha de publicación del video.
	 *		thumbnail (texto): dirección de la imagen identificativa del video.
	 */
	
	Connection con;

	/**
	 * Obtiene la conexión a la BD.
	 * @return conexión a la BD.
	 */
	public void openConnection()
	{
		try
        {
        	Context envContext = new InitialContext();
        	Context initContext  = (Context)envContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)initContext.lookup("jdbc/ytb");
            con = ds.getConnection();            
        }
        catch (SQLException e)
        {
        	e.printStackTrace();
        }
		catch (NamingException e)
		{
			e.printStackTrace();
		}              
	}
	
	/**
	 * Introduce una lista de videos en la BD.
	 * @param lst lista de videos.
	 */
	public void insert(List<Video> lst)
	{
		for (Video video : lst)
		{
			// Para prevenir que haya comillas dobles en el texto.
			String sTitulo = video.getTitulo().replaceAll("\"", "\"\"");
			String sDescripcion = video.getDescripcion().replaceAll("\"", "\"\"");
			
			String sql = "INSERT INTO videos(id, id_canal, titulo, descripcion, fecha_publicacion, thumbnail) VALUES (\""
					+ video.getId() + "\", \"" + video.getIdCanal() + "\", \"" + sTitulo 
					+  "\", \"" + sDescripcion + "\", \"" + video.getFechaPublicacion() +  "\", \"" + video.getThumbnail() + "\")"; 
			try
			{
				Statement statement = con.createStatement();
				
				// Comprueba que el video no esté insertado ya.
				ResultSet rs = statement.executeQuery("SELECT id FROM videos WHERE id='" + video.getId() + "'");
				
				if (!rs.next())
				{
					statement.executeUpdate(sql);
	                System.out.println("Almacenando video " + sTitulo + " en la BD.");
				}
				
		        statement.close();
			}
			catch (SQLException e)
			{
				System.err.println(video.getTitulo() + ", " + video.getDescripcion());
			}			
		}	
	}

	/**
	 * Obtiene videos de un canal de la BD.
	 * @param idCanal canal del cual se van a obtener los videos.
	 * @param página a mostrar.
	 * @param número de videos por página a mostrar.
	 * @return lista de videos del canal.
	 */
	public List<Video> select(String idCanal, int nNumPagina, int nNumVideosPorPagina)
	{
		List<Video> lstVideos = new ArrayList<Video>();
		Video video;

		try
		{
			// Primer video a recuperar en función de la página en la que nos encontremos.
			int nPrimerVideo = ((nNumPagina - 1) * nNumVideosPorPagina) + 1;

			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM videos WHERE id_canal='" + idCanal + "' ORDER BY id LIMIT " + nPrimerVideo + ", " + nNumVideosPorPagina);
	
			while(resultSet.next())
			{
				video = new Video(resultSet.getString("id"), resultSet.getString("id_canal"), resultSet.getString("titulo")
						, resultSet.getString("descripcion"), resultSet.getString("fecha_publicacion"), resultSet.getString("thumbnail"));
				lstVideos.add(video);
			}

			resultSet.close();
			statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return lstVideos;
	}

	/**
	 * Obtiene de la BD los id de vídeo de un canal.
	 * @param idCanal canal del cual se van a obtener los videos.
	 *
	 */
	public List<String> select(String idCanal)
	{
		List<String> lstVideos = new ArrayList<String>();
		
		try
		{			
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT id_video FROM videos WHERE id_canal='" + idCanal + "'");
			while(resultSet.next())
			{
				lstVideos.add(resultSet.getString("id_video"));
			}

			resultSet.close();
			statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return lstVideos;
	}
	
	/**
	 * Obtiene un video de la BD.
	 * @param id identificador del video.
	 * @return video.
	 */
	public Video selectVideo(String id)
	{
		Video video = new Video();

		try
		{
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM videos WHERE id='" + id + "'");
	
			if(resultSet.next())
			{
				video = new Video(resultSet.getString("id"), resultSet.getString("id_canal"), resultSet.getString("titulo")
						, resultSet.getString("descripcion"), resultSet.getString("fecha_publicacion"), resultSet.getString("thumbnail"));
			}

			resultSet.close();
			statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return video;
	}

	/**
	 * Obtiene el número de vídeos registrados en la BD de un determinado canal.
	 * @param idCanal canal del cual se van a obtener los vides.
	 * @return número de videos del canal.
	 */
	public int getNumVideos(String idCanal)
	{
		int nNumVideos = 0;
		
		try
		{
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM videos WHERE id_canal='" + idCanal + "'");
			nNumVideos = resultSet.getInt(1);
			resultSet.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return nNumVideos;
	}

	/**
	 * Cierra la conexión a la BD.
	 */
	public void closeConnection()
	{		
		try
		{
			if (con != null)
				con.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}