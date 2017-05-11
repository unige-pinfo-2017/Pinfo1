package ch.unige.pinfo.device.service;

import java.util.List;
import java.util.Set;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;

public interface DeviceManager {
	double getDeviceData(Long deviceId, String sensorName, String from, String to);
	double getSumSensorForUser(Long userId, String sensorName, String from, String to);
	Set<Sensor> getSensorsForTypeDevice(String name);
	List<Device> getAllDevicesForUserByTypeDevice(Long userId, String typeDevice);
} 
