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
	@Path("/demo/columns")
	@Produces({ "application/json" })
	public JsonArray getTableDemoColumns2(){
		JsonArray columns = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("title", "Name")
						.add("name", "name"))
				.add(Json.createObjectBuilder()
						.add("title", "Role")
						.add("name", "role"))
				.build();
		return columns;
	}
	
	@GET
	@Path("/demo/data")
	@Produces({ "application/json" })
	public JsonArray getTableDemoData(){
		JsonArray data = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("name", "Antoine")
						.add("role", "User Interface"))
				.add(Json.createObjectBuilder()
						.add("name", "Aziz")
						.add("role", "User Interface"))
				.build();
		return data;
	}
	
	@GET
	@Path("/demo/columns2")
	@Produces({ "application/json" })
	public JsonArray getTableDemoColumns(){
		JsonArray columns = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("title", "Col1")
						.add("name", "col1"))
				.add(Json.createObjectBuilder()
						.add("title", "Col2")
						.add("name", "col2"))
				.build();
		return columns;
	}
	
	@GET
	@Path("/demo/data2")
	@Produces({ "application/json" })
	public JsonArray getTableDemoData2(){
		JsonArray data = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("col1", "data1")
						.add("col2", "data2"))
				.add(Json.createObjectBuilder()
						.add("col1", "data3")
						.add("col2", "data4"))
				.build();
		return data;
	}
	
	@GET
	@Path("/device/columns")
	@Produces({ "application/json" })
	public JsonArray getTableDeviceColumns(@QueryParam("type") String type){
		
		JsonArray powerSocket = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("title", "Device ID")
						.add("name", "devId"))
				.add(Json.createObjectBuilder()
						.add("title", "Power")
						.add("name", "power"))
				.add(Json.createObjectBuilder()
						.add("title", "Status")
						.add("name", "status"))
				.add(Json.createObjectBuilder()
						.add("title", "Current")
						.add("name", "current"))
				.build();
		
		JsonArray beacon = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("title", "Device ID")
						.add("name", "devId"))
				.add(Json.createObjectBuilder()
						.add("title", "Temperature")
						.add("name", "temperature"))
				.add(Json.createObjectBuilder()
						.add("title", "Light")
						.add("name", "light"))
				.add(Json.createObjectBuilder()
						.add("title", "Battery")
						.add("name", "battery"))
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
						.add("devId", "1")
						.add("power", "5.0")
						.add("status", "1.0")
						.add("current", "1.6"))
				.add(Json.createObjectBuilder()
						.add("devId", "2")
						.add("power", "4.0")
						.add("status", "1.0")
						.add("current", "1.2"))
				.build();
		
		JsonArray beacon = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("devId", "3")
						.add("temperature", "30.0")
						.add("light", "9.0")
						.add("battery", "0.3"))
				.add(Json.createObjectBuilder()
						.add("devId", "4")
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
}
