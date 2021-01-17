package es.uned2014.recursosAlpha.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SalidaUsuario.
 * Cierra la sesión del usuario.
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @fecha 	Junio-Julio 2014
 */
public class SalidaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Como método constructor se utiliza el de la clase HttpServlet
     * @see HttpServlet#HttpServlet()
     */
    public SalidaUsuario() {
        super();
    }

	/**
	 * Método en el que se describen las acciones a realizar cuando un usuario presiona 
	 * el botón de salir.	(Petición tipo Get)		
	 * @param request
	 * @param response con la respuesta del método
	 * @return null
	 * @throws
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// Se comprueba si se sale porque el usuario ha sido eliminado:
		String eliminado = request.getParameter("eliminado");		
		
		HttpSession sesion = request.getSession();
		sesion.invalidate();
		
		if (eliminado!=null && eliminado.equals("true")){
			response.sendRedirect("./index.jsp?estado=salida&eliminado=true");
			System.out.println("La session se ha terminado y elimindao correctamente");
		} else {
			response.sendRedirect("./index.jsp?estado=salida");
			System.out.println("La session se ha terminado correctamente");
		}	
	}

	/**
	 * Método que se ejecuta si se recibe una petición de tipo Post.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	


}
