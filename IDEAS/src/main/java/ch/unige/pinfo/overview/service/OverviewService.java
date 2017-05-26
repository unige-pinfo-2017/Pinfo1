package ch.unige.pinfo.overview.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.core.Response;


import ch.unige.pinfo.backend.BackEndFacade;
import ch.unige.pinfo.overview.dom.LiveData;

@Stateless
public class OverviewService {	
	@Inject
	private OverviewJsonBuilder overviewJsonBuilder;
	
	@Inject
	private BackEndFacade backEndFacade;
	
	private final int decimalNumber = 2;
	
	public JsonArray buildLiveData(Long userId) {
		Set<LiveData> preferences = backEndFacade.getUserPreferences(userId);
		List<String> measureNames = getMeasureNames(preferences);
		List<String> units = getUnits(preferences);
		List<Double> values = backEndFacade.getLiveDatas(userId);
		JsonArrayBuilder builder = Json.createArrayBuilder();
		
		for (int i=0; i<measureNames.size(); i++) {
			builder.add(overviewJsonBuilder.buildLiveDataJson(
					measureNames.get(i), 
					units.get(i),
					Double.toString(roundDecimal(values.get(i), decimalNumber))
			));
		}
		
		return builder.build();
	}
	
	public JsonArray buildHiddenData(Long userId) {
		List<LiveData> liveDatas = backEndFacade.getAllLiveDatas();
		Set<LiveData> preferences = backEndFacade.getUserPreferences(userId);
		
		Iterator<LiveData> it = liveDatas.iterator();
		while (it.hasNext()) {
		   LiveData liveData = it.next(); // must be called before you can call i.remove()
		   if (preferences.contains(liveData)) {
			   it.remove();
		   }
		}
		
		return overviewJsonBuilder.buildHiddenDatas(liveDatas);
	}
	
	public List<String> getMeasureNames(Set<LiveData> liveDatas) {
		List<String> measureNames = new ArrayList<String>();
		for (LiveData liveData: liveDatas) {
			measureNames.add(liveData.getSensor().getMeasureName());
		}
		return measureNames;
	}
	
	public List<String> getUnits(Set<LiveData> liveDatas) {
		List<String> units = new ArrayList<String>();
		for (LiveData liveData: liveDatas) {
			units.add(liveData.getSensor().getUnit());
		}
		return units;
	}
	
	public double roundDecimal(double value, int decimalNumber) {
		double powerOfTen = Math.pow(10, decimalNumber);
		return Math.round(value*powerOfTen)/powerOfTen;
	}

	public Response addPreference(Long userId, String measureName) {
		Long preferenceId = backEndFacade.getLiveDataIdFromSensorMeasureName(measureName);
		if (!backEndFacade.addPreference(userId, preferenceId)) {
			return Response.status(500).entity("Adding " + measureName + " to preferences failed.").build();
		}
		return Response.status(200).entity(measureName + " added to preferences.").build();
	}

	public Response removePreference(Long userId, String measureName) {
		Long preferenceId = backEndFacade.getLiveDataIdFromSensorMeasureName(measureName);
		if (!backEndFacade.removePreference(userId, preferenceId)) {
			return Response.status(500).entity("Removing " + measureName + " from preferences failed.").build();
		}
		return Response.status(200).entity(measureName + " removed from preferences.").build();
	}

	
	/*public JsonArray buildLiveData2(Long userId) {
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
	}*/
}
