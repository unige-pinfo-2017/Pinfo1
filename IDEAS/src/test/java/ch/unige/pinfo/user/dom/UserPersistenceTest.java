package ch.unige.pinfo.user.dom;

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

import ch.unige.pinfo.device.dom.Device;
import ch.unige.pinfo.overview.dom.LiveData;
import junit.framework.Assert;

@RunWith(Arquillian.class)
public class UserPersistenceTest {
	
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
	
	private static final String[] NAMES = {
			"test",
			"test espace"
	};

	private static final String[] PASSWORDS = {
			"testpw",
			"espacepw"
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
		Basic user1 = new Basic();
        user1.setUsername(NAMES[0]);
        user1.setPassword(PASSWORDS[0]);
        user1.setDevices(new HashSet<Device>());
		user1.setPreferences(new HashSet<LiveData>());
		
        em.persist(user1);
		Basic user2 = new Basic();
        user2.setUsername(NAMES[1]);
        user2.setPassword(PASSWORDS[1]);
        user2.setDevices(new HashSet<Device>());
		user2.setPreferences(new HashSet<LiveData>());
		
        em.persist(user2);
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
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		
		Root<User> user = criteria.from(User.class);
		criteria.select(user);
		criteria.orderBy(builder.asc(user.get("id")));
		
		List<User> users = em.createQuery(criteria).getResultList();
		assertAllUsers(users);
	}
	
	private static void assertAllUsers(Collection<User> foundUsers) {
		Assert.assertEquals(NAMES.length, foundUsers.size());
		final Set<String> foundUsersNames = new HashSet<String>();
		final Set<String> foundUsersPasswords = new HashSet<String>();
		for (User user : foundUsers) {
            System.out.println("* " + user);
			foundUsersNames.add(user.getUsername());
			foundUsersPasswords.add(user.getPassword());
		}
		Assert.assertTrue(foundUsersNames.containsAll(Arrays.asList(NAMES)) && foundUsersPasswords.containsAll(Arrays.asList(PASSWORDS)));
	}
}
