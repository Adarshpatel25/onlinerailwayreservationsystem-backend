package com.casestudy.railwayreservationsystem.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.railwayreservationsystem.dao.DestinationRepository;
import com.casestudy.railwayreservationsystem.dto.MyBookings;
import com.casestudy.railwayreservationsystem.dto.PassengerDetails;
import com.casestudy.railwayreservationsystem.dto.TrainDto;
import com.casestudy.railwayreservationsystem.dto.TrainSchedule;
import com.casestudy.railwayreservationsystem.model.Destination;
import com.casestudy.railwayreservationsystem.model.Train;
import com.casestudy.railwayreservationsystem.model.TrainDestination;
import com.casestudy.railwayreservationsystem.service.BookingService;
import com.casestudy.railwayreservationsystem.service.DestinationService;
import com.casestudy.railwayreservationsystem.service.EmailService;
import com.casestudy.railwayreservationsystem.service.TrainService;
import com.casestudy.railwayreservationsystem.service.UserService;

@RestController
@RequestMapping("booking/")
@CrossOrigin
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private DestinationService destinationService;

	@Autowired
	private TrainService trainService;
	
	@Autowired
	private EmailService emailService;

	

	@RequestMapping("/searchTrain")
	public List<String> loadBookingPage() {
		List<String> destinationsList = destinationService.getDestinationNames();
		return destinationsList;
	}

	@RequestMapping("/handleSearch")
	public List<TrainDto> getTrainDetailsBasedOnSearch(@RequestParam("from") String fromStation,
			@RequestParam("to") String toStation, @RequestParam("seatCoach") String seatCoach) {
		
		List<TrainDto> trains = bookingService.getTrainBasedOnDestination(fromStation, toStation, seatCoach);
	
		return trains;
	}
	
	
	@RequestMapping(value="/cancelTicket", method = RequestMethod.POST)
	public boolean cancelTicket(@RequestParam("pnrNo") String pnr, @RequestParam("refundAmount") String refundAmount) {
		
		long pnrNo = Long.parseLong(pnr);
		bookingService.cancelTicket(pnrNo);
		
		emailService.sendEmail(bookingService.getUserBasedOnPnr(pnrNo).getEmail(), "Ticket Cancellation confirmation"
				, "Hello user, we have successfuly cancelled your ticket, and refunded " + refundAmount + "INR to you");
		
		return true;
	}

	@RequestMapping("/viewBookings")
	public List<MyBookings> viewBookingsPage(@RequestParam("email") String email) {
		
		List<MyBookings> bookings = bookingService.getBookingDetails(email);
		
		return bookings;

	}

	@RequestMapping("/selectBooking")
	public Double selectBookingsPage(@RequestParam("trainNo") String trainNo,
			@RequestParam("fromStation") String fromStation, @RequestParam("toStation") String toStation,
			@RequestParam("seatCoach") String seatCoach) {

		long trainNum = Long.parseLong(trainNo);
		
		double ticketFee = bookingService.calculateTicketFee(trainNum, fromStation, toStation, seatCoach);
		System.out.println(ticketFee);
		return ticketFee;

	}

	@RequestMapping(value="/handleBooking", method=RequestMethod.POST)
	public String showTransactionPage(@RequestBody MyBookings myBookings) {
		
		long pnrNo = bookingService.saveBookings(myBookings);
		
		String passengerDetails = myBookings.getPassengerDetails().toString();
		
		
		emailService.sendEmail(myBookings.getEmail(), "Booking Confirmation!", "Hello User, Your booking has been "
				+ "confirmed.\n" + "Your Pnr no. is: " + pnrNo + ". Train Name: " + 
				myBookings.getTrainName() + "\nDate of Onboarding: " + myBookings.getReservationDate() + 
				", Onboarding Station: " + myBookings.getFromStation() + " Destination Station: " + myBookings.getToStation() +
				" " + trainService.getTrainTimings(myBookings.getTrainNo()) + "\n" + passengerDetails + 
				"\nTotal Fare: " + myBookings.getAmount() + "\nThanks for booking the journey. We wish you the best for your journey.");
		
		return "Booked";

	}

	@RequestMapping(value="/getDestinations") 
	public List<TrainSchedule> getDestinationsList(@RequestParam("trainNo") long trainNo,
			@RequestParam("fromStation") String fromLocation, @RequestParam("toStation") String toLocation) {
				
		return destinationService.getDestinationsBasedOnTrain(trainNo, fromLocation, toLocation);
	}
	
}
