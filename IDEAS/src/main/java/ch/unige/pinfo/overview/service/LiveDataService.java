package ch.unige.pinfo.overview.service;

import java.util.List;

import javax.ejb.Local;

import ch.unige.pinfo.overview.dom.LiveData;

@Local
public interface LiveDataService {
	
	/**
	 * <b>addLiveData</b>
	 * <p>
	 * {@code void addLiveData(LiveData liveData)}
	 * <p>
	 * 
	 * Add Live data in database.
	 * 
	 * @param liveData - The data Live to add
	 */
	void addLiveData(LiveData liveData);
	
	/**
	 * <b>deleteLiveData</b>
	 * <p>
	 * {@code void deleteLiveData(Long id)}
	 * <p>
	 * 
	 * Delete Live data from the database.
	 * 
	 * @param id - The live data's id.
	 */
	void deleteLiveData(Long id);
	
	/**
	 * <b>getLiveDataById</b>
	 * <p>
	 * {@code LiveData getLiveDataById(Long id)}
	 * <p>
	 * 
	 * Get live data by his id.
	 * 
	 * @param id - The live data's id.
	 * @return
	 * The live data.
	 */
	LiveData getLiveDataById(Long id);
	
	/**
	 * <b>getAllLiveData</b>
	 * <p>
	 * {@code List<LiveData> getAllLiveData()}
	 * <p>
	 * 
	 * Get all the existing data live.
	 * 
	 * @return
	 * A list of Live data.
	 */
	List<LiveData> getAllLiveData();
}
