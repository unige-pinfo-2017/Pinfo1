package ch.unige.pinfo.user.service;

import java.util.List;

import javax.ejb.Local;

import ch.unige.pinfo.user.User;

@Local
public interface UserService{
	
	void addUser(User user);
	void deleteUser(Integer Id);
	User getUserByID(Integer Id);
	User getUserByUsername(String username);
	List<User> getAllUsers();
	
}
