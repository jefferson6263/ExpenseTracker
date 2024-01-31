package com.fdmgroup.JeffersonExpenseTracker;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.JeffersonExpenseTracker.Service.CategoryService;
import com.fdmgroup.JeffersonExpenseTracker.Service.ExpenseService;
import com.fdmgroup.JeffersonExpenseTracker.Service.UserService;

@ExtendWith(MockitoExtension.class)
public class MainControllerTest {
	
	@Mock
	UserService userService;
	
	@Mock
	CategoryService categoryService;
	
	@Mock
	ExpenseService expenseService;
	
	
}
