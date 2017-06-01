package ch.unige.pinfo.overview.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ch.unige.pinfo.backend.BackEndFacade;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.overview.dom.LiveData;


public class OverviewServiceTest {
	@Mock
	BackEndFacade mockBackEndFacade;
	
	@Mock
	OverviewJsonBuilder mockOverviewJsonBuilder;
	
	@InjectMocks
	OverviewService os = new OverviewService();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void buildLiveDataTest() {
		Long userId = 1l;
		
		Sensor sensor = new Sensor();
		sensor.setMeasureName("measure");
		sensor.setUnit("unit");
		
		LiveData liveData = new LiveData();
		liveData.setSensor(sensor);
		
		Set<LiveData> preferences = new HashSet<LiveData>();
		preferences.add(liveData);
		
		List<Double> values = new ArrayList<Double>();
		values.add(1d);
		
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("name", sensor.getMeasureName())
				.add("unit", sensor.getUnit())
				.add("value", values.get(0))
				.build();
		
		when(mockBackEndFacade.getUserPreferences(userId))
			.thenReturn(preferences);
		when(mockBackEndFacade.getLiveDatas(userId))
			.thenReturn(values);
		when(mockOverviewJsonBuilder.buildLiveDataJson(sensor.getMeasureName(), sensor.getUnit(), Double.toString(values.get(0))))
			.thenReturn(jsonObject);
		
		JsonArray output = os.buildLiveData(userId);
		
		JsonArray expected = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("name", sensor.getMeasureName())
						.add("unit", sensor.getUnit())
						.add("value", values.get(0))
						.build())
				.build();
			
		assertEquals(expected, output);
	}
	
	@Test
	public void buildHiddenDataTest() {
		Long userId = 1l;
		
		Sensor s1 = new Sensor();
		s1.setMeasureName("measureName1");
		Sensor s2 = new Sensor();
		s2.setMeasureName("measureName2");
		
		LiveData ld1 = new LiveData();
		ld1.setId(1l);
		ld1.setSensor(s1);
		
		LiveData ld2 = new LiveData();
		ld2.setId(2l);
		ld2.setSensor(s2);
		
		List<LiveData> allLiveDatas = new ArrayList<LiveData>();
		allLiveDatas.add(ld1);
		allLiveDatas.add(ld2);
		
		Set<LiveData> userPref = new HashSet<LiveData>();
		userPref.add(ld1);
		
		List<LiveData> liveDatasAfterRemoving = new ArrayList<LiveData>();
		liveDatasAfterRemoving.addAll(allLiveDatas);
		liveDatasAfterRemoving.remove(ld1);
		
		JsonArray output = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
					.add("name", ld2.getSensor().getMeasureName())
					.build())
				.build();
		
		when(mockBackEndFacade.getAllLiveDatas())
			.thenReturn(allLiveDatas);
		when(mockBackEndFacade.getUserPreferences(userId))
			.thenReturn(userPref);
		when(mockOverviewJsonBuilder.buildHiddenDatas(liveDatasAfterRemoving))
			.thenReturn(output);
		
		JsonArray expected = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("name", ld2.getSensor().getMeasureName())
						.build())
					.build();
		
		assertEquals(expected, output);
	}
	
	@Test
	public void addPreferenceTest() {
		Long userId = 1l;
		String measureName = "measure";
		
		Long prefId = 2l;
		
		when(mockBackEndFacade.getLiveDataIdFromSensorMeasureName(measureName))
			.thenReturn(prefId);
		when(mockBackEndFacade.addPreference(userId, prefId)).thenReturn(true);
		
		Response output = os.addPreference(userId, measureName);
		
		Response expected = Response.status(200).entity(measureName + " added to preferences.").build();
		
		assertEquals(expected.getEntity(), output.getEntity());
		assertEquals(expected.getStatus(), output.getStatus());
	}
	
	@Test
	public void addPreferenceTest_fail() {
		Long userId = 1l;
		String measureName = "measure";
		
		Long prefId = 2l;
		
		when(mockBackEndFacade.getLiveDataIdFromSensorMeasureName(measureName))
			.thenReturn(prefId);
		when(mockBackEndFacade.addPreference(userId, prefId)).thenReturn(false);
		
		Response output = os.removePreference(userId, measureName);
		
		Response expected = Response.status(500).entity("Removing " + measureName + " from preferences failed.").build();
		
		assertEquals(expected.getEntity(), output.getEntity());
		assertEquals(expected.getStatus(), output.getStatus());
	}
	
	@Test
	public void removePreferenceTest() {
		Long userId = 1l;
		String measureName = "measure";
		
		Long prefId = 2l;
		
		when(mockBackEndFacade.getLiveDataIdFromSensorMeasureName(measureName))
			.thenReturn(prefId);
		when(mockBackEndFacade.removePreference(userId, prefId)).thenReturn(true);
		
		Response output = os.removePreference(userId, measureName);
		
		Response expected = Response.status(200).entity(measureName + " removed from preferences.").build();
		
		assertEquals(expected.getEntity(), output.getEntity());
		assertEquals(expected.getStatus(), output.getStatus());
	}
	
	@Test
	public void removePreferenceTest_fail() {
		Long userId = 1l;
		String measureName = "measure";
		
		Long prefId = 2l;
		
		when(mockBackEndFacade.getLiveDataIdFromSensorMeasureName(measureName))
			.thenReturn(prefId);
		when(mockBackEndFacade.removePreference(userId, prefId)).thenReturn(false);
		
		Response output = os.removePreference(userId, measureName);
		
		Response expected = Response.status(500).entity("Removing " + measureName + " from preferences failed.").build();
		
		assertEquals(expected.getEntity(), output.getEntity());
		assertEquals(expected.getStatus(), output.getStatus());
	}
	
	
	@Test
	public void roundDecimalTest() {
		double value = 2.222;
		int decimalNumber = 2;
		double output = os.roundDecimal(value, decimalNumber);
		double expected = 2.22;
		assertEquals(expected, output, 0.000000000000001d);
	}
}
