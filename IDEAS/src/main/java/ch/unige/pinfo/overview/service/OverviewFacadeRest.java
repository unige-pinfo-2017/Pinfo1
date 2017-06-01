package ch.unige.pinfo.overview.service;

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

import ch.unige.pinfo.overview.dom.LiveData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="Overview")
@Path("/rest/overview")
public class OverviewFacadeRest {
	@Inject
	private OverviewService overviewService;
	
	/*
	@GET
	@Path("/live-data2/{userId}")
	@Produces({ "application/json" })
	@Transactional
	public JsonArray getLiveData2(@PathParam("userId") Long userId) {
		return overviewService.buildLiveData2(userId);
	}*/
	
	@GET
	@Path("/live-data/{userId}")
	@Produces({ "application/json" })
	@ApiOperation(value="Get live data",
		response=LiveData.class,
		responseContainer="List")
	public JsonArray getLiveData(
			@ApiParam("User Id") @PathParam("userId") Long userId) {
		return overviewService.buildLiveData(userId);
	}
	
	@GET
	@Path("/hidden-data/{userId}")
	@Produces({ "application/json" })
	@ApiOperation(value="Get hidden data",
		response=LiveData.class,
		responseContainer="List")
	public JsonArray getHiddenData(
			@ApiParam("User Id") @PathParam("userId") Long userId) {
		return overviewService.buildHiddenData(userId);
	}
	
	@POST
	@Path("/preferences/{userId}/add")
	@Consumes({ MediaType.TEXT_PLAIN })
	@ApiOperation(value = "Add prefereces for a user")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message= "Addin {measureName} to preferences failed."),
			@ApiResponse(code = 200, message = "{measureName} added to preferences.")
	})
	public Response addPreference(
			@ApiParam(value="User's id")@PathParam("userId") Long userId, 
			@ApiParam(value="measure name") String measureName) {
		return overviewService.addPreference(userId, measureName);
	}
	
	@POST
	@Path("/preferences/{userId}/remove")
	@ApiOperation(value="Remove prefereces for a user")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Removing {measureName} from preferences failed."),
			@ApiResponse(code = 200, message = "{measureName} removed from preferences.")
	})
	@Consumes({ MediaType.TEXT_PLAIN })
	public Response removePreference(
			@ApiParam(value="User's id")@PathParam("userId") Long userId, 
			@ApiParam(value="measure name") String measureName) {
		return overviewService.removePreference(userId, measureName);
	}
	
}

