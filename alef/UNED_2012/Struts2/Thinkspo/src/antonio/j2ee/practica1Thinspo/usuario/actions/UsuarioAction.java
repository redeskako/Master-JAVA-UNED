package antonio.j2ee.practica1Thinspo.usuario.actions;

import java.util.Collection;
import java.util.Map;

import antonio.j2ee.practica1Thinspo.excepciones.SQLNegocioException;
import antonio.j2ee.practica1Thinspo.usuario.modelo.Usuario;
import antonio.j2ee.practica1Thinspo.usuario.modelo.UsuarioDAOFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * Action de Struts2 para dar soporte al CRUD de usuarios
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see com.opensymphony.xwork2.ActionSupport
 */
public class UsuarioAction extends ActionSupport {
	private static final long serialVersionUID = -8317261768670298489L;
    private String login;
    private String password;
    private String nombre;
    private String apellidos;

    
	/**
	 * Realiza la creacion de un nuevo usuario en caso de no existir ninguno con el mismo login y pass
	 * @return
	 */
    public String crear() {
      try{	
			//Comprobamos la correccion de los datos
			if( login==null || login.equals("")|| login.length()>15){
				addActionError("Debe introducir un login de usuario que no supere los 15 caracteres");
				return INPUT;
			}
			if( password==null || password.equals("")|| password.length()>15){
				addActionError("Debe introducir un password de usuario que no supere los 15 caracteres");
				return INPUT;
			}	
			if( nombre==null || nombre.equals("")|| nombre.length()>30){
				addActionError("Debe introducir un nombre de usuario que no supere los 30 caracteres");
				return INPUT;
			}
			if( apellidos==null || apellidos.equals("")|| apellidos.length()>30){
				addActionError("Debe introducir unos apellidos de usuario que no supere los 30 caracteres");
				return INPUT;
			}
	            //Comprobamos si existe algun usuario con el mismo Login
	        if(UsuarioDAOFactory.getInstancia().getUsuarioDAO().existeLogin(login)>0){
	                 addActionError("Existe un usuario del sistemas con el mismo Login");
	                 return INPUT;
	        }else {//procedemos con la creacion
	        	Usuario usuario=new Usuario(getLogin(),getPassword(),getNombre(),getApellidos());
	            UsuarioDAOFactory.getInstancia().getUsuarioDAO().create(usuario);
	            addActionMessage("Creado usuario con login: "+login);
	            return SUCCESS;
	        }   
      }catch (SQLNegocioException sqle) {
		 sqle.printStackTrace();
		 addActionError(sqle.getMessage());
		 return INPUT;
	  }
    }
    

	/**
	 * Realiza la modificacion de un usuario existente
	 * 
	 * @return
	 */
    public String modificar() {
        try{	
  			//Comprobamos la correccion de los datos.Deben de llegarnos al menos login y password
  			if( login==null || login.equals("")|| login.length()>15){
  				addActionError("Debe introducir un login de usuario que no supere los 15 caracteres");
  				return INPUT;
  			}
  			if( password==null || password.equals("")|| password.length()>15){
  				addActionError("Debe introducir un password de usuario que no supere los 15 caracteres");
  				return INPUT;
  			}	
  			if( nombre.length()>30){
  				addActionError("Debe introducir un nombre de usuario que no supere los 30 caracteres");
  				return INPUT;
  			}
  			if( apellidos.length()>30){
  				addActionError("Debe introducir unos apellidos de usuario que no supere los 30 caracteres");
  				return INPUT;
  			}
  	            //Comprobamos si existe algun usuario con el mismo Login y password
  	        if(UsuarioDAOFactory.getInstancia().getUsuarioDAO().existeLogin(login)==0){
  	                 addActionError("No Existe un usuario del sistema con el Login introducido");
  	                 return INPUT;
  	        }else {//Procedemos a realizar la modificacion del usuario
  	        	Usuario usuario=new Usuario(getLogin(),getPassword(),getNombre(),getApellidos());
  	            UsuarioDAOFactory.getInstancia().getUsuarioDAO().modificar(usuario);
  	            addActionMessage("Modificado usuario con login: "+login);
  	            return SUCCESS;
  	        }   
        }catch (SQLNegocioException sqle) {
  		 sqle.printStackTrace();
  		 addActionError(sqle.getMessage());
  		 return INPUT;
  	  }
  }
    
    /**
     * Realiza el login del usuario
     * @return
     */
	public String logar() {
		try{
			//Comprobamos la correccion de los datos
			if( login==null || login.equals("")|| login.length()>15){
				addActionError("Debe introducir un login de usuario que no supere los 15 caracteres");
				return INPUT;
			}
			if( password==null || password.equals("")|| password.length()>15){
				addActionError("Debe introducir un password de usuario que no supere los 15 caracteres");
				return INPUT;
			}		
			
			Map<String, Object> session=ActionContext.getContext().getSession();
			//Buscamos en BBDD para ver si es un usuario valido de la aplicacion
			Boolean encontrado=UsuarioDAOFactory.getInstancia().getUsuarioDAO().findUsuario(login, password);
				if(encontrado){
					session.put("user", true);
					//TODO actualizar fecha del ultimo acceso
					return SUCCESS;
				}else{
					addActionError("Usuario NO dado de alta en el Sistema.Consulte con el administrador");
				    return INPUT;
				} 
		}catch(SQLNegocioException sqle){
			addActionError(sqle.getMessage());
			return INPUT;
		}
	}
	
	
	public String eliminar() {
		try{
			//Comprobamos la correccion de los datos
			if( login==null || login.equals("")|| login.length()>15){
				addActionError("Debe introducir un login de usuario que no supere los 15 caracteres");
				return INPUT;
			}
			if( password==null || password.equals("")|| password.length()>15){
				addActionError("Debe introducir un password de usuario que no supere los 15 caracteres");
				return INPUT;
			}
			
			if(UsuarioDAOFactory.getInstancia().getUsuarioDAO().findUsuario(getLogin(),getPassword())) {
			       UsuarioDAOFactory.getInstancia().getUsuarioDAO().eliminar(getLogin(),getPassword());
			       addActionMessage("Eliminado usuario con login: "+login);
			       return SUCCESS;
			}else{
				 addActionError("El usuario no existe.No se puede eliminar");
				 return INPUT;
			}
				
		}catch(SQLNegocioException sqle ){
			sqle.printStackTrace();
			addActionError(sqle.getMessage());
			return INPUT;
		}	
	}

	//getter y setter
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	
}
