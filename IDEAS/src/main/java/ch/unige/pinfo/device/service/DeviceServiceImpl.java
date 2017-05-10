package ch.unige.pinfo.device.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.device.dom.TypeDevice;
import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.UserService;
import ch.unige.pinfo.wso2.service.WSO2Wrapper;

@Stateless
@Default
public class DeviceServiceImpl implements DeviceService {
	@Inject 
	private UserService userService;
	
	@Inject 
	private SensorService sensorService;
	
	@Inject
	private WSO2Wrapper wso2Wrapper;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void addDevice(Device device) {
		entityManager.persist(device);		
	}

	@Override
	public void deleteDevice(Long id) {
		Device device = entityManager.find(Device.class, id);
		entityManager.remove(device);	
	}

	@Override
	public Device getDeviceById(Long id) {
		return entityManager.find(Device.class, id);
	}

	@Override
	public List<Device> getAllDevices() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Device> c = cb.createQuery(Device.class);
		Root<Device> devices = c.from(Device.class);
		c.select(devices);
		TypedQuery<Device> query = entityManager.createQuery(c);
		return query.getResultList();
	}

	@Override
	public List<Device> getDevicesBySensor4User(Long userId, String sensorName) {
		User user = userService.getUserById(userId);
		Sensor sensor = sensorService.getSensorByName(sensorName);
		
		List<Device> devices = new ArrayList<Device>();
		for(Device device: user.getDevices()){
			for(TypeDevice type: sensor.getTypeDevices()){
				if (device.getType().getId() == type.getId()){
					devices.add(device);
				}
			}
		}
		return devices;
	}
	
	@Override
	public double getSumSensorForUser(long userId, String sensorName, String from, String to) {
		double sum = 0;
		
		List<Device> ld = this.getDevicesBySensor4User(userId, sensorName);
		for (Device device: ld){
			sum += wso2Wrapper.getValue(device.getType().getName(), device.getDeviceId(), sensorName, from, to);
		}
		
		return sum;
	}
	
/*
	@Override
	public List<Device> getDevicesByType(TypeDevice type) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Device> c = cb.createQuery(Device.class);
		Root<Device> device = c.from(Device.class);
		Predicate condition = cb.equal(device.get("type"), type);
		c.where(condition);
		TypedQuery<Device> query = entityManager.createQuery(c);
		return query.getResultList();
	}
*/
}
