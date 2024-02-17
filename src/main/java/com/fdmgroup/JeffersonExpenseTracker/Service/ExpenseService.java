package com.fdmgroup.JeffersonExpenseTracker.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.fdmgroup.JeffersonExpenseTracker.Dao.ExpenseRepository;
import com.fdmgroup.JeffersonExpenseTracker.Exceptions.ExpenseIdException;
import com.fdmgroup.JeffersonExpenseTracker.Model.Category;
import com.fdmgroup.JeffersonExpenseTracker.Model.Expense;




@Service
public class ExpenseService {
	
	private ExpenseRepository expenseRepo;
	private UserService userService;
	private CategoryService categoryService;

	@Autowired
	public ExpenseService(ExpenseRepository expenseRepository, UserService userService, CategoryService categoryService) {
		super();
		this.expenseRepo = expenseRepository;
		this.userService = userService;
		this.categoryService = categoryService;
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
 
	
	public void update(Authentication auth, Expense newExpense, String[] categories) {
		
		

		if (expenseRepo.existsById(newExpense.getId())) {

			newExpense.setUser(userService.findByEmail(auth.getName())); 
			
			ArrayList<Category> newCategories = new ArrayList<>();
			 
			for (String i : categories) {
				Category newCategory = new Category(i);
				newCategories.add(newCategory);
				categoryService.save(newCategory);
			} 
			
			newExpense.updateCategories(newCategories);
			expenseRepo.save(newExpense);
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

	public void addExpenseToUser(Authentication auth, Expense newExpense) {
		System.out.println(newExpense);
		newExpense.setUser(userService.findByEmail(auth.getName()));
		this.expenseRepo.save(newExpense);
		
		return;
	}

	public void addCategoriesToExpense(Expense newExpense, String[] categories) {
		
		
		for (String i : categories) {
			System.out.println(i);
			Category newCategory = new Category(i);
			 
			
			newExpense.setCategories(newCategory);
			categoryService.save(newCategory);
		} 
		
	}

	public List<Expense> findExpensesBelongToUser(int userId) {
		
		return expenseRepo.findExpensesByUserId(userId);
	}
	
	
}
