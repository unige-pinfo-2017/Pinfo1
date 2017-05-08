package ch.unige.pinfo.oldwso2.rest;

import javax.ws.rs.*;

@Path("/fakeWso2")
public class FakeWSO2Server {
	/*Faking a wso2 server that has Json object
	 * here just for powerSocket and his 3 Sensors 
	 */

	@GET
	@Path("/PowerSocket/device/stats/{deviceId}")
	@Produces({ "application/json" })
	public String getPowerSocket(
			@QueryParam("from") String from,
			@QueryParam("to") String to,
			@QueryParam("sensorType") String sensorType){ 

		if (sensorType.equals("powerSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290847263,\"powerSensor\":0.0,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290855191,\"powerSensor\":1.98,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else if(sensorType.equals("statusSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290847263,\"statusSensor\":0.0,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290855191,\"statusSensor\":1.0,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else if(sensorType.equals("currentSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290847263,\"currentSensor\":1.2,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290855191,\"currentSensor\":2.5,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else{
			String res = "[{\"Sensor type\":\"this sensor doesn't exist for PowerSocket\"}]";
			return res;
		}
	}

	@GET
	@Path("/Beacon/device/stats/{deviceId}")
	@Produces({ "application/json" })
	public String getBeacon(
			@QueryParam("from") String from,
			@QueryParam("to") String to,
			@QueryParam("sensorType") String sensorType){ 

		if (sensorType.equals("temperatureSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"estimotebeacon\",\"meta_time\":1493290847263,\"temperatureSensor\":20.0,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"estimotebeacon\",\"meta_time\":1493290855191,\"temperatureSensor\":23.50,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else if(sensorType.equals("lightSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"estimotebeacon\",\"meta_time\":1493290847263,\"lightSensor\":20.0,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"estimotebeacon\",\"meta_time\":1493290855191,\"lightSensor\":23.50,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else if(sensorType.equals("batterySensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"estimotebeacon\",\"meta_time\":1493290847263,\"batterySensor\":20.0,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"estimotebeacon\",\"meta_time\":1493290855191,\"batterySensor\":23.50,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else{
			String res = "[{\"Sensor type\":\"this sensor doesn't exist for Beacon\"}]";
			return res;
		}
	}
	
	@GET
	@Path("/Light/device/stats/{deviceId}")
	@Produces({ "application/json" })
	public String getLight(
			@QueryParam("from") String from,
			@QueryParam("to") String to,
			@QueryParam("sensorType") String sensorType){ 

		if (sensorType.equals("brightnessSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290847263,\"brightnessSensor\":20.0,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290855191,\"brightnessSensor\":23.50,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else if(sensorType.equals("colorSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290847263,\"colorSensor\":5000,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290855191,\"colorSensor\":5000,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else if(sensorType.equals("powerSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290847263,\"powerSensor\":20.0,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290855191,\"powerSensor\":23.50,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else{
			String res = "[{\"Sensor type\":\"this sensor doesn't exist for Light\"}]";
			return res;
		}
	}

}
