package ch.unige.pinfo.overview;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ch.unige.pinfo.user.service.UserService;

@Path("/overview")
public class OverviewFacadeRest {
	@Inject
	private UserService userService;
	
	@GET
	@Path("/live")
	@Produces({ "application/json" })
	public JsonArray getLiveData(@QueryParam("userid") Long userId){
		// Dans l'api actuel, seuls ces types de données sont intéressants 
		// pour l'overview.
		
		// TODO: Méthode qui crée un JsonObject à partir d'une classe
		// TODO: Créer une classe pour données ?
		
		// userService.getSumBySensor(userId, sensorName, "0", "0");
		
		// Mock pour l'instant
		JsonArray data = Json.createArrayBuilder()
							.add(Json.createObjectBuilder()
									.add("name", "Power")
									.add("unit", "W")
									.add("value", "120")
									)
							.add(Json.createObjectBuilder()
									.add("name", "Temperature")
									.add("unit", "°C")
									.add("value", "25")
									)
							.add(Json.createObjectBuilder()
									.add("name", "Current")
									.add("unit", "A")
									.add("value", "25")
									)
							.build();
		
		return data;
	}
	
}
