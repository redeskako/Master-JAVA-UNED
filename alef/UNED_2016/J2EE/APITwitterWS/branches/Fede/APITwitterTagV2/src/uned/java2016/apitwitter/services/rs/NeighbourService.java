package uned.java2016.apitwitter.services.rs;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.*;
import javax.xml.namespace.*;
import java.util.*;
import uned.java2016.apitwitter.dao.*;

import uned.java2016.apitwitter.services.rs.jaxb.*;

@Path("/neighbour")
public class NeighbourService {
	
public NeighbourService(){}
	
	/**
	 * Llamada para probar correcto funcionamiento del WS, devuelto como texto plano
	 * @param str Cadena a la que hacer eco
	 * @return la cadena str de entrada
	 */
	@GET
	@Path("/echo")
	@Produces({MediaType.TEXT_PLAIN})
	public Response echo(@QueryParam("str") String str){
		System.out.println("Calling ECHO");
		return Response.ok(str,MediaType.TEXT_PLAIN).build();
	}
	
	/**
	 * Recupera los vecinos del hashtag 'ht'.Ek argumento deben formar parte de la URL
	 * de llamada, la cual debera tener el siguiente formato:
	 *    /getNeighbour/<ht>
	 * @param ht Hashtag
	 * @return Vecinos del hashtag 'ht'
	 */
	@GET
	@Path("/getNeighbour/{ht: [a-zA-Z]+}")
	@Produces({MediaType.APPLICATION_XML})
	public Response getNeighbour(@PathParam("ht") String ht){
		Response ret=null;
		Neighbours n=null;
		// abrimos conexion
		ConnDAOImpl conn = null;
		try{
		conn=new ConnDAOImpl();
		conn.abrirConexion();
		NeighborsDAO ndao=new NeighborsDAOImpl(conn.getConnection());
		List<String> list=ndao.selectNeighbors(ht);
		n=new Neighbours(list);					
		ret=Response.ok(new JAXBElement(new QName("","neighbours"),Neighbours.class,n), MediaType.APPLICATION_XML).build();		
		}catch(Exception e){
			ret=Response.status(Response.Status.INTERNAL_SERVER_ERROR).
								entity("Error accediendo a datos "+e.toString()).
								type(MediaType.TEXT_HTML).
								build();
		}
		finally{
			try{conn.getConnection().close();}catch(Exception ce){}finally{conn=null;}
		}
		return ret;
	}
}
