package ch.unige.pinfo.login;

import javax.inject.Inject;

import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ch.unige.pinfo.user.dom.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="Login")
@Path("/rest/login")
public class LoginFacadeRest {
	
	@Inject
	private LoginService loginService;
	
	public LoginFacadeRest(){}

	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/")
	@ApiOperation(value="Authentificate an user")
	public String authentication(
			@ApiParam(value= "The username for login", required=true) @QueryParam("username") String username, 
			@ApiParam(value="The password for login", required=true) @QueryParam("pw") String inputPw){
		return loginService.authenticate(username, inputPw);
	}
	
	@GET		
	@Produces({"application/json"})
	@Path("/get-subordinates/{userId}")
	@ApiOperation(value="List of subordinates ID",
	response = User.class,
	responseContainer = "List")
	public JsonArray getSubordinates(
			@ApiParam(value="Manager/SysAdmin ID")@PathParam("userId") Long userId) {
		return loginService.getSubordinates(userId);
	}
	
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/get-role/{userId}")
	@ApiOperation(value="Role of a user")
	public String getRole(
			@ApiParam(value="the user's id") @PathParam("userId") Long userId) {
		return loginService.getRole(userId);
	}
}

