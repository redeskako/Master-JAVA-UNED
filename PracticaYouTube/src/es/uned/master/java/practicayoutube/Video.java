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
 * @author grupo alef (Juan S�nchez, Francisco Yag�e, Jose Torrecilla)
 *
 */
public class Video {
	private String videoId;
	private String titulo;
	private List<Tag> losTagsDelVideo = new ArrayList<Tag>();
	private static TreeSet<String> lasNoClaves;	
	ServletContext context;
	
	/**
	 * Constructor de clase con colecci�n de no claves
	 * 
	 * @param nc colecci�n de no claves
	 */
	public Video(TreeSet<String> nc) {
		lasNoClaves = nc;
	}
	
	/**
	 * Constructor de clase con id de video y t�tulo
	 * 
	 * @param id id de video
	 * @param t t�tulo
	 */
	public Video(String id, String t) {
		videoId = id;
		titulo = t;
	}
	
	/**
	 * Constructor de clase con id de v�deo, t�tulo y palabras clave
	 * @param id id de video
	 * @param t t�tulo
	 * @param mk palabras clave (obtenidas de Youtube, es un objeto diferente a los 'Tags' asociados a un v�deo en la Base de Datos 

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
	 * Devuelve el id de v�deo
	 * 
	 * @return id de v�deo
	 */
	public String getVideoId() {
		return videoId;
	}

	/**
	 * Devuelve el t�tulo del v�deo
	 * 
	 * @return t�tulo del v�deo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Establece el t�tulo del v�deo
	 * 
	 * @param titulo nuevo t�tulo del v�deo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Devuelve todos los v�deos de la base de datos, con paginaci�n
	 * 
	 * @param primerRegistro Primer registro devuelto (paginaci�n)
	 * @param limite N�mero m�ximo de registros devueltos (paginaci�n)
	 * @return todos los v�deos de la base de datos, con paginaci�n
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public static List<Video> todosLosVideos(int primerRegistro, int limite) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().buscarTodosLosVideos(primerRegistro, limite);
	}
	
	/**
	 * Cuenta todos los v�deos de la base de datos
	 * 
	 * @return N�mero de v�deos grabados en la base de datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public static int contarTodosLosVideos() throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().contarTodosLosVideos();
	}
	
	/**
	 * Devuelve el canal al que este v�deo est� asociado
	 * 
	 * @return Canal al que este v�deo est� asociado
	 * @throws NamingException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public Canal canal() throws NamingException, SQLException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().buscarCanalDeVideo(this);
	}
	
	/**
	 * Asocia un v�deo a un canal
	 * 
	 * @param canal Canal al que se asocia este v�deo en la Base de Datos
	 * @return 'true' si la asociaci�n se ha dado de alta en la Base de Datos sin problema. 'false' si no se ha dado de alta la asociaci�n porque el canal o el v�deo no exist�an en la Base de Datos previamente
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public boolean asociarACanal(Canal canal) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().grabarAsociacionVideoCanal(this, canal);
	}
	
	/**
	 * Devuelve todos los tags de este v�deo, con paginaci�n
	 * 
	 * @param primerRegistro Primer registro devuelto (paginaci�n)
	 * @param limite N�mero m�ximo de registros devueltos (paginaci�n)
	 * @return Colecci�n de todos los tags asociados a este Video que existen en la Base de datos (con paginaci�n)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public List<Tag> tags(int primerRegistro, int limite) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().buscarTagsDeVideo(this, primerRegistro, limite);
	}
	
	/**
	 * Cuenta todos los tags de este v�deo, con paginaci�n
	 * 
	 * @return N�mero de tags asociados a este v�deo en la base de datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public int contarTags() throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().contarTagsDeVideo(this);
	}
	
	/**
	 * Asocia un tag a este v�deo
	 * 
	 * @param tag tag al que se asocia este v�deo
	 * @return 'true' si la asociaci�n no exist�a previamente en la Base de Datos (INSERT), 'false' si ya exist�a previamente (por lo que no se ha hecho nada)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public boolean asociarTag(Tag tag) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().grabarAsociacionVideoTag(this, tag);
	}
	
	/**
	 * Graba este v�deo en la base de datos
	 * 
	 * @return 'true' si el v�deo no exist�a anteriormente en la Base de Datos (INSERT), 'false' si exist�a previamente (UPDATE)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public boolean grabarEnBBDD() throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().grabarEnTablaVideos(this);
	}
	
	/**
	 * Devuelve un List con todos los Tags asociados a este v�deo obtenidos de YouTube
	 * 
	 * @return List con todos los Tags asociados a este v�deo obtenidos de YouTube
	 */
	public List<Tag> getTags() {
		return losTagsDelVideo;
	}
	
	/**
	 * Devuelve los Tags como un array de Strings (para depuraci�n)
	 * 
	 * @return List<String> con los nombres de los tags del v�deo
	 */
	public List<String> tagsComoArrayString() {
		List<String> retorno = new ArrayList<String>();
		for (Tag keyword : getTags()) {
			retorno.add(keyword.getTagId());
		}
		return retorno;
	}
	

	/**
	 * Devuelve un String con la informaci�n del v�deo 
	 * 
	 * @return String con la informaci�n del v�deo
	 */
	public String toString() {
		return titulo;
	}	
	
}
