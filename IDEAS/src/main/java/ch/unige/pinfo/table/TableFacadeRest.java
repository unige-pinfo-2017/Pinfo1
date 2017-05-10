package ch.unige.pinfo.table;

import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/table")
public class TableFacadeRest {
	
	@GET
	@Path("/device/columns")
	@Produces({ "application/json" })
	public JsonArray getTableDeviceColumns(@QueryParam("type") String type){
		
		JsonArray powerSocket = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("prop", "deviceId"))
				.add(Json.createObjectBuilder()
						.add("prop", "power"))
				.add(Json.createObjectBuilder()
						.add("prop", "status"))
				.add(Json.createObjectBuilder()
						.add("prop", "current"))
				.build();
		
		JsonArray beacon = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("prop", "deviceId"))
				.add(Json.createObjectBuilder()
						.add("prop", "temperature"))
				.add(Json.createObjectBuilder()
						.add("prop", "light"))
				.add(Json.createObjectBuilder()
						.add("prop", "battery"))
				.build();
		
		if (type.equals("powersocket")){
			return powerSocket;
		} else if (type.equals("beacon")){
			return beacon;
		} else {
			return null;
		}
	}
	
	@GET
	@Path("/device/data")
	@Produces({ "application/json" })
	public JsonArray getTableDevice(@QueryParam("type") String type){
		
		JsonArray powerSocket = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("deviceId", "1")
						.add("power", "5.0")
						.add("status", "1.0")
						.add("current", "1.6"))
				.add(Json.createObjectBuilder()
						.add("deviceId", "2")
						.add("power", "4.0")
						.add("status", "1.0")
						.add("current", "1.2"))
				.build();
		
		JsonArray beacon = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("deviceId", "3")
						.add("temperature", "30.0")
						.add("light", "9.0")
						.add("battery", "0.3"))
				.add(Json.createObjectBuilder()
						.add("deviceId", "4")
						.add("temperature", "40.0")
						.add("light", "2.0")
						.add("battery", "0.9"))
				.build();
		
		if (type.equals("powersocket")){
			return powerSocket;
		} else if (type.equals("beacon")){
			return beacon;
		} else {
			return null;
		}
	}
	
	// public JsonObject buildColumn
}
