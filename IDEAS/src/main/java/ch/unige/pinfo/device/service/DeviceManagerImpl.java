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
	public double getSumSensorLiveForUser(Long userId, String sensorName) {
		double sum = 0;
		
		List<Device> ld = deviceService.getDevicesBySensorForUser(userId, sensorName);
		for (Device device: ld){
			sum += Double.parseDouble(wso2Wrapper.getValueLive(device.getType().getName(), device.getDeviceId(), sensorName));
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
	public List<Device> getAllDevicesForUserBySensorName(Long userId, String sensorName){
		return deviceService.getDevicesBySensorForUser(userId, sensorName);
	}
	
	@Override
	public double getDeviceDataLive(Long deviceId, String sensorName) {
		Device device = deviceService.getDeviceById(deviceId);
		return Double.parseDouble(wso2Wrapper.getValueLive(device.getType().getName(), device.getDeviceId(), sensorName));
	}

	@Override
	public Sensor getSensorFromSensorName(String sensorName) {
		return sensorService.getSensorByName(sensorName);
	}
}
