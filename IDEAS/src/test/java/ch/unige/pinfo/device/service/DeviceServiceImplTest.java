package ch.unige.pinfo.device.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.device.dom.TypeDevice;
import ch.unige.pinfo.user.dom.Basic;
import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.UserService;

public class DeviceServiceImplTest {
	
	@Mock
	UserService userServiceMock;
	
	@Mock
	SensorService sensorServiceMock;
	
	@InjectMocks
	DeviceServiceImpl serveImpl;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getDevicesBySensorForUserTest() {
		Long userId = 1l;
		User us = new Basic();
		us.setId(userId);
		
		Device d1 = new Device("one");
		Device d2 = new Device("two");
		
		TypeDevice td1 = new TypeDevice("powerSocket");
		td1.setId(1l);
		TypeDevice td2 = new TypeDevice("Beacon");
		td2.setId(2l);
		TypeDevice td3 = new TypeDevice("Light");
		td3.setId(3l);
		TypeDevice td4 = new TypeDevice("Test");
		td4.setId(4l);
		
		Sensor s1 = new Sensor();
		Sensor s2 = new Sensor();
		Sensor s3 = new Sensor();

		d1.setType(td1);
		d2.setType(td2);
		
		Set<TypeDevice> listType1 = new HashSet<TypeDevice>();
		listType1.add(td1);
		listType1.add(td2);
		

		Set<TypeDevice> listType2 = new HashSet<TypeDevice>();
		listType2.add(td1);
		listType2.add(td3);
		

		Set<TypeDevice> listType3 = new HashSet<TypeDevice>();
		listType3.add(td3);
		listType3.add(td4);
		
		s1.setTypeDevices(listType1);
		s2.setTypeDevices(listType2);
		s3.setTypeDevices(listType3);
		
		Set<Device> listDev = new HashSet<Device>();
		listDev.add(d1);
		listDev.add(d2);
		
		String sensorName1 = "powerSensor";
		String sensorName2 = "ColorSensor";
		String sensorName3 = "TemperatureSensor";
		
		us.setDevices(listDev);
		
		when(userServiceMock.getUserById(userId)).thenReturn(us);
		when(sensorServiceMock.getSensorByName(sensorName1)).thenReturn(s1);
		when(sensorServiceMock.getSensorByName(sensorName2)).thenReturn(s2);
		when(sensorServiceMock.getSensorByName(sensorName3)).thenReturn(s3);
		
		List<Device> expect1 = new ArrayList<Device>();
		expect1.add(d1);
		expect1.add(d2);

		List<Device> expect2 = new ArrayList<Device>();
		expect2.add(d1);

		List<Device> expect3 = new ArrayList<Device>();
		
		List<Device> res1 = serveImpl.getDevicesBySensorForUser(userId, sensorName1);
		List<Device> res2 = serveImpl.getDevicesBySensorForUser(userId, sensorName2);
		List<Device> res3 = serveImpl.getDevicesBySensorForUser(userId, sensorName3);
		
		Assert.assertTrue(res1.containsAll(expect1));
		Assert.assertTrue(res2.containsAll(expect2));
		Assert.assertTrue(res3.containsAll(expect3));
	}
}
