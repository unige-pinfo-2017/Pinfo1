package ch.unige.pinfo.device.service;

import java.util.List;
import java.util.Set;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.user.dom.User;

public interface DeviceManager {
	
	String getDeviceDataLive(String deviceId, String sensorName);
	
	/**
	 * <b>getAvgSensorLiveForUser</b>
	 * <p>
	 * {@code double getAvgSensorLiveForUser(Long userId, String sensorName)}
	 * <p>
	 * 
	 * Calculate the average of live consummation of a {@code sensorName} for a user specifying his {@code userId}.
	 * 
	 * @param userId - The id of the User
	 * @param sensorName - The name of the sensor we want 
	 * @return
	 * The live average.
	 */
	double getAvgSensorLiveForUser(Long userId, String sensorName);
	
	/**
	 * <b>getSumSensorLiveForUser</b>
	 * <p>
	 * {@code double getSumSensorLiveForUser(Long userId, String sensorName)}
	 * <p>
	 * 
	 * Calculate the sum of live consummation of a {@code sensorName} for a user specifying his {@code userId}.
	 * 
	 * @param userId - The id of the User
	 * @param sensorName - The name of the sensor we want 
	 * @return
	 * The live sum.
	 */
	double getSumSensorLiveForUser(Long userId, String sensorName);
	
	/**
	 * <b>getSensorsForTypeDevice</b>
	 * <p>
	 * {@code Set<Sensor> getSensorsForTypeDevice(String name)}
	 * <p>
	 * 
	 * Get all sensors for a device. 
	 * 
	 * @param name - type of a Device
	 * @return
	 * A set of sensors.
	 */
	Set<Sensor> getSensorsForTypeDevice(String name);
	
	/**
	 * <b>getAllDevicesForUserByTypeDevice</b>
	 * <p>
	 * {@code List<Device> getAllDevicesForUserByTypeDevice(Long userId, String typeDevice)}
	 * <p>
	 * 
	 * Get all the devices by type device for a user.
	 * 
	 * @param userId - The id of the User
	 * @param typeDevice - The type of the device
	 * @return
	 * A list of devices.
	 */
	List<Device> getAllDevicesForUserByTypeDevice(Long userId, String typeDevice);
	
	/**
	 * <b>getAllDevicesForUserBySensorName</b>
	 * <p>
	 * {@code List<Device> getAllDevicesForUserBySensorName(Long userId, String sensorName)}
	 * <p>
	 * 
	 * Get all devices by sensor name for a user.
	 * 
	 * @param userId - The id of the user
	 * @param sensorName - The sensor name.
	 * @return
	 * A list of devices.
	 */
	List<Device> getAllDevicesForUserBySensorName(Long userId, String sensorName);
	
	/**
	 * <b>getSensorFromSensorName</b>
	 * <p>
	 * {@code Sensor getSensorFromSensorName(String sensorName)}
	 * <p>
	 * 
	 * Get a sensor specifying his name.
	 * 
	 * @param sensorName - The name of the sensor.
	 * @return
	 * A sensor.
	 */
	Sensor getSensorFromSensorName(String sensorName);
	
	/**
	 * <b>getDeviceBySensorName</b>
	 * <p>
	 * {@code Device getDeviceBySensorName(String sensorName)}
	 * <p>
	 * 
	 * Get a device specifying his sensor. 
	 * 
	 * @param sensorName - The name of the sensor.
	 * @return
	 * A device.
	 */
	Device getDeviceBySensorName(String sensorName);
	
	/**
	 * <b>getAllDevicesForUsersBySensorName</b>
	 * <p>
	 * {@code List<Device> getAllDevicesForUsersBySensorName(List<User> users, String sensorName)}
	 * <p>
	 * 
	 * Get all devices by sensor name for all users.
	 * 
	 * @param users - A list of users.
	 * @param sensorName - The name of the sensor.
	 * @return
	 * A list of Devices.
	 */
	List<Device> getAllDevicesForUsersBySensorName(List<User> users, String sensorName);
	
	/**
	 * <b>getDeviceDataLiveColor</b>
	 * <p>
	 * {@code  List<String> getDeviceDataLiveColor(String deviceType, String deviceId)}
	 * <p>
	 * 
	 * Get the live Color display by a type device specifying his id
	 * 
	 * @param deviceType - The type of the device
 	 * @param deviceId - The Id of the device.
	 * @return
	 */
	List<String> getDeviceDataLiveColor(String deviceType, String deviceId);
	
	/**
	 * <b>getDeviceTypeNameFromDeviceId</b>
	 * <p>
	 * {@code String getDeviceTypeNameFromDeviceId(String deviceId)}
	 * <p>
	 * 
	 * Get the name of a device specifying his Id.
	 * 
	 * @param deviceId - Id of the device
	 * @return
	 * Name of the device.
	 */
	String getDeviceTypeNameFromDeviceId(String deviceId);
	
	/**
	 * <b>changeDevice</b>
	 * <p>
	 * {@code String changeDevice(String deviceId, String resource, String state)}
	 * <p>
	 * 
	 * change the state of a Light's resource specifying his {@code deviceId}.  
	 * 
	 * @param deviceId - The id of the Light device
	 * @param resource - The resource of the Light. Can be Hue, Saturation, Kelvin or State
	 * @param state - The new state we want to set.
	 * @return
	 * The value of the new state in {@code String} format
	 */
	String changeDevice(String deviceId, String resource, String state);
} 
