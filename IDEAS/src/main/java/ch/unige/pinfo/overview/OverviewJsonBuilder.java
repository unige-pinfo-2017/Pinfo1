package ch.unige.pinfo.overview;

import javax.json.Json;
import javax.json.JsonObject;

public class OverviewJsonBuilder {
	public JsonObject buildLiveDataJson(String measure, String unit, String value) {
		return Json.createObjectBuilder()
				.add("name", measure)
				.add("unit", unit)
				.add("value", value)
				.build();
	}
}
