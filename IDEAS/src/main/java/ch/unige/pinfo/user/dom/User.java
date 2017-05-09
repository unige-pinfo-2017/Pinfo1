package ch.unige.pinfo.user.dom;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.google.gson.Gson;

import ch.unige.pinfo.device.dom.Device;

@Entity
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
	
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy="owner")
    private Set<Device> devices;
	
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
}
