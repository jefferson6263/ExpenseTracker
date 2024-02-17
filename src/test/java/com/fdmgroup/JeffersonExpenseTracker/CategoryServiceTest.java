package com.fdmgroup.JeffersonExpenseTracker;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.JeffersonExpenseTracker.Dao.CategoryRepository;
import com.fdmgroup.JeffersonExpenseTracker.Exceptions.CategoryIdException;
import com.fdmgroup.JeffersonExpenseTracker.Model.Category;
import com.fdmgroup.JeffersonExpenseTracker.Service.CategoryService;


@ExtendWith(MockitoExtension.class)

public class CategoryServiceTest {
	
	@Mock
	CategoryRepository categoryRepo;
	
	CategoryService categoryService;
	
	
	@BeforeEach
	void setup() {
		
		this.categoryService = new CategoryService(categoryRepo);
	}
	
	@Test
	void find_all_categories_test() {
		
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
		
		when(categoryRepo.findAll()).thenReturn(allCategories);
		
		List<Category> foundCategories= categoryRepo.findAll();
	
		verify(categoryRepo, times(1)).findAll();
		assertSame(foundCategories, allCategories);
		
	}
	
	@Test
	void find_category_by_category_id_test() {

		Optional<Category> cat1 = Optional.of(new Category("Technology"));
		Optional<Category> cat2 = Optional.of(new Category("Leisure"));
		Optional<Category> cat3 = Optional.of(new Category("Cleaning"));
		Optional<Category> cat4 = Optional.of(new Category("Bills"));
		
		
		when(categoryRepo.findById(1)).thenReturn(cat1);
		when(categoryRepo.findById(2)).thenReturn(cat2);
		when(categoryRepo.findById(3)).thenReturn(cat3);
		when(categoryRepo.findById(4)).thenReturn(cat4);

		assertSame(cat1.get(), categoryService.findByCategoryId(1));
		assertSame(cat2.get(), categoryService.findByCategoryId(2));
		assertSame(cat3.get(), categoryService.findByCategoryId(3));
		assertSame(cat4.get(), categoryService.findByCategoryId(4));
		
		verify(categoryRepo, times(1)).findById(1);
		verify(categoryRepo, times(1)).findById(2);
		verify(categoryRepo, times(1)).findById(3);
		verify(categoryRepo, times(1)).findById(4);
	}
	
	@Test
	void find_expense_by_expense_id_fail_test() {

		assertThrows(CategoryIdException.class, () -> categoryService.findByCategoryId(1));
		verify(categoryRepo, times(1)).findById(1);
	}
	
	@Test
	void save_category_test() {
		
		Category cat1 = new Category("Technology");
		
		categoryService.save(cat1);
		verify(categoryRepo, times(1)).save(cat1);
	}
	
	@Test
	void save_multiple_categories_test() {

		Category cat1 = new Category("Technology");
		Category cat2 = new Category("Leisure");
		Category cat3 = new Category("Cleaning");
		Category cat4 = new Category("Bills");
		Category cat5 = new Category("Food");
		Category cat6 = new Category("Travel");
		Category cat7 = new Category("Supplies");
		
		categoryService.save(cat1);
		categoryService.save(cat2);
		categoryService.save(cat3);
		categoryService.save(cat4);
		categoryService.save(cat5);
		categoryService.save(cat6);
		categoryService.save(cat7);
	
		
		verify(categoryRepo, times(1)).save(cat1);
		verify(categoryRepo, times(1)).save(cat2);
		verify(categoryRepo, times(1)).save(cat3);
		verify(categoryRepo, times(1)).save(cat4);
		verify(categoryRepo, times(1)).save(cat5);
		verify(categoryRepo, times(1)).save(cat6);
		verify(categoryRepo, times(1)).save(cat7);
		
				
	}
	
	@Test
	void update_category_test() {

		
		Category cat1 = new Category("Technology");
		
		cat1.setId(1);
		
		when(categoryRepo.existsById(1)).thenReturn(true);
		categoryService.update(cat1);

		verify(categoryRepo, times(1)).existsById(1);
		verify(categoryRepo, times(1)).save(cat1);
	
	}
	
	@Test
	void update_category_fail_test() {

		
		Category cat1 = new Category("Technology");
		
		cat1.setId(1);
		
		when(categoryRepo.existsById(1)).thenReturn(false);

		assertThrows(CategoryIdException.class, () -> categoryService.update(cat1));
		verify(categoryRepo, times(1)).existsById(1);
		verify(categoryRepo, times(0)).save(cat1);
	
	}
	
	@Test
	void delete_category_test() {
		
		when(categoryRepo.existsById(1)).thenReturn(true);
		
		categoryService.deleteById(1);
		
		verify(categoryRepo, times(1)).existsById(1);
		verify(categoryRepo, times(1)).deleteById(1);

	}
	
	@Test
	void delete_category_fail_test() {
		
		when(categoryRepo.existsById(1)).thenReturn(false);
		
		assertThrows(CategoryIdException.class, () -> categoryService.deleteById(1));
		verify(categoryRepo, times(1)).existsById(1);
		verify(categoryRepo, times(0)).deleteById(1);
	}
	
}
