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
public class TypeDevicePersistenceTest {
	
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
	            .addPackage(TypeDevice.class.getPackage())
	            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
	            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	        }
	
	private static final String[] TYPEDEVICE_NAME = {
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
		em.createQuery("delete from TypeDevice").executeUpdate();
		utx.commit();
	}

	private void insertData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Inserting test data ...");
		TypeDevice td1 = new TypeDevice(TYPEDEVICE_NAME[0]);
		em.persist(td1);

		TypeDevice td2 = new TypeDevice(TYPEDEVICE_NAME[1]);
		em.persist(td2);
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
	public void shouldFindAllDevices() throws Exception {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<TypeDevice> criteria = builder.createQuery(TypeDevice.class);
		
		Root<TypeDevice> typedevice = criteria.from(TypeDevice.class);
		criteria.select(typedevice);
		criteria.orderBy(builder.asc(typedevice.get("id")));
		
		List<TypeDevice> typedevices = em.createQuery(criteria).getResultList();
		System.out.println("la liste: "+ typedevices);
		assertAllTypeDevices(typedevices);
	}
	
	private static void assertAllTypeDevices(Collection<TypeDevice> foundTypeDevices) {
		Assert.assertEquals(TYPEDEVICE_NAME.length, foundTypeDevices.size());
		final Set<String> foundDeviceIds = new HashSet<String>();
		for (TypeDevice typedevice : foundTypeDevices) {
            System.out.println("* " + typedevice.getName());
			foundDeviceIds.add(typedevice.getName());
		}
		Assert.assertTrue(foundDeviceIds.containsAll(Arrays.asList(TYPEDEVICE_NAME)));
	}
}
