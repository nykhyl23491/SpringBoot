package com.app.rest.assign.advice;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExceptionResponse {
	private int statusCode;
	private String errorMessage;
	private String requestURI;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(final String requestURI) {
		this.requestURI = requestURI;
	}

}
