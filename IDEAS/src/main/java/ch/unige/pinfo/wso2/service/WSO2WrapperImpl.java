package ch.unige.pinfo.wso2.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.service.DeviceManager;
import ch.unige.pinfo.wso2.rest.WSO2ClientRest;

@Stateless
public class WSO2WrapperImpl implements WSO2Wrapper {
	
	@Inject
	WSO2ClientRest wcr;
	
	@Inject
	Instance<DeviceManager> dm;
	
	@Inject
	ChartDataService chartDataService;
	
	private final int decimalNumber = 2;
	
	@Override
	public String getValueLive(String deviceType, String deviceId,  String SensorType){
		// Retourne la dernière valeur live
		// A remplacer quand la vraie methode sera donnee
		
		/*if(deviceType.equals("Light") && SensorType.equals("colorSensor")){
			JsonArray states = polling(deviceType, deviceId, SensorType);
			JsonArray statesB = polling(deviceType, deviceId, "brightnessSensor");
			
			//Recupere la couleur HSB la converti en RGB et la renvoit en String:
			//JsonArray states = wcr.getStates(deviceType, deviceId, SensorType, "0", "0");
			//JsonArray statesB = wcr.getStates(deviceType, deviceId, "brightnessSensor", "0", "0");
			
			JsonObject joTo = (JsonObject) states.getJsonObject(states.size()-1).get("values");
			JsonObject joToB = (JsonObject) statesB.getJsonObject(statesB.size()-1).get("values");
			
			JsonObject valueTo = (JsonObject) joTo.get(SensorType);
			
			String hueString = valueTo.get("hue").toString();
			String satString = valueTo.get("saturation").toString();
			String briString = joToB.get("brightnessSensor").toString();
			
			String rgb = getHSBColor(hueString, satString, briString);
			
			return rgb;
		}
		else {*/
			JsonArray states = polling(deviceType, deviceId, SensorType);
			//JsonArray states = wcr.getStates(deviceType, deviceId, SensorType, "0", "0");
			
			JsonObject joTo = (JsonObject) states.getJsonObject(states.size()-1).get("values");
			
			
			String valueTo = joTo.get(SensorType).toString();
			return Double.toString(roundDecimal(Double.parseDouble(valueTo), decimalNumber)); // - Double.parseDouble(valueFrom);
		//}
	}
	
	/*@Override
	public List<String> getValueLive2(String deviceType, String deviceId,  String SensorType){
		// Retourne la dernière valeur live
		// A remplacer quand la vraie methode sera donnee
		List<String> value = new ArrayList<>();
		
		if(deviceType.equals("Light") && SensorType.equals("colorSensor")){
			JsonArray states = polling(deviceType, deviceId, SensorType);
			
			//Recupere les valeurs se truovant dans colorSensor:
			//JsonArray states = wcr.getStates(deviceType, deviceId, SensorType, "0", "0");
		
			JsonObject joTo = (JsonObject) states.getJsonObject(states.size()-1).get("values");
			
			JsonObject valueTo = (JsonObject) joTo.get(SensorType);
			
			String hueString = valueTo.get("hue").toString();
			String satString = valueTo.get("saturation").toString();
			String kelString = valueTo.get("kelvin").toString();
			
			value.add(hueString);
			value.add(satString);
			value.add(kelString);
			
			return value;
		}
		else{
			JsonArray states = polling(deviceType, deviceId, SensorType);
			
			//JsonArray states = wcr.getStates(deviceType, deviceId, SensorType, "0", "0");
			
			JsonObject joTo = (JsonObject) states.getJsonObject(states.size()-1).get("values");
			String valueTo = joTo.get(SensorType).toString();
			
			value.add(valueTo);
			
			return value; // - Double.parseDouble(valueFrom);
		}
	}*/

	@Override
	public String[] getValue(String deviceType, String deviceId, String SensorType, String From, String To) throws ParseException {
		// Retourne les valeurs entre deux intervalles
		// Mock pour l'instant
		
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date fromDate = format.parse(From);
		Date toDate = format.parse(To);
		
		//date to unix timeStamp to String:
		String fromString = String.valueOf(fromDate.getTime());
		String toString = String.valueOf(toDate.getTime());

		JsonArray states = wcr.getStates(deviceType, deviceId, SensorType, fromString, toString);
		String[] readings = new String[states.size()];
		for (int i=0; i<states.size(); i++) {
			readings[i] = ((JsonObject) states.getJsonObject(i).get("values")).get(SensorType).toString();
		}
		
		return readings;
	}
	
	@Override
	public String changeState(String deviceId, String state) {
		String deviceTypeName = dm.get().getDeviceTypeNameFromDeviceId(deviceId);
		return wcr.postStatus(deviceTypeName, deviceId, "status", state.toUpperCase());
	}
	
	@Override
	public String changeHue(String deviceId, String state) {
		String deviceTypeName = dm.get().getDeviceTypeNameFromDeviceId(deviceId);
		return wcr.postStatus(deviceTypeName, deviceId, "status", state.toUpperCase());
	}
	
	@Override
	public String changeSaturation(String deviceId, String state) {
		String deviceTypeName = dm.get().getDeviceTypeNameFromDeviceId(deviceId);
		return wcr.postStatus(deviceTypeName, deviceId, "status", state.toUpperCase());
	}
	
	@Override
	public String changeKelvin(String deviceId, String state) {
		String deviceTypeName = dm.get().getDeviceTypeNameFromDeviceId(deviceId);
		return wcr.postStatus(deviceTypeName, deviceId, "status", state.toUpperCase());
	}
	
	@Override
	public String changePowerSocketStatus(String deviceId, String state){
		Long id = Long.parseLong(deviceId);
		String device = "PowerSocket";
		String response = "Error"; 

		if (hasSensor(id, device)){
			response = wcr.postStatus(device, deviceId, "status", state.toUpperCase());
		}
		return response;
	}
	
	@Override
	public String changeLightStatus(String deviceId, String state){
		Long id = Long.parseLong(deviceId);
		String device = "Light";
		String response = "Error"; 

		if (hasSensor(id, device)){
			response = wcr.postStatus(device, deviceId, "status", state.toUpperCase());
		}
		return response;
	}
	
	@Override
	public String changeLightBrightness(String deviceId, String state){
		Long id = Long.parseLong(deviceId);
		String device = "Light";
		String response = "Error"; 
		
		if (hasSensor(id, device)){
			response = wcr.postStatus(device, deviceId, "brightness", state);
		}
		return response;
	}
	
	@Override
	public String changeLightSaturation(String deviceId, String state){
		Long id = Long.parseLong(deviceId);
		String device = "Light";
		String response = "Error"; 

		if(hasSensor(id, device)){
			response = wcr.postStatus(device, deviceId, "saturation", state);
		}
		return response;
	}
	
	@Override
	public String changeLightHue(String deviceId, String state){
		Long id = Long.parseLong(deviceId);
		String device = "Light";
		String response = "Error"; 

		if(hasSensor(id, device)){
			response = wcr.postStatus(device, deviceId, "hue", state);
		}
		return response;
	}
	
	@Override
	public String changeLightKelvin(String deviceId, String state){
		Long id = Long.parseLong(deviceId);
		String device = "Light";
		String response = "Error"; 

		if(hasSensor(id, device)){
			response = wcr.postStatus(device, deviceId, "kelvin", state);
		}
		return response;
	}
	
	@Override
	public JsonArray polling(String deviceType, String deviceId,  String SensorType){
		
		Instant now = Instant.now();
		Duration duration = Duration.ofMinutes(5);
		Instant before =  now.minus(duration); 
		
		JsonArray states = null;
		
		int stop = 0;
		while(stop < 10000){
			
			String nowString = String.valueOf(now.getEpochSecond());
			String beforeString = String.valueOf(before.getEpochSecond());
			
			states = wcr.getStates(deviceType, deviceId, SensorType, beforeString, nowString);
			
			if (states == null){
				before = before.minus(duration);
				stop += 1;
			}
			else{
				stop = 10001;
			}
		}
		return states;
	}
	
	@Override
	public List<String> getValueLiveColor(String deviceType, String deviceId) {
		List<String> values = new ArrayList<String>();
		JsonArray states = polling(deviceType, deviceId, "colorSensor");
		JsonObject live = (JsonObject) states.getJsonObject(states.size()-1).get("values");
		live = (JsonObject) live.get("colorSensor");
		values.add(live.get("hue").toString());
		values.add(Double.toString(roundDecimal(Double.parseDouble(live.get("saturation").toString()), decimalNumber)));
		values.add(live.get("kelvin").toString());
		return values;
	}
	
	
	/**
	 * <b>hasSensor</b>
	 * <p>
	 * {@code private private boolean hasSensor(Long deviceId, String sensorName)}
	 * <p>
	 * 
	 * Check if a sensor with {@code deviceId} have a {@code sensorName}.
	 * 
	 * @param deviceId - The Id of the device
	 * @param sensorName - The sensor type name.
	 * @return
	 * {@code true} if correspond, {@code false} otherwise.
	 */
	public boolean hasSensor(Long deviceId, String sensorName){
		Device device = dm.get().getDeviceBySensorName(sensorName);		
		Long id = device.getId();
		
		if(id == deviceId){
			return true;
		}
		else{
			return false;
		}
	}
	
	public double roundDecimal(double value, int decimalNumber) {
		double powerOfTen = Math.pow(10, decimalNumber);
		return Math.round(value*powerOfTen)/powerOfTen;
	}
	
	@Override
	public List<Double> getLastDayData(String deviceType, String deviceId, String sensorType) {		
		Instant now = Instant.now();
		List<Instant> timePoints = new ArrayList<Instant>();
		for (int i=0; i<24; i++) {
			timePoints.add(now.minus(Duration.ofHours(i)));
		}
		List<Reading> readings = mockReadingsLastDay(now);
		Collections.reverse(readings); // On inverse l'ordre de 'readings' car la liste est du plus ancient au plus recent alors que timepoints est du plus recent au plus ancien
		readings = chartDataService.averageValuePerTimeSlot(readings, timePoints);
		Collections.reverse(readings); //On renvoit les donnees dans l'ordre old-new
		
		return getValuesOfReadings(readings);
	}
	
	@Override
	public List<Double> getLastWeekData(String deviceType, String deviceId, String sensorType) {
		Instant now = Instant.now();
		List<Instant> timePoints = new ArrayList<Instant>();
		for (int i=0; i<7; i++) {
			timePoints.add(now.minus(Duration.ofDays(i)));
		}
		List<Reading> readings = mockReadingsLastWeek(now);
		Collections.reverse(readings);
		readings = chartDataService.averageValuePerTimeSlot(readings, timePoints);
		Collections.reverse(readings); //On renvoit les donnees dans l'ordre old-new
		
		return getValuesOfReadings(readings);
	}
	
	@Override
	public List<Double> getLastMonthData(String deviceType, String deviceId, String sensorType) {
		Instant now = Instant.now();
		List<Instant> timePoints = new ArrayList<Instant>();
		for (int i=0; i<30; i++) {
			timePoints.add(now.minus(Duration.ofDays(i)));
		}
		List<Reading> readings = mockReadingsLastMonth(now);
		Collections.reverse(readings);
		readings = chartDataService.averageValuePerTimeSlot(readings, timePoints);
		Collections.reverse(readings);
		
		return getValuesOfReadings(readings);
	}
	
	public List<Double> getValuesOfReadings(List<Reading> readings) {
		List<Double> values = new ArrayList<Double>();
		for (Reading reading: readings) {
			values.add(reading.getValue());
		}
		return values;
	}
	
	public List<Reading> getReadings(String deviceType, String deviceId, String SensorType, String From, String To) {
		// Retourne les readings du serveur entre from et to
		List<Reading> readings = new ArrayList<Reading>();
		JsonArray states = wcr.getStates(deviceType, deviceId, SensorType, From, To);
		for (int i=0; i<states.size(); i++) {
			JsonObject jsonObject = (JsonObject) states.getJsonObject(i).get("values");
			readings.add(new Reading(jsonObject.get("meta_time").toString(), jsonObject.get(SensorType).toString()));
		}
		
		return readings;
	}
	
	
	public List<Reading> mockReadingsLastDay(Instant instant) {
		// Create mock Reading list with old to new order
		// 10 9 8 7 6 5 4 3 2 1 0 0 0 ...
		List<Reading> mock = new ArrayList<Reading>();
		for (int i=10; i>=0; i--){
			Instant inst = instant.minus(Duration.ofHours(i)); //.minus(Duration.ofMinutes(10)));
			mock.add(new Reading(inst, (double) i));
			if (i == 5) {
				Instant inst2 = instant.minus(Duration.ofHours(i));
				inst2 = inst2.plus(Duration.ofMinutes(10));
				mock.add(new Reading(inst2, 10d));
			}
		}
		return mock;
	}
	
	public List<Reading> mockReadingsLastWeek(Instant instant) {
		List<Reading> mock = new ArrayList<Reading>();
		for (int i=6; i>=0; i--) {
			Instant inst = instant.minus(Duration.ofDays(i));
			mock.add(new Reading(inst, (double) i));
		}
		return mock;
	}
	
	private List<Reading> mockReadingsLastMonth(Instant instant) {
		List<Reading> mock = new ArrayList<Reading>();
		for (int i=29; i>=0; i--) {
			Instant inst = instant.minus(Duration.ofDays(i));
			mock.add(new Reading(inst, (double) i));
		}
		return mock;
	}

}