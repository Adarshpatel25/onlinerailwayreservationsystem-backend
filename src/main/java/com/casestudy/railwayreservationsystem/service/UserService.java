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
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public Users getUserByEmail(String email) {
		return userR.findByEmail(email);
	}
	
	
	public void createAdmin() {
		Users user = new Users();
		user.setName("ADMIN");
		user.setEmail("admin320@gmail.com");
		user.setPassword(passwordEncoder.encode("12345"));
		user.setIsAdmin(true);
		user.setGender("Male");
		user.setAge(21);
		
		userR.save(user);
	}
	
	public void createUser(UserReg userReg) {
		Users user = modelMapper.map(userReg, Users.class);
		user.setPassword(passwordEncoder.encode(userReg.getPassword()));
		user.setIsAdmin(false);
		userR.save(user);
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
	
}
