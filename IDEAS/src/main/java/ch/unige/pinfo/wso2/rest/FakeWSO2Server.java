package ch.unige.pinfo.wso2.rest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonArrayBuilder;
import javax.json.JsonReader;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/fakeWso2")
public class FakeWSO2Server {
	/*Faking a wso2 server that has Json object
	 * here just for powerSocket and his 3 Sensors 
	 */

	//retourne dans un tableau de String tout les états de "from" à "to".
	@GET
	@Path("/PowerSocketTest/device/stats/{deviceId}")
	@Produces({ "application/json" })
	public JsonArray getDataPowerSocket(
			@QueryParam("from") String from, 
			@QueryParam("to") String to,
			@QueryParam("sensorType") String sensorType) throws IOException{

		//InputStream fis = new FileInputStream("c:\\powerSensor.json");
		InputStream fis = new FileInputStream("c:\\json1.json");
		JsonReader jReader = Json.createReader(fis);
		JsonArray data = jReader.readArray();
		jReader.close();
		fis.close();

		long timeStampFrom = Long.parseLong(from);
		Date fromTime = new Date(timeStampFrom);
		long timeStampTo = Long.parseLong(to);
		Date toTime = new Date(timeStampTo);

		int fromIndex = 0;
		int toIndex = 0;

		for(int i=0; i<data.size() ; i++){
			String dataTime = ((JsonObject) data.getJsonObject(i).get("values")).get("meta_time").toString();
			long timeStampTime = Long.parseLong(dataTime);
			Date time = new Date(timeStampTime);

			if( time.before(fromTime) ){
				fromIndex = i;
			}	
		}

		for(int i=0; i<data.size() ; i++){
			String dataTime = ((JsonObject) data.getJsonObject(i).get("values")).get("meta_time").toString();
			long timeStampTime = Long.parseLong(dataTime);
			Date time = new Date(timeStampTime);

			if( toTime.after(time) ){
				toIndex = i;
			}
		}

		JsonArrayBuilder builder = Json.createArrayBuilder();
		for(int j=fromIndex ; j<toIndex; j++ ){
			builder.add(data.getJsonObject(j));
		}
		JsonArray dataFromTo = builder.build();

		return dataFromTo;
	}

	
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
			String res = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290847263,\"colorSensor\":{\"hue\":100, \"saturation\":1, \"kelvin\": 5000},\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"lightslifx\",\"meta_time\":1493290855191,\"colorSensor\":{\"hue\":100, \"saturation\":1, \"kelvin\": 5000},\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
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

	//@POST
	@GET
	@Path("/PowerSocket/device/{deviceId}/change-status")
	@Produces(MediaType.TEXT_PLAIN)
	//@Consumes(MediaType.TEXT_PLAIN)
	public Response postPowerSocket(
			@QueryParam("state") String state){

		if (state.equals("ON") || state.equals("OFF")){
			String st = "status :"+state;
			return Response.status(200).entity(st).build();
		}
		else{
			String st = "This status doesn't exist for PowerSocket";
			return Response.status(500).entity(st).build();
		}
	}


	//@POST
	@GET
	@Path("/Light/device/{deviceId}/change-status")
	@Produces(MediaType.TEXT_PLAIN)
	//@Consumes(MediaType.TEXT_PLAIN)
	public Response postLightStatus(
			@QueryParam("state") String state){

		if (state.equals("ON") || state.equals("OFF")){
			String st = "status :"+state;
			return Response.status(200).entity(st).build();
		}
		else{
			String st = "This status doesn't exist for Light";
			return Response.status(500).entity(st).build();
		}
	}

	//@POST
	@GET
	@Path("/Light/device/{deviceId}/change-brightness")
	@Produces(MediaType.TEXT_PLAIN)
	//@Consumes(MediaType.TEXT_PLAIN)
	public Response postLightBright(
			@QueryParam("state") double state){

		if (0 <= state && state <= 1){
			String st = "status: "+state+" brightness";
			return Response.status(200).entity(st).build();
		}
		else{
			String st = "This status doesn't exist for Light";
			return Response.status(500).entity(st).build();
		}
	}

	//@POST
	@GET
	@Path("/Light/device/{deviceId}/change-hue")
	@Produces(MediaType.TEXT_PLAIN)
	//@Consumes(MediaType.TEXT_PLAIN)
	//modifie la couleur
	public Response postLightHue(
			@QueryParam("state") int state){

		if (0 <= state && state <= 360){
			String st = "status: "+state+" hue";
			return Response.status(200).entity(st).build();
		}
		else{
			String st = "This status doesn't exist for Light";
			return Response.status(500).entity(st).build();
		}
	}

	//@POST
	@GET
	@Path("/Light/device/{deviceId}/change-saturation")
	@Produces(MediaType.TEXT_PLAIN)
	//@Consumes(MediaType.TEXT_PLAIN)
	//modifie la saturation de la couleur
	public Response postLightSat(
			@QueryParam("state") double state){

		if (0 <= state && state <= 1){
			String st = "status: "+state+" saturation";
			return Response.status(200).entity(st).build();
		}
		else{
			String st = "This status doesn't exist for Light";
			return Response.status(500).entity(st).build();
		}
	}

	//@POST
	@GET
	@Path("/Light/device/{deviceId}/change-kelvin")
	@Produces(MediaType.TEXT_PLAIN)
	//@Consumes(MediaType.TEXT_PLAIN)
	//imite la lumiere du soleil selon la journee (ex: 2200K = leve du soleil(jaune/orange) 6500: soleil de midi (blanc))
	public Response postLightKev(
			@QueryParam("state") int state){

		if (5000 <= state && state <= 9999){
			String st = "status: "+state+" kelvin";
			return Response.status(200).entity(st).build();
		}
		else{
			String st = "This status doesn't exist for Light";
			return Response.status(500).entity(st).build();
		}
	}

}
