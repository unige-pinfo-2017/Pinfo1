package ch.unige.pinfo.editmenu;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;



@Path("/edit")
public class EditMenuFacadeRest {
	@Inject
	private EditMenuService editMenuService;
	
	@GET
	@Path("/{deviceId}")
	@Produces({"application/json"})
	@Transactional
	public JsonArray getEditMenu(@PathParam("deviceId") String deviceId){
		return editMenuService.getEditMenu(deviceId);
	}
	
}
