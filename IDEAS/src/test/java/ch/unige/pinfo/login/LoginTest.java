package ch.unige.pinfo.login;


import static org.junit.Assert.*;
import ch.unige.pinfo.login.*;

import org.junit.Test;

public class LoginTest {
	@Test
	public void testCheckPassword(){
		LoginService login = new LoginService();
		String inputPassword = "abc";
		String realPassword = "abc";
		assertTrue(login.checkPassword(inputPassword, realPassword));
	}
	
	@Test
	public void testCheckPassword_fail(){
		LoginService login = new LoginService();
		String inputPassword = "abc";
		String realPassword = "def";
		assertFalse(login.checkPassword(inputPassword, realPassword));
	}
	
	/*@Test
	public void authentication(){
		Login login = new Login();
		
		String username = "ideas";
		String password = "ideaspw";
		
		login.authentication(username, password);
		
	}*/
}
