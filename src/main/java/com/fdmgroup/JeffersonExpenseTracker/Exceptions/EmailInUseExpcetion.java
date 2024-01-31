package com.fdmgroup.JeffersonExpenseTracker.Exceptions;

public class EmailInUseExpcetion extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailInUseExpcetion (String message) {
		super(message);
	}
}
