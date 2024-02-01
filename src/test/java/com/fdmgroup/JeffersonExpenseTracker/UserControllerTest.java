package com.fdmgroup.JeffersonExpenseTracker;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.JeffersonExpenseTracker.Controller.UserController;
import com.fdmgroup.JeffersonExpenseTracker.Model.User;
import com.fdmgroup.JeffersonExpenseTracker.Service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	@Mock
	UserService userService;
	
	
	UserController userController;
	
	@BeforeEach
	void setup() {
		this.userController = new UserController(userService);
	}
	
	@Test
	void findUserById_test() {
		
		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		
		when(userService.findById(1)).thenReturn(user1);
		User foundUser = userController.findUserById(1);
		assertSame(user1, foundUser);
		verify(userService, times(1)).findById(1);
	}
	
	@Test
	void createUser_test() {
		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		userController.createUser(user1);
		verify(userService, times(1)).save(user1);
	}
	
	@Test
	void updateUser_test() {
		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		userController.updateUser(user1);
		verify(userService, times(1)).update(user1);
	}
	
	@Test
	void deleteUser_test() {
		userController.deleteUser(1);
		verify(userService, times(1)).deleteById(1);
	}
	
	@Test
	void findAllUsers() {
		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		User user2 = new User("Jane", "Doe", "janedoe@live.com", "janedoe3", "pass123 ");
		User user3 = new User("Mike", "Yao", "mikeyao@live.com", "mikeyao", "java23");
		User user4 = new User("Rachel", "Perry", "rachelperry@live.com", "rperry1", "moo89");
		
		List<User> allUsers = new ArrayList<>();
		allUsers.add(user1);
		allUsers.add(user2);
		allUsers.add(user3);
		allUsers.add(user4);
		
		when(userService.findAll()).thenReturn(allUsers);
		assertSame(userController.findAllUsers(), allUsers);
		verify(userService, times(1)).findAll();
	}
	
	
}
