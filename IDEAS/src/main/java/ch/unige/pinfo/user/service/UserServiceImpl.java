package ch.unige.pinfo.user.service;


import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.device.service.DeviceManager;
import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.overview.service.OverviewService;
import ch.unige.pinfo.user.dom.User;

@Stateless
@Default
public class UserServiceImpl implements UserService{
	@Inject 
	private DeviceManager deviceManager;
	
	@Inject
	private OverviewService overviewService;
	
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
	public List<User> getAllUsers() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> c = cb.createQuery(User.class);
		Root<User> users = c.from(User.class);
		c.select(users);
		TypedQuery<User> query = entityManager.createQuery(c);
		return query.getResultList();
	}
	
	@Override
	public double getAvgSensorLiveForUser(Long userId, String sensorName) {
		return deviceManager.getAvgSensorLiveForUser(userId, sensorName);
	}
	
	@Override
	public double getSumSensorLiveForUser(Long userId, String sensorName) {
		return deviceManager.getSumSensorLiveForUser(userId, sensorName);
	}

	@Override
	public Set<Sensor> getSensorsForTypeDevice(String name) {
		return deviceManager.getSensorsForTypeDevice(name);
	}

	@Override
	public List<Device> getAllDevicesForUserByTypeDevice(Long userId, String typeDevice) {
		return deviceManager.getAllDevicesForUserByTypeDevice(userId, typeDevice);
	}	
	
	@Override
	public double getDeviceDataLive(Long deviceId, String sensorName) {
	public String getDeviceDataLive(Long deviceId, String sensorName) {
		return deviceManager.getDeviceDataLive(deviceId, sensorName);
	}

	@Override
	public List<Device> getAllDevicesForUserBySensorName(Long userId, String sensorName) {
		return deviceManager.getAllDevicesForUserBySensorName(userId, sensorName);
	}

	@Override
	public Sensor getSensorFromSensorName(String sensorName) {
		return deviceManager.getSensorFromSensorName(sensorName);
	}

	@Override
	public List<LiveData> getAllLiveData() {
		return overviewService.getLiveDataService().getAllLiveData();
	}
}
