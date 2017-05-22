package ch.unige.pinfo.device.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.device.dom.TypeDevice;
import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.UserService;
import ch.unige.pinfo.wso2.service.WSO2Wrapper;

public class DeviceManagerImpl implements DeviceManager {	
	@Inject 
	private SensorService sensorService;
	
	@Inject
	private DeviceService deviceService;
	
	@Inject
	private TypeDeviceService typeDeviceService;
	
	@Inject
	private WSO2Wrapper wso2Wrapper;
	
	@Inject
	private UserService userService;
	
	@Override
	public double getAvgSensorLiveForUser(Long userId, String sensorName) {
		double sum = 0;
		
		List<Device> ld = deviceService.getDevicesBySensorForUser(userId, sensorName);
		
		if (ld.size() == 0) {
			return 0;
		}
		
		for (Device device: ld){
			sum += Double.parseDouble(wso2Wrapper.getValueLive(device.getType().getName(), device.getDeviceId(), sensorName));
		}
		
		return sum/ld.size();
	}
	
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
		List<Device> devices = deviceService.getDevicesByTypeDeviceForUser(userId, typeDevice);
		String role = userService.getUserRoleById(userId);
		if (role.equals("Manager")) {
			devices.addAll(getAllDevicesForUsersByTypeDevice(userService.getUsersOfManager(userId), typeDevice));
		} else if (role.equals("SysAdmin")) {
			devices.addAll(getAllDevicesForUsersByTypeDevice(userService.getUsersOfSysAdmin(userId), typeDevice));
		}
		return devices;
	}
	
	public List<Device> getAllDevicesForUsersByTypeDevice(List<User> users, String typeDevice) {
		List<Device> devices = new ArrayList<Device>();
		for (User user: users) {
			devices.addAll(deviceService.getDevicesByTypeDeviceForUser(user.getId(), typeDevice));
		}
		return devices;
	}

	@Override
	public List<Device> getAllDevicesForUserBySensorName(Long userId, String sensorName){
		List<Device> devices = deviceService.getDevicesBySensorForUser(userId, sensorName);
		String role = userService.getUserRoleById(userId);
		if (role.equals("Manager")) {
			devices.addAll(getAllDevicesForUsersBySensorName(userService.getUsersOfManager(userId), sensorName));
		} else if (role.equals("SysAdmin")) {
			devices.addAll(getAllDevicesForUsersBySensorName(userService.getUsersOfSysAdmin(userId), sensorName));
		}
		return devices;
	}
	
	@Override
	public List<Device> getAllDevicesForUsersBySensorName(List<User> users, String sensorName) {
		List<Device> devices = new ArrayList<Device>();
		for (User user: users) {
			devices.addAll(deviceService.getDevicesBySensorForUser(user.getId(), sensorName));
		}
		return devices;
	}
	
	@Override
	public String getDeviceDataLive(String deviceId, String sensorName) {
		Device device = deviceService.getDeviceByDeviceId(deviceId);
		return wso2Wrapper.getValueLive(device.getType().getName(), device.getDeviceId(), sensorName);
	}

	@Override
	public Sensor getSensorFromSensorName(String sensorName) {
		return sensorService.getSensorByName(sensorName);
	}
	
	@Override
	public Device getDeviceBySensorName(String sensorName){
		Sensor sensor = sensorService.getSensorByName(sensorName);
		Device device = new Device();
		
		for (TypeDevice type : sensor.getTypeDevices()){
			if(device.getType().getId() == type.getId()){
				return device;
			}
		}
		return device;
	}
}
