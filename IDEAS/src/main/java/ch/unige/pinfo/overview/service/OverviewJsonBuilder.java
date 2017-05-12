package ch.unige.pinfo.overview.service;

import javax.json.Json;
import javax.json.JsonObject;

public class OverviewJsonBuilder {
	public JsonObject buildLiveDataJson(String measure, String unit, String value) {
		// Construit un JsonObject au format {name: measure, unit: unit, value: value}
		return Json.createObjectBuilder()
				.add("name", measure)
				.add("unit", unit)
				.add("value", value)
				.build();
	}
}
