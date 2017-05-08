package ch.unige.pinfo.device.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ch.unige.pinfo.device.dom.TypeDevice;

@Stateless
@Default
public class TypeDeviceServiceImpl implements TypeDeviceService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void addTypeDevice(TypeDevice typeDevice) {
		entityManager.persist(typeDevice);		
	}

	@Override
	public void deleteTypeDevice(Long id) {
		TypeDevice typeDevice = entityManager.find(TypeDevice.class, id);
		entityManager.remove(typeDevice);		
	}

	@Override
	public TypeDevice getTypeDeviceById(Long id) {
		return entityManager.find(TypeDevice.class, id);
	}

	@Override
	public List<TypeDevice> getAllTypeDevices() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TypeDevice> c = cb.createQuery(TypeDevice.class);
		Root<TypeDevice> typeDevices = c.from(TypeDevice.class);
		c.select(typeDevices);
		TypedQuery<TypeDevice> query = entityManager.createQuery(c);
		return query.getResultList();
	}
}
