package ch.unige.pinfo.overview.service;

import java.util.List;

import ch.unige.pinfo.overview.dom.LiveData;


public interface LiveDataService {
	
	void addLiveData(LiveData liveData);
	void deleteLiveData(Long id);
	LiveData getLiveDataById(Long id);
	List<LiveData> getAllLiveData();
}
