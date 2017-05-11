package ch.unige.pinfo.overview;

import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.overview.service.LiveDataService;
import ch.unige.pinfo.user.service.UserService;

@Path("/overview")
public class OverviewFacadeRest {
	@Inject
	private UserService userService;
	
	@Inject 
	private OverviewJsonBuilder overviewJsonBuilder;
	
	@Inject
	private LiveDataService liveDataService;
	
	@GET
	@Path("/live")
	@Produces({ "application/json" })
	@Transactional
	public JsonArray getLiveData(@QueryParam("userid") Long userId){
		// Dans l'api actuel, seuls ces types de données sont intéressants 
		// pour l'overview.
		
		// TODO: Méthode qui crée un JsonObject à partir d'une classe
		// TODO: Créer une classe pour données ?
		
		// userService.getSumBySensor(userId, sensorName, "0", "0");
		
		// Pseudo code:
		// Récupérer les objets LiveData
		// Créer le JsonArray nécessaire
		
		// Mock pour l'instant
		/*
		JsonArrayBuilder builder = Json.createArrayBuilder();
		builder.add(Json.createObjectBuilder()
			.add("name", "Power")
			.add("unit", "W")
			.add("value", "120"));
		builder.add(Json.createObjectBuilder()
			.add("name", "Temperature")
			.add("unit", "°C")
			.add("value", "25"));
		builder.add(Json.createObjectBuilder()
						.add("name", "Current")
						.add("unit", "A")
						.add("value", "25"));
		JsonArray data = builder.build();
		*/
			
		JsonArray data = buildLiveData(userId);
		return data;
	
		/*List<LiveData> liveDataList = liveDataService.getAllLiveData();
		String s = "";
		for (LiveData ld: liveDataList){
			s += ld.getSensor().getMeasureName() + " " + ld.getSensor().getUnit() + "\n";
		}
		return s;*/
	}
	
	public JsonArray buildLiveData(Long userId) {
		// Pour chaque liveData:
		// 	Récupérer le nom de la ressource -> ok
		//	Récupérer l'unité de la ressource -> ok
		//  Faire le calcul de la valeur à afficher
		
		//JsonArray arr = Json.createArrayBuilder().build();
		JsonArrayBuilder builder = Json.createArrayBuilder();
		
		List<LiveData> liveDatas = liveDataService.getAllLiveData(); 
		
		for (int i=0; i<liveDatas.size(); i++){
			LiveData ld = liveDatas.get(i);
			String measure = ld.getSensor().getMeasureName();
			String unit = ld.getSensor().getUnit();
			//String value = "5"; // Mock
			String value = Double.toString(userService.getSumBySensor(userId, ld.getSensor().getName(), "0", "0"));
			builder.add(overviewJsonBuilder.buildLiveDataJson(measure, unit, value));
		}
	
		return builder.build();
	}
}

