package ch.unige.pinfo.smartLab.device;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PSE")
public class PowerSensor extends Sensor{
	
}
