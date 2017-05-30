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
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value="Chart")
@Path("/chart")
public class ChartFacadeRest {
	@Inject
	private ChartService chartService;
	
	@GET
	@Produces({"application/json"})
	@Path("/{resource}/{time}")
	@ApiOperation(value = "Get chart data for a resource and a specific time")
	public JsonArray getChartData(
			@ApiParam(value = "resource") @PathParam("resource") String resource, 
			@ApiParam(value = "time") @PathParam("time") String time) {
		return chartService.getChartData(resource, time);
	}
	
	@GET
	@Path("/{resource}/mock-last-day")
	@Produces({"application/json"})
	@ApiOperation(value="Get chart data of the last day")
	public JsonArray getChartDataDay(
			@ApiParam(value = "resource")@PathParam("resource") String resource) {
		return chartService.getChartDataMockLastDay(resource);
	}
	
	@GET
	@Path("/{resource}/mock-last-week")
	@Produces({ "application/json" })
	@ApiOperation(value="Get chart data of the last week")
	public JsonArray getChartDataWeek(
			@ApiParam(value="resource")@PathParam("resource") String resource) {
		return chartService.getChartDataMockLastWeek(resource);
	}
	
	@GET
	@Path("/{resource}/mock-last-month")
	@Produces({ "application/json" })
	@ApiOperation(value="Get chart data of the last month")
	public JsonArray getChartDataMonth(
			@ApiParam(value="resource")@PathParam("resource") String resource) {
		return chartService.getChartDataMockLastMonth(resource);
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
