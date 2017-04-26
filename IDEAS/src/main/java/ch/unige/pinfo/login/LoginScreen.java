package ch.unige.pinfo.login;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import ch.unige.pinfo.user.User;
import ch.unige.pinfo.user.service.*;

@Path("/login")
public class LoginScreen {
	
	@Inject
	private UserService userService;
	
	public LoginScreen(){
	}

	@GET
	//@Produces(MediaType.TEXT_HTML)
	@Path("/")
	public String logTest(){
		
		// ce que le user pourait entrer
		User u_logged = new User();
		u_logged.setUsername("fsdf");
		u_logged.setPassword("pass");
		
		// on recuperer l'user qui est en base de données avec l'username entré
		User u_db = userService.getUserByUsername(u_logged.getUsername());
		
		// ici nons avons un problème 
		// prendre le id de la base de données correspondant aux info dessus
		u_logged.setId(2);
		
		// test et affichage
		if (u_logged.equals(u_db))
			return "success";
		else
			return "fail";
	}
	
	public String display(String msg){
		return msg;
	}
}

