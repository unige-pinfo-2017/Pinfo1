package ch.unige.pinfo.oldwso2.service;

public interface WSO2Service {
	
	void initDB();
	String getSumBySensor(String sensorName, String from, String to);
	
}
