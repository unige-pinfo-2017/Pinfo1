
package ch.unige.pinfo.wso2.service;

import java.time.Instant;

public class Reading {
	private Instant timestamp;
	private String value;
	
	public Reading(Instant timestamp, String value) {
		this.timestamp = timestamp;
		this.value = value;
	}
	
	public Reading(String timestampInMilli, String value) {
		this.timestamp = Instant.ofEpochMilli(Long.parseLong(timestampInMilli));
		this.value = value;
	}
	
	public Instant getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
