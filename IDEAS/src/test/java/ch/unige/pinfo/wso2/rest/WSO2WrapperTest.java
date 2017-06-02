package ch.unige.pinfo.wso2.rest;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ch.unige.pinfo.wso2.service.WSO2WrapperImpl;

public class WSO2WrapperTest {
	
	// Mock of WSO2Client
	@Mock
	WSO2ClientRest mockClient;
	
	// Inject WSO2Client mock in the Wrapper
	@InjectMocks
	WSO2WrapperImpl wrapper = new WSO2WrapperImpl();
	
	// Initialize the mocks
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getValueTest() throws ParseException {
		
		// Define the tested values
		String test = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290847263,\"powerSensor\":0.0,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290855191,\"powerSensor\":1.98,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
		JsonArray testJ = (JsonArray) Json.createReader(new StringReader(test)).readArray();
		String deviceType = "PowerSocket";
		String sensorType = "powerSensor";
		String deviceId = "1";
		String from = "01.05.2017";
		String to = "07.05.2017";

		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date fromDate = format.parse(from);
		Date toDate = format.parse(to);
		
		//date to unix timeStamp to String:
		String fromString = String.valueOf(fromDate.getTime());
		String toString = String.valueOf(toDate.getTime());
		
		// mock the getStates() function of the WSO2Client
		when(mockClient.getStates(deviceType, deviceId, sensorType, fromString, toString)).thenReturn(testJ);
		
		// Calculate the result which must be tested
		String[] res = wrapper.getValue(deviceType, deviceId, sensorType, from, to);
		
		assertTrue(res[0].equals("0.0") && res[1].equals("1.98"));
	}
		
}
