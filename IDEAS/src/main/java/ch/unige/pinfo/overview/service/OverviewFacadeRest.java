package ch.unige.pinfo.overview.service;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/overview")
public class OverviewFacadeRest {
	@Inject
	private OverviewService overviewService;
	
	@GET
	@Path("/live")
	@Produces({ "application/json" })
	@Transactional
	public JsonArray getLiveData(@QueryParam("userid") Long userId){
		return overviewService.buildLiveData(userId);
	}
}

