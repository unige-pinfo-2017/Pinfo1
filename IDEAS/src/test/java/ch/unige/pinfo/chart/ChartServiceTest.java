package ch.unige.pinfo.chart;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ChartServiceTest {
	private ChartService cs = new ChartService();
	
	@Test
	public void getLabelsLastDayTest() {
		List<String> output = cs.getLabelsLastDay();
		
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
		
		assertEquals(expected, output);
	}
	
	@Test
	public void getLabelsLastWeekTest() {
		List<String> output = cs.getLabelsLastWeek();
		
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
		
		assertEquals(expected, output);
	}
	
	@Test
	public void getLabelsLastMonthTest() {
		List<String> output = cs.getLabelsLastMonth();
		
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
		
		assertEquals(expected, output);
	}
	
}
