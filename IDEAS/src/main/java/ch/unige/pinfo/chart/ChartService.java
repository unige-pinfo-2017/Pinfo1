package ch.unige.pinfo.chart;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;

@Stateless
public class ChartService {
	@Inject
	ChartJsonBuilder chartJsonBuilder;
	
	public JsonArray getChartData(String resource, String time) {
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
	
	public boolean isTimeValid(String time) {
		if (time.equals("day") || time.equals("week") || time.equals("month")) {
			return true;
		} else {
			return false;
		}
	}

}
