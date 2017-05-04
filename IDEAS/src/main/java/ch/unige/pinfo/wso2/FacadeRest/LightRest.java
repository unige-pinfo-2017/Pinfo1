package ch.unige.pinfo.wso2.FacadeRest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ch.unige.pinfo.wso2.TypeLightSensors;
import ch.unige.pinfo.wso2.Dom.State;
import ch.unige.pinfo.wso2.Service.WSO2Service;

@Path("/wso2")
public class LightRest {
	@Inject 
	private WSO2Service wso2Service;

	@GET
	@Path("/Light/brightnessSensor/{deviceId}")
	@Produces({ "application/json" })
	public State[] getLightState(
			@PathParam("deviceId") String deviceId,
			@QueryParam("from") String from,
			@QueryParam("to") String to){
		
		State[] states = wso2Service.getLightState(deviceId, from, to, TypeLightSensors.brightnessSensor);
		return states;
	}
	
	@GET
	@Path("/Light/colorSensor/{deviceId}")
	@Produces({ "application/json" })
	public State[] getColorSensorState(
			@PathParam("deviceId") String deviceId,
			@QueryParam("from") String from,
			@QueryParam("to") String to ){
		
		State[] states = wso2Service.getLightState(deviceId, from, to, TypeLightSensors.colorSensor);
		return states;
	}
	
	@GET
	@Path("/Light/powerSensor/{deviceId}")
	@Produces({ "application/json" })
	public State[] getPowerSensorState(
			@PathParam("deviceId") String deviceId,
			@QueryParam("from") String from,
			@QueryParam("to") String to ){
		
		State[] states = wso2Service.getLightState(deviceId, from, to, TypeLightSensors.powerSensor);
		return states;
	}

}
