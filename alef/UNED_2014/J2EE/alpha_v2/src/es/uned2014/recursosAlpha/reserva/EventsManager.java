package es.uned2014.recursosAlpha.reserva;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;

import es.uned2014.recursosAlpha.usuario.Usuario;

/***
 * EventsManager class is extending DHXEventsManager.
 * 
 * Esta clase es usada para gestionar la vista del Java Planner y sus callbacks
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @fecha 	November 2016
 *
 */

public class EventsManager extends DHXEventsManager {
	Usuario usuario;
	HttpServletResponse response;
	HttpServletRequest request;

	public EventsManager(HttpServletRequest request, HttpServletResponse response) {
		super(request);
		this.request = request;
		this.response = response;
		
		HttpSession session = request.getSession();
		Integer id = (Integer)session.getAttribute("idSesion");
		if(id != null)
			usuario = Usuario.getUserById(id);
	}
	
	/***
	 * Funcion convierte todas las reservas en objetos evento
	 * Es usada para mostrar todos los evenetos en la vista del calendario
	 * 
	 */
	@Override
	public Iterable<DHXEv> getEvents() {
		ArrayList<DHXEv> events = new ArrayList<DHXEv>();
		if(usuario == null) return events;
		
		Reserva reservaObjeto = new Reserva();
		ArrayList<Reserva> reservas;
		if(usuario.getIdRol() == Usuario.ROLE_RESPONSABLE){
			reservas = reservaObjeto.reservasListado(0, 0);
		}else{
			String sql = reservaObjeto.sqlBuscadorReservas(Usuario.ROLE_RESPONSABLE, usuario.getIdUsuario(), 
					0, null, null, null, null, 0);
			reservas = reservaObjeto.reservasBuscar(0, sql);
		}
		Evento ev;
		for(Reserva reserva:reservas){
			ev = new Evento();
			ev.setId(reserva.getIdReserva());
			ev.setStart_date(reserva.getInicio());
			ev.setEnd_date(reserva.getFin());
			ev.setText(reserva.getDescripcionRecurso());
			ev.setEstado(reserva.getEstado());
			ev.setUsuario(reserva.getNombreUsuario());
			events.add(ev);
		}
		return events;
	}
	
	/**
	 * Esta funcion es llamada siempre que alguien quiere cambiar un eveneto en el calendario
	 * 
	 */
	@Override
	public DHXStatus saveEvent(DHXEv event, DHXStatus status) {
		return status;
	}
}
