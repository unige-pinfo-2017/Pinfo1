package ch.unige.pinfo.editmenu;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.json.JsonArray;

import ch.unige.pinfo.backend.BackEndFacade;

public class EditMenuService {

	@Inject
	BackEndFacade backEndFacade;
	
	@Inject
	EditMenuJsonBuilder editMenuJsonBuilder;
	
	public JsonArray getEditMenu(String deviceId) {
		List<String> names = getFieldNames(deviceId);
		List<String> values = getFieldValues(deviceId);
		
		return editMenuJsonBuilder.buildFields(names, values);
	}
	
	public List<String> getFieldNames(String deviceId) {
		List<String> names = new ArrayList<String>();
		String deviceType = backEndFacade.getDeviceTypeNameFromDeviceId(deviceId);
		if (deviceType.equals("PowerSocket")) {
			names.add("State");
		} else if (deviceType.equals("Light")) {
			names = addColorToFieldNames(names);
			names.add("State");
		}
		return names;
	}
	
	public List<String> getFieldValues(String deviceId) {
		List<String> values = new ArrayList<String>();
		String deviceType = backEndFacade.getDeviceTypeNameFromDeviceId(deviceId);
		if (deviceType.equals("PowerSocket")) {
			values.add(backEndFacade.getDeviceDataLive(deviceId, "statusSensor"));
		} else if (deviceType.equals("Light")) {
			values = addColorToFieldValues(deviceId, deviceType, values);
			values.add(backEndFacade.getDeviceDataLive(deviceId, "statusSensor"));
		}
		
		return values;
	}
	
	public List<String> addColorToFieldNames(List<String> names) {
		// Attentenion, cet ordre dépend de l'api.
		names.add("Hue");
		names.add("Saturation");
		names.add("Kelvin");
		return names;
	}
	
	public List<String> addColorToFieldValues(String deviceId, String deviceType ,List<String> values) {
		List<String> colorValues = backEndFacade.getDeviceDataLiveColor(deviceType, deviceId);
		for (String colorValue: colorValues) {
			values.add(colorValue);
		}
		return values;
	}
	

}
