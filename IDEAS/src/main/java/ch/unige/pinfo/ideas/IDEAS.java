package ch.unige.pinfo.ideas;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.device.dom.TypeDevice;
import ch.unige.pinfo.device.service.DeviceService;
import ch.unige.pinfo.device.service.SensorService;
import ch.unige.pinfo.device.service.TypeDeviceService;
import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.overview.service.LiveDataService;
import ch.unige.pinfo.user.dom.*;
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
	@Path("/test/{userId}")
	@Transactional
	public String test(@PathParam("userId") Long userId) {
		
		LiveData ldDel = null;
		String test = "Liste des preferences avant : ";
		for (LiveData ld : userService.getUserById(userId).getPreferences()){
			test = test + ld.getSensor().getMeasureName() + " ";
			ldDel = ld;
		}
		userService.getUserById(userId).getPreferences().remove(ldDel);
		ldDel.getUsers().remove(userService.getUserById(userId));
		
		test = test + " pref apres : ";
		
		for (LiveData ld : userService.getUserById(userId).getPreferences()){
			test = test + ld.getSensor().getMeasureName() + " ";
		}
		return test;
	}
	
	@GET
	@Path("test/role")
	@Transactional
	public String testRole() {
		String manager = userService.getUserRoleById(new Long(1));
		String basic = userService.getUserRoleById(new Long(2));
		String sysadmin = userService.getUserRoleById(new Long(4));
		return manager + " " + basic + " " + sysadmin;
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
        
        Manager user1 = new Manager();
        user1.setUsername("ideas");
        user1.setPassword("ideaspw");
        user1.setUsers(new HashSet<User>());
        user1.setDevices(new HashSet<Device>());
        
        Manager user2 = new Manager();
        user2.setUsername("Quang-Minh");
        user2.setPassword("Nguyen");
        user2.setUsers(new HashSet<User>());
        user2.setDevices(new HashSet<Device>());
        
        SysAdmin user3 = new SysAdmin();
        user3.setUsername("Luca");
        user3.setPassword("Joss");
        user3.setUsers(new HashSet<User>());
        user3.setDevices(new HashSet<Device>());

        Basic user4 = new Basic();
        user4.setUsername("Melanie");
        user4.setPassword("Glauser");
        user4.setDevices(new HashSet<Device>());
        
        Basic user5 = new Basic();
        user5.setUsername("Antoine");
        user5.setPassword("Benquet");
        user5.setDevices(new HashSet<Device>());
       
        Basic user6 = new Basic();
        user6.setUsername("Jason");
        user6.setPassword("Toko");
        user6.setDevices(new HashSet<Device>());
        
        Basic user7 = new Basic();
        user7.setUsername("Aziz");
        user7.setPassword("Ferchiou");
        user7.setDevices(new HashSet<Device>());
      
        Basic user8 = new Basic();
        user8.setUsername("Joao");
        user8.setPassword("Ramos");
        user8.setDevices(new HashSet<Device>());
      
        user1.getUsers().add(user2);
        user1.getUsers().add(user3);        
        user1.getUsers().add(user4);
        user1.getUsers().add(user5);
        user1.getUsers().add(user6);
        user1.getUsers().add(user7);
        user1.getUsers().add(user8);

        user3.getUsers().add(user2);
        user3.getUsers().add(user4);
        user3.getUsers().add(user5);
        user3.getUsers().add(user6);
        user3.getUsers().add(user7);
        user3.getUsers().add(user8);

        
		Device d1 = new Device("id1");
		d1.setType(td1);
		d1.setOwner(user1);
		devicesPowerSocket.add(d1);
		user1.getDevices().add(d1);
		
		Device d2 = new Device("id2");
		d2.setType(td2);
		d2.setOwner(user1);
		devicesBeacon.add(d2);
		user1.getDevices().add(d2);

		Device d3 = new Device("id3");
		d3.setType(td3);
		d3.setOwner(user1);
		devicesLight.add(d3);
		user1.getDevices().add(d3);
		
		Device d4 = new Device("id4");
		d4.setType(td1);
		d4.setOwner(user2);
		devicesPowerSocket.add(d4);
		user2.getDevices().add(d4);
		
		Device d5 = new Device("id5");
		d5.setType(td2);
		d5.setOwner(user3);
		devicesBeacon.add(d5);
		user3.getDevices().add(d5);

		Device d6 = new Device("id6");
		d6.setType(td3);
		d6.setOwner(user4);
		devicesLight.add(d6);
		user4.getDevices().add(d6);
		
		
		Device d7 = new Device("id7");
		d7.setType(td1);
		d7.setOwner(user5);
		devicesPowerSocket.add(d7);
		user5.getDevices().add(d7);
		
		Device d8 = new Device("id8");
		d8.setType(td2);
		d8.setOwner(user6);
		devicesBeacon.add(d8);
		user6.getDevices().add(d8);

		Device d9 = new Device("id9");
		d9.setType(td3);
		d9.setOwner(user7);
		devicesLight.add(d9);
		user7.getDevices().add(d9);
		
		Device d10 = new Device("id10");
		d10.setType(td3);
		d10.setOwner(user8);
		devicesPowerSocket.add(d10);
		user8.getDevices().add(d10);
		


		td1.setDevices(devicesPowerSocket);
		td2.setDevices(devicesBeacon);
		td3.setDevices(devicesLight);
				
		LiveData ld1 = new LiveData();
		ld1.setComputeType("Sum");
		ld1.setSensor(s1);
		ld1.setUsers(new HashSet<User>());
		
		LiveData ld2 = new LiveData();
		ld2.setComputeType("Sum");
		ld2.setSensor(s3);
		ld2.setUsers(new HashSet<User>());
		
		LiveData ld3 = new LiveData();
		ld3.setComputeType("Average");
		ld3.setSensor(s4);
		ld3.setUsers(new HashSet<User>());
		
		LiveData ld4 = new LiveData();
		ld4.setComputeType("Average");
		ld4.setSensor(s5);
		
		LiveData ld5 = new LiveData();
		ld5.setComputeType("Average");
		ld5.setSensor(s7);
		
		user1.setPreferences(new HashSet<LiveData>());
		user1.getPreferences().add(ld1);
		user1.getPreferences().add(ld2);
		user1.getPreferences().add(ld3);
		ld1.getUsers().add(user1);
		ld2.getUsers().add(user1);
		ld3.getUsers().add(user1);

		user2.setPreferences(new HashSet<LiveData>());
		user2.getPreferences().add(ld1);
		user2.getPreferences().add(ld2);
		user2.getPreferences().add(ld3);
		ld1.getUsers().add(user2);
		ld2.getUsers().add(user2);
		ld3.getUsers().add(user2);
		
		user3.setPreferences(new HashSet<LiveData>());
		user3.getPreferences().add(ld1);
		user3.getPreferences().add(ld2);
		user3.getPreferences().add(ld3);
		ld1.getUsers().add(user3);
		ld2.getUsers().add(user3);
		ld3.getUsers().add(user3);
		
		user4.setPreferences(new HashSet<LiveData>());
		user4.getPreferences().add(ld1);
		user4.getPreferences().add(ld2);
		user4.getPreferences().add(ld3);
		ld1.getUsers().add(user4);
		ld2.getUsers().add(user4);
		ld3.getUsers().add(user4);
		
		user5.setPreferences(new HashSet<LiveData>());
		user5.getPreferences().add(ld1);
		user5.getPreferences().add(ld2);
		user5.getPreferences().add(ld3);
		ld1.getUsers().add(user5);
		ld2.getUsers().add(user5);
		ld3.getUsers().add(user5);
		
		user6.setPreferences(new HashSet<LiveData>());
		user6.getPreferences().add(ld1);
		user6.getPreferences().add(ld2);
		user6.getPreferences().add(ld3);
		ld1.getUsers().add(user6);
		ld2.getUsers().add(user6);
		ld3.getUsers().add(user6);
		
		user7.setPreferences(new HashSet<LiveData>());
		user7.getPreferences().add(ld1);
		user7.getPreferences().add(ld2);
		user7.getPreferences().add(ld3);
		ld1.getUsers().add(user7);
		ld2.getUsers().add(user7);
		ld3.getUsers().add(user7);
		
		user8.setPreferences(new HashSet<LiveData>());
		user8.getPreferences().add(ld1);
		user8.getPreferences().add(ld2);
		user8.getPreferences().add(ld3);
		ld1.getUsers().add(user8);
		ld2.getUsers().add(user8);
		ld3.getUsers().add(user8);
		
		typeDeviceService.addTypeDevice(td1);
		typeDeviceService.addTypeDevice(td2);
		typeDeviceService.addTypeDevice(td3);
		
		liveDataService.addLiveData(ld1);
		liveDataService.addLiveData(ld2);
		liveDataService.addLiveData(ld3);
		liveDataService.addLiveData(ld4);
		liveDataService.addLiveData(ld5);

		return "reussi";
	}
	

}
