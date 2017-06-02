package ch.unige.pinfo.user.service;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ch.unige.pinfo.user.dom.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value="User")
@Path("/rest/user")
public class UserFacadeRest {
	
	@Inject 
	private UserService userService;
	@GET
	@Path("/")
	public String test(){
		return "Testing REST for user class.";
	}
	
	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	@ApiOperation(value = "Create test user class",
			response= User.class,
			responseContainer = "List")
	public User getUserByID(
			@ApiParam(value="The user'id")@PathParam("id") Long id){
		User user = new User();
		user.setId(id);
		user.setUsername("test" + id);
		user.setPassword("testpw");
		return user;
	}
	
	@PUT
	@Path("/add")
	@Produces({ "application/json" })
	@ApiOperation(value = "Add new user to the data base")
	@ApiResponses(value={
			@ApiResponse(code=201, message="User details"),
			@ApiResponse(code=404, message="not found")
	})
	public Response add(@NotNull User user) throws URISyntaxException {
		userService.addUser(user);
		return Response.status(201).contentLocation(new URI("user/byId/" + user.getId())).build();
	}
	
	@GET
	@Path("/byId/{id}")
	@Produces({ "application/json" })
	@ApiOperation(value = "Get a user by his id")
	@ApiResponses(value = {
			@ApiResponse(code=404, message = "not found"),
			@ApiResponse(code=200, message= "User details")
	})
	public Response getUserById(@NotNull @Digits(integer = 7, fraction = 0) 
		@ApiParam(value="he user's id")@PathParam("id") Long id) {
		User u = null;
		try {
			u = userService.getUserById(id);
		} catch (NoResultException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok().entity(u).build();
	}
	
	@GET
	@Path("/all")
	@Produces({ "application/json" })
	@ApiOperation(value="Get all existing users")
	@ApiResponses(value={
			@ApiResponse(code=200, message= "Users details"),
	})
	public Response getAll() {
		return Response.ok().entity(userService.getAllUsers()).build();
	}
	
	@GET
	public Response getByUsername(@PathParam("username") String username) {
		return Response.ok().entity(userService.getUserByUsername(username)).build();
	}
	
	@GET
	@Path("/byUsername/{username}")
	@Produces({ "application/json" })
	@ApiOperation(value="Get a user by user name")
	@ApiResponses(value = {
			@ApiResponse(code=200, message="User details")
	})
	public User getByUsername2(
			@ApiParam(value ="The user name")@PathParam("username") String username) {
		return userService.getUserByUsername(username).get(0);
	}
	
}
