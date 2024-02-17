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
import com.fdmgroup.JeffersonExpenseTracker.Service.UserService;

@Service
public class DataLoader implements ApplicationRunner {
	
	private UserRepository userRepo;
	private CategoryRepository categoryRepo;
	private ExpenseRepository expenseRepo;
	private UserService userService;
	
		
	@Autowired
	public DataLoader(UserRepository userRepository, CategoryRepository categoryRepository, ExpenseRepository expenseRepository, UserService userService) {
		super();
		this.userRepo = userRepository;
		this.categoryRepo = categoryRepository;
		this.expenseRepo = expenseRepository;
		this.userService = userService;
		
	}
 
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		User user2 = new User("Jane", "Doe", "janedoe@live.com", "janedoe3", "pass123 ");
		User user3 = new User("Mike", "Yao", "mikeyao@live.com", "mikeyao", "java23");
		User user4 = new User("Rachel", "Perry", "rachelperry@live.com", "rperry1", "moo89");
		User user5 = new User("aa", "aa", "aa", "aa", "aa");
		User user6 = new User("firstNameTest", "lastNameTest", "a", "test1", "a");

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

		
		Expense exp9 = new Expense("Petrol Fee", 350, "Why is petty so expensive", 
				LocalDate.of(2024, 1, 4), LocalDate.of(2024, 1, 4));
		
		Expense exp10 = new Expense("Grocery Shopping", 150, "Stocked up for the month",
	            LocalDate.of(2024, 2, 10), LocalDate.of(2024, 2, 10));
		
		Expense exp11 = new Expense("Electric Bill", 200, "Used aircon extensively this month",
	            LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 30));
		
		Expense exp12 = new Expense("Gym Membership", 80, "Monthly subscription",
	            LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 1));
		
		Expense exp13 = new Expense("Medical Checkup", 120, "Routine checkup",
	            LocalDate.of(2024, 2, 9), LocalDate.of(2024, 2, 9));
		
		

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
		
		exp9.setCategories(cat6);
		exp9.setCategories(cat4);
		exp10.setCategories(cat4);
		exp10.setCategories(cat5);
		exp11.setCategories(cat4);
		exp12.setCategories(cat2);


		exp1.setUser(user1);
		exp2.setUser(user2);
		exp3.setUser(user3); 
		exp4.setUser(user4);
		exp5.setUser(user3);
		exp6.setUser(user3);
		exp7.setUser(user4);
		exp8.setUser(user2);
		
		
 
		exp8.setUser(user4);
		exp2.setUser(user4);
		exp3.setUser(user4);
		exp9.setUser(user6);
		exp10.setUser(user6);
		exp11.setUser(user6); 
		exp12.setUser(user6);
		exp13.setUser(user6);
		
		
		userService.register(user1);
		userService.register(user2);
		userService.register(user3); 
		userService.register(user4);
		userService.register(user5);
		userService.register(user6);
		
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
		expenseRepo.save(exp9);
		expenseRepo.save(exp10);
		expenseRepo.save(exp11);
		expenseRepo.save(exp12);
		expenseRepo.save(exp13);

	

	}


	
}