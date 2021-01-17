package org.Servlet;
import org.BBDD.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class actualizar
 */
@WebServlet("/Actualizar")
public class Actualizar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Actualizar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doActualizar(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doActualizar(request, response);

		
	}
	
	protected void doActualizar (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	

		// RECOJEMOS EL Nº DE ID A ACTUALIZAR
		HttpSession sesion= request.getSession();
		String idRecurso=String.valueOf(sesion.getAttribute("idRec"));//
		

		BBDD mibd=new BBDD();

		mibd.actualizarRecursos("Update recursos Set Descripcion='"+request.getParameter("recurso")+"' where idRecurso="+idRecurso);

		response.sendRedirect("actualizador.jsp");
		
	}
}
