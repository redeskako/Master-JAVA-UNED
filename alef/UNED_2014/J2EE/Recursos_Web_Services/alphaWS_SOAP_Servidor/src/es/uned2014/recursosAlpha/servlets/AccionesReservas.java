package es.uned2014.recursosAlpha.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import es.uned2014.recursosAlpha.recurso.Recurso;
import es.uned2014.recursosAlpha.reserva.*;
import es.uned2014.recursosAlpha.usuario.Usuario;

/**
 * Servlet implementation class AccionesReservas Gestiona las acciones a
 * realizar relacionadas con las reservas en función del parámetro "tipo" del
 * request: - Listados - Crear - Editar/Eliminar
 * 
 * @author Alpha UNED 2014
 * @version Recursos 1.0
 * @since Junio 2014
 */
public class AccionesReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Como método constructor se utiliza el de la clase HttpServlet
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public AccionesReservas() {
		super();
	}

	/**
	 * Método en el que se describen las acciones a realizar relacionadas con
	 * Reservas (Petición tipo Get)
	 * 
	 * @param request
	 * @param response
	 * @return null
	 * @throws ServletException
	 *             , IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String tipo = request.getParameter("tipo"); // Listado, crear, editar...

		String p = request.getParameter("p"); // paginación

		String idNuevoS = request.getParameter("idNuevo");
		String idEditadoS = request.getParameter("idEditado");
		String eliminadoS = request.getParameter("eliminado");

		String idEditarS = request.getParameter("idEditar");
		String idEliminarS = request.getParameter("idEliminar");

		String nombre = request.getParameter("nombre");

		HttpSession session = request.getSession();
		Integer vistaSesion = (Integer) session.getAttribute("vistaSesion");

		// Paginación: por defecto, pag 1. Si la sesión tiene asociada otra
		// página ("p"), se asigna ese valor
		Integer pagina = 1;
		if (p != null) {
			pagina = Integer.parseInt(p);
		}

		// Se crea un objeto Reserva, Usuario y Recurso:
		Reserva r = new Reserva();
		Usuario u = new Usuario();
		Recurso rec = new Recurso();

		// SE REALIZAN ACCIONES EN FUNCIÃ“N DEL TIPO DE PETICIÃ“N QUE SE HACE AL
		// SERVLET:
		switch (tipo) {

		// Si la acción es ver el listado:
		case "listado":
			// Se obtiene el array con el contenido del listado:
			ArrayList<Reserva> arrayReserva = r.reservasListado(pagina, 0);

			// Se obtiene el nÃºmero de finas del listado (para paginación):
			int numFilas = r.totalFilasReserva();

			// Se aÃ±ade el array al request:
			request.setAttribute("reservasListado", arrayReserva);
			request.setAttribute("numFilas", numFilas);

			// Se re-dirige a la página jsp:
			RequestDispatcher rd1 = request
					.getRequestDispatcher("reservasListado.jsp");
			rd1.forward(request, response);

			break;

		// Si se ha pulsado en el menu la opcion: crear reserva
		case "menuCrear":
			// Se obtiene los distintos usuarios para el formulario
			ArrayList<Usuario> arrayUsuario = u.usuariosListado(0);
			request.setAttribute("usuariosListado", arrayUsuario);

			// Se obtienen los recuros disponibles
			ArrayList<Recurso> arrayRecurso = rec.recursoListado(0);
			request.setAttribute("recursosListado", arrayRecurso);

			// Se obtienen los estados posibles para la reserva
			ArrayList<Reserva> arrayEstados = r.reservasEstados();
			request.setAttribute("estadosListado", arrayEstados);

			// Se obtienen las sucursaless posibles para la reserva
			ArrayList<Reserva> arraySucursales = r.reservasSucursales();
			request.setAttribute("sucursalesListado", arraySucursales);

			// Se re-dirige a la página jsp:
			RequestDispatcher rd2 = request
					.getRequestDispatcher("reservasCrear.jsp?idNuevo="
							+ idNuevoS);
			rd2.forward(request, response);

			break;

		// Si ha rellenado el formulario: crear reserva
		case "crear":
			// Se recuperan los valores del formulario:
			int usuId = Integer.parseInt(request.getParameter("usuario"));
			int recId = Integer.parseInt(request.getParameter("recurso"));
			String inicioSt = request.getParameter("inicio") + " "
					+ request.getParameter("horaInicio") + ":"
					+ request.getParameter("minutoInicio") + ":00";
			String finSt = request.getParameter("final") + " "
					+ request.getParameter("horaFinal") + ":"
					+ request.getParameter("minutoFinal") + ":00";
			int estadoId = Integer.parseInt(request.getParameter("estado"));
			int sucursalId = Integer.parseInt(request.getParameter("sucursal"));

			int idNuevo;

			// Se transforman las fechas:
			SimpleDateFormat formato = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

			try {
				Date inicio = formato.parse(inicioSt);
				Date fin = formato.parse(finSt);

				// Se crea la nueva reserva y se recupera su id:
				idNuevo = r.nuevo(usuId, recId, inicio, fin, estadoId,
						sucursalId);

			} catch (ParseException e) {
				idNuevo = 0;
				e.printStackTrace();
			}

			// Si el nuevo id es 0, ha habido un error al crear la reserva. Si
			// no, todo ha ido bien.
			if (idNuevo != 0) {
				String n = u.getNombreUsuario(usuId);
				response.sendRedirect("./AccionesReservas?tipo=menuCrear&idNuevo="
						+ idNuevo + "&nombre=" + n);
				System.out.println("Se ha creado una nueva reserva con ID "
						+ idNuevo + " y hecho por '" + n + "'");
			} else {
				response.sendRedirect("./AccionesReservas?tipo=menuCrear&idNuevo=0");
				System.out.println("ERROR al crear una reserva");
			}

			break;

		// Si se ha pulsado en el menÃº: editar/eliminar reserva
		case "menuEditar":

			// Si se ha solicitado la edición de un elemento, se cargan datos al
			// request:
			if (idEditarS != null) {
				request.setAttribute("idEditar", idEditarS);

				// Para recargar el formulario, hay que volver a cargar los
				// datos para los combos:
				// Se obtiene los distintos usuarios para el formulario
				ArrayList<Usuario> arrayUsuario2 = u.usuariosListado(0);
				request.setAttribute("usuariosListadoCompleto", arrayUsuario2);

				// Se obtienen los recuros disponibles
				ArrayList<Recurso> arrayRecurso2 = rec.recursoListado(0);
				request.setAttribute("recursosListadoCompleto", arrayRecurso2);

				// Se obtienen los estados posibles para la reserva
				ArrayList<Reserva> arrayEstados2 = r.reservasEstados();
				request.setAttribute("estadosListado", arrayEstados2);
			}

			// Si se ha solicitado la eliminación de un elemento, se cargan
			// datos al request:
			if (idEliminarS != null) {
				request.setAttribute("idEliminar", idEliminarS);
				request.setAttribute("nombre", nombre);
			}

			// Se obtiene el array con loas reservas:
			ArrayList<Reserva> arrayReservas = r.reservasListado(pagina, 0);
			ArrayList<Reserva> arrayReservaTotal = r.reservasListado(0, 0);

			// Se obtiene el nÃºmero de finas del listado (para paginación):
			int numFilas2 = r.totalFilasReserva();

			// Se aÃ±ade el array a la variable de sesión:
			request.setAttribute("reservasListado", arrayReservas);
			request.setAttribute("numFilas", numFilas2);
			request.setAttribute("reservasListadoCompleto", arrayReservaTotal);

			// Se re-dirige a la página jsp:
			RequestDispatcher rd3 = request
					.getRequestDispatcher("reservasEditar.jsp?idEditado="
							+ idEditadoS + "&eliminado=" + eliminadoS
							+ "&nombre=" + nombre);
			rd3.forward(request, response);

			break;

		// Si se ha pulsado el botón: editar reserva
		case "botonEditar":
			// Se obtienen los datos del request:
			String idListaEditar = request.getParameter("id");

			// Se re-dirige:
			response.sendRedirect("./AccionesReservas?tipo=menuEditar&idEditar="
					+ idListaEditar);

			break;

		// Si se ha rellenado el formulario: editar reserva
		case "editar":
			// Se recuperan los valores del formulario:
			int usuIdEdit = Integer.parseInt(request.getParameter("usuario"));
			int recIdEdit = Integer.parseInt(request.getParameter("recurso"));
			String inicioStEdit = request.getParameter("inicio") + " "
					+ request.getParameter("horaInicio") + ":"
					+ request.getParameter("minutoInicio") + ":00";
			String finStEdit = request.getParameter("final") + " "
					+ request.getParameter("horaFinal") + ":"
					+ request.getParameter("minutoFinal") + ":00";
			int estadoIdEdit = Integer.parseInt(request.getParameter("estado"));
			int idReserva = Integer.parseInt(request.getParameter("idReserva"));
			int sucursalIdEdit = Integer.parseInt(request
					.getParameter("sucursal"));

			int idEditado = 0; // Id de la reserva editada

			// Se transforman las fechas:
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			try {
				Date inicioEdit = df.parse(inicioStEdit);
				Date finEdit = df.parse(finStEdit);

				// Se edita la reserva y se recupera su id:
				idEditado = r.editarReserva(idReserva, usuIdEdit, recIdEdit,
						inicioEdit, finEdit, estadoIdEdit, sucursalIdEdit);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			// Si el nuevo id es 0, ha habido un error al editar. Si no, todo ha
			// ido bien.
			if (idEditado != 0) {
				response.sendRedirect("./AccionesReservas?tipo=menuEditar&nombre="
						+ nombre + "&idEditado=" + idEditado);
				System.out.println("Se ha actualizado una reserva con ID "
						+ idEditado);
			} else {
				response.sendRedirect("./AccionesReservas?tipo=menuEditar&idEditado=0");
				System.out.println("ERROR actualizando reserva con ID "
						+ idReserva);
			}

			break;

		// Si se ha pulsado el botón: eliminar reserva
		case "botonEliminar":
			// Se obtienen los datos del request:
			String idListaEliminar = request.getParameter("id");

			// Se re-dirige:
			response.sendRedirect("./AccionesReservas?tipo=menuEditar&idEliminar="
					+ idListaEliminar + "&nombre=" + nombre);

			break;

		// Si se ha confirmado la eliminación de una reserva
		case "eliminar":
			// Se obtienen los datos del request:
			int idEliminar = Integer.parseInt(request.getParameter("id"));

			// Se elimina la reserva:
			boolean eliminado = r.eliminar(idEliminar);

			// Si eliminado == true, se ha eliminado sin problema. Si no, todo
			// ha ido bien.
			response.sendRedirect("./AccionesReservas?tipo=menuEditar&eliminado="
					+ eliminado + "&nombre=" + nombre);
			System.out.println("Se ha eliminado una reserva con ID "
					+ idEliminar);
			break;

		// Si se ha solicitado el buscador
		case "buscador":

			// Se recuperan los valores del formulario:
			int usuB = 0;
			int recB = 0;
			int estB = 0;
			int sucB = 0;
			Date inicioD = new Date();
			Date inicioH = new Date();
			Date finalD = new Date();
			Date finalH = new Date();

			if (request.getParameter("usuario") != null
					&& !request.getParameter("usuario").equals("")) {
				usuB = Integer.parseInt(request.getParameter("usuario"));
			}

			if (request.getParameter("recurso") != null
					&& !request.getParameter("recurso").equals("")) {
				recB = Integer.parseInt(request.getParameter("recurso"));
			}

			if (request.getParameter("estado") != null
					&& !request.getParameter("estado").equals("")) {
				estB = Integer.parseInt(request.getParameter("estado"));
			}

			if (request.getParameter("sucursal") != null
					&& !request.getParameter("sucursal").equals("")) {
				sucB = Integer.parseInt(request.getParameter("sucursal"));
			}

			// Se transforman las fechas:
			SimpleDateFormat formatoB = new SimpleDateFormat("yyyy-MM-dd");

			if (request.getParameter("inicioD") != null
					&& !request.getParameter("inicioD").equals("")) {
				try {
					inicioD = formatoB.parse(request.getParameter("inicioD"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				inicioD.setTime(0);
			}

			if (request.getParameter("inicioH") != null
					&& !request.getParameter("inicioH").equals("")) {
				try {
					inicioH = formatoB.parse(request.getParameter("inicioH"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				inicioH.setTime(0);
			}

			if (request.getParameter("finalD") != null
					&& !request.getParameter("finalD").equals("")) {
				try {
					finalD = formatoB.parse(request.getParameter("finalD"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				finalD.setTime(0);
			}

			if (request.getParameter("finalH") != null
					&& !request.getParameter("finalH").equals("")) {
				try {
					finalH = formatoB.parse(request.getParameter("finalH"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				finalH.setTime(0);
			}

			// Se recupera el valor de la vista de la sesión:
			int vista = 0;
			if (vistaSesion != null) {
				vista = vistaSesion;
			}

			// Se buscan las reservas que cumplen los requisitos:
			String sql = r.sqlBuscadorReservas(vista, usuB, recB, inicioD,
					inicioH, finalD, finalH, estB, sucB);
			ArrayList<Reserva> arrayReservaB = r.reservasBuscar(pagina, sql);

			// Se obtiene el número de finas del listado (para paginación):
			int numFilasB = r.numeroFilasBuscador(sql);

			// Para recargar el formulario, hay que volver a cargar los datos
			// para los combos:
			// Se obtiene los distintos usuarios para el formulario
			ArrayList<Usuario> arrayUsuario2 = u.usuariosListado(0);
			request.setAttribute("usuariosListadoCompleto", arrayUsuario2);

			// Se obtienen los recuros disponibles
			ArrayList<Recurso> arrayRecurso2 = rec.recursoListado(0);
			request.setAttribute("recursosListadoCompleto", arrayRecurso2);

			// Se obtienen los estados posibles para la reserva
			ArrayList<Reserva> arrayEstados2 = r.reservasEstados();
			request.setAttribute("estadosListado", arrayEstados2);

			// Se obtienen las sucursales posibles para la reserva
			ArrayList<Reserva> arraySucursales2 = r.reservasSucursales();
			request.setAttribute("sucursalesListado", arraySucursales2);

			// Se añade el array al request:
			request.setAttribute("reservasBuscadas", arrayReservaB);
			request.setAttribute("numFilas", numFilasB);

			// Se aÃ±aden los datos del formulario, para que se muestren por
			// defecto:

			// Se re-dirige a la página jsp:
			Reserva rFecha = new Reserva();
			String stringID = rFecha.dateToString(inicioD);
			String stringIH = rFecha.dateToString(inicioH);
			String stringFD = rFecha.dateToString(finalD);
			String stringFH = rFecha.dateToString(finalH);

			String url = "reservasBuscador.jsp?usuario=" + usuB + "&recurso="
					+ recB + "&estado=" + estB + "&sucursal=" + sucB
					+ "&inicioD=" + stringID + "&inicioH=" + stringIH
					+ "&finalD=" + stringFD + "&finalH=" + stringFH;
			RequestDispatcher rd4 = request.getRequestDispatcher(url);
			rd4.forward(request, response);

			break;

		default:
			break;
		}

	} // fin doGet

	/**
	 * Método que se ejecuta si se recibe una petición de tipo Post: se usa
	 * doGet()
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	} // fin doPost

} // fin AccionesReservas
