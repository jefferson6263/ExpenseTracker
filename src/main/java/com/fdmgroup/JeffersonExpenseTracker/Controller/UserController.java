package com.fdmgroup.JeffersonExpenseTracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.fdmgroup.JeffersonExpenseTracker.Model.User;

import com.fdmgroup.JeffersonExpenseTracker.Service.UserService;

public class UserController {
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {		
		super();
		this.userService = userService;
		
	}
	
	
	@GetMapping("users/{userId}")
	public User findUserById(@PathVariable int userId) {
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


}
