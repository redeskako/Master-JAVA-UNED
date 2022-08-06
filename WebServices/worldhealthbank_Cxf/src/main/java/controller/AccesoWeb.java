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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<User> user1 = dao.getAllUser();
		System.out.println("Holas"+user1);
		
		response.getWriter().append("Served at: ").append(request.getContextPath()); //El contexto es Prueba_web
		
				
		HttpSession sesion = request.getSession(true); //almaceno la sesion
		
		
		String usuarioAcceso = request.getParameter("username"); //almaceno el usuario
		response.getWriter().append("\nUSUARIO: " + usuarioAcceso); //visualizao el valor del usuario

		String passAcceso = request.getParameter("password"); //almaceno el pass
		response.getWriter().append("\nPASSWORD: " + passAcceso); //visualizao el valor del pass
				
		User usuario = new User();
        usuario.setUser(usuarioAcceso);
        usuario.setPassword(passAcceso);
        
        String tipoUsuario = dao.getSession(usuario);
       //System.out.println("\nEl tipo de usuario es: " + tipoUsuario);
		
        if (tipoUsuario.equals("BAD_USER")) {
        	response.sendRedirect("index.jsp?estado=1");
        } else {
        	List<Country> listadoPaises = countryDao.getCountryPagination(0, 500);
        	List<HealthIndicator> listadoIndicador = healthIndicatorDao.getHealthPagination(0,100);
        
        	//Iteramos el arraylist de paises
        	//System.out.println("Using for loop for listadoPaises:");
        	for (int i = 0; i < listadoPaises.size(); i++) {
        		//Iteramos el arraylist de paises
        		//System.out.println("LISTAPAISES: " + listadoPaises.get(i).toString());
        	}
        	//añadimos como atributo el objeto con el arraylist de los paises
        	sesion.setAttribute("listaPaises",listadoPaises);
		
        	//Iteramos el arraylist de indicador
        	//System.out.println("Using for loop for listadoIndicador:");
        	for (int i = 0; i < listadoIndicador.size(); i++) {
        		//Iteramos el arraylist de indicadores
        		//System.out.println("LISTAINDICADOR: " + listadoIndicador.get(i).toString());
        	}
        	//añadimos como atributo el objeto con el arraylist de los indicadores
        	sesion.setAttribute("listaIndicador",listadoIndicador);
		
        	//pasar el arraylist como request es imposible por el tamaño de la url
        	//response.sendRedirect("Respuesta.jsp?estado=3&tipo="+tipo);
        	response.sendRedirect("main.jsp?estado=1");
        }
        
            sesion.setAttribute("usuarioAcceso",usuarioAcceso);
        
        /*
		usuario usu = new usuario(usuario, pass);
		country pai = new country();
		health_indicator ind = new health_indicator();
		//usu = defino el objeto usuario
		//pai = defino el objeto country
		//ind = defino el objeto health_indicator
		
		if (usu.validarUsuario(usuario, pass)) {
			//obtengo el tipo de usuario: admin o usuario
			String tipo = usu.obtenerTipo(usuario, pass);
			
			//obtengo todos los listados con la info de 3 tablas; usuarios, country y health_indicator
			ArrayList<usuario> listado1 = usu.listadoUsuarios("select * from usuario");
			ArrayList<country> listado2 = pai.listadoPaises("select * from country");
			ArrayList<health_indicator> listado3 = ind.listadoIndicador("select * from health_indicator");
			
			//añado atributos a la sesion
			sesion.setAttribute("EstadoSesion","3");
			sesion.setAttribute("TipoSesion",tipo);
			
			//Iteramos el arraylist de usuarios
	        System.out.println("Using for loop for listado1:");
	        for (int i = 0; i < listado1.size(); i++) {
	        	//Iteramos el arraylist de usuarios
	            System.out.println("LISTAUSER: " + listado1.get(i).toString());
	        }
	        //añadimos como atributo el objeto con el arraylist de los usuarios
			sesion.setAttribute("listaUsuarios",listado1);
			
			//Iteramos el arraylist de paises
	        System.out.println("Using for loop for listado2:");
	        for (int i = 0; i < listado2.size(); i++) {
	        	//Iteramos el arraylist de paises
	            System.out.println("LISTAPAISES: " + listado2.get(i).toString());
	        }
	        //añadimos como atributo el objeto con el arraylist de los paises
			sesion.setAttribute("listaPaises",listado2);
			
			//Iteramos el arraylist de indicador
	        System.out.println("Using for loop for listado3:");
	        for (int i = 0; i < listado3.size(); i++) {
	        	//Iteramos el arraylist de indicadores
	        	System.out.println("LISTAINDICADOR: " + listado3.get(i).toString());
	        }
	        //añadimos como atributo el objeto con el arraylist de los indicadores
			sesion.setAttribute("listaIndicador",listado3);
			
			//pasar el arraylist como request es imposible por el tamaño de la url
			//response.sendRedirect("Respuesta.jsp?estado=3&tipo="+tipo+"&lista1="+listado1+"&lista2="+listado2);
			response.sendRedirect("Respuesta.jsp?estado=3&tipo="+tipo);
		} else {
			System.out.println("usuario invalido!!");
			response.sendRedirect("Respuesta.jsp?estado=1");
		}
			
			/*if (parametro.equalsIgnoreCase("Acceso1")){
			sesion.setAttribute("EstadoSesion","1"); //adjunto info a la sesion
			response.sendRedirect("Respuesta.jsp?estado=1"); //abro la respuesta y ademas de la sesion y la cookie tb le paso info por parametro
		}else {
			sesion.setAttribute("EstadoSesion","2");
			response.sendRedirect("Respuesta.jsp?estado=2");
		}*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
