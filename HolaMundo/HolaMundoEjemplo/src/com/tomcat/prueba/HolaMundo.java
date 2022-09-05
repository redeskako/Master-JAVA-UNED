package com.tomcat.prueba;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: HolaMundo
 *
 */
 public class HolaMundo extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public HolaMundo() {
		super();
	}

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Saludos desde Servleg</title>");
        out.println("</head>");
        out.println("<body>");
		try{
			String firstName = request.getParameter("Apellido_1").toString();
			String surname = request.getParameter("Apellido_2").toString();
			String name = request.getParameter("Nombre").toString();

	        out.println("<h1>Saludo en " + request.getContextPath () + "</h1>");
	        out.println("<p>Bienvenido " + firstName + " " + surname + ", "+ name+ "</p>");
		}catch(Exception e){
			out.println("<h1>Error en "+ request.getContextPath() + "</h1>");
			out.println("<p>Error en el uso de la llamada a la p&aacute;gina.");
			out.println("<br/>Intente con este <a href='http://pi-:8080/holaMundo/'>enlace</a></p>");
		}
        out.println("</body>");
        out.println("</html>");
        out.close();
	}
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doService(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doService(request, response);
	}
}