package ch.unige.pinfo.wso2.service;

public interface WSO2Wrapper {
	
	double getValue(String deviceType, String deviceId,  String SensorType, String From, String To);
}
