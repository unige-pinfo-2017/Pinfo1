package ch.unige.pinfo.table;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class TableJsonBuilder {
	
	public JsonArray buildTable(List<String> columns, List<List<String>> values) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		builder.add(buildColumns(columns));
		builder.add(buildRows(columns, values));
		return builder.build();
	}
	
	public JsonObject buildRow(List<String> columns, List<String> values) {
		// Construit un JsonObject au format {columns1: values1, ..., columnsN: valuesN}
		JsonObjectBuilder builder = Json.createObjectBuilder();
		for (int i=0; i<columns.size(); i++){
			builder.add(columns.get(i), values.get(i));
		}
		return builder.build();
	}
	
	public JsonArray buildRows(List<String> columns, List<List<String>> allValues) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (List<String> values: allValues) {
			builder.add(buildRow(columns, values));
		}
		return builder.build();
	}
	
	public JsonArray buildColumns(List<String> columns) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (String column: columns) {
			builder.add(buildColumn(column));
		}
		return builder.build();
	}
	
	public JsonObject buildColumn(String colName) {
		// Construit un JsonObject au format {prop: colName}
		return Json.createObjectBuilder().add("prop", colName).build();
	}
}
