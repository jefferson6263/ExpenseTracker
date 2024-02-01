package com.fdmgroup.JeffersonExpenseTracker;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.fdmgroup.JeffersonExpenseTracker.Dao.CategoryRepository;
import com.fdmgroup.JeffersonExpenseTracker.Dao.ExpenseRepository;
import com.fdmgroup.JeffersonExpenseTracker.Dao.UserRepository;
import com.fdmgroup.JeffersonExpenseTracker.Model.Category;
import com.fdmgroup.JeffersonExpenseTracker.Model.Expense;
import com.fdmgroup.JeffersonExpenseTracker.Model.User;

@Service
public class DataLoader implements ApplicationRunner {
	
	//private WebApplicationContext context;
		// inject multiple repos here
		// can use save all to put entries from array into repo
	private UserRepository userRepo;
	private CategoryRepository categoryRepo;
	private ExpenseRepository expenseRepo;
		
		
	@Autowired
	public DataLoader(UserRepository userRepository, CategoryRepository categoryRepository, ExpenseRepository expenseRepository) {
		super();
		this.userRepo = userRepository;
		this.categoryRepo = categoryRepository;
		this.expenseRepo = expenseRepository;
	}

	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		

		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		User user2 = new User("Jane", "Doe", "janedoe@live.com", "janedoe3", "pass123 ");
		User user3 = new User("Mike", "Yao", "mikeyao@live.com", "mikeyao", "java23");
		User user4 = new User("Rachel", "Perry", "rachelperry@live.com", "rperry1", "moo89");

		Expense exp1 = new Expense("Water Fee", 100.23,"water fee, a little higher than usual as a friend was staying over", 
				LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 17));
		
		Expense exp2 = new Expense("Office Supplies", 6.34, "Purchase of essential office supplies",
				LocalDate.of(2024, 1, 4), LocalDate.of(2024, 1, 4));
		
		Expense exp3 = new Expense("Travel Expense", 25.30, "Costs related to business travel",
				LocalDate.of(2023, 11, 10), LocalDate.of(2023, 11, 17));
		
		Expense exp4 = new Expense("Dining Out", 70.85, "Cost of meals and snacks from restaurants",
				LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 1));
		
		Expense exp5 = new Expense("Personal Care", 120.10, "Expenditure on toiletries, skincare products",
				LocalDate.of(2024, 1, 3), LocalDate.of(2024, 1, 3));
		
		Expense exp6 = new Expense("Hobbies", 40.30, "Costs associated with pursuing hobbies and recreational activities",
				LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15));
		
		Expense exp7 = new Expense("New Tv", 500.40, "Purchased new TV", 
				LocalDate.of(2024, 1, 13), LocalDate.of(2024, 1, 13));
		
		Expense exp8 = new Expense("NetFlix", 15.75, "Monthly Netflix subscription", 
				LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 30));

		Category cat1 = new Category("Technology");
		Category cat2 = new Category("Leisure");
		Category cat3 = new Category("Cleaning");
		Category cat4 = new Category("Bills");
		Category cat5 = new Category("Food");
		Category cat6 = new Category("Travel");
		Category cat7 = new Category("Supplies");

		exp1.setCategories(cat4);
		exp2.setCategories(cat7);
		exp3.setCategories(cat6);
		exp4.setCategories(cat2);
		exp4.setCategories(cat5);
		exp5.setCategories(cat7);
		exp6.setCategories(cat2);
		exp7.setCategories(cat1);
		exp7.setCategories(cat2);
		exp8.setCategories(cat1);
		exp8.setCategories(cat2);

//		user1.setExpenses(exp1);
//		user2.setExpenses(exp2);
//		user3.setExpenses(exp3);
//		user4.setExpenses(exp4);
//		user3.setExpenses(exp5);
//		user3.setExpenses(exp6);
//		user4.setExpenses(exp7);
//		user2.setExpenses(exp8);

		exp1.setUser(user1);
		exp2.setUser(user2);
		exp3.setUser(user3);
		exp4.setUser(user4);
		exp5.setUser(user3);
		exp6.setUser(user3);
		exp7.setUser(user4);
		exp8.setUser(user2);
		
		userRepo.save(user1);
		userRepo.save(user2);
		userRepo.save(user3);
		userRepo.save(user4);
		
		categoryRepo.save(cat1);
		categoryRepo.save(cat2);
		categoryRepo.save(cat3);
		categoryRepo.save(cat4);
		categoryRepo.save(cat5);
		categoryRepo.save(cat6);
		categoryRepo.save(cat7);
		
		expenseRepo.save(exp1);
		expenseRepo.save(exp2);
		expenseRepo.save(exp3);
		expenseRepo.save(exp4);
		expenseRepo.save(exp5);
		expenseRepo.save(exp6);
		expenseRepo.save(exp7);
		expenseRepo.save(exp8);
	

	}


	
}