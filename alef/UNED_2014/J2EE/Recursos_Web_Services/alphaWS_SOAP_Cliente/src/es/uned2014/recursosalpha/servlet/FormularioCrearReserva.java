package es.uned2014.recursosalpha.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub;
import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.ComboRecursos;
import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.ComboRecursosResponse;
import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.ComboUsuarios;
import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.ComboUsuariosResponse;
import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.ComboEstados;
import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.ComboEstadosResponse;
import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.ComboSucursales;
import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.ComboSucursalesResponse;
import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.Usuario;
import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.Recurso;
import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.Reserva;

/**
 * Servlet implementation class FormularioCrearReserva Se ejecuta cuando el
 * usuario desea cargar el formulario de crear nueva Reserva. Se realizan
 * diferentes llamadas a los métodos definidos en el Servicio Web de la
 * aplicación Servidor.
 * 
 * @author Alpha UNED 2014
 * @version RecursosWS 1.0
 * @since Octubre 2014
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		CrearReservaSoapStub c = new CrearReservaSoapStub();

		// Listado Usuarios:
		ComboUsuarios comboU = new ComboUsuarios();

		ComboUsuariosResponse usuResp = c.comboUsuarios(comboU);

		Usuario[] usuarios = usuResp.get_return();
		ArrayList<Usuario> arrayUsuarios = new ArrayList<Usuario>();
		for (int i = 0; i < usuarios.length; i++) {
			arrayUsuarios.add(usuarios[i]);
		}

		// Listado Recursos:
		ComboRecursos comboR = new ComboRecursos();

		ComboRecursosResponse recResp = c.comboRecursos(comboR);

		Recurso[] recursos = recResp.get_return();
		ArrayList<Recurso> arrayRecursos = new ArrayList<Recurso>();
		for (int i = 0; i < recursos.length; i++) {
			arrayRecursos.add(recursos[i]);
		}

		// Listado Estados:
		ComboEstados comboE = new ComboEstados();

		ComboEstadosResponse estResp = c.comboEstados(comboE);

		Reserva[] estados = estResp.get_return();
		ArrayList<Reserva> arrayEstados = new ArrayList<Reserva>();
		for (int i = 0; i < estados.length; i++) {
			arrayEstados.add(estados[i]);
		}

		// Listado Sucursales:
		ComboSucursales comboS = new ComboSucursales();

		ComboSucursalesResponse sucResp = c.comboSucursales(comboS);

		Reserva[] sucursales = sucResp.get_return();
		ArrayList<Reserva> arraySucursales = new ArrayList<Reserva>();
		for (int i = 0; i < sucursales.length; i++) {
			arraySucursales.add(sucursales[i]);
		}

		// Se redirecciona:
		request.setAttribute("arrayUsuarios", arrayUsuarios);
		request.setAttribute("arrayRecursos", arrayRecursos);
		request.setAttribute("arrayEstados", arrayEstados);
		request.setAttribute("arraySucursales", arraySucursales);
		RequestDispatcher rd = request
				.getRequestDispatcher("reservasCrear.jsp");
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

} // end class CargarListadosWS
