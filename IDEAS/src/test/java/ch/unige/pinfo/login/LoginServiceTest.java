package ch.unige.pinfo.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ch.unige.pinfo.backend.BackEndFacade;
import ch.unige.pinfo.user.dom.User;

public class LoginServiceTest {
	@Mock
	BackEndFacade mockBackEndFacade;
	
	@Mock
	LoginJsonBuilder mockLoginJsonBuilder;
	
	@InjectMocks
	LoginService ls = new LoginService();
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void authenticateTest() {
		String username = "username";
		String inputPw = "password";
		String password = "password";
		Long id = (long) 1;
		User mockUser = new User();
		mockUser.setUsername(username);
		mockUser.setPassword(password);
		mockUser.setId(id);
		
		when(mockBackEndFacade.getUserByUsername(username)).thenReturn(mockUser);
		
		String output = ls.authenticate(username, inputPw);
		
		assertEquals(Long.toString(id), output);
	}
	
	@Test
	public void authenticateTest_fail() {
		String username = "username";
		String inputPw = "notpassword";
		String password = "password";
		Long id = (long) 1;
		User mockUser = new User();
		mockUser.setUsername(username);
		mockUser.setPassword(password);
		mockUser.setId(id);
		
		when(mockBackEndFacade.getUserByUsername(username)).thenReturn(mockUser);
		
		String output = ls.authenticate(username, inputPw);
		
		assertTrue(!(Long.toString(id).equals(output)));
	}
	
	@Test
	public void getSurbordinatesTest_Manager() {
		Long userId = (long) 1;
		String mockRole = "Manager";
		
		User managerUser = new User();
		managerUser.setId((long) 1);
		
		User sysAdminUser = new User();
		sysAdminUser.setId((long) 2);
		
		List<User> managerUsers = new ArrayList<User>();
		managerUsers.add(managerUser);
		
		List<User> sysAdminUsers = new ArrayList<User>();
		sysAdminUsers.add(sysAdminUser);
		
		List<User> basicUsers = new ArrayList<User>();
		
		JsonArray empty = Json.createArrayBuilder().build();
		
		JsonArray managerJson = Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("manager", "manager")
										.build())
								.build();
		
		JsonArray sysAdminJson = Json.createArrayBuilder()
									.add(Json.createObjectBuilder()
											.add("sysAdmin", "sysAdmin")
											.build())
									.build();
		
		when(mockBackEndFacade.getUserRoleById(userId)).thenReturn(mockRole);
		when(mockBackEndFacade.getUsersOfManager(userId)).thenReturn(managerUsers);
		when(mockBackEndFacade.getUsersOfSysAdmin(userId)).thenReturn(sysAdminUsers);
		when(mockLoginJsonBuilder.buildIds(managerUsers)).thenReturn(managerJson);
		when(mockLoginJsonBuilder.buildIds(sysAdminUsers)).thenReturn(sysAdminJson);
		when(mockLoginJsonBuilder.buildIds(basicUsers)).thenReturn(empty);
		
		JsonArray output = ls.getSubordinates(userId);
		
		assertEquals(managerJson, output);
	}
	
	@Test
	public void getSurbordinatesTest_SysAdmin() {
		Long userId = (long) 1;
		String mockRole = "SysAdmin";
		
		User managerUser = new User();
		managerUser.setId((long) 1);
		
		User sysAdminUser = new User();
		sysAdminUser.setId((long) 2);
		
		List<User> managerUsers = new ArrayList<User>();
		managerUsers.add(managerUser);
		
		List<User> sysAdminUsers = new ArrayList<User>();
		sysAdminUsers.add(sysAdminUser);
		
		List<User> basicUsers = new ArrayList<User>();
		
		JsonArray empty = Json.createArrayBuilder().build();
		
		JsonArray managerJson = Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("manager", "manager")
										.build())
								.build();
		
		JsonArray sysAdminJson = Json.createArrayBuilder()
									.add(Json.createObjectBuilder()
											.add("sysAdmin", "sysAdmin")
											.build())
									.build();
		
		when(mockBackEndFacade.getUserRoleById(userId)).thenReturn(mockRole);
		when(mockBackEndFacade.getUsersOfManager(userId)).thenReturn(managerUsers);
		when(mockBackEndFacade.getUsersOfSysAdmin(userId)).thenReturn(sysAdminUsers);
		when(mockLoginJsonBuilder.buildIds(managerUsers)).thenReturn(managerJson);
		when(mockLoginJsonBuilder.buildIds(sysAdminUsers)).thenReturn(sysAdminJson);
		when(mockLoginJsonBuilder.buildIds(basicUsers)).thenReturn(empty);
		
		JsonArray output = ls.getSubordinates(userId);
		
		assertEquals(sysAdminJson, output);
	}
	
	@Test
	public void getSurbordinatesTest_Basic() {
		Long userId = (long) 1;
		String mockRole = "Basic";
		
		User managerUser = new User();
		managerUser.setId((long) 1);
		
		User sysAdminUser = new User();
		sysAdminUser.setId((long) 2);
		
		List<User> managerUsers = new ArrayList<User>();
		managerUsers.add(managerUser);
		
		List<User> sysAdminUsers = new ArrayList<User>();
		sysAdminUsers.add(sysAdminUser);
		
		List<User> basicUsers = new ArrayList<User>();
		
		JsonArray empty = Json.createArrayBuilder().build();
		
		JsonArray managerJson = Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("manager", "manager")
										.build())
								.build();
		
		JsonArray sysAdminJson = Json.createArrayBuilder()
									.add(Json.createObjectBuilder()
											.add("sysAdmin", "sysAdmin")
											.build())
									.build();
		
		when(mockBackEndFacade.getUserRoleById(userId)).thenReturn(mockRole);
		when(mockBackEndFacade.getUsersOfManager(userId)).thenReturn(managerUsers);
		when(mockBackEndFacade.getUsersOfSysAdmin(userId)).thenReturn(sysAdminUsers);
		when(mockLoginJsonBuilder.buildIds(managerUsers)).thenReturn(managerJson);
		when(mockLoginJsonBuilder.buildIds(sysAdminUsers)).thenReturn(sysAdminJson);
		when(mockLoginJsonBuilder.buildIds(basicUsers)).thenReturn(empty);
		
		JsonArray output = ls.getSubordinates(userId);
		
		assertEquals(empty, output);
	}
}
