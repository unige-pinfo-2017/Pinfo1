package ch.unige.pinfo.editmenu;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import ch.unige.pinfo.backend.BackEndFacade;

public class EditMenuServiceTest {
	@Mock
	BackEndFacade mockBackEndFacade;
	
	@InjectMocks
	EditMenuService ems = new EditMenuService();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addColorToFieldsNamesTest() {
		List<String> names = new ArrayList<String>();
		names.add("name");
		
		List<String> output = ems.addColorToFieldNames(names);
		
		List<String> expected = new ArrayList<String>();
		expected.add("name");
		expected.add("Hue");
		expected.add("Saturation");
		expected.add("Kelvin");
		
		assertEquals(expected, output);
	}
	
	@Test
	public void addColorToFieldValuesTest() {
		String deviceId = "id1";
		String deviceType = "Light";
		List<String> values = new ArrayList<String>();
		values.add("value");
		
		List<String> colorValues = new ArrayList<String>();
		colorValues.add("360");
		colorValues.add("1");
		colorValues.add("9999");
		
		when(mockBackEndFacade.getDeviceDataLiveColor(deviceType, deviceId))
			.thenReturn(colorValues);
		
		List<String> expected = new ArrayList<String>();
		expected.add("value");
		expected.add("360");
		expected.add("1");
		expected.add("9999");
		
		List<String> output = ems.addColorToFieldValues(deviceId, deviceType, values);
		
		assertEquals(expected, output);
		
	}
}
