package ch.unige.pinfo.editmenu;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class EditMenuJsonBuilder {
	
    /**
     * <b>buildField</b>
     * <p>
     * {@code public JsonObject buildField(String name, String value)}
     * <p>
     * 
     * Build a {@code JsonObject} of field containing the name and the value.
     * 
     * @param name - The name of the field.
     * @param value - The value of the field.
     * @return
     * A {@code JsonObject} of field; name:{@code name} and value:{@code value}.
     */
	public JsonObject buildField(String name, String value){
		return Json.createObjectBuilder()
				.add("name", name)
				.add("value", value)
				.build();
	}
	
	/**
     * <b>buildFields</b>
     * <p>
     * {@code public JsonArray buildFields(List<String> names, List<String> values)}
     * <p>
     * 
     * Build a {@code JsonArray} of fields.
     * 
     * @param names - A list of field's name.
     * @param values - A list of field's value.
     * @return
     * A {@code JsonArray} of fields.
     */
	public JsonArray buildFields(List<String> names, List<String> values) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (int i=0; i<names.size(); i++) {
			builder.add(buildField(names.get(i), values.get(i)));
		}
		return builder.build();
	}
}
