package ch.unige.pinfo.wso2.rest;


import javax.inject.Inject;
import java.util.Random;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.unige.pinfo.wso2.mock.LightData;
import ch.unige.pinfo.wso2.mock.LightDataService;
import ch.unige.pinfo.wso2.mock.PowerSocketStatus;
import ch.unige.pinfo.wso2.mock.PowerSocketStatusService;

@Path("/fakeWso2")
public class FakeWSO2Server {
	/*Faking a wso2 server that has Json object
	 * here just for powerSocket and his 3 Sensors 
	 */
	
	@Inject
	PowerSocketStatusService powerSocketStatusService;
	
	@Inject
	LightDataService lightDataService;
	
	@GET
	@Path("/PowerSocket/device/stats/{deviceId}")
	@Produces({ "application/json" })
	public String getPowerSocket(
			@PathParam("deviceId") String deviceId,
			@QueryParam("from") String from,
			@QueryParam("to") String to,
			@QueryParam("sensorType") String sensorType){ 
		
		Random rand = new Random();
		String val = Double.toString((50 - 0) * rand.nextDouble());
		PowerSocketStatus pss = powerSocketStatusService.getPowerSocketStatusByDeviceId(deviceId);
		if (pss.getStatus().equals("0"))
			val = "0";
		
		if (sensorType.equals("powerSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290847263,\"powerSensor\":"+ Double.toString((50 - 0) * rand.nextDouble()) +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290855191,\"powerSensor\":"+ val +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else if(sensorType.equals("statusSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290847263,\"statusSensor\":"+ pss.getStatus() +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290855191,\"statusSensor\":"+ pss.getStatus() +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else if(sensorType.equals("currentSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290847263,\"currentSensor\":"+ Double.toString((50 - 0) * rand.nextDouble()) +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290855191,\"currentSensor\":"+ val +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
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
			@PathParam("deviceId") String deviceId,
			@QueryParam("from") String from,
			@QueryParam("to") String to,
			@QueryParam("sensorType") String sensorType){ 
		
		Random rand = new Random();
		if (sensorType.equals("temperatureSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"estimotebeacon\",\"meta_time\":1493290847263,\"temperatureSensor\":"+ Double.toString((50 - 0) * rand.nextDouble()) +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"estimotebeacon\",\"meta_time\":1493290855191,\"temperatureSensor\":"+ Double.toString((50 - 0) * rand.nextDouble()) +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else if(sensorType.equals("lightSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"estimotebeacon\",\"meta_time\":1493290847263,\"lightSensor\":"+ Double.toString((50 - 0) * rand.nextDouble()) +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"estimotebeacon\",\"meta_time\":1493290855191,\"lightSensor\":"+ Double.toString((50 - 0) * rand.nextDouble()) +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else if(sensorType.equals("batterySensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"estimotebeacon\",\"meta_time\":1493290847263,\"batterySensor\":"+ Double.toString((50 - 0) * rand.nextDouble()) +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"estimotebeacon\",\"meta_time\":1493290855191,\"batterySensor\":"+ Double.toString((50 - 0) * rand.nextDouble()) +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
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
			@PathParam("deviceId") String deviceId,
			@QueryParam("from") String from,
			@QueryParam("to") String to,
			@QueryParam("sensorType") String sensorType){ 
		
		Random rand = new Random();
		String power = Double.toString((50 - 0) * rand.nextDouble());
	
		LightData ld = lightDataService.getLightDataByDeviceId(deviceId);
		if (ld.getStatus().equals("1")) {
			ld.setBrightness(Double.toString(rand.nextDouble()));
			ld.setHue(Integer.toString(rand.nextInt(361)));
			ld.setSaturation(Double.toString(rand.nextDouble()));
			ld.setKelvin(Integer.toString(rand.nextInt(5000)+5000));
		lightDataService.updateLightData(ld);
		}
		
		if (sensorType.equals("brightnessSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290847263,\"brightnessSensor\":"+ Double.toString((50 - 0) * rand.nextDouble()) +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290855191,\"brightnessSensor\":"+ ld.getBrightness() +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else if(sensorType.equals("colorSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290847263,\"colorSensor\":{\"hue\":"+ Integer.toString(rand.nextInt(361)) +", \"saturation\":"+ Double.toString((1) * rand.nextDouble()) +", \"kelvin\": "+ Integer.toString(rand.nextInt(5000)+5000) +"},\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290855191,\"colorSensor\":{\"hue\":"+ ld.getHue() +", \"saturation\":"+ ld.getSaturation() +", \"kelvin\": "+ ld.getKelvin() +"},\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else if(sensorType.equals("powerSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290847263,\"powerSensor\":"+ Double.toString((50 - 0) * rand.nextDouble()) +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290855191,\"powerSensor\":"+ power +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else if(sensorType.equals("statusSensor")){
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290847263,\"statusSensor\":"+ ld.getStatus() +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290855191,\"statusSensor\":"+ ld.getStatus() +",\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
			return res;
		}
		else{
			String res = "[{\"Sensor type\":\"this sensor doesn't exist for Light\"}]";
			return res;
		}
	}

	@POST
	@Path("/PowerSocket/device/{deviceId}/change-status")
	@Produces(MediaType.TEXT_PLAIN)
	public Response postPowerSocket(
			@PathParam("deviceId") String deviceId,
			String state){

		PowerSocketStatus pss = powerSocketStatusService.getPowerSocketStatusByDeviceId(deviceId);
		if (state.equals("ON")){
			pss.setStatus("1");
			powerSocketStatusService.updatePowerSocketStatus(pss);
			return Response.status(200).entity("Status changed").build();
		}
		else if (state.equals("OFF")){
			pss.setStatus("0");
			powerSocketStatusService.updatePowerSocketStatus(pss);
			return Response.status(200).entity("Status changed").build();
		}
		else{
			String st = "This status doesn't exist for PowerSocket";
			return Response.status(500).entity(st).build();
		}
	}


	@POST
	@Path("/Light/device/{deviceId}/change-status")
	@Produces(MediaType.TEXT_PLAIN)
	public Response postLightStatus(
			@PathParam("deviceId") String deviceId,
			String state) {
		
		Random rand = new Random();
		LightData ld = lightDataService.getLightDataByDeviceId(deviceId);
		
		if (state.equals("ON")){
			ld.setStatus("1");
			ld.setBrightness(Double.toString(rand.nextDouble()));
			ld.setHue(Integer.toString(rand.nextInt(361)));
			ld.setSaturation(Double.toString(rand.nextDouble()));
			ld.setKelvin(Integer.toString(rand.nextInt(5000)+5000));
			lightDataService.updateLightData(ld);
			return Response.status(200).entity("Status changed").build();
		}
		else if (state.equals("OFF")){
			ld.setStatus("0");
			ld.setBrightness("0");
			ld.setHue("0");
			ld.setSaturation("0");
			ld.setKelvin("0");	
			lightDataService.updateLightData(ld);
			return Response.status(200).entity("Status changed").build();
		}
		else{
			String st = "This status doesn't exist for PowerSocket";
			return Response.status(500).entity(st).build();
		}
	}

	@POST
	@Path("/Light/device/{deviceId}/change-brightness")
	@Produces(MediaType.TEXT_PLAIN)
	public Response postLightBright(
			@PathParam("deviceId") String deviceId,
			Double state){
		
		LightData ld = lightDataService.getLightDataByDeviceId(deviceId);

		if (0 <= state && state <= 1){
			ld.setBrightness(Double.toString(state));
			lightDataService.updateLightData(ld);
			return Response.status(200).entity("Brightness changed").build();
		}
		else{
			String st = "This state of brightness doesn't exist for Light";
			return Response.status(500).entity(st).build();
		}
	}

	@POST
	@Path("/Light/device/{deviceId}/change-hue")
	@Produces(MediaType.TEXT_PLAIN)
	//modifie la couleur
	public Response postLightHue(
			@PathParam("deviceId") String deviceId,
			Double state){

		LightData ld = lightDataService.getLightDataByDeviceId(deviceId);

		if (0 <= state && state <= 360){
			ld.setHue(Double.toString(state));
			lightDataService.updateLightData(ld);
			return Response.status(200).entity("Hue changed").build();
		}
		else{
			String st = "This state of hue doesn't exist for Light";
			return Response.status(500).entity(st).build();
		}
	}

	@POST
	@Path("/Light/device/{deviceId}/change-saturation")
	@Produces(MediaType.TEXT_PLAIN)
	//modifie la saturation de la couleur
	public Response postLightSaturation(
			@PathParam("deviceId") String deviceId,
			Double state){
		
		LightData ld = lightDataService.getLightDataByDeviceId(deviceId);

		if (0 <= state && state <= 1){
			ld.setSaturation(Double.toString(state));
			lightDataService.updateLightData(ld);
			return Response.status(200).entity("Saturation changed").build();
		}
		else{
			String st = "This state of saturation doesn't exist for Light";
			return Response.status(500).entity(st).build();
		}
	}

	@POST
	@Path("/Light/device/{deviceId}/change-kelvin")
	@Produces(MediaType.TEXT_PLAIN)
	//imite la lumiere du soleil selon la journee (ex: 2200K = leve du soleil(jaune/orange) 6500: soleil de midi (blanc))
	public Response postLightKev(
			@PathParam("deviceId") String deviceId,
			Double state){

		LightData ld = lightDataService.getLightDataByDeviceId(deviceId);

		if (5000 <= state && state <= 9999){
			ld.setKelvin(Double.toString(state));
			lightDataService.updateLightData(ld);
			return Response.status(200).entity("Kelvin changed").build();
		}
		else{
			String st = "This state of kelvin doesn't exist for Light";
			return Response.status(500).entity(st).build();
		}
	}

}
