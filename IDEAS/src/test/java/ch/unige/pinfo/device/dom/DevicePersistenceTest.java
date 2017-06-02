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
public class DevicePersistenceTest {
	
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
	
	private static final String[] DEVICEID = {
			"1acb",
			"2dcf"
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
		em.createQuery("delete from Device").executeUpdate();
		utx.commit();
	}

	private void insertData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Inserting test data ...");
		Device dev1 = new Device(DEVICEID[0]);
		em.persist(dev1);

		Device dev2 = new Device(DEVICEID[1]);
		em.persist(dev2);
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
		CriteriaQuery<Device> criteria = builder.createQuery(Device.class);
		
		Root<Device> device = criteria.from(Device.class);
		criteria.select(device);
		criteria.orderBy(builder.asc(device.get("id")));
		
		List<Device> devices = em.createQuery(criteria).getResultList();
		assertAllDevices(devices);
	}
	
	private static void assertAllDevices(Collection<Device> foundDevices) {
		Assert.assertEquals(DEVICEID.length, foundDevices.size());
		final Set<String> foundDeviceIds = new HashSet<String>();
		for (Device device : foundDevices) {
            System.out.println("* " + device);
			foundDeviceIds.add(device.getDeviceId());
		}
		Assert.assertTrue(foundDeviceIds.containsAll(Arrays.asList(DEVICEID)));
	}
}
