package ch.unige.pinfo.table;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.junit.Test;

public class TableJsonBuilderTest {
	private TableJsonBuilder tjb = new TableJsonBuilder();
	
	@Test
	public void buildColumnTest() {
		String mockCol = "col";
		
		JsonObject output = tjb.buildColumn(mockCol);
		JsonObject expected = Json.createObjectBuilder()
								.add("prop", mockCol)
								.build();
		
		assertEquals(expected, output);
	}
	
	@Test
	public void buildColumnsTest() {
		String mockCol = "col";
		
		List<String> mockCols = new ArrayList<String>();
		mockCols.add(mockCol);
		mockCols.add(mockCol);
		
		JsonArray output = tjb.buildColumns(mockCols);
		JsonArray expected = Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("prop", mockCol)
										.build())
								.add(Json.createObjectBuilder()
										.add("prop", mockCol)
										.build())
								.build();
		
		assertEquals(expected, output);
	}
	
	@Test
	public void buildRowTest() {
		String mockCol = "mock";
		String mockVal = "value";
		
		List<String> mockCols = new ArrayList<String>();
		mockCols.add(mockCol);
		mockCols.add(mockCol);
		
		List<String> mockVals = new ArrayList<String>();
		mockVals.add(mockVal);
		mockVals.add(mockVal);
		
		JsonObject output = tjb.buildRow(mockCols, mockVals);
		JsonObject expected = Json.createObjectBuilder()
								.add(mockCol, mockVal)
								.add(mockCol, mockVal)
								.build();
		
		assertEquals(expected, output);
	}
	
	@Test
	public void buildRowsTest() {
		String mockCol = "mockCol";
		String mockVal = "value";
		
		List<String> mockCols = new ArrayList<String>();
		mockCols.add(mockCol);
		mockCols.add(mockCol);
		
		List<String> mockVals = new ArrayList<String>();
		mockVals.add(mockVal);
		mockVals.add(mockVal);
		
		List<List<String>> mockAllVals = new ArrayList<List<String>>();
		mockAllVals.add(mockVals);
		mockAllVals.add(mockVals);
		
		JsonArray output = tjb.buildRows(mockCols, mockAllVals);
		JsonArray expected = Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
									.add(mockCol, mockVal)
									.add(mockCol, mockVal)
									.build())
								.add(Json.createObjectBuilder()
									.add(mockCol, mockVal)
									.add(mockCol, mockVal)
									.build())
								.build();
		
		assertEquals(expected, output);
	}
	
	@Test
	public void buildTableTest() {
		String mockCol = "mockCol";
		String mockVal = "value";
		
		List<String> mockCols = new ArrayList<String>();
		mockCols.add(mockCol);
		mockCols.add(mockCol);
		
		List<String> mockVals = new ArrayList<String>();
		mockVals.add(mockVal);
		mockVals.add(mockVal);
		
		List<List<String>> mockAllVals = new ArrayList<List<String>>();
		mockAllVals.add(mockVals);
		mockAllVals.add(mockVals);
		
		JsonArray output = tjb.buildTable(mockCols, mockAllVals);
		JsonArray expected = Json.createArrayBuilder()
								.add(Json.createArrayBuilder()
										.add(Json.createObjectBuilder()
												.add("prop", mockCol)
												.build())
										.add(Json.createObjectBuilder()
												.add("prop", mockCol)
												.build())
										.build())
								.add(Json.createArrayBuilder()
											.add(Json.createObjectBuilder()
													.add(mockCol, mockVal)
													.add(mockCol, mockVal)
													.build())
											.add(Json.createObjectBuilder()
													.add(mockCol, mockVal)
													.add(mockCol, mockVal)
													.build())
										.build())
								.build();
		
		assertEquals(expected, output);
	}
	
}