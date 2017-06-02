package ch.unige.pinfo.overview.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.junit.Test;

import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.overview.dom.LiveData;

public class OverviewJsonBuilderTest {
	private OverviewJsonBuilder ojb = new OverviewJsonBuilder();
	
	@Test
	public void buildLiveDataJsonTest() {
		String measure = "measure";
		String unit = "unit";
		String value = "value";
		
		JsonObject output = ojb.buildLiveDataJson(measure, unit, value);
		JsonObject expected = Json.createObjectBuilder()
				.add("name", measure)
				.add("unit", unit)
				.add("value", value)
				.build();
		
		assertEquals(expected, output);
	}
	
	@Test
	public void buildHiddenDataTest() {
		String mockMeasure = "measure";
		Sensor mockSensor = new Sensor();
		mockSensor.setMeasureName(mockMeasure);
		LiveData mockHiddenData = new LiveData();
		mockHiddenData.setSensor(mockSensor);
		
		JsonObject output = ojb.buildHiddenData(mockHiddenData);
		JsonObject expected = Json.createObjectBuilder()
								.add("name", mockSensor.getMeasureName())
								.build();
		
		assertEquals(expected, output);
	}
	
	@Test
	public void buildHiddenDatasTest() {
		String mockMeasure = "measure";
		Sensor mockSensor = new Sensor();
		mockSensor.setMeasureName(mockMeasure);
		LiveData ld1 = new LiveData();
		LiveData ld2 = new LiveData();
		ld1.setSensor(mockSensor);
		ld2.setSensor(mockSensor);
		
		List<LiveData> mockHiddenDatas = new ArrayList<LiveData>();
		mockHiddenDatas.add(ld1);
		mockHiddenDatas.add(ld2);
		
		JsonArray output = ojb.buildHiddenDatas(mockHiddenDatas);
		
		JsonArray expected = Json.createArrayBuilder()
				.add(Json.createObjectBuilder().add("name", mockSensor.getMeasureName()))
				.add(Json.createObjectBuilder().add("name", mockSensor.getMeasureName()))
				.build();
		
		assertEquals(expected, output);
	}
}