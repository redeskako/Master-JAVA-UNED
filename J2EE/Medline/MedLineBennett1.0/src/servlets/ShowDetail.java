package servlets;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.HealthTopics;
import beans.HealthTopics.HealthTopic;

/** Servlet implementation class ShowDetail */

public class ShowDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDetail() {
        super();
    }
    
   @SuppressWarnings("unchecked")
   protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	HealthTopics hts = new HealthTopics();
 		ArrayList<HealthTopic> listadoCompleto = new ArrayList<HealthTopic>(),
 				listadoPagina = new ArrayList<HealthTopic>();
 		HealthTopic ht = null;
 		List<HealthTopics.HealthTopic.Site> sites;
 		TreeSet<String> setOrg = new TreeSet<String>();
 		ArrayList<String> listadoOrg = null;
 		RequestDispatcher vista;
 		HttpSession session = request.getSession();
 		int pag = 1, index = 0, numListado, regPorPag = 5;
 		String busqueda = "medline", campo = "*", palabra = "";
 		
 		if (request.getParameter("search") != null)
 			busqueda = request.getParameter("search");
 		if (request.getParameter("pag") != null)
 			pag = Integer.parseInt(request.getParameter("pag"));
 		if (request.getParameter("index") != null)
 			index = Integer.parseInt(request.getParameter("index"));
 		if (request.getParameter("txtCampo") != null)
 			campo = request.getParameter("txtCampo");
 		if (request.getParameter("txtPalabra") != null)
 			palabra = request.getParameter("txtPalabra");
 		numListado = (busqueda.equals("medline") ? 1: 2); 
 		if (session.getAttribute("healthtopicFullList" + numListado) != null)
 			listadoCompleto = (ArrayList<HealthTopic>) 
 				session.getAttribute("healthtopicFullList" + numListado);
 		
		listadoPagina = hts.PaginacionBusquedaXML(listadoCompleto, pag, regPorPag);
		if (listadoPagina.size() > index) {
			// Obtenemos el healthtopic correspondiente al índice solicitado
			// en la página.
			ht = listadoPagina.get(index);
			// Creamos un listado con las distintas organizaciones
			// asociadas a los sites del healthtopic, para evitar que 
			// algunas de ellas se muestren repetidas.
			sites = ht.getSite();
			for (HealthTopics.HealthTopic.Site st: sites) {
				for (String org: st.getOrganization()) {
					if (!setOrg.contains(org)) setOrg.add(org);
				}		
			}
			listadoOrg = new ArrayList<String>();
			listadoOrg.addAll(setOrg);
		}
		
		request.setAttribute("search", busqueda);
		request.setAttribute("ht", ht);
		request.setAttribute("orgList", listadoOrg);
 	   request.setAttribute("pag", pag);
 	   request.setAttribute("campo", campo);
 	   request.setAttribute("palabra", palabra);
 	   vista = request.getRequestDispatcher("healthtopicView.jsp");
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
