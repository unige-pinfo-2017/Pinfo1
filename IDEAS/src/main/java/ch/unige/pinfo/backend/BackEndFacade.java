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
	
	/**
	 * <b>getDataYear</b>
	 * <p>
	 * {@code 	public List<Double> getDataYear(String deviceId, String sensorType, int year) }
	 * <p>
	 * 
	 * @param deviceId - The device's id 
	 * @param sensorType - The sensor's type
	 * @param year - The specify year
	 * @return
	 * A list of values for a device's sensor for the specify year in {@code Double} format.
	 */
	public List<Double> getDataYear(String deviceId, String sensorType, int year) {
		return wso2Wrapper.getDataForYear(deviceId, sensorType, year);
	}
	
	/**
	 * <b>getLastMonthData</b>
	 * <p>
	 * {@code public List<Double> getLastMonthData(String deviceType, String deviceId, String sensorType) }
	 * <p>
	 * 
	 * @param deviceType - The device's type.
	 * @param deviceId - The device's id 
	 * @param sensorType - The sensor's type
	 * @return
	 * A list of values for a device's sensor for the last month in {@code Double} format.
	 */
	public List<Double> getLastMonthData(String deviceType, String deviceId, String sensorType) {
		return wso2Wrapper.getLastMonthData(deviceType, deviceId, sensorType);
	}
	
	/**
	 * <b>getLastWeekData</b>
	 * <p>
	 * {@code public List<Double> getLastWeekData(String deviceType, String deviceId, String sensorType)}
	 * <p>
	 * 
	 * @param deviceType - The device's type.
	 * @param deviceId - The device's id 
	 * @param sensorType - The sensor's type
	 * @return
	 * A list of values for a device's sensor for the last week in {@code Double} format.
	 */
	public List<Double> getLastWeekData(String deviceType, String deviceId, String sensorType) {
		return wso2Wrapper.getLastWeekData(deviceType, deviceId, sensorType);
	}
	
	/**
	 * <b>getLastDayData</b>
	 * <p>
	 * {@code public List<Double> getLastDayData(String deviceType, String deviceId, String sensorType)}
	 * <p>
	 * 
	 * @param deviceType - The device's type.
	 * @param deviceId - The device's id 
	 * @param sensorType - The sensor's type
	 * @return
	 * A list of values for a device's sensor for the last day in {@code Double} format.
	 */
	public List<Double> getLastDayData(String deviceType, String deviceId, String sensorType) {
		return wso2Wrapper.getLastDayData(deviceType, deviceId, sensorType);
	}
	
	/**
	 * <b>isMeasureName</b>
	 * <p>
	 * {@code public boolean isMeasureName(String measureName)}
	 * <p>
	 * 
	 * verify if parameter is a measure.
	 * 
	 * @param measureName 
	 * @return
	 * {@code true} if it's valid measure, {@code false} otherwise.
	 */
	public boolean isMeasureName(String measureName) {
		List<LiveData> liveDatas = getAllLiveDatas();
		for (LiveData liveData: liveDatas) {
			if (liveData.getSensor().getMeasureName().equals(measureName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <b>removePreference</b>
	 * <p>
	 * {@code public boolean removePreference(Long userId, Long liveDataId)}
	 * <p>
	 * 
	 * Remove livedata form user's preferences.
	 * 
	 * @param userId - The user's id
	 * @param liveDataId - The live data to remove.
	 * @return
	 * {@code true} if live data is removed, {@code false} otherwise.
	 */
	public boolean removePreference(Long userId, Long liveDataId) {
		return userService.removePreference(userId, liveDataId);
	}
	
	/**
	 * <b>addPreference</b>
	 * <p>
	 * {@code public boolean addPreference(Long userId, Long liveDataId)}
	 * <p>
	 * 
	 * Add livedata to user's preferences.
	 * 
	 * @param userId - The user's id
	 * @param liveDataId - The live data to remove.
	 * @return
	 * {@code true} if live data is added, {@code false} otherwise.
	 */
	public boolean addPreference(Long userId, Long liveDataId) {
		return userService.addPreference(userId, liveDataId);
	}
	
	/**
	 * <b>getLiveDataIdFromSensorMeasureName</b>
	 * <p>
	 * {@code public Long getLiveDataIdFromSensorMeasureName(String measureName)}
	 * <p>
	 * 
	 * @param measureName - The specify measure name
	 * @return
	 * The value of live data for the measure name.
	 */
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
	
	/**
	 * <b>getDeviceTypeNameFromDeviceId</b>
	 * <p>
	 * {@code public String getDeviceTypeNameFromDeviceId(String deviceId)}
	 * <p>
	 * 
	 * @param deviceId - The device's id
	 * @return
	 * Get the name of the device.
	 */
	public String getDeviceTypeNameFromDeviceId(String deviceId) {
		return deviceManager.getDeviceTypeNameFromDeviceId(deviceId);
	}
	
	/**
	 * <b>getUsersList</b>
	 * <p>
	 * {@code public List<User> getUsersList(Long userId)}
	 * <p>
	 * 
	 * @param userId - The user's id
	 * @return
	 * A list of subordinate users for a user.
	 */
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
	
	/**
	 * <b>getDeviceDataLiveColor</b>
	 * <p>
	 * {@code public List<String> getDeviceDataLiveColor(String deviceType, String deviceId)}
	 * <p>
	 * 
	 * Get the live Color display by a type device specifying his {@code deviceId}
	 * 
	 * @param deviceType - The type of the device
 	 * @param deviceId - The Id of the device.
	 * @return
	 * list containing hue, saturation and kelvin data in {@code String}.
	 */
	public List<String> getDeviceDataLiveColor(String deviceType, String deviceId) {
		return deviceManager.getDeviceDataLiveColor(deviceType, deviceId);
	}
	
	/**
	 * <b>getSensorsForTypeDevice</b>
	 * <p>
	 * {@code public Set<Sensor> getSensorsForTypeDevice(String deviceType)}
	 * <p>
	 * 
	 * Get all sensors for a type of device. 
	 * 
	 * @param deviceType - type of a Device
	 * @return
	 * A set of sensors.
	 */
	public Set<Sensor> getSensorsForTypeDevice(String deviceType) {
		return deviceManager.getSensorsForTypeDevice(deviceType);
	}
	
	/**
	 * <b>getAllDevicesForUserByTypeDevice</b>
	 * <p>
	 * {@code public List<Device> getAllDevicesForUserByTypeDevice(Long userId, String deviceType)}
	 * <p>
	 * 
	 * Get all the devices by type device for a user.
	 * 
	 * @param userId - The id of the User
	 * @param deviceType - The type of the device
	 * @return
	 * A list of devices.
	 */
	public List<Device> getAllDevicesForUserByTypeDevice(Long userId, String deviceType) {
		return deviceManager.getAllDevicesForUserByTypeDevice(userId, deviceType);
	}
	
	/**
	 * <b>getAllDevicesForUserBySensorName</b>
	 * <p>
	 * {@code public List<Device> getAllDevicesForUserBySensorName(Long userId, String sensorName)}
	 * <p>
	 * 
	 * Get all devices by sensor name for a user.
	 * 
	 * @param userId - The id of the user
	 * @param sensorName - The sensor name.
	 * @return
	 * A list of devices.
	 */
	public List<Device> getAllDevicesForUserBySensorName(Long userId, String sensorName) {
		return deviceManager.getAllDevicesForUserBySensorName(userId, sensorName);
	}
	
	/**
	 * <b>getUserByUsername</b>
	 * <p>
	 * {@code public User getUserByUsername(String username)}
	 * <p>
	 * 
	 * Get the user by his name.
	 * 
	 * @param username - The name of the user.
	 * @return
	 * A user.
	 */
	public User getUserByUsername(String username) {
		return userService.getUserByUsername(username).get(0);
	}
	
	/**
	 * <b>getUserRoleById</b>
	 * <p>
	 * {@code public String getUserRoleById(Long id)}
	 * <p>
	 * 
	 * Get the user's role specifying the user's id.
	 * 
	 * @param id - The user's id
	 * @return
	 * The user's role in {@code String} format.
	 */
	public String getUserRoleById(Long id) {
		return userService.getUserRoleById(id);
	}
	
	/**
	 * <b>getUsersOfManager</b>
	 * <p>
	 * {@code public List<User> getUsersOfManager(Long userId)}
	 * <p>
	 * 
	 * Get user with role manager
	 * 
	 * @param userId - The user's id.
	 * @return
	 * A list of manager.
	 */
	public List<User> getUsersOfManager(Long userId) {
		return userService.getUsersOfManager(userId);
	}
	
	/**
	 * <b>getUsersOfSysAdmin</b>
	 * <p>
	 * {@code public List<User> getUsersOfSysAdmin(Long userId)}
	 * <p>
	 * 
	 * Get user with role System administrator.
	 * 
	 * @param userId - The user's id.
	 * @return
	 * A list of system administrator.
	 */
	public List<User> getUsersOfSysAdmin(Long userId) {
		return userService.getUsersOfSysAdmin(userId);
	}
	
	/**
     * <b>getDeviceDataLive</b>
     * <p>
     * {@code public String getDeviceDataLive(String deviceId, String sensorName)}
     * <p>
     * 
     * Get the live data of a device specifying his {@code deviceId} and the sensor we want the data.
     * 
     * @param deviceId - The id of the device
     * @param sensorName - The sensor name.
     * @return
     * The data in {@code String} format.
     */
	public String getDeviceDataLive(String deviceId, String sensorName) {
		return deviceManager.getDeviceDataLive(deviceId, sensorName);
	}

	/**
	 * <b>getAllLiveData</b>
	 * <p>
	 * {@code public List<LiveData> getAllLiveData()}
	 * <p>
	 * 
	 * Get all the existing data live.
	 * 
	 * @return
	 * A list of Live data.
	 */
	public List<LiveData> getAllLiveDatas(){
		return liveDataService.getAllLiveData();
	}
	
	/**
	 * <b>getUserPreferences</b>
	 * <p>
	 * {@code public Set<LiveData> getUserPreferences(Long userId)}
	 * <p>
	 * 
	 * Get the preferences of a user.
	 * 
	 * @param userId - The user's id
	 * @return
	 * A set of preferences.
	 */
	public Set<LiveData> getUserPreferences(Long userId) {
		return userService.getUserById(userId).getPreferences();
	}
	
	
	/**
	 * <b>getLiveDatas</b>
	 * <p>
	 * {@code public List<Double> getLiveDatas(Long userId)}
	 * <p>
	 * 
	 * @param userId -The user's id
	 * @return
	 * A list of live data for a user in {@code Double} format.
	 */
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
	
	/**
	 * <b>getLiveDatasBasic</b>
	 * <p>
	 * {@code private List<Double> getLiveDatasBasic(Long userId)}
	 * <p>
	 * 
	 * @param userId -The user's id
	 * @return
	 * A list of live data for a basic user.
	 */
	private List<Double> getLiveDatasBasic(Long userId) {
		List<Double> values = new ArrayList<Double>();
		Set<LiveData> liveDatas = userService.getUserById(userId).getPreferences();
		
		for (LiveData liveData: liveDatas) {	
			values.add(getLiveDataValueForUser(userId, liveData.getComputeType(), liveData.getSensor().getName()));
		}
		
		return values;
	}
	
	/**
	 * <b>getLiveDatasManager</b>
	 * <p>
	 * {@code private List<Double> getLiveDatasManager(Long userId)}
	 * <p>
	 * 
	 * @param userId -The user's id
	 * @return
	 * A list of live data for a manager.
	 */
	private List<Double> getLiveDatasManager(Long userId) {
		List<User> users = userService.getUsersOfManager(userId);
		List<Double> values = new ArrayList<Double>();
		Set<LiveData> liveDatas = userService.getUserById(userId).getPreferences();
		
		for (LiveData liveData: liveDatas) {	
			values.add(getLiveDataValueForUsers(users, liveData.getComputeType(), liveData.getSensor().getName()));
		}
		
		return values;
	}
	
	/**
	 * <b>getLiveDatasSysAdmin</b>
	 * <p>
	 * {@code private List<Double> getLiveDatasSysAdmin(Long userId)}
	 * <p>
	 * 
	 * @param userId -The user's id
	 * @return
	 * A list of live data for a sys admin.
	 */
	private List<Double> getLiveDatasSysAdmin(Long userId) {
		List<User> users = userService.getUsersOfSysAdmin(userId);
		List<Double> values = new ArrayList<Double>();
		Set<LiveData> liveDatas = userService.getUserById(userId).getPreferences();
		
		for (LiveData liveData: liveDatas) {	
			values.add(getLiveDataValueForUsers(users, liveData.getComputeType(), liveData.getSensor().getName()));
		}
		
		return values;
	}
	
	/**
	 * <b>getLiveDataValueForUser</b>
	 * <p>
	 * {@code public double getLiveDataValueForUser(Long userId, String computeType, String sensorName)}
	 * <p>
	 * 
	 * Get the value of live data fore a user.
	 * 
	 * @param userId - The user's id
	 * @param computeType - sum or average
	 * @param sensorName - sensor's name
	 * @return
	 * A value of live data.
	 */
	// TODO: Traiter le cas d'erreur
	public double getLiveDataValueForUser(Long userId, String computeType, String sensorName) {
		if ("Sum".equals(computeType)) {
			return getSumSensorLiveForUser(userId, sensorName);
		} else if ("Average".equals(computeType)) {
			return getAvgSensorLiveForUser(userId, sensorName);
		}
		return 0;
	}
	
	/**
	 * <b>getLiveDataValueForUsers</b>
	 * <p>
	 * {@code public double getLiveDataValueForUsers(List<User> users, String computeType, String sensorName)}
	 * <p>
	 * 
	 * Get the value of live data fore users.
	 * 
	 * @param users - List os users
	 * @param computeType - sum or average
	 * @param sensorName - sensor's name.
	 * @return
	 * A value of live data.
	 */
	public double getLiveDataValueForUsers(List<User> users, String computeType, String sensorName) {
		if ("Sum".equals(computeType)) {
			return getSumSensorLiveForUsers(users, sensorName);
		} else if ("Average".equals(computeType)) {
			return getAvgSensorLiveForUsers(users, sensorName);
		}
		return 0;
	}
	
	/**
	 * <b>getAvgSensorLiveForUser</b>
	 * <p>
	 * {@code private double getAvgSensorLiveForUser(Long userId, String sensorName)}
	 * <p>
	 * 
	 * Get the average value of live sensor data for a user.
	 * 
	 * @param userId - The user's id
	 * @param sensorName - The sensor's type.
	 * @return
	 * The average value.
	 */
	private double getAvgSensorLiveForUser(Long userId, String sensorName) {
		return deviceManager.getAvgSensorLiveForUser(userId, sensorName);
	}
	
	/**
	 * <b>getAvgSensorLiveForUsers</b>
	 * <p>
	 * {@code private double getAvgSensorLiveForUsers(List<User> users, String sensorName)}
	 * <p>
	 * 
	 * Get the average value of a live sensor data for users.
	 * 
	 * @param users - List of users
	 * @param sensorName - The sensor's type.
	 * @return
	 * The average value
	 */
	private double getAvgSensorLiveForUsers(List<User> users, String sensorName) {
		if (users.isEmpty()) {
			return 0;
		}
		return getSumSensorLiveForUsers(users, sensorName)/users.size();
	}
	
	/**
	 * <b>getSumSensorLiveForUser</b>
	 * <p>
	 * {@code private double getSumSensorLiveForUser(Long userId, String sensorName)}
	 * <p>
	 * 
	 * Get the sum value of live sensor data for a user.
	 * 
	 * @param userId - The user's id
	 * @param sensorName - The sensor's type.
	 * @return
	 * The sum value.
	 */
	private double getSumSensorLiveForUser(Long userId, String sensorName) {
		return deviceManager.getSumSensorLiveForUser(userId, sensorName);
	}
	
	/**
	 * <b>getSumSensorLiveForUsers</b>
	 * <p>
	 * {@code private double getSumSensorLiveForUsers(List<User> users, String sensorName)}
	 * <p>
	 * 
	 * Get the sum value of a live sensor data for users.
	 * 
	 * @param users - List of users
	 * @param sensorName - The sensor's type.
	 * @return
	 * The sum value
	 */
	private double getSumSensorLiveForUsers(List<User> users, String sensorName) {
		double res = 0;
		for (User user: users) {
			res += getSumSensorLiveForUser(user.getId(), sensorName);
		}
		return res;
	}

	/**
	 * <b>getSensorFromSensorName</b>
	 * <p>
	 * {@code public Sensor getSensorFromSensorName(String sensorName)}
	 * <p>
	 * 
	 * Get a sensor specifying his {@code sensorName}.
	 * 
	 * @param sensorName - The name of the sensor.
	 * @return
	 * A sensor.
	 */
	public Sensor getSensorFromSensorName(String sensorName) {
		return deviceManager.getSensorFromSensorName(sensorName);
	}

	/**
	 * <b>changeDevice</b>
	 * <p>
	 * {@code public Response changeDevice(String deviceId, String resource, String state)}
	 * <p>
	 * 
	 * Change the state of a Light's resource specifying his {@code deviceId}.  
	 * 
	 * @param deviceId - The id of the Light device
	 * @param resource - The resource of the Light. Can be Hue, Saturation, Kelvin or State
	 * @param state - The new state we want to set.
	 * @return
	 * A {@code Response} : 200 if state is changed, 500 otherwise.
	 */
	public Response changeDevice(String deviceId, String resource, String state) {
		return deviceManager.changeDevice(deviceId, resource, state);
	}
}
