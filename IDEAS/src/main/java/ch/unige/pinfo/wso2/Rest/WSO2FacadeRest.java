package ch.unige.pinfo.wso2.Rest;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import ch.unige.pinfo.wso2.Service.WSO2Service;


@Path("/wso2")
public class WSO2FacadeRest {

	@Inject 
	private WSO2Service wso2Service;

	
	@GET
	@Path("/")
	public String test(){
		return "test";
	}
	@GET
	@Path("/init")
	public String init(){
		wso2Service.initDB();
		return "init";
	}
	
	@GET
	@Path("/sum/sensor/{sensorName}")
	public Response getSumBySensor(@NotNull @PathParam("sensorName") String sensorName){
		String sum = "";
		try {
			sum = wso2Service.getSumBySensor(sensorName);
		} catch (NoResultException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok().entity(sum).build();
	}
}
