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
 * Clase encargada de la gestion del acceso a la web
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
     * Constructor del servlet AccesoWeb con la generación de los datos
     * @see HttpServlet#HttpServlet()
     */
    public AccesoWeb() {
        super();
        dao = new UserDao();
        dataDao = new DataDao();
        countryDao = new CountryDao();
        healthIndicatorDao = new HealthIndicatorDao();
    }

	/**
	 * Método DoGet del servlet AccesoWeb
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Visualizacion del contexto es Prueba_web
		response.getWriter().append("Served at: ").append(request.getContextPath()); 	
		//captura de la sesion
		HttpSession sesion = request.getSession(true); 
		
		//captura del usuario
		String usuarioAcceso = request.getParameter("username"); 
		//visualizacion del valor del usuario en la web
		response.getWriter().append("\nUSUARIO: " + usuarioAcceso); 

		//captura del pass
		String passAcceso = request.getParameter("password");
		//visualizacion el valor del pass en la web
		response.getWriter().append("\nPASSWORD: " + passAcceso); 
				
		//Creacion de un nuevo usuario con los datos que se reciben en la request (user+pass)
		User usuario = new User();
        usuario.setUser(usuarioAcceso);
        usuario.setPassword(passAcceso);
        
        //Validacion del usuario
        String tipoUsuario = dao.getSession(usuario);
        if (tipoUsuario.equals("BAD_USER")) {
        	response.sendRedirect("index.jsp?estado=1");
        } else {
        	List<Country> listadoPaises = countryDao.getCountryPagination(0, 500);
        	List<HealthIndicator> listadoIndicador = healthIndicatorDao.getHealthPagination(0,500);
        	List<Data> listadoDatas = dataDao.getDataPagination(0, 500);
        	
        	//añadimos como atributo el objeto con el list de los paises
        	sesion.setAttribute("listaPaises",listadoPaises);
		
        	//añadimos como atributo el objeto con el list de los indicadores
        	sesion.setAttribute("listaIndicador",listadoIndicador);
		
        	//pasar el list como request es imposible por el tamaño de la url
        	response.sendRedirect("main.jsp?estado=1");
        }
        
        //Mantenemos el usuario en la sesion durante la navegación sobre la web
        sesion.setAttribute("usuarioAcceso",usuarioAcceso);

	}

	/**
	 * Método DoPost del servlet AccesoWeb que llama al método DoGet
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Llamada al método anterior
		doGet(request, response);
	}

}
