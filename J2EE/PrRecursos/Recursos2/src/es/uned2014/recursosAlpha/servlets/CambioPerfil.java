package es.uned2014.recursosAlpha.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CambioPerfil.
 * Intercambia la vista entre vista de responsable y de empleado, para que 
 * un usuario-responsable tenga acceso a las dos posibles visualizaciones. 
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @fecha 	Junio-Julio 2014
 */
public class CambioPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Como método constructor se utiliza el de la clase HttpServlet
     * @see HttpServlet#HttpServlet()
     */
    public CambioPerfil() {
        super();
    }

	/**
	 * Método en el que se describen las acciones a realizar cuando un 
	 * usuario-responsable utiliza el link para cambiar de vista empleado-responsable.	
	 * (Petición tipo Get)	
	 * @param request 
	 * @param response con la respuesta del método
	 * @return null
	 * @throws ServletException, IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer vista = (Integer)session.getAttribute("vistaSesion");
		
		// Si ha pulsado el link un responsable, se pasa a vista de empleado, y viceversa:
		if (vista == 1){
			session.setAttribute("vistaSesion", 2);
			response.sendRedirect("./acceso.jsp?estado=accesoResponsable");
		} else {
			session.setAttribute("vistaSesion", 1);
			response.sendRedirect("./acceso.jsp?estado=accesoEmpleado");
		}	
	}

	/**
	 * Método que se ejecuta si se recibe una petición de tipo Post: se usa doGet()
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
