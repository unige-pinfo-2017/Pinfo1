package ch.unige.pinfo.wso2.service;

import java.text.ParseException;
import java.util.List;

import javax.ejb.Local;
import javax.json.JsonArray;
import javax.ws.rs.core.Response;

@Local
public interface WSO2Wrapper {
	
	/**
	 * <b>polling</b>
	 * <p>
	 * {@code JsonArray polling(String deviceType, String deviceId,  String SensorType) throws ParseException}
	 * <p>
	 * 
	 * Poll the server until obtain the last state of a device's sensor. 
	 * If polling is to long, then stop. 
	 * 
	 * @param deviceType - The device's type
	 * @param deviceId - The device's Id
	 * @param SensorType - The sensor's type
	 * @return
	 * A {@code JsonArray} if last state is found, empty {@code JsonArray} otherwise..
	 * @throws ParseException
	 */
	JsonArray polling(String deviceType, String deviceId,  String SensorType) throws ParseException;
	
	/**
	 * <b>getValueLive</b>
	 * <p>
	 * {@code String getValueLive(String deviceType, String deviceId,  String SensorType)}
	 * <p>
	 * 
	 * Get the value of a {@code deviceType}'s {@code SensorType} in {@code JSON} and convert it into {@code String}   
	 * 
	 * @param deviceType - The type of device 
	 * @param deviceId - The Id of the device type
	 * @param SensorType - The sensor type corresponding to the device type
	 * @return
	 * The value of the sensor type in {@code String} format.
	 */
	String getValueLive(String deviceType, String deviceId,  String SensorType);
	
	/**
	 * <b>getValue</b>
	 * <p>
	 * {@code public String[] getValue(String deviceType, String deviceId, String SensorType, String From, String To)}
	 * <p>
	 * 
	 * Get the value of a {@code deviceType}'s {@code SensorType} in {@code JSON} from a period {@code from}
	 * <br> and from a period {@code to}. Convert both values it into {@code String}  
	 * 
	 * @param deviceType - The type of device
	 * @param deviceId - The Id of the device type
	 * @param SensorType - The sensor type corresponding to the device type
	 * @param From  
	 * @param To 
	 * @return
	 * The values of the sensor type in {@code String} format.
	 * @throws ParseException 
	 */
	String[] getValue(String deviceType, String deviceId,  String SensorType, String From, String To) throws ParseException;
	
	/**
	 * <b>getValueLiveColor</b>
	 * <p>
	 * {@code List<String> getValueLiveColor(String deviceType, String deviceId)}
	 * <p>
	 * 
	 * Get the live color display by a device.
	 * 
	 * @param deviceType - The device's type
	 * @param deviceId - The device's id
	 * @return
	 * A list of color values (hue, saturation, kelvin) in {@code String} format.
	 */
	List<String> getValueLiveColor(String deviceType, String deviceId);

	/**
	 * <b>changeState</b>
	 * <p>
	 * {@code Response changeState(String deviceId, String state)}
	 * <p>
	 * 
	 * Change the state of a device.
	 * 
	 * @param deviceId - The device's id
	 * @param state - The new state.
	 * @return
	 * A {@code Response} 200 if the state is changed, 500 otherwise.
	 */
	Response changeState(String deviceId, String state);

	/**
	 * <b>changeHue</b>
	 * <p>
	 * {@code Response changeHue(String deviceId, String state)}
	 * <p>
	 * 
	 * Change the hue value of a device.
	 * 
	 * @param deviceId - The device's id
	 * @param state - The new state/value of hue.
	 * @return
	 * A {@code Response} 200 if the state is changed, 500 otherwise.
	 */
	Response changeHue(String deviceId, String state);

	/**
	 * <b>changeSaturation</b>
	 * <p>
	 * {@code Response changeSaturation(String deviceId, String state)}
	 * <p>
	 * 
	 * Change the saturation value of a device.
	 * 
	 * @param deviceId - The device's id
	 * @param state - The new state/value of saturation.
	 * @return
	 * A {@code Response} 200 if the state is changed, 500 otherwise.
	 */
	Response changeSaturation(String deviceId, String state);

	/**
	 * <b>changeKelvin</b>
	 * <p>
	 * {@code Response changeKelvin(String deviceId, String state)}
	 * <p>
	 * 
	 * Change the kelvin value of a device.
	 * 
	 * @param deviceId - The device's id
	 * @param state - The new state/value of kelvin.
	 * @return
	 * A {@code Response} 200 if the state is changed, 500 otherwise.
	 */
	Response changeKelvin(String deviceId, String state);

	/**
	 * <b>getLastDayData</b>
	 * <p>
	 * {@code List<Double> getLastDayData(String deviceType, String deviceId, String sensorType)}
	 * <p>
	 * 
	 * Get the data of a device's sensor for the last day.
	 * 
	 * @param deviceType - The devie's type
	 * @param deviceId - The device's id
	 * @param sensorType - The sensor's type.
	 * @return
	 * A list of values of the last day in {@code Double} format.
	 */
	List<Double> getLastDayData(String deviceType, String deviceId, String sensorType);
	
	/**
	 * <b>getLastWeekData</b>
	 * <p>
	 * {@code List<Double> getLastWeekData(String deviceType, String deviceId, String sensorType)}
	 * <p>
	 * 
	 * Get the data of a device's sensor for the last week.
	 * 
	 * @param deviceType - The devie's type
	 * @param deviceId - The device's id
	 * @param sensorType - The sensor's type.
	 * @return
	 * A list of values of the last week in {@code Double} format.
	 */
	List<Double> getLastWeekData(String deviceType, String deviceId, String sensorType);

	/**
	 * <b>getLastMonthData</b>
	 * <p>
	 * {@code List<Double> getLastMonthData(String deviceType, String deviceId, String sensorType)}
	 * <p>
	 * 
	 * Get the data of a device's sensor for the last month.
	 * 
	 * @param deviceType - The devie's type
	 * @param deviceId - The device's id
	 * @param sensorType - The sensor's type.
	 * @return
	 * A list of values of the last month in {@code Double} format.
	 */
	List<Double> getLastMonthData(String deviceType, String deviceId, String sensorType);

	/**
	 * <b>getDataForYear</b>
	 * <p>
	 * {@code List<Double> getDataForYear(String deviceId, String sensorType, int year)}
	 * <p>
	 * 
	 * Get the data of a device's sensor for a year.
	 * 
	 * @param deviceId - The device's id 
	 * @param sensorType - The sensor's type
	 * @param year - The specify year.
	 * @return
	 * A list of values of the year in {@code Double} format.
	 */
	List<Double> getDataForYear(String deviceId, String sensorType, int year);
}
