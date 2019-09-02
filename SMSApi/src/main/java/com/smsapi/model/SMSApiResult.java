package com.smsapi.model;


public class SMSApiResult {
	private String message;
	private String error;
	
	public SMSApiResult(String message, String error) {
		super();
		this.message = message;
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
