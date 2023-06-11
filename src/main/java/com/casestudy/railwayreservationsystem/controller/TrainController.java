package com.casestudy.railwayreservationsystem.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.railwayreservationsystem.model.Train;
import com.casestudy.railwayreservationsystem.service.TrainService;

import antlr.collections.List;

@RestController
public class TrainController {

	@Autowired
	private TrainService trainService;
	
	@PostMapping("/addTrain")
	public boolean addTrain(@RequestBody Train train, @RequestBody ArrayList<String> timings) {
		trainService.addTrain(timings, train.getTrainName(), train.getFromStation(), train.getToStation());
		return true;
	}
	
	@DeleteMapping("/deleteTrain/{trainNo}")
	public void deleteTrain(@PathVariable("trainNo") Long trainNo) {
		trainService.deleteTrain(trainNo);
	}
	
	
}
