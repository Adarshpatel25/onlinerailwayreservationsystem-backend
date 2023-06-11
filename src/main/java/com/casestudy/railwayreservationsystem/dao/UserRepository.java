package com.casestudy.railwayreservationsystem.dao;

import org.springframework.data.repository.CrudRepository;

import com.casestudy.railwayreservationsystem.model.Users;

public interface UserRepository extends CrudRepository<Users, Integer>{

	public boolean existsByEmail(String email);
	
	public Users findByEmail(String email);
	
}
