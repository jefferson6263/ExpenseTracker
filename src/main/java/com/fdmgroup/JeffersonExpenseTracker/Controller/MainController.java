package com.fdmgroup.JeffersonExpenseTracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.JeffersonExpenseTracker.Dao.CategoryRepository;
import com.fdmgroup.JeffersonExpenseTracker.Dao.ExpenseRepository;
import com.fdmgroup.JeffersonExpenseTracker.Model.Category;
import com.fdmgroup.JeffersonExpenseTracker.Model.Expense;
import com.fdmgroup.JeffersonExpenseTracker.Model.User;
import com.fdmgroup.JeffersonExpenseTracker.Service.CategoryService;
import com.fdmgroup.JeffersonExpenseTracker.Service.ExpenseService;
import com.fdmgroup.JeffersonExpenseTracker.Service.UserService;

@RestController
public class MainController {

	private UserService userService;
	private CategoryService categoryService;
	private ExpenseService expenseService;

	@Autowired
	public MainController(UserService userService, CategoryService categoryService, ExpenseService expenseService) {
		super();
		this.userService = userService;
		this.categoryService = categoryService;
		this.expenseService = expenseService;
	}
	

	// CRUD for users
	
	
	@GetMapping("users/{userId}")
	public User findUserById(@PathVariable int userId) {
		System.out.println("hello1");
		return userService.findById(userId);
	}

	// you could: if you want to creat ea user don't pass an id
	// if you weant to change a user provide an id
	
	@PostMapping("users")
	public void createUser(@RequestBody User newUser) {
		userService.save(newUser);
	}

	@PutMapping("users")
	public void updateUser(@RequestBody User newUser) {
		userService.update(newUser);
	}

	@DeleteMapping("users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userService.deleteById(userId);
	}
	
	@GetMapping("allusers")
	public List<User> findAllUsers() {
		return userService.findAll();
	}

	
	// CRUD for expenses
	
	@GetMapping("expenses/{expenseId}")
	public Expense findExpenseById(@PathVariable int expenseId) {
		return expenseService.findById(expenseId);
	}
	
	@PostMapping("expenses")
	public void createExpense(@RequestBody Expense newExpense) {
		expenseService.save(newExpense);
	}
	
	@PutMapping("expenses")
	public void updateExpense(@RequestBody Expense newExpense) {
		expenseService.update(newExpense);
	}
	
	@DeleteMapping("expenses/{expenseId}")
	public void deleteExpense(@PathVariable int userId) {
		expenseService.deleteById(userId);
	}
	
	@GetMapping("allexpenses")
	public List<Expense> findAllExpenses() {
		return expenseService.findAll();
	}
	// CRUD for categories
	
	@GetMapping("categories/{categorieId}")
	public Category findCategoryById(@PathVariable int categoryId) {
		return categoryService.findById(categoryId);
	}
	
	@PostMapping("categories")
	public void createCategory(@RequestBody Category newCategory) {
		categoryService.save(newCategory);
	}
	
	@PutMapping("categories")
	public void updateCategory(@RequestBody Category newCategory) {
		categoryService.update(newCategory);
	}
	
	@DeleteMapping("categories/{categorieId}")
	public void deleteCategory(@PathVariable int categoryId) {
		categoryService.deleteById(categoryId);
	}
	
	@GetMapping("allcategories")
	public List<Category> findAllCategories() {
		return categoryService.findAll();
	}
	
}
