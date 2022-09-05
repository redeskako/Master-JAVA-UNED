package es.uned2014.recursosAlpha.serviciosWeb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import es.uned2014.recursosAlpha.reserva.Reserva;
import es.uned2014.recursosAlpha.usuario.Usuario;

/**
 * Servicio Web para la edici�n de una nueva reserva.
 * @author	Alpha UNED 2014
 * @version	Recursos Servicio Web 1.0
 * @since 	Septiembre 2014
 */
@Path("/editarReserva")
public class EditarReserva {
	
	/**
	 * Edita una reserva con los datos recuperados del formulario
	 * @param idUsuario
	 * @param idRecurso
	 * @param diaInicioSt
	 * @param horaInicioSt
	 * @param minInicioSt
	 * @param diaFinalSt
	 * @param horaFinalSt
	 * @param minFinalSt
	 * @param idEstado
	 * @param idSucursal
	 * @param url - URL de la pagina Mis Peticiones del Cliente
	 * @param idReserva
	 * @return String con el c�digo HTML informativo o de error (por incompatibilidad)
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String editarReserva(@FormParam("usuario") int idUsuario, 
			@FormParam("recurso") int idRecurso, @FormParam("inicio") String diaInicioSt, 
			@FormParam("horaInicio") String horaInicioSt, @FormParam("minutoInicio") String minInicioSt, 
			@FormParam("final") String diaFinalSt, @FormParam("horaFinal") String horaFinalSt,
			@FormParam("minutoFinal") String minFinalSt, @FormParam("estado") int idEstado, 
			@FormParam("sucursal") int idSucursal, @FormParam("reserva") int idReserva,
			@FormParam("volverIndex") String url) {
		
		Reserva r = new Reserva();
		
		int idEditado = 0; // Id de la reserva editada
		Usuario u = new Usuario();
		String usuario = u.getNombreUsuario(idUsuario);

		// Lo primero, se comprueba si el usuario es el due�o de la reserva:
		boolean usuarioEditar = r.comprobarUsuario(usuario, idReserva);
		
		// Se transforman las fechas:
		String inicioStEdit = diaInicioSt + " " + horaInicioSt + ":" + minInicioSt + ":00";
		String finStEdit = diaFinalSt + " " + horaFinalSt + ":" + minFinalSt + ":00";
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if (usuarioEditar){
			// Se contin�a:
			// Se transforman las fechas:
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {	 
				Date inicioEdit = df.parse(inicioStEdit);
				Date finEdit = df.parse(finStEdit);
			
				// Se edita la reserva y se recupera su id:
				idEditado =  r.editarReserva(idReserva, idUsuario, idRecurso, inicioEdit, 
					finEdit, idEstado, idSucursal);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			// Si el nuevo id es 0, ha habido un error al editar. Si no, todo ha ido bien.
			System.out.println("Se ha editado la reserva con ID " + idEditado);
			
			return "<html>"
			+ "<head>"
				+ "<title>Gesti&oacute;n de Recursos</title>"
			+ "</head>"
			+ "<body>"
				+ "<div style='background-color:#5FA'>Se ha editado la reserva con ID '" + idEditado + "'</div>"
				+ "<a  href='" + url + "' >Volver a Mis Peticiones</a>"
			+ "</body></html>";	
		} else {
			System.out.println("ERROR al editar la reserva");
			return "<html>"
					+ "<head>"
						+ "<title>Gesti&oacute;n de Recursos</title>"
					+ "</head>"
					+ "<body>"
						+ "<div style='background-color:#FAA'>Se ha producido un error al editar la reserva</div>"
						+ "<a  href='" + url + "' >Volver a Mis Peticiones</a>"
					+ "</body></html>";	
		}
		
	} // end editarReserva

} // end class EditarReserva
