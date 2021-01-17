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
		
		List<HealthIndicator> healthindicatorList = healthIndicatorDao.getHealthPagination(1, 15);
	
	    for (int i = 0; i < healthindicatorList.size(); i++) {
		
		sesion.setAttribute("healthindicatorList",healthindicatorList);
		
	}
	
	    String newindicatorcode = request.getParameter("indicatorcode");
	    String indicatorname = request.getParameter("indicatorname");
	    String sourcenote = request.getParameter("sourcenote");
	    String sourceorganization = request.getParameter("sourceorganization");
	    
	    HealthIndicator newindicator = new HealthIndicator(); 
	    
	    newindicator.setIndicador_code(newindicatorcode); 
	    newindicator.setIndicador_name(indicatorname); 
	    newindicator.setSource_note(sourcenote); 
	    newindicator.setSource_organization(sourceorganization);
	
	    healthIndicatorDao.addHealthIndicator(newindicator); 
	    
	    
	    
	    
	   
	    String indicatortodelete = request.getParameter("idindicator");
	    
	    healthIndicatorDao.deleteHealthIndicator(indicatortodelete);
	
	 
	
	}

	
	
	
	
	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
