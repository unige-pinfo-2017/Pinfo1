
package ch.unige.pinfo.wso2.service;

import java.io.Serializable;
import java.time.Instant;

public class Reading implements Serializable {
	
	/**
	 *  The serial-id
	 */
	private static final long serialVersionUID = 2945152924877569609L;
	
	private Instant timestamp;
	private Double value;
	
	/*public Reading(Instant timestamp, String value) {
		this.timestamp = timestamp;
		this.value = Double.parseDouble(value);
	}*/
	
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
