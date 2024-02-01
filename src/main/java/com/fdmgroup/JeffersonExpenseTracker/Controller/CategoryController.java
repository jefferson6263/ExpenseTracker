package com.fdmgroup.JeffersonExpenseTracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.JeffersonExpenseTracker.Model.Category;
import com.fdmgroup.JeffersonExpenseTracker.Service.CategoryService;

@RestController
public class CategoryController {

	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;

	}
	
	
	@GetMapping("categories/{categoriesId}")
	public Category findCategoryById(@PathVariable int categoriesId) {
		return categoryService.findByCategoryId(categoriesId);
	}
	
	@PostMapping("categories")
	public void createCategory(@RequestBody Category newCategory) {
		categoryService.save(newCategory);
	}
	
	@PutMapping("categories")
	public void updateCategory(@RequestBody Category newCategory) {
		categoryService.update(newCategory);
	}
	
	@DeleteMapping("categories/{categoriesId}")
	public void deleteCategory(@PathVariable int categoriesId) {
		categoryService.deleteById(categoriesId);
	}
	
	@GetMapping("allcategories")
	public List<Category> findAllCategories() {
		return categoryService.findAll();
	}
}
