package com.youcode.reservationApp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youcode.reservationApp.dao.ReservationDao;
import com.youcode.reservationApp.dao.UserDao;
import com.youcode.reservationApp.entities.Reservation;
import com.youcode.reservationApp.entities.Users;
import com.youcode.reservationApp.repositories.ReservationRepository;

@Controller
public class ReservationController {

	@Autowired
	private ReservationDao reservationDao;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private UserDao userDao;

	// controller method to show the reservation page

	@RequestMapping("/reservation")
	public String reservationPage(Model theModel, HttpSession session) {
		if (session.getAttribute("role") != null && session.getAttribute("role").equals("apprenant")) {

			Long userId = (Long) session.getAttribute("id");

			List<Reservation> reservations = new ArrayList<Reservation>();

			Users user = userDao.getById(userId);

			reservations = reservationRepository.getAllReservationsById(userId);

			theModel.addAttribute("reservations", reservations);

			theModel.addAttribute("name", user.getFirstName());

			return "reservation";
		} else {
			return "redirect:/";
		}

	}

	// controller method to process the reservation request
	@RequestMapping("/makeReservation")
	public String makeReservation(Model theModel, HttpSession session) {

		Long userId = (Long) session.getAttribute("id");

		reservationDao.addReservation(userId);

		return "redirect:/reservation";
	}

}
