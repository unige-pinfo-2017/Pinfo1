package ch.unige.pinfo.user.service;


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

import ch.unige.pinfo.smartLab.device.Device;
import ch.unige.pinfo.smartLab.device.PowerSensor;
import ch.unige.pinfo.smartLab.device.PowerSocket;
import ch.unige.pinfo.user.dom.User;

@Stateless
@Default
public class UserServiceImpl implements UserService{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void addUser(User user) {
		entityManager.persist(user);		
	}

	@Override
	public void deleteUser(Long Id) {
		User user = entityManager.find(User.class, Id);
		entityManager.remove(user);		
	}

	@Override
	public User getUserById(Long Id) {
		return entityManager.find(User.class, Id);
	}

	@Override
	public List<User> getUserByUsername(String username) {
		
		PowerSocket ps = new PowerSocket();
		ps.setName("name");
		ps.getMySensors().add(new PowerSensor());
		entityManager.persist(ps);
		
		/*CriteriaBuilder cb1 = entityManager.getCriteriaBuilder();
		CriteriaQuery<Device> c1 = cb1.createQuery(Device.class);
		Root<Device> device = c1.from(Device.class);
		//Predicate condition = cb.equal(user.get("username"), username);
		//c.where(condition);
		TypedQuery<Device> query1 = entityManager.createQuery(c1);
		query1.getResultList().get(1).getMySensors().get(0);*/
		
		
		
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
}
