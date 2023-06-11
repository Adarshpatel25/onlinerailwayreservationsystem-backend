package com.casestudy.railwayreservationsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.railwayreservationsystem.dao.PassengerRepository;
import com.casestudy.railwayreservationsystem.dao.SeatRepository;
import com.casestudy.railwayreservationsystem.dao.SeatReservationRepository;
import com.casestudy.railwayreservationsystem.dao.TrainDestinationRepository;
import com.casestudy.railwayreservationsystem.dao.TrainRepository;
import com.casestudy.railwayreservationsystem.dao.UserRepository;
import com.casestudy.railwayreservationsystem.dto.MyBookings;
import com.casestudy.railwayreservationsystem.dto.PassengerDetails;
import com.casestudy.railwayreservationsystem.dto.TrainDto;
import com.casestudy.railwayreservationsystem.model.Passenger;
import com.casestudy.railwayreservationsystem.model.Seat;
import com.casestudy.railwayreservationsystem.model.SeatReservation;
import com.casestudy.railwayreservationsystem.model.Train;
import com.casestudy.railwayreservationsystem.model.TrainDestination;
import com.casestudy.railwayreservationsystem.model.Transaction;
import com.casestudy.railwayreservationsystem.model.Users;

@Service
public class BookingService {

	@Autowired
	private TrainDestinationRepository trainDestinationRepository;

	@Autowired
	private SeatReservationRepository seatReservationRepository;

	@Autowired
	private PassengerRepository passengerRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TrainRepository trainRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	
	public Users getUserBasedOnPnr(long pnrNo) {
		return seatReservationRepository.findById(pnrNo).get().getUser();
	}
	

	public List<TrainDto> getTrainBasedOnDestination(String fromLocation, String toLocation) {
		List<TrainDestination> trainDestinationList = (List<TrainDestination>) trainDestinationRepository.findAll();
		int countOfLocations = 0;
		long currentTrainNo = trainDestinationList.get(0).getTrain().getTrainNo();

		List<TrainDto> trains = new ArrayList<TrainDto>();
		int flag = 0;
		String fromStationTiming = "", toStationTiming = "";
		for (TrainDestination tDest : trainDestinationList) {
			if (currentTrainNo == tDest.getTrain().getTrainNo()) {
				if (tDest.getDestination_name().equals(fromLocation)) {
					flag = 1;
					fromStationTiming = tDest.getDestination_timing();
					countOfLocations++;
				} else if (tDest.getDestination_name().equals(toLocation)) {
					flag = 2;
					toStationTiming = tDest.getDestination_timing();
					countOfLocations++;
				}
				if (countOfLocations == 2 && flag == 2) {
					TrainDto trainDto = modelMapper.map(tDest.getTrain(), TrainDto.class);
					trainDto.setFromStationTiming(fromStationTiming);
					trainDto.setToStationTiming(toStationTiming);
					trainDto.setFromStation(fromLocation);
					trainDto.setToStation(toLocation);
					trains.add(trainDto);
					countOfLocations = 0;
					flag = 0;
				}

			} else {
				countOfLocations = 0;
			}
			currentTrainNo = tDest.getTrain().getTrainNo();
		}

		return trains;
	}

	public void cancelTicket(long pnrNo) {

		SeatReservation seatR = seatReservationRepository.findById(pnrNo).get();
		seatR.setCancelled(true);
		seatReservationRepository.save(seatR);
	}

	public List<MyBookings> getBookingDetails(String email) {

		List<MyBookings> bookings = new ArrayList<>();

		Users user = userRepository.findByEmail(email);

		for (SeatReservation seatReservation : user.getSeatReservation()) {
			
			if (seatReservation.getUser().getEmail().equals(email)) {
				
				List<PassengerDetails> passengerDetailsList = new ArrayList<>();
						
				for(Passenger passenger: seatReservation.getPassenger()) {
					PassengerDetails passengerDetails = new PassengerDetails();
					passengerDetails.setPassengerName(passenger.getPassengerName());
					passengerDetails.setPassengerAge(passenger.getPassengerAge());
					passengerDetailsList.add(passengerDetails);
				}
				
				
				MyBookings booking = new MyBookings();
				booking.setTrainNo(seatReservation.getTrain().getTrainNo());
				booking.setPnrNo(seatReservation.getPnrNo());
				booking.setTrainName(seatReservation.getTrainName());
				booking.setFromStation(seatReservation.getFromStation());
				booking.setToStation(seatReservation.getToStation());
				booking.setAmount(seatReservation.getTransaction().getAmount());
				booking.setPassengerDetails(passengerDetailsList);
				booking.setReservationDate(seatReservation.getTrainDate());
				booking.setTime(seatReservation.getTransaction().getTransactionTime());
				booking.setDate(seatReservation.getTransaction().getTransactionDate());
				booking.setCancelled(seatReservation.isCancelled());

				bookings.add(booking);
			}

		}

		return bookings;
	}

	public TrainDto getTrain(String trainName, String fromStation, String toStation) {

		for (Train train : trainRepository.findAll()) {
			if (train.getTrainName().equalsIgnoreCase(trainName)) {
				TrainDto trainDto = new TrainDto();
				trainDto.setTrainName(train.getTrainName());
				trainDto.setTrainNo(train.getTrainNo());
				trainDto.setFromStation(fromStation);
				trainDto.setToStation(toStation);
				for (TrainDestination dest : train.getTrainDestination()) {
					if (dest.getDestination_name().equalsIgnoreCase(fromStation)) {
						trainDto.setFromStationTiming(dest.getDestination_timing());
					} else if (dest.getDestination_name().equalsIgnoreCase(toStation)) {
						trainDto.setToStationTiming(dest.getDestination_timing());
						break;
					}
				}
				return trainDto;
			}
		}
		return null;
	}

	public Double calculateTicketFee(String fromStation, String toStation) {
		List<TrainDestination> trainDestinationList = (List<TrainDestination>) trainDestinationRepository.findAll();
		int count = 0;
		boolean canStart = false;
		for (TrainDestination trainDestination : trainDestinationList) {
			if (trainDestination.getDestination_name().equalsIgnoreCase(fromStation)) {
				canStart = true;
			} else if (trainDestination.getDestination_name().equalsIgnoreCase(toStation)) {
				break;
			}
			if (canStart) {
				count++;
			}
		}
		return (double) (count * 100);
	}

	public Seat generateSeat(MyBookings myBookings) {
		List<Seat> seatList = (List<Seat>) seatRepository.findAll();

		List<Seat> availableSeats = new ArrayList<Seat>();

		if (myBookings.getSeatCoach().equalsIgnoreCase("First Class")) {
			for (int i = 0; i < 20; i++) {
				if (seatList.get(i).isAvailable() == true) {
					availableSeats.add(seatList.get(i));
				}
			}
		} else if (myBookings.getSeatCoach().equalsIgnoreCase("Second Class")) {
			for (int i = 20; i < 40; i++) {
				if (seatList.get(i).isAvailable() == true) {
					availableSeats.add(seatList.get(i));
				}
			}
		} else if (myBookings.getSeatCoach().equalsIgnoreCase("Third Class")) {
			for (int i = 40; i < 60; i++) {
				if (seatList.get(i).isAvailable() == true) {
					availableSeats.add(seatList.get(i));
				}
			}
		}

		Random random = new Random();
		Seat seat = availableSeats.get(random.nextInt(availableSeats.size()));

		return seat;
	}

	public Transaction getTransaction(MyBookings myBookings, Users user) {
		Transaction transaction = new Transaction();
		transaction.setAmount(myBookings.getAmount());
		transaction.setTransactionDate(myBookings.getDate());
		transaction.setTransactionTime(myBookings.getTime());
		transaction.setUser(user);

		return transaction;
	}

	public Train getTrain(long trainNo) {
		Train train = trainRepository.findById(trainNo).get();
		return train;
	}

	public String getTrainTiming(long trainNo, String fromStation) {
		List<TrainDestination> trainDestinationList = (List<TrainDestination>) trainDestinationRepository.findAll();

		for (TrainDestination trainDestination : trainDestinationList) {
			if (trainDestination.getTrain().getTrainNo() == trainNo
					&& trainDestination.getDestination_name().equalsIgnoreCase(fromStation)) {
				return trainDestination.getDestination_timing();
			}
		}
		return "";
	}
	
	public long saveBookings(MyBookings myBookings) {

		SeatReservation seatReservation = new SeatReservation();
		Users user = userRepository.findByEmail(myBookings.getEmail());
		seatReservation.setUser(user);

		List<Passenger> passengersList = new ArrayList<>();
		for(PassengerDetails passengerDetails: myBookings.getPassengerDetails()) {
			Passenger passenger = new Passenger();
			passenger.setPassengerName(passengerDetails.getPassengerName());
			passenger.setPassengerAge(passengerDetails.getPassengerAge());
			passengersList.add(passenger);
		}
		
		
		Seat seat = generateSeat(myBookings);
		seat.setAvailable(false);
		seatReservation.setSeat(seat);
		
		seatReservation.setTrainStatus(myBookings.getFromStation());

		seatReservation.setPassenger(passengersList);
		
		Transaction transaction = getTransaction(myBookings, user);
		seatReservation.setTransaction(transaction);

		Train train = getTrain(myBookings.getTrainNo());
		seatReservation.setTrain(train);

		seatReservation.setTrainTiming(getTrainTiming(myBookings.getTrainNo(), myBookings.getFromStation()));
		seatReservation.setTrainDate(myBookings.getReservationDate());
		seatReservation.setFromStation(myBookings.getFromStation());
		seatReservation.setToStation(myBookings.getToStation());
		seatReservation.setTrainName(myBookings.getTrainName());
		seatReservation.setCancelled(false);

		seatReservationRepository.save(seatReservation);
		List<SeatReservation> seatReservationList = new ArrayList<SeatReservation>();
		seatReservationList.add(seatReservation);
		user.setSeatReservation(seatReservationList);
		userRepository.save(user);
		
		return user.getSeatReservation().get(user.getSeatReservation().size()-1).getPnrNo();
		
	}

}
