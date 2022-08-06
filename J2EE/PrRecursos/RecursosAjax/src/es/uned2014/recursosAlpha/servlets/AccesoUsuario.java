package es.uned2014.recursosAlpha.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uned2014.recursosAlpha.usuario.Usuario;

/**
 * Servlet implementation class AccesoUsuario.
 * Se ejecuta cuando el usuario envía el formulario de acceso a la aplicación.
 * Si el formulario se ha cumplimentado, se comprueba si existe un usuario con 
 * esos nombre de usuario y contraseña en la base de datos. 
 * Si es así, se accede en calidad de empleado o responsable, según sea su rol.
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @fecha 	Junio-Julio 2014
 */
public class AccesoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Como método constructor se utiliza el de la clase HttpServlet
     * @see HttpServlet#HttpServlet()
     */
    public AccesoUsuario() {
        super();
    }

	/**
	 * Método que se ejecuta si se recibe una petición de tipo Get.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.doService(request, response);
	}

	/**
	 * Método que se ejecuta si se recibe una petición de tipo Post.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.doService(request, response);
	}
	
	/**
	 * Método en el que se describen las acciones a realizar cuando un usuario intenta 
	 * acceder a través del formulario de acceso.
	 * 	1 Se recuperan los valores de usuario y contraseña
	 * 	2 Si los campos no están vacíos: se busca si existe en BBDD.
	 * 		2.1 Si existe, se crea cookie y sesión. Se envía al menú correspondiente.
	 * 		2.2 Si no existe se vuelve a index con estado no válido
	 * 	3 Si algún campo está vacío: vuelve a index.jsp con estado no válido		
	 * @param request con el contenido del formulario
	 * @param response con la respuesta del método
	 * @return null
	 * @throws ServletException, IOException
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		
		// Si usuario y contraseña no vacíos:
		if (usuario!=null && contrasena!=null){
			// Se crea un usuario como respuesta de comprobar los valores enviados 
			// (se comprueba en BBDD)
			Usuario u = new Usuario().validarUsuario(usuario, contrasena);
			
			// Si el usuario existe en la base de datos, se crea cookie y sesión
			if(u != null){
				System.out.println("Ha accedido el usuario " + u.getNombreUsuario());
				
				// Se crea la cookie:
				Cookie miCookie = new Cookie("idCookie", usuario);
				miCookie.setMaxAge(15*60);
				response.addCookie(miCookie);
				
				// Se inicia la sesión:
				HttpSession session = request.getSession();
				session.setAttribute("idSesion", usuario);;
				
				System.out.println("La cookie y la sesión se han creado correctamente");
				
				// Se almacena el rol del usuario en la sesión
				int rol = u.getIdRol();
				session.setAttribute("rolSesion", rol);
				
				// Se almacena la vista de la sesión (para rol responsable, que tiene acceso a las dos)
				int vista = u.getIdRol(); // Por defecto, vista = rol
				session.setAttribute("vistaSesion", vista);
				
				// Se almacena el nombre y apellidos del usuario en la sesión
				String nombreApellidos = u.getNombre() + " " + u.getApellidos();
				session.setAttribute("nombreSesion", nombreApellidos);
				
				// Se carga index.jsp en visión empleado o responsable, en función del rol
				if (rol == 2){
					response.sendRedirect("acceso.jsp?estado=accesoResponsable");
				} else {
					response.sendRedirect("acceso.jsp?estado=accesoEmpleado");
				}
			} else {
				// Si el usuario no aparece en la base de datos:
				System.out.println("Usuario no válido: usuario no registrado.");
				response.sendRedirect("index.jsp?estado=noValido");
			}
			
		} else {
			// Si no se han rellenado los campos del formulario de acceso:
			System.out.println("Usuario no válido: no se han rellenado los campos del formulario.");
			response.sendRedirect("index.jsp?estado=noValido");
		}
	}
}
