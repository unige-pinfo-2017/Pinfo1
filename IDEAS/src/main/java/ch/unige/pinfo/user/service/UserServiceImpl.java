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
import ch.unige.pinfo.device.service.DeviceService;
import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.wso2.service.WSO2Wrapper;

@Stateless
@Default
public class UserServiceImpl implements UserService{
	@Inject 
	private DeviceManager deviceManager;
	
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
	public double getSumBySensor(Long userId, String sensorName, String from, String to) {
		return deviceManager.getSumSensorForUser(userId, sensorName, from, to);
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
	public double getDeviceData(Long deviceId, String sensorName, String from, String to) {
		return deviceManager.getDeviceData(deviceId, sensorName, from, to);
	}

	
}
