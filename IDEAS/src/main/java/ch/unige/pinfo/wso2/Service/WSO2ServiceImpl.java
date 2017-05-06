package ch.unige.pinfo.wso2.Service;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import ch.unige.pinfo.wso2.Dom.Device;
import ch.unige.pinfo.wso2.Dom.Sensor;
import ch.unige.pinfo.wso2.Dom.TypeDevice;

public class WSO2ServiceImpl implements WSO2Service {
	
	@Inject 
	private DeviceService deviceService;
	@Inject 
	private TypeDeviceService typeDeviceService;
	@Inject 
	private SensorService sensorService;
	@Inject
	private WSO2ClientService clientService;
	
	@Override
	public void initDB() {
		Sensor s1 = new Sensor("powerSensor");
		Sensor s2 = new Sensor("statusSensor");
		Sensor s3 = new Sensor("currentSensor");
		Sensor s4 = new Sensor("temperatureSensor");
		Sensor s5 = new Sensor("lightSensor");
		Sensor s6 = new Sensor("batterySensor");
		Sensor s7 = new Sensor("brightnessSensor");
		Sensor s8 = new Sensor("colorSensor");
		
		TypeDevice td1 = new TypeDevice("PowerSocket");
		TypeDevice td2 = new TypeDevice("Beacon");
		TypeDevice td3 = new TypeDevice("Light");
		
		s1.getTypeDevices().add(td1);
		s2.getTypeDevices().add(td1);
		s3.getTypeDevices().add(td1);
		s4.getTypeDevices().add(td2);
		s5.getTypeDevices().add(td2);
		s6.getTypeDevices().add(td2);
		s7.getTypeDevices().add(td3);
		s8.getTypeDevices().add(td3);
				
		td1.getSensors().add(s1);
		td1.getSensors().add(s2);
		td1.getSensors().add(s3);

		td2.getSensors().add(s4);
		td2.getSensors().add(s5);
		td2.getSensors().add(s6);
		
		td3.getSensors().add(s7);
		td3.getSensors().add(s8);
		
		typeDeviceService.addTypeDevice(td1);
		typeDeviceService.addTypeDevice(td2);
		typeDeviceService.addTypeDevice(td3);

		
		Device d1 = new Device("id1", td1);
		Device d2 = new Device("id2", td2);
		Device d3 = new Device("id3", td3);
		Device d4 = new Device("id4", td1);
		Device d5 = new Device("id5", td2);
		Device d6 = new Device("id6", td3);
		Device d7 = new Device("id7", td1);
		Device d8 = new Device("id8", td2);
		Device d9 = new Device("id9", td3);
		
		deviceService.addDevice(d1);
		deviceService.addDevice(d2);
		deviceService.addDevice(d3);
		deviceService.addDevice(d4);
		deviceService.addDevice(d5);
		deviceService.addDevice(d6);
		deviceService.addDevice(d7);
		deviceService.addDevice(d8);
		deviceService.addDevice(d9);	
	}
	
	@Override
	public String getSumBySensor(String sensorName){
		Sensor sensor = sensorService.getSensorByName(sensorName);
		if (sensor == null)
			return "sensor null";
				
		Set<Device> devices = new HashSet<Device>();
		
		for(TypeDevice type: sensor.getTypeDevices()){
			devices.addAll(type.getDevices());
		}

		String sum =" sum : ";
		
		for(Device device: devices){
			String val = clientService.getValue(device.getDeviceId());
			sum = sum + val + " ";
		}
		return sum;
	}
	
	
}
