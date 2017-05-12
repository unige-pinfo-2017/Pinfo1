package ch.unige.pinfo.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.user.service.UserService;

@Path("/table")
public class TableFacadeRest {
	@Inject
	private UserService userService;
	
	@Inject
	private TableJsonBuilder tableJsonBuilder;
	
	@GET
	@Path("/device/columns")
	@Produces({ "application/json" })
	public JsonArray getTableDeviceColumns(@QueryParam("type") String type){
		
		JsonArray powerSocket = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("prop", "deviceId:" + "unit"))
				.add(Json.createObjectBuilder()
						.add("prop", "power"))
				.add(Json.createObjectBuilder()
						.add("prop", "status"))
				.add(Json.createObjectBuilder()
						.add("prop", "current"))
				.build();
		
		JsonArray beacon = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("prop", "deviceId"))
				.add(Json.createObjectBuilder()
						.add("prop", "temperature"))
				.add(Json.createObjectBuilder()
						.add("prop", "light"))
				.add(Json.createObjectBuilder()
						.add("prop", "battery"))
				.build();
		
		if (type.equals("powersocket")){
			return powerSocket;
		} else if (type.equals("beacon")){
			return beacon;
		} else {
			return null;
		}
	}
	
	@GET
	@Path("/device/data")
	@Produces({ "application/json" })
	public JsonArray getTableDevice(@QueryParam("type") String type){
		
		JsonArray powerSocket = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("deviceId", "1")
						.add("power", "5.0")
						.add("status", "1.0")
						.add("current", "1.6"))
				.add(Json.createObjectBuilder()
						.add("deviceId", "2")
						.add("power", "4.0")
						.add("status", "1.0")
						.add("current", "1.2"))
				.build();
		
		JsonArray beacon = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("deviceId", "3")
						.add("temperature", "30.0")
						.add("light", "9.0")
						.add("battery", "0.3"))
				.add(Json.createObjectBuilder()
						.add("deviceId", "4")
						.add("temperature", "40.0")
						.add("light", "2.0")
						.add("battery", "0.9"))
				.build();
		
		if (type.equals("powersocket")){
			return powerSocket;
		} else if (type.equals("beacon")){
			return beacon;
		} else {
			return null;
		}
	}
	
	@GET
	@Path("/columns")
	@Produces({"application/json"})
	@Transactional
	public JsonArray buildColumns (@QueryParam("type") String deviceType, @QueryParam("userid") Long userId) {
		// Initialisation des structures necessaires
		JsonArrayBuilder tableBuilder = Json.createArrayBuilder();
		JsonArrayBuilder columnsBuilder = Json.createArrayBuilder();
		JsonArrayBuilder rowsBuilder = Json.createArrayBuilder();
		
		List<String> columns = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		
		// Recuperation des senseurs et des devices du user
		Set<Sensor> sensors = userService.getSensorsForTypeDevice(deviceType);
		List<Device> devices = userService.getAllDevicesForUserByTypeDevice(userId, deviceType);
		
		
		//TODO: Accéder directement aux columns plutot que de copier
		// Creation du JSON de la colonne
		columnsBuilder.add(tableJsonBuilder.buildDeviceColumns("DeviceId"));
		columns.add("DeviceId");
		for (Sensor sensor: sensors){
			String measure = sensor.getMeasureName();
			String unit = sensor.getUnit();
			String content = measure + "  [" + unit + " ]";
			columnsBuilder.add(tableJsonBuilder.buildDeviceColumns(content));
			columns.add(content);
		}
		
		
		// Creation du JSON des lignes
		for (Device device: devices){
			values.clear();
			values.add(device.getDeviceId()); // Ajout du device id
			for (Sensor sensor: sensors) {
				values.add(Double.toString((userService.getDeviceData(device.getId(), sensor.getName(), "0", "0")))); // Ajout des valeurs pour chaque senseur du device
			}
			rowsBuilder.add(tableJsonBuilder.buildDeviceRows(columns, values)); // Ajout d'un row
		}
		
		tableBuilder.add(columnsBuilder.build()); // Ajout du JSON des columns
		tableBuilder.add(rowsBuilder.build()); // Ajout du JSON des rows
		return tableBuilder.build(); // Construction du JSON final
	}
}
