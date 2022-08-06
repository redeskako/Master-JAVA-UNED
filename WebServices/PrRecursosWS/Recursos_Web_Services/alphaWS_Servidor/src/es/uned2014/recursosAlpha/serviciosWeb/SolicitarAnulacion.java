package es.uned2014.recursosAlpha.serviciosWeb;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import es.uned2014.recursosAlpha.reserva.Reserva;

/**
 * Servicio Web para solicitar la anulacion de las de reservas de un usuario
 * 
 * @author Alpha UNED 2014
 * @version Recursos Servicio Web 1.0
 * @since Septiembre 2014
 */
@Path("/solicitarAnulacion")
public class SolicitarAnulacion {
	
	/**
	 * Solicita la anulacion de las reservas
	 * @param idReserva
	 * @param usuario
	 * @param url - URL de la pagina Mis Reservas del Cliente
	 * @return String con el c�digo HTML informativo o de error
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String solicitarAnulacion(@FormParam("idReserva") int idReserva, 
			@FormParam("usuario") String usuario, @FormParam("volver") String url ) {
		
		Reserva r = new Reserva();
		
		int idSolicitado = 0; // Id de la reserva editada
		
		// Lo primero, se comprueba si el usuario es el due�o de la reserva:
		boolean usuarioAnular = r.comprobarUsuario(usuario, idReserva);
		
		if (usuarioAnular){
			// Se solicita el cambio de estado a 'pendiente de anulacion' (4) y se almacena el id:
			idSolicitado = r.cambiarEstado(idReserva, 4);
			
			System.out.println("Se ha solicitado correctamente la anulaci�n de la reserva con ID " + idReserva 
					+ " del usuario '" + usuario + "'");
			return "<html>"
					+ "<head>"
						+ "<title>Gesti&oacute;n de Recursos</title>"
					+ "</head>"
					+ "<body>"
						+ "<div style='background-color:#5FA'>Se ha solicitado correctamente la anulaci&oacute;n"
						+ " de la reserva con ID " + idReserva + " del usuario '" + usuario + "'</div>"
						+ "<a  href='" + url + "' >Volver a Mis Reservas</a>"
					+ "</body></html>";	
		} else {
			System.out.println("ERROR al solicitar la anulaci�n");
			return "<html>"
					+ "<head>"
						+ "<title>Gesti&oacute;n de Recursos</title>"
					+ "</head>"
					+ "<body>"
						+ "<div style='background-color:#FAA'>Se ha producido un error al solicitar la anulaci&oacute;n"
						+ " de la reserva con ID " + idReserva + " del usuario '" + usuario + "'</div>"
						+ "<a  href='" + url + "' >Volver a Mis Reservas</a>"
					+ "</body></html>";	
		}

	} // end solicitarAnulacion

} // end class SolicitarAnulacion
