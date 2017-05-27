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
	@Path("/{resource}/{time}")
	@Produces({"application/json"})
	public JsonArray getChartData(@PathParam("resource") String resource, @PathParam("time") String time) {
		return chartService.getChartData(resource, time);
	}
	
	@GET
	@Path("/{resource}/mock-last-day")
	@Produces({"application/json"})
	public JsonArray getChartDataDay(@PathParam("resource") String resource) {
		return chartService.getChartDataMockLastDay(resource);
	}
	
	@GET
	@Path("/{resource}/mock-last-week")
	@Produces({ "application/json" })
	public JsonArray getChartDataWeek(@PathParam("resource") String resource) {
		return chartService.getChartDataMockLastWeek(resource);
	}
	
	@GET
	@Path("/{resource}/mock-last-month")
	@Produces({ "application/json" })
	public JsonArray getChartDataMonth(@PathParam("resource") String resource) {
		return chartService.getChartDataMockLastMonth(resource);
	}
	
	@GET
	@Path("/{resource}/year/{yearNum}")
	@Produces({ "application/json" })
	public JsonArray getChartDataYear(@PathParam("resource") String resource, @PathParam("yearNum") int year) {
		return chartService.getChartDataYear(resource, year);
	}
}
