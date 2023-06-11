package com.casestudy.railwayreservationsystem.dao;

import org.springframework.data.repository.CrudRepository;

import com.casestudy.railwayreservationsystem.model.Seat;

public interface SeatRepository extends CrudRepository<Seat, Integer>{

}
