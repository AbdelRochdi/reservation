package com.youcode.reservationApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
	
	//controller method to show the registration form
	
	@RequestMapping("/register")
	public String registrationFrom() {
		return "register";
	}
	
	//controller method to process the registration form
	@RequestMapping("/processForm")
	public String processFrom() {
		
		return "index";
	}
	

}
