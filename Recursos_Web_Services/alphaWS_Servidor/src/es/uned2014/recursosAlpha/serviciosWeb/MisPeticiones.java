package es.uned2014.recursosAlpha.serviciosWeb;

import java.io.File;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import es.uned2014.recursosAlpha.conjuntos.Conjunto;
import es.uned2014.recursosAlpha.reserva.Reserva;

/**
 * Servicio Web para mostrar las de reservas de un usuario
 * 
 * @author Alpha UNED 2014
 * @version Recursos Servicio Web 1.0
 * @since Septiembre 2014
 */
@Path("/misPeticiones")
public class MisPeticiones {
	
	/**
	 * Obtiene un array con el listado de peticiones de reservas de un usuario
	 * Despu�s lo convierte en documento XML para ser enviado como respuesta del servicio web. 
	 * @param pagina
	 * @param usuario
	 * @return archivo XML (Conjunto)
	 * @throws JAXBException
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Conjunto cMisPeticiones(@QueryParam("pagina") int pagina,
			@QueryParam("usuario") String usuario) {
		
		Reserva r = new Reserva();
		
		// Se obtiene el array de reservas para el usuario conectado:
		ArrayList<Reserva> arrayP = r.misPeticiones(usuario, pagina);
		
		// El n�mero total de filas es la suma de el estado 1 (para paginaci�n)
		int numFilas = r.totalFilasReserva(1, usuario);
		
		Conjunto conjunto = new Conjunto();
		
		// Se incluyen las variables en el objeto Conjunto:
		conjunto.setArrayReservas(arrayP);
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
	} // end cMisPeticiones

} // end class MisPeticiones
