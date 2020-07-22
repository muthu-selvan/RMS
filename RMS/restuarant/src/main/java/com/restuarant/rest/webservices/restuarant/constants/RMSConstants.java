package com.restuarant.rest.webservices.restuarant.constants;
public enum RMSConstants {
	RESTORE("restore"),
	YES("Yes"),
	NO("No");
	
	private String value ;
	
	RMSConstants(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
