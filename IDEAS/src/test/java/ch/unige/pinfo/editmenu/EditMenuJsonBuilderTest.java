package ch.unige.pinfo.editmenu;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.junit.Test;
import org.mockito.InjectMocks;

public class EditMenuJsonBuilderTest {
	@InjectMocks
	private EditMenuJsonBuilder emjb = new EditMenuJsonBuilder();
	
	@Test
	public void buildFieldTest() {
		String mockName = "name";
		String mockValue = "value";
		
		JsonObject output = emjb.buildField(mockName, mockValue);
		JsonObject expected = Json.createObjectBuilder()
								.add("name", mockName)
								.add("value", mockValue)
								.build();
		
		assertEquals(output, expected);
	}
	
	@Test
	public void buildFieldsTest() {
		String mockName = "name";
		String mockValue = "value";
		
		List<String> mockNames = new ArrayList<String>();
		mockNames.add(mockName);
		mockNames.add(mockName);
		
		List<String> mockValues = new ArrayList<String>();
		mockValues.add(mockValue);
		mockValues.add(mockValue);
		
		JsonArray output = emjb.buildFields(mockNames, mockValues);
		JsonArray expected = Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("name", mockName)
										.add("value", mockValue))
								.add(Json.createObjectBuilder()
										.add("name", mockName)
										.add("value", mockValue))
								.build();
		
		assertEquals(output, expected);
	}
}
