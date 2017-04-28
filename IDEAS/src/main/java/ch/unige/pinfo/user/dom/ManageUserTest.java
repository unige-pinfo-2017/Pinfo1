package ch.unige.pinfo.user.dom;

import static org.junit.Assert.*;

import org.junit.Test;

public class ManageUserTest {

	@Test
	public void testGetUserByUserName(){
		String username = "ideas";
		String password = "ideaspw";
		
		User user = new User();
		user.setId(0);
		user.setUsername(username);
		user.setPassword(password);
		
		ManageUser manageUser = new ManageUser(user);
		User retrievedUser = manageUser.getUserByUsername(username);
		
		assertTrue(user.equals(retrievedUser));
	}
	
	@Test
	public void testGetUserByUserName_fail_user_not_exist(){
		String username = "ideas";
		
		User user = new User();
		user.setId(0);
		user.setUsername(username);
		
		ManageUser manageUser = new ManageUser(user);
		User retrieved = manageUser.getUserByUsername("bob");
		
		assertFalse(user.equals(retrieved));
	}
}
