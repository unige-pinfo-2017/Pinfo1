package ch.unige.pinfo.device.service;

import java.util.List;

import javax.ejb.Local;

import ch.unige.pinfo.device.dom.Device;

@Local
public interface DeviceService {
	
	/**
	 * <b>addDevice</b>
	 * <p>
	 * {@code void addDevice(Device device)}
	 * <p>
	 * 
	 * Add device in database.
	 * 
	 * @param device - The device to add
	 */
	void addDevice(Device device);
	
	/**
	 * <b>deleteDevice</b>
	 * <p>
	 * {@code void deleteDevice(Long id)}
	 * 
	 * Remove device from database. 
	 * 
	 * @param id - The id of the device to remove
	 */
	void deleteDevice(Long id);
	
	/**
	 * <b>getDeviceById</b>
	 * <p>
	 * {@code Device getDeviceById(Long id)}
	 * 
	 * Get the device by its id.
	 * 
	 * @param id - The Id of the device
	 * @return
	 * A device.
	 */
	Device getDeviceById(Long id);
	
	/**
	 * <b>getAllDevices</b>
	 * <p>
	 * {@code List<Device> getAllDevices()}
	 * 
	 * Get all devices.
	 * 
	 * @return
	 * A list of all devices.
	 */
	List<Device> getAllDevices();
	
	/**
	 * <b>getDevicesBySensorForUser</b>
	 * <p>
	 * {@code List<Device> getDevicesBySensorForUser(Long userId, String sensorName)}
	 * 
	 * Get devices by sensor for a user specified by its {@code userId} and the {@code sensorName}. 
	 * 
	 * @param userId - The user id 
	 * @param sensorName - The sensor name
	 * @return
	 * A list of devices.
	 */
	List<Device> getDevicesBySensorForUser(Long userId, String sensorName);
	
	/**
	 * <b>getDevicesByTypeDeviceForUser</b>
	 * <p>
	 * {@code List<Device> getDevicesByTypeDeviceForUser(Long userId, String typeDeviceName)}
	 * <p>
	 * 
	 * Get device by type of device for a user specified by its {@code userId}.
	 * 
	 * @param userId - The id of the user.
	 * @param typeDeviceName - The type of the device.
	 * @return
	 * A list of devices.
	 */
	List<Device> getDevicesByTypeDeviceForUser(Long userId, String typeDeviceName);
	
	/**
	 * <b>getDeviceByDeviceId</b>
	 * <p>
	 * {@code Device getDeviceByDeviceId(String deviceId)}
	 * 
	 * Get a device specified by its {@code device Id}.
	 * 
	 * @param deviceId - The id of the device.
	 * @return
	 * A device.
	 */
	Device getDeviceByDeviceId(String deviceId);
}
