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
	
    /**
     * <b>addColorToColumns</b>
     * <p>
     * {@code public List<String> addColorToColumns(List<String> columns)}
     * <p>
     * 
     * add resource name of color in column.
     * 
     * @param columns - column's names
     * @return
     * A list of column's name in {@code String} format.
     */
	public List<String> addColorToColumns(List<String> columns) {
		columns.add("Hue [° ]");
		columns.add("Saturation ");
		columns.add("Kelvin [K ]");
		return columns;
	}
	
	/**
     * <b>addColorToValues</b>
     * <p>
     * {@code public List<String> addColorToValues(List<String> values, String deviceType, String deviceId)}
     * <p>
     * 
     * Add the values of color.
     * 
     * @param values - values to add.
     * @param deviceType - The type of device to add values
     * @param deviceId - The device's id.
     * @return
     * A list of values in {@code String} format.
     */
	public List<String> addColorToValues(List<String> values, String deviceType, String deviceId) {
		List<String> stringsOfColorSensor = backEndFacade.getDeviceDataLiveColor(deviceType, deviceId);
		for (String string: stringsOfColorSensor) {
			values.add(string);
		}
		return values;
	}
	
	/**
     * <b>buildTableForDeviceType</b>
     * <p>
     * {@code public JsonArray buildTableForDeviceType(String deviceType, Long userId)}
     * <p>
     * 
     * Build a table with column's names: DeviceId, Owner; and their values.
     * 
     * @param deviceType - The device type.
     * @param userId - The user's id.
     * @return
     * A {@code JsonArray}.
     */
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
	
	/**
     * <b>buildTableForUser</b>
     * <p>
     * {@code public JsonArray buildTableForUser(Long userId)}
     * <p>
     * 
     * Build a table for a user with column's names: UserId, Username; and their values
     * 
     * @param userId - The user's id
     * @return
     * A {@code JsonArray} .
     */
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

    /**
     * <b>buildTableForSensorType</b>
     * <p>
     * {@code public JsonArray buildTableForSensorType(String sensorName, Long userId)}
     * <p>
     * 
     * Build a table with column's names: DeviceId, DeviceType, Owner, nad their values. 
     * 
     * @param sensorName
     * @param userId
     * @return
     */
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
	
}
