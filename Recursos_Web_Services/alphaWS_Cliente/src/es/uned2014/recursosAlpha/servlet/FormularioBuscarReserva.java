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

import es.uned2014.recursosAlpha.clasesCliente.Conjunto;

/**
 * Servlet implementation class FormularioBuscarReserva
 * Se ejecuta cuando el usuario envía el formulario de búsqueda de Reserva.
 * Se recuperan los parámetros de búsqueda y se realiza una llamada al servicio web
 * correspondiente: buscarReservas.
 * La respuesta obtenida (XML) se traduce a un objeto Conjunto y se pasa el resultado
 * de la búsqueda al archivo jsp.
 * 
 * @author	Alpha UNED 2014
 * @version	RecursosWS 1.0
 * @since 	Septiembre 2014
 */
public class FormularioBuscarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormularioBuscarReserva() {
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

		// SE RECUPERAN VARIABLES: ids de usuario, recurso y sucursal
		Integer idUsuario = 0;
		Integer idRecurso = 0;
		Integer idSucursal = 0;

		if (request.getParameter("usuario") != null && !request.getParameter("usuario").equals("")) {
			idUsuario = Integer.parseInt(request.getParameter("usuario"));
		}
		
		if (request.getParameter("recurso") != null && !request.getParameter("recurso").equals("")) {
			idRecurso = Integer.parseInt(request.getParameter("recurso"));
		}
		
		if (request.getParameter("sucursal") != null && !request.getParameter("sucursal").equals("")) {
			idSucursal = Integer.parseInt(request.getParameter("sucursal"));
		}
		
		// SE SOLICITA SERVICIO WEB
		// Se crea el cliente:
		Client cliente = ClientBuilder.newClient();
		
		// Se indica a dónde va a lanzar su consulta:
		WebTarget recurso = cliente.target("http://localhost:8080/alphaWS_Servidor/rest/buscarReservas"
				+ "?pagina=" + pagina + "&usuario=" + idUsuario + "&recurso=" + idRecurso + "&sucursal=" + idSucursal);
		
		// Se indica qué tipo de dato espera recibir:
		Builder constructor = recurso.request(MediaType.APPLICATION_XML);
		
		// Se ejecuta la consulta, indicando que es de tipo GET, y que la respuesta que se espera es String:
		String reservasXml = constructor.get(String.class);

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
		request.setAttribute("arrayUsuarios", conjunto.getArrayUsuarios()); // para el combo
		request.setAttribute("arrayRecursos", conjunto.getArrayRecursos()); // para el combo
		request.setAttribute("arraySucursales", conjunto.getArraySucursales()); // para el combo
		
		request.setAttribute("arrayReservas", conjunto.getArrayReservas());
		request.setAttribute("numFilas", conjunto.getNumeroFilas());
		RequestDispatcher rd = request.getRequestDispatcher("reservasBuscar.jsp"
				+ "?pagina=" + pagina + "&usuario=" + idUsuario + "&recurso=" + idRecurso + "&sucursal=" + idSucursal);
		rd.forward(request, response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
