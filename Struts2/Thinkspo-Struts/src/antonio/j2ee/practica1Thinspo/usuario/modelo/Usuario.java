package antonio.j2ee.practica1Thinspo.usuario.modelo;


/**
 * Representa a una fila de la tabla Usuario
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public class Usuario {
   protected String nombre;
   protected String apellidos;
   protected String login;
   protected String password;

   public Usuario(){
	   
   }
   
   /**
    * Constructor con parametros
    * @param login
    * @param password
    * @param nombre
    * @param apellidos
    */
   public Usuario(String login,String password,String nombre,String apellidos){
	   this.nombre=nombre;
	   this.apellidos=apellidos;
	   this.login=login;
	   this.password=password;
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

  
}
