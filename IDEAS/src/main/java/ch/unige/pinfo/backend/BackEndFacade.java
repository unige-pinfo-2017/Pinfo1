package ch.unige.pinfo.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.device.service.DeviceManager;
import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.overview.service.LiveDataService;
import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.UserService;

public class BackEndFacade {
	@Inject
	UserService userService;
	
	@Inject
	DeviceManager deviceManager;
	
	@Inject
	LiveDataService liveDataService;
	
	public void removePreference(Long userId, Long liveDataId) {
		userService.removePreference(userId, liveDataId);
	}
	
	public void addPreference(Long userId, Long liveDataId) {
		userService.addPreference(userId, liveDataId);
	}
	
	public Long getLiveDataIdFromSensorMeasureName(String measureName) {
		List<LiveData> liveDatas = getAllLiveDatas();
		LiveData liveData = new LiveData();
		for (int i=0; i<liveDatas.size(); i++) {
			if (liveDatas.get(i).getSensor().getMeasureName().equals(measureName)) {
				liveData = liveDatas.get(i);
				break;
			}
		}
		return liveData.getId();
	}
	
	public String getDeviceTypeNameFromDeviceId(String deviceId) {
		return deviceManager.getDeviceTypeNameFromDeviceId(deviceId);
	}
	
	public List<User> getUsersList(Long userId) {
		List<User> users; 
		String role = getUserRoleById(userId);
		if (role.equals("Manager")) {
			users = getUsersOfManager(userId);
		} else if (role.equals("SysAdmin")) {
			users = getUsersOfSysAdmin(userId);
		} else {
			users = new ArrayList<User>();
		}
		return users;
	}
	
	public List<String> getDeviceDataLiveColor(String deviceType, String deviceId) {
		return deviceManager.getDeviceDataLiveColor(deviceType, deviceId);
	}
	
	public Set<Sensor> getSensorsForTypeDevice(String deviceType) {
		return deviceManager.getSensorsForTypeDevice(deviceType);
	}
	
	public List<Device> getAllDevicesForUserByTypeDevice(Long userId, String deviceType) {
		return deviceManager.getAllDevicesForUserByTypeDevice(userId, deviceType);
	}
	
	public List<Device> getAllDevicesForUserBySensorName(Long userId, String sensorName) {
		return deviceManager.getAllDevicesForUserBySensorName(userId, sensorName);
	}
	
	public User getUserByUsername(String username) {
		return userService.getUserByUsername(username).get(0);
	}
	
	public String getUserRoleById(Long id) {
		return userService.getUserRoleById(id);
	}
	
	public List<User> getUsersOfManager(Long userId) {
		return userService.getUsersOfManager(userId);
	}
	
	public List<User> getUsersOfSysAdmin(Long userId) {
		return userService.getUsersOfSysAdmin(userId);
	}
	
	public String getDeviceDataLive(String deviceId, String sensorName) {
		return deviceManager.getDeviceDataLive(deviceId, sensorName);
	}

	public List<LiveData> getAllLiveDatas(){
		return liveDataService.getAllLiveData();
	}
	
	public Set<LiveData> getUserPreferences(Long userId) {
		return userService.getUserById(userId).getPreferences();
	}
	
	// TODO: Traiter le cas d'erreur
	public List<Double> getLiveDatas(Long userId) {
		String role = getUserRoleById(userId);
		if (role.equals("Manager")) {
			return getLiveDatasManager(userId);
		} else if (role.equals("SysAdmin")) {
			return getLiveDatasSysAdmin(userId);
		} else if (role.equals("Basic")) {
			return getLiveDatasBasic(userId);
		}
		return new ArrayList<Double>();
		
	}
	
	private List<Double> getLiveDatasBasic(Long userId) {
		List<Double> values = new ArrayList<Double>();
		Set<LiveData> liveDatas = userService.getUserById(userId).getPreferences();
		
		for (LiveData liveData: liveDatas) {	
			values.add(getLiveDataValueForUser(userId, liveData.getComputeType(), liveData.getSensor().getName()));
		}
		
		return values;
	}
	
	private List<Double> getLiveDatasManager(Long userId) {
		List<User> users = userService.getUsersOfManager(userId);
		List<Double> values = new ArrayList<Double>();
		Set<LiveData> liveDatas = userService.getUserById(userId).getPreferences();
		
		for (LiveData liveData: liveDatas) {	
			values.add(getLiveDataValueForUsers(users, liveData.getComputeType(), liveData.getSensor().getName()));
		}
		
		return values;
	}
	
	private List<Double> getLiveDatasSysAdmin(Long userId) {
		List<User> users = userService.getUsersOfSysAdmin(userId);
		List<Double> values = new ArrayList<Double>();
		Set<LiveData> liveDatas = userService.getUserById(userId).getPreferences();
		
		for (LiveData liveData: liveDatas) {	
			values.add(getLiveDataValueForUsers(users, liveData.getComputeType(), liveData.getSensor().getName()));
		}
		
		return values;
	}
	
	// TODO: Traiter le cas d'erreur
	public double getLiveDataValueForUser(Long userId, String computeType, String sensorName) {
		if (computeType.equals("Sum")) {
			return getSumSensorLiveForUser(userId, sensorName);
		} else if (computeType.equals("Average")) {
			return getAvgSensorLiveForUser(userId, sensorName);
		}
		return 0;
	}
	
	public double getLiveDataValueForUsers(List<User> users, String computeType, String sensorName) {
		if (computeType.equals("Sum")) {
			return getSumSensorLiveForUsers(users, sensorName);
		} else if (computeType.equals("Average")) {
			return getAvgSensorLiveForUsers(users, sensorName);
		}
		return 0;
	}
	
	private double getAvgSensorLiveForUser(Long userId, String sensorName) {
		return deviceManager.getAvgSensorLiveForUser(userId, sensorName);
	}
	
	private double getAvgSensorLiveForUsers(List<User> users, String sensorName) {
		if (users.size() == 0) {
			return 0;
		}
		return getSumSensorLiveForUsers(users, sensorName)/users.size();
	}
	
	private double getSumSensorLiveForUser(Long userId, String sensorName) {
		return deviceManager.getSumSensorLiveForUser(userId, sensorName);
	}
	
	private double getSumSensorLiveForUsers(List<User> users, String sensorName) {
		double res = 0;
		for (User user: users) {
			res += getSumSensorLiveForUser(user.getId(), sensorName);
		}
		return res;
	}

	public Sensor getSensorFromSensorName(String sensorName) {
		return deviceManager.getSensorFromSensorName(sensorName);
	}
}
