package ch.unige.pinfo.wso2.Service;

public interface WSO2Service {
	
	void initDB();
	String getSumBySensor(String sensorName, String from, String to);
	
}
