package com.fdmgroup.JeffersonExpenseTracker;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.JeffersonExpenseTracker.Controller.CategoryController;

import com.fdmgroup.JeffersonExpenseTracker.Model.Category;

import com.fdmgroup.JeffersonExpenseTracker.Service.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

	@Mock
	CategoryService categoryService;
	

	CategoryController categoryController;
	
	@BeforeEach
	void setup() {
		this.categoryController = new CategoryController(categoryService);
	}
	
	
	@Test
	void findCategoryById_test() {
		
		Category cat1 = new Category("Technology");

		
		when(categoryService.findByCategoryId(1)).thenReturn(cat1);
		Category foundCategory = categoryController.findCategoryById(1);
		assertSame(cat1, foundCategory);
		verify(categoryService, times(1)).findByCategoryId(1);
	}
	
	@Test
	void createCategory_test() {
		Category cat1 = new Category("Technology");
		categoryController.createCategory(cat1);
		verify(categoryService, times(1)).save(cat1);
	}
	
	@Test
	void updateCategory_test() {
		Category cat1 = new Category("Technology");
		categoryController.updateCategory(cat1);
		verify(categoryService, times(1)).update(cat1);
	}
	
	@Test
	void deleteCategory_test() {
		categoryController.deleteCategory(1);
		verify(categoryService, times(1)).deleteById(1);
	}
	
	@Test
	void findAllCategories_test() {
		
		Category cat1 = new Category("Technology");
		Category cat2 = new Category("Leisure");
		Category cat3 = new Category("Cleaning");
		Category cat4 = new Category("Bills");
		Category cat5 = new Category("Food");
		Category cat6 = new Category("Travel");
		Category cat7 = new Category("Supplies");

		
		List<Category> allCategories = new ArrayList<>();
		allCategories.add(cat1);
		allCategories.add(cat2);
		allCategories.add(cat3);
		allCategories.add(cat4);
		allCategories.add(cat5);
		allCategories.add(cat6);
		allCategories.add(cat7);
		
		
		when(categoryService.findAll()).thenReturn(allCategories);
		assertSame(categoryController.findAllCategories(), allCategories);
		verify(categoryService, times(1)).findAll();
	}
}
