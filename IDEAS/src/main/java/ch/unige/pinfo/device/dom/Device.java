package ch.unige.pinfo.device.dom;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import ch.unige.pinfo.user.dom.User;

@Entity
@Table( name = "Devices")
public class Device {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "Id")
	private Long id;
	
	@Column( name = "DeviceId", nullable = false ) 
	@Size(min = 3, max = 40)
	private String deviceId;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	TypeDevice type;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	User owner;
	

	public Device(){}
	public Device(String deviceId){
		this.deviceId = deviceId;
	}
	
	@Override
	public boolean equals(Object obj){
		Device device = (Device) obj;
		if (device == null)
			return false;
		return ((this.getDeviceId().equals(device.deviceId)) 
				&& (this.getType().equals(device.type)));
	}

	@Override
	public int hashCode(){
        int hash = 1;
        hash = hash * 31 + deviceId.hashCode();
        return hash;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypeDevice getType() {
		return type;
	}

	public void setType(TypeDevice type) {
		this.type = type;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
}
