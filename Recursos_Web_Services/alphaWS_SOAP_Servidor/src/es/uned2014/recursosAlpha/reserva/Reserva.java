package es.uned2014.recursosAlpha.reserva;

import java.text.SimpleDateFormat;
import java.util.*;

import es.uned2014.recursosAlpha.baseDatos.BaseDatos;
import es.uned2014.recursosAlpha.usuario.Usuario;

/**
 * Representa una petici�n o reserva que un usuario ha hecho para un recurso
 * durante un periodo de tiempo.
 * 
 * 
 * @author Alpha UNED 2014
 * @version Recursos 1.0
 * @since Junio-Julio 2014
 */
public class Reserva implements Comparable<Object> {
	private int idReserva;
	private int idUsuario;
	private int idRecurso;
	private Date inicio;
	private Date fin;
	private int idEstado;
	private int idSucursal;
	private String nombreUsuario;
	private String descripcionRecurso;
	private String estado;
	private String sucursal;

	/**
	 * M�todo constructor, inicializa las variables a 0 o String vac�o
	 */
	public Reserva() {
		this.inicializar(); // se inicializan a cero todas las variables
	}

	/**
	 * M�todo constructor, asigna valor a las variables:
	 * 
	 * @param idres
	 *            para idReserva
	 */
	public Reserva(int idres) {
		this.idReserva = idres;
	}

	/**
	 * M�todo constructor, asigna valor a las variables de la tabla Reservas en
	 * BBDD:
	 * 
	 * @param idres
	 *            para idReserva
	 * @param idu
	 *            para idUsuario
	 * @param idrec
	 *            para idRecurso
	 * @param i
	 *            para fecha inicio
	 * @param f
	 *            para fecha fin
	 * @param e
	 *            para idEstado
	 * @param s
	 *            para idSucursal
	 */
	public Reserva(int idres, int idu, int idrec, Date i, Date f, int e, int s) {
		this.inicializar(); // se inicializan a cero todas las variables
		this.idReserva = idres;
		this.idUsuario = idu;
		this.idRecurso = idrec;
		this.inicio = i;
		this.fin = f;
		this.idEstado = e;
		this.idSucursal = s;
	}

	/**
	 * M�todo constructor, asigna valor a las variables:
	 * 
	 * @param idres
	 *            para idReserva
	 * @param idu
	 *            para idUsuario
	 * @param idrec
	 *            para idRecurso
	 * @param i
	 *            para fecha inicio
	 * @param f
	 *            para fecha fin
	 * @param e
	 *            para idEstado
	 * @param s
	 *            para idSucursal
	 * @param usu
	 *            para nombreUsuario
	 * @param rec
	 *            para descripcionRecurso
	 * @param est
	 *            para estado
	 * @param suc
	 *            para sucursal
	 */
	public Reserva(int idres, int idu, int idrec, Date i, Date f, int e, int s,
			String usu, String rec, String est, String suc) {
		this.idReserva = idres;
		this.idUsuario = idu;
		this.idRecurso = idrec;
		this.inicio = i;
		this.fin = f;
		this.idEstado = e;
		this.idSucursal = s;
		this.nombreUsuario = usu;
		this.descripcionRecurso = rec;
		this.estado = est;
		this.sucursal = suc;
	}

	/**
	 * M�todo constructor, asigna valor a las variables idEstado y estado:
	 * 
	 * @param e
	 *            para idEstado
	 * @param est
	 *            para estado
	 */
	public Reserva(int e, String est) {
		this.inicializar(); // se inicializan a cero todas las variables
		this.idEstado = e;
		this.estado = est;
	}

	/**
	 * M�todo constructor, asigna valor a las variables idSucursal y sucursal:
	 * 
	 * @param s
	 *            para idSucursal
	 * @param suc
	 *            para sucursal
	 */
	public Reserva(String suc, int s) {
		this.inicializar(); // se inicializan a cero todas las variables
		this.idSucursal = s;
		this.sucursal = suc;
	}

	/**
	 * Inicializa a cero o "" todas las variables
	 * 
	 * @param null
	 * @return null
	 */
	private void inicializar() {
		this.idReserva = 0;
		this.idUsuario = 0;
		this.idRecurso = 0;
		this.inicio = new Date();
		this.fin = new Date();
		this.idEstado = 0;
		this.idSucursal = 0;
		this.nombreUsuario = "";
		this.descripcionRecurso = "";
		this.estado = "";
		this.sucursal = "";
	}

	/**
	 * Crea un objeto BaseDatos, abre la conexi�n y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta obtiene todas las peticiones
	 * (reservas pendientes) de un usuario.
	 * 
	 * @param usuario
	 * @param pagina
	 * @return array con las peticiones del usuario
	 */
	public ArrayList<Reserva> misPeticiones(String usuario, int pagina) {

		// Se crea un objeto BaseDatos y se abre la conexi�n:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// Se obtiene el id del usuario:
		Usuario u = new Usuario();
		int id = u.getIdUsuario(usuario);

		// solo mostramos 20 resultados por pagina
		int limit = 0;
		if (pagina > 1) {
			limit = (pagina * 20) - 20;
		}

		// String con la consulta:
		String sql = " SELECT * FROM `reservas` AS res "
				+ " LEFT JOIN recursos AS rec ON rec.IdRecurso = res.IdRecurso "
				+ " LEFT JOIN usuarios AS u ON u.IdUsuario = res.IdUsuario "
				+ " LEFT JOIN estados AS e ON e.IdEstado = res.IdEstado "
				+ " LEFT JOIN sucursales AS s ON s.IdSucursal = res.IdSucursal "
				+ " WHERE res.IdEstado = '1' AND res.IdUsuario='" + id + "' "
				+ " ORDER BY	res.Inicio DESC " + " LIMIT " + limit + ",20 ";

		// Se obtiene un array con el resultado:
		ArrayList<Reserva> array = bbdd.consultarReservas(sql);

		// Se cierra la conexi�n a la base de datos:
		bbdd.cerrarConexion();

		return array;
	}

	/**
	 * Crea un objeto BaseDatos, abre la conexi�n y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta obtiene todas las reservas
	 * vigentes de un usuario.
	 * 
	 * @param usuario
	 * @param pagina
	 * @return array con las reservas del usuario
	 */
	public ArrayList<Reserva> misReservas(String usuario, int pagina) {

		// Se crea un objeto BaseDatos y se abre la conexi�n:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// Se obtiene el id del usuario:
		Usuario u = new Usuario();
		int id = u.getIdUsuario(usuario);

		// solo mostramos 20 resultados por pagina
		int limit = 0;
		if (pagina > 1) {
			limit = (pagina * 20) - 20;
		}

		// String con la consulta:
		String sql = " SELECT * FROM `reservas` AS res "
				+ " LEFT JOIN recursos AS rec ON rec.IdRecurso = res.IdRecurso "
				+ " LEFT JOIN usuarios AS u ON u.IdUsuario = res.IdUsuario "
				+ " LEFT JOIN estados AS e ON e.IdEstado = res.IdEstado "
				+ " LEFT JOIN sucursales AS s ON s.IdSucursal = res.IdSucursal "
				+ " WHERE res.IdEstado IN (2,4,6) AND res.IdUsuario='" + id
				+ "' " + " ORDER BY	res.Inicio DESC " + " LIMIT " + limit
				+ ",20 ";

		// Se obtiene un array con el resultado:
		ArrayList<Reserva> array = bbdd.consultarReservas(sql);

		// Se cierra la conexi�n a la base de datos:
		bbdd.cerrarConexion();

		return array;
	}

	/**
	 * Se obtiene el n�mero total de filas de la tabla de reservas
	 * 
	 * @return n�mero de filas
	 */
	public int totalFilasReserva() {
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// String con la consulta
		String sql = " SELECT COUNT(*) FROM `reservas` ";

		// Array con el resultado de la consulta
		int filas = bbdd.getNumeroFilas(sql);

		// Se cierra la conexion
		bbdd.cerrarConexion();
		return filas;
	}

	/**
	 * Se obtiene el n�mero total de filas de la tabla de reservas de un
	 * usuario.
	 * 
	 * @param usuario
	 * @return n�mero de filas de ese usuario
	 * @throws null
	 */
	public int totalFilasReserva(String usuario) {
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// Se obtiene el id del usuario:
		Usuario u = new Usuario();
		int id = u.getIdUsuario(usuario);

		// String con la consulta
		String sql = " SELECT COUNT(*) FROM `reservas` WHERE IdUsuario = '"
				+ id + "' ";

		// Array con el resultado de la consulta
		int filas = bbdd.getNumeroFilas(sql);

		// Se cierra la conexion
		bbdd.cerrarConexion();
		return filas;
	}

	/**
	 * Se obtiene el n�mero total de filas de la tabla de reservas con un estado
	 * concreto.
	 * 
	 * @param idEstado
	 *            que se desea consultar
	 * @return n�mero de filas con ese estado
	 * @throws null
	 */
	public int totalFilasReserva(int idEstado) {
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// String con la consulta: Reservas con IdEstado i
		String sql = " SELECT COUNT(*) FROM `reservas` WHERE IdEstado = '"
				+ idEstado + "' ";

		// Array con el resultado de la consulta
		int filas = bbdd.getNumeroFilas(sql);

		// Se cierra la conexion
		bbdd.cerrarConexion();
		return filas;
	}

	/**
	 * Se obtiene el n�mero total de filas de la tabla de reservas de un usuario
	 * concreto y con un estado concreto.
	 * 
	 * @param idEstado
	 *            que se desea consultar
	 * @param usuario
	 * @return n�mero de filas con ese estado
	 * @throws null
	 */
	public int totalFilasReserva(int idEstado, String usuario) {
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// Se obtiene el id del usuario:
		Usuario u = new Usuario();
		int id = u.getIdUsuario(usuario);

		// String con la consulta: Reservas con IdEstado i
		String sql = " SELECT COUNT(*) FROM `reservas` WHERE IdEstado = '"
				+ idEstado + "' AND IdUsuario = '" + id + "' ";

		// Array con el resultado de la consulta
		int filas = bbdd.getNumeroFilas(sql);

		// Se cierra la conexion
		bbdd.cerrarConexion();
		return filas;
	}

	/**
	 * Crea un objeto BaseDatos, abre la conexi�n y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta obtiene todas las reservas con un
	 * estado concreto.
	 * 
	 * @param pagina
	 * @param i
	 *            que representa los estados de las reservas para el listado. 0
	 *            es para todos los estados.
	 * @return array con todas las reservas
	 */
	public ArrayList<Reserva> reservasListado(int pagina, int i) {

		// Se crea un objeto BaseDatos y se abre la conexi�n:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// solo mostramos 20 resultados por pagina
		int limit = 0;
		if (pagina > 1) {
			limit = (pagina * 20) - 20;
		}

		String sql; // String con la consulta

		if (i == 0) {
			// String con la consulta para TODOS LOS ESTADOS:
			sql = " SELECT * FROM `reservas` AS res "
					+ " LEFT JOIN recursos AS rec ON rec.IdRecurso = res.IdRecurso "
					+ " LEFT JOIN usuarios AS u ON u.IdUsuario = res.IdUsuario "
					+ " LEFT JOIN estados AS e ON e.IdEstado = res.IdEstado "
					+ " LEFT JOIN sucursales AS s ON s.IdSucursal = res.IdSucursal "
					+ " ORDER BY	res.Inicio DESC ";
		} else {
			// String con la consulta para UN ESTADOS:
			sql = " SELECT * FROM `reservas` AS res "
					+ " LEFT JOIN recursos AS rec ON rec.IdRecurso = res.IdRecurso "
					+ " LEFT JOIN usuarios AS u ON u.IdUsuario = res.IdUsuario "
					+ " LEFT JOIN estados AS e ON e.IdEstado = res.IdEstado "
					+ " LEFT JOIN sucursales AS s ON s.IdSucursal = res.IdSucursal "
					+ " WHERE res.IdEstado = " + i
					+ " ORDER BY	res.Inicio DESC ";
		}

		// Si no se ha elegido el listado completo (pagina = 0), se a�ade la
		// paginaci�n:
		if (pagina != 0) {
			sql += " LIMIT " + limit + ",20 ";
		}

		// Se obtiene un array con el resultado:
		ArrayList<Reserva> array = bbdd.consultarReservas(sql);

		// Se cierra la conexi�n a la base de datos:
		bbdd.cerrarConexion();

		return array;
	}

	/**
	 * Crea un objeto BaseDatos, abre la conexi�n y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta obtiene todos los estados
	 * posibles para reservas.
	 * 
	 * @return array con reservas, una por cada estado posible
	 */
	public ArrayList<Reserva> reservasEstados() {
		// Se crea una base de datos y se establece la conexion
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// String con la consulta
		String sql = " SELECT * FROM `estados`";

		// Array con el resultado de la consulta
		ArrayList<Reserva> arrayEstados = bbdd.consultarEstados(sql);

		// Se cierra la conexionz
		bbdd.cerrarConexion();
		return arrayEstados;
	}

	/**
	 * Crea un objeto BaseDatos, abre la conexi�n y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta obtiene todas las sucursales
	 * posibles para reservas.
	 * 
	 * @return array con reservas, una por cada sucursal posible
	 */
	public ArrayList<Reserva> reservasSucursales() {
		// Se crea una base de datos y se establece la conexion
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// String con la consulta
		String sql = " SELECT * FROM `sucursales`";

		// Array con el resultado de la consulta
		ArrayList<Reserva> arrayEstados = bbdd.consultarSucursales(sql);

		// Se cierra la conexionz
		bbdd.cerrarConexion();
		return arrayEstados;
	}

	/**
	 * Crea un objeto BaseDatos, abre la conexi�n y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta crea una fila nueva en la tabla
	 * de reservas.
	 * 
	 * @param usu
	 *            para idUsuario
	 * @param rec
	 *            para idRecurso
	 * @param i
	 *            para fecha inicio
	 * @param f
	 *            para fecha fin
	 * @param est
	 *            para idEstado
	 * @return id de la nueva reserva
	 * @throws null
	 */
	public int nuevo(int usu, int rec, Date i, Date f, int est, int suc) {
		int idUsu = usu;
		int idRec = rec;
		Date inicio = i;
		Date fin = f;
		int idEst = est;
		int idSuc = suc;

		int idNuevo; // id del nuevo Usuario
		Boolean comprobada;

		// Si el estado es 2,4 o 6, consulta para comprobar si la reserva es
		// compatible
		if (idEst == 2 || idEst == 4 || idEst == 6) {
			comprobada = this.comprobarReserva(0, idRec, i, f);
		} else {
			comprobada = true;
		}

		// Si el usuario no existe previamente, se crea. Si ya existe, se envía
		// error.
		if (comprobada) {
			BaseDatos bbdd = new BaseDatos();
			bbdd.abrirConexion();

			String fi = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(inicio);
			String ff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fin);

			// Consulta para crear nueva reserva:
			String sql2 = "INSERT INTO reservas (IdUsuario, IdRecurso, Inicio, Final, IdEstado, IdSucursal) "
					+ " VALUES ('"
					+ idUsu
					+ "', '"
					+ idRec
					+ "', '"
					+ fi
					+ "', '" + ff + "', '" + idEst + "', '" + idSuc + "')";

			// Se realiza la consulta y se recupera el nuevo id:
			idNuevo = bbdd.crear(sql2);

			// Se cierra la conexion
			bbdd.cerrarConexion();
		} else {
			idNuevo = 0;
		}

		return idNuevo;
	}

	/**
	 * Crea un objeto BaseDatos, abre la conexi�n y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta consulta si una reserva es
	 * compatible con las reservas vigentes.
	 * 
	 * @param res
	 *            para idReserva (no tiene que comprobar que sea compatible
	 *            consigo misma)
	 * @param rec
	 *            para idRecurso
	 * @param i
	 *            para fecha inicio
	 * @param f
	 *            para fecha fin
	 * @return booleano true si la reserva es compatible, false si no lo es
	 * @throws null
	 */
	public boolean comprobarReserva(int res, int rec, Date i, Date f) {
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		String fi = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(i);
		String ff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(f);
		String sql1 = "SELECT * FROM `reservas`  WHERE " + " (( DATE('" + fi
				+ "') BETWEEN Inicio AND Final) " + " OR ( '" + ff
				+ "' BETWEEN Inicio AND Final) " + " OR ( Inicio BETWEEN '"
				+ fi + "' AND '" + ff + "') " + " OR ( Final BETWEEN '" + fi
				+ "' AND '" + ff + "')) "
				+ " AND IdEstado IN (2,4,6) AND IdRecurso = " + rec
				+ " AND IdReserva != " + res;
		ArrayList<Reserva> arrayR = bbdd.validarReservas(sql1);

		// System.out.println("sql para comprobar: "+sql1);
		// System.out.println("array de reservas incompatibles: "+arrayR);

		// Se cierra la conexion
		bbdd.cerrarConexion();

		// Si es compatible con las existentes, se devuelve true. Si no lo es,
		// false.
		if (arrayR.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Crea un objeto BaseDatos, abre la conexi�n y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta edita una fila en la tabla de
	 * reservas.
	 * 
	 * @param id
	 *            de la reserva a editar
	 * @param idU
	 *            para id del usuario
	 * @param idRec
	 *            para id del recurso
	 * @param i
	 *            para fecha de inicio
	 * @param f
	 *            para fecha de fin
	 * @param idE
	 *            para id del estado3
	 * @param idS
	 *            para id de la sucursal
	 * @return id de la reserva editada
	 * @throws null
	 */
	public int editarReserva(int id, int idU, int idRec, Date i, Date f,
			int idE, int idS) {
		int idReserva = id;
		int idUsuario = idU;
		int idRecurso = idRec;
		Date inicio = i;
		Date fin = f;
		int idEstado = idE;
		int idSucursal = idS;

		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		int idEditado; // id del Usuario modificado
		boolean comprobada;

		// Consulta para comprobar si la reserva es compatible (estado 2, 4 o 6:
		// vigentes)
		if (idEstado == 2 || idEstado == 4 || idEstado == 6) {
			comprobada = comprobarReserva(idReserva, idRecurso, inicio, fin);
		} else {
			comprobada = true;
		}

		// Si la reserva es compatible, se modifica. Si ya existe, se envía
		// error.
		if (comprobada) {
			String fi = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(inicio);
			String ff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fin);

			// Consulta para editar reserva:
			String sql2 = "UPDATE `reservas` SET `IdUsuario` = '" + idUsuario
					+ "', " + "`IdRecurso` = '" + idRecurso + "', `Inicio` = '"
					+ fi + "', " + "`Final` = '" + ff + "', `IdEstado` = '"
					+ idEstado + "' , " + "`IdSucursal` = '" + idSucursal
					+ "' " + " WHERE `IdReserva` = " + idReserva;

			// Se realiza la modificaci�n y se recupera el nuevo id:
			idEditado = bbdd.editar(idReserva, sql2);
		} else {
			idEditado = 0;
		}

		// Se cierra la conexion
		bbdd.cerrarConexion();

		return idEditado;
	}

	/**
	 * Cambia el estado de una reserva a un estado que no requiera comprobar
	 * compatibilidad
	 * 
	 * @param id
	 *            de la reserva
	 * @param est
	 *            para id de estado (denegar, anular, etc)
	 * @return id de la reserva editada
	 * @throws null
	 */
	public int cambiarEstado(int id, int est) {
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		int idEditado; // id del Usuario modificado

		// Se ejecuta el sql que la modifica:
		String sql = " UPDATE reservas SET IdEstado = '" + est
				+ "' WHERE IdReserva = " + id;
		idEditado = bbdd.editar(id, sql);

		// Se cierra la conexion
		bbdd.cerrarConexion();

		return idEditado;
	}

	/**
	 * Cambia el estado de una reserva a un estado vigente (confirmado, no
	 * anulado, pendiente de anulaci�n), que requiere comprobar compatibilidad.
	 * 
	 * @param idReserva
	 *            de la reserva
	 * @param idRecurso
	 *            para id del Recurso
	 * @param i
	 *            para fecha de inicio
	 * @param f
	 *            para fecha de finalizaci�n
	 * @param est
	 *            para id de estado (cancelar,confirmar, etc)
	 * @return id de la reserva editada
	 * @throws null
	 */
	public int confirmar(int idReserva, int idRecurso, Date i, Date f, int est) {
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		int idEditado; // id del Usuario modificado
		boolean comprobada = comprobarReserva(idReserva, idRecurso, i, f);

		// Si es compatible, se ejecuta el sql que la modifica:
		if (comprobada) {
			String sql = " UPDATE reservas SET IdEstado = '" + est
					+ "' WHERE IdReserva = " + idReserva;
			idEditado = bbdd.editar(idReserva, sql);
		} else {
			idEditado = 0;
		}

		// Se cierra la conexion
		bbdd.cerrarConexion();

		return idEditado;
	}

	/**
	 * Crea un objeto BaseDatos, abre la conexi�n y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta elimina una fila en la tabla de
	 * Reservas de la base se datos.
	 * 
	 * @param i
	 *            para id del elemento a eliminar
	 * @return true si se elimina, false si da error
	 * @throws null
	 */
	public boolean eliminar(int i) {
		int id = i;

		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// Consulta para comprobar si existe nombreUsuario
		String sql = "DELETE FROM `reservas` WHERE `IdReserva` = " + id;

		boolean eliminado = bbdd.eliminar(sql);

		// Se cierra la conexion
		bbdd.cerrarConexion();

		return eliminado;
	}

	/**
	 * Crea un String con la consulta a realizar desde el buscador de reservas
	 * 
	 * @param vista
	 *            de responsable (2) o diferente
	 * @param u
	 *            idUsuario
	 * @param r
	 *            idRecurso
	 * @param iniD
	 *            d�a de inicio
	 * @param iniH
	 *            hora de inicio
	 * @param finD
	 *            d�a de finalizaci�n
	 * @param finH
	 *            hora de finalizaci�n
	 * @param e
	 *            idEstado
	 * @param s
	 *            idSucursal
	 * @return sql de la consulta
	 */
	public String sqlBuscadorReservas(int vista, int u, int r, Date iniD,
			Date iniH, Date finD, Date finH, int e, int s) {

		// Se obtiene una fecha "vacía" para poder comprobar si se han
		// rellenado inicio y/o fin
		// en el formulario:
		Date fechaCero = new Date();
		fechaCero.setTime(0);

		String sql = ""; // String con la consulta

		if (u != 0) {
			// Si el nombreUsuario se ha definido:
			sql += " WHERE res.IdUsuario = " + u;
		} else {
			// Si el nombreUsuario está vacío en el formulario, se busca en
			// todos los nombreUsuario
			// N�mero de usuarios:
			Usuario uB = new Usuario();
			int numUsuarios = uB.totalFilasUsuario();
			ArrayList<Usuario> uTodos = uB.usuariosListado(0);

			sql += " WHERE res.IdUsuario IN ( ";
			for (int i = 0; i < numUsuarios; i++) {
				sql += uTodos.get(i).getIdUsuario() + ", ";
			}
			sql += numUsuarios + ")";
		}

		if (r != 0) {
			// Si el recurso se ha definido:
			sql += " AND res.IdRecurso = " + r;
		}

		if (!iniD.equals(fechaCero) && !iniD.equals(fechaCero)) {
			// Si se ha definido intervalo para la fecha de inicio:
			String diaInicioDesde = new SimpleDateFormat("yyyy-MM-dd")
					.format(iniD);
			String diaInicioHasta = new SimpleDateFormat("yyyy-MM-dd")
					.format(iniH);

			diaInicioDesde += " 00:00:00";
			diaInicioHasta += " 23:59:59";

			sql += " AND ( res.Inicio >= '" + diaInicioDesde
					+ "' AND res.Inicio <= '" + diaInicioHasta + "'  ) ";
		}

		if (!finD.equals(fechaCero) && !finD.equals(fechaCero)) {
			// Si se ha definido intervalo para la fecha final:
			String diaFinalDesde = new SimpleDateFormat("yyyy-MM-dd")
					.format(finD);
			String diaFinalHasta = new SimpleDateFormat("yyyy-MM-dd")
					.format(finH);

			diaFinalDesde += " 00:00:00";
			diaFinalHasta += " 23:59:59";

			sql += " AND ( res.Final >= '" + diaFinalDesde
					+ "' AND res.Final <= '" + diaFinalHasta + "'  ) ";
		}

		// Para un responsable:
		if (vista == 2 && e != 0) {
			// Si se ha definido un estado (para responsables):
			sql += " AND res.IdEstado IN (" + e + ")";
		}

		// Para un no responsable:
		if (vista != 2) {
			// Para no responsables, se buscan estados 2, 4 y 6
			sql += " AND res.IdEstado IN (2, 4, 6)";
		}

		if (s != 0) {
			// Si se ha definido un estado (para responsables):
			sql += " AND res.IdSucursal IN (" + s + ")";
		}

		sql += " ORDER BY	res.Inicio DESC ";

		// System.out.println(sql);
		return sql;
	}

	/**
	 * Crea un objeto BaseDatos, abre la conexi�n y solicita que se ejecute una
	 * consulta antes de cerrar. Esta consulta obtiene todas las reservas que
	 * cumplen los requisitos indicados.
	 * 
	 * @param pagina
	 * @param sql
	 *            de la consulta
	 * @return array con todas las reservas
	 */
	public ArrayList<Reserva> reservasBuscar(int pagina, String sql) {

		// Se crea un objeto BaseDatos y se abre la conexi�n:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// solo mostramos 20 resultados por pagina
		int limit = 0;
		if (pagina > 1) {
			limit = (pagina * 20) - 20;
		}

		// Se adapta el sql para la paginaci�n
		String sql2 = " SELECT * FROM `reservas` AS res"
				+ " LEFT JOIN recursos AS rec ON rec.IdRecurso = res.IdRecurso"
				+ " LEFT JOIN usuarios AS u ON u.IdUsuario = res.IdUsuario"
				+ " LEFT JOIN estados AS e ON e.IdEstado = res.IdEstado"
				+ " LEFT JOIN sucursales AS s ON s.IdSucursal = res.IdSucursal";
		sql2 += sql;
		sql2 += " LIMIT " + limit + ",20 ";

		// System.out.println(sql2);

		// Se obtiene un array con el resultado:
		ArrayList<Reserva> array = bbdd.consultarReservas(sql2);

		// Se cierra la conexi�n a la base de datos:
		bbdd.cerrarConexion();

		return array;
	}

	/**
	 * Se obtiene el n�mero total de filas de la tabla de reservas para una
	 * b�squeda concreta.
	 * 
	 * @param sql
	 *            con la consulta
	 * @return n�mero de filas con ese estado
	 * @throws null
	 */
	public int numeroFilasBuscador(String sql) {
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// Se adapta el sql para que cuente:
		String sqlContar = " SELECT COUNT(*) FROM `reservas` AS res";
		sqlContar += sql;

		// Array con el resultado de la consulta
		int filas = bbdd.getNumeroFilas(sqlContar);

		// Se cierra la conexion
		bbdd.cerrarConexion();
		return filas;
	}

	/**
	 * Se comprueba si el usuario que ha solicitado editar/eliminar una
	 * petici�n/reserva es el due�o
	 * 
	 * @param nombreUsuario
	 * @param idReserva
	 * @return boolean true si es el due�o, false si no lo es
	 */
	public boolean comprobarUsuario(String nombreUsuario, int idReserva) {

		// Se crea un objeto BaseDatos y se abre la conexi�n:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// Se busca si existe un usuario con nombreUsuario y contrasena
		// indicados:
		String sql = " SELECT * FROM `reservas` AS res  "
				+ " LEFT JOIN recursos AS rec ON rec.IdRecurso = res.IdRecurso"
				+ " LEFT JOIN usuarios AS u ON u.IdUsuario = res.IdUsuario"
				+ " LEFT JOIN estados AS e ON e.IdEstado = res.IdEstado"
				+ " LEFT JOIN sucursales AS s ON s.IdSucursal = res.Idsucursal"
				+ " WHERE NombreUsuario='" + nombreUsuario
				+ "' AND IdReserva='" + idReserva + "'";

		ArrayList<Reserva> a = bbdd.consultarReservas(sql);

		// Se cierra la conexi�n a la base de datos:
		bbdd.cerrarConexion();

		// Si la longitud del array es >0, existe esa reserva para ese usuario:
		if (a.size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Representaci�n en String de una Reserva
	 */
	public String toString() {
		String s = "IdReserva: " + this.idReserva + " | IdUsuario: "
				+ this.idUsuario + " | IdRecurso: " + this.idRecurso
				+ " | Inicio: " + this.inicio + " | Fin: " + this.fin
				+ " | IdEstado: " + this.idEstado + " | Usuario: "
				+ this.nombreUsuario + " | Recurso: " + this.descripcionRecurso
				+ " | Estado: " + this.estado + " | Sucursal: " + this.sucursal;

		return s;
	}

	/**
	 * Representaci�n en String de una Fecha
	 */
	public String dateToString(Date d) {
		if (d.getTime() != 0) {
			String s = new SimpleDateFormat("yyyy-MM-dd").format(d);
			return s;
		} else {
			return "";
		}
	}

	/**
	 * Representaci�n en String de una Fecha
	 */
	public String dateCompleteToString(Date d) {
		if (d.getTime() != 0) {
			String s = new SimpleDateFormat("yyyy-MM-dd (HH:mm)").format(d);
			return s;
		} else {
			return "";
		}
	}

	/**
	 * Para comparar dos Recursos, se comparan sus ids y sus fechas de inicio.
	 * Se ordenan por fecha de inicio primero, despu�s por id. Dos reservas con
	 * misma fecha de inicio, se diferencian por su id.
	 */
	public int compareTo(Object o) {
		Reserva r = (Reserva) o;

		int comparaInicio = this.inicio.compareTo(r.getInicio());

		// Para usar el m�todo compareTo, se envuelven los int en Integer:
		Integer idThis = (Integer) this.idReserva;
		Integer idCompare = (Integer) r.idReserva;
		int comparaId = idThis.compareTo(idCompare);

		if (comparaInicio != 0) {
			return comparaInicio;
		} else {
			return comparaId;
		}
	}

	/**
	 * Dos Reservas son iguales si sus ids son iguales.
	 */
	public boolean equals(Object o) {
		Reserva r = (Reserva) o;

		return this.idReserva == r.idReserva;
	}

	/**
	 * @return idReserva
	 */
	public int getIdReserva() {
		return idReserva;
	}

	/**
	 * @param idReserva
	 */
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	/**
	 * @return idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return idRecurso
	 */
	public int getIdRecurso() {
		return idRecurso;
	}

	/**
	 * @param idRecurso
	 */
	public void setIdRecurso(int idRecurso) {
		this.idRecurso = idRecurso;
	}

	/**
	 * @return the inicio
	 */
	public Date getInicio() {
		return inicio;
	}

	public String inicioToString() {
		String s = new SimpleDateFormat("yyyy-MM-dd (HH:mm)").format(inicio);
		return s;
	}

	/**
	 * @param inicio
	 *            the inicio to set
	 */
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	/**
	 * @return the fin
	 */
	public Date getFin() {
		return fin;
	}

	public String finToString() {
		String s = new SimpleDateFormat("yyyy-MM-dd (HH:mm)").format(fin);
		return s;
	}

	/**
	 * @param fin
	 *            the fin to set
	 */
	public void setFin(Date fin) {
		this.fin = fin;
	}

	/**
	 * @return estado
	 */
	public int getIdEstado() {
		return idEstado;
	}

	/**
	 * @return estado
	 */
	public int getIdEstado(String s) {
		// Se crea un objeto BaseDatos y se abre la conexi�n:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// Se busca si existe un estado con el string indicado:
		ArrayList<Reserva> a = bbdd
				.consultarEstados("SELECT * FROM estados WHERE Estado='" + s
						+ "'");

		// Se cierra la conexi�n a la base de datos:
		bbdd.cerrarConexion();

		return a.get(0).getIdEstado();
	}

	/**
	 * @param idEstado
	 */
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	/**
	 * @return the idSucursal
	 */
	public int getIdSucursal() {
		return idSucursal;
	}

	/**
	 * @param idSucursal
	 *            the idSucursal to set
	 */
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
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
	 * @return the descripcionRecurso
	 */
	public String getDescripcionRecurso() {
		return descripcionRecurso;
	}

	/**
	 * @param descripcionRecurso
	 *            the descripcionRecurso to set
	 */
	public void setDescripcionRecurso(String descripcionRecurso) {
		this.descripcionRecurso = descripcionRecurso;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

}
