package es.uned2014.recursosAlpha.servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import es.uned2014.recursosAlpha.usuario.*;

/**
 * Servlet implementation class AccionesUsuarios
 * Gestiona las acciones a realizar relacionadas con los usuarios en función del parámetro "tipo" del request:
 * 	- Listados
 * 	- Crear
 * 	- Editar/Eliminar
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @fecha 	Junio-Julio 2014
 */
public class AccionesUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Como método constructor se utiliza el de la clase HttpServlet
	 * @see HttpServlet#HttpServlet()
	 */
	public AccionesUsuarios() {
		super();
	} // fin AccionesEmpleados
	
	
	/**
	 * Método en el que se describen las acciones a realizar relacionadas con Usuarios (Petición tipo Get)	
	 * @param request 
	 * @param response con la respuesta del método
	 * @return null
	 * @throws ServletException, IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo"); // Listado, crear, editar, etc
		String p = request.getParameter("p"); // paginación
		
		String idNuevoS = request.getParameter("idNuevo");
		String idEditadoS = request.getParameter("idEditado");
		String eliminadoS = request.getParameter("eliminado");
		
		String idEditarS = request.getParameter("idEditar");
		String idEliminarS = request.getParameter("idEliminar");
		
		String nombre = request.getParameter("nombre");
		
		// Paginación: por defecto, pag 1. Si la sesión tiene asociada otra página ("p"), se asigna ese valor
		Integer pagina = 1;
		if(p != null){
			pagina = Integer.parseInt(p);	
		}
		
		// Se crea un objeto Usuario:
		Usuario u = new Usuario();
		
		// SE REALIZAN ACCIONES EN FUNCIÓN DEL TIPO DE PETICIÓN QUE SE HACE AL SERVLET:
		switch (tipo) {
		
		// Si la acción es ver el listado:
		case "listado":
			// Se obtiene el array con el contenido del listado:
			ArrayList<Usuario> arrayUsuario = u.usuariosListado(pagina);
			
			// Se obtiene el número de finas del listado (para paginación):
			int numFilas = u.totalFilasUsuario();
			
			// Se añade el array a la variable de sesión:
			request.setAttribute("usuariosListado", arrayUsuario);
			request.setAttribute("numFilas", numFilas);
			
			// Se re-dirige a la página jsp:
			RequestDispatcher rd1 = request.getRequestDispatcher("usuariosListado.jsp");
			rd1.forward(request, response);			
			
			break;
			
		// Si se ha pulsado en el menú: crear usuario
		case "menuCrear":
			// Se obtienen los posibles roles de usuario, para el formulario
			ArrayList<Usuario> arrayRolesUsuario = u.usuariosRoles();
			request.setAttribute("rolesUsuario", arrayRolesUsuario);
			
			// Se re-dirige a la página jsp:
			RequestDispatcher rd2 = request.getRequestDispatcher("usuariosCrear.jsp?idNuevo="+idNuevoS+"&nombre="+nombre);
			rd2.forward(request, response);			
			
			break;	
			
		// Si se ha rellenado el formulario: crear usuario	
		case "crear":
			// Se obtienen los datos del formulario:
			String nombreUsuario = request.getParameter("nombreUsuario");
			String nom = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String contrasena = request.getParameter("contrasena");	
			int idRol = Integer.parseInt(request.getParameter("rol"));
			
			// Se crea el nuevo usuario y se recupera su id:
			int idNuevo =  u.nuevo(nombreUsuario, nom, apellidos, contrasena, idRol);
			
			// Para recargar el formulario, se obtienen los roles posibles:
			ArrayList<Usuario> arrayRolesUsuario2 = u.usuariosRoles();
			request.setAttribute("rolesUsuario", arrayRolesUsuario2);
			
			// Si el nuevo id es 0, ha habido un error al crear el usuario. Si no, todo ha ido bien.
			if (idNuevo != 0){
				response.sendRedirect("./AccionesUsuarios?tipo=menuCrear&idNuevo="+idNuevo+"&nombre="+nombreUsuario);
				System.out.println("Se ha creado un nuevo usuario con ID "+idNuevo+" y NombreUsuario '"+nombreUsuario+"'");
			} else {
				response.sendRedirect("./AccionesUsuarios?tipo=menuCrear&idNuevo=0");
				System.out.println("ERROR al crear Usuario: es posible que exista otro usuario con el mismo nombreUsuario.");
			}
			
			break;	
			
		// Si se ha pulsado en el menú: editar/eliminar usuario
		case "menuEditar":
			
			// Si se ha solicitado la edición de un elemento, se cargan datos al request:
			if (idEditarS != null){
				request.setAttribute("idEditar", idEditarS);
				
				// Para recargar el formulario, se obtienen los roles posibles:
				ArrayList<Usuario> arrayRolesUsuario3 = u.usuariosRoles();
				request.setAttribute("rolesUsuario", arrayRolesUsuario3);	
			}
			
			// Si se ha solicitado la eliminación de un elemento, se cargan datos al request:
			if (idEliminarS != null){
				request.setAttribute("idEliminar", idEliminarS);	
				request.setAttribute("nombre", nombre);
			}
			
			// Se obtiene el array con los usuarios:
			ArrayList<Usuario> arrayUsuario2 = u.usuariosListado(pagina);
			ArrayList<Usuario> arrayUsuarioTotal = u.usuariosListado(0);
			
			// Se obtiene el número de finas del listado (para paginación):
			int numFilas2 = u.totalFilasUsuario();
			
			// Se añade el array a la variable de sesión:
			request.setAttribute("usuariosListado", arrayUsuario2);
			request.setAttribute("numFilas", numFilas2);
			request.setAttribute("usuariosListadoCompleto", arrayUsuarioTotal);
			
			// Se re-dirige a la página jsp:
			RequestDispatcher rd3 = request.getRequestDispatcher("usuariosEditar.jsp?idEditado="+idEditadoS+"&eliminado="+eliminadoS+"&nombre="+nombre);
			rd3.forward(request, response);			
			
			break;	
			
		// Si se ha pulsado el botón: editar usuario
		case "botonEditar":
			// Se obtienen los datos del request:
			String idListaEditar = request.getParameter("id");
			
			// Se re-dirige:
			response.sendRedirect("./AccionesUsuarios?tipo=menuEditar&idEditar="+idListaEditar);		
			
			break;	
			
		// Si se ha rellenado el formulario: editar usuario	
		case "editar":
			// Se obtienen los datos del formulario:
			String nombreUsuarioEd = request.getParameter("nombreUsuario");
			String nombreEd = request.getParameter("nombre");
			String apellidosEd = request.getParameter("apellidos");
			String contrasenaEd = request.getParameter("contrasena");	
			int idRolEd = Integer.parseInt(request.getParameter("rol"));
			int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
			
			// Se crea el nuevo usuario y se recupera su id:
			int idEditado =  u.editarUsuario(idUsuario, nombreUsuarioEd, nombreEd, apellidosEd, contrasenaEd, idRolEd);
	
			// Si el nuevo id es 0, ha habido un error al editar. Si no, todo ha ido bien.
			if (idEditado != 0){
				response.sendRedirect("./AccionesUsuarios?tipo=menuEditar&nombre="+nombreUsuarioEd+"&idEditado="+idEditado);
				System.out.println("Se ha actualizado un usuario con ID "+idUsuario);
			} else {
				response.sendRedirect("./AccionesUsuarios?tipo=menuEditar&idEditado=0");
				System.out.println("ERROR actualizando usuario con ID "+idUsuario+": es posible que exista otro usuario con el mismo nombreUsuario.");
			}				
			
			break;	
			
		// Si se ha pulsado el botón: eliminar usuario
		case "botonEliminar":
			// Se obtienen los datos del request:
			String idListaEliminar = request.getParameter("id");	
			String nombreEliminar = request.getParameter("nombre");
			
			// Se re-dirige:
			response.sendRedirect("./AccionesUsuarios?tipo=menuEditar&idEliminar="+idListaEliminar+"&nombre="+nombreEliminar);		
			
			break;
			
		// Si se ha rellenado el formulario: crear usuario	
		case "eliminar":
			// Se obtienen los datos del request:
			int idEliminar = Integer.parseInt(request.getParameter("id"));	

			// Si se elimina a si mismo, se saldrá de sesión una vez eliminado:
			HttpSession session = request.getSession();
			String nU = (String)session.getAttribute("idSesion");
			int idU = u.getIdUsuario(nU);
			
			// Se elimina el usuario y se recupera su nombre:
			boolean eliminado =  u.eliminar(idEliminar);
			
			if(idEliminar == idU){
				System.out.println("Se ha eliminado un usuario con ID "+idEliminar);
				response.sendRedirect("./SalidaUsuario?eliminado="+eliminado);		
			} else {
				// Si eliminado == true, se ha eliminado sin problema. Si no, todo ha ido bien.
				System.out.println("Se ha eliminado un usuario con ID "+idEliminar);
				response.sendRedirect("./AccionesUsuarios?tipo=menuEditar&eliminado="+eliminado+"&nombre="+nombre);		
			}
			
			break;	
			
		// Si se ha pulsado en el menu: Buscador
		case "buscador":
			
			// Se recuperan los valores del formulario:
			String nombreUsuarioB = "";
			String nombreB = "";
			String apellidosB = "";
			String rolB = "0";
			
			if (request.getParameter("nombreUsuarioB") != null && !request.getParameter("nombreUsuarioB").equals("")) {
				nombreUsuarioB = request.getParameter("nombreUsuarioB");
			} // fin if
			
			if (request.getParameter("nombreB") != null && !request.getParameter("nombreB").equals("")) {
				nombreB = request.getParameter("nombreB");
			} // fin if
			
			if (request.getParameter("apellidosB") != null && !request.getParameter("apellidosB").equals("")) {
				apellidosB = request.getParameter("apellidosB");
			} // fin if
			
			if (request.getParameter("rolB") != null && !request.getParameter("rolB").equals("0")) {
				rolB = request.getParameter("rolB");
			} // fin if
			
			// Se buscan los usuarios que cumplen los requisitos:
			String sql = u.sqlBuscadorUsuarios(nombreUsuarioB, nombreB, apellidosB, rolB);
			ArrayList<Usuario> arrayUsuariosB = u.usuariosBuscar(pagina, sql);
			
			// Se obtiene el número de finas del listado (para paginación):
			int numFilasB = u.numeroFilasBuscador(sql);
			
			// Se obtiene los distintos usuarios para el formulario
			ArrayList<Usuario> arrayRoles = u.usuariosRoles();
			request.setAttribute("rolesUsuarios", arrayRoles);
			
			request.setAttribute("usuariosBuscados", arrayUsuariosB);
			request.setAttribute("numFilas", numFilasB);
			
			// Se re-dirige a la página jsp:
			String url = "usuariosBuscador.jsp?nombreUsuarioB=" + nombreUsuarioB
					+ "&nombreB=" + nombreB + "&apellidosB=" + apellidosB
					+ "&rolB=" + rolB;
			
			RequestDispatcher rd4 = request.getRequestDispatcher(url);
			rd4.forward(request, response);
			
			break;
				
		default:
			break;
		}
		
	} //fin doGet
	
	
	/**
	 * Método que se ejecuta si se recibe una petición de tipo Post: se usa doGet()
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	} // fin doPost

} // fin AccionesUsuarios
