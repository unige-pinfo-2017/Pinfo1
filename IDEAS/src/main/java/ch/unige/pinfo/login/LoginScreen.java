package ch.unige.pinfo.login;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import ch.unige.pinfo.user.dom.User;
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
		return "WIP";
	}
	
	public String display(String msg){
		return msg;
	}
}

