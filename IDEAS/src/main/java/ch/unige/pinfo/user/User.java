package ch.unige.pinfo.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table( name = "user")
public class User {
	// Fields:
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column( name = "ID", nullable = false )
	private Integer id;
	
	@Column( name = "USERNAME", nullable = false ) 
	private String username;
	
	@Column( name = "PASSWORD", nullable = false ) 
	private String password;
	
	public boolean equals(Object obj){
		User user = (User) obj;
		if (user == null){
			return false;
		}
		return ((this.id == user.id) 
			&& (this.getUsername().equals(user.username))
			&& (this.password.equals(user.password)));
	}
	
	public String Json(){
		return new Gson().toJson(this);
	}
	
	// Getters
	public int getId() {
		return id;
	}
	
	public String getUsername(){
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
