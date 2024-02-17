package com.fdmgroup.JeffersonExpenseTracker;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import com.fdmgroup.JeffersonExpenseTracker.Dao.ExpenseRepository;
import com.fdmgroup.JeffersonExpenseTracker.Exceptions.ExpenseIdException;
import com.fdmgroup.JeffersonExpenseTracker.Model.Expense;
import com.fdmgroup.JeffersonExpenseTracker.Model.User;
import com.fdmgroup.JeffersonExpenseTracker.Service.CategoryService;
import com.fdmgroup.JeffersonExpenseTracker.Service.ExpenseService;
import com.fdmgroup.JeffersonExpenseTracker.Service.UserService;




@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {
	
	@Mock
	ExpenseRepository expenseRepo;
	
	@Mock
	Authentication auth;
	
	@Mock
	UserService userService;
	
	@Mock
	CategoryService categoryService;
	
	
	ExpenseService expenseService;
	
	
	@BeforeEach
	void setup() {
		
		this.expenseService = new ExpenseService(expenseRepo, userService, categoryService);
	}
	
	@Test
	void save_expense_test() {
		
		Expense exp1 = new Expense("Water Fee", 100.23,"water fee, a little higher than usual as a friend was staying over", 
				LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 17));
		
		expenseService.save(exp1);
		verify(expenseRepo, times(1)).save(exp1);
	}
	
	@Test
	void save_multiple_expenses_test() {

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
		
		expenseService.save(exp1);
		expenseService.save(exp2);
		expenseService.save(exp3);
		expenseService.save(exp4);
		expenseService.save(exp5);
		expenseService.save(exp6);
		expenseService.save(exp7);
		expenseService.save(exp8);
		
		verify(expenseRepo, times(1)).save(exp1);
		verify(expenseRepo, times(1)).save(exp2);
		verify(expenseRepo, times(1)).save(exp3);
		verify(expenseRepo, times(1)).save(exp4);
		verify(expenseRepo, times(1)).save(exp5);
		verify(expenseRepo, times(1)).save(exp6);
		verify(expenseRepo, times(1)).save(exp7);
		verify(expenseRepo, times(1)).save(exp8);
				
	}
	
	@Test
	void find_all_expense_test() {

		Expense exp1 = new Expense("Personal Care", 120.10, "Expenditure on toiletries, skincare products",
				LocalDate.of(2024, 1, 3), LocalDate.of(2024, 1, 3));
		
		Expense exp2 = new Expense("Hobbies", 40.30, "Costs associated with pursuing hobbies and recreational activities",
				LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15));
		
		Expense exp3 = new Expense("New Tv", 500.40, "Purchased new TV", 
				LocalDate.of(2024, 1, 13), LocalDate.of(2024, 1, 13));
		
		Expense exp4 = new Expense("NetFlix", 15.75, "Monthly Netflix subscription", 
				LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 30));
		
	
		
		List<Expense> allExpenses = new ArrayList<>();
		
		allExpenses.add(exp1);
		allExpenses.add(exp2);
		allExpenses.add(exp3);
		allExpenses.add(exp4);

		
		when(expenseRepo.findAll()).thenReturn(allExpenses);
		
		List<Expense> foundExpenses = expenseRepo.findAll();
	
		verify(expenseRepo, times(1)).findAll();
		assertSame(foundExpenses, allExpenses);
	}
	
	@Test
	void find_expense_by_expense_id_test() {

		Optional<Expense> exp1 = Optional.of(new Expense("Water Fee", 100.23,"water fee, a little higher than usual as a friend was staying over", 
				LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 17)));
		
		Optional<Expense> exp2 = Optional.of(new Expense("Office Supplies", 6.34, "Purchase of essential office supplies",
				LocalDate.of(2024, 1, 4), LocalDate.of(2024, 1, 4)));
		
		Optional<Expense> exp3 = Optional.of(new Expense("Travel Expense", 25.30, "Costs related to business travel",
				LocalDate.of(2023, 11, 10), LocalDate.of(2023, 11, 17)));
		
		Optional<Expense> exp4 = Optional.of(new Expense("Dining Out", 70.85, "Cost of meals and snacks from restaurants",
				LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 1)));
		
		
		when(expenseRepo.findById(1)).thenReturn(exp1);
		when(expenseRepo.findById(2)).thenReturn(exp2);
		when(expenseRepo.findById(3)).thenReturn(exp3);
		when(expenseRepo.findById(4)).thenReturn(exp4);

		assertSame(exp1.get(), expenseService.findByExpenseId(1));
		assertSame(exp2.get(), expenseService.findByExpenseId(2));
		assertSame(exp3.get(), expenseService.findByExpenseId(3));
		assertSame(exp4.get(), expenseService.findByExpenseId(4));
		
		verify(expenseRepo, times(1)).findById(1);
		verify(expenseRepo, times(1)).findById(2);
		verify(expenseRepo, times(1)).findById(3);
		verify(expenseRepo, times(1)).findById(4);
	}
	
	@Test
	void find_expense_by_expense_id_fail_test() {

		assertThrows(ExpenseIdException.class, () -> expenseService.findByExpenseId(1));
		verify(expenseRepo, times(1)).findById(1);
	}
	
	@Test
	void find_all_expenses_belonging_to_user_test() {
		
		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		User user2 = new User("Jane", "Doe", "janedoe@live.com", "janedoe3", "pass123 ");
		

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
		
		List<Expense> user1Expenses = new ArrayList<>();
		List<Expense> user2Expenses = new ArrayList<>();
		
		user1Expenses.add(exp1);
		user1Expenses.add(exp6);
		user1Expenses.add(exp3);
		
		user2Expenses.add(exp2);
		user2Expenses.add(exp4);
		user2Expenses.add(exp5);
		
		when(expenseRepo.findExpensesByUserId(1)).thenReturn(user1Expenses);
		when(expenseRepo.findExpensesByUserId(2)).thenReturn(user2Expenses);
		
		List<Expense> foundUser1Expenses = expenseService.findUserExpenses(1);
		List<Expense> foundUser2Expenses = expenseService.findUserExpenses(2);
		
		
		verify(expenseRepo, times(1)).findExpensesByUserId(1);
		verify(expenseRepo, times(1)).findExpensesByUserId(2);
		
		assertSame(user1Expenses, foundUser1Expenses);
		assertSame(user2Expenses, foundUser2Expenses);
	}
	
	
	@Test
	void update_expense_test() {

		Optional<Expense> exp1 = Optional.of(new Expense(" Personal Care", 120.10, "not updated",
				LocalDate.of(2024, 1, 3), LocalDate.of(2024, 1, 3)));
		
		Expense updatedExp1 = new Expense("Updated Personal Care", 120.10, "updated",
				LocalDate.of(2024, 1, 3), LocalDate.of(2024, 1, 3));
		
	
		updatedExp1.setId(1);
		String[] cat = {};
	
		when(expenseRepo.existsById(1)).thenReturn(true);

		expenseService.update(auth, updatedExp1, cat);

		verify(expenseRepo, times(1)).existsById(1);
		verify(expenseRepo, times(1)).save(updatedExp1);
	
	}
	
	@Test
	void update_expense_fail_test() {

		
		Expense updatedExp1 = new Expense("Updated Personal Care", 120.10, "updated",
				LocalDate.of(2024, 1, 3), LocalDate.of(2024, 1, 3));
		
		updatedExp1.setId(1);
		
		when(expenseRepo.existsById(1)).thenReturn(false);

		assertThrows(ExpenseIdException.class, () -> expenseService.update(auth, updatedExp1, null));
		verify(expenseRepo, times(1)).existsById(1);
		verify(expenseRepo, times(0)).save(updatedExp1);
	
	}
	
	@Test
	void delete_expense_test() {
		
		when(expenseRepo.existsById(1)).thenReturn(true);
		
		expenseService.deleteById(1);
		
		verify(expenseRepo, times(1)).existsById(1);
		verify(expenseRepo, times(1)).deleteById(1);

	}
	
	@Test
	void delete_expense_fail_test() {
		
		when(expenseRepo.existsById(1)).thenReturn(false);
		
		assertThrows(ExpenseIdException.class, () -> expenseService.deleteById(1));
		verify(expenseRepo, times(1)).existsById(1);
		verify(expenseRepo, times(0)).deleteById(1);
	}
	
	

}
