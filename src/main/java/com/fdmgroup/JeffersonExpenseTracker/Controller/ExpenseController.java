package com.fdmgroup.JeffersonExpenseTracker.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.JeffersonExpenseTracker.Model.Expense;
import com.fdmgroup.JeffersonExpenseTracker.Service.ExpenseService;

@RestController
public class ExpenseController {

	private ExpenseService expenseService;

	@Autowired
	public ExpenseController(ExpenseService expenseService) {
		super();

		this.expenseService = expenseService;
	}
	
	@GetMapping("expenses/{expenseId}")
	public Expense findExpenseById(@PathVariable int expenseId) {
		return expenseService.findByExpenseId(expenseId);
	}
	
	@PostMapping("expenses")
	public void createExpense(@RequestBody Expense newExpense) {
		expenseService.save(newExpense);
	}
	
	@PutMapping("expenses")
	public void updateExpense(@RequestBody Expense newExpense) {
		//gets rid of the user id
		expenseService.update(newExpense);
	}
	
	@DeleteMapping("expenses/{expenseId}")
	public void deleteExpense(@PathVariable int expenseId) {
		expenseService.deleteById(expenseId);
	} 
	
	@GetMapping("allexpenses")
	public List<Expense> findAllExpenses() {
		return expenseService.findAll();  
	}

}
