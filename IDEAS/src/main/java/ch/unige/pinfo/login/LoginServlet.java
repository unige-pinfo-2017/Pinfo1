package ch.unige.pinfo.login;

import java.io.IOException;
import java.io.StringReader;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.unige.pinfo.user.service.UserService;

@WebServlet(name = "/login", urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {
	
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = -1516258385227218380L;

	@Inject
	private UserService userService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String line = req.getReader().lines().collect(Collectors.joining());		
		JsonReader jsonReader = Json.createReader(new StringReader(line));
		JsonObject object = jsonReader.readObject();
		jsonReader.close();
		
		// The parameters for the authentication are retrieved.
		String username = object.getString("username");
		String password = object.getString("password");
		//resp.getWriter().print(" u : "+username+" p : "+password+" id : "+loginService.authenticate(username, password));
		
		// We try to perform authentication on the server with the input parameters.
		try{
			req.login(username, password);
			resp.setStatus(200);
			// The id of the user who authenticated is returned in the body of the response.
			resp.getWriter().print(userService.getUserByUsername(username).get(0).getId());
		} catch(ServletException e){
			resp.setStatus(401);
			resp.getWriter().print(e);
		}
	}
}