package ch.unige.pinfo.wso2.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ch.unige.pinfo.wso2.Dom.Sensor;

public class SensorServiceImpl implements SensorService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void addSensor(Sensor sensor) {
		entityManager.persist(sensor);		
	}

	@Override
	public void deleteSensor(Long id) {
		Sensor sensor = entityManager.find(Sensor.class, id);
		entityManager.remove(sensor);
	}

	@Override
	public Sensor getSensorById(Long id) {
		return entityManager.find(Sensor.class, id);
	}

	@Override
	public List<Sensor> getAllSensors() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Sensor> c = cb.createQuery(Sensor.class);
		Root<Sensor> sensors = c.from(Sensor.class);
		c.select(sensors);
		TypedQuery<Sensor> query = entityManager.createQuery(c);
		return query.getResultList();
	}

	@Override
	public Sensor getSensorByName(String sensorName) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Sensor> c = cb.createQuery(Sensor.class);
		Root<Sensor> sensor = c.from(Sensor.class);
		Predicate condition = cb.equal(sensor.get("name"), sensorName);
		c.where(condition);
		TypedQuery<Sensor> query = entityManager.createQuery(c);
		if (query.getResultList() == null)
			return new Sensor();
		return query.getResultList().get(0);
	}
}
