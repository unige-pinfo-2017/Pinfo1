package ch.unige.pinfo.table;


import java.util.List;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ch.unige.pinfo.wso2.service.WSO2Wrapper;


@Path("/table")
public class TableFacadeRest {
	@Inject
	private TableService tableService;
	
	@GET
	@Path("/{userId}/device/{type}")
	@Produces({"application/json"})
	@Transactional
	public JsonArray getTableForDeviceType(@PathParam("userId") Long userId, @PathParam("type") String deviceType) {
		return tableService.buildTableForDeviceType(deviceType, userId);
	}
	
	@GET
	@Path("/{userId}/sensor/{type}")
	@Produces({"application/json"})
	@Transactional
	public JsonArray getTableForSensorType(@PathParam("userId") Long userId, @PathParam("type") String deviceType) {
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
	@Transactional
	public JsonArray getTableOfUsers(@PathParam("userId") Long userId) {
		return tableService.buildTableForUser(userId);
	}
	
	@GET
	@Path("/color")
	@Produces({ MediaType.TEXT_PLAIN })
	@Transactional
	public String getColorTest() {
		return tableService.getColorTest();
	}
	
	@POST
	@Path("/change-state/{deviceId}/{action}")
	public void changeState(@PathParam("deviceId") Long deviceId, @PathParam("action") String action, @QueryParam("state") String state){
		
	}
	
	
}
