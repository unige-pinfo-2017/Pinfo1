package ch.unige.pinfo.user.service;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;

import ch.unige.pinfo.user.dom.User;

public class UserServiceImplTest {
	
	
	@Inject
	private UserService userService;
	
	@Test
	public void testGetUserById(){
		UserServiceImpl usi = new UserServiceImpl();
		
		User user = new User();
		user.setUsername("IDEAS");
		user.setPassword("IDEASPW");
		
		assertTrue(user.equals(usi.getUserById((long) 1)));
	}
	
	@Test
	public void testGetUserByUsername_null(){
		String username = "temp";
		String password = "temp";
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		userService.addUser(user);
		assertTrue(userService.getUserByUsername(username) != null);
	}
	
	/*@Test
	public void testAddUser(){
		UserServiceImpl usi = new UserServiceImpl();
		
		User userToAdd = new User();
		userToAdd.setUsername("testUsername");
		userToAdd.setPassword("testPassword");
		
		usi.addUser(userToAdd);
	}*/
}
