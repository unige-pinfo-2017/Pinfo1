package ch.unige.pinfo.user.dom;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.google.gson.Gson;

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.overview.dom.LiveData;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Role", discriminatorType = DiscriminatorType.STRING, length = 20)
@DiscriminatorValue("User")
@Table( name = "Users")
public class User {
	// Fields:
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "Id")
	private Long id;
	
	@Column( name = "Username", nullable = false ) 
	@Size(min = 3, max = 40)
	private String username;
	
	@Column( name = "Password", nullable = false ) 
	@Size(min = 3, max = 40)
	private String password;
	
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Device> devices;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "users")  
    private Set<LiveData> preferences;
	
	@Override
	public boolean equals(Object obj){
		User user = (User) obj;
		if (user == null){
			return false;
		}
		return ((this.getUsername().equals(user.username))
			&& (this.password.equals(user.password)));
	}
	
	public String Json(){
		return new Gson().toJson(this);
	}
	
	// Getters 
	public Long getId() {
		return id;
	}
	
	public String getUsername(){
		return username;
	}

	public String getPassword() {
		return password;
	}

	// Setters
	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}
	
	public Set<LiveData> getPreferences() {
		return preferences;
	}

	public void setPreferences(Set<LiveData> preferences) {
		this.preferences = preferences;
	}
}
