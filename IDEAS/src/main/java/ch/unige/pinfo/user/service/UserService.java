package ch.unige.pinfo.user.service;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.user.dom.User;

@Local
public interface UserService{
	
	void addUser(User user);
	void deleteUser(Long Id);
	User getUserById(Long Id);
	List<User> getUserByUsername(String username);
	List<User> getAllUsers();
	double getSumBySensor(Long userId, String sensorName, String from, String to);
	Set<Sensor> getSensorsForTypeDevice(String name);
	List<Device> getAllDevicesForUserByTypeDevice(Long userId, String typeDevice);
	List<Device> getAllDevicesForUserBySensorName(Long userId, String sensorName);
	double getDeviceDataLive(Long deviceId, String sensorName);
	Sensor getSensorFromSensorName(String sensorName);
}
