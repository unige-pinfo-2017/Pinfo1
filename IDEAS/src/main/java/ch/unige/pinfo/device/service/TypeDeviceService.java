package ch.unige.pinfo.device.service;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

import ch.unige.pinfo.device.dom.TypeDevice;

@Local
public interface TypeDeviceService extends Serializable {
	
	/**
	 * <b>addTypeDevice</b>
	 * <p>
	 * {@code void addTypeDevice(TypeDevice typeDevice)}
	 * <p>
	 * 
	 * Add a type of device in the database.
	 * 
	 * @param typeDevice - The type of device to add.
	 */
	void addTypeDevice(TypeDevice typeDevice);
	
	/**
	 * <b>deleteTypeDevice</b>
	 * <p>
	 * {@code void deleteTypeDevice(Long id)}
	 * <p>
	 * 
	 * Delete a type of device from the database.
	 * 
	 * @param id - The id of the type device to remove.
	 */
	void deleteTypeDevice(Long id);
	
	/**
	 * <b>getTypeDeviceById</b>
	 * <p>
	 * {@code TypeDevice getTypeDeviceById(Long id)}
	 * <p>
	 * 
	 * Get the type of a device specified by its id.
	 * 
	 * @param id - The id of the device 
	 * @return
	 * A type of device.
	 */
	TypeDevice getTypeDeviceById(Long id);
	
	/**
	 * <b>getAllTypeDevices</b>
	 * <p>
	 * {@code List<TypeDevice> getAllTypeDevices()}
	 * <p>
	 * 
	 * Get all type of devices.
	 * 
	 * @return
	 * A list of type devices.
	 */
	List<TypeDevice> getAllTypeDevices();
	
	/**
	 * <b>getTypeDeviceByName</b>
	 * <p>
	 * {@code TypeDevice getTypeDeviceByName(String name)}
	 * <p>
	 * 
	 * Get a type of a device by its name 
	 * 
	 * @param name - The type name 
	 * @return
	 * A type of a device.
	 */
	TypeDevice getTypeDeviceByName(String name);
}
