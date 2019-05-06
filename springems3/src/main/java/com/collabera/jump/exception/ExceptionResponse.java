package com.collabera.jump.exception;

import java.util.Date;

public class ExceptionResponse {
	private String details;

	private String message;

	private Date timestamp;

	public String getDetails() {
		return details;
	}

	public ExceptionResponse() {
		this.timestamp = new Date();
	}

	public ExceptionResponse(String details, String message) {
		super();
		this.details = details;
		this.message = message;
		this.timestamp = new Date();
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

//	public void setTimestamp(Date timestamp) {
//		this.timestamp = timestamp;
//	}

}
