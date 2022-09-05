package antonio.j2ee.practica1Thinspo.usuario.modelo;
/**
 * Clase que implementa el Patron Factory utilizada para la creacion del DAO de usuarios
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public class UsuarioDAOFactory {
	private UsuarioDAO usuarioDAO;
	private static UsuarioDAOFactory instancia=null;

	public static UsuarioDAOFactory getInstancia(){
	  if (instancia==null)
	    instancia=new UsuarioDAOFactory();
	  return instancia;
	}
	   
	protected UsuarioDAOFactory(){
	   usuarioDAO=new UsuarioDAO();
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}
	   
	   

}
