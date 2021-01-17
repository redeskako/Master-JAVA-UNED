package org.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.BBDD.BBDD;

/**
 * Servlet implementation class Insertador
 */
@WebServlet("/Insertador")
public class Insertador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insertador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doServicioInsert(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doServicioInsert(request, response);
	}
	protected void doServicioInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//UTILIZAMOS EL MISMO METODO DE CLASE BBDD PARA AINSERTAR REGISTROS
		BBDD mibd=new BBDD();


		System.out.println("Insert Into recursos (Descripcion) Values('"+request.getParameter("recurso")+"')");
		mibd.actualizarRecursos("Insert Into recursos (Descripcion) Values('"+request.getParameter("recurso")+"')");

		response.sendRedirect("actualizador.jsp");
	}
}
