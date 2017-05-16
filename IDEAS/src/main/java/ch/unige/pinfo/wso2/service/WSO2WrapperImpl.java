package ch.unige.pinfo.wso2.service;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;

import ch.unige.pinfo.wso2.rest.WSO2ClientRest;

public class WSO2WrapperImpl implements WSO2Wrapper {
	
	@Inject
	WSO2ClientRest wcr;
	
	@Override
	public double getValueLive(String deviceType, String deviceId,  String SensorType){
		// Retourne la dernière valeur live
		// A remplacer quand la vraie methode sera donnee
		
		JsonArray states = wcr.getStates(deviceType, deviceId, SensorType, "0", "0");
		
		JsonObject joTo = (JsonObject) states.getJsonObject(states.size()-1).get("values");
		String valueTo = joTo.get(SensorType).toString();
		
		return Double.parseDouble(valueTo); // - Double.parseDouble(valueFrom);
	}

	@Override
	public double[] getValue(String deviceType, String deviceId, String SensorType, String From, String To) {
		// Retourne les valeurs entre deux intervalles
		// Mock pour l'instant
		
		JsonArray states = wcr.getStates(deviceType, deviceId, SensorType, From, To);
		double[] readings = new double[states.size()];
		for (int i=0; i<states.size(); i++) {
			readings[i] = Double.parseDouble(((JsonObject) states.getJsonObject(i).get("values")).get(SensorType).toString());
		}
		
		return readings;
	}
}
