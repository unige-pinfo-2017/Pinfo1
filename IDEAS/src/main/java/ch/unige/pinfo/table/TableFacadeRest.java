package ch.unige.pinfo.table;


import javax.inject.Inject;
import javax.json.JsonArray;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Path("/table")
public class TableFacadeRest {
	@Inject
	private TableService tableService;
	
	@GET
	@Path("/{userId}/{type}")
	@Produces({"application/json"})
	@Transactional
	public JsonArray getColumsAndData(@PathParam("userId") Long userId, @PathParam("type") String deviceType) {
		return tableService.buildColumns(deviceType, userId);
	}
}
