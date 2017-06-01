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
}
