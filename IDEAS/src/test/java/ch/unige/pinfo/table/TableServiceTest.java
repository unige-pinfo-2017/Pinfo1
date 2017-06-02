package ch.unige.pinfo.table;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ch.unige.pinfo.backend.BackEndFacade;
import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.device.dom.TypeDevice;
import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.user.dom.User;


public class TableServiceTest {
	
	@Mock
	BackEndFacade mockBackEndFacade;
	
	@Mock
	TableJsonBuilder mockTableJsonBuilder;
	
	@InjectMocks
	TableService ts = new TableService();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addColorToColumnsTest() {
		List<String> cols = new ArrayList<String>();
		cols.add("mock");
		
		List<String> expected = new ArrayList<String>();
		expected.addAll(cols);
		expected.add("Hue [° ]");
		expected.add("Saturation ");
		expected.add("Kelvin [K ]");
		
		List<String> output = ts.addColorToColumns(cols);
		
		assertEquals(expected, output);
	}
	
	@Test
	public void addColorToValuesTest() {
		List<String> values = new ArrayList<String>();
		values.add("1");
		
		String deviceType = "Light";
		String deviceId = "id1";
		
		List<String> colorValues = new ArrayList<String>();
		colorValues.add("360");
		colorValues.add("1");
		colorValues.add("9999");
		
		when(mockBackEndFacade.getDeviceDataLiveColor(deviceType, deviceId))
			.thenReturn(colorValues);
		
		List<String> expected = new ArrayList<String>();
		expected.addAll(values);
		expected.addAll(colorValues);
		
		List<String> output = ts.addColorToValues(values, deviceType, deviceId);
		
		assertEquals(expected, output);
	}
	
	/*@Test
	public void buildTableForDeviceTypeTest() {
		String deviceType = "Light";
		Long userId = (long) 1;
	
		Sensor sensor1 = new Sensor();
		sensor1.setName("colorSensor");
		Sensor sensor2 = new Sensor();
		sensor2.setName("statusSensor");
		sensor2.setMeasureName("Status");
		sensor2.setUnit("On/Off");
		
		Set<Sensor> sensors = new LinkedHashSet<Sensor>();
		sensors.add(sensor1);
		sensors.add(sensor2);
		when(mockBackEndFacade.getSensorsForTypeDevice(deviceType))
		.thenReturn(sensors);
		
		String username = "user1";
		User user1 = new User();
		user1.setUsername(username);
		String deviceId = "id1";
		Device device1 = new Device();
		device1.setDeviceId(deviceId);
		device1.setOwner(user1);
		List<Device> devices = new ArrayList<Device>();
		devices.add(device1);
		when(mockBackEndFacade.getAllDevicesForUserByTypeDevice(userId, deviceType))
			.thenReturn(devices);

		
		List<String> colorValues = new ArrayList<String>();
		colorValues.add("360");
		colorValues.add("1");
		colorValues.add("9999");
		when(mockBackEndFacade.getDeviceDataLiveColor(deviceType, deviceId))
			.thenReturn(colorValues);
		
		String value = "1";
		when(mockBackEndFacade.getDeviceDataLive(deviceId, sensor2.getName()))
			.thenReturn(value);

		List<String> temp = new ArrayList<String>();
		temp.add("DeviceId");
		temp.add("Owner");
		temp.add("Hue [° ]");
		temp.add("Saturation");
		temp.add("Kelvin [K ]");
		temp.add("Status [On/Off ]");
		
		List<String> temp2 = new ArrayList<String>();
		temp2.addAll(colorValues);
		temp2.add(value);
		
		List<List<String>> temp3 = new ArrayList<List<String>>();
		temp3.add(temp2);
 		
		JsonArray outputCols = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("prop", "DeviceId")
						.build())
				.add(Json.createObjectBuilder()
						.add("prop", "Owner")
						.build())
				.add(Json.createObjectBuilder()
						.add("prop", "Hue [° ]")
						.build())
				.add(Json.createObjectBuilder()
						.add("prop", "Saturation")
						.build())
				.add(Json.createObjectBuilder()
						.add("prop", "Kelvin [K ]")
						.build())
				.add(Json.createObjectBuilder()
						.add("prop", "Status [On/Off ]")
						.build())
				.build();
	
	JsonArray outputValues = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.build())
				.build();
					
	
	JsonArray outputTable = Json.createArrayBuilder()
				.add(outputCols)
				.add(outputValues)
				.build();
		
		when(mockTableJsonBuilder.buildTable(temp, temp3))
			.thenReturn(outputTable);
		
		JsonArray expectedCols = Json.createArrayBuilder()
					.add(Json.createObjectBuilder()
							.add("prop", "DeviceId")
							.build())
					.add(Json.createObjectBuilder()
							.add("prop", "Owner")
							.build())
					.add(Json.createObjectBuilder()
							.add("prop", "Hue [° ]")
							.build())
					.add(Json.createObjectBuilder()
							.add("prop", "Saturation")
							.build())
					.add(Json.createObjectBuilder()
							.add("prop", "Kelvin [K ]")
							.build())
					.add(Json.createObjectBuilder()
							.add("prop", "Status [On/Off ]")
							.build())
					.build();
		
		JsonArray expectedValues = Json.createArrayBuilder()
					.add(Json.createObjectBuilder()
							.build())
					.build();
						
		
		JsonArray expected = Json.createArrayBuilder()
					.add(expectedCols)
					.add(expectedValues)
					.build();
		
		JsonArray output = ts.buildTableForDeviceType(deviceType, userId);
		
		assertEquals(expected, output);
	}*/
	
	/*@Test
	public void buildTableForSensorTypeTest() {
		String sensorName = "colorSensor";
		Long userId = 1l;
		
		Sensor sensor = new Sensor();
		sensor.setName("colorSensor");
		
		User user = new User();
		user.setUsername("user");
		user.setId(userId);
		
		TypeDevice deviceType = new TypeDevice();
		deviceType.setName("Light");
		
		String deviceId = "id1";
		Device device = new Device();
		device.setDeviceId(deviceId);
		device.setType(deviceType);
		device.setOwner(user);
		
		List<Device> devices = new ArrayList<Device>();
		devices.add(device);
		
		List<String> colorValues = new ArrayList<String>();
		colorValues.add("360");
		colorValues.add("1");
		colorValues.add("9999");
		
		List<String> columns = new ArrayList<String>();
		columns.add("DeviceId");
		columns.add("Owner");
		columns.add("Hue [° ]");
		columns.add("Saturation ");
		columns.add("Kelvin [K ]");
		
		List<List<String>> allValues = new ArrayList<List<String>>();
		List<String> values = new ArrayList<String>();
		values.add("360");
		values.add("1");
		values.add("9999");
		allValues.add(values);
		
		JsonArray outputCols = Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("prop", "DeviceId")
										.build())
								.add(Json.createObjectBuilder()
										.add("prop", "Owner")
										.build())
								.add(Json.createObjectBuilder()
										.add("prop", "Hue [° ]")
										.build())
								.add(Json.createObjectBuilder()
										.add("prop", "Saturation ")
										.build())
								.add(Json.createObjectBuilder()
										.add("prop", "Kelvin [K ]")
										.build())
								.build();

		JsonArray outputValues = Json.createArrayBuilder()
									.add(Json.createObjectBuilder()
											.add("DeviceId", "id1")
											.build())
									.add(Json.createObjectBuilder()
											.add("Owner", "owner")
											.build())
									.add(Json.createObjectBuilder()
											.add("Hue [° ]", "360")
											.build())
									.add(Json.createObjectBuilder()
											.add("Saturation ", "1")
											.build())
									.add(Json.createObjectBuilder()
											.add("Kelvin [K ]", "9999")
											.build())
									.build();
				
		JsonArray outputTable = Json.createArrayBuilder()
					.add(outputCols)
					.add(outputValues)
					.build();
		
		when(mockBackEndFacade.getAllDevicesForUserBySensorName(userId, sensorName))
			.thenReturn(devices);
		when(mockBackEndFacade.getSensorFromSensorName(sensorName))
			.thenReturn(sensor);
		when(mockBackEndFacade.getDeviceDataLiveColor(device.getType().getName(), deviceId))
			.thenReturn(colorValues);		
		when(mockTableJsonBuilder.buildTable(columns, allValues))
				.thenReturn(outputTable);
		
		JsonArray expectedCols = Json.createArrayBuilder()
									.add(Json.createObjectBuilder()
											.add("prop", "DeviceId")
											.build())
									.add(Json.createObjectBuilder()
											.add("prop", "Owner")
											.build())
									.add(Json.createObjectBuilder()
											.add("prop", "Hue [° ]")
											.build())
									.add(Json.createObjectBuilder()
											.add("prop", "Saturation ")
											.build())
									.add(Json.createObjectBuilder()
											.add("prop", "Kelvin [K ]")
											.build())
									.build();
		
		JsonArray expectedValues = Json.createArrayBuilder()
									.add(Json.createObjectBuilder()
											.add("DeviceId", "id1")
											.build())
									.add(Json.createObjectBuilder()
											.add("Owner", "owner")
											.build())
									.add(Json.createObjectBuilder()
											.add("Hue [° ]", "360")
											.build())
									.add(Json.createObjectBuilder()
											.add("Saturation ", "1")
											.build())
									.add(Json.createObjectBuilder()
											.add("Kelvin [K ]", "9999")
											.build())
									.build();
									
		JsonArray expected = Json.createArrayBuilder()
								.add(expectedCols)
								.add(expectedValues)
								.build();
	
		JsonArray output = ts.buildTableForSensorType(sensorName, userId);
		
		assertEquals(expected, output);
	}*/
	
	@Test
	public void buildTableForUserTest() {
		Long userId = 1l;
		
		Long subUserId = 2l;
		String subUsername = "subUsername";
		
		String computeType = "Sum";
		String sensorName = "powerSensor";
		
		Sensor sensor = new Sensor();
		sensor.setName(sensorName);
		sensor.setMeasureName("measure");
		sensor.setUnit("unit");
		
		LiveData liveData = new LiveData();
		liveData.setSensor(sensor);
		liveData.setComputeType(computeType);
		
		List<LiveData> liveDatas = new ArrayList<LiveData>();
		liveDatas.add(liveData);
		
		User sub = new User();
		sub.setId(subUserId);
		sub.setUsername(subUsername);
		
		List<User> subs = new ArrayList<User>();
		subs.add(sub);
		
		Double value = 1d;
		
		List<String> columns = new ArrayList<String>();
		columns.add("UserId");
		columns.add("Username");
		columns.add(sensor.getMeasureName() + " [" + sensor.getUnit() + " ]");
		
		List<String> values = new ArrayList<String>();
		values.add(Long.toString(sub.getId()));
		values.add(sub.getUsername());
		values.add(Double.toString(value));
		
		List<List<String>> allValues = new ArrayList<List<String>>();
		allValues.add(values);
		
		JsonArray outputJson = Json.createArrayBuilder()
				.add(Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("prop", "UserId")
								.build())
						.add(Json.createObjectBuilder()
								.add("prop", "Username")
								.build())
						.add(Json.createObjectBuilder()
								.add("prop", sensor.getMeasureName() + " [" + sensor.getUnit() + " ]")
								.build())
						.build())
				.add(Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("UserId", Long.toString(sub.getId()))
								.add("Username", sub.getUsername())
								.add(sensor.getMeasureName() + " [" + sensor.getUnit() + " ]", Double.toString(value))
								.build())
						.build())
				.build();
		
		when(mockBackEndFacade.getAllLiveDatas())
			.thenReturn(liveDatas);
		when(mockBackEndFacade.getUsersList(userId))
			.thenReturn(subs);
		when(mockBackEndFacade.getLiveDataValueForUser(subUserId, computeType, sensorName))
			.thenReturn(value);
		when(mockTableJsonBuilder.buildTable(columns, allValues))
			.thenReturn(outputJson);
		
		JsonArray expected = Json.createArrayBuilder()
				.add(Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("prop", "UserId")
								.build())
						.add(Json.createObjectBuilder()
								.add("prop", "Username")
								.build())
						.add(Json.createObjectBuilder()
								.add("prop", sensor.getMeasureName() + " [" + sensor.getUnit() + " ]")
								.build())
						.build())
				.add(Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("UserId", Long.toString(sub.getId()))
								.add("Username", sub.getUsername())
								.add(sensor.getMeasureName() + " [" + sensor.getUnit() + " ]", Double.toString(value))
								.build())
						.build())
				.build();
		
		JsonArray output = ts.buildTableForUser(userId);
		
		assertEquals(expected, output);
	}
}
