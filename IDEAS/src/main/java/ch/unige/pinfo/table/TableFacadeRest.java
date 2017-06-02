package ch.unige.pinfo.table;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ch.unige.pinfo.device.dom.TypeDevice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(value="Table")
@Path("/table")
public class TableFacadeRest {
	@Inject
	private TableService tableService;
	
	@GET
	@Path("/{userId}/device/{type}")
	@Produces({"application/json"})
	@ApiOperation(value="Get table for device type",
		response=TypeDevice.class,
		responseContainer="List")
	public JsonArray getTableForDeviceType(
			@ApiParam("User's id") @PathParam("userId") Long userId,
			@ApiParam("Device Type") @PathParam("type") String deviceType) {
		return tableService.buildTableForDeviceType(deviceType, userId);
	}
	
	@GET
	@Path("/{userId}/sensor/{type}")
	@Produces({"application/json"})
	@ApiOperation(value="Get table for sensor type")
	public JsonArray getTableForSensorType(
			@ApiParam("User ID") @PathParam("userId") Long userId, 
			@ApiParam("Sensor Type") @PathParam("type") String deviceType) {
		return tableService.buildTableForSensorType(deviceType, userId);
	}
	
	@GET
	@Path("/{userId}/user/all")
	@Produces({"application/json"})
	@ApiOperation(value="Get table of users",
		notes="Available for Managers and SysAdmin")
	public JsonArray getTableOfUsers(@ApiParam () @PathParam("userId") Long userId) {
		return tableService.buildTableForUser(userId);
	}
	
}
