package ch.unige.pinfo.ideas;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.device.dom.TypeDevice;
import ch.unige.pinfo.device.service.DeviceService;
import ch.unige.pinfo.device.service.SensorService;
import ch.unige.pinfo.device.service.TypeDeviceService;
import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.overview.service.LiveDataService;
import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.UserService;


@Path("/")
public class IDEAS {

	@Inject 
	private TypeDeviceService typeDeviceService;
	@Inject 
	private SensorService sensorService;
	@Inject 
	private LiveDataService liveDataService;
	@Inject 
	private DeviceService deviceService;
	@Inject 
	private UserService userService;
	
	public IDEAS() {}
	
	@GET
	//@Produces(MediaType.TEXT_HTML)
	@Path("/home")
	public String home(){
		return "Welcome to IDEAS! The application is under construction.";
	}
		
	@GET
	@Path("/init")
	@Transactional
	public String initDB() {
		Set<TypeDevice> powerSensor = new HashSet<TypeDevice>(); 
		Set<TypeDevice> statusSensor = new HashSet<TypeDevice>();  
		Set<TypeDevice> currentSensor = new HashSet<TypeDevice>();  
		Set<TypeDevice> temperatureSensor = new HashSet<TypeDevice>();  
		Set<TypeDevice> lightSensor = new HashSet<TypeDevice>();  
		Set<TypeDevice> batterySensor = new HashSet<TypeDevice>();  
		Set<TypeDevice> brightnessSensor = new HashSet<TypeDevice>();  
		Set<TypeDevice> colorSensor = new HashSet<TypeDevice>();  
	          
		Set<Sensor> PowerSocketSensors = new HashSet<Sensor>();  
		Set<Sensor> BeaconSensors = new HashSet<Sensor>();  
		Set<Sensor> LightSensors = new HashSet<Sensor>();  
		
		TypeDevice td1 = new TypeDevice("PowerSocket");
		TypeDevice td2 = new TypeDevice("Beacon");
		TypeDevice td3 = new TypeDevice("Light");
		
		powerSensor.add(td1);
		statusSensor.add(td1);
		currentSensor.add(td1);
		
		temperatureSensor.add(td2);
		lightSensor.add(td2); 
		batterySensor.add(td2);
		
		powerSensor.add(td3);
		brightnessSensor.add(td3);
		colorSensor.add(td3);

		Sensor s1 = new Sensor("powerSensor", "W", "Power");
		Sensor s2 = new Sensor("statusSensor", "On/Off", "Status");
		Sensor s3 = new Sensor("currentSensor", "A","Current");
		Sensor s4 = new Sensor("temperatureSensor", "°C", "Temperature");
		Sensor s5 = new Sensor("lightSensor", "I", "Intensity");
		Sensor s6 = new Sensor("batterySensor", "%", "Battery");
		Sensor s7 = new Sensor("brightnessSensor", "%","Brightness");
		Sensor s8 = new Sensor("colorSensor", "RGB", "Color");
		
		PowerSocketSensors.add(s1);
		PowerSocketSensors.add(s2);
		PowerSocketSensors.add(s3);
		BeaconSensors.add(s4);
		BeaconSensors.add(s5);
		BeaconSensors.add(s6);
		LightSensors.add(s7);
		LightSensors.add(s8);
		LightSensors.add(s1);
		
		td1.setSensors(PowerSocketSensors);
		td2.setSensors(BeaconSensors);
		td3.setSensors(LightSensors);

		s1.setTypeDevices(powerSensor);
		s2.setTypeDevices(statusSensor);
		s3.setTypeDevices(currentSensor);
		s4.setTypeDevices(temperatureSensor);
		s5.setTypeDevices(lightSensor);
		s6.setTypeDevices(batterySensor);
		s7.setTypeDevices(brightnessSensor);
		s8.setTypeDevices(colorSensor);
		
          
        Set<Device> devicesPowerSocket = new HashSet<Device>();
        Set<Device> devicesBeacon = new HashSet<Device>();  
        Set<Device> devicesLight = new HashSet<Device>();  

        Set<Device> devicesIdeasUser = new HashSet<Device>(); 
        User user = new User();
        user.setUsername("ideas");
        user.setPassword("ideaspw");
        
		Device d1 = new Device("id1");
		d1.setType(td1);
		d1.setOwner(user);
		devicesPowerSocket.add(d1);
		devicesIdeasUser.add(d1);
		
		Device d2 = new Device("id2");
		d2.setType(td2);
		d2.setOwner(user);
		devicesBeacon.add(d2);
		devicesIdeasUser.add(d2);

		Device d3 = new Device("id3");
		d3.setType(td3);
		d3.setOwner(user);
		devicesLight.add(d3);
		devicesIdeasUser.add(d3);

		td1.setDevices(devicesPowerSocket);
		td2.setDevices(devicesBeacon);
		td3.setDevices(devicesLight);
		
		user.setDevices(devicesIdeasUser);
		
		LiveData ld1 = new LiveData();
		ld1.setComputeType("Sum");
		ld1.setSensor(s1);
		
		LiveData ld2 = new LiveData();
		ld2.setComputeType("Sum");
		ld2.setSensor(s3);
		
		LiveData ld3 = new LiveData();
		ld3.setComputeType("Average");
		ld3.setSensor(s4);
		
		typeDeviceService.addTypeDevice(td1);
		typeDeviceService.addTypeDevice(td2);
		typeDeviceService.addTypeDevice(td3);
		liveDataService.addLiveData(ld1);
		liveDataService.addLiveData(ld2);
		liveDataService.addLiveData(ld3);

		return "reussi";
	}
	

}
