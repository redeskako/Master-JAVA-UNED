package es.uned2014.recursosalpha.clasesCliente;

/**
 * Representa un usuario que puede acceder o no a la aplicación.
 * 
 * @author Alpha UNED 2014
 * @version RecursosWS 1.0
 * @since Septiembre 2014
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
	public Usuario() {
		this.idUsuario = 0;
		this.nombreUsuario = "";
		this.nombre = "";
		this.apellidos = "";
		this.contrasena = "";
	}

	/**
	 * Método constructor, asigna valor a las variables:
	 * 
	 * @param id
	 *            para idUsuario
	 * @param nu
	 *            para nombreUsuario
	 * @param n
	 *            para nombre
	 * @param a
	 *            para apellidos
	 * @param c
	 *            para contrasena
	 */
	public Usuario(int id, String nu, String n, String a, String c) {
		this.idUsuario = id;
		this.nombreUsuario = nu;
		this.nombre = n;
		this.apellidos = a;
		this.contrasena = c;
	}

	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            the idUsuario to set
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
	 * @param nombreUsuario
	 *            the nombreUsuario to set
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
	 * @param nombre
	 *            the nombre to set
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
	 * @param apellidos
	 *            the apellidos to set
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
	 * @param contrasena
	 *            the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

} // end class Usuario
