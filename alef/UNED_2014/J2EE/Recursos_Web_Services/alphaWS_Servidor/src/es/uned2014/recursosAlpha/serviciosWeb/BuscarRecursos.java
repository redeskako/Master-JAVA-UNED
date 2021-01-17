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
import es.uned2014.recursosAlpha.recurso.Recurso;

/**
 * Servicio Web para busqueda de recursos.
 * @author	Alpha UNED 2014
 * @version	Recursos Servicio Web 1.0
 * @since 	Septiembre 2014
 */

//Se indica el Path para acceder al SW a trav�s de la URI:
@Path("/buscarRecursos")
public class BuscarRecursos {
	
	/**
	 * Obtiene los arrays de los diferentes combos, y los envuelve en un objeto
	 * de la clase Conjunto.
	 * Despu�s lo convierte en documento XML para ser enviado como respuesta del servicio web.
	 * @param pagina
	 * @param busqueda
	 * @return archivo XML (Conjunto)
	 * @throws JAXBException
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Conjunto conjuntoBuscarRecursos(@QueryParam("pagina") Integer pagina, @QueryParam("busqueda") String busqueda) {
		Recurso r = new Recurso();
		
		// Se buscan los recursos que cumplen los requisitos:
		String sql = r.sqlBuscadorRecursos(busqueda);
		ArrayList<Recurso> arrayR = r.recursosBuscar(pagina, sql);
		
		// Se obtiene el n�mero de finas del listado (para paginaci�n):
		int numFilasB = r.numeroFilasBuscador(sql);
					
		Conjunto conjunto = new Conjunto();
		
		// Se incluyen las variables en el objeto Conjunto:
		conjunto.setArrayRecursos(arrayR);
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
	} // end conjuntoBuscarRecursos
	
} // end clase BuscarRecursos
