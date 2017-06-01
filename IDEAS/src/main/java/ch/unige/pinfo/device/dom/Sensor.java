package ch.unige.pinfo.device.dom;

import java.io.Serializable;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table( name = "Sensors")
public class Sensor implements Serializable {
    /**
	 *  The serial-id
	 */
	private static final long serialVersionUID = -8288619276374547322L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "Id")
	private Long id;
    
	@Column( name = "SensorName", nullable = false ) 
	@Size(min = 3, max = 40)
	private String name;
	
	@Column( name = "UnitName", nullable = false ) 
	@Size(min = 1, max = 10)
	private String unit;
	
	@Column( name = "MeasureName", nullable = false ) 
	@Size(min = 3, max = 40)
	private String measureName;
		
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  
    @JoinTable(name="TypeDevices_Sensors", joinColumns=@JoinColumn(name="sensor_Id"), inverseJoinColumns=@JoinColumn(name="typeDevice_Id")) 
    @OrderBy("id asc")
    private Set<TypeDevice> typeDevices;
    
    public Sensor(){}
    
    public Sensor(String name, String unitName, String measureName){
    	this.name = name;
    	this.unit = unitName;
    	this.measureName = measureName;
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
	

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMeasureName() {
		return measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}

}
