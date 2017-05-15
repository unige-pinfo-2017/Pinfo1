package ch.unige.pinfo.device.service;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.user.service.UserService;
import ch.unige.pinfo.wso2.service.WSO2Wrapper;

public class DeviceManagerImpl implements DeviceManager {
	@Inject 
	private UserService userService;
	
	@Inject 
	private SensorService sensorService;
	
	@Inject
	private DeviceService deviceService;
	
	@Inject
	private TypeDeviceService typeDeviceService;
	
	@Inject
	private WSO2Wrapper wso2Wrapper;
	
	@Override
	public double getSumSensorForUser(Long userId, String sensorName, String from, String to) {
		double sum = 0;
		
		List<Device> ld = deviceService.getDevicesBySensorForUser(userId, sensorName);
		for (Device device: ld){
			sum += wso2Wrapper.getValueLive(device.getType().getName(), device.getDeviceId(), sensorName, from, to);
		}
		
		return sum;
	}

	@Override
	public Set<Sensor> getSensorsForTypeDevice(String name) {
		return typeDeviceService.getTypeDeviceByName(name).getSensors();
	}

	@Override
	public List<Device> getAllDevicesForUserByTypeDevice(Long userId, String typeDevice) {
		return deviceService.getDevicesByTypeDeviceForUser(userId, typeDevice);
	}

	@Override
	public double getDeviceData(Long deviceId, String sensorName, String from, String to) {
		Device device = deviceService.getDeviceById(deviceId);
		return wso2Wrapper.getValueLive(device.getType().getName(), device.getDeviceId(), sensorName, from, to);
	}
	
	

}
