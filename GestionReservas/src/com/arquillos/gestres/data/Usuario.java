/*
 * 
 * 
 */

package com.arquillos.gestres.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.jasypt.util.password.BasicPasswordEncryptor;

/* 
 * La anotación javax.persistence.Entity se utiliza para marcar una clase como entidad.
 * Adicionalmente, la anotación javax.persistence.Table especifica explícitamente el nombre
 * de la tabla a la que está asociada la entidad. Sin esta especificación, se utiliza
 * como nombre de la tabla el de la entidad en mayúsculas.
 */

@Entity
@Table( name = "usuario" )
public class Usuario implements Serializable { 

	public static final String ADMIN_LOGIN_DEF = "admin";
	public static final String ADMIN_PASSWORD_DEF = "admin";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer usuario_id;  
	@Column( nullable = false, unique = true)
	private String username;			// código de usuario para acceder a la aplicación
	private String password;  		// password de usuario para acceder a la aplicación
	private String nombre;				// nombre de usuario
	private String apellidos;			// apellidos de usuario
	private String email;					// correo electrónico (se usa para enviar las notificaciones)
	private String telefono;			// teléfono o extensión telefónica
	private String comentarios; 	// comentarios 
	@Column(name="es_admin")
	private boolean admin;				// indicador de que el usuario tiene perfil de administrador
	private boolean activo;  			// baja lógica
	@ManyToOne
	@JoinColumn(name="autorizador")
	private Usuario autorizador;  // autorizador
	@Column(columnDefinition = "enum('DUMMY')")
	@Enumerated(EnumType.STRING)
	private Color color;					// Color asociado al usuario

	public Usuario() {
		color = Color.DEFECTO;
	}  

	public Integer getId() {
		return usuario_id;
	}

	public void setId(Integer id) {
		this.usuario_id = id;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}  

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}  

	public Usuario getAutorizador() {
		return autorizador;
	}

	public void setAutorizador(Usuario autorizador) {
		this.autorizador = autorizador;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public int hashCode() {
		return (usuario_id != null ? usuario_id.hashCode() : 0);
	}

	@Override
	public boolean equals(Object objeto) {
		// TODO: Este método no funcionará si no se establece id
		if (!( objeto instanceof Usuario)) {
			return false;
		}
		Usuario otro = (Usuario) objeto;
		if ((this.usuario_id == null && otro.usuario_id != null) || 
				(this.usuario_id != null && !this.usuario_id.equals(otro.usuario_id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return nombre + " " + ((apellidos == null) ? "" : apellidos);
	}

	public boolean isNuevo() {
		return usuario_id == null || usuario_id == 0;       
	}

	public static Usuario crearUsuarioAdministradorDefecto() {		
		Usuario u = new Usuario();

		u.admin = true;
		u.autorizador = null;
		u.color = Color.DEFECTO;
		u.comentarios = "Usuario Administrador por Defecto";
		u.email = "aarquillos@orange.es";
		u.username = ADMIN_LOGIN_DEF;
		u.nombre= "Usuario Administrador por Defecto";
		u.password = new BasicPasswordEncryptor().encryptPassword(ADMIN_PASSWORD_DEF);
		u.telefono = "no disponible";

		return u;
	}

	public static Usuario crearUsuarioCentinela() {		
		Usuario u = new Usuario();

		u.admin = false;
		u.autorizador = null;
		u.color = Color.DEFECTO;
		u.comentarios = "";
		u.email = "";
		u.usuario_id = -1;
		u.username = "";
		u.nombre = "- None -";
		u.password = "";
		u.telefono = "";

		return u;
	}	
}