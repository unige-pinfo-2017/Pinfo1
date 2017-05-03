package ch.unige.pinfo.wso2.Dom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class State {

	@SerializedName("values")
	@Expose
	private Values values;
	
	@SerializedName("id")
	@Expose
	private String id;

	// Getters & setters
	public Values getValues() {
		return values;
	}
	public void setValues(Values values) {
		this.values = values;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
