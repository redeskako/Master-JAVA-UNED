/**
 * 
 */
package es.uned.master.java.practicayoutube;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.google.gdata.util.ResourceNotFoundException;

import es.uned.master.java.practicayoutube.bbdd.GestorBBDD;

/**
 * Clase que implementa un objeto Tag
 * 
 * @author Grupo Alef (José Torrecila)
 *
 */
public class Tag {
	private String tagId;

	/**
	 * Constructor de clase
	 * 
	 * @param tagId id del Tag
	 */
	public Tag(String tagId) {
		this.tagId = tagId;
	}

	/**
	 * Devuelve el id del Tag
	 * 
	 * @return id del Tag
	 */
	public String getTagId() {
		return tagId;
	}
	
	/**
	 * Devuelve el id del Tag
	 * 
	 * @return id del Tag
	 */
	public String toString() {
		return tagId;
	}

	/**
	 * Devuelve todos los vídeos de la base de datos, con paginación
	 * @param primerRegistro Primer registro devuelto (paginación)
	 * @param limite Número máximo de registros devueltos (paginación)
	 * @return Todos los tags de la base de datos, con paginación
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException
	 */
	public static List<Tag> todosLosTags(int primerRegistro, int limite) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().buscarTodosLosTags(primerRegistro, limite);
	}
	
	/**
	 * Cuenta todos los tags de la base de datos
	 * @return Número de tags grabados en la base de datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException
	 */
	public static int contarTodosLosTags() throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().contarTodosLosTags();
	}
	
	/**
	 * Devuelve todos los vídeos asociados a este tag, con paginación
	 * @param primerRegistro Primer registro devuelto (paginación)
	 * @param limite Número máximo de registros devueltos (paginación)
	 * @return Todos los vídeos asociados a este tag, con paginación
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException
	 */
	public List<Video> videos(int primerRegistro, int limite) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().buscarVideosDeTag(this, primerRegistro, limite);
	}
	
	/**
	 * Cuenta todos los vídeos asociados a este tag en la base de datos
	 * @return Número de vídeos asociados a este tag en la base de datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException
	 */
	public int contarVideos() throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().contarVideosDeTag(this);
	}
	
	/**
	 * Asocia un vídeo a este tag
	 * @param video video que se asocia a este tag
	 * @return 'true' si la asociación no existía previamente en la Base de Datos (INSERT), 'false' si ya existía previamente (por lo que no se ha hecho nada)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException
	 */
	public boolean asociarVideo(Video video) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().grabarAsociacionVideoTag(video,this);
	}

	/**
	 * Graba este tag en la base de datos
	 * @return 'true' si el tag no existía anteriormente en la Base de Datos (INSERT), 'false' si existía previamente (UPDATE)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException
	 */
	public boolean grabarEnBBDD() throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().grabarEnTablaTags(this);
	}
}
