package dao;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.User;

public interface UserDaoI {

	@POST
	@Path( "/gettoken" )
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces( "text/plain" )
	String gettoken(User usuario);
}
