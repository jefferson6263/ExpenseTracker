package com.fdmgroup.JeffersonExpenseTracker.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.JeffersonExpenseTracker.Dao.UserRepository;
import com.fdmgroup.JeffersonExpenseTracker.Exceptions.EmailInUseException;
import com.fdmgroup.JeffersonExpenseTracker.Exceptions.UserIdException;
import com.fdmgroup.JeffersonExpenseTracker.Model.User;

@Service
public class UserService {
	
	private UserRepository userRepo;

	@Autowired
	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	public List<User> findAll() {
		return this.userRepo.findAll();
	}

	public User findById(int userId) {

		return this.userRepo.findById(userId).orElseThrow(() -> new UserIdException("User with id " + userId + " not found"));
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
			this.userRepo.save(newUser);
			return;
		}

		throw new UserIdException("Must provide a valid userId for updating");
	}

	public void deleteById(int userId) {

		if (userRepo.existsById(userId)) {
			this.userRepo.deleteById(userId);
			return;
		}

		throw new UserIdException("Must provide a valid userId for deleting");

	}



}
