package ch.unige.pinfo.overview.service;

import java.util.List;

import javax.ejb.Local;

import ch.unige.pinfo.overview.dom.LiveData;

@Local
public interface LiveDataService {
	
	void addLiveData(LiveData liveData);
	void deleteLiveData(Long id);
	LiveData getLiveDataById(Long id);
	List<LiveData> getAllLiveData();
}
