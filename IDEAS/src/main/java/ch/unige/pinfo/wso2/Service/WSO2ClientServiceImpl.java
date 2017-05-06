package ch.unige.pinfo.wso2.Service;

public class WSO2ClientServiceImpl implements WSO2ClientService{
	
	@Override
	public String getValue(String deviceId){
		if (deviceId.equals("id1"))
			return "1";
		if (deviceId.equals("id2"))
			return "2";
		if (deviceId.equals("id3"))
			return "3";
		if (deviceId.equals("id4"))
			return "4";
		if (deviceId.equals("id5"))
			return "5";
		if (deviceId.equals("id6"))
			return "6";
		if (deviceId.equals("id7"))
			return "7";
		if (deviceId.equals("id8"))
			return "8";
		if (deviceId.equals("id9"))
			return "9";
		return "";
	}
	

}
