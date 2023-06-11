package com.casestudy.railwayreservationsystem.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.railwayreservationsystem.dao.DestinationRepository;
import com.casestudy.railwayreservationsystem.dao.SeatRepository;
import com.casestudy.railwayreservationsystem.dao.TrainDestinationRepository;
import com.casestudy.railwayreservationsystem.dao.TrainRepository;
import com.casestudy.railwayreservationsystem.model.Destination;
import com.casestudy.railwayreservationsystem.model.Seat;
import com.casestudy.railwayreservationsystem.model.Train;
import com.casestudy.railwayreservationsystem.model.TrainDestination;

@Service
public class TrainService {

	@Autowired
	private TrainRepository trainRepository;

	@Autowired
	private TrainDestinationRepository trainDestinationRepository;

	@Autowired
	private SeatRepository seatRepository;
	
	@Autowired
	private DestinationRepository destinationRepository;

	public void addTrain(List<String> timings, String trainName, String fromStation, String toStation) {
		List<Train> list = (List<Train>) trainRepository.findAll();
		Long trainNo = list.get(list.size()-1).getTrainNo() + 1;
		Train train = new Train();
		train.setTrainNo(trainNo);
		train.setTrainName(trainName);
		train.setFromStation(fromStation);
		train.setToStation(toStation);
		train.setTrainDestination(addTrainDestination(train, timings, fromStation, toStation));
		addSeats(train);
	}

	public List<TrainDestination> addTrainDestination(Train train, List<String> timings, String fromStation, String toStation) {
		List<Integer> destinationIdList = new ArrayList<Integer>();
		List<String> destinationNameList = new ArrayList<String>();
		
		int fromIndex = 0, toIndex = 0;
		int index = -1;
		
		List<Destination> destinationList = (List<Destination>) destinationRepository.findAll();
		
		for (Destination destination : destinationList) {
			index++;
			if (destination.getDestination_name().equalsIgnoreCase(fromStation)) {
				fromIndex = index;
			} else if (destination.getDestination_name().equalsIgnoreCase(toStation)) {
				toIndex = index;
			}
		}

		if (fromIndex < toIndex) {
			for (int i = fromIndex; i <= toIndex; i++) {
				destinationIdList.add(destinationList.get(i).getDestination_id());
				destinationNameList.add(destinationList.get(i).getDestination_name());
			}
		}
		else if (fromIndex > toIndex) {
			for (int i = toIndex; i >= fromIndex; i--) {
				destinationIdList.add(destinationList.get(i).getDestination_id());
				destinationNameList.add(destinationList.get(i).getDestination_name());
			}
		}
	
		List<TrainDestination> trainDestinationList = (List<TrainDestination>) trainDestinationRepository.findAll();
		int trainDestinationId = trainDestinationList.get(trainDestinationList.size()-1).getSequence_no() + 1;
		
		List<TrainDestination> trainDestinations = new ArrayList<>();
		
		for(int i = 0; i < destinationIdList.size(); i++) {
			Destination destination = new Destination();
			destination.setDestination_id(destinationIdList.get(i));
			destination.setDestination_name(destinationNameList.get(i));
			TrainDestination trainDestination = new TrainDestination();
			trainDestination.setTrainDestination_id(trainDestinationId++);
			trainDestination.setTrain(train);
			trainDestination.setSequence_no(i+1);
			trainDestination.setDestination(destination);
			trainDestination.setDestination_timing(timings.get(i));
			trainDestination.setDestination_name(destinationNameList.get(i));
			trainDestinationRepository.save(trainDestination);
			trainDestinations.add(trainDestination);
		}

		return trainDestinations;
	}

	public void addSeats(Train train) {
		int cnt = -1;
		for(int i = 1; i <= 60; i++) {
			Seat seat = new Seat();
			seat.setSeatId(i);
			if(i > 3*cnt && i <= 3*(cnt+1) && cnt%2==0) {
				if(i%3==1) {
					seat.setSeatType("Window");
				}
				else if(i%3==2) {
					seat.setSeatType("Middle");
				}
				else if(i%3==0) {
					seat.setSeatType("Side");
				}
			}
			else if(i > 3*cnt && i <= 3*(cnt+1) && cnt%2==1) {
				if(i%3==1) {
					seat.setSeatType("Side");
				}
				else if(i%3==2) {
					seat.setSeatType("Middle");
				}
				else if(i%3==0) {
					seat.setSeatType("Window");
				}
			}
			if(i<=20) {
				seat.setSeatCoach("First Class");
			}
			else if(i >= 21 && i <= 40) {
				seat.setSeatCoach("Second Class");
			}
			else if(i >= 41){
				seat.setSeatCoach("Third Class");
			}
			if(i%3==0) {
				cnt++;
			}
			seat.setSeatNo(i);
			seat.setTrain(train);
			seat.setAvailable(true);
			seatRepository.save(seat);
		}
	}
	
	public void deleteTrain(long trainNo) {
		trainRepository.deleteById(trainNo);
	}
	
	public String getTrainTimings(long trainNo) {
		
		Train train = trainRepository.findById(trainNo).get();
		
		String trainName = train.getTrainName();
		String fromStation = train.getFromStation();
		String toStation = train.getToStation();
		
		List<TrainDestination> trainDestinationList = (List<TrainDestination>) trainDestinationRepository.findAll();
		
		String trainTimings = "Onboarding Timing: ";
		
		for(TrainDestination trainDestination: trainDestinationList) {
			if(trainDestination.getDestination_name().equalsIgnoreCase(fromStation) && trainDestination.getTrain().getTrainName().equalsIgnoreCase(trainName)) {
				trainTimings += trainDestination.getDestination_timing() + ", Destination Timing: ";
			}
			if(trainDestination.getDestination_name().equalsIgnoreCase(toStation) && trainDestination.getTrain().getTrainName().equalsIgnoreCase(trainName)) {
				trainTimings += trainDestination.getDestination_timing();
			}
		}
		
		return trainTimings;
	}
	
}
