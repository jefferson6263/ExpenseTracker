package com.fdmgroup.JeffersonExpenseTracker.Controller;

import com.fdmgroup.JeffersonExpenseTracker.Model.Expense;

public class ExpenseRequest {
    private Expense newExpense;
    private String[] categories;
    
	public Expense getNewExpense() {
		return newExpense;
	}
	public void setNewExpense(Expense newExpense) {
		this.newExpense = newExpense;
	}
	public String[] getCategories() {
		return categories;
	}
	public void setCategories(String[] someArray) {
		this.categories = someArray;
	}

}