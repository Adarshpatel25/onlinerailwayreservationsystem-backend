package com.casestudy.railwayreservationsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.casestudy.railwayreservationsystem.dao.UserRepository;
import com.casestudy.railwayreservationsystem.model.Users;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Users users = userRepository.findByEmail(email);
		System.out.println(users.getPassword());
		if(users != null) {
			return new CustomUserDetails(users);
		}
			
		throw new UsernameNotFoundException("User not available");
		
	}

	
	
}
