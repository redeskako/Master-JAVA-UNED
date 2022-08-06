package com.restfulexample.rest.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello/{nombre}")
public class RestHelloWorld {

	// This method is called if TEXT_PLAIN is request
	@GET
	 @Produces(MediaType.TEXT_PLAIN)
	 public String sayHello(@PathParam("nombre") String nombre){
		return "Hello World " + nombre;
	 }
	
	// This method is called if XML is request
	@GET
	@Produces(MediaType.TEXT_XML)
	  public String sayXMLHello(@PathParam("nombre") String nombre) {
	    return "<?xml version=\"1.0\"?>" + "<hello> Hello World "+ nombre + "</hello>";
	  }

	  // This method is called if HTML is request
	  @GET
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello(@PathParam("nombre") String nombre) {
	    return "<html> " + "<title>" + "Hello World "+ nombre + "</title>"
	        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
	  }

}
