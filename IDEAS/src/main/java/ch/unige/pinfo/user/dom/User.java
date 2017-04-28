package ch.unige.pinfo.user.dom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table( name = "user")
@NamedQueries({
    @NamedQuery(name="User.findAll", query="SELECT e FROM User e"),
    @NamedQuery(name="User.findByUsername", query="SELECT e FROM User e where e.username=:username"),
}) 
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
