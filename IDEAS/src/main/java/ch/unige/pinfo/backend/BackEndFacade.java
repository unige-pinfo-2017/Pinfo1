package ch.unige.pinfo.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.device.service.DeviceManager;
import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.overview.service.LiveDataService;
import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.UserService;
import ch.unige.pinfo.wso2.service.WSO2Wrapper;

public class BackEndFacade {
	@Inject
	UserService userService;
	
	@Inject
	DeviceManager deviceManager;
	
	@Inject
	LiveDataService liveDataService;
	
	// Temp, For testing
	@Inject
	WSO2Wrapper wso2Wrapper;
	
	public List<Double> getDataYear(String deviceId, String sensorType, int year) {
		return wso2Wrapper.getDataForYear(deviceId, sensorType, year);
	}
	
	public List<Double> getLastMonthData(String deviceType, String deviceId, String sensorType) {
		return wso2Wrapper.getLastMonthData(deviceType, deviceId, sensorType);
	}
	
	public List<Double> getLastWeekData(String deviceType, String deviceId, String sensorType) {
		return wso2Wrapper.getLastWeekData(deviceType, deviceId, sensorType);
	}
	
	public List<Double> getLastDayData(String deviceType, String deviceId, String sensorType) {
		return wso2Wrapper.getLastDayData(deviceType, deviceId, sensorType);
	}
	
	public boolean isMeasureName(String measureName) {
		List<LiveData> liveDatas = getAllLiveDatas();
		for (LiveData liveData: liveDatas) {
			if (liveData.getSensor().getMeasureName().equals(measureName)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean removePreference(Long userId, Long liveDataId) {
		return userService.removePreference(userId, liveDataId);
	}
	
	public boolean addPreference(Long userId, Long liveDataId) {
		return userService.addPreference(userId, liveDataId);
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
		if ("Manager".equals(role)) {
			users = getUsersOfManager(userId);
		} else if ("SysAdmin".equals(role)) {
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
		if ("Manager".equals(role)) {
			return getLiveDatasManager(userId);
		} else if ("SysAdmin".equals(role)) {
			return getLiveDatasSysAdmin(userId);
		} else if ("Basic".equals(role)) {
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
		if ("Sum".equals(computeType)) {
			return getSumSensorLiveForUser(userId, sensorName);
		} else if ("Average".equals(computeType)) {
			return getAvgSensorLiveForUser(userId, sensorName);
		}
		return 0;
	}
	
	public double getLiveDataValueForUsers(List<User> users, String computeType, String sensorName) {
		if ("Sum".equals(computeType)) {
			return getSumSensorLiveForUsers(users, sensorName);
		} else if ("Average".equals(computeType)) {
			return getAvgSensorLiveForUsers(users, sensorName);
		}
		return 0;
	}
	
	private double getAvgSensorLiveForUser(Long userId, String sensorName) {
		return deviceManager.getAvgSensorLiveForUser(userId, sensorName);
	}
	
	private double getAvgSensorLiveForUsers(List<User> users, String sensorName) {
		if (users.isEmpty()) {
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

	public Response changeDevice(String deviceId, String resource, String state) {
		return deviceManager.changeDevice(deviceId, resource, state);
	}
}
