package ch.unige.pinfo.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.io.Serializable;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.device.service.DeviceManager;
import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.overview.service.LiveDataService;
import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.UserService;
import ch.unige.pinfo.wso2.service.WSO2Wrapper;

public class BackEndFacade implements Serializable {
	/**
	 * The serial-id
	 */
	private static final long serialVersionUID = -4722536340334232427L;

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
	 * Computes the data of a device for a particular sensor over a given year.
	 * An average value is computed for each month of the given year.
	 * <p>
	 * 
	 * @param deviceId - The identifier of the device
	 * @param sensorType - The sensor type 
	 * @param year - The specified year
	 * @return
	 * A {@code List} containing the data of a device for the specified sensor over the given year in {@code Double}.
	 */
	public List<Double> getDataYear(String deviceId, String sensorType, int year) {
		return wso2Wrapper.getDataForYear(deviceId, sensorType, year);
	}
	
	/**
	 * <b>getLastMonthData</b>
	 * <p>
	 * {@code public List<Double> getLastMonthData(String deviceType, String deviceId, String sensorType) }
	 * <p>
	 * Computes the data of a device for a particular sensor over the past month.
	 * An average value is computed for each day of the past month starting with the moment the method is called.
	 * <p>
	 *
	 * @param deviceType - The device type
	 * @param deviceId - The identifier of the device
	 * @param sensorType - The sensor type
	 * @return
	 * A {@code List} containing the data of the device for the specified sensor over the last month in {@code Double}.
	 */
	public List<Double> getLastMonthData(String deviceType, String deviceId, String sensorType) {
		return wso2Wrapper.getLastMonthData(deviceType, deviceId, sensorType);
	}
	
	/**
	 * <b>getLastWeekData</b>
	 * <p>
	 * {@code public List<Double> getLastWeekData(String deviceType, String deviceId, String sensorType)}
	 * <p>
	 * Computes the data of a device for a particular sensor over the past week.
	 * An average value is computed for each day of the past week starting with the moment the method is called.
	 * <p>
	 * 
	 * @param deviceType - The device type
	 * @param deviceId - The identifier of the device
	 * @param sensorType - The sensor type
	 * @return
	 * A {@code List} containing the data of the device for the specified sensor over the past week in {@code Double}.
	 */
	public List<Double> getLastWeekData(String deviceType, String deviceId, String sensorType) {
		return wso2Wrapper.getLastWeekData(deviceType, deviceId, sensorType);
	}
	
	/**
	 * <b>getLastDayData</b>
	 * <p>
	 * {@code public List<Double> getLastDayData(String deviceType, String deviceId, String sensorType)}
	 * <p>
	 * Computes the data of a device for a particular sensor over the last day.
	 * An average value is computed for each hour of the past day starting with the moment the method is called.
	 * <p>
	 * 
	 * @param deviceType - The device type.
	 * @param deviceId - The identifier of the device
	 * @param sensorType - The sensor type
	 * @return
	 * A {@code List} containing the data of the device for the specified sensor over the past day in {@code Double}.
	 */
	public List<Double> getLastDayData(String deviceType, String deviceId, String sensorType) {
		return wso2Wrapper.getLastDayData(deviceType, deviceId, sensorType);
	}
	
	/**
	 * <b>isMeasureName</b>
	 * <p>
	 * {@code public boolean isMeasureName(String measureName)}
	 * <p>
	 * Verify the validity of a given measure name.
	 * <p>
	 * 
	 * @param measureName - The name of the measure
	 * @return
	 * {@code true} if the measure name is valid, {@code false} otherwise.
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
	 * Remove a live data from a user's preferences.
	 * <p>
	 * @param userId - The user's id
	 * @param liveDataId - The identifier of the live data to  be removed from the user's preferences
	 * @return
	 * {@code true} if live data has been successfully removed from the user's preferences, {@code false} otherwise.
	 */
	public boolean removePreference(Long userId, Long liveDataId) {
		return userService.removePreference(userId, liveDataId);
	}
	
	/**
	 * <b>addPreference</b>
	 * <p>
	 * {@code public boolean addPreference(Long userId, Long liveDataId)}
	 * <p>
	 * Add a live data to a user's preferences.
	 * <p>
	 * @param userId - The user's id
	 * @param liveDataId - The identifier of the live data to be added to the user's preferences
	 * @return
	 * {@code true} if live data has been successfully added to the user's preferences, {@code false} otherwise.
	 */
	public boolean addPreference(Long userId, Long liveDataId) {
		return userService.addPreference(userId, liveDataId);
	}
	
	/**
	 * <b>getLiveDataIdFromSensorMeasureName</b>
	 * <p>
	 * {@code public Long getLiveDataIdFromSensorMeasureName(String measureName)}
	 * <p>
	 * Computes the identifier of a live data from it's measure name.
	 * <p>
	 * 
	 * @param measureName - The measure name
	 * @return
	 * A {@code Long} containing the identifier of the live data corresponding to the given measure name
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
	 * Computes the device type name of a device from its device id.
	 * <p>
	 * 
	 * @param deviceId - The device's id
	 * @return
	 * A {@code String} containing the name of the device type.
	 */
	public String getDeviceTypeNameFromDeviceId(String deviceId) {
		return deviceManager.getDeviceTypeNameFromDeviceId(deviceId);
	}
	
	/**
	 * <b>getUsersList</b>
	 * <p>
	 * {@code public List<User> getUsersList(Long userId)}
	 * <p>
	 * Computes the subordinates list of a user from its user id.
	 * <p>
	 * @param userId - The user's id
	 * @return
	 * A {@code List<User>} containing the subordinates of the user.
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
	 * Get the live Color display by a type device specifying its {@code deviceId}
	 * <p>
	 * @param deviceType - The type of the device
 	 * @param deviceId - The identifier of the device.
	 * @return
	 * A {@code List<String>} containing the hue, the saturation and the kelvin of the device.
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
	 * A {@code Set<Sensor>} containing all the sensors.
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
	 * @param userId - The id of the user
	 * @param deviceType - The type of the device
	 * @return
	 * A {@code List<Device>} containing all the devices of the device type for the user.
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
	 * A {@code List<Device>} containing the user's devices equipped with the sensor.
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
	 * Get the user by its name.
	 * 
	 * @param username - The name of the user.
	 * @return
	 * A {@code User} object corresponding to the username.
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
	 * Get the role of a user using its id.
	 * 
	 * @param id - The user's id
	 * @return
	 * A {@code String} containing the role of the user.
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
	 * Get subordinates of a manager
	 * 
	 * @param userId - The manager's id
	 * @return
	 * A {@code List<User>} containing the subordinates of the manager
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
	 * Get subordinates of a system administrator.
	 * 
	 * @param userId - The user's id.
	 * @return
	 * A {@code List<User>} containing the subordinates of the system administrator.
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
     * Get the live value of a device for a specific sensor.
     * 
     * @param deviceId - The id of the device
     * @param sensorName - The sensor name.
     * @return
     * A {@code String} containing the live value of the device for the specified sensor.
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
	 * Get all the live datas.
	 * 
	 * @return
	 * A {@code List<LiveData>} containing all the live datas.
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
	 * A {@code Set<LiveData>} containing the live data in the user preferences.
	 */
	public Set<LiveData> getUserPreferences(Long userId) {
		return userService.getUserById(userId).getPreferences();
	}
	
	
	/**
	 * <b>getLiveDatas</b>
	 * <p>
	 * {@code public List<Double> getLiveDatas(Long userId)}
	 * <p>
	 * Get the live data of a user using its user id.
	 * <p>
	 * @param userId -The user's id
	 * @return
	 * A {@code List<Double>} containing the live data of the user.
	 */
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
	 * A {@code List<Double>} containing the basic user's live data.
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
	 * Compute the live data of a manager using its user id.
	 * The live data of a manager is computed using the live values of its own devices and its subordinates'.
	 * <p>
	 * 
	 * @param userId -The user's id
	 * @return
	 * A {@code List<Double>} containing the manager's live data.
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
	 * Compute the live data of a system administrator using its user id.
	 * The live data of a system administrator is computed using the live values of its own devices and its subordinates'.
	 * <p>
	 * 
	 * @param userId -The user's id
	 * @return
	 * A {@code List<Double>} containing the system administrator's live data.
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
	 * Compute the live data of a basic user using its user id.
	 * <p>
	 * 
	 * Get the value of live data fore a user.
	 * 
	 * @param userId - The user's id
	 * @param computeType - sum or average
	 * @param sensorName - sensor's name
	 * @return
	 * A {@code double} containing the live data of the user.
	 */
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
	 * Compute the live data for a list of subordinates.
	 * 
	 * @param users - List of users
	 * @param computeType - sum or average
	 * @param sensorName - sensor's name.
	 * @return
	 * A {@code double} containing the live data value of the subordinates.
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
	 * A {@code double} containing the average sensor value of the user's sensors.
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
	 * A {@code double} containing the average value of the live data.
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
	 * A {@code double} containing the sum value of the live data.
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
	 * A {double} corresponding to the sum.
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
	 * Get a sensor specifying its {@code sensorName}.
	 * 
	 * @param sensorName - The name of the sensor.
	 * @return
	 * A {@code Sensor} object corresponding to the sensor name.
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
	 * Change the state of a device resource specified by its {@code deviceId}.  
	 * 
	 * @param deviceId - The id of the device
	 * @param resource - The resource of the device
	 * @param state - The new state we want to set
	 * @return
	 * A {@code Response} : 200 if state is changed, 500 otherwise.
	 */
	public Response changeDevice(String deviceId, String resource, String state) {
		return deviceManager.changeDevice(deviceId, resource, state);
	}
}
