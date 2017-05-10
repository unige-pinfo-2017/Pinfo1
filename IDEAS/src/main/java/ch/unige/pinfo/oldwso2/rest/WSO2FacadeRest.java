package ch.unige.pinfo.oldwso2.rest;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.unige.pinfo.oldwso2.service.WSO2Service;
import ch.unige.pinfo.user.service.UserService;


@Path("/wso2Old")
public class WSO2FacadeRest {

	@Inject 
	private WSO2Service wso2Service;
	@Inject 
	private UserService userService;
	
	@GET
	@Path("/")
	public String test(){
		return "test";
	}
	
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/init")
	public String init(){
		wso2Service.initDB();
		return "init";
	}
	
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/sum/sensor/{sensorName}")
	public Response getSumBySensor(@NotNull @PathParam("sensorName") String sensorName,
									@QueryParam("from") String from,
									@QueryParam("to") String to,
									@QueryParam("userId") Long userId) {
		String sum = "";
		try {
			sum = Double.toString(userService.getSumBySensor(userId, sensorName, from, to));
		} catch (NoResultException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok().entity(sum).build();
	}
}
