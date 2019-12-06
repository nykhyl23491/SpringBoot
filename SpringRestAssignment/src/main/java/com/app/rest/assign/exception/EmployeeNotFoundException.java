package com.app.rest.assign.exception;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(final String message) {
		super(message);
		System.out.println("-----------EmployeeNotFoundException()" + message);

	}

}
