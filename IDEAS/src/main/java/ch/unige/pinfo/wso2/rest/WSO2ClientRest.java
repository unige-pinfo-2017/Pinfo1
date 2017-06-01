package ch.unige.pinfo.wso2.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.Serializable;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;

/*
 * Remarques: 
 * Client de base temporaire fonctionnant avec un fake serveur WSO2 qui contient du
 * Json pour les Get.  
 * A noter que le serveur WSO2 utilise de l'hyper Text Transfert Protocol Secure 
 * (https). Il nous faut donc un nom d'utilisateur et un password qui ne sont tjs pas 
 * disponibles. En plus de l'IP WSO2 (tjs non disponible).
 * 
 *  Le client officiel et ses attributs sont pour l'instant mis en commentaire. 
 */

public class WSO2ClientRest implements Serializable {

	/**
	 *  The serial-id
	 */
	private static final long serialVersionUID = 6758944176529352716L;
	
	private String urlBase;

	public WSO2ClientRest(){
		//url de base pour le fake server (temporaire):
		urlBase = "http://localhost:8080/IDEAS/fakeWso2";

		//url reel (non disponible actuellement):
		//urlBase = "<wso2 ip address>:8243";   
	}

	/**
	 * <b>getStates</b>
	 * <p>
	 * {@code public JsonArray getStates(String deviceType, String deviceId, String qpSensorType, String qpFrom, String qpTo)}
	 * <p>
	 * 
	 * Rest client that get device's sensor data for a time interval
	 * 
	 * @param deviceType - The device's type
	 * @param deviceId - The device's id
	 * @param qpSensorType - The sensor's type
	 * @param qpFrom - initial timestamp
	 * @param qpTo - final timestamp
	 * @return
	 * A {@code JsonArray} device's sensor data.
	 */
	//Client temporaire fonctionnant avec FakeWSO2Server:
	public JsonArray getStates(String deviceType, String deviceId, String qpSensorType, String qpFrom, String qpTo){
		Client client = ClientBuilder.newClient();
		WebTarget wb = client.target(urlBase);
		WebTarget targetUpdated = wb
				.path("/"+deviceType+"/device/stats/"+deviceId)
				.queryParam("from", qpFrom)
				.queryParam("to", qpTo)
				.queryParam("sensorType", qpSensorType);
		String response = targetUpdated.request("application/json").get(String.class);

		JsonArray responseJ = (JsonArray) Json.createReader(new StringReader(response)).readArray();
		return responseJ;
	}

	//Post peut etre accede avec des strig, int et double pour le query param qpState:
	/*public Response postStatus2(String deviceType, String deviceId, String status, String qpState){
		Client client = ClientBuilder.newClient();
		WebTarget wb = client.target(urlBase);
		
		Form form = new Form();
		form.param("state", qpState);
		
		WebTarget targetUpdated = wb
				.path("/"+deviceType+"/device/"+deviceId+"/change-"+status);
		Response response = targetUpdated.request(MediaType.TEXT_PLAIN).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		//Response response = targetUpdated.request().get();
		return response;

	}*/
	
	/**
	 * <b>postStatus</b>
	 * <p>
	 * {@code public Response postStatus(String deviceType, String deviceId, String status, String qpState)}
	 * <p>
	 * 
	 * Rest client that modifies device's status.
	 * 
	 * @param deviceType - The device's type
	 * @param deviceId - The device's id
	 * @param status - The resource
	 * @param qpState - The new state value 
	 * @return
	 * A {@code Response}: 200 if state is changed, 500 otherwise.
	 */
	public Response postStatus(String deviceType, String deviceId, String status, String qpState){
		Client client = ClientBuilder.newClient();
		WebTarget wb = client.target(urlBase);

		WebTarget targetUpdated = wb
				.path("/"+deviceType+"/device/"+deviceId+"/change-"+status);
		//String response = targetUpdated.request(MediaType.TEXT_PLAIN).get(String.class);
		//Response response = targetUpdated.request(MediaType.TEXT_PLAIN).post(Entity.entity(qpState, MediaType.TEXT_PLAIN));
		Response response = targetUpdated.request(MediaType.TEXT_PLAIN).post(Entity.text(qpState));
		if (response.hasEntity()) {
			return Response.status(200).entity(qpState).build();
		}
		return Response.status(500).entity("no entity").build();
	}

	//Client officiel (non disponible):
	/*
	  public JsonArray getStates(String deviceType, String deviceId,  String qpSensorType, String qpFrom, String qpTo){
		Client client = ClientBuilder.newClient();
		WebTarget wb = client.target(urlBase);
		WebTarget targetUpdated = wb
				.path("/"+deviceType+"/device/stats/"+deviceId)
				.queryParam("from", qpFrom)
				.queryParam("to", qpTo)
				.queryParam("sensorType", qpSensorType)
				.headers(<autorisation>);
		String response = targetUpdated.request("application/json").get(String.class);

		JsonParser parser = new JsonParser();
		JsonArray responseJ =  parser.parse(response).getAsJsonArray();

		return response;

	}*/

}
