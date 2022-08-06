package es.uned2014.recursosAlpha.servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import es.uned2014.recursosAlpha.recurso.*;

/**
 * Servlet implementation class AccionesRecursos Gestiona las acciones a
 * realizar relacionadas con los recursos en función del parámetro "tipo" del
 * request: - Listados - Crear - Editar/Eliminar
 * 
 * @author Alpha UNED 2014
 * @version Recursos 1.0
 * @since Junio 2014
 */
public class AccionesRecursos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Como método constructor se utiliza el de la clase HttpServlet
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public AccionesRecursos() {
		super();
	} // fin AccionesRecursos

	/**
	 * Método en el que se describen las acciones a realizar relacionadas con
	 * Recursos (Petición tipo Get)
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

		// Paginación: por defecto, pag 1. Si la sesión tiene asociada otra
		// página ("p"), se asigna ese valor
		Integer pagina = 1;
		if (p != null) {
			pagina = Integer.parseInt(p);
		}

		// Se crea un objeto Recurso:
		Recurso r = new Recurso();

		// SE REALIZAN ACCIONES EN FUNCIÃ“N DEL TIPO DE PETICIÃ“N QUE SE HACE AL
		// SERVLET:
		switch (tipo) {

		// Si la acción es ver el listado:
		case "listado":
			// Se obtiene el array con el contenido del listado:
			ArrayList<Recurso> arrayRecurso = r.recursoListado(pagina);

			// Se obtiene el nÃºmero de finas del listado (para paginación):
			int numFilas = r.totalFilasRecurso();

			// Se aÃ±ade el array a la variable de sesión:
			request.setAttribute("recursosListado", arrayRecurso);
			request.setAttribute("numFilas", numFilas);

			// Se re-dirige a la página jsp:
			RequestDispatcher rd = request
					.getRequestDispatcher("recursosListado.jsp");
			rd.forward(request, response);

			break;

		// Si se ha pulsado en el menÃº: crear recurso
		case "menuCrear":
			// Se re-dirige a la página jsp:
			response.sendRedirect("recursosCrear.jsp?idNuevo=" + idNuevoS
					+ "&nombre=" + nombre);

			break;

		// Si se ha rellenado el formulario: crear recurso
		case "crear":
			// Se obtienen los datos del formulario:
			String descripcion = request.getParameter("descripcion");

			// Se crea el nuevo recurso y se recupera su id:
			int idNuevo = r.nuevo(descripcion);

			// Si el nuevo id es 0, ha habido un error al crear recurso. Si no,
			// todo ha ido bien.
			if (idNuevo != 0) {
				response.sendRedirect("./AccionesRecursos?tipo=menuCrear&idNuevo="
						+ idNuevo + "&nombre=" + descripcion);
				System.out.println("Se ha creado un nuevo recurso con ID "
						+ idNuevo + " y descripción '" + descripcion + "'");
			} else {
				response.sendRedirect("./AccionesRecursos?tipo=menuCrear&idNuevo=0");
				System.out.println("ERROR al crear Recurso.");
			}

			break;

		// Si se ha pulsado en el menÃº: editar/eliminar recurso
		case "menuEditar":

			// Si se ha solicitado la edición de un elemento, se cargan datos al
			// request:
			if (idEditarS != null) {
				request.setAttribute("idEditar", idEditarS);
			}

			// Si se ha solicitado la eliminación de un elemento, se cargan
			// datos al request:
			if (idEliminarS != null) {
				request.setAttribute("idEliminar", idEliminarS);
				request.setAttribute("nombre", nombre);
			}

			// Se obtiene el array con los recursos:
			ArrayList<Recurso> arrayRecurso2 = r.recursoListado(pagina);
			ArrayList<Recurso> arrayRecursoTotal = r.recursoListado(0);

			// Se obtiene el nÃºmero de finas del listado (para paginación):
			int numFilas2 = r.totalFilasRecurso();

			// Se aÃ±ade el array a la variable de sesión:
			request.setAttribute("recursosListado", arrayRecurso2);
			request.setAttribute("numFilas", numFilas2);
			request.setAttribute("recursosListadoCompleto", arrayRecursoTotal);

			// Se re-dirige a la página jsp:
			RequestDispatcher rd3 = request
					.getRequestDispatcher("recursosEditar.jsp?idEditado="
							+ idEditadoS + "&eliminado=" + eliminadoS
							+ "&nombre=" + nombre);
			rd3.forward(request, response);

			break;

		// Si se ha pulsado el botón: editar recurso
		case "botonEditar":
			// Se obtienen los datos del request:
			String idListaEditar = request.getParameter("id");

			// Se re-dirige:
			response.sendRedirect("./AccionesRecursos?tipo=menuEditar&idEditar="
					+ idListaEditar);

			break;

		// Si se ha rellenado el formulario: editar recurso
		case "editar":
			// Se obtienen los datos del formulario:
			String descripcionEd = request.getParameter("descripcion");
			int idRecurso = Integer.parseInt(request.getParameter("idRecurso"));

			// Se crea el nuevo recurso y se recupera su id:
			int idEditado = r.editarRecurso(idRecurso, descripcionEd);

			// Si el nuevo id es 0, ha habido un error al editar. Si no, todo ha
			// ido bien.
			if (idEditado != 0) {
				response.sendRedirect("./AccionesRecursos?tipo=menuEditar&nombre="
						+ descripcionEd + "&idEditado=" + idEditado);
				System.out.println("Se ha actualizado un recurso con ID "
						+ idEditado);
			} else {
				response.sendRedirect("./AccionesRecursos?tipo=menuEditar&idEditado=0");
				System.out.println("ERROR actualizando recurso con ID "
						+ idRecurso);
			}

			break;

		// Si se ha pulsado el botón: eliminar recurso
		case "botonEliminar":
			// Se obtienen los datos del request:
			String idListaEliminar = request.getParameter("id");
			String nombreEliminar = request.getParameter("nombre");

			// Se re-dirige:
			response.sendRedirect("./AccionesRecursos?tipo=menuEditar&idEliminar="
					+ idListaEliminar + "&nombre=" + nombreEliminar);

			break;

		// Si se ha rellenado el formulario: crear usuario
		case "eliminar":
			// Se obtienen los datos del request:
			int idEliminar = Integer.parseInt(request.getParameter("id"));

			// Se elimina el usuario y se recupera su nombre:
			boolean eliminado = r.eliminar(idEliminar);

			// Si eliminado == true, se ha eliminado sin problema. Si no, todo
			// ha ido bien.
			response.sendRedirect("./AccionesRecursos?tipo=menuEditar&eliminado="
					+ eliminado + "&nombre=" + nombre);
			System.out.println("Se ha eliminado un recurso con ID "
					+ idEliminar);

			break;

		// Si se ha solicitado el buscador
		case "buscador":

			// Se recuperan los valores del formulario:
			String busqueda = "";

			if (request.getParameter("busqueda") != null
					&& !request.getParameter("busqueda").equals("")) {
				busqueda = request.getParameter("busqueda");
			}

			// Se buscan los recursos que cumplen los requisitos:
			String sql = r.sqlBuscadorRecursos(busqueda);
			ArrayList<Recurso> arrayRecursoB = r.recursosBuscar(pagina, sql);

			// Se obtiene el nÃºmero de finas del listado (para paginación):
			int numFilasB = r.numeroFilasBuscador(sql);

			// Se aÃ±ade el array al request:
			request.setAttribute("recursosBuscados", arrayRecursoB);
			request.setAttribute("numFilas", numFilasB);

			// Se re-dirige a la página jsp:
			RequestDispatcher rd4 = request
					.getRequestDispatcher("recursosBuscador.jsp?busqueda="
							+ busqueda);
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

} // fin clase Recursos
