package ch.unige.pinfo.wso2.Dom;

import java.util.HashSet;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table( name = "TYPE_DEVICES")
public class TypeDevice {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "ID")
	private Long id;
    
	@Column( name = "TYPE_NAME", nullable = false ) 
	@Size(min = 3, max = 40)
	private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="type", cascade = {CascadeType.ALL})
    private Set<Device> devices = new HashSet<>();
    
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<Sensor> sensors = new HashSet<>() ;

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