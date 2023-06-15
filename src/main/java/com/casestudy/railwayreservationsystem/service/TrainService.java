package com.casestudy.railwayreservationsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.railwayreservationsystem.dao.DestinationRepository;
import com.casestudy.railwayreservationsystem.dao.SeatRepository;
import com.casestudy.railwayreservationsystem.dao.TrainDestinationRepository;
import com.casestudy.railwayreservationsystem.dao.TrainRepository;
import com.casestudy.railwayreservationsystem.dto.AddTrainDto;
import com.casestudy.railwayreservationsystem.dto.TrainDto;
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

	public String getStationTiming(List<TrainDestination> trainDestinations, String fromStation) {
		for (TrainDestination trainDestination : trainDestinations) {
			if (trainDestination.getDestination_name().equalsIgnoreCase(fromStation)) {
				return trainDestination.getDestination_timing();
			}
		}
		return null;
	}

	public List<TrainDto> getTrainsList() {
		List<Train> trainsList = (List<Train>) trainRepository.findAll();

		List<TrainDto> trains = new ArrayList<TrainDto>();
		for (Train train : trainsList) {
			TrainDto trainDto = new TrainDto();
			trainDto.setTrainNo(train.getTrainNo());
			trainDto.setTrainName(train.getTrainName());
			trainDto.setFromStation(train.getFromStation());
			trainDto.setFromStationTiming(getStationTiming(train.getTrainDestination(), train.getFromStation()));
			trainDto.setToStation(train.getToStation());
			trainDto.setToStationTiming(getStationTiming(train.getTrainDestination(), train.getToStation()));

			trains.add(trainDto);
		}

		return trains;
	}

	public void addTrain(AddTrainDto addTrainDto) {
		List<Train> list = (List<Train>) trainRepository.findAll();

		Long trainNo = list.get(list.size() - 1).getTrainNo() + 1;

		Train train = new Train();
		train.setTrainNo(trainNo);
		train.setTrainName(addTrainDto.getTrainName());
		train.setFromStation(addTrainDto.getFromStation());
		train.setToStation(addTrainDto.getToStation());
		train.setTrainDestination(addTrainDestination(train, addTrainDto));

		addSeats(train);

		trainRepository.save(train);
	}

	public List<TrainDestination> addTrainDestination(Train train, AddTrainDto trains) {
		List<Integer> destinationIdList = new ArrayList<Integer>();
		List<String> destinationNameList = new ArrayList<String>();

		int fromIndex = 0, toIndex = 0;
		int index = -1;

		List<Destination> destinationList = (List<Destination>) destinationRepository.findAll();

		for (Destination destination : destinationList) {
			index++;
			if (destination.getDestination_name().equalsIgnoreCase(trains.getFromStation())) {
				fromIndex = index;
			} else if (destination.getDestination_name().equalsIgnoreCase(trains.getToStation())) {
				toIndex = index;
			}
		}

		if (fromIndex < toIndex) {
			for (int i = fromIndex; i <= toIndex; i++) {
				destinationIdList.add(destinationList.get(i).getDestination_id());
				destinationNameList.add(destinationList.get(i).getDestination_name());
			}
		} else if (fromIndex > toIndex) {
			for (int i = fromIndex; i >= toIndex; i--) {
				destinationIdList.add(destinationList.get(i).getDestination_id());
				destinationNameList.add(destinationList.get(i).getDestination_name());
			}
		}

		List<TrainDestination> trainDestinationList = (List<TrainDestination>) trainDestinationRepository.findAll();
		int trainDestinationId = trainDestinationList.get(trainDestinationList.size() - 1).getTrainDestination_id() + 1;

		List<TrainDestination> trainDestinations = new ArrayList<>();
		String stationTiming = trains.getStartTiming();

		for (int i = 0; i < destinationIdList.size(); i++) {
			Destination destination = new Destination();
			destination.setDestination_id(destinationIdList.get(i));
			destination.setDestination_name(destinationNameList.get(i));
			TrainDestination trainDestination = new TrainDestination();
			trainDestination.setTrainDestination_id(trainDestinationId++);
			trainDestination.setTrain(train);
			trainDestination.setSequence_no(i + 1);
			trainDestination.setDestination(destination);
			trainDestination.setDestination_timing(stationTiming);
			trainDestination.setDestination_name(destinationNameList.get(i));
			trainDestinations.add(trainDestination);

			int hoursTime = Integer.parseInt(stationTiming.substring(0, 2));
			int minutesTime = Integer.parseInt(stationTiming.substring(3));

			if (minutesTime < 45) {
				minutesTime += 15;
				stationTiming = stationTiming.substring(0, 3) + String.valueOf(minutesTime);
			} else if (minutesTime >= 45) {
				if (hoursTime < 23) {
					hoursTime++;
					minutesTime = minutesTime - 45;
					if (String.valueOf(hoursTime).length() == 1) {
						if (String.valueOf(minutesTime).length() == 1) {
							stationTiming = "0" + hoursTime + ":" + "0" + String.valueOf(minutesTime);
						}
						else {
							stationTiming = "0" + hoursTime + ":" + String.valueOf(minutesTime);
						}
					} else {
						stationTiming = String.valueOf(hoursTime) + ":" + String.valueOf(minutesTime);
					}

				} else if (hoursTime == 23) {
					minutesTime = minutesTime - 45;
					if (String.valueOf(minutesTime).length() == 1) {
						stationTiming = "00:0" + minutesTime;
					} else {
						stationTiming = "00:" + minutesTime;
					}
				}
			}

			System.out.println(stationTiming);
		}

		return trainDestinations;
	}

	public void addSeats(Train train) {

		List<Seat> seatsList = (List<Seat>) seatRepository.findAll();
		int seatId = seatsList.get(seatsList.size() - 1).getSeatId() + 1;

		List<Seat> seats = new ArrayList<Seat>();

		int cnt = -1;
		for (int i = 1; i <= 60; i++) {
			Seat seat = new Seat();
			seat.setSeatId(seatId++);
			if (i > 3 * cnt && i <= 3 * (cnt + 1) && cnt % 2 == 0) {
				if (i % 3 == 1) {
					seat.setSeatType("Window");
				} else if (i % 3 == 2) {
					seat.setSeatType("Middle");
				} else if (i % 3 == 0) {
					seat.setSeatType("Side");
				}
			} else if (i > 3 * cnt && i <= 3 * (cnt + 1) && cnt % 2 == 1) {
				if (i % 3 == 1) {
					seat.setSeatType("Side");
				} else if (i % 3 == 2) {
					seat.setSeatType("Middle");
				} else if (i % 3 == 0) {
					seat.setSeatType("Window");
				}
			}
			if (i <= 20) {
				seat.setSeatCoach("First Class");
			} else if (i >= 21 && i <= 40) {
				seat.setSeatCoach("Second Class");
			} else if (i >= 41) {
				seat.setSeatCoach("Third Class");
			}
			if (i % 3 == 0) {
				cnt++;
			}
			seat.setSeatNumber(i);
			seat.setTrain(train);
			seat.setIsAvailable(true);

			seats.add(seat);
		}
		train.setSeat(seats);
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

		for (TrainDestination trainDestination : trainDestinationList) {
			if (trainDestination.getDestination_name().equalsIgnoreCase(fromStation)
					&& trainDestination.getTrain().getTrainName().equalsIgnoreCase(trainName)) {
				trainTimings += trainDestination.getDestination_timing() + ", Destination Timing: ";
			}
			if (trainDestination.getDestination_name().equalsIgnoreCase(toStation)
					&& trainDestination.getTrain().getTrainName().equalsIgnoreCase(trainName)) {
				trainTimings += trainDestination.getDestination_timing();
			}
		}

		return trainTimings;
	}

}
