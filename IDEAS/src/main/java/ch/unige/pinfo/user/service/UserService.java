package ch.unige.pinfo.user.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import ch.unige.pinfo.device.dom.Sensor;
import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.user.dom.Basic;
import ch.unige.pinfo.user.dom.Manager;
import ch.unige.pinfo.user.dom.SysAdmin;
import ch.unige.pinfo.user.dom.User;

@Local
public interface UserService extends Serializable {
	
	/**
	 * <b>addUser</b>
	 * <p>
	 * {@code void addUser(User user)}
	 * <p>
	 * 
	 * Add a user in the database.
	 * 
	 * @param user - The user to add.
	 */
	void addUser(User user);
	
	/**
	 * <b>deleteUser</b>
	 * <p>
	 * {@code void deleteUser(Long Id)}
	 * <p>
	 * 
	 * Delete user from the database.
	 * 
	 * @param Id - The user's id to remove.
	 */
	void deleteUser(Long Id);
	
	/**
	 * <b>getUserById</b>
	 * <p>
	 * {@code User getUserById(Long Id)}
	 * <p>
	 * 
	 * Get a user by his id.
	 * 
	 * @param Id - The user's id. 
	 * @return
	 * A user.
	 */
	User getUserById(Long Id);
	
	/**
	 * <b>getUserRoleById</b>
	 * <p>
	 * {@code String getUserRoleById(Long id)}
	 * <p>
	 * 
	 * Get the user's role specifying the user's id.
	 * 
	 * @param id - The user's id
	 * @return
	 * The user's role in {@code String} format.
	 */
	String getUserRoleById(Long id);
	
	/**
	 * <b>getUserByUsername</b>
	 * <p>
	 * {@code List<User> getUserByUsername(String username)}
	 * <p>
	 * 
	 * Get the user by his name.
	 * 
	 * @param username - The name of the user.
	 * @return
	 * A list of users.
	 */
	List<User> getUserByUsername(String username);
	
	/**
	 * <b>getAllUsers</b>
	 * <p>
	 * {@code List<User> getAllUsers()}
	 * <p>
	 * 
	 * Get all users.
	 * 
	 * @return
	 * List of user
	 */
	List<User> getAllUsers();
	
	/**
	 * <b>getSensorsForTypeDevice</b>
	 * <p>
	 * {@code Set<Sensor> getSensorsForTypeDevice(String name)}
	 * <p>
	 * 
	 * Get all sensors for a type of device.
	 * 
	 * @param name - type of device. 
	 * @return
	 * A set of sensors.
	 */
	Set<Sensor> getSensorsForTypeDevice(String name);
	
	/**
	 * <b>getUsersOfManager</b>
	 * <p>
	 * {@code List<User> getUsersOfManager(Long userId)}
	 * <p>
	 * 
	 * Get subordinates of manager specified by its user id.
	 * 
	 * @param userId - The user's id.
	 * @return
	 * A list of subordinates
	 */
	List<User> getUsersOfManager(Long userId);
	
	/**
	 * <b>getUsersOfSysAdmin</b>
	 * <p>
	 * {@code List<User> getUsersOfSysAdmin(Long userId)}
	 * <p>
	 * 
	 * Get subordinates of the system administrator specified by its id.
	 * 
	 * @param userId - The user's id.
	 * @return
	 * A list of subordinates
	 */
	List<User> getUsersOfSysAdmin(Long userId);
	
	/**
	 * <b>getSysAdminById</b>
	 * <p>
	 * {@code SysAdmin getSysAdminById(Long id)}
	 * <p>
	 * 
	 * Get the system administrator specified by its id.
	 * 
	 * @param id - The user's id.
	 * @return
	 * System Admin user.
	 */
	SysAdmin getSysAdminById(Long id);
	
	/**
	 * <b>getManagerById</b>
	 * <p>
	 * {@code Manager getManagerById(Long id)}
	 * <p>
	 * 
	 * Get user with manager role. 
	 * 
	 * @param id - The user's id.
	 * @return
	 * Manager user.
	 */
	Manager getManagerById(Long id);
	
	/**
	 * <b>getBasicById</b>
	 * <p>
	 * {@code Basic getBasicById(Long id)}
	 * <p>
	 * 
	 * Get user with basic role. 
	 * 
	 * @param id - The user's id. 
	 * @return
	 * Basic user.
	 */
	Basic getBasicById(Long id);
	
	/**
	 * <b>addPreference</b>
	 * <p>
	 * {@code boolean addPreference(Long userId, Long liveDataId)}
	 * <p>
	 * 
	 * Add the displaying preference of a user in database.  
	 * 
	 * @param userId - The user's id
	 * @param liveDataId - The liveData's id
	 * @return
	 * {@code true} when preference is added, {@code false} otherwise.
	 */
	boolean addPreference(Long userId, Long liveDataId);
	
	/**
	 * <b>removePreference</b>
	 * <p>
	 * {@code boolean removePreference(Long userId, Long liveDataId)}
	 * <p>
	 * 
	 * Remove the preference of a user from database. 
	 * 
	 * @param userId - The user's id.
	 * @param liveDataId - The liveData's id.
	 * @return
	 * {@code true} when preference is removed, {@code false} otherwise.
	 */
	boolean removePreference(Long userId, Long liveDataId);
	
	/**
	 * <b>getUserPreferenceByUserId</b>
	 * <p>
	 * {@code Set<LiveData> getUserPreferenceByUserId(Long userId)}
	 * <p>
	 * 
	 * Get the preferences for a user.
	 * 
	 * @param userId - The user's id
	 * @return
	 * A set of live data.
	 */
	Set<LiveData> getUserPreferenceByUserId(Long userId);
	
}
