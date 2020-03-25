package com.scaleSampark.response;

public class ResponseStatus {
	private String message;
	private int status;
	public ResponseStatus(String message, int status) {
		super();
		this.setMessage(message);
		this.setStatus(status);
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
