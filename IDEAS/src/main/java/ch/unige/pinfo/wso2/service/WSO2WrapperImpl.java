package ch.unige.pinfo.wso2.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import ch.unige.pinfo.device.service.DeviceManager;
import ch.unige.pinfo.wso2.rest.WSO2ClientRest;

public class WSO2WrapperImpl implements WSO2Wrapper {
	
	/**
	 *  The serial-id
	 */
	private static final long serialVersionUID = -3193538432088886844L;

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
			JsonArray states = polling(deviceType, deviceId, SensorType);
			
			JsonObject joTo = (JsonObject) states.getJsonObject(states.size()-1).get("values");
			
			
			String valueTo = joTo.get(SensorType).toString();
			return Double.toString(roundDecimal(Double.parseDouble(valueTo), decimalNumber));
	}

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
	public Response changeState(String deviceId, String state) {
		if (state.equals("1")) state = "ON";
		if (state.equals("0")) state = "OFF";
		String deviceTypeName = dm.get().getDeviceTypeNameFromDeviceId(deviceId);
		return wcr.postStatus(deviceTypeName, deviceId, "status", state.toUpperCase());
	}
	
	@Override
	public Response changeHue(String deviceId, String state) {
		String deviceTypeName = dm.get().getDeviceTypeNameFromDeviceId(deviceId);
		return wcr.postStatus(deviceTypeName, deviceId, "hue", state.toUpperCase());
	}
	
	@Override
	public Response changeSaturation(String deviceId, String state) {
		String deviceTypeName = dm.get().getDeviceTypeNameFromDeviceId(deviceId);
		return wcr.postStatus(deviceTypeName, deviceId, "saturation", state.toUpperCase());
	}
	
	@Override
	public Response changeKelvin(String deviceId, String state) {
		String deviceTypeName = dm.get().getDeviceTypeNameFromDeviceId(deviceId);
		return wcr.postStatus(deviceTypeName, deviceId, "kelvin", state.toUpperCase());
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
	 * <b>roundDecimal</b>
	 * <p>
	 * {@code public double roundDecimal(double value, int decimalNumber)}
	 * <p>
	 * 
	 * Round the given value to the specify decimal.
	 * 
	 * @param value - The value to round 
	 * @param decimalNumber - The decimal.
	 * @return
	 * The rounded value.
	 */
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
	
	
	@Override
	public List<Double> getDataForYear(String deviceId, String sensorType, int year) {
		Calendar cal = Calendar.getInstance();
		
		List<Instant> timePoints = new ArrayList<Instant>();
		
		for (int i=12; i>=0; i--) {
			if ( (i == 1) || (i == 3) || (i == 5) || (i == 7) ||  (i == 8) || (i == 10) || (i == 12)) {
				cal.set(year, i, 31);
			}  else if ( (i==4) || (i==6) || (i==9) || (i==11) ) {
				cal.set(year, i, 30);
			} else if (i==2) {
				cal.set(year, i, 28);
			} else if (i==0) {
				cal.set(year, 1, 1);
			}
			timePoints.add(cal.toInstant());
		}
		
		
		List<Reading> readings = mockReadingsYear(year);
		Collections.reverse(readings);
		readings = chartDataService.averageValuePerTimeSlot(readings, timePoints);
		Collections.reverse(readings);
		readings.remove(0);
		
		return getValuesOfReadings(readings);
	}

	/**
	 * <b>getValuesOfReadings</b>
	 * <p>
	 * {@code public List<Double> getValuesOfReadings(List<Reading> readings)}
	 * <p>
	 * 
	 * @param readings - The readings.
	 * @return
	 * A list of reading's values in {@code Double} format.
	 */
	public List<Double> getValuesOfReadings(List<Reading> readings) {
		List<Double> values = new ArrayList<Double>();
		for (Reading reading: readings) {
			values.add(reading.getValue());
		}
		return values;
	}
	
	public List<Reading> getReadings(String deviceType, String deviceId, String SensorType, String From, String To) {
		// Return readings between 'from' and 'to' from the server
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
		List<Reading> mock = new ArrayList<Reading>();
		Random r = new Random();
		for (int i=23; i>=0; i--){
			Instant inst = instant.minus(Duration.ofHours(i));
			mock.add(new Reading(inst, (double) r.nextInt(20)));
		}
		return mock;
	}
	
	public List<Reading> mockReadingsLastWeek(Instant instant) {
		List<Reading> mock = new ArrayList<Reading>();
		Random r = new Random();
		for (int i=6; i>=0; i--) {
			Instant inst = instant.minus(Duration.ofDays(i));
			mock.add(new Reading(inst, (double) r.nextInt(20)));
		}
		return mock;
	}
	
	private List<Reading> mockReadingsLastMonth(Instant instant) {
		List<Reading> mock = new ArrayList<Reading>();
		Random r = new Random();
		for (int i=29; i>=0; i--) {
			Instant inst = instant.minus(Duration.ofDays(i));
			mock.add(new Reading(inst, (double) r.nextInt(20)));
		}
		return mock;
	}
	
	private List<Reading> mockReadingsYear(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, 12, 15);
		Instant instant = cal.toInstant();
		List<Reading> mock = new ArrayList<Reading>();
		
		Random r = new Random();
		
		for (int i=11; i>=0; i--) {
			Instant inst = instant.minus(Duration.ofDays( ((long)31) * i));
			mock.add(new Reading(inst, (double) r.nextInt(20)));
		}
		return mock;
	}

}