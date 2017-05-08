package ch.unige.pinfo.device.service;

import java.util.List;

import javax.ejb.Local;

import ch.unige.pinfo.device.dom.TypeDevice;

@Local
public interface TypeDeviceService {
	
	void addTypeDevice(TypeDevice typeDevice);
	void deleteTypeDevice(Long id);
	TypeDevice getTypeDeviceById(Long id);
	List<TypeDevice> getAllTypeDevices();
}
