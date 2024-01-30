package com.fdmgroup.JeffersonExpenseTracker.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.JeffersonExpenseTracker.Dao.CategoryRepository;
import com.fdmgroup.JeffersonExpenseTracker.Dao.ExpenseRepository;
import com.fdmgroup.JeffersonExpenseTracker.Model.Category;
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

	public Expense findById(int expenseId) {

		return this.expenseRepo.findById(expenseId).orElseThrow(() -> new RuntimeException("user with id not found"));
	}

	public void save(Expense newExpense) {
		this.expenseRepo.save(newExpense);

	}

	public void update(Expense newExpense) {

		if (expenseRepo.existsById(newExpense.getId())) {
			this.expenseRepo.save(newExpense);
			return;
		}

		throw new RuntimeException("must provide a user id for put mapping");
	}

	public void deleteById(int expenseId) {
		this.expenseRepo.deleteById(expenseId);

	}
}
