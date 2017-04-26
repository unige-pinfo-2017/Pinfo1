package ch.unige.pinfo.user.service;


import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public void deleteUser(Integer Id) {
		User user = entityManager.find(User.class, Id);
		entityManager.remove(user);		
	}

	@Override
	public User getUserByID(Integer Id) {
		return entityManager.find(User.class, Id);
	}

	@Override
	public User getUserByUsername(String username) {
		Query query = entityManager.createNamedQuery("User.findByUsername");
		query.setParameter("username", username);
		if (query.getResultList().size() == 0)
			return new User();
		else
			return (User) query.getResultList().get(0) ;
	}

	@Override
	public List<User> getAllUsers() {
		Query query = entityManager.createNamedQuery("User.findAll");
		return query.getResultList();
	}
}
