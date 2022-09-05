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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import es.uned.clasesCliente.UsuarioCliente;
import es.uned.clasesCliente.UsuarioConjuntoCliente;

/**
 * Servlet implementation class UsuariosXml
 * 
 * @author	Cristina Morales
 * @version	Pruebas 1.0
 * @since	Septiembre 2014
 */
public class UsuariosXml extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuariosXml() {
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
		
		// Se crea el cliente:
		Client cliente = ClientBuilder.newClient();
		
		// Se indica a dónde va a lanzar su consulta:
		WebTarget recurso = cliente.target("http://localhost:8080/REST_Servidor/rest/usuariosListado1");
		
		// Se indica qué tipo de dato espera recibir:
		Builder constructor = recurso.request(MediaType.APPLICATION_XML);
		
		// Se ejecuta la consulta, indicando que es de tipo GET, y que la respuesta que se espera es String:
		String usuariosXML = constructor.get(String.class);

		// Se cierra el cliente:
		cliente.close();
		
		
		// Se convierte XML --> Objeto (Usuario):
		UsuarioConjuntoCliente uConjunto = new UsuarioConjuntoCliente();
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(UsuarioConjuntoCliente.class);
			Unmarshaller um = context.createUnmarshaller();
			uConjunto = (UsuarioConjuntoCliente)um.unmarshal(new File("uConjunto.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		// Se redirecciona:
		request.setAttribute("uListado", uConjunto.getListado());
		RequestDispatcher rd = request.getRequestDispatcher("mostrarUsuariosListado.jsp");
		rd.forward(request, response);
		
		
	}

}
