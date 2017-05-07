package ch.unige.pinfo.wso2.Rest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class WSO2ClientRestTest {

	@Test
	public void testClientTrue(){
		
		String deviceType = "PowerSocket";
		String sensorType = "powerSensor";
		String deviceId = "1";
		String from = "01.05.2017";
		String to = "07.05.2017";
		
		WSO2ClientRest cr = new WSO2ClientRest();
		
		String response = cr.getDevice(deviceType, deviceId, sensorType, from, to);
		String test = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290847263,\"powerSensor\":0.0,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290855191,\"powerSensor\":1.98,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
		
		//JsonParser parser = new JsonParser();
		//JsonObject testJ = parser.parse(test).getAsJsonObject();
		
		assertTrue(response.equals(test));
	}
	
	@Test
	public void testClientFalse(){
		
		String deviceType = "PowerSocket";
		String sensorType = "bla";
		String deviceId = "1";
		String from = "01.05.2017";
		String to = "07.05.2017";
		
		WSO2ClientRest cr = new WSO2ClientRest();
		
		String response = cr.getDevice(deviceType, deviceId, sensorType, from, to);
		String test = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290847263,\"powerSensor\":0.0,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290855191,\"powerSensor\":1.98,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			
		//JsonParser parser = new JsonParser();
		//JsonObject testJ = parser.parse(test).getAsJsonObject();
		
		assertFalse(response.equals(test));
	}
		
}
