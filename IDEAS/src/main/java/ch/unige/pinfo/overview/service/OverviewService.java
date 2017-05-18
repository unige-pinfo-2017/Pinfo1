package ch.unige.pinfo.overview.service;

import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.user.dom.Basic;
import ch.unige.pinfo.user.dom.Manager;
import ch.unige.pinfo.user.dom.SysAdmin;
import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.UserService;

public class OverviewService {
	@Inject
	private LiveDataService liveDataService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private OverviewJsonBuilder overviewJsonBuilder;
	
	/*public JsonArray buildLiveData2(Long userId) {
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
			String value = Double.toString(computeLiveData(userId, ld));
			builder.add(overviewJsonBuilder.buildLiveDataJson(measure, unit, value));
		}
	
		return builder.build();
	}*/
	
	public JsonArray buildLiveData(Long userId) {
		String role = userService.getUserRoleById(userId);
		if (role.equals("Manager")) {
			return buildLiveDataManager(userId);
		} else if (role.equals("SysAdmin")) {
			return buildLiveDataSysAdmin(userId);
		} else if (role.equals("Basic")) {
			return buildLiveDataBasic(userId);
		}
		return null;
	}
	
	public JsonArray buildLiveDataBasic(Long userId) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		List<LiveData> liveDatas = liveDataService.getAllLiveData(); 
		
		Basic basic = userService.getBasicById(userId);
		
		for (LiveData liveData: liveDatas) {
			String measure = liveData.getSensor().getMeasureName();
			String unit = liveData.getSensor().getUnit();
			String value = Double.toString(computeLiveData(basic.getId(), liveData));
			builder.add(overviewJsonBuilder.buildLiveDataJson(measure, unit, value));
		}
	
		return builder.build();
	}
	
	public JsonArray buildLiveDataManager(Long userId) {
		Manager manager = userService.getManagerById(userId);
		
		JsonArrayBuilder builder = Json.createArrayBuilder();
		List<User> users = userService.getUsersOfManager(manager.getId());
		List<LiveData> liveDatas = liveDataService.getAllLiveData(); 
		
		for (LiveData liveData: liveDatas) {
			String measure = liveData.getSensor().getMeasureName();
			String unit = liveData.getSensor().getUnit();
			String value = Double.toString(combineLiveData(users, liveData));
			builder.add(overviewJsonBuilder.buildLiveDataJson(measure, unit, value));
		}

		return builder.build();
	}
	
	public JsonArray buildLiveDataSysAdmin(Long userId) {
		SysAdmin sysAdmin = userService.getSysAdminById(userId);
		
		JsonArrayBuilder builder = Json.createArrayBuilder();
		List<User> users = userService.getUsersOfManager(sysAdmin.getId());
		List<LiveData> liveDatas = liveDataService.getAllLiveData(); 
		
		for (LiveData liveData: liveDatas) {
			String measure = liveData.getSensor().getMeasureName();
			String unit = liveData.getSensor().getUnit();
			String value = Double.toString(combineLiveData(users, liveData));
			builder.add(overviewJsonBuilder.buildLiveDataJson(measure, unit, value));
		}

		return builder.build();
	}
	
	
	public LiveDataService getLiveDataService() {
		return this.liveDataService;
	}
	
	public double combineLiveData(List<User> users, LiveData liveData) {
		double res = 0;
		if (liveData.getComputeType().equals("Sum")) {
			res = userService.getSumSensorLiveForUsers(users, liveData.getSensor().getName());
		} else if (liveData.getComputeType().equals("Average")) {
			res = userService.getAvgSensorLiveForUsers(users, liveData.getSensor().getName());
		}
		return roundDecimal(res,2);
	}
	
	public double computeLiveData(Long userId, LiveData liveData) {
		double res = 0;
		if (liveData.getComputeType().equals("Sum")) {
			res = userService.getSumSensorLiveForUser(userId, liveData.getSensor().getName());
		} else if (liveData.getComputeType().equals("Average")) {
			res = userService.getAvgSensorLiveForUser(userId, liveData.getSensor().getName());
		}
		return roundDecimal(res,2);
	}
	
	public double roundDecimal(double value, int decimalNumber) {
		double powerOfTen = Math.pow(10, decimalNumber);
		return Math.round(value*powerOfTen)/powerOfTen;
	}
}
