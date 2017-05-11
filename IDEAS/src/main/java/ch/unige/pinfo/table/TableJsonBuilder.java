package ch.unige.pinfo.table;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class TableJsonBuilder {
	public JsonObject buildDeviceColumns(String colName) {
		return Json.createObjectBuilder().add("prop", colName).build();
	}
	
	public JsonObject buildDeviceRows(List<String> columns, List<String> values) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		for (int i=0; i<columns.size(); i++){
			builder.add(columns.get(i), values.get(i));
		}
		return builder.build();
	}
	
}
