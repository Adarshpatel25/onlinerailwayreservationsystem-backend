package com.casestudy.railwayreservationsystem.dao;

import org.springframework.data.repository.CrudRepository;

import com.casestudy.railwayreservationsystem.model.Passenger;

public interface PassengerRepository extends CrudRepository<Passenger, Integer>{

	
	
}
