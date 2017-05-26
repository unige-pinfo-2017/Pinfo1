package ch.unige.pinfo.device.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
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
import ch.unige.pinfo.device.dom.TypeDevice;
import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.UserService;

@Stateless
public class DeviceServiceImpl implements DeviceService {
	@Inject 
	private UserService userService;
	
	@Inject 
	private SensorService sensorService;
	
	@Inject 
	private TypeDeviceService typeDeviceService;
	
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
	
	public Device getDeviceByDeviceId(String deviceId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Device> c = cb.createQuery(Device.class);
		Root<Device> user = c.from(Device.class);
		Predicate condition = cb.equal(user.get("deviceId"), deviceId);
		c.where(condition);
		TypedQuery<Device> query = entityManager.createQuery(c);
		return query.getResultList().get(0);
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
	public List<Device> getDevicesBySensorForUser(Long userId, String sensorName) {
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
	public List<Device> getDevicesByTypeDeviceForUser(Long userId, String typeDeviceName) {
		User user = userService.getUserById(userId);
		TypeDevice typeDevice = typeDeviceService.getTypeDeviceByName(typeDeviceName);
		
		List<Device> devices = new ArrayList<Device>();
		for(Device device: user.getDevices()){
			if (device.getType().getId() == typeDevice.getId()){
				devices.add(device);
			}
		}
		return devices;
	}
}
