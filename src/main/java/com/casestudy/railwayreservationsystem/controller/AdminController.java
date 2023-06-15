package com.casestudy.railwayreservationsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.railwayreservationsystem.dto.AddTrainDto;
import com.casestudy.railwayreservationsystem.dto.TrainDto;
import com.casestudy.railwayreservationsystem.model.Train;
import com.casestudy.railwayreservationsystem.service.TrainService;


@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

	@Autowired
	private TrainService trainService;
	
	@RequestMapping(value="/viewTrains") 
	public List<TrainDto> getTrainsList() {
		return trainService.getTrainsList();
	}
	
	
	@RequestMapping(value="/addTrain", method=RequestMethod.POST)
	public boolean addTrain(@RequestBody AddTrainDto trains) {
		trainService.addTrain(trains);
		return true;
	}
	
	@RequestMapping(value="/deleteTrain/{trainNo}", method=RequestMethod.DELETE)
	public void deleteTrain(@PathVariable("trainNo") Long trainNo) {
		trainService.deleteTrain(trainNo);
	}
	
	
}
