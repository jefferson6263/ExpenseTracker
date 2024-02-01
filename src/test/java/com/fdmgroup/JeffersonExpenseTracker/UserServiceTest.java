package com.fdmgroup.JeffersonExpenseTracker;



import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.JeffersonExpenseTracker.Dao.UserRepository;
import com.fdmgroup.JeffersonExpenseTracker.Exceptions.EmailInUseException;
import com.fdmgroup.JeffersonExpenseTracker.Exceptions.UserIdException;
import com.fdmgroup.JeffersonExpenseTracker.Model.User;
import com.fdmgroup.JeffersonExpenseTracker.Service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	UserRepository userRepo;
	
	UserService userService;
	
	@BeforeEach
	void setup() {
		
		this.userService = new UserService(userRepo);

	}
	
	@Test
	void save_user_test() {
		
		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		
		userService.save(user1);
		verify(userRepo, times(1)).save(user1);
	}
	
	@Test
	void save_multiple_users_test() {
		

		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		User user2 = new User("Jane", "Doe", "janedoe@live.com", "janedoe3", "pass123 ");
		User user3 = new User("Mike", "Yao", "mikeyao@live.com", "mikeyao", "java23");
		User user4 = new User("Rachel", "Perry", "rachelperry@live.com", "rperry1", "moo89");
		
		userService.save(user1);
		verify(userRepo, times(1)).save(user1);
		
		userService.save(user2);
		verify(userRepo, times(1)).save(user2);
		
		userService.save(user3);
		verify(userRepo, times(1)).save(user3);
		
		userService.save(user4);
		verify(userRepo, times(1)).save(user4);
	}
	
	@Test
	void save_already_existing_user_test() {
		

		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		User duplicateUser1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		
		EmailInUseException exception = new EmailInUseException("Email already in use");
		
		
		userService.save(user1);
		verify(userRepo, times(1)).save(user1);

		doThrow(exception).when(userRepo).save(duplicateUser1);
		assertThrows(EmailInUseException.class, () -> userService.save(duplicateUser1));
	}
	
	@Test
	void find_all_user_test() {
		
	
		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		User user2 = new User("Jane", "Doe", "janedoe@live.com", "janedoe3", "pass123 ");
		User user3 = new User("Mike", "Yao", "mikeyao@live.com", "mikeyao", "java23");
		User user4 = new User("Rachel", "Perry", "rachelperry@live.com", "rperry1", "moo89");
		
		List<User> allUsers = new ArrayList<>();
		allUsers.add(user1);
		allUsers.add(user2);
		allUsers.add(user3);
		allUsers.add(user4);
		
		when(userRepo.findAll()).thenReturn(allUsers);
		
		List<User> foundUsers = userService.findAll();
	
		verify(userRepo, times(1)).findAll();
		assertSame(foundUsers, allUsers);
	}
	
	@Test
	void find_user_by_id_test() {

		Optional<User> user1 = Optional.of(new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123"));

		when(userRepo.findById(1)).thenReturn(user1);
		User foundUser1 = userService.findById(1);
		
		verify(userRepo, times(1)).findById(1);
		assertSame(user1.get(), foundUser1);

	}
	
	@Test
	void find_user_by_id_fail_test() {

		assertThrows(UserIdException.class, () -> userService.findById(1));
		verify(userRepo, times(1)).findById(1);
	}
	
	@Test
	void update_user_test() {
		
		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		user1.setId(1);
		
		when(userRepo.existsById(1)).thenReturn(true);
		userService.update(user1);
		
		verify(userRepo, times(1)).existsById(1);
		verify(userRepo, times(1)).save(user1);

	}
	
	@Test
	void update_user_fail_test() {
		
		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		user1.setId(1);
		
		when(userRepo.existsById(1)).thenReturn(false);
		
		assertThrows(UserIdException.class, () -> userService.update(user1));
		verify(userRepo, times(1)).existsById(1);
		verify(userRepo, times(0)).save(user1);
	}
	
	@Test
	void delete_user_test() {
		
		when(userRepo.existsById(1)).thenReturn(true);
		
		userService.deleteById(1);
		
		verify(userRepo, times(1)).existsById(1);
		verify(userRepo, times(1)).deleteById(1);

	}
	
	@Test
	void delete_user_fail_test() {
		
		when(userRepo.existsById(1)).thenReturn(false);
		
		assertThrows(UserIdException.class, () -> userService.deleteById(1));
		verify(userRepo, times(1)).existsById(1);
		verify(userRepo, times(0)).deleteById(1);
	}
	
	
}
