package com.casestudy.railwayreservationsystem.dao;

import org.springframework.data.repository.CrudRepository;

import com.casestudy.railwayreservationsystem.model.SeatReservation;

public interface SeatReservationRepository extends CrudRepository<SeatReservation, Long>{

	
	
}
