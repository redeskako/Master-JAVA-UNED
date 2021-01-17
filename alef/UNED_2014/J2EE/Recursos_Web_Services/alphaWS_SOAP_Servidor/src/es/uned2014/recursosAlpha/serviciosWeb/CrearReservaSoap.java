package es.uned2014.recursosAlpha.serviciosWeb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import es.uned2014.recursosAlpha.recurso.Recurso;
import es.uned2014.recursosAlpha.reserva.Reserva;
import es.uned2014.recursosAlpha.usuario.Usuario;

/**
 * Servicio Web para la creación de una nueva reserva con SOAP.
 * 
 * @author Alpha UNED 2014
 * @version Recursos Servicio Web 1.0
 * @since Octubre 2014
 */
public class CrearReservaSoap {

	private int idNuevo;
	private ArrayList<Usuario> arrayUsuarios;
	private ArrayList<Recurso> arrayRecursos;
	private ArrayList<Reserva> arrayEstados;
	private ArrayList<Reserva> arraySucursales;

	/**
	 * Constructor. Se inicializan parámetros.
	 */
	public CrearReservaSoap() {
		this.idNuevo = 0;
		this.arrayUsuarios = new ArrayList<Usuario>();
		this.arrayRecursos = new ArrayList<Recurso>();
		this.arrayEstados = new ArrayList<Reserva>();
		this.arraySucursales = new ArrayList<Reserva>();
	}

	/**
	 * Crea una nueva reserva con todos los datos necesarios.
	 * 
	 * @param idUsuario
	 * @param idRecurso
	 * @param diaInicioSt
	 * @param horaInicioSt
	 * @param minInicioSt
	 * @param diaFinalSt
	 * @param horaFinalSt
	 * @param minFinalSt
	 * @param idEstado
	 * @param idSucursal
	 * @return String con el código HTML informativo o de error (por
	 *         incompatibilidad)
	 */
	public int crearReserva(int idUsuario, int idRecurso, String diaInicioSt,
			String horaInicioSt, String minInicioSt, String diaFinalSt,
			String horaFinalSt, String minFinalSt, int idEstado, int idSucursal) {

		int usuId = idUsuario;
		int recId = idRecurso;
		String inicioSt = diaInicioSt + " " + horaInicioSt + ":" + minInicioSt + ":00";
		String finSt = diaFinalSt + " " + horaFinalSt + ":" + minFinalSt + ":00";
		int estadoId = idEstado;
		int sucursalId = idSucursal;

		Reserva r = new Reserva();
		Usuario u = new Usuario();

		// Se transforman las fechas:
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date inicio = formato.parse(inicioSt);
			Date fin = formato.parse(finSt);

			// Se crea la nueva reserva y se recupera su id:
			idNuevo = r.nuevo(usuId, recId, inicio, fin, estadoId, sucursalId);

		} catch (ParseException e) {
			idNuevo = 0;
			e.printStackTrace();
		}

		// Si el nuevo id es 0, ha habido un error al crear la reserva. Si
		// no, todo ha ido bien.
		if (idNuevo != 0) {
			String n = u.getNombreUsuario(usuId);
			System.out.println("Se ha creado una nueva reserva con ID "
					+ idNuevo + " del usuario '" + n + "'");
		} else {
			System.out.println("ERROR al crear una reserva");
		}

		return idNuevo;

	}

	/**
	 * Método para la obtención del listado de todos los Usuarios.
	 * 
	 * @return ArrayList<Usuario> con el listado de todos los Usuarios
	 */
	public ArrayList<Usuario> comboUsuarios() {
		Usuario u = new Usuario();
		return u.usuariosListado(0);
	}

	/**
	 * Método para la obtención del listado de todos los Recursos.
	 * 
	 * @return ArrayList<Recurso> con el listado de todos los Recursos
	 */
	public ArrayList<Recurso> comboRecursos() {
		Recurso r = new Recurso();
		return r.recursoListado(0);
	}

	/**
	 * Método para la obtención del listado de todos los Estados.
	 * 
	 * @return ArrayList<Reserva> con el listado de todos los Estados de Reserva
	 */
	public ArrayList<Reserva> comboEstados() {
		Reserva e = new Reserva();
		return e.reservasEstados();
	}

	/**
	 * Método para la obtención del listado de todas las Sucursales.
	 * 
	 * @return ArrayList<Reserva> con el listado de todas las Sucursales de
	 *         Reserva
	 */
	public ArrayList<Reserva> comboSucursales() {
		Reserva s = new Reserva();
		return s.reservasSucursales();
	}

	/**
	 * @return the arrayUsuarios
	 */
	public ArrayList<Usuario> getArrayUsuarios() {
		return arrayUsuarios;
	}

	/**
	 * @param arrayUsuarios
	 *            the arrayUsuarios to set
	 */
	public void setArrayUsuarios(ArrayList<Usuario> arrayUsuarios) {
		this.arrayUsuarios = arrayUsuarios;
	}

	/**
	 * @return the arrayRecursos
	 */
	public ArrayList<Recurso> getArrayRecursos() {
		return arrayRecursos;
	}

	/**
	 * @param arrayRecursos
	 *            the arrayRecursos to set
	 */
	public void setArrayRecursos(ArrayList<Recurso> arrayRecursos) {
		this.arrayRecursos = arrayRecursos;
	}

	/**
	 * @return the arrayEstados
	 */
	public ArrayList<Reserva> getArrayEstados() {
		return arrayEstados;
	}

	/**
	 * @param arrayEstados
	 *            the arrayEstados to set
	 */
	public void setArrayEstados(ArrayList<Reserva> arrayEstados) {
		this.arrayEstados = arrayEstados;
	}

	/**
	 * @return the arraySucursales
	 */
	public ArrayList<Reserva> getArraySucursales() {
		return arraySucursales;
	}

	/**
	 * @param arraySucursales
	 *            the arraySucursales to set
	 */
	public void setArraySucursales(ArrayList<Reserva> arraySucursales) {
		this.arraySucursales = arraySucursales;
	}

	/**
	 * @return the idNuevo
	 */
	public int getIdNuevo() {
		return idNuevo;
	}

	/**
	 * @param idNuevo
	 *            the idNuevo to set
	 */
	public void setIdNuevo(int idNuevo) {
		this.idNuevo = idNuevo;
	}

}
