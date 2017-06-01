package ch.unige.pinfo.table;



import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.device.dom.TypeDevice;
import ch.unige.pinfo.user.dom.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


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
	
	/*@GET
	@Path("/{userId}/{type}")
	@Produces({"application/json"})
	@Transactional
	public JsonArray getTable(@PathParam("userId") Long userId, @PathParam("type") String deviceType) {
		return tableService.buildTableForSensorType(deviceType, userId);
	}*/
	
	@GET
	@Path("/{userId}/user/all")
	@Produces({"application/json"})
	@ApiOperation(value="Get table of users",
		notes="Available for Managers and SysAdmin")
	public JsonArray getTableOfUsers(@ApiParam () @PathParam("userId") Long userId) {
		return tableService.buildTableForUser(userId);
	}
	
	/*@GET
	@Path("/color")
	@Produces({ MediaType.TEXT_PLAIN })
	@ApiOperation(value = "Get a color")
	public String getColorTest() {
		return tableService.getColorTest();
	}*/
	
	/*@POST
	@Path("/change-state/{deviceId}/{action}")
	@ApiOperation(value="Change state of a device",
		notes="Available for a SysAdmin")
	public void changeState(@ApiParam("Device ID") @PathParam("deviceId") Long deviceId, 
			@ApiParam("Action") @PathParam("action") String action, 
			@ApiParam("State") @QueryParam("state") String state){
	}
	*/
	
}
