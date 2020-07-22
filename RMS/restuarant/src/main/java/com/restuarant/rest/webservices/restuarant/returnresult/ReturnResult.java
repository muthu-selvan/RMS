package com.restuarant.rest.webservices.restuarant.returnresult;

import com.restuarant.rest.webservices.restuarant.constants.ReturnResultStatus;

public class ReturnResult {
	
	private ReturnResultStatus status;
	private String message;
	
	public ReturnResult(ReturnResultStatus status) {
		super();
		this.status = status;
	}
	
	public ReturnResult(ReturnResultStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public ReturnResultStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ReturnResultStatus status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
