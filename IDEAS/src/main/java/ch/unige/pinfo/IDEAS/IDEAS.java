package ch.unige.pinfo.IDEAS;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.unige.pinfo.login.Login;

@Path("/")
public class IDEAS {
	public IDEAS() {}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/")
	public String test(){
		return "Welcome to IDEAS! The application is under construction.";
	}
}
