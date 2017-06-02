package ch.unige.pinfo.wso2.service;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ChartDataService implements Serializable {
	/**
	 *  The serial-id
	 */
	private static final long serialVersionUID = 6439313062444735257L;
	
	private final double emptyValue = -1;
	
	/**
	 * <b>averageValuePerTimeSlot</b>
	 * <p>
	 * {@code public List<Reading> averageValuePerTimeSlot(List<Reading> readings, List<Instant> timePoints)}
	 * <p>
	 * 
	 * Get the average value per slot time.
	 * 
	 * @param readings - readings
	 * @param timePoints - Times point.
	 * @return
	 * A list of {@code Reading} values .
	 */
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
	
	/**
	 * <b>findClosestNotNull</b>
	 * <p>
	 * {@code public List<Reading> findClosestNotNull(List<Reading> readings)}
	 * <p>
	 * 
	 * Find closest reading with a value
	 * 
	 * @param readings - List of readings
	 * @return
	 * A list of reading with not null values.
	 */
	public List<Reading> findClosestNotNull(List<Reading> readings) {
		// For each time point without an average reading, we copy the value of the closest time point with one
		
		for (int i=0; i<readings.size(); i++) {
			
			if (isEqualDouble(readings.get(i).getValue(), emptyValue)) {
				for (int j=i; j<readings.size(); j++) { // find closest reading with a value
					
					if (!isEqualDouble(readings.get(j).getValue(), emptyValue)) {
						readings.get(i).setValue(readings.get(j).getValue()); // copy it
						break;
					}
				}
			}
		}
		return readings;
	}
	
	/**
	 * <b>computeLastReading</b>
	 * <p>
	 * {@code public Reading computeLastReading(List<Reading> readings, Instant lastTimePoint, List<Reading> newReadings)}
	 * <p>
	 * 
	 * @param readings
	 * @param lastTimePoint
	 * @param newReadings
	 * @return
	 * The last Reading of a Reading list.
	 */
	public Reading computeLastReading(List<Reading> readings, Instant lastTimePoint, List<Reading> newReadings) {
		// If there is a reading with a timestamp equals to lastTimePoint
		for (Reading reading: readings) {
			if (reading.getTimestamp().equals(lastTimePoint)) {
				return reading;
			}
		}
		
		// Else, the lastTimePoint looks at the latest computed new reading
		//if (newReadings.get(newReadings.size()-1).getValue() != emptyValue) {
			
		if (!isEqualDouble(newReadings.get(newReadings.size()-1).getValue(), emptyValue)) {
			return newReadings.get(newReadings.size()-1);
		}
		return new Reading(lastTimePoint, 0d);
	}
	
	
	/**
	 * <b>averageReading</b>
	 * <p>
	 * {@code public Reading averageReading(List<Reading> readings)}
	 * <p>
	 * 
	 * @param readings 
	 * @return
	 * The average Readings of a reading list.
	 */
	public Reading averageReading(List<Reading> readings) {
		// Computes the average Reading of a Reading list
		
		long avgInstant = 0; // In second
		double avgValue = 0;
		int size = readings.size();
		
		if (size == 0) {
			return new Reading(Instant.ofEpochSecond(0), 0d);
		}
		
		for (Reading reading: readings) {
			avgInstant += reading.getTimestamp().getEpochSecond();
			avgValue += reading.getValue();
		}
		return new Reading(Instant.ofEpochSecond((long) (avgInstant/(double)size)), (double) avgValue/(double) size);
	}
	
	public boolean isEqualDouble(double d1, double d2) {
		double epsilon = 0.00000000001;
		return (Math.abs(d1 - d2) < epsilon);
	}
}
