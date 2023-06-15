package com.casestudy.railwayreservationsystem.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.casestudy.railwayreservationsystem.dto.SeatDto;
import com.casestudy.railwayreservationsystem.dto.TrainDto;
import com.casestudy.railwayreservationsystem.dto.TrainSchedule;
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

	public boolean isSeatAvailable(long trainNo, String seatCoach) {
		Train train = trainRepository.findById(trainNo).get();
		List<Seat> seatList = train.getSeat();

		for (Seat seat : seatList) {
			if (seat.getSeatCoach().equalsIgnoreCase(seatCoach) && seat.getIsAvailable()) {
				return true;
			}
		}

		return false;
	}

	public List<TrainDto> getTrainBasedOnDestination(String fromLocation, String toLocation, String seatCoach) {

		List<Train> trainList = (List<Train>) trainRepository.findAll();
		List<TrainDto> trains = new ArrayList<TrainDto>();

		for (Train train : trainList) {

			int flag = 0;
			String fromLocationTiming = "";
			String toLocationTiming = "";

			List<TrainDestination> trainDestinationList = train.getTrainDestination();
			Collections.sort(trainDestinationList, new Comparator<TrainDestination>() {

				@Override
				public int compare(TrainDestination o1, TrainDestination o2) {
					return (o1.getSequence_no() > o2.getSequence_no()) ? 1 : -1;
				}

			});

			for (TrainDestination trainDestination : train.getTrainDestination()) {

				if (trainDestination.getDestination_name().equalsIgnoreCase(fromLocation)) {
					if (flag == 0) {
						fromLocationTiming = trainDestination.getDestination_timing();
						flag = 1;
					}

				} else if (trainDestination.getDestination_name().equalsIgnoreCase(toLocation)) {
					if (flag == 1) {
						toLocationTiming = trainDestination.getDestination_timing();
						TrainDto trainDto = new TrainDto();
						trainDto.setTrainNo(train.getTrainNo());
						trainDto.setTrainName(train.getTrainName());
						trainDto.setFromStation(fromLocation);
						trainDto.setFromStationTiming(fromLocationTiming);
						trainDto.setToStation(toLocation);
						trainDto.setToStationTiming(toLocationTiming);

						trains.add(trainDto);
						System.out.println(trainDto);
						break;
					} else {
						break;
					}
				}
			}
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

				for (Passenger passenger : seatReservation.getPassenger()) {
					PassengerDetails passengerDetails = new PassengerDetails();
					passengerDetails.setPassengerName(passenger.getPassengerName());
					passengerDetails.setPassengerAge(passenger.getPassengerAge());
					passengerDetails.setSeatNo(passenger.getSeat().getSeatNumber());
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
			
				booking.setSeatCoach(seatReservation.getSeatCoach());
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

	public Double calculateTicketFee(long trainNo, String fromStation, String toStation, String seatCoach) {
		
		Train train = trainRepository.findById(trainNo).get();
		List<TrainDestination> trainDestinationList = train.getTrainDestination();
		Collections.sort(trainDestinationList, new Comparator<TrainDestination>() {

			@Override
			public int compare(TrainDestination o1, TrainDestination o2) {
				return (o1.getSequence_no() > o2.getSequence_no()) ? 1 : -1;
			}
		});

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

	public void generateSeat(MyBookings myBookings, SeatReservation seatReservation) {

		List<Seat> availableSeats = new ArrayList<Seat>();

		Train train = trainRepository.findById(myBookings.getTrainNo()).get();
		List<Seat> seatList = train.getSeat();
		
		List<Seat> seats = new ArrayList<Seat>();
		List<Passenger> passengers = new ArrayList<Passenger>();
		
		if (myBookings.getSeatCoach().equalsIgnoreCase("First Class")) {
			for (PassengerDetails passengersList : myBookings.getPassengerDetails()) {
				for (int i = 0; i < 20; i++) {
					if (seatList.get(i).getIsAvailable() == true) {
						availableSeats.add(seatList.get(i));
					}
				}
				Random random = new Random();
				Seat seat = availableSeats.get(random.nextInt(availableSeats.size()));
				seat.setIsAvailable(false);
				Passenger passenger = new Passenger();
				passenger.setPassengerName(passengersList.getPassengerName());
				passenger.setPassengerAge(passengersList.getPassengerAge());
				passenger.setSeat(seat);
				passengers.add(passenger);
				seats.add(seat);
			}
		} else if (myBookings.getSeatCoach().equalsIgnoreCase("Second Class")) {
			for (PassengerDetails passengersList : myBookings.getPassengerDetails()) {
				for (int i = 20; i < 40; i++) {
					if (seatList.get(i).getIsAvailable() == true) {
						availableSeats.add(seatList.get(i));
					}
				}
				Random random = new Random();
				Seat seat = availableSeats.get(random.nextInt(availableSeats.size()));
				seat.setIsAvailable(false);
				Passenger passenger = new Passenger();
				passenger.setPassengerName(passengersList.getPassengerName());
				passenger.setPassengerAge(passengersList.getPassengerAge());
				passenger.setSeat(seat);
				passengers.add(passenger);
				seats.add(seat);
			}
		} else if (myBookings.getSeatCoach().equalsIgnoreCase("Third Class")) {
			for (PassengerDetails passengersList : myBookings.getPassengerDetails()) {
				for (int i = 40; i < 60; i++) {
					if (seatList.get(i).getIsAvailable() == true) {
						availableSeats.add(seatList.get(i));
					}
				}
				Random random = new Random();
				Seat seat = availableSeats.get(random.nextInt(availableSeats.size()));
				seat.setIsAvailable(false);
				Passenger passenger = new Passenger();
				passenger.setPassengerName(passengersList.getPassengerName());
				passenger.setPassengerAge(passengersList.getPassengerAge());
				passenger.setSeat(seat);
				passengers.add(passenger);
				seats.add(seat);
			}
		}

		passengerRepository.saveAll(passengers);
		seatReservation.setPassenger(passengers);
	
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
		for (PassengerDetails passengerDetails : myBookings.getPassengerDetails()) {
			Passenger passenger = new Passenger();
			passenger.setPassengerName(passengerDetails.getPassengerName());
			passenger.setPassengerAge(passengerDetails.getPassengerAge());
			passengersList.add(passenger);
		}

		generateSeat(myBookings, seatReservation);
		seatReservation.setSeatCoach(myBookings.getSeatCoach());
		
		seatReservation.setTrainStatus(myBookings.getFromStation());

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

		return user.getSeatReservation().get(user.getSeatReservation().size() - 1).getPnrNo();

	}

}
