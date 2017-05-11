package ch.unige.pinfo.device.service;

import java.util.Set;

import ch.unige.pinfo.device.dom.Sensor;

public interface DeviceManager {
	double getSumSensorForUser(Long userId, String sensorName, String from, String to);
	Set<Sensor> getSensorsForTypeDevice(String name);
}
