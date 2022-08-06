package es.uned2014.recursosAlpha.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uned2014.recursosAlpha.recurso.Recurso;
import es.uned2014.recursosAlpha.reserva.Reserva;

/**
 * Servlet implementation class MisPeticionesReservas Gestiona la visualización
 * por parte de un usuario-empleado de sus propias peticiones (reservas
 * pendientes) y de sus reservas vigentes (reservas confirmadas, pendientes de
 * anulación y no anuladas).
 * 
 * @author Alpha UNED 2014
 * @version Recursos 1.0
 * @since Junio 2014
 */
public class MisPeticionesReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Como método constructor se utiliza el de la clase HttpServlet
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public MisPeticionesReservas() {
		super();
	}

	/**
	 * Método que obtiene en bbdd un array con las reservas correspondientes
	 * (peticiones o reservas) y se las hace llegar al jsp para que muestre el
	 * listado.
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
		String tipo = request.getParameter("tipo");
		String p = request.getParameter("p"); // paginación

		// Por defecto, página 1. Si la sesión tiene asociada otra página ("p"),
		// se asigna ese valor:
		Integer pagina = 1;
		if (p != null) {
			pagina = Integer.parseInt(p);
		}

		Reserva r = new Reserva();
		Recurso rec = new Recurso();

		// Se recuperan los datos del request:
		int resModificada = 0;
		if (request.getParameter("reserva") != null) {
			resModificada = Integer.parseInt(request.getParameter("reserva"));
		}

		// Se obtiene la sesión y el usuario conectado:
		HttpSession session = request.getSession();
		String usuario = (String) session.getAttribute("idSesion");

		// SE REALIZAN ACCIONES EN FUNCIÓN DEL TIPO DE PETICIÓN QUE SE HACE AL
		// SERVLET:
		switch (tipo) {

		// Si se han solicitado peticiones pendientes:
		case "peticiones":
			// Se obtiene el array de reservas para el usuario conectado:
			ArrayList<Reserva> arrayPeticiones = r.misPeticiones(usuario,
					pagina);

			// El número total de filas (estado 1 = "pendiente")
			int numFilas = r.totalFilasReserva(1, usuario);

			// Se añade el array a la variable del request:
			request.setAttribute("misPeticiones", arrayPeticiones);
			request.setAttribute("numFilas", numFilas);
			request.setAttribute("reserva", resModificada);

			// Se añade el array de recursos, para editar peticiones:
			ArrayList<Recurso> arrayRecurso2 = rec.recursoListado(0);
			request.setAttribute("recursosListadoCompleto", arrayRecurso2);

			// Se re-dirige a la página jsp:
			RequestDispatcher rd = request
					.getRequestDispatcher("misPeticionesPendientes.jsp");
			rd.forward(request, response);

			break;

		// Si se han solicitado reservas vigentes:
		case "reservas":
			// Se obtiene el array de reservas para el usuario conectado:
			ArrayList<Reserva> arrayReservas2 = r.misReservas(usuario, pagina);

			// El número total de filas es la suma de los estados 2, 4 y 6
			int numFilas2 = r.totalFilasReserva(2, usuario)
					+ r.totalFilasReserva(4, usuario)
					+ r.totalFilasReserva(6, usuario);

			// Se añade el array a la variable de sesión:
			request.setAttribute("misReservas", arrayReservas2);
			request.setAttribute("numFilas", numFilas2);
			request.setAttribute("reserva", resModificada);

			// Se re-dirige a la página jsp:
			RequestDispatcher rd2 = request
					.getRequestDispatcher("misReservasPendientes.jsp");
			rd2.forward(request, response);

			break;

		// Si se ha pulsado un botón para solicitar anulación:
		case "solicitarAnulacion":

			// Se recuperan los valores de la petición:
			int idSolicitar = Integer.parseInt(request.getParameter("id"));

			int idSolicitado = 0; // Id de la reserva editada

			// Lo primero, se comprueba si el usuario es el dueño de la reserva:
			boolean usuarioAnular = r.comprobarUsuario(usuario, idSolicitar);

			if (usuarioAnular) {
				// Se solicita el cambio de estado a denegado (3) y se almacena
				// el id:
				idSolicitado = r.cambiarEstado(idSolicitar, 4);

				// Si el nuevo id es 0, ha habido un error al editar. Si no,
				// todo ha ido bien.
				response.sendRedirect("./MisPeticionesReservas?tipo=reservas&idEditado="
						+ idSolicitado);
			} else {
				response.sendRedirect("index.jsp?estado=ilegal");
			}

			break;

		// Si se ha pulsado el botón: eliminar usuario
		case "botonEliminar":
			// Se obtienen los datos del request:
			String idListaEliminar = request.getParameter("id");
			String nombre = request.getParameter("nombre");

			// Lo primero, se comprueba si el usuario es el dueño de la reserva:
			boolean usuarioEliminar = r.comprobarUsuario(usuario,
					Integer.parseInt(idListaEliminar));

			if (usuarioEliminar) {
				response.sendRedirect("./MisPeticionesReservas?tipo=peticiones&idEliminar="
						+ idListaEliminar + "&nombre=" + nombre);
			} else {
				response.sendRedirect("index.jsp?estado=ilegal");
			}

			break;

		// Si se ha pulsado un botón para eliminar petición pendiente:
		case "eliminar":

			// Se recuperan los valores de la petición:
			int idEliminar = Integer.parseInt(request.getParameter("id"));

			boolean eliminado = false; // reserva eliminada

			// Lo primero, se comprueba si el usuario es el dueño de la reserva:
			boolean usuarioEliminar2 = r.comprobarUsuario(usuario, idEliminar);

			if (usuarioEliminar2) {
				// Se solicita eliminar una reserva:
				eliminado = r.eliminar(idEliminar);

				// Si el nuevo id es 0, ha habido un error al editar. Si no,
				// todo ha ido bien.
				response.sendRedirect("./MisPeticionesReservas?tipo=peticiones&eliminado="
						+ eliminado);
				System.out.println("Se ha eliminado una reserva");
			} else {
				response.sendRedirect("index.jsp?estado=ilegal");
				System.out.println("ERROR al intentar eliminar una reserva");
			}

			break;

		// Si se ha pulsado el botón: editar petición
		case "botonEditar":
			// Se obtienen los datos del request:
			String idListaEditar = request.getParameter("id");

			// Lo primero, se comprueba si el usuario es el dueño de la reserva:
			boolean usuarioEditar = r.comprobarUsuario(usuario,
					Integer.parseInt(idListaEditar));

			// Se re-dirige:
			if (usuarioEditar) {
				response.sendRedirect("./MisPeticionesReservas?tipo=peticiones&idEditar="
						+ idListaEditar);
			} else {
				response.sendRedirect("index.jsp?estado=ilegal");
			}

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
			int sucursalIdEdit = Integer.parseInt(request
					.getParameter("sucursal"));
			int idReserva = Integer.parseInt(request.getParameter("idReserva"));

			int idEditado = 0; // Id de la reserva editada

			// Lo primero, se comprueba si el usuario es el dueño de la reserva:
			boolean usuarioEditar2 = r.comprobarUsuario(usuario, idReserva);

			if (usuarioEditar2) {
				// Se continúa:
				// Se transforman las fechas:
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				try {
					Date inicioEdit = df.parse(inicioStEdit);
					Date finEdit = df.parse(finStEdit);

					// Se edita la reserva y se recupera su id:
					idEditado = r.editarReserva(idReserva, usuIdEdit,
							recIdEdit, inicioEdit, finEdit, estadoIdEdit,
							sucursalIdEdit);

				} catch (ParseException e) {
					e.printStackTrace();
				}

				// Si el nuevo id es 0, ha habido un error al editar. Si no,
				// todo ha ido bien.
				response.sendRedirect("./MisPeticionesReservas?tipo=peticiones&idEditado="
						+ idEditado);
			} else {
				response.sendRedirect("index.jsp?estado=ilegal");
			}

			break;

		default:
			break;
		}

	}

	/**
	 * Método que se ejecuta si se recibe una petición de tipo Post.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
