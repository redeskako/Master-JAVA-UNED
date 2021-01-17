package es.uned2014.recursosAlpha.serviciosWeb;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import es.uned2014.recursosAlpha.reserva.Reserva;

/**
 * Servicio Web para eliminar una peticion pendiente de un usuario
 * 
 * @author Alpha UNED 2014
 * @version Recursos Servicio Web 1.0
 * @since Septiembre 2014
 */
@Path("/eliminar")
public class Eliminar {
	
	/**
	 * Elimina una reseva con los datos obtenidos del formulario
	 * @param idReserva
	 * @param usuario
	 * @param url - URL de la pagina Mis Peticiones del Cliente
	 * @return String con el c�digo HTML informativo o de error
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String eliminar(@FormParam("idReserva") int idReserva, @FormParam("usuario") String usuario, 
			@FormParam("volver") String url) {
		
		Reserva r = new Reserva();
	
		boolean eliminado = false; // reserva eliminada

		// Lo primero, se comprueba si el usuario es el due�o de la reserva:
		boolean usuarioEliminar = r.comprobarUsuario(usuario, idReserva);
		
		if (usuarioEliminar){
			// Se solicita eliminar una reserva:
			eliminado = r.eliminar(idReserva);
			
			System.out.println("Se ha eliminado correctamente la reserva");
			
			return "<html>"
			+ "<head>"
				+ "<title>Gesti&oacute;n de Recursos</title>"
			+ "</head>"
			+ "<body>"
				+ "<div style='background-color:#5FA'>Se ha eliminado correctamente la reserva</div>"
				+ "<a  href='" + url + "' >Volver a Mis Peticiones</a>"
			+ "</body></html>";	
		} else {
			System.out.println("ERROR al intentar eliminar una reserva");
			return "<html>"
					+ "<head>"
						+ "<title>Gesti&oacute;n de Recursos</title>"
					+ "</head>"
					+ "<body>"
						+ "<div style='background-color:#FAA'>Se ha producido un error al eliminar la reserva</div>"
						+ "<a  href='" + url + "' >Volver a Mis Peticiones</a>"
					+ "</body></html>";	
		}
		
	} // end eliminar

} // end class Eliminar
