package ch.unige.pinfo.wso2.FacadeRest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ch.unige.pinfo.wso2.TypeBeaconSensors;
import ch.unige.pinfo.wso2.Dom.State;
import ch.unige.pinfo.wso2.Service.WSO2Service;

@Path("/wso2")
public class BeaconRest {
	@Inject 
	private WSO2Service wso2Service;

	@GET
	@Path("/Beacon/temperatureSensor/{deviceId}")
	@Produces({ "application/json" })
	public State[] getBeaconState(
			@PathParam("deviceId") String deviceId,
			@QueryParam("from") String from,
			@QueryParam("to") String to){
		
		State[] states = wso2Service.getBeaconState(deviceId, from, to, TypeBeaconSensors.temperatureSensor);
		return states;
	}
	
	@GET
	@Path("/Beacon/lightSensor/{deviceId}")
	@Produces({ "application/json" })
	public State[] getLightSensorState(
			@PathParam("deviceId") String deviceId,
			@QueryParam("from") String from,
			@QueryParam("to") String to ){
		
		State[] states = wso2Service.getBeaconState(deviceId, from, to, TypeBeaconSensors.lightSensor);
		return states;
	}
	
	@GET
	@Path("/Beacon/batterySensor/{deviceId}")
	@Produces({ "application/json" })
	public State[] getBatterySensorState(
			@PathParam("deviceId") String deviceId,
			@QueryParam("from") String from,
			@QueryParam("to") String to ){
		
		State[] states = wso2Service.getBeaconState(deviceId, from, to, TypeBeaconSensors.batterySensor);
		return states;
	}

}
