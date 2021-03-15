package com.youcode.reservationApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youcode.reservationApp.dao.ReservationDao;
import com.youcode.reservationApp.dao.UserDao;
import com.youcode.reservationApp.entities.Reservation;
import com.youcode.reservationApp.entities.Users;

@Controller
public class ReservationController {

	@Autowired
	private ReservationDao reservationDao;
	
	@Autowired
	private UserDao userDao;
	
	//controller method to show the reservation page
	
	@RequestMapping("/reservation")
	public String reservationPage(Model theModel) {
		return "reservation";
	}
	
	//controller method to process the reservation request
	@RequestMapping("/makeReservation")
	public String makeReservation(Model theModel) {
		
		reservationDao.addReservation();
		
		return "reservation";
	}
	
	
	
}
