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
 * Clase que implementa el servicio web 'tweets' con el que gestionar los tweets en
 * el sistema APITwitterWeb
 * 
 * Se publica en el path 'tweets' bajo el que opera Jersey (definido en web.xml como
 * mapping del servlet que hace de provider jersey)
 * @author José Barba Martínez (jbarba63)
 *
 */
@Path("/tweets")
public class TweetsService {

	public TweetsService(){}
	
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
	 * Obtenemos un tweet por su id, que esperamos en el path /getById/<id>
	 * @Path("/getById/{id: [0-9]+}")
	 * @param id
	 * @return
	 */
	@GET
	@Path("/getById/{id: [0-9]+}")
	@Produces({MediaType.APPLICATION_XML})
	public Response getTweet(@PathParam("id") long id){
		Response ret=null;
		Tweet t=null;
		// abrimos conexion
		ConnDAOImpl conn = null;
		try{
		conn=new ConnDAOImpl();
		conn.abrirConexion();
		TweetsDAO tdao=new TweetsDAOImpl(conn.getConnection());
		t=tdao.selectTweet(id);
		ret=Response.ok(new JAXBElement(new QName("","tweet"),Tweet.class,t), MediaType.APPLICATION_XML).build();		
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
	 * Recupera los tweets que contienen el hashtag 'ht', permitiendo recuperar solo un 
	 * grupo concreto 'count' de ellos. Los dos argumentos deben formar parte de la URL
	 * de llamada, que deberia ser asi:
	 *    /getByHashtag/<ht>/<count>
	 * @param ht Hashtag
	 * @param count Numero de tweets a recuperar
	 * @return Tweets que contienen el hashtag 'ht'
	 */
	@GET
	@Path("/getByHashtag/{ht: [a-zA-Z]+}/{count: [0-9]{1,2}}")
	@Produces({MediaType.APPLICATION_XML})
	public Response getTweet(@PathParam("ht") String ht,
							 @PathParam("count") int count){
		Response ret=null;
		Tweets t=null;
		// abrimos conexion
		ConnDAOImpl conn = null;
		try{
		conn=new ConnDAOImpl();
		conn.abrirConexion();
		TweetsDAO tdao=new TweetsDAOImpl(conn.getConnection());
		List<Tweet> list=tdao.leerTweetsconHashtagDeterminado(ht,1,count);
		t=new Tweets(list);					
		ret=Response.ok(new JAXBElement(new QName("","tweets"),Tweets.class,t), MediaType.APPLICATION_XML).build();		
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
