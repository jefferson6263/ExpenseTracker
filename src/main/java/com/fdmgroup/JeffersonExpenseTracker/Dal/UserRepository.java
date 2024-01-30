package com.fdmgroup.JeffersonExpenseTracker.Dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.JeffersonExpenseTracker.Model.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//	List<User> findPartialMatch(String q);
//	List<User> findByFavColor(String favColor);
//	
//	@Query("SELECT u from User12 u WHERE u.name LIKE CONCAT('%', :name, '%')")
//	List<User> findPartialMatch(String name);
//	
//	// if putting in methods that change the database you need the transactional tag
//	// modiyfing tag is used for non select queryies
//
//	@Transactional
//	void deleteByFavColor(String favColor);
}
