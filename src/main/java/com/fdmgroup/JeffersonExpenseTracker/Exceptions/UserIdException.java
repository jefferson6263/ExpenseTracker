package com.fdmgroup.JeffersonExpenseTracker.Exceptions;

public class UserIdException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserIdException (String message) {
		super(message);
	}
}
