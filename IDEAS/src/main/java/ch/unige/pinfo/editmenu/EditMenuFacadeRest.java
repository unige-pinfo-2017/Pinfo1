package ch.unige.pinfo.editmenu;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;


@Api(value="Edit")
@Path("/edit")
public class EditMenuFacadeRest {
	@Inject
	private EditMenuService editMenuService;
	
	@GET
	@Path("/{deviceId}/menu")
	@Produces({"application/json"})
	@ApiOperation(value = "Get the edit menu")
	public JsonArray getEditMenu(
			@ApiParam(value = "the device's id")@PathParam("deviceId") String deviceId){
		return editMenuService.getEditMenu(deviceId);
	}
	
	/*
	@POST
	@Path("/{deviceId}/change-{resource}")
	@Consumes({ MediaType.TEXT_PLAIN })
	public String changeDevice(@PathParam("deviceId") String deviceId, @PathParam("resource") String resource, @QueryParam("value") String state) {
		return editMenuService.changeDevice(deviceId, resource, state);
	}*/
	
	@POST
	@Path("/{deviceId}/change-{resource}")
	@Consumes({ MediaType.TEXT_PLAIN })
	@ApiOperation(value = "change the value of the device'resource")
	@ApiResponses(value = {
			@ApiResponse(code=200, message="Any"),
			@ApiResponse(code=500, message="Resource does not exist for this device.")
	})
	public Response changeDevice(
			@ApiParam(value = "the device's id")@PathParam("deviceId")String deviceId, 
			@ApiParam(value = "resource to change")@PathParam("resource") String resource, String newState) {
		return editMenuService.changeDevice(deviceId, resource, newState);
	}
}
