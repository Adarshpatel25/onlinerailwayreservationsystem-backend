package com.casestudy.railwayreservationsystem.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.railwayreservationsystem.dao.DestinationRepository;
import com.casestudy.railwayreservationsystem.dao.TrainDestinationRepository;
import com.casestudy.railwayreservationsystem.dao.TrainRepository;
import com.casestudy.railwayreservationsystem.dto.TrainSchedule;
import com.casestudy.railwayreservationsystem.model.Destination;
import com.casestudy.railwayreservationsystem.model.Train;
import com.casestudy.railwayreservationsystem.model.TrainDestination;

@Service
public class DestinationService {

	@Autowired
	private DestinationRepository destinationRepository;

	@Autowired
	private TrainRepository trainRepository;

	@Autowired
	private TrainDestinationRepository trainDestinationRepository;

	public List<String> getDestinationNames() {
		List<Destination> destinations = (List<Destination>) destinationRepository.findAll();
		List<String> destinationNames = new ArrayList<>();
		for (Destination dest : destinations) {
			destinationNames.add(dest.getDestination_name());
		}
		return destinationNames;
	}

	public List<TrainSchedule> getDestinationsBasedOnTrain(long trainNo, String fromLocation, String toLocation) {

		List<TrainSchedule> destinationsBasedOnTrain = (List<TrainSchedule>) new ArrayList<TrainSchedule>();

		int flag = 0;
		Train train = trainRepository.findById(trainNo).get();
		List<TrainDestination> trainDestinationList = train.getTrainDestination();
		
		Collections.sort(trainDestinationList, new Comparator<TrainDestination>() {

			@Override
			public int compare(TrainDestination o1, TrainDestination o2) {
				return (o1.getSequence_no() > o2.getSequence_no()) ? 1 : -1;
			}
		});
		
		for (TrainDestination trainDestination : trainDestinationList) {
			if (trainDestination.getTrain().getTrainNo() == trainNo
					&& trainDestination.getDestination_name().equalsIgnoreCase(fromLocation)) {
				flag = 1;
			} else if (trainDestination.getTrain().getTrainNo() == trainNo
					&& trainDestination.getDestination_name().equalsIgnoreCase(toLocation)) {
				TrainSchedule trainSchedule = new TrainSchedule();
				trainSchedule.setDestinationNo(trainDestination.getSequence_no());
				trainSchedule.setDestinationName(trainDestination.getDestination_name());
				trainSchedule.setDestinationTime(trainDestination.getDestination_timing());

				destinationsBasedOnTrain.add(trainSchedule);
				break;
			}
			if (flag == 1) {
				TrainSchedule trainSchedule = new TrainSchedule();
				trainSchedule.setDestinationNo(trainDestination.getSequence_no());
				trainSchedule.setDestinationName(trainDestination.getDestination_name());
				trainSchedule.setDestinationTime(trainDestination.getDestination_timing());

				destinationsBasedOnTrain.add(trainSchedule);
			}
		}


		return destinationsBasedOnTrain;
	}
	
	public void addDestinations(List<Destination> destinations) {
		destinationRepository.saveAll(destinations);
	}

	
}
