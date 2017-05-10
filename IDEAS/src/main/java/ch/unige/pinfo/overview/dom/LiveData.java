package ch.unige.pinfo.overview.dom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import ch.unige.pinfo.device.dom.Sensor;

@Entity
@Table( name = "LiveData")
public class LiveData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "Id")
	private Long id;	
    
    @Column( name = "ComputeType", nullable = false ) 
	@Size(min = 3, max = 40)
	private String computeType;
    
    @OneToOne
    @JoinColumn(name="Sensor_Id")
    private Sensor sensor;
    
    // Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComputeType() {
		return computeType;
	}

	public void setComputeType(String computeType) {
		this.computeType = computeType;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
}
