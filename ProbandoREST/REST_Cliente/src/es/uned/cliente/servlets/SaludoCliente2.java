package es.uned.cliente.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Servlet implementation class Saludar
 * 
 * @author	Cristina Morales
 * @version	Pruebas 1.0
 * @since	Septiembre 2014
 */
public class SaludoCliente2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaludoCliente2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre = request.getParameter("nombre");
		
		// Se crea el cliente:
		Client cliente = ClientBuilder.newClient();
		
		// Se indica a dónde va a lanzar su consulta:
		WebTarget recurso = cliente.target("http://localhost:8080/REST_Servidor/rest/saludo2?nombre=" + nombre);
		
		// Se indica qué tipo de dato espera recibir:
		Builder constructor = recurso.request(MediaType.TEXT_PLAIN);
		
		// Se ejecuta la consulta, indicando que es de tipo GET, y que la respuesta que se espera es String:
		String mensaje = constructor.get(String.class);

		// Se cierra el cliente:
		cliente.close();
		
		
		
		
		// Se redirecciona:
		request.setAttribute("mensaje", mensaje);
		RequestDispatcher rd = request.getRequestDispatcher("mensaje.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
