package ch.unige.pinfo.login;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import ch.unige.pinfo.user.dom.User;

public class LoginJsonBuilder {
	public JsonObject buildId(String id) {
		return Json.createObjectBuilder().add("id", id).build();
	}	
	
	public JsonArray buildIds(List<User> users) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (User user: users) {
			builder.add(buildId(Long.toString(user.getId())));
		}
		return builder.build();
	}
}
