package com.uc.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepository repo;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user=repo.getUserByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new MyUserDetails(user);
	}
}
