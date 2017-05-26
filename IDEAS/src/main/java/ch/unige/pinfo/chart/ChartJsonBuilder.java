package ch.unige.pinfo.chart;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class ChartJsonBuilder {
	public JsonArray buildChartJson(String name, List<Double> values, List<String> labels) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		builder.add(buildValues(values));
		builder.add(buildLabel(name));
		builder.add(buildLabels(labels));
		return builder.build();
	}

	
	public JsonArray buildValues(List<Double> values) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (Double value: values) {
			builder.add(buildValue(value));
		}
		return builder.build();
	}
	
	public JsonObject buildValue(Double value) {
		return Json.createObjectBuilder().add("value", value).build();
	}
	
	
	public JsonArray buildLabels(List<String> labels) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (String label: labels) {
			builder.add(buildLabel(label));
		}
		return builder.build();
	}
	
	public JsonObject buildLabel(String label) {
		return Json.createObjectBuilder().add("label", label).build();
	}
}
