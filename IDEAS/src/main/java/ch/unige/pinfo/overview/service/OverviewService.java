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
	
	/**
	 * <b>buildLiveData</b>
	 * <p>
	 * {@code public JsonArray buildLiveData(Long userId)}
	 * <p>
	 * 
	 * Build a {@code JsonArray} of the live data that will be display on the overview for a user.  
	 * 
	 * @param userId - The user's id.
	 * @return
	 * A {@code JsonArray} of the live data.
	 */
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
	
	/**
	 * <b>buildHiddenData</b>
	 * <p>
	 * {@code public JsonArray buildHiddenData(Long userId)}
	 * <p>
	 * 
	 * Build a {@code JsonArray} of the hidden data that they're not displayed for a user.
	 * 
	 * @param userId - The user's id.
	 * @return
	 * A {@code JsonArray} of hidden data.
	 */
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
	
	/**
	 * <b>getMeasureNames</b>
	 * <p>
	 * {@code public List<String> getMeasureNames(Set<LiveData> liveDatas)}
	 * <p>
	 * 
	 * Get the name of the measure for a set of live data.
	 * 
	 * @param liveDatas - Set of live data we want the name measure.
	 * @return
	 * A list of the measure in {@code String} format.
	 */
	public List<String> getMeasureNames(Set<LiveData> liveDatas) {
		List<String> measureNames = new ArrayList<String>();
		for (LiveData liveData: liveDatas) {
			measureNames.add(liveData.getSensor().getMeasureName());
		}
		return measureNames;
	}
	
	/**
	 * <b>getUnits</b>
	 * <p>
	 * {@code public List<String> getUnits(Set<LiveData> liveDatas)}
	 * <p>
	 * 
	 * Get the unit of a set of live data.
	 * 
	 * @param liveDatas - The set of data we want the unit.
	 * @return
	 * A list of unit in {@code String} format.
	 */
	public List<String> getUnits(Set<LiveData> liveDatas) {
		List<String> units = new ArrayList<String>();
		for (LiveData liveData: liveDatas) {
			units.add(liveData.getSensor().getUnit());
		}
		return units;
	}
	
	/**
	 * <b>roundDecimal</b>
	 * <p>
	 * {@code public double roundDecimal(double value, int decimalNumber)}
	 * <p>
	 * 
	 * Round the value to a specify decimalNumber 
	 * 
	 * @param value - value to round
	 * @param decimalNumber - decimal to round the value.
	 * @return
	 * The rounded value.
	 */
	public double roundDecimal(double value, int decimalNumber) {
		double powerOfTen = Math.pow(10, decimalNumber);
		return Math.round(value*powerOfTen)/powerOfTen;
	}

	/**
	 * <b>addPreference</b>
	 * <p>
	 * {@code public Response addPreference(Long userId, String measureName)}
	 * <p>
	 * 
	 * Add a preference for a user int the overview. 
	 * 
	 * @param userId - The user's id 
	 * @param measureName - The measure name to add.
	 * @return
	 * A response: 200 if added, 500 if not.
	 */
	public Response addPreference(Long userId, String measureName) {
		Long preferenceId = backEndFacade.getLiveDataIdFromSensorMeasureName(measureName);
		if (!backEndFacade.addPreference(userId, preferenceId)) {
			return Response.status(500).entity("Adding " + measureName + " to preferences failed.").build();
		}
		return Response.status(200).entity(measureName + " added to preferences.").build();
	}

	/**
	 * <b>removePreference</b>
	 * <p>
	 * {@code public Response removePreference(Long userId, String measureName)}
	 * <p>
	 * 
	 * Remove a preference from the overview for a user.
	 * 
	 * @param userId - The user's id
	 * @param measureName - The measure name to remove
	 * @return
	 * A response: 200 if remove, 500 if not.
	 */
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
