package ch.unige.pinfo.table;


import javax.inject.Inject;
import javax.json.JsonArray;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Path("/table")
public class TableFacadeRest {
	@Inject
	private TableService tableService;
	
	@GET
	@Path("/columns")
	@Produces({"application/json"})
	@Transactional
	public JsonArray getColumsAndData(@QueryParam("type") String deviceType, @QueryParam("userid") Long userId) {
		return tableService.buildColumns(deviceType, userId);
	}
}
