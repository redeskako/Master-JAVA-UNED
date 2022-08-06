package es.uned2014.recursosAlpha.auth;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import es.uned2014.recursosAlpha.baseDatos.BaseDatos;
import es.uned2014.recursosAlpha.usuario.Usuario;

/**
 * La clase coge a un usuario y lo envia al cliente
 * Contiene el objeto usuario, un token y un ID
 * Esta clase produce un token al producirse nuevo acceso, que nunca sera borrado y/o borrado
 * 
 * @author	Alpha UNED 2014
 * @version	RecursosWS 1.0
 * @since 	October 2016
 */

@XmlRootElement(name="auth")
public class Auth {

	private int idAuth;
	private String token;
	private Usuario usuario;

	/**
	 * Constructor: Inicializar nuevo usuario
	 */
	public Auth() {
		this.usuario = new Usuario();
	}

	/**
	 * @return The user object
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param The user to be set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getIdAuth() {
		return idAuth;
	}

	public void setIdAuth(int idAuth) {
		this.idAuth = idAuth;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * El metodo coge un token y comprueba si existe
	 * 	Si existe, devuelve un usuario y una instancia Auth
	 * @param token 
	 * @return Auth o null
	 */
	public static Auth getAuthByToken(String token){
		// Open a Database connection to get my data.
		BaseDatos bbdd = new BaseDatos();
		Auth auth = bbdd.getAuthByToken(token);

		// It returns a auth object if null if it doen't exists.
		return auth;
	}

	/**
	 * Generate a token for this Auth object
	 */
	public void generateToken(){
		this.token = UUID.randomUUID().toString();
	}


	/**
	 * Almacenar instancia Auth
	 * 
	 * @return boolean operacion exitosa
	 */
	public boolean save(){
		boolean result = false;
		
		if(this.usuario != null){
			int id;
			
			BaseDatos bbdd = new BaseDatos();
			bbdd.abrirConexion();
			String sql = "INSERT INTO auth (Token, Usuario) "
					+ " VALUES ('" + this.token + "', '" + this.usuario.getIdUsuario() + ")";
			id = bbdd.crear(sql);
			if(id != 0)
				result = true;
			
			// Se cierra la conexion
			bbdd.cerrarConexion();
		}

		return result;
	}
}
