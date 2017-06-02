
package ch.unige.pinfo.wso2.service;

import java.time.Instant;

public class Reading {
	private Instant timestamp;
	private Double value;
	
	public Reading(String timestampInMilli, String value) {
		this.timestamp = Instant.ofEpochMilli(Long.parseLong(timestampInMilli));
		this.value = Double.parseDouble(value);
	}
	
	public Reading(String timestampInMilli, Double value) {
		this.timestamp = Instant.ofEpochMilli(Long.parseLong(timestampInMilli));
		this.value = value;
	}
	
	public Reading(Instant timestamp, Double value) {
		this.timestamp = timestamp;
		this.value = value;
	}
	
	public String getValueInString() {
		return Double.toString(this.value);
	}
	
	public Instant getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
}
