package ch.unige.pinfo.wso2.Service;


import com.google.gson.Gson;

import ch.unige.pinfo.wso2.TypeBeaconSensors;
import ch.unige.pinfo.wso2.TypeLightSensors;
import ch.unige.pinfo.wso2.TypePowerSocketSensors;
import ch.unige.pinfo.wso2.Dom.State;

public class WSO2ServiceImpl implements WSO2Service {

	@Override
	public State[] getPowerSensorState(String deviceId, String from, String to, TypePowerSocketSensors sensorType) {
		String URL = "https://<wso2 ip address>:8243/powersocketubiquiti/device/stats/";
		
		String mock = "[{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290847263,\"testSensor\":0.0,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"604604ce-7e3f-312a-8ed6-7fb8f2b32113\"},{\"values\":{\"meta_owner\":\"admin\",\"meta_deviceType\":\"powersocketubiquiti\",\"meta_time\":1493290855191,\"powerSensor\":1.98,\"_version\":\"1.0.0\",\"meta_deviceId\":\"qxoblpnqwzfp\"},\"id\":\"75bc0fb3-a1e0-35f2-970f-992650ad85c7\"}]";
		
		Gson gson = new Gson();
		State[] states = null;
		switch(sensorType){
			case powerSensor: states = gson.fromJson(mock, State[].class); break;
			case statusSensor: states = gson.fromJson(mock, State[].class); break;
			case currentSensor: states = gson.fromJson(mock, State[].class); break;
		}
		return states;
	}

	@Override
	public State[] getBeaconState(String deviceId, String from, String to, TypeBeaconSensors sensorType) {
		String URL = "https://<wso2 ip address>:8243/estimotebeacon/device/stats/";
		return null;
	}

	@Override
	public State[] getLightState(String deviceId, String from, String to, TypeLightSensors sensorType) {
		String URL = "https://<wso2 ip address>:8243/lightslifx/device/stats/";
		return null;
	}
}
