package ch.unige.pinfo.device.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.UserService;
import ch.unige.pinfo.wso2.service.WSO2Wrapper;

public class DeviceManagerImpl implements DeviceManager {	
	/**
	 *  The serial-id
	 */
	private static final long serialVersionUID = -296921927597374900L;

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
	public Response changeDevice(String deviceId, String resource, String state) {
		if ("hue".equals(resource.toLowerCase())) {
			return wso2Wrapper.changeHue(deviceId, state);
		} else if ("saturation".equals(resource.toLowerCase())) {
			return wso2Wrapper.changeSaturation(deviceId, state);
		} else if ("kelvin".equals(resource.toLowerCase())) {
			return wso2Wrapper.changeKelvin(deviceId, state);
		} else if ("state".equals(resource.toLowerCase())) {
			return wso2Wrapper.changeState(deviceId, state);
		} 
		
		return Response.status(500).entity("Resource does not exist for this device.").build(); 
	}
	
	@Override
	public double getAvgSensorLiveForUser(Long userId, String sensorName) {
		double sum = 0;
		
		List<Device> ld = deviceService.getDevicesBySensorForUser(userId, sensorName);
		
		if (ld.isEmpty()) {
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
		if ("Manager".equals(role)) {
			devices.addAll(getAllDevicesForUsersByTypeDevice(userService.getUsersOfManager(userId), typeDevice));
		} else if ("SysAdmin".equals(role)) {
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
		if ("Manager".equals(role)) {
			devices.addAll(getAllDevicesForUsersBySensorName(userService.getUsersOfManager(userId), sensorName));
		} else if ("SysAdmin".equals(role)) {
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
	public List<String> getDeviceDataLiveColor(String deviceType, String deviceId) {
		return wso2Wrapper.getValueLiveColor(deviceType, deviceId);
	}

	@Override
	public String getDeviceTypeNameFromDeviceId(String deviceId) {
		return deviceService.getDeviceByDeviceId(deviceId).getType().getName();
	}
}
