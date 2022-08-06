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
 * Servicio Web para la creaci�n de una nueva reserva.
 * @author	Alpha UNED 2014
 * @version	Recursos Servicio Web 1.0
 * @since 	Septiembre 2014
 */

//Se indica el Path para acceder al SW a trav�s de la URI:
@Path("/crearReserva")
public class CrearReserva {
	
	/**
	 * Crea una nueva reserva con los datos recuperados del formulario.
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
	 * @param urlIndex - URL de la p�gina de inicio del Cliente
	 * @return String con el c�digo HTML informativo o de error (por incompatibilidad)
	 */
	// M�todo a ejecutar si se pide XML a trav�s de un formulario (POST):
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String crearReserva(@FormParam("usuario") int idUsuario , @FormParam("recurso") int idRecurso, 
			@FormParam("inicio") String diaInicioSt, @FormParam("horaInicio") String horaInicioSt, 
			@FormParam("minutoInicio") String minInicioSt, @FormParam("final") String diaFinalSt, 
			@FormParam("horaFinal") String horaFinalSt,	@FormParam("minutoFinal") String minFinalSt,
			@FormParam("estado") int idEstado, @FormParam("sucursal") int idSucursal,
			@FormParam("volverIndex") String urlIndex){
		
		Reserva r = new Reserva();
		
		// Variable en la que se almacenar� el IdReserva de la nueva reserva
		int idNuevo = 0;
				
		// Se transforman las fechas:
		String inicioSt = diaInicioSt + " " + horaInicioSt + ":" + minInicioSt + ":00";
		String finalSt = diaFinalSt + " " + horaFinalSt + ":" + minFinalSt + ":00";
		
		SimpleDateFormat formato = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		try {
			Date inicio = formato.parse(inicioSt);
			Date fin = formato.parse(finalSt);

			// Se crea la nueva reserva y se recupera su id:
			idNuevo = r.nuevo(idUsuario, idRecurso, inicio, fin, idEstado, idSucursal);

		} catch (ParseException e) {
			idNuevo = 0;
			e.printStackTrace();
		}	
		
		// Si el nuevo id es 0, ha habido un error al crear la reserva. Si
		// no, todo ha ido bien.
		Usuario u = new Usuario();
		if (idNuevo != 0) {
			System.out.println("Se ha creado una nueva reserva con ID " + idNuevo 
					+ " para el usuario '" + u.getNombreUsuario(idUsuario) + "'");
			return "<html>"
					+ "<head>"
						+ "<title>Gesti&oacute;n de Recursos</title>"
					+ "</head>"
					+ "<body>"
					/*	+ "<div><script type='application/javascript'>"
							+ "alert('Se ha creado una nueva reserva con ID " + idNuevo 
							+ " para el usuario " + u.getNombreUsuario(idUsuario) + "');"
						+ "</script></div>"*/
						+ "<div style='background-color:#5FA'>Se ha creado una nueva reserva con ID " + idNuevo 
						+ " para el usuario '" + u.getNombreUsuario(idUsuario) + "'</div>"
	
						+ "<a  href='" + urlIndex + "' >Volver al inicio</a>"
					+ "</body></html>";		
		} else {
			System.out.println("ERROR al crear una reserva");
			return "<html>"
					+ "<head>"
						+ "<title>Gesti&oacute;n de Recursos</title>"
					+ "</head>"
					+ "<body>"
						+ "<div style='background-color:#FAA'>Se ha producido un error al crear la reserva</div>"
						+ "<a  href='" + urlIndex + "' >Volver al inicio</a>"
					+ "</body></html>";						
		}
		
	}

}

