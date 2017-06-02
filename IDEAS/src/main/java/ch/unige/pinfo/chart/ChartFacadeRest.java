package ch.unige.pinfo.chart;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="Chart")
@Path("/rest/chart")
public class ChartFacadeRest {
	@Inject
	private ChartService chartService;
	
	@GET
	@Path("/{resource}/last-day")
	@Produces({"application/json"})
	@ApiOperation(value="Get chart data of the last day")
	public JsonArray getChartDataDay(
			@ApiParam(value = "resource")@PathParam("resource") String resource) {
		return chartService.getChartDataLastDay(resource);
	}
	
	@GET
	@Path("/{resource}/last-week")
	@Produces({ "application/json" })
	@ApiOperation(value="Get chart data of the last week")
	public JsonArray getChartDataWeek(
			@ApiParam(value="resource")@PathParam("resource") String resource) {
		return chartService.getChartDataLastWeek(resource);
	}
	
	@GET
	@Path("/{resource}/last-month")
	@Produces({ "application/json" })
	@ApiOperation(value="Get chart data of the last month")
	public JsonArray getChartDataMonth(
			@ApiParam(value="resource")@PathParam("resource") String resource) {
		return chartService.getChartDataLastMonth(resource);
	}
	
	@GET
	@Path("/{resource}/year/{yearNum}")
	@Produces({ "application/json" })
	@ApiOperation(value="Get chart data for a year")
	public JsonArray getChartDataYear(
			@ApiParam(value="resource") @PathParam("resource") String resource, 
			@ApiParam(value="a year") @PathParam("yearNum") int year) {
		return chartService.getChartDataYear(resource, year);
	}
}
