package ch.unige.pinfo.editmenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.core.Response;

import ch.unige.pinfo.backend.BackEndFacade;

@Stateless
public class EditMenuService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2140036917895085472L;

	@Inject
	BackEndFacade backEndFacade;
	
	@Inject
	EditMenuJsonBuilder editMenuJsonBuilder;
	
	/**
     * <b>changeDevice</b>
     * <p>
     * {@code public Response changeDevice(String deviceId, String resource, String state) }
     * <p>
     * 
     * Change the state of a device's resource.  
     * 
     * @param deviceId - The device's id.
     * @param resource - The resource name to change the state.
     * @param state - The new state.
     * @return
     * A {@code Response}: 200 if state is changed, 500 otherwise.
     */
	public Response changeDevice(String deviceId, String resource, String state) {
		return backEndFacade.changeDevice(deviceId,  resource, state);
	}
	
	/**
     * <b>getEditMenu</b>
     * <p>
     * {@code public JsonArray getEditMenu(String deviceId)}
     * <p>
     * 
     * Get the edit menu for a device.
     *  
     * @param deviceId - The device's id
     * @return
     * A {@code JsonArray} of field with names and values.
     */
	public JsonArray getEditMenu(String deviceId) {
		List<String> names = getFieldNames(deviceId);
		List<String> values = getFieldValues(deviceId);
		
		return editMenuJsonBuilder.buildFields(names, values);
	}
	
	 /**
     * <b>getFieldNames</b>
     * <p>
     * {@code public List<String> getFieldNames(String deviceId)}
     * <p>
     * 
     * Get the names of fields for a device.
     * 
     * @param deviceId - The device's name
     * @return
     * A list of names in {@code String} format.
     */
	public List<String> getFieldNames(String deviceId) {
		List<String> names = new ArrayList<String>();
		String deviceType = backEndFacade.getDeviceTypeNameFromDeviceId(deviceId);
		if ("PowerSocket".equals(deviceType)) {
			names.add("State");
		} else if ("Light".equals(deviceType)) {
			names = addColorToFieldNames(names);
			//names.add("State");
		}
		return names;
	}
	
	/**
     * <b>getFieldValues</b>
     * <p>
     * {@code public List<String> getFieldValues(String deviceId)}
     * <p>
     * 
     * Get the values of fields for a device.
     * 
     * @param deviceId - The device's id.
     * @return
     * A list of values in {@code String} format.
     */
	public List<String> getFieldValues(String deviceId) {
		List<String> values = new ArrayList<String>();
		String deviceType = backEndFacade.getDeviceTypeNameFromDeviceId(deviceId);
		if ("PowerSocket".equals(deviceType)) {
			values.add(backEndFacade.getDeviceDataLive(deviceId, "statusSensor"));
		} else if ("Light".equals(deviceType)) {
			values = addColorToFieldValues(deviceId, deviceType, values);
			//values.add(backEndFacade.getDeviceDataLive(deviceId, "statusSensor"));
		}
		
		return values;
	}
	
    /**
     * <b>addColorToFieldNames</b>
     * <p>
     * {@code public List<String> addColorToFieldNames(List<String> names)}
     * <p>
     * 
     * Add the color data name to the field.
     * 
     * @param names - name of filed.
     * @return
     * A list with name of fields in {@code String} format.
     */
	public List<String> addColorToFieldNames(List<String> names) {
		// Warning, the order depends on the wso2 server api
		names.add("Hue");
		names.add("Saturation");
		names.add("Kelvin");
		return names;
	}
	
    /**
     * <b>addColorToFieldValues</b>
     * <p>
     * {@code public List<String> addColorToFieldValues(String deviceId, String deviceType ,List<String> values)}
     * <p>
     * 
     * Add the color values to a field for a device.
     * 
     * @param deviceId - The device's id
     * @param deviceType - The device's type.
     * @param values - the color values (hue, saturation, kelvin). 
     * @return
     * A list of the color component (hue, saturation, kelvin) in {@code String} format.
     */
	public List<String> addColorToFieldValues(String deviceId, String deviceType ,List<String> values) {
		List<String> colorValues = backEndFacade.getDeviceDataLiveColor(deviceType, deviceId);
		for (String colorValue: colorValues) {
			values.add(colorValue);
		}
		return values;
	}

}
