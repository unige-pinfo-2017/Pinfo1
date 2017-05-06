package ch.unige.pinfo.wso2.Service;

import java.util.List;

import javax.ejb.Local;

import ch.unige.pinfo.wso2.Dom.Device;
import ch.unige.pinfo.wso2.Dom.TypeDevice;

@Local
public interface DeviceService {
	
	void addDevice(Device device);
	void deleteDevice(Long id);
	Device getDeviceById(Long id);
	List<Device> getAllDevices();
	List<Device> getDevicesByType(TypeDevice type);
	
}
