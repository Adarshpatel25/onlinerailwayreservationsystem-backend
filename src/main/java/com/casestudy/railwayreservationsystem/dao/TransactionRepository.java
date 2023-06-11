package com.casestudy.railwayreservationsystem.dao;

import org.springframework.data.repository.CrudRepository;

import com.casestudy.railwayreservationsystem.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer>{

}
