package ch.unige.pinfo.device.service;

import java.util.List;

import javax.ejb.Local;

import ch.unige.pinfo.device.dom.Device;

@Local
public interface DeviceService {
	
	void addDevice(Device device);
	void deleteDevice(Long id);
	Device getDeviceById(Long id);
	List<Device> getAllDevices();
	List<Device> getDevicesBySensorForUser(Long userId, String sensorName);
	List<Device> getDevicesByTypeDeviceForUser(Long userId, String typeDeviceName);
}
