package ch.unige.pinfo.device.service;

import java.util.List;

import javax.ejb.Local;

import ch.unige.pinfo.device.dom.Sensor;

@Local
public interface SensorService {
	
	void addSensor(Sensor sensor);
	void deleteSensor(Long id);
	Sensor getSensorById(Long id);
	List<Sensor> getAllSensors();
	Sensor getSensorByName(String sensorName);
}
