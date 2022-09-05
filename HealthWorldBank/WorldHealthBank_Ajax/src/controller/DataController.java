package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CountryDao;
import dao.DataDao;
import dao.HealthIndicatorDao;
import model.Country;
import model.Data;
import model.HealthIndicator;

/**
 * Servlet implementation class data
 */
@WebServlet("/data")
public class DataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataDao dataDao;
	private CountryDao countryDao;
	private HealthIndicatorDao healthIndicatorDao;
	private static String LIST_DATA = "/data/data.jsp";
	private static String INSERT_OR_EDIT = "/data/addData.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataController() {
        super();
        // TODO Auto-generated constructor stub
        dataDao = new DataDao();
        countryDao = new CountryDao();
        healthIndicatorDao = new HealthIndicatorDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession sesion = request.getSession(true);
		
		
		
		
		String forward="";
        String action = request.getParameter("action");
        forward = LIST_DATA;
        
        if(action.equals("delete")){
	        String indicador = (request.getParameter("Indicador"));
	        String code = (request.getParameter("code"));
	        int year = Integer.parseInt(request.getParameter("year"));
	        dataDao.deleteData(indicador,code,year);
	        //forward = LIST_DATA;
	        //request.setAttribute("datas", dataDao.getDataPagination(0, 20));
	        response.sendRedirect("/WorldHealthBank_Boza/data?action=get&pag=1");
	        
        }
		else if(action.equals("get")){
        	 int pag = Integer.parseInt(request.getParameter("pag"));
			 sesion.setAttribute("datas", dataDao.getDataPagination(pag-1, 20));
			 sesion.setAttribute("paginaActual", pag);
			 sesion.setAttribute("numeroPaginas", (dataDao.getNumeroPaginas()%20 == 0)?dataDao.getNumeroPaginas()/20:dataDao.getNumeroPaginas()/20+1);
		        RequestDispatcher view = request.getRequestDispatcher(forward);
		        view.forward(request, response);
		        
		 }
		else if(action.equals("edit")){
				forward = INSERT_OR_EDIT;
				Data data = new Data();
				if(request.getParameter("Indicador") != ""){
					String indicador = (request.getParameter("Indicador"));
				    String code = (request.getParameter("code"));
				    int year = Integer.parseInt(request.getParameter("year"));
				    data = dataDao.geteOneData(indicador, code, year);
				}else{
					data.setCountry_code(null);
					data.setIndicador_code(null);
					data.setPercentage(0);
					data.setYear(0);
				}
	            sesion.setAttribute("data", data);
	            sesion.setAttribute("countries", countryDao.getAllCountry());
	            sesion.setAttribute("healthIndicators", healthIndicatorDao.getAllHealth());
	            
	            sesion.setAttribute("numeroPaginas", (dataDao.getNumeroPaginas()%20 == 0)?dataDao.getNumeroPaginas()/20:dataDao.getNumeroPaginas()/20+1);
	            RequestDispatcher view = request.getRequestDispatcher(forward);
	            view.forward(request, response);
	            
			
		}
		
        
       
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Data data = new Data();
			
		data.setIndicador_code((request.getParameter("indicador")));
	    data.setCountry_code(request.getParameter("country"));
	    data.setYear(Integer.parseInt(request.getParameter("year")));
	    data.setPercentage(Float.parseFloat((request.getParameter("percentage"))));
	   
	    if(data.getIndicador_code() != null || data.getCountry_code() != null || data.getYear() != 0){
	    
	        if(dataDao.geteOneData((request.getParameter("indicador")), request.getParameter("country"), Integer.parseInt(request.getParameter("year"))).getIndicador_code() != null)
	        {
	            dataDao.updateData(data);
	        }
	        else
	        {
	            dataDao.createData(data);
	        }
	        response.sendRedirect("/WorldHealthBank_Boza/data?action=get&pag=1");
	    }else{
	    	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error: Faltan campos obligatorios");
	    }
        
    }
	
	
	
	

}
