package ch.unige.pinfo.device.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.TypeDevice;

@Stateless
@Default
public class DeviceServiceImpl implements DeviceService {

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
	public List<Device> getDevicesByType(TypeDevice type) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Device> c = cb.createQuery(Device.class);
		Root<Device> device = c.from(Device.class);
		Predicate condition = cb.equal(device.get("type"), type);
		c.where(condition);
		TypedQuery<Device> query = entityManager.createQuery(c);
		return query.getResultList();
	}
	
}
