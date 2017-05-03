package ch.unige.pinfo.wso2.Dom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Values {

	@SerializedName("meta_owner")
	@Expose
	private String metaOwner;
	
	@SerializedName("meta_deviceType")
	@Expose
	private String metaDeviceType;
	
	@SerializedName("meta_time")
	@Expose
	private String metaTime;
	
	@SerializedName("powerSensor")
	@Expose
	private String powerSensor;
	
	@SerializedName("_version")
	@Expose
	private String version;
	
	@SerializedName("meta_deviceId")
	@Expose
	private String metaDeviceId;

	// Getters & setters
	public String getMetaOwner() {
		return metaOwner;
	}

	public void setMetaOwner(String metaOwner) {
		this.metaOwner = metaOwner;
	}

	public String getMetaDeviceType() {
		return metaDeviceType;
	}

	public void setMetaDeviceType(String metaDeviceType) {
		this.metaDeviceType = metaDeviceType;
	}

	public String getMetaTime() {
		return metaTime;
	}

	public void setMetaTime(String metaTime) {
		this.metaTime = metaTime;
	}

	public String getPowerSensor() {
		return powerSensor;
	}

	public void setPowerSensor(String powerSensor) {
		this.powerSensor = powerSensor;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMetaDeviceId() {
		return metaDeviceId;
	}

	public void setMetaDeviceId(String metaDeviceId) {
		this.metaDeviceId = metaDeviceId;
	}

}