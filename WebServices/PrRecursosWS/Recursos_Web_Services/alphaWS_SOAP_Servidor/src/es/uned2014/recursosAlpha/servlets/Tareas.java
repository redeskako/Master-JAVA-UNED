package es.uned2014.recursosAlpha.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uned2014.recursosAlpha.reserva.Reserva;

/**
 * Servlet implementation class Tareas. Gestiona la carga de datos y
 * visualización de las tareas de un usuario-responsable: - Tabla de reservas
 * pendientes, para confirmar o denegar - Tabla de reservas pendientes de
 * anulación, para anular o no anular
 * 
 * @author Alpha UNED 2014
 * @version Recursos 1.0
 * @since Junio 2014
 */
public class Tareas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Como método constructor se utiliza el de la clase HttpServlet
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public Tareas() {
		super();
	}

	/**
	 * Método que se ejecuta si se recibe una petición de tipo Get. Carga los
	 * datos a mostrar en función del request, y asigna los valores a la sesión
	 * para que el archivo jsp pueda recuperarlos. Al final, redirecciona al jsp
	 * correspondiente.
	 * 
	 * @param request
	 *            con el contenido del href
	 * @param response
	 *            con la respuesta del método
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

		String nombre = request.getParameter("nombre");

		// Por defecto, página 1. Si la sesión tiene asociada otra página ("p"),
		// se asigna ese valor:
		Integer pagina = 1;
		if (p != null) {
			pagina = Integer.parseInt(p);
		}

		// Se recuperan los datos del request:
		int resModificada = 0;
		if (request.getParameter("reserva") != null) {
			resModificada = Integer.parseInt(request.getParameter("reserva"));
		}

		Reserva r = new Reserva();

		// SE REALIZAN ACCIONES EN FUNCIÓN DEL TIPO DE PETICIÓN QUE SE HACE AL
		// SERVLET:
		switch (tipo) {

		// Si se han solicitado tareas_peticiones pendientes de
		// confirmar/denegar:
		case "peticiones":
			// Se recuperan los datos del request:
			String id = request.getParameter("id");
			String editado = request.getParameter("editado");

			// Se genera el array con las peticiones:
			ArrayList<Reserva> arrayPeticiones = r.reservasListado(pagina, 1);

			int numFilas = r.totalFilasReserva(1); // Total reservas pendientes

			// Se añade el array al request:
			request.setAttribute("arrayPeticiones", arrayPeticiones);
			request.setAttribute("numFilas", numFilas);
			request.setAttribute("id", id);
			request.setAttribute("editado", editado);

			// Se re-dirige a la página jsp:
			RequestDispatcher rd = request
					.getRequestDispatcher("tareas_PeticionesPendientes.jsp");
			rd.forward(request, response);

			break;

		// Si se han solicitado tareas_reservas pendientes de anular:
		case "reservas":
			// Se genera el array con las reservas:
			ArrayList<Reserva> arrayReservas = r.reservasListado(pagina, 4);

			int numFilas2 = r.totalFilasReserva(4); // Total reservas pendientes
													// de anulación

			// Se añade el array al request:
			request.setAttribute("arrayReservas", arrayReservas);
			request.setAttribute("numFilas", numFilas2);
			request.setAttribute("reserva", resModificada);

			// Se re-dirige a la página jsp:
			RequestDispatcher rd2 = request
					.getRequestDispatcher("tareas_ReservasPendientes.jsp");
			rd2.forward(request, response);

			break;

		// Si se ha pulsado un botón para confirmar:
		case "confirmar":

			// Se recuperan los valores de la petición:
			int idConfirmar = Integer.parseInt(request.getParameter("id"));

			// Se extrae la reserva entera del array de reservas pendientes:
			Reserva reservaConfirmar = new Reserva();
			ArrayList<Reserva> array = r.reservasListado(0, 1);
			for (Reserva res : array) {
				if (res.getIdReserva() == idConfirmar) {
					reservaConfirmar = res;
				}
			}

			// Se obtienen las fechas de inicio y fin:
			Date inicio = reservaConfirmar.getInicio();
			Date fin = reservaConfirmar.getFin();
			int idRecurso = reservaConfirmar.getIdRecurso();
			int idConfirmado = 0; // Id de la reserva editada

			// Se solicita el cambio de estado a confirmado (2) y se almacena el
			// id:
			idConfirmado = r.confirmar(idConfirmar, idRecurso, inicio, fin, 2);

			// Si el nuevo id es 0, ha habido un error al editar. Si no, todo ha
			// ido bien.
			if (idConfirmado != 0) {
				response.sendRedirect("./Tareas?tipo=peticiones&nombre="
						+ nombre + "&idEditado=" + idConfirmado
						+ "&accion=confirmada");
				System.out.println("Se ha confirmado la reserva con ID "
						+ idConfirmado);
			} else {
				response.sendRedirect("./Tareas?tipo=peticiones&idEditado=0");
				System.out.println("ERROR al intentar confirmar la reserva");
			}

			break;

		// Si se ha pulsado un botón para denegar:
		case "denegar":

			// Se recuperan los valores de la petición:
			int idDenegar = Integer.parseInt(request.getParameter("id"));

			int idDenegado = 0; // Id de la reserva editada

			// Se solicita el cambio de estado a denegado (3) y se almacena el
			// id:
			idDenegado = r.cambiarEstado(idDenegar, 3);

			// Si el nuevo id es 0, ha habido un error al editar. Si no, todo ha
			// ido bien.
			if (idDenegado != 0) {
				response.sendRedirect("./Tareas?tipo=peticiones&nombre="
						+ nombre + "&idEditado=" + idDenegado
						+ "&accion=denegada");
				System.out.println("Se ha denegado la reserva con ID "
						+ idDenegado);
			} else {
				response.sendRedirect("./Tareas?tipo=peticiones&idEditado=0");
				System.out.println("ERROR al intentar denegar la reserva");
			}

			break;

		// Si se ha pulsado un botón para anular:
		case "anular":

			// Se recuperan los valores de la petición:
			int idAnular = Integer.parseInt(request.getParameter("id"));

			int idAnulado = 0; // Id de la reserva editada

			// Se solicita el cambio de estado a anulado (5) y se almacena el
			// id:
			idAnulado = r.cambiarEstado(idAnular, 5);

			// Si el nuevo id es 0, ha habido un error al editar. Si no, todo ha
			// ido bien.
			if (idAnulado != 0) {
				response.sendRedirect("./Tareas?tipo=reservas&nombre=" + nombre
						+ "&idEditado=" + idAnulado + "&accion=anulada");
				System.out.println("Se ha anulado la reserva con ID "
						+ idAnulado);
			} else {
				response.sendRedirect("./Tareas?tipo=reservas&idEditado=0");
				System.out.println("ERROR al intentar anular la reserva");
			}

			break;

		// Si se ha pulsado un botón para no anular:
		case "noAnular":

			// Se recuperan los valores de la petición:
			int idNoAnular = Integer.parseInt(request.getParameter("id"));

			int idNoAnulado = 0; // Id de la reserva editada

			// Se solicita el cambio de estado a no anulado (6) y se almacena el
			// id:
			idNoAnulado = r.cambiarEstado(idNoAnular, 6);

			// Si el nuevo id es 0, ha habido un error al editar. Si no, todo ha
			// ido bien.
			if (idNoAnulado != 0) {
				response.sendRedirect("./Tareas?tipo=reservas&nombre=" + nombre
						+ "&idEditado=" + idNoAnulado + "&accion=no-anulada");
				System.out.println("No se ha anulado la reserva con ID "
						+ idNoAnulado);
			} else {
				response.sendRedirect("./Tareas?tipo=reservas&idEditado=0");
				System.out.println("ERROR al intentar no-anular la reserva");
			}

			break;

		default:
			break;

		}
	}

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
	}
}
