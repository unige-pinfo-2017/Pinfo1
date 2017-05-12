package ch.unige.pinfo.overview.service;

import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.user.service.UserService;

public class OverviewService {
	@Inject
	private LiveDataService liveDataService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private OverviewJsonBuilder overviewJsonBuilder;
	
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
