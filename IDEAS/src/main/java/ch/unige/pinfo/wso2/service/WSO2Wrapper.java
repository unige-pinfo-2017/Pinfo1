package ch.unige.pinfo.wso2.service;

public interface WSO2Wrapper {
	
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
}
