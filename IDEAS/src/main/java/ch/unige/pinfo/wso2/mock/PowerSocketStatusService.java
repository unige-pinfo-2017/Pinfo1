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
public class PowerSocketStatusService implements Serializable{
	
	/**
	 *  The serial-id
	 */
	private static final long serialVersionUID = 2843380944414953803L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void addPowerSocketStatus(PowerSocketStatus pss){
		entityManager.persist(pss);
	}
	
	public void deletePowerSocketStatus(Long id) {
		PowerSocketStatus pss = entityManager.find(PowerSocketStatus.class, id);
		entityManager.remove(pss);		
	}
	
	public PowerSocketStatus getPowerSocketStatusByDeviceId(String deviceId){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PowerSocketStatus> c = cb.createQuery(PowerSocketStatus.class);
		Root<PowerSocketStatus> pss = c.from(PowerSocketStatus.class);
		Predicate condition = cb.equal(pss.get("deviceId"), deviceId);
		c.where(condition);
		TypedQuery<PowerSocketStatus> query = entityManager.createQuery(c);
		return query.getResultList().get(0);
	}
	
	public void updatePowerSocketStatus(PowerSocketStatus pss){
		entityManager.merge(pss);
	}
}
