package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.jws.WebService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BBDD;
import beans.Bennett;

/** Servlet implementation class BennettSearch */

@WebService(targetNamespace = "http://servlets/", portName = "BennettSearchPort", serviceName = "BennettSearchService")
public class BennettSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   /**
    * @see HttpServlet#HttpServlet()
    */
	public BennettSearch() {
		super();
	}
   
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BBDD bd = new BBDD();
		ArrayList<Bennett> listado = null;
		RequestDispatcher vista;
		int pag = 1, filasPorPagina = 12, filas, paginas;
		String campo = "*", palabra = "";
		
		if (request.getParameter("pag") != null)
			pag = Integer.parseInt(request.getParameter("pag"));
		if (request.getParameter("txtCampo") != null)
			campo = request.getParameter("txtCampo");
		if (request.getParameter("txtPalabra") != null)
			palabra = request.getParameter("txtPalabra");
			
		bd.abrirConexion();
		listado = bd.listadoBennett(campo, palabra, pag, filasPorPagina);
		filas = bd.getFilas();
		paginas = (int) Math.ceil(filas * 1.0 / filasPorPagina);
	   bd.cerrarConexion();
	   
	   request.setAttribute("bennettList", listado);
	   request.setAttribute("paginas", paginas);
	   request.setAttribute("pag", pag);
	   request.setAttribute("campo", campo);
	   request.setAttribute("palabra", palabra);
	   vista = request.getRequestDispatcher("bennettView.jsp");
	   vista.forward(request, response);
	} 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doService(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doService(request, response);
	}
}
