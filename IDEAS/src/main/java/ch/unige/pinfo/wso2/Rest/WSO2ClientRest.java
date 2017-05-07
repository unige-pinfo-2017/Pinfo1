package ch.unige.pinfo.wso2.Rest;

import javax.ws.rs.client.Client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/*
 * Remarques: 
 * Client de base temporaire fonctionnant avec un fake serveur WSO2 qui contient du
 * Json. 
 * A noter que le serveur WSO2 utilise de l'hyper Text Transfert Protocol Secure 
 * (https). Il nous faut donc un nom d'utilisateur et un password qui ne sont tjs pas 
 * disponibles. En plus de l'IP WSO2 (tjs non disponible).
 * 
 *  Le client officiel et ses attributs sont pour l'instant mis en commentaire. 
 */

public class WSO2ClientRest {

	private String urlBase;

	public WSO2ClientRest(){
		//url de base pour le fake server (temporaire):
		urlBase = "http://localhost:8080/IDEAS/fakeWso2";
		
		//url réel (non disponible actuellement):
		//urlBase = "<wso2 ip address>:8243";   
	}

	//Client temporaire fonctionnant avec FakeWSO2Server:
	public JsonArray getDevice(String deviceType, String deviceId,  String qpSensorType, String qpFrom, String qpTo){
		Client client = ClientBuilder.newClient();
		WebTarget wb = client.target(urlBase);
		WebTarget targetUpdated = wb
				.path("/"+deviceType+"/device/stats/"+deviceId)
				.queryParam("from", qpFrom)
				.queryParam("to", qpTo)
				.queryParam("sensorType", qpSensorType);
		String response = targetUpdated.request("application/json").get(String.class);
		
		JsonParser parser = new JsonParser();
		JsonArray responseJ =  parser.parse(response).getAsJsonArray();
		
		return responseJ;
	}
	
	//Client officiel (non disponible):
	/*
	  public String getDevice(String deviceType, String deviceId,  String qpSensorType, String qpFrom, String qpTo){
		Client client = ClientBuilder.newClient();
		WebTarget wb = client.target(urlBase);
		WebTarget targetUpdated = wb
				.path("/"+deviceType+"/device/stats/"+deviceId)
				.queryParam("from", qpFrom)
				.queryParam("to", qpTo)
				.queryParam("sensorType", qpSensorType)
				.headers(<autorisation>);
		String response = targetUpdated.request("application/json").get(String.class);
		return response;
		
	}*/
	 
}
