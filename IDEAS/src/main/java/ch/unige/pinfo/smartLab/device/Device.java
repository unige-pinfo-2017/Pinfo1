package ch.unige.pinfo.smartLab.device;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DEVICES")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("D")
public class Device implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8034446372546013212L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL) // Si table device dropped => devices table dropped
	@JoinColumn(name="DEVICES_ID", nullable=true)
	private List<Sensor> mySensors;

	public Device () {
		this.mySensors = new ArrayList<>();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Sensor> getMySensors() {
		return mySensors;
	}

	public void setMySensors(List<Sensor> mySensors) {
		this.mySensors = mySensors;
	}
	
	
}
