package ch.unige.pinfo.device.service;

import java.util.List;
import java.util.Set;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;

public interface DeviceManager {
	String getDeviceDataLive(String deviceId, String sensorName);
	double getAvgSensorLiveForUser(Long userId, String sensorName);
	double getSumSensorLiveForUser(Long userId, String sensorName);
	Set<Sensor> getSensorsForTypeDevice(String name);
	List<Device> getAllDevicesForUserByTypeDevice(Long userId, String typeDevice);
	List<Device> getAllDevicesForUserBySensorName(Long userId, String sensorName);
	Sensor getSensorFromSensorName(String sensorName);
	Device getDeviceBySensorName(String sensorName);
} 
