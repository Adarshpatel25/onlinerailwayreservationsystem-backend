package com.casestudy.railwayreservationsystem.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.casestudy.railwayreservationsystem.dao.UserRepository;
import com.casestudy.railwayreservationsystem.dto.UserReg;
import com.casestudy.railwayreservationsystem.model.Users;

@Service
public class UserService {

	@Autowired
	private UserRepository userR;
	
	private Users loggedInUser;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public void createUser(UserReg userReg) {
		Users user = modelMapper.map(userReg, Users.class);
		user.setPassword(passwordEncoder.encode(userReg.getPassword()));
		user.setAdmin(false);
		userR.save(user);
	}
	
	public boolean authenticateUser(String email, String password) {
		List<Users> users = (List<Users>) userR.findAll();
		for(Users user: users) {

			if(user.getEmail().equals(email) && passwordEncoder.matches(password, user.getPassword())) {
				setUser(user);
				return true;
			}
			
		}
		return false;
	}
	
	public int getUserId(String email) {
		List<Users> users = (List<Users>) userR.findAll();
		for(Users user: users) {
			if(user.getEmail().equals(email)) {
				return user.getUser_id();
			}
		}
		return -1;
	}
	
	public Users getUser() {
		return loggedInUser;
	}
	
	public void setUser(Users user) {
		this.loggedInUser = user;
	}
	
}
