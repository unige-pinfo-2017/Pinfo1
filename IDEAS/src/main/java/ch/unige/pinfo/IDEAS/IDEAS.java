package ch.unige.pinfo.IDEAS;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.unige.pinfo.login.Login;
import ch.unige.pinfo.user.User;

@Path("/home")
public class IDEAS {
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction userTransaction;
	
	public IDEAS() {}
	
	@GET
	//@Produces(MediaType.TEXT_HTML)
	@Path("/test")
	public String test() throws ServletException{
		
		// on cree un user 
	User u = new User();
	u.setUsername("test");
	u.setPassword("PAss");

				
	// inscrire

		try {
			userTransaction.begin();
			em.persist(u);
			userTransaction.commit();
		} catch (Exception e) {
			System.out.println(e);
		}

			    
	//trouver par index
	User u1 = em.find(User.class, 2);
	
	return "Welcome to IDEAS! The application is under construction. + "+u1.getUsername();
	}
}
