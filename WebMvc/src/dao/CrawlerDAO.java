package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Canal;
import beans.Video;


public class CrawlerDAO {

	private Connection con;

	/**
	 *  Constructor.
	 *  Registra el driver para la conexiÃ³n a la BD.
	 */
	public CrawlerDAO()
	{
		try
		{
			DriverManager.registerDriver(new org.sqlite.JDBC());
		}
		catch (Exception e)
		{
			System.out.println("Error abriendo la base de datos");
			System.exit(0);
		}	 
	}

	/**
	 * Abre la conexiÃ³n a la BD.
	 */
	public void abre(String pathBD)
	{
		try
		{			 
			con  = DriverManager.getConnection("jdbc:sqlite:" + pathBD);			 
		}
		catch (Exception e)
		{
			System.out.println("Error abriendo la base de datos");
			System.exit(0);
			 
		}
	}
	
	public List<Canal> select()
	{
		List<Canal> canales = new ArrayList<Canal>();
		Canal canal;		
		try
		{
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM canales");
	
			while(resultSet.next())
			{
				canal = new Canal();
				canal.setId(resultSet.getString("id"));
				canal.setNombre(resultSet.getString("nombre"));
				canal.setDescripcion(resultSet.getString("descripcion"));
				canales.add(canal);
			}

			resultSet.close();
			statement.close();
		}
		catch (SQLException e)
		{
			System.out.println("Error obteniendo los videos de  la base de datos");
			 
		}

		return canales;
	}
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
	public List<String> selectVideos(String idCanal)
	{
		List<String> lstVideos = new ArrayList<String>();
		
		try
		{			
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT id FROM videos WHERE id_canal='" + idCanal + "'");
			while(resultSet.next())
			{
				lstVideos.add(resultSet.getString("id"));
			}

			resultSet.close();
			statement.close();
		}
		catch (SQLException e)
		{
			System.out.println("Error obteniendo los videos de  la base de datos");
			 
		}

		return lstVideos;
	}

	/**
	 *  Cierra la conexiÃ³n a la BD.
	 */
	public void cierra()
	{
		try
		{
			con.close();
		}
		catch (Exception e)
		{
			System.out.println("Error cerrando la base de datos");
			System.exit(0);
			
		}
	}	
	 
	 	 
}