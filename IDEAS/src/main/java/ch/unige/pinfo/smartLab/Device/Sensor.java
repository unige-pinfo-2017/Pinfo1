package ch.unige.pinfo.smartLab.Device;

abstract class Sensor {
	protected String value;
	
	String getValue(){
		return value; 
	};
	void setValue(String newVal){
		this.value = newVal;
	};
}

