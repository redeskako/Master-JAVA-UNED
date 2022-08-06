package es.uned2014.recursosAlpha.clasesCliente;

import java.util.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.XmlRootElement;

import es.uned2014.recursosAlpha.clasesCliente.BaseDatos;
import es.uned2014.recursosAlpha.clasesCliente.ExcepcionRecursos;

/**
 * Representa un usuario que puede acceder o no a la aplicación.
 * 
 * @author	Alpha UNED 2014
 * @version	RecursosWS 1.0
 * @since 	Septiembre 2014
 */
public class Usuario {
	
	private int idUsuario;
	private String nombreUsuario;
	private String nombre;
	private String apellidos;
	private String contrasena;
	
	/**
	 * Método constructor, inicializa las variables a 0 o String vacío
	 */
	public Usuario(){
		this.idUsuario = 0;
		this.nombreUsuario = "";
		this.nombre = "";
		this.apellidos = "";
		this.contrasena = "";
	}	
	
	/**
	 * Método constructor, asigna valor a las variables:
	 * @param id para idUsuario
	 * @param nu para nombreUsuario
	 * @param n para nombre
	 * @param a para apellidos
	 * @param c para contrasena
	 */
	public Usuario(int id, String nu, String n, String a, String c){
		this.idUsuario = id;
		this.nombreUsuario = nu;
		this.nombre = n;
		this.apellidos = a;
		this.contrasena = c;
	}
	
	/**
	 * Se encripta la contraseña para que en caso de acceso a la base de datos, no se tenga acceso a 
	 * las contraseñas de los usuarios.
	 * @param s String con la contraseña original
	 * @return String con la contraseña encriptada
	 */
	public String trasformarContrasena(String s){
		
		String salt = "PracticaDelEquipoAlpha@#$!%^&*(*)1234567890";
		String contrasenaOriginal = s;

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
			md.update((contrasenaOriginal+salt).getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new ExcepcionRecursos(e.getMessage());
		}
		String contrasenaFinal = (new BigInteger(md.digest())).toString(16);
		
		return contrasenaFinal;
	}
	
	/**
	 * Método que comprueba si una pareja de usuario y contraseña existe en la base de datos.
	 * Si existe, devuelve el usuario. Si no, devuelve null.
	 * @param u String con el nombre de usuario
	 * @param c String con la contraseña sin encriptar
	 */
	public Usuario validarUsuario(String u, String c){
		Usuario usuario = null;
		
		// Se crea un objeto BaseDatos y se abre la conexión:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();
		
		// Se busca si existe un usuario con nombreUsuario y contrasena indicados:
		String sql = " SELECT * FROM `usuarios`"
				   + " WHERE NombreUsuario='" + u + "' AND Contrasena='" + this.trasformarContrasena(c) + "'";
		ArrayList<Usuario> arrayUsuarios = bbdd.consultarUsuarios(sql);
		if(arrayUsuarios.size()>0){
			usuario = arrayUsuarios.get(0);
		}	
		
		// Se cierra la conexión a la base de datos:
		bbdd.cerrarConexion();
		
		// Se devuelve el usuario encontrado (null si no se encuentra ninguno)
		return usuario;
		
	}

	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}	

} // end class Usuario
