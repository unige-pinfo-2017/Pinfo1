package ch.unige.pinfo.device.service;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.junit.Before;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.TypeDevice;
import ch.unige.pinfo.user.dom.Basic;
import ch.unige.pinfo.user.dom.Manager;
import ch.unige.pinfo.user.dom.SysAdmin;
import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.UserService;
import ch.unige.pinfo.wso2.service.WSO2Wrapper;
import junit.framework.Assert;

public class DeviceManagerImplTest {

	@Mock
	private WSO2Wrapper wso2WrapperMock;
	
	@Mock
	private DeviceService deviceServiceMock;
	
	@Mock
	private UserService userServiceMock;
	
	@Mock
	private SensorService sensorServiceMock;
	
	@InjectMocks
	DeviceManagerImpl devImpl;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testChangeDevice() {
		
		String deviceId = "1";
		String r1 = "hue";
		String r2 = "kelvin";
		String r3 = "saturation";
		String r4 = "state";
		String r5 = "unknown device";
		
		Response expect1 = Response.status(500).entity("response for hue").build();
		Response expect2 = Response.status(500).entity("response for kelvin").build();
		Response expect3 = Response.status(500).entity("response for saturation").build();
		Response expect4 = Response.status(500).entity("response for state").build();
		Response expect5 = Response.status(500).entity("Resource does not exist for this device.").build();
		
		String state = "test";
		
		when(wso2WrapperMock.changeHue(deviceId, state)).thenReturn(expect1);
		when(wso2WrapperMock.changeKelvin(deviceId, state)).thenReturn(expect2);
		when(wso2WrapperMock.changeSaturation(deviceId, state)).thenReturn(expect3);
		when(wso2WrapperMock.changeState(deviceId, state)).thenReturn(expect4);
		
		Response res1 = devImpl.changeDevice(deviceId, r1, state);
		Response res2 = devImpl.changeDevice(deviceId, r2, state);
		Response res3 = devImpl.changeDevice(deviceId, r3, state);
		Response res4 = devImpl.changeDevice(deviceId, r4, state);
		Response res5 = devImpl.changeDevice(deviceId, r5, state);
		
		assertResponse(expect1, res1);
		assertResponse(expect2, res2);
		assertResponse(expect3, res3);
		assertResponse(expect4, res4);
		assertResponse(expect5, res5);
	}
	
	@Test
	public void getAvgSensorLiveForUserTest() {
		Long userId = 1l;
		String sensorName = "powerSensor";
		
		Device d1 = new Device("1");
		TypeDevice td1 = new TypeDevice("one");
		d1.setType(td1);
		Device d2 = new Device("2");
		TypeDevice td2 = new TypeDevice("two");
		d2.setType(td2);
		Device d3 = new Device("3");
		TypeDevice td3 = new TypeDevice("three");
		d3.setType(td3);
		
		List<Device> listDev = new ArrayList<Device>();
		listDev.add(d1);
		listDev.add(d2);
		listDev.add(d3);
		
		when(deviceServiceMock.getDevicesBySensorForUser(userId, sensorName)).thenReturn(listDev);
		when(wso2WrapperMock.getValueLive(d1.getType().getName(), d1.getDeviceId(), sensorName)).thenReturn("1.1");
		when(wso2WrapperMock.getValueLive(d2.getType().getName(), d2.getDeviceId(), sensorName)).thenReturn("2.2");
		when(wso2WrapperMock.getValueLive(d3.getType().getName(), d3.getDeviceId(), sensorName)).thenReturn("3.3");
		
		Double expectedSum = (1.1d+2.2d+3.3d)/3;
		
		Double res = devImpl.getAvgSensorLiveForUser(userId, sensorName);
		
		assertEquals(expectedSum, res);
	}
	
	@Test
	public void getSumSensorLiveForUserTest() {
		Long userId = 1l;
		String sensorName = "powerSensor";
		
		Device d1 = new Device("1");
		TypeDevice td1 = new TypeDevice("one");
		d1.setType(td1);
		Device d2 = new Device("2");
		TypeDevice td2 = new TypeDevice("two");
		d2.setType(td2);
		Device d3 = new Device("3");
		TypeDevice td3 = new TypeDevice("three");
		d3.setType(td3);

		List<Device> listDev = new ArrayList<Device>();
		listDev.add(d1);
		listDev.add(d2);
		listDev.add(d3);
		
		when(deviceServiceMock.getDevicesBySensorForUser(userId, sensorName)).thenReturn(listDev);
		when(wso2WrapperMock.getValueLive(d1.getType().getName(), d1.getDeviceId(), sensorName)).thenReturn("1.1");
		when(wso2WrapperMock.getValueLive(d2.getType().getName(), d2.getDeviceId(), sensorName)).thenReturn("2.2");
		when(wso2WrapperMock.getValueLive(d3.getType().getName(), d3.getDeviceId(), sensorName)).thenReturn("3.3");
		
		Double expectedSum = (1.1d+2.2d+3.3d);
		
		Double res = devImpl.getSumSensorLiveForUser(userId, sensorName);
		
		assertEquals(expectedSum, res);
	}
	
	@Test
	public void getAllDevicesForUserByTypeDeviceTest() {
		Long userIdMan = 1l;
		Long userIdSys = 20l;
		Long userIdBase = 71l;
		String typeDevice = "powerSocket";
		User manUser = new Manager();
		manUser.setId(userIdMan);
		User sysadUser = new SysAdmin();
		sysadUser.setId(userIdSys);
		User baseUser = new Basic();
		baseUser.setId(userIdBase);
		
		Device d1 = new Device("1");
		Device d2 = new Device("2");
		Device d3 = new Device("3");
		Device d4 = new Device("4");
		Device d5 = new Device("5");
		Device d6 = new Device("6");
		TypeDevice td1 = new TypeDevice("one");
		TypeDevice td2 = new TypeDevice("two");
		TypeDevice td3 = new TypeDevice("three");
		TypeDevice td4 = new TypeDevice("four");
		TypeDevice td5 = new TypeDevice("five");
		TypeDevice td6 = new TypeDevice("six");
		d1.setId(1l);
		d2.setId(2l);
		d3.setId(3l);
		d4.setId(4l);
		d5.setId(5l);
		d6.setId(6l);
		d1.setType(td1);
		d2.setType(td2);
		d3.setType(td3);
		d4.setType(td4);
		d5.setType(td5);
		d6.setType(td6);

		List<Device> listDevMan = new ArrayList<Device>();
		listDevMan.add(d1);
		listDevMan.add(d2);
		List<Device> listDevSys = new ArrayList<Device>();
		listDevSys.add(d3);
		listDevSys.add(d4);
		List<Device> listDevBase = new ArrayList<Device>();
		listDevBase.add(d5);
		listDevBase.add(d6);
		
		List<User> managerUsers = new ArrayList<User>();
		List<User> sysadminUsers = new ArrayList<User>();
		managerUsers.add(sysadUser);
		sysadminUsers.add(manUser);
		sysadminUsers.add(baseUser);
		
		when(deviceServiceMock.getDevicesByTypeDeviceForUser(userIdMan, typeDevice)).thenReturn(listDevMan);
		when(deviceServiceMock.getDevicesByTypeDeviceForUser(userIdSys, typeDevice)).thenReturn(listDevSys);
		when(deviceServiceMock.getDevicesByTypeDeviceForUser(userIdBase, typeDevice)).thenReturn(listDevBase);
		
		when(userServiceMock.getUserRoleById(userIdMan)).thenReturn("Manager");
		when(userServiceMock.getUserRoleById(userIdSys)).thenReturn("SysAdmin");
		when(userServiceMock.getUserRoleById(userIdBase)).thenReturn("Basic");
		
		when(userServiceMock.getUsersOfManager(userIdMan)).thenReturn(managerUsers);
		when(userServiceMock.getUsersOfSysAdmin(userIdSys)).thenReturn(sysadminUsers);
		
		List<Device> expectMan = new ArrayList<Device>();
		List<Device> expectSys = new ArrayList<Device>();
		List<Device> expectBase = new ArrayList<Device>();
		
		expectMan.addAll(listDevMan);
		expectMan.addAll(listDevSys);
		
		expectSys.addAll(listDevSys);
		expectSys.addAll(listDevMan);
		expectSys.addAll(listDevBase);
		
		expectBase.addAll(listDevBase);
		
		List<Device> resMan = new ArrayList<Device>();
		List<Device> resSys = new ArrayList<Device>();
		List<Device> resBase = new ArrayList<Device>();
		
		resMan = devImpl.getAllDevicesForUserByTypeDevice(userIdMan, typeDevice);
		resSys = devImpl.getAllDevicesForUserByTypeDevice(userIdSys, typeDevice);
		resBase = devImpl.getAllDevicesForUserByTypeDevice(userIdBase, typeDevice);
		
		Assert.assertTrue(resMan.containsAll(expectMan));
		Assert.assertTrue(resSys.containsAll(expectSys));
		Assert.assertTrue(resBase.containsAll(expectBase));
	}
	
	@Test
	public void getAllDevicesForUserBySensorNameTest() {
		Long userIdMan = 1l;
		Long userIdSys = 20l;
		Long userIdBase = 71l;
		String sensorName = "powerSensor";
		User manUser = new Manager();
		manUser.setId(userIdMan);
		User sysadUser = new SysAdmin();
		sysadUser.setId(userIdSys);
		User baseUser = new Basic();
		baseUser.setId(userIdBase);
		
		Device d1 = new Device("1");
		Device d2 = new Device("2");
		Device d3 = new Device("3");
		Device d4 = new Device("4");
		Device d5 = new Device("5");
		Device d6 = new Device("6");
		TypeDevice td1 = new TypeDevice("one");
		TypeDevice td2 = new TypeDevice("two");
		TypeDevice td3 = new TypeDevice("three");
		TypeDevice td4 = new TypeDevice("four");
		TypeDevice td5 = new TypeDevice("five");
		TypeDevice td6 = new TypeDevice("six");
		d1.setId(1l);
		d2.setId(2l);
		d3.setId(3l);
		d4.setId(4l);
		d5.setId(5l);
		d6.setId(6l);
		d1.setType(td1);
		d2.setType(td2);
		d3.setType(td3);
		d4.setType(td4);
		d5.setType(td5);
		d6.setType(td6);

		List<Device> listDevMan = new ArrayList<Device>();
		listDevMan.add(d1);
		listDevMan.add(d2);
		List<Device> listDevSys = new ArrayList<Device>();
		listDevSys.add(d3);
		listDevSys.add(d4);
		List<Device> listDevBase = new ArrayList<Device>();
		listDevBase.add(d5);
		listDevBase.add(d6);
		
		List<User> managerUsers = new ArrayList<User>();
		List<User> sysadminUsers = new ArrayList<User>();
		managerUsers.add(sysadUser);
		sysadminUsers.add(manUser);
		sysadminUsers.add(baseUser);
		
		when(deviceServiceMock.getDevicesBySensorForUser(userIdMan, sensorName)).thenReturn(listDevMan);
		when(deviceServiceMock.getDevicesBySensorForUser(userIdSys, sensorName)).thenReturn(listDevSys);
		when(deviceServiceMock.getDevicesBySensorForUser(userIdBase, sensorName)).thenReturn(listDevBase);

		when(userServiceMock.getUserRoleById(userIdMan)).thenReturn("Manager");
		when(userServiceMock.getUserRoleById(userIdSys)).thenReturn("SysAdmin");
		when(userServiceMock.getUserRoleById(userIdBase)).thenReturn("Basic");
		
		when(userServiceMock.getUsersOfManager(userIdMan)).thenReturn(managerUsers);
		when(userServiceMock.getUsersOfSysAdmin(userIdSys)).thenReturn(sysadminUsers);
		
		List<Device> expectMan = new ArrayList<Device>();
		List<Device> expectSys = new ArrayList<Device>();
		List<Device> expectBase = new ArrayList<Device>();
		
		expectMan.addAll(listDevMan);
		expectMan.addAll(listDevSys);
		
		expectSys.addAll(listDevSys);
		expectSys.addAll(listDevMan);
		expectSys.addAll(listDevBase);
		
		expectBase.addAll(listDevBase);
		
		List<Device> resMan = new ArrayList<Device>();
		List<Device> resSys = new ArrayList<Device>();
		List<Device> resBase = new ArrayList<Device>();
		
		resMan = devImpl.getAllDevicesForUserBySensorName(userIdMan, sensorName);
		resSys = devImpl.getAllDevicesForUserBySensorName(userIdSys, sensorName);
		resBase = devImpl.getAllDevicesForUserBySensorName(userIdBase, sensorName);
		
		Assert.assertTrue(resMan.containsAll(expectMan));
		Assert.assertTrue(resSys.containsAll(expectSys));
		Assert.assertTrue(resBase.containsAll(expectBase));
	}
	
	private void assertResponse(Response expectedResponse, Response recievedResponse) {
		assertEquals(expectedResponse.getEntity(), recievedResponse.getEntity());
		assertEquals(expectedResponse.getStatus(), recievedResponse.getStatus());
	}

}
