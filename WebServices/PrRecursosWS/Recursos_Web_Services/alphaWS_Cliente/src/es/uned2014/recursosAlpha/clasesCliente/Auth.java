package es.uned2014.recursosAlpha.clasesCliente;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * Class will basically wrap a user to send back to the client service.
 * It contains the user object itself, a token and id.
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
	 * Constructor: Initialize a new user
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
}
