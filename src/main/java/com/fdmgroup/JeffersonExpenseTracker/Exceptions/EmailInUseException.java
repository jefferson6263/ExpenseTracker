package com.fdmgroup.JeffersonExpenseTracker.Exceptions;

public class EmailInUseException extends RuntimeException {

	public EmailInUseException (String message) {
		super(message);
	}
}
