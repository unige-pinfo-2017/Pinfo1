package ch.unige.pinfo.wso2.Dom;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table( name = "SENSORS")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "ID")
	private Long id;
    
	@Column( name = "SENSOR_NAME", nullable = false ) 
	@Size(min = 3, max = 40)
	private String name;
	
    @ManyToMany(fetch = FetchType.EAGER, mappedBy="sensors")
    private Set<TypeDevice> typeDevices = new HashSet<>();
    
    public Sensor(){}
    
    public Sensor(String name){
    	this.name = name;
    }
    
	@Override
	public boolean equals(Object obj){
		Sensor sensor = (Sensor) obj;
		if (sensor == null)
			return false;
		return (this.getName().equals(sensor.name));
	}

	@Override
	public int hashCode(){
        int hash = 1;
        hash = hash * 31 + name.hashCode();
        return hash;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TypeDevice> getTypeDevices() {
		return typeDevices;
	}

	public void setTypeDevices(Set<TypeDevice> typeDevices) {
		this.typeDevices = typeDevices;
	}

}
