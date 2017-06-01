package ch.unige.pinfo.login;

import java.io.Serializable;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import ch.unige.pinfo.user.dom.User;

public class LoginJsonBuilder implements Serializable {
	
	/**
	 *  The serial-id
	 */
	private static final long serialVersionUID = -5315565523787604723L;

	/**
	 * <b>buildId</b>
	 * <p>
	 * {@code public JsonObject buildId(String id)}
	 * <p>
	 * 
	 * Build a {@code JsonObject} with id.
	 * 
	 * @param id - The id
	 * @return
	 * A {@code JsonObject} of id:{@code id}; (name:value) format.
	 */
	public JsonObject buildId(String id) {
		return Json.createObjectBuilder().add("id", id).build();
	}	
	
	/**
	 * <b>buildIds</b>
	 * <p>
	 * {@code public JsonArray buildIds(List<User> users)}
	 * <p>
	 * 
	 * Build a {@code JsonArray} of ids
	 * 
	 * @param users - List of users
	 * @return
	 * A {@code JsonArray} of user's ids.
	 */
	public JsonArray buildIds(List<User> users) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (User user: users) {
			builder.add(buildId(Long.toString(user.getId())));
		}
		return builder.build();
	}
}
