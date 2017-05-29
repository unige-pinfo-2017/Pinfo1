package ch.unige.pinfo.chart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;

import ch.unige.pinfo.backend.BackEndFacade;

@Stateless
public class ChartService {
	@Inject
	ChartJsonBuilder chartJsonBuilder;
	
	@Inject
	BackEndFacade backEndFacade;
	
	public JsonArray getChartDataMock(String resource, String time) {
		// Mock
		String name = "Last day";
		
		List<Double> values = new ArrayList<Double>();
		values.add(100d);
		values.add(110d);
		values.add(90d);
		values.add(130d);
		
		List<String> labels = new ArrayList<String>();
		labels.add("12h");
		labels.add("15h");
		labels.add("18h");
		labels.add("21h");
		
		return chartJsonBuilder.buildChartJson(name, values, labels);
	}
	
	public JsonArray getChartData(String resource, String time) {
		// Mock
		List<Double> values = backEndFacade.getLastDayData(null, null, null);
		List<String> labels = getLabelsLastDay();
		//Collections.reverse(values);
		//Collections.reverse(labels);
		return chartJsonBuilder.buildChartJson("Last day", values, labels);
	}
	
	public JsonArray getChartDataMockLastDay(String resource) {
		List<Double> values = backEndFacade.getLastDayData(null, null, null);
		List<String> labels = getLabelsLastDay();
		return chartJsonBuilder.buildChartJson("Last day", values, labels);
	}
	
	public JsonArray getChartDataMockLastWeek(String resource) {
		List<Double> values = backEndFacade.getLastWeekData(null, null, null);
		List<String> labels = getLabelsLastWeek();
		return chartJsonBuilder.buildChartJson("Last week", values, labels);
	}
	
	public JsonArray getChartDataMockLastMonth(String resource) {
		List<Double> values = backEndFacade.getLastMonthData(null, null, null);
		List<String> labels = getLabelsLastMonth();
		return chartJsonBuilder.buildChartJson("Last month", values, labels);
	}
	
	public JsonArray getChartDataYear(String resource, int year) {
		List<Double> values = backEndFacade.getDataYear(null, null, year);
		List<String> labels = getLabelsYear(year);
		return chartJsonBuilder.buildChartJson(Integer.toString(year), values, labels);
	}

	public List<String> getLabelsLastDay() {
		List<String> labels = new ArrayList<String>();
		
		Calendar cal = Calendar.getInstance();
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
	    labels.add(sdf.format(cal.getTime()));
		for (int i=0; i<23; i++) {
			cal.add(Calendar.HOUR, -1);
			labels.add(sdf.format(cal.getTime()));
		}
		Collections.reverse(labels);
		return labels;
	}
	
	public List<String> getLabelsLastWeek() {
		List<String> labels = new ArrayList<String>();
		
		Calendar cal = Calendar.getInstance();
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("EE");
	    
	    labels.add(sdf.format(cal.getTime()));
		for (int i=0; i<6; i++) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
			labels.add(sdf.format(cal.getTime()));
		}
		Collections.reverse(labels);
		return labels;
	}
	
	public List<String> getLabelsLastMonth() {
		List<String> labels = new ArrayList<String>();
		
		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
		
		labels.add(sdf.format(cal.getTime()));
		for (int i=0; i<29; i++) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
			labels.add(sdf.format(cal.getTime()));
		}
		Collections.reverse(labels);
		return labels;
	}
	
	private List<String> getLabelsYear(int year) {
		List<String> labels = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM");
		cal.set(Calendar.YEAR, year);
		for (int i=0; i<12; i++) {
			cal.set(Calendar.MONTH, i);
			labels.add(sdf.format(cal.getTime()));
		}
		return labels;
	}
	
	public boolean isTimeValid(String time) {
		/*if ("day".equals(time) || "week".equals(time) || "month".equals(time)) {
			return true;
		} else {
			return false;
		}*/
		return ("day".equals(time) || "week".equals(time) || "month".equals(time));
	}


}
