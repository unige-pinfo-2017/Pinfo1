package ch.unige.pinfo.overview.service;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import ch.unige.pinfo.overview.dom.LiveData;

public class OverviewJsonBuilder {
	public JsonObject buildLiveDataJson(String measure, String unit, String value) {
		// Construit un JsonObject au format {name: measure, unit: unit, value: value}
		return Json.createObjectBuilder()
				.add("name", measure)
				.add("unit", unit)
				.add("value", value)
				.build();
	}
	
	public JsonArray buildHiddenDatas(List<LiveData> hiddenDatas) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (LiveData hiddenData: hiddenDatas) {
			builder.add(buildHiddenData(hiddenData));
		}
		return builder.build();
	}
	
	public JsonObject buildHiddenData(LiveData hiddenData) {
		return Json.createObjectBuilder()
				.add("name", hiddenData.getSensor().getMeasureName())
				.build();
	}
}
