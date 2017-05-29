package ch.unige.pinfo.editmenu;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class EditMenuJsonBuilder {
	public JsonObject buildField(String name, String value){
		return Json.createObjectBuilder()
				.add("name", name)
				.add("value", value)
				.build();
	}
	
	public JsonArray buildFields(List<String> names, List<String> values) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (int i=0; i<names.size(); i++) {
			builder.add(buildField(names.get(i), values.get(i)));
		}
		return builder.build();
	}
}
