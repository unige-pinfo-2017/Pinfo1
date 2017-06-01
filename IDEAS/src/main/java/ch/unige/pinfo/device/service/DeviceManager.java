package ch.unige.pinfo.device.service;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ws.rs.core.Response;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.user.dom.User;

@Local
public interface DeviceManager {
	
	/**
     * <b>getDeviceDataLive</b>
     * <p>
     * {@code String getDeviceDataLive(String deviceId, String sensorName)}
     * <p>
     * 
     * Get the live data of a device specified by its {@code deviceId} and the sensor we want the data.
     * 
     * @param deviceId - The id of the device
     * @param sensorName - The sensor name.
     * @return
     * The data in {@code String} format.
     */
	String getDeviceDataLive(String deviceId, String sensorName);
	
	/**
	 * <b>getAvgSensorLiveForUser</b>
	 * <p>
	 * {@code double getAvgSensorLiveForUser(Long userId, String sensorName)}
	 * <p>
	 * 
	 * Compute the average live value of a sensor for a user specified by its {@code userId}.
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
	 * Compute the sum of live value of a sensor for a user specified by its {@code userId}.
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
	 * Get all sensors for a type of device. 
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
	 * Get a sensor specified by its {@code sensorName}.
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
	 * Get a device specified by its {@code sensorName}. 
	 * 
	 * @param sensorName - The name of the sensor.
	 * @return
	 * A device.
	 */
//	Device getDeviceBySensorName(String sensorName);
	
	/**
	 * <b>getAllDevicesForUsersBySensorName</b>
	 * <p>
	 * {@code List<Device> getAllDevicesForUsersBySensorName(List<User> users, String sensorName)}
	 * <p>
	 * 
	 * Get all devices by sensor name for a list of users.
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
	 * Get the live color value for a device specified by its {@code deviceId} and {@code deviceType}.
	 * 
	 * @param deviceType - The type of the device
 	 * @param deviceId - The Id of the device.
	 * @return
	 * list containing hue, saturation and kelvin data in {@code String}.
	 */
	List<String> getDeviceDataLiveColor(String deviceType, String deviceId);
	
	/**
	 * <b>getDeviceTypeNameFromDeviceId</b>
	 * <p>
	 * {@code String getDeviceTypeNameFromDeviceId(String deviceId)}
	 * <p>
	 * 
	 * Get the name of a device specified by its {@code deviceId}.
	 * 
	 * @param deviceId - Id of the device
	 * @return
	 * Name of the device.
	 */
	String getDeviceTypeNameFromDeviceId(String deviceId);
	
	/**
	 * <b>changeDevice</b>
	 * <p>
	 * {@code Response changeDevice(String deviceId, String resource, String state)}
	 * <p>
	 * 
	 * Change the state of a device resource specified its {@code deviceId}.  
	 * 
	 * @param deviceId - The id of the device
	 * @param resource - The resource of the device
	 * @param state - The new state we want to set.
	 * @return
	 * a {@code Response} : 200 if state is changed, 500 otherwise.
	 */
	Response changeDevice(String deviceId, String resource, String state);
} 
