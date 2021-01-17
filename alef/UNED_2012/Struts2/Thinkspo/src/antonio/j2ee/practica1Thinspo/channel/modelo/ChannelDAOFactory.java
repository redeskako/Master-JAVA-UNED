package antonio.j2ee.practica1Thinspo.channel.modelo;

/**
 * Clase que implementa el Patron Factory utilizada para la creacion del DAO de channels
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public class ChannelDAOFactory {
	private ChannelDAO channelDAO;
	private static ChannelDAOFactory instancia=null;

	public static ChannelDAOFactory getInstancia(){
	  if (instancia==null)
	    instancia=new ChannelDAOFactory();
	  return instancia;
	}
	   
	protected ChannelDAOFactory(){
	   channelDAO=new ChannelDAO();
	}

	public ChannelDAO getChannelDAO() {
		return channelDAO;
	}
	   
	   

}
