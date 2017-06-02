package ch.unige.pinfo.login;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.junit.Test;

import ch.unige.pinfo.user.dom.User;

public class LoginJsonBuilderTest {
	private LoginJsonBuilder ljb = new LoginJsonBuilder();
	
	@Test
	public void buildIdTest() {
		String mockId = "1";
		
		JsonObject output = ljb.buildId(mockId);
		JsonObject expected  = Json.createObjectBuilder()
								.add("id", mockId)
								.build();
		
		assertEquals(expected, output);
	}

	@Test
	public void buildIdsTest() {
		String mockId = "1";
		
		User mockUser1 = new User();
		mockUser1.setId(Long.parseLong(mockId));
		
		User mockUser2 = new User();
		mockUser2.setId(Long.parseLong(mockId));
		
		List<User> mockUsers = new ArrayList<User>();
		mockUsers.add(mockUser1);
		mockUsers.add(mockUser2);
		
		JsonArray output = ljb.buildIds(mockUsers);
		JsonArray expected = Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("id", mockId)
										.build())
								.add(Json.createObjectBuilder()
										.add("id", mockId)
										.build())
								.build();
		
		assertEquals(expected, output);
	}
}
