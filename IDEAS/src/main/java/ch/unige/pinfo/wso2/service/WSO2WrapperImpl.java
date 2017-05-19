package ch.unige.pinfo.wso2.service;

import java.awt.Color;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;

import ch.unige.pinfo.wso2.rest.WSO2ClientRest;

public class WSO2WrapperImpl implements WSO2Wrapper {
	
	@Inject
	WSO2ClientRest wcr;
	
	@Override
	public String getValueLive(String deviceType, String deviceId,  String SensorType){
		// Retourne la dernière valeur live
		// A remplacer quand la vraie methode sera donnee
		
		if(deviceType.equals("Light") && SensorType.equals("colorSensor")){
			//Recupere la couleur HSB la converti en RGB et la renvoit en String:
			JsonArray states = wcr.getStates(deviceType, deviceId, SensorType, "0", "0");
			JsonArray statesB = wcr.getStates(deviceType, deviceId, "brightnessSensor", "0", "0");
			
			JsonObject joTo = (JsonObject) states.getJsonObject(states.size()-1).get("values");
			JsonObject joToB = (JsonObject) statesB.getJsonObject(statesB.size()-1).get("values");
			
			JsonObject valueTo = (JsonObject) joTo.get(SensorType);
			
			String hueString = valueTo.get("hue").toString();
			String satString = valueTo.get("saturation").toString();
			String briString = joToB.get("brightnessSensor").toString();
			
			String rgb = getHSBColor(hueString, satString, briString);
			
			return rgb;
		}
		else{
			JsonArray states = wcr.getStates(deviceType, deviceId, SensorType, "0", "0");
			
			JsonObject joTo = (JsonObject) states.getJsonObject(states.size()-1).get("values");
			String valueTo = joTo.get(SensorType).toString();
			
			return valueTo; // - Double.parseDouble(valueFrom);
		}
	}

	@Override
	public String[] getValue(String deviceType, String deviceId, String SensorType, String From, String To) {
		// Retourne les valeurs entre deux intervalles
		// Mock pour l'instant
		
		JsonArray states = wcr.getStates(deviceType, deviceId, SensorType, From, To);
		String[] readings = new String[states.size()];
		for (int i=0; i<states.size(); i++) {
			readings[i] = ((JsonObject) states.getJsonObject(i).get("values")).get(SensorType).toString();
		}
		
		return readings;
	}
	
	public String setValue(String deviceType, String deviceId, String status, String state){
		
		String reponse = wcr.postStatus(deviceType, deviceId, status, state);
		return reponse;
	}
	
	/**
	 * <b>getHSBColor</b>
	 * <p>
	 * {@code private String getHSBColor(String hueS, String saturation, String brightness)}
	 * <p>
	 *
	 * Convert HSB color into hexadecimal value
	 *
	 * @param hueS - The hue value. Integer between [0..360].
	 * @param saturation - The saturation value. Double between [0..1]
	 * @param brightness - The brightness value. Double between [0..1]
	 * @return
	 * hexadecimal {@code String} format
	 */
	private String getHSBColor(String hueS, String saturation, String brightness){
		float hue = Float.parseFloat(hueS); 
		hue = hue/360f;
		float sat = Float.parseFloat(saturation);
		float bri = Float.parseFloat(brightness);
		
		int RGB = Color.HSBtoRGB(hue, sat, bri);
		int red = (RGB >> 16) & 0xFF;
		int green = (RGB >> 8) & 0xFF;
		int blue = RGB & 0xFF;
		
		String hex = String.format("#%02x%02x%02x", red, green, blue);
		
		return hex;
	}
	
}