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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="Login")
@Path("/login")
public class LoginFacadeRest {
	
	@Inject
	private LoginService loginService;
	
	public LoginFacadeRest(){}

	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/")
	@ApiOperation(value="Authentificate an user")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Authtification successful"),
			@ApiResponse(code=400, message ="Authentification failed"),
	})
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
	@ApiResponses(value= {
			@ApiResponse(code=400, message ="Invalid ID supplied"),
			@ApiResponse(code=404, message = "Subordinates not found")
	})
	public JsonArray getSubordinates(@ApiParam(value="Manager/SysAdmin ID")@PathParam("userId") Long userId) {
		return loginService.getSubordinates(userId);
	}
	
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/get-role/{userId}")
	public String getRole(@PathParam("userId") Long userId) {
		return loginService.getRole(userId);
	}
}

