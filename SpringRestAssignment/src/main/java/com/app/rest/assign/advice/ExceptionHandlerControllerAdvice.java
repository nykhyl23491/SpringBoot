package com.app.rest.assign.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.rest.assign.exception.DepartmentNotFoundException;
import com.app.rest.assign.exception.EmployeeNotFoundException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	
	@ExceptionHandler(DepartmentNotFoundException.class)
	@ResponseStatus(value= HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleDepartmentNotFoundException(final DepartmentNotFoundException exception,final  HttpServletRequest request)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		exceptionResponse.setErrorMessage(exception.getMessage());
		exceptionResponse.setRequestURI(request.getRequestURI());
		return exceptionResponse;
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(value= HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleEmployeeNotFoundException(final EmployeeNotFoundException exception,final  HttpServletRequest request)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		exceptionResponse.setErrorMessage(exception.getMessage());
		exceptionResponse.setRequestURI(request.getRequestURI());
		return exceptionResponse;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value= HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse handleException(final Exception exception,final  HttpServletRequest request)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		exceptionResponse.setErrorMessage("Bad Request");
		exceptionResponse.setRequestURI(request.getRequestURI());
		return exceptionResponse;
	}
}
