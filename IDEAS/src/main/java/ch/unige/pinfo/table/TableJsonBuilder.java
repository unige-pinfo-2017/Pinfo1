package ch.unige.pinfo.table;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class TableJsonBuilder {
	public JsonObject buildColumn(String colName) {
		// Construit un JsonObject au format {prop: colName}
		return Json.createObjectBuilder().add("prop", colName).build();
	}
	
	public JsonObject buildRow(List<String> columns, List<String> values) {
		// Construit un JsonObject au format {columns1: values1, ..., columnsN: valuesN}
		JsonObjectBuilder builder = Json.createObjectBuilder();
		for (int i=0; i<columns.size(); i++){
			builder.add(columns.get(i), values.get(i));
		}
		return builder.build();
	}
	
}
