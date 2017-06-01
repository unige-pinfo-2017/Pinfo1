package ch.unige.pinfo.wso2.mock;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class LightDataService implements Serializable {
	
	/**
	 *  The serial-id
	 */
	private static final long serialVersionUID = -6443286456628547755L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void addLightData(LightData ld){
		entityManager.persist(ld);
	}
	
	public void deleteLightData(Long id) {
		LightData ld = entityManager.find(LightData.class, id);
		entityManager.remove(ld);		
	}
	
	public LightData getLightDataByDeviceId(String deviceId){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<LightData> c = cb.createQuery(LightData.class);
		Root<LightData> ld = c.from(LightData.class);
		Predicate condition = cb.equal(ld.get("deviceId"), deviceId);
		c.where(condition);
		TypedQuery<LightData> query = entityManager.createQuery(c);
		return query.getResultList().get(0);
	}
	
	public void updateLightData(LightData ld){
		entityManager.merge(ld);
	}
}
