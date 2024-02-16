package com.fdmgroup.JeffersonExpenseTracker.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.fdmgroup.JeffersonExpenseTracker.Dao.UserRepository;
import com.fdmgroup.JeffersonExpenseTracker.Model.User;

@Service
public class AuthUserService implements org.springframework.security.core.userdetails.UserDetailsService{
	private UserRepository userRepo;

	@Autowired
	public AuthUserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		User user = this.userRepo.findByUsername(username).orElseThrow(
//				()-> new UsernameNotFoundException(username));
//		return new AuthUser(user);
 
		 
		User user = this.userRepo.findByEmail(email).orElseThrow(
				()-> new UsernameNotFoundException(email));
		
		return new AuthUser(user);
	}
	
	

}
