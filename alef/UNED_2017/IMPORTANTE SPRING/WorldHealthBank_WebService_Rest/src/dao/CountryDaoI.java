package dao;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Country;
import model.User;


public interface CountryDaoI {
	@GET
	@Path( "/getcountry/{input}" )
	@Produces( "text/plain" )
	String getCountryByCode(@HeaderParam("token") String token, @PathParam( "input" ) String input);
	
	/*@GET
	@Path( "/addcountry/{a}/{b}" )
	@Produces( "text/plain" )
	String addCountry(@PathParam( "a" ) String a, @PathParam("b") String b);*/
	
	@POST
	@Path( "/addcountry" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String addCountry(@HeaderParam("token") String token, Country pais);
	
	
	@DELETE
	@Path( "/deletecountry/{code}" )
	@Produces( "text/plain" )
	String deleteCountry(@HeaderParam("token") String token, @PathParam( "code" ) String code);
	
	/*
	@GET
	@Path( "/updatecountry/{codigo}/{nombre}" )
	@Produces( "text/plain" )
	String updateCountry(@PathParam( "codigo" ) String a, @PathParam("nombre") String b);
	*/
	
	@POST
	@Path( "/updatecountry" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String updateCountry(@HeaderParam("token") String token, Country pais);
	

	@GET
	@Path( "/getcountrypagination/{a}/{b}" )
	@Produces(MediaType.APPLICATION_JSON)
	List<Country> getCountryPagination(@HeaderParam("token") String token, @PathParam( "a" ) int pag, @PathParam("b") int numeroFilas);
	
	
	//ESTO DEBERIA ESTAR EN SU CLASE CORRESPONFIENTE: UserDaoI
	@POST
	@Path( "/gettoken" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces( "text/plain" )
	String gettoken(User usuario);
}