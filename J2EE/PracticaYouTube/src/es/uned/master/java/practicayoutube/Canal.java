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
 * @author grupo alef (Juan S�nchez, Francisco Yag�e, Jose Torrecilla)
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
	 * Consulta la base de datos para devolver una lista con todos los canales grabados, con paginaci�n
	 * 
	 * @param primerRegistro Primer registro devuelto (paginaci�n)
	 * @param limite N�mero m�ximo de registros devueltos (paginaci�n)
	 * @return Colecci�n de todos los canales que existen en la Base de datos (con paginaci�n)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public static List<Canal> todosLosCanales(int primerRegistro, int limite) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().buscarTodosLosCanales(primerRegistro, limite);
	}
	
	/**
	 * Cuenta el n�mero de canales grabados en la base de datos
	 * 
	 * @return N�mero de canales grabados en la base de datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public static int contarTodosLosCanales() throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().contarTodosLosCanales();
	}
	
	/**
	 * Consulta la bd y devuelve una lista con todos los v�deos asociados a este canal, con paginaci�n
	 * 
	 * @param primerRegistro Primer registro devuelto (paginaci�n)
	 * @param limite N�mero m�ximo de registros devueltos (paginaci�n)
	 * @return Colecci�n de todos los videos asociados a este Canal que existen en la Base de datos (con paginaci�n)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public List<Video> videos(int primerRegistro, int limite) throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().buscarVideosDeCanal(this, primerRegistro, limite);
	}
	
	/**
	 * Consulta la bd y cuenta los v�deos asociados a este canal
	 * 
	 * @return N�mero de v�deos asociados a este Canal en la base de datos
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
	 * @param video v�deo que se asocia a este Canal
	 * @return 'true' si la asociaci�n se ha dado de alta en la Base de Datos sin problema. 'false' si no se ha dado de alta la asociaci�n porque el canal o el v�deo no exist�an en la Base de Datos previamente
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
	 * @return 'true' si el canal no exist�a anteriormente en la Base de Datos (INSERT), 'false' si exist�a previamente (UPDATE)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public boolean grabarEnBBDD() throws SQLException, NamingException, IOException, ResourceNotFoundException {
		return GestorBBDD.obtenerGestorBBDD().grabarEnTablaCanales(this);
	}
	
	
	/**
	 * Obtiene desde YouTube una lista de v�deos de este canal y la devuelve 
	 * 
	 * @return Lista de v�deos
	 * @throws IOException
	 * @throws ServiceException
	 * @throws ResourceNotFoundException
	 */
	public List<Video> obtenerVideos() throws IOException, ServiceException, ResourceNotFoundException {
		YouTubeService ytService;

		ytService = new YouTubeService("uned-practicaYouTube-1");
		VideoFeed videoFeed = ytService.getFeed(new URL(USER_FEED_PREFIX + canalId + UPLOADS_FEED_SUFFIX), VideoFeed.class);
		
		// t�tulo del videoFeed
	    String title = videoFeed.getTitle().getPlainText(); 
	    System.out.printf("VideoFeed: %s\n", title);
	    
	   // almacenar t�tulo 
	    List<VideoEntry> videoEntries = videoFeed.getEntries();
	    if (videoEntries.size() == 0) {
	    	System.out.println("Este feed no contiene entradas de v�deo.");
	    	throw new ResourceNotFoundException("");
	    }
	    ArrayList<Video> listaVideos = new ArrayList<Video>();
	    for (VideoEntry ve : videoEntries) {
	    	// almacenar entrada de v�deo
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
