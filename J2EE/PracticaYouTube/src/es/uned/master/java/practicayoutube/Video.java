package es.uned.master.java.practicayoutube;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.naming.NamingException;
import javax.servlet.ServletContext;

import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.util.ResourceNotFoundException;

import es.uned.master.java.practicayoutube.bbdd.GestorBBDD;

/**
 * Clase que implementa un objeto Video
 * 
 * @author grupo alef (Juan Sánchez, Francisco Yagüe, Jose Torrecilla)
 *
 */
public class Video {
	private String videoId;
	private String titulo;
	private List<Tag> losTagsDelVideo = new ArrayList<Tag>();
	private static TreeSet<String> lasNoClaves;	
	ServletContext context;
	
	/**
	 * Constructor de clase con colección de no claves
	 * 
	 * @param nc colección de no claves
	 */
	public Video(TreeSet<String> nc) {
		lasNoClaves = nc;
	}
	
	/**
	 * Constructor de clase con id de video y título
	 * 
	 * @param id id de video
	 * @param t título
	 */
	public Video(String id, String t) {
		videoId = id;
		titulo = t;
	}
	
	/**
	 * Constructor de clase con id de vídeo, título y palabras clave
	 * @param id id de video
	 * @param t título
	 * @param mk palabras clave (obtenidas de Youtube, es un objeto diferente a los 'Tags' asociados a un vídeo en la Base de Datos 

	 */
	public Video(String id, String t, MediaKeywords mk) {
		videoId = id;
		titulo = t;

		for(String keyword : mk.getKeywords()) {
			if (!lasNoClaves.contains(keyword.toUpperCase())) {
				losTagsDelVideo.add(new Tag(keyword));
			}
		}
	}

	/**
	 * Devuelve el id de vídeo
	 * 
	 * @return id de vídeo
	 */
	public String getVideoId() {
		return videoId;
	}

	/**
	 * Devuelve el título del vídeo
	 * 
	 * @return título del vídeo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Establece el título del vídeo
	 * 
	 * @param titulo nuevo título del vídeo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Devuelve todos los vídeos de la base de datos, con paginación
	 * 
	 * @param primerRegistro Primer registro devuelto (paginación)
	 * @param limite Número máximo de registros devueltos (paginación)
	 * @return todos los vídeos de la base de datos, con paginación
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public static List<Video> todosLosVideos(int primerRegistro, int limite) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().buscarTodosLosVideos(primerRegistro, limite);
	}
	
	/**
	 * Cuenta todos los vídeos de la base de datos
	 * 
	 * @return Número de vídeos grabados en la base de datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public static int contarTodosLosVideos() throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().contarTodosLosVideos();
	}
	
	/**
	 * Devuelve el canal al que este vídeo está asociado
	 * 
	 * @return Canal al que este vídeo está asociado
	 * @throws NamingException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public Canal canal() throws NamingException, SQLException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().buscarCanalDeVideo(this);
	}
	
	/**
	 * Asocia un vídeo a un canal
	 * 
	 * @param canal Canal al que se asocia este vídeo en la Base de Datos
	 * @return 'true' si la asociación se ha dado de alta en la Base de Datos sin problema. 'false' si no se ha dado de alta la asociación porque el canal o el vídeo no existían en la Base de Datos previamente
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public boolean asociarACanal(Canal canal) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().grabarAsociacionVideoCanal(this, canal);
	}
	
	/**
	 * Devuelve todos los tags de este vídeo, con paginación
	 * 
	 * @param primerRegistro Primer registro devuelto (paginación)
	 * @param limite Número máximo de registros devueltos (paginación)
	 * @return Colección de todos los tags asociados a este Video que existen en la Base de datos (con paginación)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public List<Tag> tags(int primerRegistro, int limite) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().buscarTagsDeVideo(this, primerRegistro, limite);
	}
	
	/**
	 * Cuenta todos los tags de este vídeo, con paginación
	 * 
	 * @return Número de tags asociados a este vídeo en la base de datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public int contarTags() throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().contarTagsDeVideo(this);
	}
	
	/**
	 * Asocia un tag a este vídeo
	 * 
	 * @param tag tag al que se asocia este vídeo
	 * @return 'true' si la asociación no existía previamente en la Base de Datos (INSERT), 'false' si ya existía previamente (por lo que no se ha hecho nada)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public boolean asociarTag(Tag tag) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().grabarAsociacionVideoTag(this, tag);
	}
	
	/**
	 * Graba este vídeo en la base de datos
	 * 
	 * @return 'true' si el vídeo no existía anteriormente en la Base de Datos (INSERT), 'false' si existía previamente (UPDATE)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public boolean grabarEnBBDD() throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().grabarEnTablaVideos(this);
	}
	
	/**
	 * Devuelve un List con todos los Tags asociados a este vídeo obtenidos de YouTube
	 * 
	 * @return List con todos los Tags asociados a este vídeo obtenidos de YouTube
	 */
	public List<Tag> getTags() {
		return losTagsDelVideo;
	}
	
	/**
	 * Devuelve los Tags como un array de Strings (para depuración)
	 * 
	 * @return List<String> con los nombres de los tags del vídeo
	 */
	public List<String> tagsComoArrayString() {
		List<String> retorno = new ArrayList<String>();
		for (Tag keyword : getTags()) {
			retorno.add(keyword.getTagId());
		}
		return retorno;
	}
	

	/**
	 * Devuelve un String con la información del vídeo 
	 * 
	 * @return String con la información del vídeo
	 */
	public String toString() {
		return titulo;
	}	
	
}
