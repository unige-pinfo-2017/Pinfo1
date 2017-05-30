package ch.unige.pinfo.chart;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import ch.unige.pinfo.backend.BackEndFacade;

public class ChartServiceTest {
	@Mock
	private BackEndFacade mockBackEndFacade;
	
	@InjectMocks
	private ChartService cs = new ChartService();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	private List<String> getExpectedLastDayLabel() {
		List<String> expected = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		expected.add(sdf.format(cal.getTime()));
	
		Collections.reverse(expected);
		
		return expected;
	}
	
	private List<String> getExpectedLastWeekLabel() {
		List<String> expected = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EE");
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		
		Collections.reverse(expected);
		
		return expected;
	}
	
	private List<String> getExpectedLastMonthLabel() {
		List<String> expected = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		expected.add(sdf.format(cal.getTime()));
		
		Collections.reverse(expected);
		
		return expected;
	}
	
	private List<String> getExpectedYearLabel(int year) {
		List<String> expected = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM");
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, 0);
		expected.add(sdf.format(cal.getTime()));
		cal.set(Calendar.MONTH, 1);
		expected.add(sdf.format(cal.getTime()));
		cal.set(Calendar.MONTH, 2);
		expected.add(sdf.format(cal.getTime()));
		cal.set(Calendar.MONTH, 3);
		expected.add(sdf.format(cal.getTime()));
		cal.set(Calendar.MONTH, 4);
		expected.add(sdf.format(cal.getTime()));
		cal.set(Calendar.MONTH, 5);
		expected.add(sdf.format(cal.getTime()));
		cal.set(Calendar.MONTH, 6);
		expected.add(sdf.format(cal.getTime()));
		cal.set(Calendar.MONTH, 7);
		expected.add(sdf.format(cal.getTime()));
		cal.set(Calendar.MONTH, 8);
		expected.add(sdf.format(cal.getTime()));
		cal.set(Calendar.MONTH, 9);
		expected.add(sdf.format(cal.getTime()));
		cal.set(Calendar.MONTH, 10);
		expected.add(sdf.format(cal.getTime()));
		cal.set(Calendar.MONTH, 11);
		expected.add(sdf.format(cal.getTime()));
		
		return expected;
	}
	
	@Test
	public void getLabelsLastDayTest() {
		List<String> output = cs.getLabelsLastDay();
		List<String> expected = getExpectedLastDayLabel();
		assertEquals(expected, output);
	}
	
	@Test
	public void getLabelsLastWeekTest() {
		List<String> output = cs.getLabelsLastWeek();
		List<String> expected = getExpectedLastWeekLabel();
		assertEquals(expected, output);
	}
	
	@Test
	public void getLabelsLastMonthTest() {
		List<String> output = cs.getLabelsLastMonth();
		List<String> expected = getExpectedLastMonthLabel();
		assertEquals(expected, output);
	}
	
	@Test
	public void getLabelsYearTest() {
		int year = 2017;
		List<String> output = cs.getLabelsYear(year);
		List<String> expected = getExpectedYearLabel(year);
		assertEquals(expected, output);
	}
	
}
