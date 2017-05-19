package ch.unige.pinfo.overview.dom;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.user.dom.User;

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
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Sensor_Id")
    private Sensor sensor;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  
    @JoinTable(name="Prefereneces", joinColumns=@JoinColumn(name="LiveData_Id"), inverseJoinColumns=@JoinColumn(name="User_Id")) 
    @OrderBy("id asc")
    private Set<User> users;
    
    //public LiveData() {}
    


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
	
    public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
