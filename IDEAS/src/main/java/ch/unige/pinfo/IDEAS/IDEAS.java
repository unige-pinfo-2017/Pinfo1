package ch.unige.pinfo.IDEAS;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/home")
public class IDEAS {

	
	public IDEAS() {}
	
	@GET
	//@Produces(MediaType.TEXT_HTML)
	@Path("/")
	public String test(){
		return "Welcome to IDEAS! The application is under construction.";
	}
}
