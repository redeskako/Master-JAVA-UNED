package es.uned2014.recursosalpha.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub;
import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.CrearReserva;
import es.uned2014.recursosalpha.serviciosweb.CrearReservaSoapStub.CrearReservaResponse;

/**
 * Servlet implementation class CrearReserva Se ejecuta cuando el usuario
 * cumplimenta el formulario de creación de nueva reserva. Se pasan los
 * parámetros recibidos al Servicio Web, y se obtiene como respuesta el ID de la
 * nueva reserva (ID=0 si ha habido error).
 * 
 * @author Alpha UNED 2014
 * @version RecursosWS 1.0
 * @since Octubre 2014
 */
public class NuevaReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NuevaReserva() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Se recuperan los valores del formulario:
		int usuId = Integer.parseInt(request.getParameter("usuario"));
		int recId = Integer.parseInt(request.getParameter("recurso"));
		String inicio = request.getParameter("inicio");
		String horaInicio = request.getParameter("horaInicio");
		String minutoInicio = request.getParameter("minutoInicio");
		String fin = request.getParameter("final");
		String horaFinal = request.getParameter("horaFinal");
		String minutoFinal = request.getParameter("minutoFinal");
		int estadoId = Integer.parseInt(request.getParameter("estado"));
		int sucursalId = Integer.parseInt(request.getParameter("sucursal"));

		int idNuevo;

		CrearReservaSoapStub c = new CrearReservaSoapStub();

		// Nueva Reserva:
		CrearReserva crearR = new CrearReserva();
		crearR.setIdUsuario(usuId);
		crearR.setIdRecurso(recId);
		crearR.setDiaInicioSt(inicio);
		crearR.setHoraInicioSt(horaInicio);
		crearR.setMinInicioSt(minutoInicio);
		crearR.setDiaFinalSt(fin);
		crearR.setHoraFinalSt(horaFinal);
		crearR.setMinFinalSt(minutoFinal);
		crearR.setIdEstado(estadoId);
		crearR.setIdSucursal(sucursalId);

		CrearReservaResponse crearResp = c.crearReserva(crearR);

		idNuevo = crearResp.get_return();

		// Si el nuevo id es 0, ha habido un error al crear la reserva. Si no, todo ha ido bien.
		response.sendRedirect("reservasCrearInfo.jsp?id=" + idNuevo);
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
