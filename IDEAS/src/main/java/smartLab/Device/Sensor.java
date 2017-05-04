package smartLab.Device;

abstract public class Sensor {
	protected String value;
	
	String getValue(){
		return value; 
	};
	void setValue(String newVal){
		this.value = newVal;
	};
}

