package ch.unige.pinfo.overview.service;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.transaction.Transactional;
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
@Path("/overview")
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
	@Transactional
	@ApiOperation(value="Get live data",
	response=LiveData.class,
	responseContainer="List")
	@ApiResponses(value= {
			@ApiResponse(code=404, message = "Live data not found")
	})
	public JsonArray getLiveData(@ApiParam("User Id") @PathParam("userId") Long userId) {
		return overviewService.buildLiveData(userId);
	}
	
	@GET
	@Path("/hidden-data/{userId}")
	@Produces({ "application/json" })
	@Transactional
	@ApiOperation(value="Get hidden data",
	response=LiveData.class,
	responseContainer="List")
	@ApiResponses(value= {
			@ApiResponse(code=404, message = "Hidden data not found")
	})
	public JsonArray getHiddenData(@ApiParam("User Id") @PathParam("userId") Long userId) {
		return overviewService.buildHiddenData(userId);
	}
	
	@POST
	@Path("/preferences/{userId}/add")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Transactional
	public Response addPreference(@PathParam("userId") Long userId, String measureName) {
		return overviewService.addPreference(userId, measureName);
	}
	
	@POST
	@Path("/preferences/{userId}/remove")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Transactional
	public Response removePreference(@PathParam("userId") Long userId, String measureName) {
		return overviewService.removePreference(userId, measureName);
	}
	
}

