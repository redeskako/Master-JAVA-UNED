package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import dao.CountryDao;
import dao.DataDao;
import dao.HealthIndicatorDao;
import model.Country;
import model.Data;
import model.HealthIndicator;



/**
 * Servlet implementation class GetEstadisticas
 */
@WebServlet("/GetEstadisticas")
public class GetEstadisticas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEstadisticas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		HttpSession sesion = request.getSession(true);
		
		String pais = request.getParameter("pais"); //almaceno el valor del pais seleccionado
		response.getWriter().append("\nPAIS: " + pais); //visualizao el valor del pais
		String indicador = request.getParameter("indicador"); //almaceno el valor del indicador seleccionado
		response.getWriter().append("\nINDICADOR: " + indicador); //visualizao el valor del indicador
		
		//Creo el objeto para interactuar con la estadisticas y almaceno el arraylist de las estadisticas en funcion de pais e indicador
		DataDao dat = new DataDao();
		List<Data> listado = dat.getDataIndicadorCountry(indicador, pais);

		//tambien necesito los datos completos del pais necesario para google maps
		CountryDao pai = new CountryDao();
		List<Country> listado2 = pai.getCountry(pais);
		
		//tambien necesito los datos completos del indicador necesario para mostrarlo en el titulo del jsp
		HealthIndicatorDao ind = new HealthIndicatorDao();
		HealthIndicator listado3 = ind.getHealthIndicatorByCode(indicador);

		//visualizo el listado de data obtenido
        /*System.out.println("Using for loop for listado:");
        for (int i = 0; i < listado.size(); i++) {
            System.out.println("LISTADATA: " + listado.get(i).toString());
        }*/
        
        //visualizo la información del pais obtenida
        /*System.out.println("Using for loop for listado2:");
        for (int i = 0; i < listado2.size(); i++) {
            System.out.println("LISTAPAIS: " + listado2.get(i).toString());
        }*/
		
		if ((listado2.size() == 0) || (listado3.getIndicator_code() == null)) {
			System.out.println("ERROR: Seleccion incorrecta");
			response.sendRedirect("main.jsp?estado=2");
		} else if (listado.size() == 0){
			System.out.println("ERROR: Datos inexistentes");
			response.sendRedirect("main.jsp?estado=3");
		} else {
			sesion.setAttribute("listaData",listado);
			sesion.setAttribute("listaPais",listado2);
			sesion.setAttribute("Indicador",listado3);
			response.sendRedirect("grafica.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}



