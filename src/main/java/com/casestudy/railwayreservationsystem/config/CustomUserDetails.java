package com.casestudy.railwayreservationsystem.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.casestudy.railwayreservationsystem.model.Users;

@Component
public class CustomUserDetails implements UserDetails{

	private Users users;
	
	public CustomUserDetails(Users users) {
		this.users = users;
	}
	public CustomUserDetails() {
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = null;
		if(users.getIsAdmin()) {
			role = "ADMIN";
		}
		else if(users.getIsAdmin() == false) {
			role = "USER";
		}
		
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
		return Arrays.asList(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return users.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}
