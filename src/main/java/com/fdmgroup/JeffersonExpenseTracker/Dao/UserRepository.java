package com.fdmgroup.JeffersonExpenseTracker.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.JeffersonExpenseTracker.Model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u.email from User u")
	List<String> getAllEmails();
//	
//	// if putting in methods that change the database you need the transactional tag
//	// modiyfing tag is used for non select queryies
//
//	@Transactional
//	void deleteByFavColor(String favColor);
}
