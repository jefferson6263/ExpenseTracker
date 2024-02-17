package com.fdmgroup.JeffersonExpenseTracker.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.JeffersonExpenseTracker.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


}


