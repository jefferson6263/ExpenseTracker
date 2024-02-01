package com.fdmgroup.JeffersonExpenseTracker.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fdmgroup.JeffersonExpenseTracker.Dao.ExpenseRepository;
import com.fdmgroup.JeffersonExpenseTracker.Exceptions.ExpenseIdException;
import com.fdmgroup.JeffersonExpenseTracker.Model.Expense;

@Service
public class ExpenseService {
	
	private ExpenseRepository expenseRepo;

	@Autowired
	public ExpenseService(ExpenseRepository expenseRepository) {
		super();
		this.expenseRepo = expenseRepository;
	}

	public List<Expense> findAll() {
		return this.expenseRepo.findAll();
	}

	public Expense findByExpenseId(int expenseId) {

		return this.expenseRepo.findById(expenseId).orElseThrow(() -> new ExpenseIdException("Expense with id " + expenseId + " not found"));
	}
	
	public List<Expense> findUserExpenses(int userId) {
		return this.expenseRepo.findExpensesByUserId(userId);
	}
	

	public void save(Expense newExpense) {
		this.expenseRepo.save(newExpense);

	}

	public void update(Expense newExpense) {

		if (expenseRepo.existsById(newExpense.getId())) {
			Expense e = expenseRepo.findById(newExpense.getId()).get();
	
			newExpense.setUser(e.getUser());
			this.expenseRepo.save(newExpense);
			return;
		}

		throw new ExpenseIdException("Must provide a valid expenseId for updating");
	}

	public void deleteById(int expenseId) {

		if (expenseRepo.existsById(expenseId)) {
			this.expenseRepo.deleteById(expenseId);
			return;
		}
		
		throw new ExpenseIdException("Must provide a valid expenseId for deleting");

	}
	
	
}
