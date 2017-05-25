package ch.unige.pinfo.user.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.device.service.DeviceManager;
import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.overview.service.LiveDataService;
import ch.unige.pinfo.user.dom.Basic;
import ch.unige.pinfo.user.dom.Manager;
import ch.unige.pinfo.user.dom.SysAdmin;
import ch.unige.pinfo.user.dom.User;

@Stateless
@Default
public class UserServiceImpl implements UserService{
	@Inject 
	private DeviceManager deviceManager;
	
	@Inject 
	private LiveDataService liveDataService;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void addUser(User user) {
		entityManager.persist(user);		
	}

	@Override
	public void deleteUser(Long id) {
		User user = entityManager.find(User.class, id);
		entityManager.remove(user);		
	}

	@Override
	public User getUserById(Long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> getUserByUsername(String username) {		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> c = cb.createQuery(User.class);
		Root<User> user = c.from(User.class);
		Predicate condition = cb.equal(user.get("username"), username);
		c.where(condition);
		TypedQuery<User> query = entityManager.createQuery(c);
		return query.getResultList();
	}
	
	@Override
	public String getUserRoleById(Long id) {		
		User user = entityManager.find(User.class, id);
		return user.getClass().getAnnotation(DiscriminatorValue.class).value();
	}

	@Override
	public List<User> getAllUsers() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> c = cb.createQuery(User.class);
		Root<User> users = c.from(User.class);
		c.select(users);
		TypedQuery<User> query = entityManager.createQuery(c);
		return query.getResultList();
	}

	@Override
	public Set<Sensor> getSensorsForTypeDevice(String name) {
		return deviceManager.getSensorsForTypeDevice(name);
	}
	
	@Override
	public List<User> getUsersOfManager(Long userId) {
		Manager manager = getManagerById(userId);
		return new ArrayList<User>(manager.getUsers());
	}

	@Override
	public List<User> getUsersOfSysAdmin(Long userId) {
		SysAdmin sysAdmin = getSysAdminById(userId);
		return new ArrayList<User>(sysAdmin.getUsers());
	}
	
	@Override
	public Basic getBasicById(Long id) {
		return entityManager.find(Basic.class, id);
	}
	
	@Override
	public Manager getManagerById(Long id) {
		return entityManager.find(Manager.class, id);
	}

	@Override
	public SysAdmin getSysAdminById(Long id) {
		return entityManager.find(SysAdmin.class, id);
	}

	@Override
	public boolean addPreference(Long userId, Long liveDataId) {
		LiveData ld = liveDataService.getLiveDataById(liveDataId);
		try {
			this.getUserById(userId).getPreferences().add(ld);
			ld.getUsers().add(this.getUserById(userId));
			return true;
		} catch (RuntimeException e) {
			return false;
		}
	}
	
	@Override
	public boolean removePreference(Long userId, Long liveDataId) {
		LiveData ld = liveDataService.getLiveDataById(liveDataId);
		try {
			this.getUserById(userId).getPreferences().remove(ld);
			ld.getUsers().remove(this.getUserById(userId));
			return true;
		} catch (RuntimeException e) {
			return false;
		}
	}
	
	@Override
	public Set<LiveData> getUserPreferenceByUserId(Long userId){
		return getUserById(userId).getPreferences();
	}
	
	/*
	public double getAvgSensorLiveForUser(Long userId, String sensorName) {
		return deviceManager.getAvgSensorLiveForUser(userId, sensorName);
	}
	
	public double getSumSensorLiveForUser(Long userId, String sensorName) {
		return deviceManager.getSumSensorLiveForUser(userId, sensorName);
	}
	
	public List<Device> getAllDevicesForUserByTypeDevice(Long userId, String typeDevice) {
		return deviceManager.getAllDevicesForUserByTypeDevice(userId, typeDevice);
	}	
	
	public String getDeviceDataLive(String deviceId, String sensorName) {
		return deviceManager.getDeviceDataLive(deviceId, sensorName);
	}

	public List<Device> getAllDevicesForUserBySensorName(Long userId, String sensorName) {
		return deviceManager.getAllDevicesForUserBySensorName(userId, sensorName);
	}

	public Sensor getSensorFromSensorName(String sensorName) {
		return deviceManager.getSensorFromSensorName(sensorName);
	}
	
	public double getSumSensorLiveForUsers(List<User> users, String sensorName) {
		double res = 0;
		for (User user: users) {
			res += getSumSensorLiveForUser(user.getId(), sensorName);
		}
		return res;
	}
	
	public double getAvgSensorLiveForUsers(List<User> users, String sensorName) {
		if (users.size() == 0) {
			return 0;
		}
		return getSumSensorLiveForUsers(users, sensorName)/users.size();
	}
	*/
}
