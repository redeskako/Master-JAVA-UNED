package es.uned.clasesCliente;

import java.util.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 * Clase Usuario implementa Comparable
 * Representa un usuario que puede acceder o no a la aplicación.
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @fecha 	Junio-Julio 2014
 */
@XmlRootElement(name="usuario")
@XmlType(propOrder = {"idUsuario", "nombreUsuario", "nombre", "apellidos", "idRol", "rol"})
public class UsuarioCliente {
	
	private int idUsuario;
	private String nombreUsuario;
	private String nombre;
	private String apellidos;
	private String contrasena;
	private int idRol;
	private String rol;
	
	/**
	 * Método constructor, inicializa las variables a 0 o String vacío
	 */
	public UsuarioCliente(){
		
	}


	
	/**
	 * @return the idUsuario
	 */
	@XmlAttribute
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
	@XmlTransient
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * @return the idRol
	 */
	public int getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol the idRol to set
	 */
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

}
