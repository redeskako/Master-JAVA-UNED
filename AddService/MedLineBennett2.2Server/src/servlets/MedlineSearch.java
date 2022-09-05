package servlets;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import beans.HealthTopics;
import beans.HealthTopics.HealthTopic;


/** Servlet implementation class MedLineSearch */

public class MedlineSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
    * @see HttpServlet#HttpServlet()
    */
	public MedlineSearch() {
		super();
   }

   @SuppressWarnings("unchecked")
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	HealthTopics hts = new HealthTopics();
 		ArrayList<HealthTopic> listadoCompleto = new ArrayList<HealthTopic>(),
 				listadoPagina = new ArrayList<HealthTopic>();
 		RequestDispatcher vista;
 		HttpSession session = request.getSession();
 		int pag = 1, regPorPag = 5, totReg, paginas;
 		String ruta, campo = "*", palabra = "";
 		
 		if (request.getParameter("pag") != null)
 			pag = Integer.parseInt(request.getParameter("pag"));
 		if (request.getParameter("txtCampo") != null)
 			campo = request.getParameter("txtCampo");
 		if (request.getParameter("txtPalabra") != null)
 			palabra = request.getParameter("txtPalabra");	
 		if (session.getAttribute("healthtopicFullList1") != null)
 			listadoCompleto = (ArrayList<HealthTopic>) session.getAttribute("healthtopicFullList1");
 
 		// Sólo si se ha introducido alguna palabra iniciamos la búsqueda.
 		if (!palabra.equals("")) {
 			// Si no se había generado el listado completo anteriormente.
 			if (request.getParameter("pag") == null) {
 				ruta = getServletContext().getRealPath(getServletContext().getInitParameter("dirUploadFiles"))
 						+ File.separator + "medlinecompleta.xml";
 				listadoCompleto = hts.BusquedaHealthTopicXML(ruta, campo, palabra);
 			}
 			totReg = listadoCompleto.size();
 			if (totReg > 0)
 				listadoPagina = hts.PaginacionBusquedaXML(listadoCompleto, pag, regPorPag);
 			paginas = (int) Math.ceil(totReg * 1.0 / regPorPag);
 	   
	 		session.setAttribute("healthtopicFullList1", listadoCompleto);
	 		request.setAttribute("healthtopicList", listadoPagina);
	 	   request.setAttribute("paginas", paginas);
	 	   request.setAttribute("pag", pag);
	 	   request.setAttribute("campo", campo);
	 	   request.setAttribute("palabra", palabra);
 		}
	 	
 		vista = request.getRequestDispatcher("medlineView.jsp");
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







	

