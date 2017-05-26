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

@Path("/user")
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
	public User getUserByID(@PathParam("id") Long id){
		User user = new User();
		user.setId(id);
		user.setUsername("test" + id);
		user.setPassword("testpw");
		return user;
	}
	
	@PUT
	@Path("/add")
	@Produces({ "application/json" })
	public Response add(@NotNull User user) throws URISyntaxException {
		userService.addUser(user);
		return Response.status(201).contentLocation(new URI("user/byId/" + user.getId())).build();
	}
	
	@GET
	@Path("/byId/{id}")
	@Produces({ "application/json" })
	public Response getUserById(@NotNull @Digits(integer = 7, fraction = 0) @PathParam("id") Long id) {
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
	public Response getAll() {
		return Response.ok().entity(userService.getAllUsers()).build();
	}
	
	@GET
	//@Path("/byUsername/{username}")
	//@Produces({ "application/json" })
	public Response getByUsername(@PathParam("username") String username) {
		return Response.ok().entity(userService.getUserByUsername(username)).build();
	}
	
	@GET
	@Path("/byUsername/{username}")
	@Produces({ "application/json" })
	public User getByUsername2(@PathParam("username") String username) {
		return userService.getUserByUsername(username).get(0);
	}
	
	/*@GET
	@Path("{id}")
	public Response pathParamTest(@PathParam("id") String id){
		return Response.status(200).entity("Path parameter is: " + id).build();
	}*/
	
}
