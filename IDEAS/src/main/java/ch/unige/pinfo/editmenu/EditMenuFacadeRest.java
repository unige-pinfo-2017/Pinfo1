package ch.unige.pinfo.editmenu;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;



@Path("/edit")
public class EditMenuFacadeRest {
	@Inject
	private EditMenuService editMenuService;
	
	@GET
	@Path("/{deviceId}/menu")
	@Produces({"application/json"})
	public JsonArray getEditMenu(@PathParam("deviceId") String deviceId){
		return editMenuService.getEditMenu(deviceId);
	}
	
	@POST
	@Path("/{deviceId}/change-{resource}")
	@Consumes({ MediaType.TEXT_PLAIN })
	public String changeDevice(@PathParam("deviceId") String deviceId, @PathParam("resource") String resource, @QueryParam("value") String state) {
		return editMenuService.changeDevice(deviceId, resource, state);
	}
}
