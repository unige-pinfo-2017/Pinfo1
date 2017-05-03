package ch.unige.pinfo.wso2.FacadeRest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ch.unige.pinfo.wso2.TypePowerSocketSensors;
import ch.unige.pinfo.wso2.Dom.State;
import ch.unige.pinfo.wso2.Service.WSO2Service;

@Path("/wso2")
public class PowerSocketRest {
	
	@Inject 
	private WSO2Service wso2Service;

	@GET
	@Path("/PowerSocket/powerSensor/{deviceId}")
	@Produces({ "application/json" })
	public State[] getPowerSensorState(
			@PathParam("deviceId") String deviceId,
			@QueryParam("from") String from,
			@QueryParam("to") String to){
		
		State[] states = wso2Service.getPowerSensorState(deviceId, from, to, TypePowerSocketSensors.powerSensor);
		return states;
	}
	
	@GET
	@Path("/PowerSocket/statusSensor/{deviceId}")
	@Produces({ "application/json" })
	public State[] getStatusSensorState(
			@PathParam("deviceId") String deviceId,
			@QueryParam("from") String from,
			@QueryParam("to") String to ){
		
		State[] states = wso2Service.getPowerSensorState(deviceId, from, to, TypePowerSocketSensors.statusSensor);
		return states;
	}
	
	@GET
	@Path("/PowerSocket/currentSensor/{deviceId}")
	@Produces({ "application/json" })
	public State[] getCurrentSensorState(
			@PathParam("deviceId") String deviceId,
			@QueryParam("from") String from,
			@QueryParam("to") String to ){
		
		State[] states = wso2Service.getPowerSensorState(deviceId, from, to, TypePowerSocketSensors.currentSensor);
		return states;
	}
}
