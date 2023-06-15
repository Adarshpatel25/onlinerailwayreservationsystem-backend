package com.casestudy.railwayreservationsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.casestudy.railwayreservationsystem.dao.DestinationRepository;
import com.casestudy.railwayreservationsystem.dao.SeatReservationRepository;
import com.casestudy.railwayreservationsystem.dao.TrainDestinationRepository;
import com.casestudy.railwayreservationsystem.dao.TrainRepository;
import com.casestudy.railwayreservationsystem.dao.UserRepository;
import com.casestudy.railwayreservationsystem.model.Destination;
import com.casestudy.railwayreservationsystem.model.Seat;
import com.casestudy.railwayreservationsystem.model.SeatReservation;
import com.casestudy.railwayreservationsystem.model.Train;
import com.casestudy.railwayreservationsystem.model.TrainDestination;
import com.casestudy.railwayreservationsystem.model.Transaction;
import com.casestudy.railwayreservationsystem.model.Users;
import com.casestudy.railwayreservationsystem.service.UserService;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class OnlineRailwayReservationSystemApplication {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(OnlineRailwayReservationSystemApplication.class,
				args);
		UserRepository userRepository = (UserRepository) context.getBean("userRepository");
		TrainRepository trainRepository = (TrainRepository) context.getBean("trainRepository");
		UserService userService = (UserService) context.getBean("userService");

		if (userRepository.findByEmail("admin320@gmail.com") == null) {
			userService.createAdmin();
		}

		// Trains
		Train train1 = new Train();
		train1.setTrainNo(10001);
		train1.setTrainName("Janshatabdi Express");
		train1.setFromStation("Narsinghpur");
		train1.setToStation("Gadarwara");

		Train train2 = new Train();
		train2.setTrainNo(10002);
		train2.setTrainName("Rajdhani Express");
		train2.setFromStation("Kareli");
		train2.setToStation("Narsinghpur");

		// Seats
		List<Seat> seats = new ArrayList<Seat>();
		int cnt = 0;
		for (int i = 1; i <= 60; i++) {
			Seat seat = new Seat();
			seat.setSeatId(i);
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
			seat.setTrain(train1);
			seat.setIsAvailable(true);
			seats.add(seat);
		}

		// Inserting train into seat
		train1.setSeat(seats);
		train2.setSeat(seats);

		// Destinations
		Destination destination1 = new Destination();
		destination1.setDestination_id(1);
		destination1.setDestination_name("Narsinghpur");

		Destination destination2 = new Destination();
		destination2.setDestination_id(2);
		destination2.setDestination_name("Kareli");

		Destination destination3 = new Destination();
		destination3.setDestination_id(3);
		destination3.setDestination_name("Gadarwara");

		Destination destination4 = new Destination();
		destination4.setDestination_id(4);
		destination4.setDestination_name("Pipariya");
		
		// Adding all Destinations to List
		List<Destination> destinations = new ArrayList<>();
		destinations.add(destination1);
		destinations.add(destination2);
		destinations.add(destination3);
		destinations.add(destination4);

		
		// TrainDestination
		TrainDestination trainDestination1 = new TrainDestination();
		trainDestination1.setTrainDestination_id(1);
		trainDestination1.setTrain(train1);
		trainDestination1.setSequence_no(1);
		trainDestination1.setDestination(destination1);
		trainDestination1.setDestination_timing("08:30");
//		trainDestination1.setDestination_date("25/02/2023");
		trainDestination1.setDestination_name(destination1.getDestination_name());

		TrainDestination trainDestination2 = new TrainDestination();
		trainDestination2.setTrainDestination_id(2);
		trainDestination2.setTrain(train1);
		trainDestination2.setSequence_no(2);
		trainDestination2.setDestination(destination2);
		trainDestination2.setDestination_timing("09:30");
//		trainDestination2.setDestination_date("25/02/2023");
		trainDestination2.setDestination_name(destination2.getDestination_name());

		TrainDestination trainDestination3 = new TrainDestination();
		trainDestination3.setTrainDestination_id(3);
		trainDestination3.setTrain(train1);
		trainDestination3.setSequence_no(3);
		trainDestination3.setDestination(destination3);
		trainDestination3.setDestination_timing("11:00");
//		trainDestination3.setDestination_date("25/02/2023");
		trainDestination3.setDestination_name(destination3.getDestination_name());

		TrainDestination trainDestination4 = new TrainDestination();
		trainDestination4.setTrainDestination_id(4);
		trainDestination4.setTrain(train1);
		trainDestination4.setSequence_no(4);
		trainDestination4.setDestination(destination4);
		trainDestination4.setDestination_timing("12:30");
//		trainDestination4.setDestination_date("25/02/2023");
		trainDestination4.setDestination_name(destination4.getDestination_name());

		
		// Inserting TrainDestinations in List
		List<TrainDestination> trainDestinations1 = new ArrayList<>();
		trainDestinations1.add(trainDestination1);
		trainDestinations1.add(trainDestination2);
		trainDestinations1.add(trainDestination3);
		trainDestinations1.add(trainDestination4);

		// inserting trainDestination into train
		train1.setTrainDestination(trainDestinations1);

		TrainDestination trainDestination5 = new TrainDestination();
		trainDestination5.setTrainDestination_id(5);
		trainDestination5.setTrain(train2);
		trainDestination5.setSequence_no(1);
		trainDestination5.setDestination(destination2);
		trainDestination5.setDestination_timing("09:30");
//		trainDestination6.setDestination_date("25/02/2023");
		trainDestination5.setDestination_name(destination2.getDestination_name());

		TrainDestination trainDestination6 = new TrainDestination();
		trainDestination6.setTrainDestination_id(6);
		trainDestination6.setTrain(train2);
		trainDestination6.setSequence_no(2);
		trainDestination6.setDestination(destination1);
		trainDestination6.setDestination_timing("10:30");
//		trainDestination6.setDestination_date("25/02/2023");
		trainDestination6.setDestination_name(destination1.getDestination_name());

		List<TrainDestination> trainDestinations2 = new ArrayList<TrainDestination>();
		trainDestinations2.add(trainDestination5);
		trainDestinations2.add(trainDestination6);

		train2.setTrainDestination(trainDestinations2);

		Users user1 = new Users();
		user1.setUser_id(1);
		user1.setEmail("adarshp320@gmail.com");
		user1.setName("Adarsh Patel");
		user1.setPassword("Patel123");
		user1.setAge(21);
		user1.setGender("Male");
		user1.setIsAdmin(false);

		trainRepository.save(train1);
		trainRepository.save(train2);
		userRepository.save(user1);

	}

}
