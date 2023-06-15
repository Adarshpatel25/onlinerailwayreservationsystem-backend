package com.casestudy.railwayreservationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.railwayreservationsystem.dto.UserReg;
import com.casestudy.railwayreservationsystem.service.EmailService;
import com.casestudy.railwayreservationsystem.service.UserService;

@RestController
@RequestMapping("/")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;
	
	
	@RequestMapping(value = "/handleRegistration", method = RequestMethod.POST)
	public String handleRegistration(@RequestBody UserReg userReg) {
		userService.createUser(userReg);
		emailService.sendEmail(userReg.getEmail(), "Railway Reservation - Registration Successful!", "We have successfully registered you on our server. We hope you have a good time on our website.\n Thank You.");
		return "True";
	}
	
}
