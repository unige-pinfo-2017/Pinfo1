package ch.unige.pinfo.wso2.service;

import java.text.ParseException;
import java.util.List;

import javax.ejb.Local;
import javax.json.JsonArray;

@Local
public interface WSO2Wrapper {
	
	//temporaire
	//List<String> getValueLive2(String deviceType, String deviceId,  String SensorType);
	
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
	 * <b>changePowerSocketStatus</b>
	 * <p>
	 * {@code String changePowerSocketStatus(String deviceId, String state)}
	 * <p>
	 * 
	 * Change the value of the Status of PowerSocket device on the server. 
	 * 
	 * @param deviceId - The Id of the device
	 * @param state - The state we want to change. Possible value: "ON" or "OFF"
	 * @return
	 * Response of the server.
	 */
	String changePowerSocketStatus(String deviceId, String state);
	
	/**
	 * <b>changeLightStatus</b>
	 * <p>
	 * {@code String changeLightStatus(String deviceId, String state)}
	 * <p>
	 * 
	 * Change the value of the Status of Light device on the server. 
	 * 
	 * @param deviceId - The Id of the device
	 * @param state - The state we want to change. Possible value: "ON" or "OFF"
	 * @return
	 * Response of the server.
	 */
	String changeLightStatus(String deviceId, String state);
	
	/**
	 * <b>changeLightBrightness</b>
	 * <p>
	 * {@code String changeLightBrightness(String deviceId, double state)}
	 * <p>
	 * 
	 * Change the value of the Brightness of Light device on the server. 
	 * 
	 * @param deviceId - The Id of the device
	 * @param state - The state we want to change. Value between [0..1] 
	 * @return
	 * Response of the server.
	 */
	String changeLightBrightness(String deviceId, String state);
	
	/**
	 * <b>changeLightSaturation</b>
	 * <p>
	 * {@code String changeLightSaturation(String deviceId, double state)}
	 * <p>
	 * 
	 * Change the value of the Saturation of Light device on the server. 
	 * 
	 * @param deviceId - The Id of the device
	 * @param state - The state we want to change. Value between [0..1] 
	 * @return
	 * Response of the server.
	 */
	String changeLightSaturation(String deviceId, String state);
	
	/**
	 * <b>changeLightHue</b>
	 * <p>
	 * {@code String changeLightHue(String deviceId, int state)}
	 * <p>
	 * 
	 * Change the value of the Hue of Light device on the server. 
	 * 
	 * @param deviceId - The Id of the device
	 * @param state - The state we want to change. Value between [0..360] 
	 * @return
	 * Response of the server.
	 */
	String changeLightHue(String deviceId, String state);
	
	/**
	 * <b>changeLightKelvin</b>
	 * <p>
	 * {@code String changeLightKelvin(String deviceId, int state)}
	 * <p>
	 * 
	 * Change the value of the temperature/Kelvin color of Light device on the server. 
	 * 
	 * @param deviceId - The Id of the device
	 * @param state - The state we want to change. Value between [5000..9999] 
	 * @return
	 * Response of the server.
	 */
	String changeLightKelvin(String deviceId, String state);

	List<String> getValueLiveColor(String deviceType, String deviceId);

	String changeState(String deviceId, String state);

	String changeHue(String deviceId, String state);

	String changeSaturation(String deviceId, String state);

	String changeKelvin(String deviceId, String state);

	List<Double> getLastDayData(String deviceType, String deviceId, String sensorType);
	
	List<Double> getLastWeekData(String deviceType, String deviceId, String sensorType);

	List<Double> getLastMonthData(String deviceType, String deviceId, String sensorType);

	List<Double> getDataForYear(String deviceId, String sensorType, int year);
}
