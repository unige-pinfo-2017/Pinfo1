package ch.unige.pinfo.wso2.service;

public interface WSO2Wrapper {
	
	String getValueLive(String deviceType, String deviceId,  String SensorType);
	String[] getValue(String deviceType, String deviceId,  String SensorType, String From, String To);
}
