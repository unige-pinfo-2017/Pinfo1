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
	
	/**
	 * <b>getChartData</b>
	 * <p>
	 * {@code public JsonArray getChartData(String resource, String time)}
	 * <p>
	 * 
	 * Get chart data for a resource and a specific time.
	 * 
	 * @param resource - The resource we want
	 * @param time - The specific time
	 * @return
	 * A {@code JsonArray} of chart with values and labels of the given time.
	 */
	
	public JsonArray getChartDataLastDay(String resource) {
		List<Double> values = backEndFacade.getLastDayData(null, null, null); // Mock
		List<String> labels = getLabelsLastDay();
		return chartJsonBuilder.buildChartJson(resource + ": Last day", values, labels);
	}
	
	public JsonArray getChartDataLastWeek(String resource) {
		List<Double> values = backEndFacade.getLastWeekData(null, null, null); // Mock
		List<String> labels = getLabelsLastWeek();
		return chartJsonBuilder.buildChartJson(resource + ": Last week", values, labels);
	}
	
	public JsonArray getChartDataLastMonth(String resource) {
		List<Double> values = backEndFacade.getLastMonthData(null, null, null); // Mock
		List<String> labels = getLabelsLastMonth();
		return chartJsonBuilder.buildChartJson(resource + ": Last month", values, labels);
	}
	
	/**
	 * <b>getChartDataYear</b>
	 * <p>
	 * {@code public JsonArray getChartDataYear(String resource, int year)}
	 * <p>
	 * 
	 * Get chart data of a resource for the specify year.
	 * 
	 * @param resource - The resource
	 * @param year - The year.
	 * @return
	 * A {@code JsonArray} of chart with values and labels of the given year.
	 */
	public JsonArray getChartDataYear(String resource, int year) {
		List<Double> values = backEndFacade.getDataYear(null, null, year);
		List<String> labels = getLabelsYear(year);
		return chartJsonBuilder.buildChartJson(resource + ": " + Integer.toString(year), values, labels);
	}

	/**
	 * <b>getLabelsLastDay</b>
	 * <p>
	 * {@code public List<String> getLabelsLastDay()}
	 * <p>
	 * 
	 * Get the labels of the last day with format HH:mm:ss. 24 hours past hours
	 * 
	 * @return
	 * A list of label's names in {@code String} format.
	 */
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
	
	/**
	 * <b>getLabelsLastWeek</b>
	 * <p>
	 * {@code public List<String> getLabelsLastWeek()}
	 * <p>
	 * 
	 * Get the labels of the last week. 6 previous days + today
	 * 
	 * @return
	 * A list of label's names in {@code String} format.
	 */
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
	
	/**
	 * <b>getLabelsLastMonth</b>
	 * <p>
	 * {@code public List<String> getLabelsLastMonth()}
	 * <p>
	 * 
	 * Get the labels of the last month. 29 previous days + today
	 * 
	 * @return
	 * A list of label's names in {@code String} format.
	 */
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
	
	/**
	 * <b>getLabelsLastYear</b>
	 * <p>
	 * {@code public List<String> getLabelsLastYear()}
	 * <p>
	 * 
	 * Get the labels of the last year.
	 * 
	 * @return
	 * A list of label's names in {@code String} format.
	 */
	public List<String> getLabelsYear(int year) {
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
	
}
