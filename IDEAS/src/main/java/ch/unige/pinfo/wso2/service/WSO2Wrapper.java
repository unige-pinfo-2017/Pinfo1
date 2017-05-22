package ch.unige.pinfo.wso2.service;

import java.util.List;

public interface WSO2Wrapper {
	
	//temporaire
	List<String> getValueLive2(String deviceType, String deviceId,  String SensorType);
	
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
	 */
	String[] getValue(String deviceType, String deviceId,  String SensorType, String From, String To);
	
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
	String changeLightBrightness(String deviceId, double state);
	
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
	String changeLightSaturation(String deviceId, double state);
	
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
	String changeLightHue(String deviceId, int state);
	
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
	String changeLightKelvin(String deviceId, int state);
}
