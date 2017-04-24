package ch.unige.pinfo.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "Utilisateur")
public class User {
	// Fields:
	@Id
	@Column( name = "id" )
	private int id;
	
	@Column( name = "username" )
	private String username;
	
	@Column( name = "password" )
	private String password;
	
	@Column( name = "role" )
	private String role;
	
	// Constructor:
	public User(){}
	
	public User(int id, String username, String password, String role ){
		this.id = id;
		this.username = username;
		this.role = role;
		this.password = password;
	}

	public boolean equals(User user){
		if (user == null){
			return false;
		}
		return ((this.id == user.id) 
			&& (this.getUsername().equals(user.username))
			&& (this.password.equals(user.password))
			&& (this.role.equals(user.role)));
	}
	
	// Getters
	public int getId() {
		return id;
	}
	
	public String getUsername(){
		return username;
	}

	public String getRole() {
		return role;
	}

	public String getPassword() {
		return password;
	}
}
