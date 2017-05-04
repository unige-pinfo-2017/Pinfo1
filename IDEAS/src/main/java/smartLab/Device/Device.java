package smartLab.Device;

import java.util.ArrayList;
import java.util.List;

abstract public class Device {
	private String Id;
	private String metaOwner;
	private String metaService;
	private String metaTime;
	private String metaDeviceId;
	private String version;
	private List<Sensor> listSensor;
	
	public Device(){
		this.listSensor = new ArrayList<Sensor>();
	}
	
}
