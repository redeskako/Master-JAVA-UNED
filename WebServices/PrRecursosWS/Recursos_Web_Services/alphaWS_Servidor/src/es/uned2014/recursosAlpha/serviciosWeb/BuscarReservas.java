package es.uned2014.recursosAlpha.serviciosWeb;

import java.io.File;
import java.net.URI;
import java.text.*;
import java.util.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.*;

import es.uned2014.recursosAlpha.conjuntos.*;
import es.uned2014.recursosAlpha.recurso.Recurso;
import es.uned2014.recursosAlpha.reserva.*;
import es.uned2014.recursosAlpha.usuario.Usuario;

/**
 * Servicio Web para busqueda de reservas.
 * 
 * @author Alpha UNED 2014
 * @version Recursos Servicio Web 1.0
 * @since Septiembre 2014
 */
//Se indica el Path para acceder al SW a trav�s de la URI:
@Path("/buscarReservas")
public class BuscarReservas {

	/**
	 * Obtiene un array con el resultado de la b�squeda de reservas, seg�n par�metros recibidos.
	 * Obtiene los arrays de los diferentes combos, y los envuelve en un objeto
	 * de la clase Conjunto.
	 * Despu�s lo convierte en documento XML para ser enviado como respuesta del servicio web.
	 * @param pagina
	 * @param idUsuario
	 * @param idRecurso
	 * @param idSucursal
	 * @return archivo XML (Conjunto)
	 * @throws JAXBException
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Conjunto conjuntoBuscarReservas( @QueryParam("pagina") int pagina,
			@QueryParam("usuario") int idUsuario, @QueryParam("recurso") int idRecurso,
			@QueryParam("sucursal") int idSucursal) {

		Reserva r = new Reserva();
		Usuario u = new Usuario();
		Recurso rec = new Recurso();
		
		int vista = 1;
		Date inicioD = new Date();
		inicioD.setTime(0);
		Date inicioH = new Date();
		inicioH.setTime(0);
		Date finalD = new Date();
		finalD.setTime(0);
		Date finalH= new Date();
		finalH.setTime(0);
		int estado = 0;
		
		// Se buscan las reservas que cumplen los requisitos:
		String sql = r.sqlBuscadorReservas(vista, idUsuario, idRecurso, inicioD, inicioH,
				finalD, finalH, estado, idSucursal);
		ArrayList<Reserva> arrayReservas = r.reservasBuscar(pagina, sql);

		// Se obtiene el n�mero de finas del listado (para paginaci�n):
		int numFilasB = r.numeroFilasBuscador(sql);
		
		// Para recargar el formulario, hay que volver a cargar los datos
		// para los combos:
		// Se obtiene los distintos usuarios para el formulario
		ArrayList<Usuario> arrayUsuarios = u.usuariosListado(0);

		// Se obtienen los recuros disponibles
		ArrayList<Recurso> arrayRecursos = rec.recursoListado(0);

		// Se obtienen las sucursales posibles para la reserva
		ArrayList<Reserva> arraySucursales = r.reservasSucursales();
		
		Conjunto conjunto = new Conjunto();
		
		// Se incluyen las variables en el objeto Conjunto:
		conjunto.setArrayReservas(arrayReservas);
		conjunto.setArrayUsuarios(arrayUsuarios);
		conjunto.setArrayRecursos(arrayRecursos);
		conjunto.setArraySucursales(arraySucursales);
		conjunto.setNumeroFilas(numFilasB);
		
		// Se convierte el Conjunto --> XML
		try {
			JAXBContext context = JAXBContext.newInstance(Conjunto.class);
			Marshaller m = context.createMarshaller();
			File file = new File("conjunto.xml"); // Archivo XML en el que se va a guardar
			m.marshal(conjunto, file);	
		} catch (JAXBException e1) {
			// TODO bloque catch autogenerado
			e1.printStackTrace();
		}
		
		return conjunto;
	} // end conjuntoBuscarReservas

} // end BuscarReservas
