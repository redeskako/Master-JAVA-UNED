package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CountryDao;
import model.Country;
import model.HealthIndicator;

/**
 * Servlet implementation class GestionBBDD
 */
@WebServlet("/GestionBBDD")
public class GestionBBDD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CountryDao countryDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionBBDD() {
        super();
        countryDao = new CountryDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession sesion = request.getSession(true);
		
	    if ("Add".equals(request.getParameter("Add_Country"))) {
	    	
	    	response.getWriter().append("ADD");
			String country_code = request.getParameter("country_code_add");
			String country_name = request.getParameter("country_name_add");
			
			Country pais = new Country();
	        pais.setCountry_code(country_code);
	        pais.setCountry_name(country_name);
			
	        countryDao.addCountry(pais);
	        response.sendRedirect("adminCountry.jsp?estadobbdd=addok");
	        
	      } else if ("Update".equals(request.getParameter("Update_Country"))) {
	    	  
	    	  response.getWriter().append("UPDATE");
				String country_code = request.getParameter("country_code_up");
				String country_name = request.getParameter("country_name_up");
				
				Country pais = new Country();
		        pais.setCountry_code(country_code);
		        pais.setCountry_name(country_name);
				
		        countryDao.updateCountry(pais);
		        response.sendRedirect("adminCountry.jsp?estadobbdd=upok");
		        
	      } else if ("Delete".equals(request.getParameter("Delete_Country"))) {
	    	  response.getWriter().append("DELETE");
	    	  
				String country_code = request.getParameter("country_code_del");
				
		        countryDao.deleteCountry(country_code);
		        response.sendRedirect("adminCountry.jsp?estadobbdd=delok");
		          
	      } else {
	    	  response.getWriter().append("ERROR");
	      }
	    
	    List<Country> listadoPaises = countryDao.getCountryPagination(0, 500);
    	sesion.setAttribute("listaPaises",listadoPaises);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
