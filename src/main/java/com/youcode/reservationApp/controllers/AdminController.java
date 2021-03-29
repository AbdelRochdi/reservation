package com.youcode.reservationApp.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youcode.reservationApp.dao.ReservationDao;
import com.youcode.reservationApp.dao.UserDao;
import com.youcode.reservationApp.entities.Reservation;
import com.youcode.reservationApp.entities.ReservationLimit;
import com.youcode.reservationApp.entities.Users;
import com.youcode.reservationApp.repositories.ReservationRepository;
import com.youcode.reservationApp.repositories.UserRepository;

@Controller
public class AdminController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReservationDao reservationDao;

	@Autowired
	private ReservationRepository reservationRepository;

	@RequestMapping("/admin")
	public String showAdmin(Model model, HttpSession session) {
		if (session.getAttribute("role") != null && session.getAttribute("role").equals("admin")) {

			List<Users> users = userDao.getAllUsers();
			List<Reservation> reservations = reservationDao.getAllReservations();
			List<ReservationLimit> reservationLimits = userRepository.getAllReservationLimits();
			List<Reservation> todayReservations = reservationRepository.getAllReservationsToday();
			List<Reservation> todayActiveReservations = reservationRepository.getAllActiveReservationsToday();

			ReservationLimit todayReservationLimit = reservationRepository.getTodayReservationLimit();

			todayReservations.sort(Comparator.comparing(Reservation::getDate));
			int i = 0;
			ArrayList<Long> ids = new ArrayList<Long>();
			
			for (int k = 0; k <= 21; k++) {
				for (int j = 0; j < todayReservations.size(); j++) {
					if (todayReservations.get(j).getUser().getUserReputation().getPresence() < k && i < todayReservationLimit.getReservationLimit()) {
						if (ids.indexOf(todayReservations.get(j).getReservationId()) == -1) {
							System.out.println(i);
							todayReservations.get(j).setState("active");
							reservationDao.updateReservation(todayReservations.get(j));
							i++;
							ids.add(todayReservations.get(j).getReservationId());
							System.out.println(i);
						}	
					} else{
						if (ids.indexOf(todayReservations.get(j).getReservationId()) == -1) {
						todayReservations.get(j).setState("inactive");
						reservationDao.updateReservation(todayReservations.get(j));
						}
					}
				}
				
			}

			model.addAttribute("reservations", reservations);
			model.addAttribute("todayReservations", todayReservations);
			model.addAttribute("todayActiveReservations", todayActiveReservations);
			model.addAttribute("reservationLimits", reservationLimits);
			model.addAttribute("users", users);

			return "admin";
		} else {
			return "redirect:/";
		}

	}

	@RequestMapping("/activateUser")
	public String activateUser(Model model, HttpServletRequest request) {

		Long userId = Long.valueOf(request.getParameter("id"));

		Users user = userDao.getById(userId);

		if (user.getState().equals("inactive")) {
			user.setState("active");
		} else {
			user.setState("inactive");
		}

		userDao.addUser(user);

		return "redirect:/admin";
	}

	@RequestMapping("/setLimit")
	public String setDailyLimit(Model model, HttpServletRequest request) {

		int limit = Integer.valueOf(request.getParameter("limit"));

		Date date = Date.valueOf(request.getParameter("date"));

		String type = request.getParameter("types");

		userRepository.setLimit(limit, type, date);

		return "redirect:/admin";
	}

}
