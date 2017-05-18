package ch.unige.pinfo.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.UserService;

public class TableService {
	@Inject
	private UserService userService;
	
	@Inject
	private TableJsonBuilder tableJsonBuilder;
	
	public JsonArray buildTableForDeviceType(String deviceType, Long userId) {
		// Colonnes: Device Id, sensorValue1, sensorValue2, ..., sensorValueN
		
		// Initialisation des structures necessaires
		JsonArrayBuilder tableBuilder = Json.createArrayBuilder();
		JsonArrayBuilder columnsBuilder = Json.createArrayBuilder();
		JsonArrayBuilder valuesBuilder = Json.createArrayBuilder();
		
		List<String> columnsList = new ArrayList<String>();
		List<String> valuesList = new ArrayList<String>();
		
		// Recuperation des senseurs et des devices du user
		Set<Sensor> sensors = userService.getSensorsForTypeDevice(deviceType);
		List<Device> devices = userService.getAllDevicesForUserByTypeDevice(userId, deviceType);
		
		
		//TODO: Accéder directement aux columns plutot que de copier
		
		// Creation du JSON de la colonne
		columnsBuilder.add(tableJsonBuilder.buildColumn("DeviceId"));
		columnsList.add("DeviceId");
		for (Sensor sensor: sensors){
			String measure = sensor.getMeasureName();
			String unit = sensor.getUnit();
			String content = measure + "  [" + unit + " ]";
			columnsBuilder.add(tableJsonBuilder.buildColumn(content));
			columnsList.add(content);
		}
		
		
		// Creation du JSON des lignes
		for (Device device: devices){
			valuesList.clear();
			valuesList.add(device.getDeviceId()); // Ajout du device id
			for (Sensor sensor: sensors) {
				valuesList.add(userService.getDeviceDataLive(device.getId(), sensor.getName())); // Ajout des valeurs pour chaque senseur du device
			}
			valuesBuilder.add(tableJsonBuilder.buildRow(columnsList, valuesList)); // Ajout d'un row
		}
		
		tableBuilder.add(columnsBuilder.build()); // Ajout du JSON des columns
		tableBuilder.add(valuesBuilder.build()); // Ajout du JSON des rows
		return tableBuilder.build(); // Construction du JSON final
	}
	
	
	public JsonArray buildTableForSensorType(String sensorName, Long userId) {
		// Colonnes: device id, owner, type device, value
		
		JsonArrayBuilder tableBuilder = Json.createArrayBuilder();
		JsonArrayBuilder columnsBuilder = Json.createArrayBuilder();
		JsonArrayBuilder valuesBuilder = Json.createArrayBuilder();
		
		List<String> columnsList = new ArrayList<String>();
		List<String> valuesList = new ArrayList<String>();
		
		List<Device> devices = userService.getAllDevicesForUserBySensorName(userId, sensorName);
		
		Sensor sensor = userService.getSensorFromSensorName(sensorName);
		String valueColumn = sensor.getMeasureName() + "  [" + sensor.getUnit() + " ]";
		
		columnsBuilder.add(tableJsonBuilder.buildColumn("DeviceId"));
		columnsBuilder.add(tableJsonBuilder.buildColumn("Owner"));
		columnsBuilder.add(tableJsonBuilder.buildColumn("DeviceType"));
		columnsBuilder.add(tableJsonBuilder.buildColumn(valueColumn));
		
		columnsList.add("DeviceId");
		columnsList.add("Owner");
		columnsList.add("DeviceType");
		columnsList.add(valueColumn);
		
		for (Device device: devices) {
			valuesList.clear();
			valuesList.add(device.getDeviceId());
			valuesList.add(device.getOwner().getUsername());
			valuesList.add(device.getType().getName());
			valuesList.add(userService.getDeviceDataLive(device.getId(), sensorName));
			valuesBuilder.add(tableJsonBuilder.buildRow(columnsList, valuesList));
		}
	
		tableBuilder.add(columnsBuilder.build());
		tableBuilder.add(valuesBuilder.build());
		return tableBuilder.build();
	}
	
	public JsonArray buildTableForUser(Long userId){
		// Colonnes: device id, owner, type device, value
		
		JsonArrayBuilder tableBuilder = Json.createArrayBuilder();
		JsonArrayBuilder columnsBuilder = Json.createArrayBuilder();
		JsonArrayBuilder valuesBuilder = Json.createArrayBuilder();
		
		List<String> columnsList = new ArrayList<String>();
		List<String> valuesList = new ArrayList<String>();
		
		// TODO: A réfactorer avec la nouvelle architecture
		List<User> users;
		String role = userService.getUserRoleById(userId);
		
		if (role.equals("Manager")) {
			users = userService.getUsersOfManager(userId);
		} else if (role.equals("SysAdmin")) {
			users = userService.getUsersOfSysAdmin(userId);
		} else {
			users = new ArrayList<User>();
		}
		
		List<LiveData> liveDatas = userService.getAllLiveData();
		
		columnsBuilder.add(tableJsonBuilder.buildColumn("Username"));
		columnsList.add("Username");
		for (LiveData liveData: liveDatas) {
			Sensor sensor = liveData.getSensor();
			String content =  sensor.getMeasureName() + "  [" + sensor.getUnit() + " ]";
			columnsBuilder.add(tableJsonBuilder.buildColumn(content));
			columnsList.add(content);
		}
		
		for (User user: users) {
			valuesList.clear();
			valuesList.add(user.getUsername());
			for (LiveData liveData: liveDatas) {
				valuesList.add(Double.toString(computeLiveData(user.getId(), liveData)));
			}
			valuesBuilder.add(tableJsonBuilder.buildRow(columnsList, valuesList));
		}
		
	
		tableBuilder.add(columnsBuilder.build());
		tableBuilder.add(valuesBuilder.build());
		return tableBuilder.build();		
	}
	
	public double computeLiveData(Long userId, LiveData liveData) {
		double res = 0;
		if (liveData.getComputeType().equals("Sum")) {
			res = userService.getSumSensorLiveForUser(userId, liveData.getSensor().getName());
		} else if (liveData.getComputeType().equals("Average")) {
			res = userService.getAvgSensorLiveForUser(userId, liveData.getSensor().getName());
		}
		return res;
	}
}
