package com.fdmgroup.JeffersonExpenseTracker.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.JeffersonExpenseTracker.Dao.UserRepository;
import com.fdmgroup.JeffersonExpenseTracker.Exceptions.EmailInUseException;
import com.fdmgroup.JeffersonExpenseTracker.Exceptions.UserIdException;
import com.fdmgroup.JeffersonExpenseTracker.Model.User;

@Service
public class UserService {
	
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public List<User> findAll() {
		return this.userRepo.findAll();
	}

	public User findById(int userId) {
		
		return this.userRepo.findById(userId).orElseThrow(() -> new UserIdException("User with id " + userId + " not found"));

	}
	
	public User findByEmail(String email) {
		
		return this.userRepo.findByEmail(email).orElseThrow(() -> new UserIdException("User with id " + email + " not found"));

	}
	
	public void deleteById(int userId) {

		if (userRepo.existsById(userId)) {
			this.userRepo.deleteById(userId);
			return;
		}
		
		throw new UserIdException("Must provide a valid userId for deleting");
	}

	public void save(User newUser) {
		if (userRepo.getAllEmails().contains(newUser.getEmail())) {
			throw new EmailInUseException("The email " + newUser.getEmail() + " is already in use");
		} else {
			this.userRepo.save(newUser);
		}
	}

	public void update(User newUser) {
		
		if (userRepo.existsById(newUser.getId())) {
			newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
			this.userRepo.save(newUser);
			return;
		}
		throw new UserIdException("Must provide a valid userId for updating");
	}
	
	public void register(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
		this.userRepo.save(user);
	}
	
	
	
}
