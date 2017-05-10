package ch.unige.pinfo.user.service;

import java.util.List;

import javax.ejb.Local;

import ch.unige.pinfo.user.dom.User;

@Local
public interface UserService{
	
	void addUser(User user);
	void deleteUser(Long Id);
	User getUserById(Long Id);
	List<User> getUserByUsername(String username);
	List<User> getAllUsers();
	double getSumBySensor(Long userId, String sensorName, String from, String to);
	
}
