package com.youcode.reservationApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youcode.reservationApp.dao.UserDao;
import com.youcode.reservationApp.entities.Users;
import com.youcode.reservationApp.repositories.UserRepository;

@Controller
public class RegisterController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRepository userRepository;
	
	//controller method to show the registration form
	
	@RequestMapping("/register")
	public String registrationFrom(Model theModel) {
		
		Users users = new Users();
		
		theModel.addAttribute("users", users);
		
		return "register";
	}
	
	//controller method to process the registration form
	@RequestMapping("/processForm")
	public String processFrom(@ModelAttribute("users") Users users) {
		
		Long idLong = userDao.addUser(users);
		
		userRepository.addPresence(idLong, 0);
		
		return "redirect:/";
	}
	

}
