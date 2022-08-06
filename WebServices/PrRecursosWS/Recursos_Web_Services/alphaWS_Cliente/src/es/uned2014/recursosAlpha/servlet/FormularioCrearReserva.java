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
 * Servlet implementation class FormularioCrearReserva
 * Se ejecuta cuando el usuario desea cargar el formulario de crear nueva Reserva.
 * Se realiza una llamada al servicio web correspondiente: combosFormulario.
 * La respuesta obtenida (XML) se traduce a un objeto Conjunto y se pasa el resultado
 * al archivo jsp.
 * 
 * @author	Alpha UNED 2014
 * @version	RecursosWS 1.0
 * @since 	Septiembre 2014
 */
public class FormularioCrearReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	public FormularioCrearReserva() {
		super();
        // TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Se crea el cliente:
		Client cliente = ClientBuilder.newClient();
		
		// Se indica a dónde va a lanzar su consulta:
		WebTarget recurso = cliente.target("http://localhost:8080/alphaWS_Servidor/rest/combosFormulario");
		
		// Se indica qué tipo de dato espera recibir:
		Builder constructor = recurso.request(MediaType.APPLICATION_XML);
		
		// Se ejecuta la consulta, indicando que es de tipo GET, y que la respuesta que se espera es String:
		String cargarListados = constructor.get(String.class);

		// Se cierra el cliente:
		cliente.close();
		
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
		
		// Se redirecciona:
		request.setAttribute("arrayUsuarios", conjunto.getArrayUsuarios());
		request.setAttribute("arrayReservas", conjunto.getArrayReservas());
		request.setAttribute("arrayRecursos", conjunto.getArrayRecursos());
		request.setAttribute("arrayEstados", conjunto.getArrayEstados());
		request.setAttribute("arraySucursales", conjunto.getArraySucursales());
		RequestDispatcher rd = request.getRequestDispatcher("reservasCrear.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
} // end class CargarListadosWS
