package com.casestudy.railwayreservationsystem.dao;

import org.springframework.data.repository.CrudRepository;

import com.casestudy.railwayreservationsystem.model.Train;

public interface TrainRepository extends CrudRepository<Train, Long>{

}
