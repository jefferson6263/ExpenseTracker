package com.fdmgroup.JeffersonExpenseTracker.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.JeffersonExpenseTracker.Dao.CategoryRepository;
import com.fdmgroup.JeffersonExpenseTracker.Dao.UserRepository;
import com.fdmgroup.JeffersonExpenseTracker.Exceptions.CategoryIdException;
import com.fdmgroup.JeffersonExpenseTracker.Exceptions.UserIdException;
import com.fdmgroup.JeffersonExpenseTracker.Model.Category;
import com.fdmgroup.JeffersonExpenseTracker.Model.User;

@Service
public class CategoryService {
	
	private CategoryRepository categoryRepo;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepo = categoryRepository;
	}

	public List<Category> findAll() {
		return this.categoryRepo.findAll();
	}

	public Category findByCategoryId(int categoryId) {

		return this.categoryRepo.findById(categoryId).orElseThrow(() -> new CategoryIdException("Category with id " + categoryId + " not found"));
	}

	public void save(Category newCategory) {
		this.categoryRepo.save(newCategory);

	}

	public void update(Category newCategory) {

		if (categoryRepo.existsById(newCategory.getId())) {
			this.categoryRepo.save(newCategory);
			return;
		}

		throw new CategoryIdException("Must provide a valid categoryId for updating");
	}

	public void deleteById(int categoryId) {
		this.categoryRepo.deleteById(categoryId);

	}
}
