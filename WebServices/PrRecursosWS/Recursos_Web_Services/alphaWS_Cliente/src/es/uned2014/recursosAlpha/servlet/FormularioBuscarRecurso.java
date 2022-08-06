package es.uned2014.recursosAlpha.servlet;

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

import org.eclipse.jdt.internal.compiler.ast.ArrayReference;

import es.uned2014.recursosAlpha.clasesCliente.Conjunto;

/**
 * Servlet implementation class FormularioBuscarRecurso
 * Se ejecuta cuando el usuario envía el formulario de búsqueda de Recurso.
 * Se recuperan los parámetros de búsqueda y se realiza una llamada al servicio web
 * correspondiente: buscarRecursos.
 * La respuesta obtenida (XML) se traduce a un objeto Conjunto y se pasa el resultado
 * de la búsqueda al archivo jsp.
 * 
 * @author	Alpha UNED 2014
 * @version	RecursosWS 1.0
 * @since 	Septiembre 2014
 */
public class FormularioBuscarRecurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormularioBuscarRecurso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// SE RECUPERAN VARIABLES: Paginación: por defecto, pag 1. 
		String paginaRecibida = request.getParameter("pagina"); // paginación

		Integer pagina = 1;
		if (paginaRecibida != null) {
			pagina = Integer.parseInt(paginaRecibida);
		}

		// SE RECUPERAN VARIABLES: String con la búsqueda, por defecto ""
		String busqueda = "";

		if (request.getParameter("busqueda") != null && !request.getParameter("busqueda").equals("")) {
			busqueda = request.getParameter("busqueda");
		}		

		// SE SOLICITA SERVICIO WEB
		// Se crea el cliente:
		Client cliente = ClientBuilder.newClient();
		
		// Se indica a dónde va a lanzar su consulta:
		WebTarget recurso = cliente.target("http://localhost:8080/alphaWS_Servidor/rest/buscarRecursos"
				+ "?pagina=" + pagina + "&busqueda=" + busqueda);
		
		// Se indica qué tipo de dato espera recibir:
		Builder constructor = recurso.request(MediaType.APPLICATION_XML);
		
		// Se ejecuta la consulta, indicando que es de tipo GET, y que la respuesta que se espera es String:
		String recursosXml = constructor.get(String.class);

		// Se cierra el cliente:
		cliente.close();
		
		
		// INTERPRETACIÓN XML
		// Se convierte XML --> Objeto (Conjunto):
		Conjunto conjunto = new Conjunto();
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Conjunto.class);
			Unmarshaller um = context.createUnmarshaller();
			conjunto = (Conjunto)um.unmarshal(new File("conjunto.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		// SE REDIRECCIONA:
		request.setAttribute("arrayRecursos", conjunto.getArrayRecursos());
		request.setAttribute("numFilas", conjunto.getNumeroFilas());
		RequestDispatcher rd = request.getRequestDispatcher("recursosBuscar.jsp"
				+ "?pagina=" + pagina + "&busqueda=" + busqueda);
		rd.forward(request, response);
	}		


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
