package es.uned2014.recursosAlpha.serviciosWeb;

import java.io.File;
import java.util.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.*;

import es.uned2014.recursosAlpha.conjuntos.Conjunto;
import es.uned2014.recursosAlpha.reserva.Reserva;

/**
 * Servicio Web para mostrar las de reservas de un usuario
 * 
 * @author Alpha UNED 2014
 * @version Recursos Servicio Web 1.0
 * @since Septiembre 2014
 */
@Path("/misReservas")
public class MisReservas {
	
	/**
	 * Obtiene un array con el listado de reservas de un usuario
	 * Después lo convierte en documento XML para ser enviado como respuesta del servicio web.
	 * @param pagina
	 * @param usuario
	 * @return archivo XML (Conjunto)
	 * @throws JAXBException
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Conjunto conjMisReservas(@QueryParam("pagina") int pagina,
			@QueryParam("usuario") String usuario) {
		
		Reserva r = new Reserva();
		
		// Se obtiene el array de reservas para el usuario conectado:
		ArrayList<Reserva> arrayR = r.misReservas(usuario, pagina);
		
		// El número total de filas es la suma de los estados 2, 4 y 6 (para paginación)
		int numFilas = r.totalFilasReserva(2, usuario) + r.totalFilasReserva(4, usuario) 
				+ r.totalFilasReserva(6, usuario);
		
		Conjunto conjunto = new Conjunto();
		
		// Se incluyen las variables en el objeto Conjunto:
		conjunto.setArrayReservas(arrayR);
		conjunto.setNumeroFilas(numFilas);
		
		// Se convierte el Conjunto --> XML
		try {
			JAXBContext context = JAXBContext.newInstance(Conjunto.class);
			Marshaller m = context.createMarshaller();
			File file = new File("conjunto.xml"); // Archivo XML en el que se va a guardar
			m.marshal(conjunto, file);	
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return conjunto;
	} // end conjMisReservas

} // end class MisReservas
