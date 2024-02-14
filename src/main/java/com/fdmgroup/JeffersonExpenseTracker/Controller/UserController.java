package com.fdmgroup.JeffersonExpenseTracker.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.JeffersonExpenseTracker.Model.Expense;
import com.fdmgroup.JeffersonExpenseTracker.Model.User;

import com.fdmgroup.JeffersonExpenseTracker.Service.UserService;

@RestController
public class UserController {
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {		
		super();
		this.userService = userService;
		
	}
	
	
	@GetMapping("getuserbyid/{userId}")
	public User findUserById(@PathVariable int userId) {
		
		return userService.findById(userId);
	}
	
	@GetMapping("getuserbyemail/{email}")
	public User findUserByEmail(@PathVariable String email) {
		
		return userService.findByEmail(email);
	}


	@PostMapping("users")
	public void createUser(@RequestBody User newUser) {
		userService.register(newUser);
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
	
	@GetMapping("getuserbytoken")
	public User findUserByToken(Authentication auth) {
		return userService.findUserByToken(auth);
	} 
	
	


}
