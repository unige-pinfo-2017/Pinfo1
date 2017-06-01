package ch.unige.pinfo.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.device.service.DeviceManager;
import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.overview.service.LiveDataService;
import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.UserService;

public class BackEndFacadeTest {
	@Mock
	private LiveDataService mockLiveDataService;
	
	@Mock
	private UserService mockUserService;
	
	@Mock
	private DeviceManager mockDeviceManager;
	
	@InjectMocks
	private BackEndFacade backEndFacade = new BackEndFacade();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void isMeasureNameTest() {
		String mockMeasure = "measure";
		String measureName = "measure";
		
		Sensor sensor1 = new Sensor();
		sensor1.setMeasureName(mockMeasure);
		Sensor sensor2 = new Sensor();
		sensor2.setMeasureName(mockMeasure);
		
		LiveData liveData1 = new LiveData();
		liveData1.setSensor(sensor1);
		LiveData liveData2 = new LiveData();
		liveData2.setSensor(sensor2);
		
		List<LiveData> mockLiveDatas = new ArrayList<LiveData>();
		mockLiveDatas.add(liveData1);
		mockLiveDatas.add(liveData2);
		
		when(mockLiveDataService.getAllLiveData()).thenReturn(mockLiveDatas);
		
		assertTrue(backEndFacade.isMeasureName(measureName));
	}
	
	@Test
	public void getLiveDataIdFromSensorMeasureNameTest () {
		String mockMeasure = "measure";
		String measureName = "measure";
		
		Long id1 = (long) 1;
		Long id2 = (long) 2;
		
		Sensor sensor1 = new Sensor();
		sensor1.setMeasureName(mockMeasure);
		Sensor sensor2 = new Sensor();
		sensor2.setMeasureName(mockMeasure);
		
		LiveData liveData1 = new LiveData();
		liveData1.setId(id1);
		liveData1.setSensor(sensor1);
		
		LiveData liveData2 = new LiveData();
		liveData2.setId(id2);
		liveData2.setSensor(sensor2);
		
		List<LiveData> mockLiveDatas = new ArrayList<LiveData>();
		mockLiveDatas.add(liveData1);
		mockLiveDatas.add(liveData2);
		
		when(mockLiveDataService.getAllLiveData()).thenReturn(mockLiveDatas);
		
		assertEquals(id1, backEndFacade.getLiveDataIdFromSensorMeasureName(mockMeasure));
	}
	
	@Test
	public void getUsersListTest() {
		Long userId = (long) 1;
		
		User userManager = new User();
		userManager.setId((long) 1);
		
		User userSysAdmin = new User();
		userManager.setId((long) 2);
		
		List<User> usersManager = new ArrayList<User>();
		usersManager.add(userManager);
		
		List<User> usersSysAdmin = new ArrayList<User>();
		usersSysAdmin.add(userSysAdmin);
		
		List<User> usersBasic = new ArrayList<User>();
		
		when(backEndFacade.getUsersOfManager(userId)).thenReturn(usersManager);
		when(backEndFacade.getUsersOfSysAdmin(userId)).thenReturn(usersSysAdmin);
		
		when(backEndFacade.getUserRoleById(userId)).thenReturn("Manager");
		assertEquals(usersManager, backEndFacade.getUsersList(userId));
		
		when(backEndFacade.getUserRoleById(userId)).thenReturn("SysAdmin");
		assertEquals(usersSysAdmin, backEndFacade.getUsersList(userId));
		
		when(backEndFacade.getUserRoleById(userId)).thenReturn("Basic");
		assertEquals(usersBasic, backEndFacade.getUsersList(userId));
	}	
	
	@Test
	public void getLiveDatasTest() {
		Long userId = 1l;
		
		String role = "Manager";
		
		String sensorName = "sensorName";
		
		Sensor sensor = new Sensor();
		sensor.setName("sensor");
		
		LiveData pref = new LiveData();
		pref.setComputeType("Sum");
		pref.setSensor(sensor);
		
		Set<LiveData> managerPref = new HashSet<LiveData>();
		managerPref.add(pref);
		
		User manager = new User();
		manager.setId(userId);
		manager.setPreferences(managerPref);
		
		User sub = new User();
		sub.setId(2l);
		
		List<User> subs = new ArrayList<User>();
		subs.add(sub);
		
		double liveValue = 1d;
		
		when(mockUserService.getUserRoleById(userId))
			.thenReturn(role);
		when(mockUserService.getUsersOfManager(userId))
			.thenReturn(subs);
		when(mockUserService.getUserById(userId))
			.thenReturn(manager);
		when(mockDeviceManager.getSumSensorLiveForUser(subs.get(0).getId(), sensor.getName()))
			.thenReturn(liveValue);
		
		List<Double> expected = new ArrayList<Double>();
		expected.add(liveValue);
		
		List<Double> output = backEndFacade.getLiveDatas(userId);
		
		assertEquals(expected, output);
		
	}
}
