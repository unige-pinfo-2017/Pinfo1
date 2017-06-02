package ch.unige.pinfo.device.dom;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import ch.unige.pinfo.user.dom.User;

@Entity
@Table( name = "Devices")
public class Device implements Serializable{
	/**
	 * The serial-id
	 */
	private static final long serialVersionUID = 3950004706048925769L;

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
		if (obj == null) {
			return false;
		}
		
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		Device device = (Device) obj;

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
