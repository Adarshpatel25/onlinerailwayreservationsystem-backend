package com.casestudy.railwayreservationsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.railwayreservationsystem.dao.DestinationRepository;
import com.casestudy.railwayreservationsystem.dao.TrainDestinationRepository;
import com.casestudy.railwayreservationsystem.model.Destination;
import com.casestudy.railwayreservationsystem.model.TrainDestination;

@Service
public class DestinationService {

	@Autowired
	private DestinationRepository destinationRepository;
	
	
	public List<String> getDestinationNames() {
		List<Destination> destinations = (List<Destination>) destinationRepository.findAll();
		List<String> destinationNames = new ArrayList<>();
		for (Destination dest : destinations) {
			destinationNames.add(dest.getDestination_name());
		}
		return destinationNames;
	}
	
	public List<Destination> getDestinations() {
		List<Destination> destinations = (List<Destination>) destinationRepository.findAll();
		return destinations;
	}
	
	
	
}
