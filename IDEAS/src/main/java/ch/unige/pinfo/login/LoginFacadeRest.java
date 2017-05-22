package ch.unige.pinfo.login;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
	
	@GET
	@Produces({"application/json"})
	@Path("/get-subordinates/{userId}")
	public JsonArray getSubordinates(@PathParam("userId") Long userId) {
		return loginService.getSubordinates(userId);
	}
	
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/get-role/{userId}")
	public String getRole(@PathParam("userId") Long userId) {
		return loginService.getRole(userId);
	}
}

