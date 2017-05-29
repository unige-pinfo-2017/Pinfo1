package ch.unige.pinfo.overview.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ch.unige.pinfo.overview.dom.LiveData;

@Stateless
public class LiveDataServiceImpl implements LiveDataService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void addLiveData(LiveData liveData) {
		entityManager.persist(liveData);		
	}

	@Override
	public void deleteLiveData(Long id) {
		LiveData liveData = entityManager.find(LiveData.class, id);
		entityManager.remove(liveData);		
	}

	@Override
	public LiveData getLiveDataById(Long id) {
		return entityManager.find(LiveData.class, id);
	}

	@Override
	public List<LiveData> getAllLiveData() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<LiveData> c = cb.createQuery(LiveData.class);
		Root<LiveData> liveDatas = c.from(LiveData.class);
		c.select(liveDatas);
		TypedQuery<LiveData> query = entityManager.createQuery(c);
		return query.getResultList();
	}

}
