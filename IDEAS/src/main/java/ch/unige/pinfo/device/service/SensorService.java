package ch.unige.pinfo.device.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import ch.unige.pinfo.device.dom.Sensor;

@Local
public interface SensorService extends Serializable{
	
	/**
	 * <b>addSensor</b>
	 * <p>
	 * {@code void addSensor(Sensor sensor)}
	 * <p>
	 * 
	 * Add a sensor in the database.
	 *  
	 * @param sensor - The sensor to add
	 */
	void addSensor(Sensor sensor);
	
	/**
	 * <b>deleteSensor</b>
	 * <p>
	 * {@code void deleteSensor(Long id)}
	 * <p>
	 * 
	 * Delete sensor from the database
	 * 
	 * @param id - The id of the sensor to remove 
	 */
	void deleteSensor(Long id);
	
	/**
	 * <b>getSensorById</b>
	 * <p>
	 * {@code Sensor getSensorById(Long id)}
	 * <p>
	 * 
	 * Get a sensor specified by its Id.
	 * 
	 * @param id - The id of the sensor.
	 * @return
	 * A sensor.
	 */
	Sensor getSensorById(Long id);
	
	/**
	 * <b>getAllSensors</b>
	 * <p>
	 * {@code List<Sensor> getAllSensors()}
	 * 
	 * Get all sensors.
	 * 
	 * @return
	 * A list of sensors.
	 */
	List<Sensor> getAllSensors();
	
	/**
	 * <b>getSensorByName</b>
	 * <p>
	 * {@code Sensor getSensorByName(String sensorName)}
	 * <p>
	 * 
	 * Get a sensor by its type.
	 * 
	 * @param sensorName - The type of the sensor
	 * @return
	 * A sensor.
	 */
	Sensor getSensorByName(String sensorName);
}
