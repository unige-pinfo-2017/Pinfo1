package ch.unige.pinfo.wso2.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ChartDataService {
	private final double emptyValue = -1;
	
	public List<Reading> averageValuePerTimeSlot(List<Reading> readings, List<Instant> timePoints) {
		// Interpolate the readings occuring at each timepoint in 'timpoints' based on 'readings'.
		// The returned list is in new-old order
		
		List<Reading> newReadings = new ArrayList<Reading>();
		
		// Iterate through each timepoint interval
		for (int i=0; i<timePoints.size()-1; i++) {
			Instant curr = timePoints.get(i);
			Instant before = timePoints.get(i+1);
			
			// Compute the list of Reading which occured in the interval
			List<Reading> toBeAveraged = new ArrayList<Reading>();
			for (int j=0; j<readings.size(); j++) {
				Reading reading = readings.get(j);
				if (reading.getTimestamp().isBefore(before)) {
					break;
				}
				
				if ((reading.getTimestamp().isBefore(curr) || reading.getTimestamp().equals(curr)) && reading.getTimestamp().isAfter(before)) {
					toBeAveraged.add(reading);
				}
			}
			
			if (toBeAveraged.isEmpty()) { // If the list is empty, then we used the default value which will be treated afterwards
				newReadings.add(new Reading(curr, emptyValue));
			} else { // Otherwise, we compute the average reading of the interval and add it
				newReadings.add(averageReading(toBeAveraged));
			}
		}
		
		// Since the average reading will be given to the 'curr' time point and not 'before', we need to compute the reading for the last time point
		Instant lastTimePoint = timePoints.get(timePoints.size()-1);
		newReadings.add(computeLastReading(readings, lastTimePoint, newReadings));
		
		// Finally, we treat each interval where no readings occured
		newReadings = findClosestNotNull(newReadings);
		
		return newReadings;
	}
	
	public List<Reading> findClosestNotNull(List<Reading> readings) {
		// For each time point without an average reading, we copy the value of the closest time point with one
		
		for (int i=0; i<readings.size(); i++) {
			if (readings.get(i).getValue() == emptyValue) { // if reading didn't get any value
				for (int j=i; j<readings.size(); j++) { // find closest reading with a value
					if (readings.get(j).getValue() != emptyValue) { 
						readings.get(i).setValue(readings.get(j).getValue()); // copy it
						break;
					}
				}
			}
		}
		return readings;
	}
	
	public Reading computeLastReading(List<Reading> readings, Instant lastTimePoint, List<Reading> newReadings) {
		// If there is a reading with a timestamp equals to lastTimePoint
		for (Reading reading: readings) {
			if (reading.getTimestamp().equals(lastTimePoint)) {
				return reading;
			}
		}
		
		// Else, the lastTimePoint looks at the latest computed new reading
		if (newReadings.get(newReadings.size()-1).getValue() != emptyValue) {
			return newReadings.get(newReadings.size()-1);
		}
		return new Reading(lastTimePoint, 0d);
	}
	
	public Reading averageReading(List<Reading> readings) {
		// Computes the average Reading of a Reading list
		
		long avgInstant = 0; // In second
		double avgValue = 0;
		double size = (double) readings.size();
		
		if (size == 0) {
			return new Reading(Instant.ofEpochSecond(0), 0d);
		}
		
		for (Reading reading: readings) {
			avgInstant += reading.getTimestamp().getEpochSecond();
			avgValue += reading.getValue();
		}
		return new Reading(Instant.ofEpochSecond((long) (avgInstant/size)), (double) avgValue/size);
	}
	
	/*public List<Double> computeClosestReadings(List<Reading> readings, List<Instant> timePoints) {
		List<Double> res = new ArrayList<Double>();
		for (int i=0; i<timePoints.size(); i++) {
			res.add(computeClosestReading(readings, timePoints.get(i)));
		}
		return res;
	}
	
	public double computeClosestReading(List<Reading> readings, Instant timePoint) {
		for (Reading reading: readings) {
			if (reading.getTimestamp().isBefore(timePoint)) {
				return reading.getValue();
			}
		}
		return 0;
	}*/
}
