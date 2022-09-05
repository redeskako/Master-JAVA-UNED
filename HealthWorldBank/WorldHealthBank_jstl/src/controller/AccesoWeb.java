package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * Servlet implementation class AccesoWeb
 */
@WebServlet("/AccesoWeb")
public class AccesoWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao dao;
    private DataDao dataDao;
    private CountryDao countryDao;
    private HealthIndicatorDao healthIndicatorDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccesoWeb() {
        super();
        dao = new UserDao();
        dataDao = new DataDao();
        countryDao = new CountryDao();
        healthIndicatorDao = new HealthIndicatorDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession(true); //almaceno la sesion

		String usuarioAcceso = request.getParameter("username"); //almaceno el usuario

		String passAcceso = request.getParameter("password"); //almaceno el pass
			
		User usuario = new User();
        usuario.setUser(usuarioAcceso);
        usuario.setPassword(passAcceso);
        
        String tipoUsuario = dao.getSession(usuario);
		
        if (tipoUsuario.equals("BAD_USER")) {
        	response.sendRedirect("index.jsp?estado=1");
        } else {
        	List<Country> listadoPaises = countryDao.getCountryPagination(0, 500);
        	List<HealthIndicator> listadoIndicador = healthIndicatorDao.getHealthPagination(0,500);
        	List<Data> listadoDatas = dataDao.getDataPagination(0, 500);
        	
        	//añadimos como atributo el objeto con el arraylist de los paises
        	sesion.setAttribute("listaPaises",listadoPaises);
		
        	        	//añadimos como atributo el objeto con el arraylist de los indicadores
        	sesion.setAttribute("listaIndicador",listadoIndicador);
		
        	response.sendRedirect("main.jsp?estado=1");
        }
        
        
        sesion.setAttribute("usuarioAcceso",usuarioAcceso);
        
        
        

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
