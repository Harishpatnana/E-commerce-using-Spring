package com.harish.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.harish.ecommerce.model.Users;
import com.harish.ecommerce.repo.UserRepo;
@Service
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users u=repo.findByUsername(username);
		
			if (u == null) {
			    throw new UsernameNotFoundException("User Not Found");
			}

		
		return new UserPrincipal(u);
	}

}
