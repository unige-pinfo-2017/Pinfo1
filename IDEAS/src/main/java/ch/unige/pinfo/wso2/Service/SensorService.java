package ch.unige.pinfo.wso2.Service;

import java.util.List;

import javax.ejb.Local;

import ch.unige.pinfo.wso2.Dom.Sensor;

@Local
public interface SensorService {
	
	void addSensor(Sensor sensor);
	void deleteSensor(Long id);
	Sensor getSensorById(Long id);
	List<Sensor> getAllSensors();
	Sensor getSensorByName(String sensorName);
}
