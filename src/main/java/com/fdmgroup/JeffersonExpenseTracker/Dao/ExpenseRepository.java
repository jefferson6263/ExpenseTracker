package com.fdmgroup.JeffersonExpenseTracker.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.JeffersonExpenseTracker.Model.Expense;


public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

	@Query("SELECT e from Expense e WHERE e.user.id = :userId")
	List<Expense> findExpensesByUserId(@Param("userId") int userId);
	
	
 
}

