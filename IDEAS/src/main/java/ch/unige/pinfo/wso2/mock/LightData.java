package ch.unige.pinfo.wso2.mock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "LightData")
public class LightData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "Id")
	private Long Id;
	
	@NotNull
	private String deviceId;
	
	@NotNull
	private String brightness;
	
	@NotNull
	private String hue;
	
	@NotNull
	private String saturation;
	
	@NotNull
	private String kelvin;
	
	@NotNull
	private String status;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getBrightness() {
		return brightness;
	}

	public void setBrightness(String brightness) {
		this.brightness = brightness;
	}

	public String getHue() {
		return hue;
	}

	public void setHue(String hue) {
		this.hue = hue;
	}

	public String getSaturation() {
		return saturation;
	}

	public void setSaturation(String saturation) {
		this.saturation = saturation;
	}

	public String getKelvin() {
		return kelvin;
	}

	public void setKelvin(String kelvin) {
		this.kelvin = kelvin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
