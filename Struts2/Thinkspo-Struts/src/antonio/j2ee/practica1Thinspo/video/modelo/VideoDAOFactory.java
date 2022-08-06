package antonio.j2ee.practica1Thinspo.video.modelo;

/**
 * Clase que implementa el Patron Factory utilizada para la creacion del DAO de Video
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public class VideoDAOFactory {
	private VideoDAO videoDAO;
	private static VideoDAOFactory instancia=null;

	public static VideoDAOFactory getInstancia(){
	  if (instancia==null)
	    instancia=new VideoDAOFactory();
	  return instancia;
	}
	   
	protected VideoDAOFactory(){
	   videoDAO=new VideoDAO();
	}

	public VideoDAO getVideoDAO() {
		return videoDAO;
	}

}
