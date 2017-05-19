package ch.unige.pinfo.device.dom;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.criteria.Expression;
import javax.validation.constraints.Size;

@Entity
@Table( name = "TypeDevices")
public class TypeDevice {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "Id")
	private Long id;
    
	@Column( name = "TypeDeviceName", nullable = false ) 
	@Size(min = 3, max = 40)
	private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "type")
    @OrderBy("id asc")
    private Set<Device> devices;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "typeDevices")  
    @OrderBy("id asc")
    private Set<Sensor> sensors;
    
	public TypeDevice(){}
	
	public TypeDevice(String name){
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj){
		TypeDevice td = (TypeDevice) obj;
		if (td == null)
			return false;
		return (this.getName().equals(td.name));
	}
	
	@Override
	public int hashCode(){
        int hash = 1;
        hash = hash * 17 + name.hashCode();
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

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public Set<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(Set<Sensor> sensors) {
		this.sensors = sensors;
	}
}
