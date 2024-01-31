package com.fdmgroup.JeffersonExpenseTracker.Exceptions;

public class ExpenseIdException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpenseIdException (String message) {
		super(message);
	}
}
