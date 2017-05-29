package ch.unige.pinfo.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;

import ch.unige.pinfo.backend.BackEndFacade;
import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.user.dom.User;

@Stateless
public class TableService {
	
	@Inject
	private TableJsonBuilder tableJsonBuilder;

	@Inject
	private BackEndFacade backEndFacade;	
	
	public List<String> addColorToColumns(List<String> columns) {
		columns.add("Hue [° ]");
		columns.add("Saturation ");
		columns.add("Kelvin [K ]");
		return columns;
	}
	
	public List<String> addColorToValues(List<String> values, String deviceType, String deviceId) {
		List<String> stringsOfColorSensor = backEndFacade.getDeviceDataLiveColor(deviceType, deviceId);
		for (String string: stringsOfColorSensor) {
			values.add(string);
		}
		return values;
	}
	
	public JsonArray buildTableForDeviceType(String deviceType, Long userId) {
		// Initialise data structures
		List<String> columns = new ArrayList<String>();
		List<List<String>> allValues = new ArrayList<List<String>>();
		
		// Fetch data
		Set<Sensor> sensors = backEndFacade.getSensorsForTypeDevice(deviceType);
		List<Device> devices = backEndFacade.getAllDevicesForUserByTypeDevice(userId, deviceType);
		
		// Construct columns list
		columns.add("DeviceId");
		columns.add("Owner");
		for (Sensor sensor: sensors) {
			if ("colorSensor".equals(sensor.getName())) {
				columns = addColorToColumns(columns);
			} else {
				columns.add(sensor.getMeasureName() + " [" + sensor.getUnit() + " ]" );
			}
		}
		
		// Construct values list
		for (Device device: devices) {
			List<String> values = new ArrayList<String>();
			values.add(device.getDeviceId());
			values.add(device.getOwner().getUsername());
			for (Sensor sensor: sensors) {
				if ("colorSensor".equals(sensor.getName())) {
					values = addColorToValues(values, deviceType, device.getDeviceId());
				} else {
					values.add(backEndFacade.getDeviceDataLive(device.getDeviceId(), sensor.getName()));
				}
			}
			allValues.add(values);
		}
		
		// Build table from lists
		return tableJsonBuilder.buildTable(columns, allValues);
	}
	
	
	public JsonArray buildTableForUser(Long userId) {
		List<String> columns = new ArrayList<String>();
		List<List<String>> allValues = new ArrayList<List<String>>();
		
		List<LiveData> liveDatas = backEndFacade.getAllLiveDatas();
		List<User> users = backEndFacade.getUsersList(userId);
		
		columns.add("UserId");
		columns.add("Username");
		for (LiveData liveData: liveDatas) {
			Sensor sensor = liveData.getSensor();
			columns.add(sensor.getMeasureName() + " [" + sensor.getUnit() + " ]");
		}
		
		for (User user: users) {
			List<String> values = new ArrayList<String>();
			values.add(Long.toString(user.getId()));
			values.add(user.getUsername());
			for (LiveData liveData: liveDatas) {
				values.add(Double.toString(backEndFacade.getLiveDataValueForUser(user.getId(), liveData.getComputeType(), liveData.getSensor().getName())));
			}
			allValues.add(values);
		}
		
		return tableJsonBuilder.buildTable(columns, allValues);
	}

	public JsonArray buildTableForSensorType(String sensorName, Long userId) {
		List<String> columns = new ArrayList<String>();
		List<List<String>> allValues = new ArrayList<List<String>>();
		
		List<Device> devices = backEndFacade.getAllDevicesForUserBySensorName(userId, sensorName);
		Sensor sensor = backEndFacade.getSensorFromSensorName(sensorName);
		
		columns.add("DeviceId");
		columns.add("DeviceType");
		columns.add("Owner");
		
		if ("colorSensor".equals(sensor.getName())) {
			columns = addColorToColumns(columns);
		} else {
			columns.add("Value [" + sensor.getUnit() + " ]");
		}
		
		for (Device device: devices) {
			List<String> values = new ArrayList<String>();
			values.add(device.getDeviceId());
			values.add(device.getType().getName());
			values.add(device.getOwner().getUsername());
			
			if ("colorSensor".equals(sensor.getName())) {
				values = addColorToValues(values, device.getType().getName(), device.getDeviceId());
			} else {
				values.add(backEndFacade.getDeviceDataLive(device.getDeviceId(), sensorName));
			}
			allValues.add(values);
		}
		
		return tableJsonBuilder.buildTable(columns, allValues);
	}
	
	public String getColorTest() {
		String s="";
		List<String> values = backEndFacade.getDeviceDataLiveColor("Light", "id9");
		for (String value: values) {
			s += value + " ";
		}
		return s;
	}
	
	/*public JsonArray buildTableForDeviceType2(String deviceType, Long userId) {
		// Colonnes: Device Id, sensorValue1, sensorValue2, ..., sensorValueN
		
		// Initialisation des structures necessaires
		JsonArrayBuilder tableBuilder = Json.createArrayBuilder();
		JsonArrayBuilder columnsBuilder = Json.createArrayBuilder();
		JsonArrayBuilder valuesBuilder = Json.createArrayBuilder();
		
		List<String> columnsList = new ArrayList<String>();
		List<String> valuesList = new ArrayList<String>();
		
		// Recuperation des senseurs et des devices du user
		Set<Sensor> sensors = backEndFacade.getSensorsForTypeDevice(deviceType);
		List<Device> devices = backEndFacade.getAllDevicesForUserByTypeDevice(userId, deviceType);
		
		
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
				valuesList.add(backEndFacade.getDeviceDataLive(device.getDeviceId(), sensor.getName())); // Ajout des valeurs pour chaque senseur du device
			}
			valuesBuilder.add(tableJsonBuilder.buildRow(columnsList, valuesList)); // Ajout d'un row
		}
		
		tableBuilder.add(columnsBuilder.build()); // Ajout du JSON des columns
		tableBuilder.add(valuesBuilder.build()); // Ajout du JSON des rows
		return tableBuilder.build(); // Construction du JSON final
	}*/
	
	/*public JsonArray buildTableForSensorType2(String sensorName, Long userId) {
		// Colonnes: device id, owner, type device, value
		
		JsonArrayBuilder tableBuilder = Json.createArrayBuilder();
		JsonArrayBuilder columnsBuilder = Json.createArrayBuilder();
		JsonArrayBuilder valuesBuilder = Json.createArrayBuilder();
		
		List<String> columnsList = new ArrayList<String>();
		List<String> valuesList = new ArrayList<String>();
		
		List<Device> devices = backEndFacade.getAllDevicesForUserBySensorName(userId, sensorName);
		
		Sensor sensor = backEndFacade.getSensorFromSensorName(sensorName);
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
			valuesList.add(backEndFacade.getDeviceDataLive(device.getDeviceId(), sensorName));
			valuesBuilder.add(tableJsonBuilder.buildRow(columnsList, valuesList));
		}
	
		tableBuilder.add(columnsBuilder.build());
		tableBuilder.add(valuesBuilder.build());
		return tableBuilder.build();
	}*/
	

	
	
	/*public JsonArray buildTableForUser2(Long userId){
		// Colonnes: device id, owner, type device, value
		
		JsonArrayBuilder tableBuilder = Json.createArrayBuilder();
		JsonArrayBuilder columnsBuilder = Json.createArrayBuilder();
		JsonArrayBuilder valuesBuilder = Json.createArrayBuilder();
		
		List<String> columnsList = new ArrayList<String>();
		List<String> valuesList = new ArrayList<String>();
		
		// TODO: A réfactorer avec la nouvelle architecture
		List<User> users;
		String role = backEndFacade.getUserRoleById(userId);
		
		if (role.equals("Manager")) {
			users = backEndFacade.getUsersOfManager(userId);
		} else if (role.equals("SysAdmin")) {
			users = backEndFacade.getUsersOfSysAdmin(userId);
		} else {
			users = new ArrayList<User>();
		}
		
		List<LiveData> liveDatas = backEndFacade.getAllLiveDatas();
		
		columnsBuilder.add(tableJsonBuilder.buildColumn("UserId"));
		columnsBuilder.add(tableJsonBuilder.buildColumn("Username"));
		columnsList.add("UserId");
		columnsList.add("Username");
		for (LiveData liveData: liveDatas) {
			Sensor sensor = liveData.getSensor();
			String content =  sensor.getMeasureName() + "  [" + sensor.getUnit() + " ]";
			columnsBuilder.add(tableJsonBuilder.buildColumn(content));
			columnsList.add(content);
		}
		
		for (User user: users) {
			valuesList.clear();
			valuesList.add(Long.toString(user.getId()));
			valuesList.add(user.getUsername());
			for (LiveData liveData: liveDatas) {
				//valuesList.add(Double.toString(computeLiveData(user.getId(), liveData)));
				valuesList.add(Double.toString(backEndFacade.getLiveDataValueForUser(user.getId(), liveData.getComputeType(), liveData.getSensor().getName())));
			}
			valuesBuilder.add(tableJsonBuilder.buildRow(columnsList, valuesList));
		}
		
	
		tableBuilder.add(columnsBuilder.build());
		tableBuilder.add(valuesBuilder.build());
		return tableBuilder.build();		
	}*/

}
