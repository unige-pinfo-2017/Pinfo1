package ch.unige.pinfo.overview.service;

import java.io.Serializable;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import ch.unige.pinfo.overview.dom.LiveData;

public class OverviewJsonBuilder implements Serializable {
	
	/**
	 *  The serial-id
	 */
	private static final long serialVersionUID = -8146477607381645405L;

	/**
	 * <b>buildLiveDataJson</b>
	 * <p>
	 * {@code public JsonObject buildLiveDataJson(String measure, String unit, String value)} 
	 * <p>
	 * 
	 * Build a {@code JsonObject} of live data containing the name of the measure data, 
	 * the unit of the measure and the value.
	 * 
	 * @param measure - The name of the measure
	 * @param unit - The unit of the unit.
	 * @param value - The value.
	 * @return
	 * A {@code JsonObject} of the live data.
	 */
	public JsonObject buildLiveDataJson(String measure, String unit, String value) {
		// Construit un JsonObject au format {name: measure, unit: unit, value: value}
		return Json.createObjectBuilder()
				.add("name", measure)
				.add("unit", unit)
				.add("value", value)
				.build();
	}
	
	/**
	 * <b>buildHiddenDatas</b>
	 * <p>
	 * {@code public JsonArray buildHiddenDatas(List<LiveData> hiddenDatas)}
	 * <p>
	 * 
	 * Build a {@code JsonArray} of the Hidden data. 
	 * 
	 * @param hiddenDatas - A list of the hidden data.
	 * @return
	 * A {@code JsonArray} of the hidden data.
	 */
	public JsonArray buildHiddenDatas(List<LiveData> hiddenDatas) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (LiveData hiddenData: hiddenDatas) {
			builder.add(buildHiddenData(hiddenData));
		}
		return builder.build();
	}
	
	/**
	 * <b>buildHiddenData</b>
	 * <p>
	 * {@code public JsonObject buildHiddenData(LiveData hiddenData)}
	 * <p>
	 * 
	 * Build a {@code JsonObject} of the hidden data containing the name of data. 
	 * The data is deducted from the live data.  
	 * 
	 * @param hiddenData - The live data to hide.
	 * @return
	 * a {@code JsonObject} of the hidden data.
	 */
	public JsonObject buildHiddenData(LiveData hiddenData) {
		return Json.createObjectBuilder()
				.add("name", hiddenData.getSensor().getMeasureName())
				.build();
	}
}
