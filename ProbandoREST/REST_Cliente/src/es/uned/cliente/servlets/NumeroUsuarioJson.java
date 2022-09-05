package es.uned.cliente.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.uned.clasesCliente.UsuarioCliente;

/**
 * Servlet implementation class NumeroUsuarioJson
 * 
 * @author	Cristina Morales
 * @version	Pruebas 1.0
 * @since	Septiembre 2014
 */
public class NumeroUsuarioJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NumeroUsuarioJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String num = request.getParameter("num");
System.out.println("paso 1");
		// Se crea el cliente:
		Client cliente = ClientBuilder.newClient();
System.out.println("paso 2");		
		// Se indica a dónde va a lanzar su consulta:
		WebTarget recurso = cliente.target("http://localhost:8080/REST_Servidor/rest/numeroUsuario3?num=" + num);
System.out.println("paso 3");
		// Se indica qué tipo de dato espera recibir:
		Builder constructor = recurso.request(MediaType.APPLICATION_JSON);
System.out.println("paso 4");
		// Se ejecuta la consulta, indicando que es de tipo GET, y que la respuesta que se espera es String:
		String usuarioJson = constructor.get(String.class);
System.out.println("paso 5");
		// Se cierra el cliente:
		cliente.close();
		
		
		// Se convierte JSON --> Objeto:
		UsuarioCliente u = new UsuarioCliente();
		ObjectMapper mapper = new ObjectMapper();
		try {
			File file = new File("uEncontrado.json");
			u = mapper.readValue(file, UsuarioCliente.class);
			
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Se redirecciona:
		request.setAttribute("usuario", u);
		RequestDispatcher rd = request.getRequestDispatcher("mostrarUsuario.jsp");
		rd.forward(request, response);
	}

}
