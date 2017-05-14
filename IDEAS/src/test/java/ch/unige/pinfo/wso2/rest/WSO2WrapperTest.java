package ch.unige.pinfo.wso2.rest;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ch.unige.pinfo.wso2.service.WSO2WrapperImpl;

public class WSO2WrapperTest {
	
	@Mock
	WSO2ClientRest mockClient;
	
	@InjectMocks
	WSO2WrapperImpl wrapper = new WSO2WrapperImpl();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testClientTrue(){
		
		String test = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290847263,\"powerSensor\":0.0,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290855191,\"powerSensor\":1.98,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
		JsonArray testJ = (JsonArray) Json.createReader(new StringReader(test)).readArray();
		
		String deviceType = "PowerSocket";
		String sensorType = "powerSensor";
		String deviceId = "1";
		String from = "01.05.2017";
		String to = "07.05.2017";
		
		when(mockClient.getStates(deviceType, deviceId, sensorType, from, to)).thenReturn(testJ);
		double res = wrapper.getValue(deviceType, deviceId, sensorType, from, to);
		
		assertTrue(res == 1.98);
	}
		
}
