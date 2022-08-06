package es.uned2014.recursosAlpha.serviciosWeb;

import java.io.File;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import es.uned2014.recursosAlpha.conjuntos.Conjunto;
import es.uned2014.recursosAlpha.recurso.Recurso;
import es.uned2014.recursosAlpha.reserva.Reserva;
import es.uned2014.recursosAlpha.usuario.Usuario;

/**
 * Servicio Web para la obtención de los combos de Usuarios, Recursos, Sucursales y Estados.
 * @author	Alpha UNED 2014
 * @version	Recursos Servicio Web 1.0
 * @since 	Septiembre 2014
 */

//Se indica el Path para acceder al SW a través de la URI:
@Path("/combosFormulario")
public class CombosFormulario {
	
	
	/**
	 * Obtiene los arrays de los diferentes combos, y los envuelve en un objeto
	 * de la clase Conjunto.
	 * Después lo convierte en documento XML para ser enviado como respuesta del servicio web.
	 * @return archivo XML (Conjunto)
	 * @throws JAXBException
	 */
	// Método a ejecutar si se pide un XML a través de un GET:
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Conjunto combosXml(){
		
		Conjunto conjunto = new Conjunto();
		
		// Se obtiene el array de Usuarios y se incluye en el objeto Conjunto:
		Usuario u = new Usuario();
		ArrayList<Usuario> arrayU = u.usuariosListado(0);
		conjunto.setArrayUsuarios(arrayU);
		
		// Se obtiene el array de Recursos y se incluye en el objeto Conjunto:
		Recurso r = new Recurso();
		ArrayList<Recurso> arrayR = r.recursoListado(0);
		conjunto.setArrayRecursos(arrayR);
		
		// Se obtiene el array de Reservas con todos los posibles estados y se incluye en el objeto Conjunto:
		Reserva e = new Reserva();
		ArrayList<Reserva> arrayE = e.reservasEstados();
		conjunto.setArrayEstados(arrayE);
		
		// Se obtiene el array de Reservas con todas las posibles sucursales y se incluye en el objeto Conjunto:
		Reserva s = new Reserva();
		ArrayList<Reserva> arrayS = s.reservasSucursales();
		conjunto.setArraySucursales(arrayS);
		
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
		
	}

}
