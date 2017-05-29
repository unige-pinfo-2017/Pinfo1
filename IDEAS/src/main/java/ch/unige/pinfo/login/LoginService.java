package ch.unige.pinfo.login;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;

import ch.unige.pinfo.backend.BackEndFacade;
import ch.unige.pinfo.user.dom.User;

@Stateless
public class LoginService {
	@Inject
	private LoginJsonBuilder loginJsonBuilder;
	
	@Inject
	private BackEndFacade backEndFacade;
	
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
	
	/**
	 * <b>authenticate</b> 
	 * <p>
	 *   {@code public String authenticate(String username, String inputPw)}
	 * <p>
	 * Compare the {@code username} with the user name in data base and compare the corresponding password to the {@code InputPw}.
	 * <br>
	 * 
	 * @param username - The user name to compare with the one in data base.
	 * @param inputPw - The password to compare with the one corresponding to the user name in data base.
	 * @return
	 * User Id if both parameters match the data in database, {@code error} otherwise.
	 */
	public String authenticate(String username, String inputPw) {
		User user = backEndFacade.getUserByUsername(username);
		if (user.getPassword().equals(inputPw)) {
			return Long.toString(user.getId());
		}
		return "error";
	}
	
	/**
	 * <b>checkPassword</b> 
	 * <p>
	 *   {@code public public boolean checkPassword(String inputPassword, String realPassword)}
	 * <p>
	 * Compare {@code realPassword} with {@code inputPassword}. 
	 * 
	 * @param inputPassword - The password to compare with the real password
	 * @param realPassword - The real password.
	 * @return
	 * {@code true} if the parameters match, {@code false} otherwise.
	 */
	public boolean checkPassword(String inputPassword, String realPassword){
		return realPassword.equals(inputPassword);
	}

	public JsonArray getSubordinates(Long userId) {
		String role = backEndFacade.getUserRoleById(userId);
		List<User> users ;
		if (role.equals("Manager")) {
			users = backEndFacade.getUsersOfManager(userId);
		} else if (role.equals("SysAdmin")) {
			users = backEndFacade.getUsersOfSysAdmin(userId);
		} else {
			users = new ArrayList<User>();
		}
		return loginJsonBuilder.buildIds(users);
	}

	public String getRole(Long userId) {
		return backEndFacade.getUserRoleById(userId);
	}

}
