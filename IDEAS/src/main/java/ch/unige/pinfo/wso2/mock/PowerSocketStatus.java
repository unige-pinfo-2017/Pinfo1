package ch.unige.pinfo.wso2.mock;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "Mock_StatusPowerSocket")
public class PowerSocketStatus implements Serializable{
	
	/**
	 *  The serial-id
	 */
	private static final long serialVersionUID = -1327307114374810283L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "Id")
	private Long Id;
	
	@NotNull
	private String deviceId;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
