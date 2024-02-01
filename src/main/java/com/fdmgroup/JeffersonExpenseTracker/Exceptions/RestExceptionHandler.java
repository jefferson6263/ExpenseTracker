package com.fdmgroup.JeffersonExpenseTracker.Exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class RestExceptionHandler {
	
	/* Have to use these next lines of code for every exception you create */
	
	// The exception we want to handle
	@ExceptionHandler(CategoryIdException.class)
	// The HTTP status you want associated - eg. 404 in this case
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorResponse handle1(CategoryIdException e) {
		return new ApiErrorResponse(e.getMessage());
	}
	
	@ExceptionHandler(EmailInUseException.class)
	@ResponseStatus(HttpStatus.IM_USED)
	public ApiErrorResponse handle2(EmailInUseException e) {
		return new ApiErrorResponse(e.getMessage());
	}
	
	@ExceptionHandler(ExpenseIdException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorResponse handle(ExpenseIdException e) {
		return new ApiErrorResponse(e.getMessage());
	}
	
	@ExceptionHandler(UserIdException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorResponse handle(UserIdException e) {
		return new ApiErrorResponse(e.getMessage());
	}
	
	

}

