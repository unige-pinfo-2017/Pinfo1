package ch.unige.pinfo.wso2.service;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.junit.Test;

public class ReadingTest {
	@Test
	public void constructorTest_string_string() {
		String timeStamp = "1000";
		Instant timeStampInInstant = Instant.ofEpochMilli(1000);
		String value = "1";
		Double valueInDouble = 1d;
		
		Reading reading = new Reading(timeStamp, value);
		
		assertEquals(timeStampInInstant, reading.getTimestamp());
		assertEquals(valueInDouble, reading.getValue());
	}
	
	@Test
	public void constructorTest_string_double() {
		String timeStamp = "1000";
		Instant timeStampInInstant = Instant.ofEpochMilli(1000);
		Double value = 1d;
		
		Reading reading = new Reading(timeStamp, value);
		
		assertEquals(timeStampInInstant, reading.getTimestamp());
		assertEquals(value, reading.getValue());
	}
	
	@Test public void setTimeStampTest() {
		Instant timeStamp = Instant.ofEpochMilli(1000);
		Double value = 1d;
	
		Instant newTimeStamp = Instant.ofEpochMilli(2000);
		
		Reading reading = new Reading(timeStamp, value);
		reading.setTimestamp(newTimeStamp);
		
		assertEquals(newTimeStamp, reading.getTimestamp());
	}
	
	@Test 
	public void getValueInString() {
		Instant timeStamp = Instant.ofEpochMilli(1000);
		Double value = 1d;
		String valueInString = Double.toString(value);
		
		Reading reading = new Reading(timeStamp, value);
		
		assertEquals(valueInString, reading.getValueInString());
	}
}
