package es.uned2014.recursosAlpha.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import es.uned2014.recursosAlpha.clasesCliente.Conjunto;

/**
 * Servlet implementation class MisPeticiones Se ejecuta cuando el usuario
 * selecciona "Mis Peticiones" en el menú. Se recupera el nombreUsuario logueado y
 * se realiza una llamada al servicio web correspondiente: misPeticiones. 
 * La respuesta obtenida (XML) se traduce a un objeto Conjunto y se pasa el
 * resultado de la búsqueda al archivo jsp.
 * 
 * @author Alpha UNED 2014
 * @version RecursosWS 1.0
 * @since 	Septiembre 2014
 */
public class MisPeticiones extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MisPeticiones() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// SE RECUPERAN VARIABLES: Paginación: por defecto, pag 1.
		String paginaRecibida = request.getParameter("pagina"); // paginación

		Integer pagina = 1;
		if (paginaRecibida != null) {
			pagina = Integer.parseInt(paginaRecibida);
		}

		// SE RECUPERAN VARIABLES: String con el nombreUsuario del usuario logueado
		String usuario = "";
		HttpSession session = request.getSession();	
		
		if (session.getAttribute("idSesion") != null
				&& !session.getAttribute("idSesion").equals("")) {
			usuario = (String)session.getAttribute("idSesion");
		}

		// SE SOLICITA SERVICIO WEB
		// Se crea el cliente:
		Client cliente = ClientBuilder.newClient();

		// Se indica a dónde va a lanzar su consulta:
		WebTarget recurso = cliente.target("http://localhost:8080/alphaWS_Servidor/rest/misPeticiones"
						+ "?pagina=" + pagina + "&usuario=" + usuario);

		// Se indica qué tipo de dato espera recibir:
		Builder constructor = recurso.request(MediaType.APPLICATION_XML);

		// Se ejecuta la consulta, indicando que es de tipo GET, y que la
		// respuesta que se espera es String:
		String misPeticionesXml = constructor.get(String.class);

		// Se cierra el cliente:
		cliente.close();

		// INTERPRETACIÓN XML
		// Se convierte XML --> Objeto (Conjunto):
		Conjunto conjunto = new Conjunto();
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Conjunto.class);
			Unmarshaller um = context.createUnmarshaller();
			conjunto = (Conjunto) um.unmarshal(new File("conjunto.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// SE REDIRECCIONA:
		request.setAttribute("arrayReservas", conjunto.getArrayReservas());
		request.setAttribute("numFilas", conjunto.getNumeroFilas());
		RequestDispatcher rd = request.getRequestDispatcher("misPeticiones.jsp"
				+ "?pagina=" + pagina + "&usuario=" + usuario);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
