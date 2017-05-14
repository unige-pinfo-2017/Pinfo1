package ch.unige.pinfo.login;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import ch.unige.pinfo.user.dom.User;
import ch.unige.pinfo.user.service.UserService;

public class LoginService {
	
	@Inject
	private UserService userService;
	
	public LoginService(){}
	
	/*public void authentication(String username, String password){
		User user = mu.getUserByUsername(username);
		
		if (user == null){
			sl.display("Username does not exist.");
		} else if (checkPassword(password, user.getPassword())){
			sl.display("Authentication successful.");
			mu.addConnectedUser(user);
		} else {
			sl.display("Authentication failed.");
		}
	}*/
	
	public String authenticate(String username, String inputPw) {
		User user = userService.getUserByUsername(username).get(0);
		if (user.getPassword().equals(inputPw)) {
			return Long.toString(user.getId());
		}
		return "error";
	}
	
	public boolean checkPassword(String inputPassword, String realPassword){
		return realPassword.equals(inputPassword);
	}

}
