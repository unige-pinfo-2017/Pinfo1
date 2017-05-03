package ch.unige.pinfo.wso2.Service;

import ch.unige.pinfo.wso2.TypeBeaconSensors;
import ch.unige.pinfo.wso2.TypeLightSensors;
import ch.unige.pinfo.wso2.TypePowerSocketSensors;
import ch.unige.pinfo.wso2.Dom.State;

public interface WSO2Service {

	State[] getPowerSensorState(String deviceId, String from, String to, TypePowerSocketSensors sensorType);
	State[] getBeaconState(String deviceId, String from, String to, TypeBeaconSensors sensorType);
	State[] getLightState(String deviceId, String from, String to, TypeLightSensors sensorType);

}
