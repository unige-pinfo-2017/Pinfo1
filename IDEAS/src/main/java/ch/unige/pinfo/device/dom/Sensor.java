package ch.unige.pinfo.device.dom;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table( name = "Sensors")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "Id")
	private Long id;
    
	@Column( name = "SensorName", nullable = false ) 
	@Size(min = 3, max = 40)
	private String name;
	
    //@ManyToMany(fetch = FetchType.EAGER)
    //private Set<TypeDevice> typeDevices = new HashSet<>();
	
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)  
    @JoinTable(name="TypeDevices_Sensors", joinColumns=@JoinColumn(name="sensor_Id"), inverseJoinColumns=@JoinColumn(name="typeDevice_Id")) 
    private Set<TypeDevice> typeDevices;
    
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
