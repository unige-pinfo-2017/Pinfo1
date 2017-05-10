package ch.unige.pinfo.wso2.service;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;

import ch.unige.pinfo.wso2.rest.WSO2ClientRest;

public class WSO2WrapperImpl implements WSO2Wrapper {
	
	@Inject
	WSO2ClientRest wcr;
	
	@Override
	public double getValue(String deviceType, String deviceId,  String SensorType, String From, String To){
		JsonArray states = wcr.getStates(deviceType, deviceId, SensorType, From, To);
		
		JsonObject joFrom = (JsonObject) states.getJsonObject(0).get("values");
		String valueFrom = joFrom.get(SensorType).toString();
		
		JsonObject joTo = (JsonObject) states.getJsonObject(1).get("values");
		String valueTo = joTo.get(SensorType).toString();
		
		return Double.parseDouble(valueTo); // - Double.parseDouble(valueFrom);
	}
}
