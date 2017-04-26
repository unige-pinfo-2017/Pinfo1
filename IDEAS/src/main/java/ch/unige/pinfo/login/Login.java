package ch.unige.pinfo.login;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import ch.unige.pinfo.user.dom.ManageUser;
import ch.unige.pinfo.user.dom.User;

public class Login {
	@Inject
	private ManageUser mu;
	
	@Inject
	private LoginScreen sl;
	
	public Login(){}
	
	public void authentication(String username, String password){
		User user = mu.getUserByUsername(username);
		
		if (user == null){
			sl.display("Username does not exist.");
		} else if (checkPassword(password, user.getPassword())){
			sl.display("Authentication successful.");
			mu.addConnectedUser(user);
		} else {
			sl.display("Authentication failed.");
		}
	}
	
	public boolean checkPassword(String inputPassword, String realPassword){
		return realPassword.equals(inputPassword);
	}

	public ManageUser getMu() {
		return mu;
	}
}
