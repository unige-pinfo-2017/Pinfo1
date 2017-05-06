package ch.unige.pinfo.user.dom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.google.gson.Gson;

@Entity
@Table( name = "USERS")
public class User {
	// Fields:
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "ID")
	private Long id;
	
	@Column( name = "USERNAME", nullable = false ) 
	@Size(min = 3, max = 40)
	private String username;
	
	@Column( name = "PASSWORD", nullable = false ) 
	@Size(min = 3, max = 40)
	private String password;
	
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
	
}
