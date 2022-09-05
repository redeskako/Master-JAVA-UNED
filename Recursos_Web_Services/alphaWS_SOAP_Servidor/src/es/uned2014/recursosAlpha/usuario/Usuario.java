package es.uned2014.recursosAlpha.usuario;

import java.util.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import es.uned2014.recursosAlpha.baseDatos.*;
import es.uned2014.recursosAlpha.excepciones.ExcepcionRecursos;

/**
 * Representa un usuario que puede acceder o no a la aplicación.
 * 
 * @author Alpha UNED 2014
 * @version Recursos 1.0
 * @since Junio 2014
 */
public class Usuario implements Comparable<Object> {

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
	public Usuario() {
		this.idUsuario = 0;
		this.nombreUsuario = "";
		this.nombre = "";
		this.apellidos = "";
		this.contrasena = "";
		this.idRol = 0;
		this.rol = "";
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
	 * @param idr
	 *            para idRol
	 */
	public Usuario(int id, String nu, String n, String a, String c, int idr) {
		this.idUsuario = id;
		this.nombreUsuario = nu;
		this.nombre = n;
		this.apellidos = a;
		this.contrasena = c;
		this.idRol = idr;
		this.rol = "";
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
	 * @param idr
	 *            para idRol
	 * @param r
	 *            para rol
	 */
	public Usuario(int id, String nu, String n, String a, String c, int idr,
			String r) {
		this.idUsuario = id;
		this.nombreUsuario = nu;
		this.nombre = n;
		this.apellidos = a;
		this.contrasena = c;
		this.idRol = idr;
		this.rol = r;
	}

	/**
	 * Método constructor, asigna valor a las variables:
	 * 
	 * @param idr
	 *            para idRol
	 * @param r
	 *            para rol
	 */
	public Usuario(int idr, String r) {
		this.idRol = idr;
		this.rol = r;
	}

	/**
	 * Método constructor, asigna valor sólo a nombreUsuario:
	 * 
	 * @param s
	 *            para nombreUsuario
	 */
	public Usuario(String s) {
		this.nombreUsuario = s;
	}

	/**
	 * Se encripta la contraseña para que en caso de acceso a la base de datos,
	 * no se tenga acceso a las contraseñas de los usuarios.
	 * 
	 * @param s
	 *            String con la contraseña original
	 * @return String con la contraseña encriptada
	 */
	public String trasformarContrasena(String s) {

		String salt = "PracticaDelEquipoAlpha@#$!%^&*(*)1234567890";
		String contrasenaOriginal = s;

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
			md.update((contrasenaOriginal + salt).getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new ExcepcionRecursos(e.getMessage());
		}
		String contrasenaFinal = (new BigInteger(md.digest())).toString(16);

		return contrasenaFinal;
	}

	/**
	 * Método que comprueba si una pareja de usuario y contraseña existe en la
	 * base de datos. Si existe, devuelve el usuario. Si no, devuelve null.
	 * 
	 * @param u
	 *            String con el nombre de usuario
	 * @param c
	 *            String con la contraseña sin encriptar
	 */
	public Usuario validarUsuario(String u, String c) {
		Usuario usuario = null;

		// Se crea un objeto BaseDatos y se abre la conexión:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// Se busca si existe un usuario con nombreUsuario y contrasena
		// indicados:
		String sql = " SELECT * FROM `usuarios` AS usu  "
				+ " LEFT JOIN roles AS rol ON rol.IdRol = usu.IdRol "
				+ " WHERE NombreUsuario='" + u + "' AND Contrasena='"
				+ this.trasformarContrasena(c) + "'";
		ArrayList<Usuario> arrayUsuarios = bbdd.consultarUsuarios(sql);
		if (arrayUsuarios.size() > 0) {
			usuario = arrayUsuarios.get(0);
		}

		// Se cierra la conexión a la base de datos:
		bbdd.cerrarConexion();

		// Se devuelve el usuario encontrado (null si no se encuentra ninguno)
		return usuario;

	}

	/**
	 * Crea un objeto BaseDatos, abre la conexión y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta obtiene todos los usuarios.
	 * 
	 * @param pagina
	 * @return array con todos los usuarios
	 */
	public ArrayList<Usuario> usuariosListado(int pagina) {
		// Se crea una base de datos y se establece la conexion
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		String sql;

		if (pagina != 0) {
			// solo mostramos 20 resultados por pagina
			int limit = 0;
			if (pagina > 1) {
				limit = (pagina * 20) - 20;
			}

			// String con la consulta
			sql = " SELECT * FROM `usuarios` AS usu "
					+ " LEFT JOIN roles AS rol ON rol.IdRol = usu.IdRol "
					+ " ORDER BY NombreUsuario ASC " + " LIMIT " + limit
					+ ",20 ";
		} else {
			// si pagina es 0, se muestran todos los resultados en un array
			sql = " SELECT * FROM `usuarios` AS usu "
					+ " LEFT JOIN roles AS rol ON rol.IdRol = usu.IdRol "
					+ " ORDER BY NombreUsuario ASC ";
		}

		// Array con el resultado de la consulta
		ArrayList<Usuario> arrayUsuario = bbdd.consultarUsuarios(sql);

		// Se cierra la conexionz
		bbdd.cerrarConexion();
		return arrayUsuario;

	} // fin usuariosListado

	/**
	 * Crea un objeto BaseDatos, abre la conexión y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta obtiene todos los roles posibles
	 * para usuarios.
	 * 
	 * @return array con usuarios estándar, uno por cada rol posible
	 */
	public ArrayList<Usuario> usuariosRoles() {
		// Se crea una base de datos y se establece la conexion
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// String con la consulta
		String sql = " SELECT * FROM `roles`";

		// Array con el resultado de la consulta
		ArrayList<Usuario> arrayRoles = bbdd.consultarRoles(sql);

		// Se cierra la conexionz
		bbdd.cerrarConexion();
		return arrayRoles;

	}

	/**
	 * Se obtiene el número total de filas de la tabla de usuarios
	 * 
	 * @return número de filas
	 * @throws null
	 */
	public int totalFilasUsuario() {
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// String con la consulta
		String sql = " SELECT COUNT(*) FROM `usuarios` ";

		// Array con el resultado de la consulta
		int filas = bbdd.getNumeroFilas(sql);

		// Se cierra la conexion
		bbdd.cerrarConexion();
		return filas;
	}

	/**
	 * Crea un objeto BaseDatos, abre la conexión y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta crea una fila nueva en la tabla
	 * de usuarios.
	 * 
	 * @param nu
	 *            para el nombreUsuario
	 * @param n
	 *            para el nombre
	 * @param a
	 *            para los apellidos
	 * @param c
	 *            para la contraseña, que se encripta previamente
	 * @param i
	 *            para el idRol
	 * @return id del nuevo usuario
	 * @throws Si
	 *             no se puede crear el nuevo usuario, se lanza error
	 */
	public int nuevo(String nu, String n, String a, String c, int i) {
		String nombreUsuario = nu;
		String nombre = n;
		String apellidos = a;
		String contrasena = this.trasformarContrasena(c);
		int idRol = i;

		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		int idNuevo; // id del nuevo Usuario

		// Consulta para comprobar si existe nombreUsuario
		String sql1 = " SELECT * FROM `usuarios` AS usu  LEFT JOIN roles AS rol ON rol.IdRol = usu.IdRol "
				+ " WHERE NombreUsuario='" + nu + "'";

		ArrayList<Usuario> arrayU = bbdd.consultarUsuarios(sql1);

		// Si el usuario no existe previamente, se crea. Si ya existe, se envía
		// error.
		if (arrayU.isEmpty()) {
			// Consulta para crear nuevo usuario:
			String sql2 = "INSERT INTO usuarios (NombreUsuario, Nombre, Apellidos, Contrasena, IdRol) "
					+ " VALUES ('"
					+ nombreUsuario
					+ "', '"
					+ nombre
					+ "', '"
					+ apellidos + "', '" + contrasena + "', " + idRol + ")";

			// Se realiza la consulta y se recupera el nuevo id:
			idNuevo = bbdd.crear(sql2);
		} else {
			idNuevo = 0;
		}

		// Se cierra la conexion
		bbdd.cerrarConexion();

		return idNuevo;
	}

	/**
	 * Crea un objeto BaseDatos, abre la conexión y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta edita una fila en la tabla de
	 * usuarios.
	 * 
	 * @param id
	 *            del usuario a editar
	 * @param nu
	 *            para el nombreUsuario
	 * @param n
	 *            para el nombre
	 * @param a
	 *            para los apellidos
	 * @param c
	 *            para la contraseña, que se encripta previamente
	 * @param i
	 *            para el idRol
	 * @return id del usuario editado
	 * @throws null
	 */
	public int editarUsuario(int id, String nu, String n, String a, String c,
			int i) {
		int idUsuario = id;
		String nombreUsuario = nu;
		String nombre = n;
		String apellidos = a;
		String contrasena = this.trasformarContrasena(c);
		int idRol = i;

		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		int idEditado; // id del Usuario modificado

		// Consulta para comprobar si existe nombreUsuario
		String sql1 = " SELECT * FROM `usuarios` AS usu  LEFT JOIN roles AS rol ON rol.IdRol = usu.IdRol "
				+ " WHERE NombreUsuario = '"
				+ nombreUsuario
				+ "' AND IdUsuario != " + idUsuario;

		ArrayList<Usuario> arrayU = bbdd.consultarUsuarios(sql1);

		// Si el usuario no existe previamente, se modifica. Si ya existe, se
		// envía error.
		if (arrayU.isEmpty()) {
			// Consulta para editar usuario:
			String sql2 = "UPDATE `usuarios` SET `NombreUsuario` = '"
					+ nombreUsuario + "', " + "`Nombre` = '" + nombre
					+ "', `Apellidos` = '" + apellidos + "', "
					+ "`Contrasena` = '" + contrasena + "', `IdRol` = '"
					+ idRol + "' " + "WHERE `usuarios`.`IdUsuario` = "
					+ idUsuario;

			// Se realiza la modificación y se recupera el nuevo id:
			idEditado = bbdd.editar(idUsuario, sql2);
		} else {
			idEditado = 0;
		}

		// Se cierra la conexion
		bbdd.cerrarConexion();

		return idEditado;
	}

	/**
	 * Crea un objeto BaseDatos, abre la conexión y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta elimina una fila en una tabla de
	 * la Base se datos.
	 * 
	 * @param i
	 *            id del elemento a eliminar
	 * @return true si se elimina, false si da error
	 * @throws null
	 */
	public boolean eliminar(int i) {
		int id = i;

		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// Consulta para eliminar todas las reservas que tengan este usuario:
		String sql1 = "DELETE FROM `reservas` WHERE `IdUsuario` = " + id;
		bbdd.eliminar(sql1);

		// Consulta para eliminar el usuario
		String sql = "DELETE FROM `usuarios` WHERE `IdUsuario` = " + id;

		boolean eliminado = bbdd.eliminar(sql);

		// Se cierra la conexion
		bbdd.cerrarConexion();

		return eliminado;
	}

	/**
	 * Crea un String con la consulta a realizar desde el buscador de usuarios
	 * 
	 * @param nU
	 *            NombreUsuario
	 * @param n
	 *            Nombre
	 * @param a
	 *            Apellidos
	 * @param r
	 *            Rol
	 * @return sql de la consulta
	 */
	public String sqlBuscadorUsuarios(String nU, String n, String a, String r) {

		String sql = "";

		// Si el nombreUsuario no está vacío en el formulario, se busca ese
		// nombreUsuario
		if (!nU.equals("")) {
			sql += " WHERE usu.nombreUsuario LIKE '%" + nU + "%'";
		} else {
			// Si el nombreUsuario está vacío en el formulario, se busca en
			// todos los nombreUsuario
			// Número de usuarios:
			Usuario uB = new Usuario();
			int numUsuarios = uB.totalFilasUsuario();
			ArrayList<Usuario> uTodos = uB.usuariosListado(0);

			sql += " WHERE usu.IdUsuario IN ( ";
			for (int i = 0; i < numUsuarios; i++) {
				sql += uTodos.get(i).getIdUsuario() + ", ";
			}
			sql += numUsuarios + ")";
		}

		// Busca coicidencias en el Nombre
		if (!n.equals("")) {
			sql += " AND Nombre LIKE '%" + n + "%'";
		}

		// Busca coincidencias en los Apellidos
		if (!a.equals("")) {
			sql += " AND Apellidos LIKE '%" + a + "%'";
		}

		// Busca en el IdRol
		if (!r.equals("0")) {
			sql += " AND usu.IdRol='" + r + "'";
		}

		return sql;
	} // fin sqlBuscadorUsuarios

	/**
	 * Crea un objeto BaseDatos, abre la conexión y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta obtiene todas las usuarios que
	 * cumplen los requisitos indicados.
	 * 
	 * @param pagina
	 * @param sql
	 *            de la consulta
	 * @return array de todos los usuarios
	 */
	public ArrayList<Usuario> usuariosBuscar(int pagina, String sql) {

		// Se crea un objeto BaseDatos y se abre la conexión:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// Solo mostramos 20 resultados por pagina
		int limit = 0;
		if (pagina > 1) {
			limit = (pagina * 20) - 20;
		} // fin if

		// Se adapta el sql para la paginación
		String sql2 = "SELECT * FROM `usuarios` AS usu "
				+ " LEFT JOIN roles AS rol ON rol.IdRol = usu.IdRol " + sql
				+ " LIMIT " + limit + ",20 ";

		// Se obtiene un array con el resultado
		ArrayList<Usuario> array = bbdd.consultarUsuarios(sql2);

		// Se cierra la conexion
		bbdd.cerrarConexion();

		// System.out.println(sql2);

		return array;

	} // fin usuariosBuscar

	/**
	 * Se obtiene el número total de filas de la tabla de usuarios para una
	 * búsqueda concreta
	 * 
	 * @param sql
	 *            con la consulta
	 * @return número de filas con ese estado
	 * @throws null
	 */
	public int numeroFilasBuscador(String sql) {

		// Se crea un objeto BaseDatos y se abre la conexión:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// Se adapta el sql para que cuente:
		String sqlContar = " SELECT COUNT(*) FROM `usuarios` AS usu";
		sqlContar += sql;
		// System.out.println(sqlContar);

		// Array con el resultado de la consulta
		int filas = bbdd.getNumeroFilas(sqlContar);

		// Se cierra la conexion
		bbdd.cerrarConexion();

		return filas;
	} // fin numeroFilasBuscador

	/**
	 * Representación en String de un Usuario
	 */
	public String toString() {
		String s = "IdUsuario: " + this.idUsuario + " | NombreUsuario: "
				+ this.nombreUsuario + " | Nombre: " + this.nombre
				+ " | Apellidos: " + this.apellidos + " | IdRol: " + this.idRol
				+ " | Rol: " + this.rol;
		return s;
	} // fin toString

	/**
	 * Para comparar dos Usuarios, se comparan sus nombreUsuario, ignorando las
	 * mayúsculas.
	 */
	public int compareTo(Object o) {
		Usuario u = (Usuario) o;
		return this.nombreUsuario.compareToIgnoreCase(u.nombreUsuario);
	}

	/**
	 * Dos Usuarios son iguales si sus nombreUsuario son iguales, ignorando las
	 * mayúsculas.
	 */
	public boolean equals(Object o) {
		Usuario u = (Usuario) o;
		return this.nombreUsuario.equalsIgnoreCase(u.nombreUsuario);
	}

	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @return the nombreUsuario
	 */
	public int getIdUsuario(String s) {
		// Se crea un objeto BaseDatos y se abre la conexión:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// Se busca si existe un usuario con nombreUsuario y Contrasena
		// indicados:
		String sql = " SELECT * FROM `usuarios` AS usu  LEFT JOIN roles AS rol ON rol.IdRol = usu.IdRol "
				+ "WHERE NombreUsuario='" + s + "'";
		ArrayList<Usuario> arrayUsuarios = bbdd.consultarUsuarios(sql);
		Usuario usuario = arrayUsuarios.get(0);

		// Se cierra la conexión a la base de datos:
		bbdd.cerrarConexion();
		return usuario.idUsuario;
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
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario(int i) {
		// Se crea un objeto BaseDatos y se abre la conexión:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// Se busca si existe un usuario con el id indicado:
		String sql = " SELECT * FROM `usuarios` AS usu  LEFT JOIN roles AS rol ON rol.IdRol = usu.IdRol "
				+ " WHERE IdUsuario='" + i + "'";
		ArrayList<Usuario> arrayUsuarios = bbdd.consultarUsuarios(sql);
		Usuario usuario = arrayUsuarios.get(0);

		// Se cierra la conexión a la base de datos:
		bbdd.cerrarConexion();
		return usuario.nombreUsuario;
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

	/**
	 * @return the idRol
	 */
	public int getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol
	 *            the idRol to set
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
	 * @param rol
	 *            the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

}
