package uned.java2016.apitwitter.services.rs;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.*;
import javax.xml.namespace.*;
import java.io.*;
import java.util.*;
import uned.java2016.apitwitter.dao.*;

import uned.java2016.apitwitter.services.rs.jaxb.*;

/**
 * Clase que implementa el servicio web 'estudios' con el que gestionar los estudios
 * asociados a tweets en el sistema APITwitterWeb.
 * 
 * Se publica en el path '/estudios' bajo el que opera Jersey (definido en web.xml como
 * mapping del servlet que hace de provider jersey)
 * @author Uned 2016
 *
 */
@Path("/estudios")
public class EstudiosService {

	public EstudiosService(){}
	
	/**
	 * Simple eco, devuelto como texto plano
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
	 * Recupera los estudios que contienen el hashtag 'ht', permitiendo recuperar solo un 
	 * grupo concreto 'count' de ellos. Los dos argumentos deben formar parte de la URL
	 * de llamada, que deberia ser asi:
	 *    /getByHashtag/<ht>/<count>
	 * @param ht Hashtag
	 * @param count Numero de estudios a recuperar
	 * @return Estudios que contienen el hashtag 'ht'
	 */
	@GET
	@Path("/getByHashtag/{ht: [a-zA-Z]+}/{count: [0-9]{1,2}}")
	@Produces({MediaType.APPLICATION_XML})
	public Response getEstudio(@PathParam("ht") String ht,
							 @PathParam("count") int count){
		Response ret=null;
		Estudios t=null;
		// abrimos conexion
		ConnDAOImpl conn = null;
		try{
		conn=new ConnDAOImpl();
		conn.abrirConexion();
		ClinicalStudyDAO tdao=new ClinicalStudyDAOImpl(conn.getConnection());
		List<ClinicalStudy> list=tdao.selectClinicalStudies(ht,1,count);
		t=new Estudios(list);					
		ret=Response.ok(new JAXBElement(new QName("","estudios"),Estudios.class,t), MediaType.APPLICATION_XML).build();
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
	
	/**
	 * Recupera los estudios a través de su nct_id
 	 * La URL de llamada deberia ser asi:
	 *    /getByNCT/<nct_id>
	 * @param ht Hashtag
	 * @param count Numero de tweets a recuperar
	 * @return Tweets que contienen el hashtag 'ht'
	 */
	@GET
	@Path("/getByNCT/{nct: [A-Za-z0-9]+}")
	@Produces({MediaType.APPLICATION_XML})
	public Response getByNCT(@PathParam("nct") String nct){
		Response ret=null;
		ClinicalStudy study=null;
		// abrimos conexion
		ConnDAOImpl conn = null;
		try{
		conn=new ConnDAOImpl();
		conn.abrirConexion();
		ClinicalStudyDAO cdao=new ClinicalStudyDAOImpl(conn.getConnection());
		study = cdao.selectClinicalStudy(nct);					
		ret=Response.ok(new JAXBElement(new QName("","estudio"),ClinicalStudy.class,study), MediaType.APPLICATION_XML).build();		
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
