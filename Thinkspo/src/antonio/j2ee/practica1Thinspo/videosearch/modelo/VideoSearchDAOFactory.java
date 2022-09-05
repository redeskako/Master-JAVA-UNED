package antonio.j2ee.practica1Thinspo.videosearch.modelo;

/**
 * Clase que implementa el Patron Factory utilizada para la creacion del DAO de VideoSearch
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public class VideoSearchDAOFactory {
	private VideoSearchDAO videoSearchDAO;
	private static VideoSearchDAOFactory instancia=null;

	public static VideoSearchDAOFactory getInstancia(){
	  if (instancia==null)
	    instancia=new VideoSearchDAOFactory();
	  return instancia;
	}
	   
	protected VideoSearchDAOFactory(){
	   videoSearchDAO=new VideoSearchDAO();
	}

	public VideoSearchDAO getVideoSearchDAO() {
		return videoSearchDAO;
	}

}
