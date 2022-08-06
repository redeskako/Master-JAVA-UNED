package es.uned2014.recursosAlpha.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import es.uned2014.recursosAlpha.clasesCliente.*;

/**
 * Servlet implementation class AccesoUsuario.
 * Se ejecuta cuando el usuario env�a el formulario de acceso a la aplicaci�n.
 * Si el formulario se ha cumplimentado, se comprueba si existe un usuario con 
 * esos nombre de usuario y contrase�a en la base de datos. 
 * Si es as�, se accede en calidad de empleado o responsable, seg�n sea su rol.
 * 
 * @author	Alpha UNED 2014
 * @version	RecursosWS 1.0
 * @since 	Septiembre 2014
 */
public class AccesoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Como m�todo constructor se utiliza el de la clase HttpServlet
	 * @see HttpServlet#HttpServlet()
	 */
	public AccesoUsuario() {
		super();
	} // fin AccesoUsuario

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");

		if (usuario!=null && contrasena!=null){
			Usuario u = new Usuario();
			Auth auth = new Auth();

			Client cliente = ClientBuilder.newClient();
			WebTarget webTarget = cliente.target("http://localhost:8080/alphaWS_Servidor/rest/accesoUsuario" + "?u=" + usuario + "&p=" + contrasena);
			Builder builder = webTarget.request();
			Invocation invocation = builder.build("GET");
			Response res = invocation.invoke();
			
			if(res.getStatus() == 200){
				auth = (Auth) res.readEntity(Auth.class);
			}
			
			cliente.close();

			if(auth.getUsuario() != null){
				u = auth.getUsuario();

				System.out.println("Ha accedido el usuario " + u.getNombreUsuario());

				// Se crea la cookie:
				Cookie miCookie = new Cookie("token", auth.getToken());
				miCookie.setMaxAge(15*60*60);
				response.addCookie(miCookie);
				
				// Se crea la cookie:
				Cookie idCookie = new Cookie("idCookie", u.getNombreUsuario());
				idCookie.setMaxAge(15*60*60);
				response.addCookie(miCookie);

				// Se inicia la sesi�n:
				HttpSession session = request.getSession();
				session.setAttribute("idSesion", u.getNombreUsuario());

				System.out.println("La cookie y la sesi�n se han creado correctamente");

				// Se almacena el rol del usuario en la sesi�n (en la aplicai�n Cliente son siempre empleados)
				int rol = 1;
				session.setAttribute("rolSesion", rol);

				// Se almacena la vista de la sesi�n (en la aplicai�n Cliente son siempre empleados)
				int vista = 1; // Por defecto, vista = rol
				session.setAttribute("vistaSesion", vista);

				// Se almacena el nombre y apellidos del usuario en la sesi�n
				String nombreApellidos = u.getNombre() + " " + u.getApellidos();
				session.setAttribute("nombreSesion", nombreApellidos);

				// Se carga index.jsp en visi�n empleado
				response.sendRedirect("index.jsp?estado=accesoEmpleado");

			} else {
				// Si el usuario no aparece en la base de datos:
				System.out.println("Usuario no v�lido: usuario no registrado.");
				response.sendRedirect("index.jsp?estado=noValido");
			}

		} else {
			// Si no se han rellenado los campos del formulario de acceso:
			System.out.println("Usuario no v�lido: no se han rellenado los campos del formulario.");
			response.sendRedirect("index.jsp?estado=noValido");
		}
	} // fin doGet

	/**
	 * M�todo que se ejecuta si se recibe una petici�n de tipo Post.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	} // fin doPost

} // fin clase AccesoUsuario
