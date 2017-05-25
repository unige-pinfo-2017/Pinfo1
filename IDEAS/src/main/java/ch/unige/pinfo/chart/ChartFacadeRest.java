package ch.unige.pinfo.chart;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/chart")
public class ChartFacadeRest {
	@Inject
	private ChartService chartService;
	
	@GET
	@Path("/{resource}/last-{time}")
	@Produces({"application/json"})
	public JsonArray getChartData(@PathParam("resource") String resource, @PathParam("time") String time) {
		return chartService.getChartData(resource, time);
	}
}
