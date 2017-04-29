package ch.unige.pinfo.user.dom;

import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserFacadeRest {
	private EntityManager em;
	
	public UserFacadeRest(){
		
	}
	
	@GET
	@Path("/")
	public String test(){
		return "Testing REST for user class.";
	}
	
	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	public User getUserById(@PathParam("id") int id){
		User user = new User();
		user.setId(id);
		user.setUsername("test" + id);
		user.setPassword("testpw");
		return user;
	}
	
	/*@GET
	@Path("{id}")
	public Response pathParamTest(@PathParam("id") String id){
		return Response.status(200).entity("Path parameter is: " + id).build();
	}*/
	
}
