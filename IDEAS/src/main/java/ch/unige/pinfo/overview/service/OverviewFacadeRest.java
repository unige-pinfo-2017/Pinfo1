package ch.unige.pinfo.overview.service;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/overview")
public class OverviewFacadeRest {
	@Inject
	private OverviewService overviewService;
	
	@GET
	@Path("/live-data/{userId}")
	@Produces({ "application/json" })
	@Transactional
	public JsonArray getLiveData(@PathParam("userId") Long userId){
		return overviewService.buildLiveData(userId);
	}
}

