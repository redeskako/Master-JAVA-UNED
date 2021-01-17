package es.uned.master.java.practicayoutube;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletContext;

import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.util.*;

import es.uned.master.java.practicayoutube.bbdd.GestorBBDD;

/**
 * Clase que representa un canal de Youtube 
 * 
 * @author grupo alef (Juan Sánchez, Francisco Yagüe, Jose Torrecilla)
 *
 */
public class Canal {
	public static final String YOUTUBE_GDATA_SERVER = "http://gdata.youtube.com";
	public static final String USER_FEED_PREFIX = YOUTUBE_GDATA_SERVER
		      + "/feeds/api/users/";
	public static final String UPLOADS_FEED_SUFFIX = "/uploads";
	private String canalId;
	private ServletContext context;
	
	/**
	 * Constructor de clase
	 * 
	 * @param cnl id del canal que se crea
	 */
	public Canal(String cnl) {
		canalId = cnl;
		System.out.printf("Creando canal: %s\n", canalId);
	}
	
	/**
	 * Devuelve el id del canal
	 * 
	 * @return canalId id del canal
	 */
	public String getCanalId() {
		return canalId;
	}

	/**
	 * Devuelve el id del canal
	 * 
	 * @return canalId id del canal
	 */
	public String toString() {
		return canalId;
	}

	
	/**
	 * Consulta la base de datos para devolver una lista con todos los canales grabados, con paginación
	 * 
	 * @param primerRegistro Primer registro devuelto (paginación)
	 * @param limite Número máximo de registros devueltos (paginación)
	 * @return Colección de todos los canales que existen en la Base de datos (con paginación)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public static List<Canal> todosLosCanales(int primerRegistro, int limite) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().buscarTodosLosCanales(primerRegistro, limite);
	}
	
	/**
	 * Cuenta el número de canales grabados en la base de datos
	 * 
	 * @return Número de canales grabados en la base de datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public static int contarTodosLosCanales() throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().contarTodosLosCanales();
	}
	
	/**
	 * Consulta la bd y devuelve una lista con todos los vídeos asociados a este canal, con paginación
	 * 
	 * @param primerRegistro Primer registro devuelto (paginación)
	 * @param limite Número máximo de registros devueltos (paginación)
	 * @return Colección de todos los videos asociados a este Canal que existen en la Base de datos (con paginación)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public List<Video> videos(int primerRegistro, int limite) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().buscarVideosDeCanal(this, primerRegistro, limite);
	}
	
	/**
	 * Consulta la bd y cuenta los vídeos asociados a este canal
	 * 
	 * @return Número de vídeos asociados a este Canal en la base de datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public int contarVideos() throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().contarVideosDeCanal(this);
	}
	
	/**
	 * Asocia un video a este canal
	 * 
	 * @param video vídeo que se asocia a este Canal
	 * @return 'true' si la asociación se ha dado de alta en la Base de Datos sin problema. 'false' si no se ha dado de alta la asociación porque el canal o el vídeo no existían en la Base de Datos previamente
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public boolean asociarVideo(Video video) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().grabarAsociacionVideoCanal(video, this);
	}
	
	
	/**
	 * Graba en la base de datos este canal
	 * 
	 * @return 'true' si el canal no existía anteriormente en la Base de Datos (INSERT), 'false' si existía previamente (UPDATE)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public boolean grabarEnBBDD() throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().grabarEnTablaCanales(this);
	}
	
	
	/**
	 * Obtiene desde YouTube una lista de vídeos de este canal y la devuelve 
	 * 
	 * @return Lista de vídeos
	 * @throws IOException
	 * @throws ServiceException
	 * @throws ResourceNotFoundException
	 */
	public List<Video> obtenerVideos() throws IOException, ServiceException, ResourceNotFoundException {
		YouTubeService ytService;

		ytService = new YouTubeService("uned-practicaYouTube-1");
		VideoFeed videoFeed = ytService.getFeed(new URL(USER_FEED_PREFIX + canalId + UPLOADS_FEED_SUFFIX), VideoFeed.class);
		
		// título del videoFeed
	    String title = videoFeed.getTitle().getPlainText(); 
	    System.out.printf("VideoFeed: %s\n", title);
	    
	   // almacenar título 
	    List<VideoEntry> videoEntries = videoFeed.getEntries();
	    if (videoEntries.size() == 0) {
	    	System.out.println("Este feed no contiene entradas de vídeo.");
	    	throw new ResourceNotFoundException("");
	    }
	    ArrayList<Video> listaVideos = new ArrayList<Video>();
	    for (VideoEntry ve : videoEntries) {
	    	// almacenar entrada de vídeo
	    	if (ve.getTitle() != null) {
	    		YouTubeMediaGroup mediaGroup = ve.getMediaGroup();
	    		MediaKeywords keywords = mediaGroup.getKeywords();
		  	    System.out.printf("Id: %s -- Titulo: %s\n", mediaGroup.getVideoId(),ve.getTitle().getPlainText());
		  	    Video nuevoVideo = new Video(mediaGroup.getVideoId(), ve.getTitle().getPlainText(),keywords);
		  	    listaVideos.add(nuevoVideo);
		  	    System.out.println("-Tags(todos)-------> " + keywords.getKeywords());
		  	    System.out.println("-Tags(sinNoClaves)-> " + nuevoVideo.tagsComoArrayString());
	  	    }
	    }
	    return listaVideos;
	}
		

	/**
	 * Prueba la clase (para pruebas)
	 * 
	 * @param args
	 * @throws ResourceNotFoundException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public static void main(String[] args) throws ResourceNotFoundException, IOException, ServiceException {
		Canal cnl = new Canal("BBCWorldWide");
		cnl.obtenerVideos();
	}
}
