package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DataDao;
import dao.UserDao;
import dao.CountryDao;
import dao.HealthIndicatorDao;
import model.User;
import model.Data;
import model.Country;
import model.HealthIndicator;

/**
 * Servlet implementation class HealthIndicatorCont
 */
@WebServlet("/HealthIndicatorCont")
public class HealthIndicatorCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HealthIndicatorDao healthIndicatorDao;  
    private UserDao dao;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HealthIndicatorCont() {
        super();
       dao = new UserDao();
       healthIndicatorDao = new HealthIndicatorDao();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		HttpSession sesion = request.getSession(true);
		
	
		if ("Add".equals(request.getParameter("Add_Indicator"))) {
			// Add HealthIndicator
			String newindicatorcode = request.getParameter("indicatorcode");
			String indicatorname = request.getParameter("indicatorname");
			String sourcenote = request.getParameter("sourcenote");
			String sourceorganization = request.getParameter("sourceorganization");
			
			HealthIndicator newindicator = new HealthIndicator(); 
	    
			newindicator.setIndicator_code(newindicatorcode); 
			newindicator.setIndicator_name(indicatorname); 
			newindicator.setSource_note(sourcenote); 
			newindicator.setSource_organization(sourceorganization);
	
			healthIndicatorDao.addHealthIndicator(newindicator); 
		
		} else if ("Update".equals(request.getParameter("Update_Indicator"))) {
			// Update Healthindicator
			String editindicatorcode = request.getParameter("editindicatorcode");
		    String editindicatorname = request.getParameter("editindicatorname");
		    String editsourcenote = request.getParameter("editsourcenote");
		    String editsourceorganization = request.getParameter("editsourceorganization");
		    
	        HealthIndicator editindicator = new HealthIndicator(); 
	  
	        editindicator.setIndicator_code(editindicatorcode); 
	        editindicator.setIndicator_name(editindicatorname); 
	        editindicator.setSource_note(editsourcenote); 
	        editindicator.setSource_organization(editsourceorganization);
		    
		    healthIndicatorDao.updateHealthIndicator(editindicator);
		
		} else if ("Delete".equals(request.getParameter("Delete_Indicator"))) {
			//Delete
			String gi = request.getParameter("deleteindicator");
		    System.out.print("button value"+ " " +gi);
	       
		    healthIndicatorDao.deleteHealthIndicator(gi);
		
		}
	       
	    List<HealthIndicator> healthindicatorList = healthIndicatorDao.getHealthPagination(0, 500);
		
	    for (int i = 0; i < healthindicatorList.size(); i++) {
		
		sesion.setAttribute("listaIndicador",healthindicatorList);
		
	    }
	    
	    response.sendRedirect("healthindicatoradmin.jsp");
	    
	}

	
	
	
	
	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
