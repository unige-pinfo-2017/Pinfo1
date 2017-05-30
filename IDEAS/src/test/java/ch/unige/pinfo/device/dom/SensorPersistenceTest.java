package ch.unige.pinfo.device.dom;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.unige.pinfo.overview.dom.LiveData;
import ch.unige.pinfo.user.dom.User;
import junit.framework.Assert;

@RunWith(Arquillian.class)
public class SensorPersistenceTest {
	
	@PersistenceContext
	EntityManager em;
	
	@Inject
	UserTransaction utx;
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap.create(WebArchive.class, "test.war")
	            .addPackage(User.class.getPackage())
	            .addPackage(LiveData.class.getPackage())
	            .addPackage(Device.class.getPackage())
	            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
	            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	        }
	
	private static final String[] SENSOR_NAME = {
			"abc",
			"a2c"
	};

	private static final String[] UNIT_NAME = {
			"abc",
			"a2c"
	};

	private static final String[] MEASURE_NAME = {
			"abc",
			"a2c"
	};
	
	@Before
	public void preparePersistenceTest() throws Exception {
		clearData();
		insertData();
		startTransaction();
	}
	
	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("clearing Data ...");
		em.createQuery("delete from User").executeUpdate();
		utx.commit();
	}

	private void insertData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Inserting test data ...");
		Sensor s1 = new Sensor(SENSOR_NAME[0], UNIT_NAME[0], MEASURE_NAME[0]);
		em.persist(s1);
		Sensor s2 = new Sensor(SENSOR_NAME[1], UNIT_NAME[1], MEASURE_NAME[1]);
		em.persist(s2);
        System.out.println("Data inserted");
        utx.commit();
        em.clear();
	}
	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
	
	@After
	public void commitTransaction() throws Exception {
		utx.commit();
	}
	
	@Test
	public void shouldFindAllUsers() throws Exception {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Sensor> criteria = builder.createQuery(Sensor.class);
		
		Root<Sensor> sensor = criteria.from(Sensor.class);
		criteria.select(sensor);
		criteria.orderBy(builder.asc(sensor.get("id")));
		
		List<Sensor> sensors = em.createQuery(criteria).getResultList();
		assertAllSensors(sensors);
	}
	
	private static void assertAllSensors(Collection<Sensor> foundSensors) {
		Assert.assertEquals(SENSOR_NAME.length, foundSensors.size());
		final Set<String> foundSensorName = new HashSet<String>();
		final Set<String> foundUnitName = new HashSet<String>();
		final Set<String> foundMeasureName = new HashSet<String>();
		for (Sensor sensor : foundSensors) {
            System.out.println("* " + sensor);
			foundSensorName.add(sensor.getName());
			foundUnitName.add(sensor.getUnit());
			foundMeasureName.add(sensor.getMeasureName());
		}
		Assert.assertTrue(foundSensorName.containsAll(Arrays.asList(SENSOR_NAME)) && foundUnitName.containsAll(Arrays.asList(UNIT_NAME)) && foundMeasureName.containsAll(Arrays.asList(MEASURE_NAME)));
	}
}
