package com.app.rest.assign.exception;

public class DepartmentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DepartmentNotFoundException(final String message) {
		super(message);
	}

}
