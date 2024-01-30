package com.fdmgroup.JeffersonExpenseTracker.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.JeffersonExpenseTracker.Dao.UserRepository;
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

		return this.userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user with id not found"));
	}

	public void save(User newUser) {
		this.userRepo.save(newUser);

	}

	public void update(User newUser) {

		if (userRepo.existsById(newUser.getId())) {
			this.userRepo.save(newUser);
			return;
		}

		throw new RuntimeException("must provide a user id for put mapping");
	}

	public void deleteById(int userId) {
		this.userRepo.deleteById(userId);

	}

//	public List<User> findpartialMatch(String q) {
//		
//		return userRepo.findPartialMatch(q);
//	}

}
