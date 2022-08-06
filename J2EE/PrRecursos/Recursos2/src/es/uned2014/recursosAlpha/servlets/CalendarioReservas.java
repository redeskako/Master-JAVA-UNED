/**
 * 
 */
package es.uned2014.recursosAlpha.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dhtmlx.planner.*;
import com.dhtmlx.planner.data.*;

import es.uned2014.recursosAlpha.usuario.Usuario;

/**
 *
 * Servlet implementation class CalendarioReservas.
 * 
 * Este Servlet es responsable de proveer datos para la vista del calendarPlanner
 * Crea y devuelve un DHXPPPlanner a nuestro fichero JSP
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @fecha 	November 2016
 *
 */
public class CalendarioReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	public CalendarioReservas() {
		
		super();
	}
	
	/**
	 * Funcion responsable de las acciones con nuestra vista de calendario (tipo Get)
	 * 
	 * @param request 
	 * @param response con la respuesta del método
	 * @throws ServletException, IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher;
		HttpSession session = request.getSession();
		Integer id = (Integer)session.getAttribute("idSesion");
		if(id != null)
			usuario = Usuario.getUserById(id);
		try {
			request.setAttribute("planner", getPlanner(request));
		} catch (Exception e) {
			e.printStackTrace();
		}

		requestDispatcher = request.getRequestDispatcher("calendarioReservas.jsp");
		requestDispatcher.forward(request, response);
	}
	
	
	/***
	 * Esta funcion crea la vista del calendarPlanner que se rellena con los diferentes objetos evento
	 * 
	 * @param HttpServletRequest request
	 * @return String containing the HTML to be rendered by the jsp
	 * @throws Exception
	 */
	protected String getPlanner(HttpServletRequest request) throws Exception {
		DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
		s.setWidth(100, "%");
		s.setHeight(900);
		s.setInitialDate(new Date());
		if(usuario == null) s.config.setReadonly(true);
		s.load("events.jsp", DHXDataFormat.JSON);
		s.data.dataprocessor.setURL("events.jsp");
		return s.render();
	}
}
