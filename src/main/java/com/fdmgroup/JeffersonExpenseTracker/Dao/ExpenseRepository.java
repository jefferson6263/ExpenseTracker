package com.fdmgroup.JeffersonExpenseTracker.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fdmgroup.JeffersonExpenseTracker.Model.Expense;
import com.fdmgroup.JeffersonExpenseTracker.Model.User;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

	@Query("SELECT e from Expense e WHERE e.user_id = (:userId)")
	List<Expense> findExpensesByUserId(int userId);

//	List<User> findPartialMatch(String q);
//	List<User> findByFavColor(String favColor);
//	
	@Query("SELECT u from User12 u WHERE u.name LIKE CONCAT('%', :name, '%')")
	List<User> findPartialMatch(String name);
//	
//	// if putting in methods that change the database you need the transactional tag
//	// modiyfing tag is used for non select queryies
//
//	@Transactional
//	void deleteByFavColor(String favColor);
}

