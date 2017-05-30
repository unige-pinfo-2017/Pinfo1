package ch.unige.pinfo.wso2.rest;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;

import ch.unige.pinfo.wso2.service.ChartDataService;
import ch.unige.pinfo.wso2.service.Reading;
import ch.unige.pinfo.wso2.service.WSO2WrapperImpl;

public class ChartDataServiceTest {
	
	@InjectMocks
	ChartDataService chartDataService = new ChartDataService();
		
	@Test
	public void averageValuePerTimeSlotTest_result_values () {
		// Each timepoint has a reading occuring at the same time
		
		int n = 5;
		List<Double> expected = new ArrayList<Double>();
 		
		// Initialise function parameters
		List<Reading> readings = new ArrayList<Reading>();
		List<Instant> timePoints = new ArrayList<Instant>();
		
		
		// Mock function parameters
		Instant now = Instant.now();
		for (int i=0; i<n; i++) {	// new to old
			timePoints.add(now.minus(Duration.ofHours(i)));
		}
		for (int i=0; i<n; i++) { // new to old
			Instant timestamp = now.minus(Duration.ofHours(i));
			readings.add(new Reading(timestamp, (double) i));
		}
		
		for (int i=0; i<n; i++) {
			expected.add((double) i);
		}
		
		// Call method
		List<Reading> newReadings = chartDataService.averageValuePerTimeSlot(readings, timePoints);
		
		
		// Test
		assertEquals(getValuesOfReadings(newReadings), expected);
	}
	
	@Test
	public void averageValuePerTimeSlotTest_result_shifted_values_minus () {
		// Each timepoint has a reading occuring slightly sooner
		int n = 5;
		List<Double> expected = new ArrayList<Double>();
		
		// Initialise function parameters
		List<Reading> readings = new ArrayList<Reading>();
		List<Instant> timePoints = new ArrayList<Instant>();
		
		
		// Mock function parameters
		Instant now = Instant.now();
		for (int i=0; i<n; i++) {	// new to old
			timePoints.add(now.minus(Duration.ofHours(i)));
		}
		for (int i=0; i<n; i++) { // new to old
			Instant timestamp = now.minus(Duration.ofHours(i)).minus(Duration.ofMinutes(10));
			readings.add(new Reading(timestamp, (double) i));
		}
		
		for (int i=0; i<n-1; i++) {
			expected.add((double) i);
		}
		expected.add(expected.get(expected.size()-1));
		
		// Call method
		List<Reading> newReadings = chartDataService.averageValuePerTimeSlot(readings, timePoints);
		
		// Test
		assertEquals(getValuesOfReadings(newReadings), expected);
	}
	
	@Test 
	public void averageValuePerTimeSlotTest_result_shifted_values_plus() {
		// Each timepoint has a reading occuring slightly later
		
		int n = 5;
		List<Double> expected = new ArrayList<Double>();
		
		// Initialise function parameters
		List<Reading> readings = new ArrayList<Reading>();
		List<Instant> timePoints = new ArrayList<Instant>();
		
		
		// Mock function parameters
		Instant now = Instant.now();
		for (int i=0; i<n; i++) {	// new to old
			timePoints.add(now.minus(Duration.ofHours(i)));
		}
		for (int i=0; i<n; i++) { // new to old
			Instant timestamp = now.minus(Duration.ofHours(i)).plus(Duration.ofMinutes(10));
			readings.add(new Reading(timestamp, (double) i));
		}
		
		for (int i=0; i<n-1; i++) {
			expected.add((double) i+1);
		}
		expected.add(expected.get(expected.size()-1));
		
		// Call method
		List<Reading> newReadings = chartDataService.averageValuePerTimeSlot(readings, timePoints);
		
		// Test
		assertEquals(getValuesOfReadings(newReadings), expected);
	}
	
	@Test
	public void averageValuePerTimeSlotTest_result_shifted_values_avg() {
		// One timepoint interval has two readings occuring in it
		
		int n = 5;
		int k = 2;
		List<Double> expected = new ArrayList<Double>();
		
		// Initialise function parameters
		List<Reading> readings = new ArrayList<Reading>();
		List<Instant> timePoints = new ArrayList<Instant>();
		
		
		// Mock function parameters
		Instant now = Instant.now();
		for (int i=0; i<n; i++) {	// new to old
			timePoints.add(now.minus(Duration.ofHours(i)));
		}
		for (int i=0; i<n; i++) { // new to old
			Instant timestamp = now.minus(Duration.ofHours(i));
			readings.add(new Reading(timestamp, (double) i));
			
			if (i==k) {
				Instant additionalTimestamp = now.minus(Duration.ofHours(i)).minus(Duration.ofMinutes(10));
				readings.add(new Reading(additionalTimestamp, (double) i+1));
			}
		}
		
		for (int i=0; i<n; i++) {
			if (i == k) {
				expected.add((double) (2d*i+1)/2d);
			} else {
				expected.add((double) i);
			}
		}
		
		// Call method
		List<Reading> newReadings = chartDataService.averageValuePerTimeSlot(readings, timePoints);
		
		// Test
		assertEquals(getValuesOfReadings(newReadings), expected);
	}
	
	@Test
	public void averageValuePerTimeSlotTest_readings_all_before_timepoints() {
		// All readings are before the timepoints
		int n = 5;
		List<Double> expected = new ArrayList<Double>();
		
		// Initialise function parameters
		List<Reading> readings = new ArrayList<Reading>();
		List<Instant> timePoints = new ArrayList<Instant>();
		
		
		// Mock function parameters
		Instant now = Instant.now();
		for (int i=0; i<n; i++) {	// new to old
			timePoints.add(now.minus(Duration.ofHours(i)));
		}
		for (int i=0; i<n; i++) { // new to old
			Instant timestamp = now.minus(Duration.ofHours( ((long)n) + i));
			readings.add(new Reading(timestamp, (double) i));
		}
		
		for (int i=0; i<n; i++) {
			expected.add(0d);
		}
		
		// Call method
		List<Reading> newReadings = chartDataService.averageValuePerTimeSlot(readings, timePoints);
		
		// Test
		assertEquals(getValuesOfReadings(newReadings), expected);
	}
	
	@Test
	public void averageValuePerTimeSlotTest_readings_all_before_timepoints_except_one() {
		// All readings are before the timepoints except the first which occurs at the same time as the last timepoint
		int n = 5;
		int v = 4;
		List<Double> expected = new ArrayList<Double>();
		
		// Initialise function parameters
		List<Reading> readings = new ArrayList<Reading>();
		List<Instant> timePoints = new ArrayList<Instant>();
		
		
		// Mock function parameters
		Instant now = Instant.now();
		for (int i=0; i<n; i++) {	// new to old
			timePoints.add(now.minus(Duration.ofHours(i)));
		}
		for (int i=0; i<n; i++) { // new to old
			Instant timestamp = now.minus(Duration.ofHours( ((long) n) - 1 + i));
			readings.add(new Reading(timestamp, (double) v));
		}
		
		for (int i=0; i<n; i++) {
			expected.add((double) v);
		}
		
		// Call method
		List<Reading> newReadings = chartDataService.averageValuePerTimeSlot(readings, timePoints);
		
		// Test
		assertEquals(getValuesOfReadings(newReadings), expected);
	}
	
	@Test
	public void averageValuePerTimeSlotTest_readings_all_after_timepoints() {
		// All readings occur after the timepoints
		
		int n = 5;
		List<Double> expected = new ArrayList<Double>();
		
		// Initialise function parameters
		List<Reading> readings = new ArrayList<Reading>();
		List<Instant> timePoints = new ArrayList<Instant>();
		
		
		// Mock function parameters
		Instant now = Instant.now();
		for (int i=0; i<n; i++) {	// new to old
			timePoints.add(now.minus(Duration.ofHours(i)));
		}
		for (int i=0; i<n; i++) { // new to old
			Instant timestamp = now.plus(Duration.ofHours( ((long)n) + i));
			readings.add(new Reading(timestamp, (double) i));
		}
		
		for (int i=0; i<n; i++) {
			expected.add(0d);
		}
		
		// Call method
		List<Reading> newReadings = chartDataService.averageValuePerTimeSlot(readings, timePoints);
		
		// Test
		assertEquals(getValuesOfReadings(newReadings), expected);
	}
	
	@Test
	public void averageValuePerTimeSlotTest_readings_only_one() {
		// Only one reading occurs

		int n = 5;
		int k = 2;
		double v = 1;
		List<Double> expected = new ArrayList<Double>();
		
		// Initialise function parameters
		List<Reading> readings = new ArrayList<Reading>();
		List<Instant> timePoints = new ArrayList<Instant>();
		
		
		// Mock function parameters
		Instant now = Instant.now();
		for (int i=0; i<n; i++) {	// new to old
			timePoints.add(now.minus(Duration.ofHours(i)));
		}
		for (int i=0; i<n; i++) { // new to old
			Instant timestamp = now.plus(Duration.ofHours( ((long) n) + i));
			readings.add(new Reading(timestamp, (double) i));
		}
		readings.add(new Reading(now.minus(Duration.ofHours( ((long) k) - 1)).minus(Duration.ofMinutes(10)), (double) v));
		
		for (int i=0; i<k; i++) {
			expected.add(v);
		}
		for (int i=k; i<n; i++) {
			expected.add(0d);
		}
		
		// Call method
		List<Reading> newReadings = chartDataService.averageValuePerTimeSlot(readings, timePoints);
		
		// Test
		assertEquals(getValuesOfReadings(newReadings), expected);
	}
	
	public void printList(List<Double> output) {
		String s = "";
		for (int i=0; i<output.size(); i++) {
			s += output.get(i) + " ";
		}
		System.out.println(s);
	}
	
	public List<Double> getValuesOfReadings(List<Reading> readings) {
		List<Double> values = new ArrayList<Double>();
		for (Reading r: readings) {
			values.add(r.getValue());
		}
		return values;
	}
}
