package ch.unige.pinfo.user.service;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.user.dom.User;

@Local
public interface UserService{
	
	void addUser(User user);
	void deleteUser(Long Id);
	User getUserById(Long Id);
	String getUserRoleById(Long id);
	List<User> getUserByUsername(String username);
	List<User> getAllUsers();
	double getSumSensorLiveForUser(Long userId, String sensorName);
	double getAvgSensorLiveForUser(Long userId, String sensorName);
	Set<Sensor> getSensorsForTypeDevice(String name);
	List<Device> getAllDevicesForUserByTypeDevice(Long userId, String typeDevice);
	List<Device> getAllDevicesForUserBySensorName(Long userId, String sensorName);
	String getDeviceDataLive(Long deviceId, String sensorName);
	Sensor getSensorFromSensorName(String sensorName);
	List<LiveData> getAllLiveData();
}
