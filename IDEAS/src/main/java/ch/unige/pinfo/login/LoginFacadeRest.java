package ch.unige.pinfo.login;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.*;

@Path("/login")
public class LoginFacadeRest {
	
	@Inject
	private LoginService loginService;
	
	public LoginFacadeRest(){}

	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/")
	public String authentication(@QueryParam("username") String username, @QueryParam("pw") String inputPw){
		return loginService.authenticate(username, inputPw);
	}
}

