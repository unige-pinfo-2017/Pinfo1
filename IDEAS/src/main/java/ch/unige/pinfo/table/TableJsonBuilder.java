package ch.unige.pinfo.table;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class TableJsonBuilder {
	
    /**
     * <b>buildTable</b>
     * <p>
     * {@code public JsonArray buildTable(List<String> columns, List<List<String>> values) }
     * <p>
     * 
     * Build a {@code JsonArray} of table.
     * 
     * @param columns - list of columns name
     * @param values - Values of columns
     * @return
     * A {@code JsonArray} of table.
     */
	public JsonArray buildTable(List<String> columns, List<List<String>> values) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		builder.add(buildColumns(columns));
		builder.add(buildRows(columns, values));
		return builder.build();
	}
	
	/**
     * <b>buildRow</b>
     * <p>
     * {@code public JsonObject buildRow(List<String> columns, List<String> values)}
     * <p>
     * 
     * Build a {@code JsonObject} with the content of a row. Build it with columns and values of columns.
     * 
     * @param columns - columns to pass
     * @param values - values of columns
     * @return
     * A {@code JsonObject} with row of values in columns:values format. 
     */
	public JsonObject buildRow(List<String> columns, List<String> values) {
		// Construit un JsonObject au format {columns1: values1, ..., columnsN: valuesN}
		JsonObjectBuilder builder = Json.createObjectBuilder();
		for (int i=0; i<columns.size(); i++){
			builder.add(columns.get(i), values.get(i));
		}
		return builder.build();
	}
	
    /**
     * <b>buildRows</b>
     * <p>
     * {@code public JsonArray buildRows(List<String> columns, List<List<String>> allValues)}
     * <p>
     * 
     * Build a {@code JsonArray} of rows. Build it with all columns and all values off columns.
     * 
     * @param columns - list of columns 
     * @param allValues - list of all values.
     * @return
     * A {@code JsonArray} containing rows and there values.
     */
	public JsonArray buildRows(List<String> columns, List<List<String>> allValues) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (List<String> values: allValues) {
			builder.add(buildRow(columns, values));
		}
		return builder.build();
	}
	
    /**
     * <b>buildColumns</b>
     * <p>
     * {@code public JsonArray buildColumns(List<String> columns)}
     * <p>
     * 
     * Build a {@code JsonArray} containing columns.
     * 
     * @param columns - list of columns name
     * @return
     * A {@code JsonArray} of names columns.
     */
	public JsonArray buildColumns(List<String> columns) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (String column: columns) {
			builder.add(buildColumn(column));
		}
		return builder.build();
	}
	
    /**
     * <b>buildColumn</b>
     * <p>
     * {@code public JsonObject buildColumn(String colName)}
     * 
     * Build a {@code JsonObject} containing column name
     * 
     * @param colName - The name of the column
     * @return
     * A {@code JsonObject} with column name in (key:value) prop:{@code colName} format.
     */
	public JsonObject buildColumn(String colName) {
		// Construit un JsonObject au format {prop: colName}
		return Json.createObjectBuilder().add("prop", colName).build();
	}
}
