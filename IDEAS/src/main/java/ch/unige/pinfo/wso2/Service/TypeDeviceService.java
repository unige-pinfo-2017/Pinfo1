package ch.unige.pinfo.wso2.Service;

import java.util.List;

import javax.ejb.Local;

import ch.unige.pinfo.wso2.Dom.TypeDevice;

@Local
public interface TypeDeviceService {
	
	void addTypeDevice(TypeDevice typeDevice);
	void deleteTypeDevice(Long id);
	TypeDevice getTypeDeviceById(Long id);
	List<TypeDevice> getAllTypeDevices();
}
