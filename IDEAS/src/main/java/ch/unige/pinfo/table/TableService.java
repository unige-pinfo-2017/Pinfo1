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
import ch.unige.pinfo.user.service.UserService;

public class TableService {
	@Inject
	private UserService userService;
	
	@Inject
	private TableJsonBuilder tableJsonBuilder;
	
	public JsonArray buildColumns (String deviceType, Long userId) {
		// Initialisation des structures necessaires
		JsonArrayBuilder tableBuilder = Json.createArrayBuilder();
		JsonArrayBuilder columnsBuilder = Json.createArrayBuilder();
		JsonArrayBuilder rowsBuilder = Json.createArrayBuilder();
		
		List<String> columnsList = new ArrayList<String>();
		List<String> valuesList = new ArrayList<String>();
		
		// Recuperation des senseurs et des devices du user
		Set<Sensor> sensors = userService.getSensorsForTypeDevice(deviceType);
		List<Device> devices = userService.getAllDevicesForUserByTypeDevice(userId, deviceType);
		
		
		//TODO: Accéder directement aux columns plutot que de copier
		// Creation du JSON de la colonne
		columnsBuilder.add(tableJsonBuilder.buildDeviceColumns("DeviceId"));
		columnsList.add("DeviceId");
		for (Sensor sensor: sensors){
			String measure = sensor.getMeasureName();
			String unit = sensor.getUnit();
			String content = measure + "  [" + unit + " ]";
			columnsBuilder.add(tableJsonBuilder.buildDeviceColumns(content));
			columnsList.add(content);
		}
		
		
		// Creation du JSON des lignes
		for (Device device: devices){
			valuesList.clear();
			valuesList.add(device.getDeviceId()); // Ajout du device id
			for (Sensor sensor: sensors) {
				valuesList.add(Double.toString((userService.getDeviceData(device.getId(), sensor.getName(), "0", "0")))); // Ajout des valeurs pour chaque senseur du device
			}
			rowsBuilder.add(tableJsonBuilder.buildDeviceRows(columnsList, valuesList)); // Ajout d'un row
		}
		
		tableBuilder.add(columnsBuilder.build()); // Ajout du JSON des columns
		tableBuilder.add(rowsBuilder.build()); // Ajout du JSON des rows
		return tableBuilder.build(); // Construction du JSON final
	}
}
