package com.fdmgroup.JeffersonExpenseTracker;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.JeffersonExpenseTracker.Controller.ExpenseController;
import com.fdmgroup.JeffersonExpenseTracker.Model.Expense;
import com.fdmgroup.JeffersonExpenseTracker.Service.ExpenseService;

@ExtendWith(MockitoExtension.class)
public class ExpenseControllerTest {

	@Mock
	ExpenseService expenseService;
	
	
	ExpenseController expenseController;
	
	@BeforeEach
	void setup() {
		this.expenseController = new ExpenseController(expenseService);
	}
	
	
	
	@Test
	void findExpenseById_test() {
		
		Expense exp1 = new Expense("Water Fee", 100.23,"water fee, a little higher than usual as a friend was staying over", 
				LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 17));
		
		when(expenseService.findByExpenseId(1)).thenReturn(exp1);
		Expense foundExpense = expenseController.findExpenseById(1);
		assertSame(exp1, foundExpense);
		verify(expenseService, times(1)).findByExpenseId(1);
	}
	
	@Test
	void createExpense_test() {
		Expense exp1 = new Expense("Water Fee", 100.23,"water fee, a little higher than usual as a friend was staying over", 
				LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 17));
		expenseController.createExpense(exp1);
		verify(expenseService, times(1)).save(exp1);
	}
	
	@Test
	void updateExpense_test() {
		Expense exp1 = new Expense("Water Fee", 100.23,"water fee, a little higher than usual as a friend was staying over", 
				LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 17));
		expenseController.updateExpense(exp1);
		verify(expenseService, times(1)).update(exp1);
	}
	
	@Test
	void deleteExpense_test() {
		expenseController.deleteExpense(1);
		verify(expenseService, times(1)).deleteById(1);
	}
	
	@Test
	void findAllExpenses() {
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
		
		List<Expense> allExpenses= new ArrayList<>();
		allExpenses.add(exp1);
		allExpenses.add(exp2);
		allExpenses.add(exp3);
		allExpenses.add(exp4);
		allExpenses.add(exp5);
		allExpenses.add(exp6);
		allExpenses.add(exp7);
		allExpenses.add(exp8);
		
		when(expenseService.findAll()).thenReturn(allExpenses);
		assertSame(expenseController.findAllExpenses(), allExpenses);
		verify(expenseService, times(1)).findAll();
	}
	

}
