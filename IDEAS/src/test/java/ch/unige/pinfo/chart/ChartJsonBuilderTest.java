package ch.unige.pinfo.chart;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.junit.Test;
import org.mockito.InjectMocks;

public class ChartJsonBuilderTest {
	@InjectMocks
	private ChartJsonBuilder cjb = new ChartJsonBuilder();
	
	@Test
	public void buildValueTest() {
		Double mockDouble = 1.5d;
		
		JsonObject output = cjb.buildValue(mockDouble);
		JsonObject expected = Json.createObjectBuilder().add("value", mockDouble).build();
		
		assertEquals(output, expected);
	}
	
	@Test
	public void buildValuesTest() {
		Double mockDouble = 1.5d;
		
		List<Double> mockValues = new ArrayList<Double>();
		mockValues.add(mockDouble);
		mockValues.add(mockDouble);
		
		JsonArray output = cjb.buildValues(mockValues);
		JsonArray expected = Json.createArrayBuilder()
								.add(Json.createObjectBuilder().add("value", mockDouble).build())
								.add(Json.createObjectBuilder().add("value", mockDouble).build())
								.build();
		
		assertEquals(output, expected);
	}
	
	@Test
	public void buildLabelTest() {
		String mockLabel = "label";
		
		JsonObject output = cjb.buildLabel(mockLabel);
		JsonObject expected = Json.createObjectBuilder().add("label", mockLabel).build();
		
		assertEquals(output, expected);
	}
	
	@Test
	public void buildLabelsTest() {
		String mockLabel = "label";
		
		List<String> mockLabels = new ArrayList<String>();
		mockLabels.add(mockLabel);
		mockLabels.add(mockLabel);
		
		JsonArray output = cjb.buildLabels(mockLabels);
		JsonArray expected = Json.createArrayBuilder()
								.add(Json.createObjectBuilder().add("label", mockLabel).build())
								.add(Json.createObjectBuilder().add("label", mockLabel).build())
								.build();
		
		assertEquals(output, expected);
	}
}
