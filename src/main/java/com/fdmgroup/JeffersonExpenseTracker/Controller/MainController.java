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

import com.fdmgroup.JeffersonExpenseTracker.Model.User;
import com.fdmgroup.JeffersonExpenseTracker.Service.UserService;

@RestController
public class MainController {

	private UserService userService;

	@Autowired
	public MainController(UserService userService) {
		super();
		this.userService = userService;
	}

//	@GetMapping("/users/search")
//	public List<User> searchByName(@RequestParam String q) {
//		return userService.findpartialMatch(q);
//	}

	@GetMapping("users")
	public List<User> firstEndpoint() {
		// return "hello world";
		return userService.findAll();
	}

	@GetMapping("users/{userId}")
	public User findById(@PathVariable int userId) {
		// return "hello world";
		return userService.findById(userId);
	}

	// you could: if you want to creat ea user don't pass an id
	// if you weant to change a user provide an id
	@PostMapping("users")
	public void createNew(@RequestBody User newUser) {
		System.out.println(newUser);
		userService.save(newUser);
	}

	@PutMapping("users")
	public void updateUser(@RequestBody User newUser) {
		System.out.println(newUser);
		userService.update(newUser);
	}

	@DeleteMapping("users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userService.deleteById(userId);
	}

}
