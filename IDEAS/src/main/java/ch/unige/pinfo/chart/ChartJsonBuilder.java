package ch.unige.pinfo.chart;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class ChartJsonBuilder {
	
	/**
     * <b>buildChartJson</b>
     * <p>
     * {@code public JsonArray buildChartJson(String name, List<Double> values, List<String> labels)}
     * <p>
     * 
     * Build a {@code JsonArray} of chart containing labels and their values: <br>
     * [[{value: x}, {value: y}, {value: z}], {name: foo}, [{label: a}, {label: b}, {label: c}]]
     *
     * @param name - The name of label
     * @param values - The value of the label
     * @param labels - labels.
     * @return
     * A {@code JsonArray} of chart data.
     */
	public JsonArray buildChartJson(String name, List<Double> values, List<String> labels) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		builder.add(buildValues(values));
		builder.add(buildLabel(name));
		builder.add(buildLabels(labels));
		return builder.build();
	}

	/**
     * <b>buildValues</b>
     * <p>
     * {@code public JsonArray buildValues(List<Double> values)}
     * <p>
     * 
     * Build a {@code JsonArray} of values: <br>
     * [{value: x}, {value: y}, {value: z}]
     * 
     * @param values - List of values.
     * @return
     * A {@code JsonArray} of values.
     */
	public JsonArray buildValues(List<Double> values) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (Double value: values) {
			builder.add(buildValue(value));
		}
		return builder.build();
	}
	
	/**
     * <b>buildValue</b>
     * <p>
     * {@code public JsonObject buildValue(Double value)}
     * <p>
     * 
     * Build {@code JsonObject} of value: <br>
     * {value: x}
     * 
     * @param value - The value. 
     * @return
     * A {@code JsonObject} of value:{@code value}; (name:value) format.
     */
	public JsonObject buildValue(Double value) {
		return Json.createObjectBuilder().add("value", value).build();
	}
	
	/**
     * <b>buildLabels</b>
     * <p>
     * {@code public JsonArray buildLabels(List<String> labels)}
     * <p>
     * 
     * Build a {@code JsonArray} of labels: <br>
     * [{label: a}, {label: b}, {label: c}]
     * 
     * @param labels - The list of labels.
     * @return
     * A {@code JsonArray} of the labels.
     */
	public JsonArray buildLabels(List<String> labels) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (String label: labels) {
			builder.add(buildLabel(label));
		}
		return builder.build();
	}
	
	/**
     * <b>buildLabel</b>
     * <p>
     * {@code public JsonObject buildLabel(String label)}
     * <p>
     * 
     * Build {@code JsonObject} of label: <br> 
     * {label: x}
     * 
     * @param label - label 
     * @return
     * A {@code JsonObject} of label:{@code label}; (name:value) format.
     */
	public JsonObject buildLabel(String label) {
		return Json.createObjectBuilder().add("label", label).build();
	}
}
