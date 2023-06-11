package com.casestudy.railwayreservationsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.railwayreservationsystem.dto.TrainDto;
import com.casestudy.railwayreservationsystem.dto.UserLogin;
import com.casestudy.railwayreservationsystem.dto.UserReg;
import com.casestudy.railwayreservationsystem.model.Destination;
import com.casestudy.railwayreservationsystem.model.Train;
import com.casestudy.railwayreservationsystem.service.BookingService;
import com.casestudy.railwayreservationsystem.service.DestinationService;
import com.casestudy.railwayreservationsystem.service.EmailService;
import com.casestudy.railwayreservationsystem.service.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private DestinationService destinationService;

	@Autowired
	private BookingService bookingService;

	private UserLogin userLogin;

	@Autowired
	private EmailService emailService;
	
	
	
	@RequestMapping(value = "/handleRegistration", method = RequestMethod.POST)
	public String handleRegistration(@RequestBody UserReg userReg) {
		userService.createUser(userReg);
		emailService.sendEmail(userReg.getEmail(), "Railway Reservation - Registration Successful!", "We have successfully registered you on our server. We hope you have a good time on our website.\n Thank You.");
		return "True";
	}


	@RequestMapping(value = "/handleLogin", method = RequestMethod.POST)
	public String handleLoginPage(@ModelAttribute UserLogin userLogin, Model model) {
		if (userService.authenticateUser(userLogin.getEmail(), userLogin.getPassword()) == true) {
			setUser(userLogin);
			return "homepageafterlogin";
		}
		return "redirect:login?isError=true";
	}
	

	@RequestMapping("/searchTrain")
	public List<String> loadBookingPage() {
		List<String> destinationsList = destinationService.getDestinationNames();
		return destinationsList;
	}

	@RequestMapping("/handleSearch")
	public List<TrainDto> getTrainDetailsBasedOnSearch(@RequestParam("from") String fromStation,
			@RequestParam("to") String toStation) {
		List<TrainDto> trains = bookingService.getTrainBasedOnDestination(fromStation, toStation);
		for(TrainDto train: trains) {
			System.out.println(train.getTrainName());
		}
		return trains;
	}

	public void setUser(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public UserLogin getUser() {
		return userLogin;
	}

}
