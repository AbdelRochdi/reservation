package com.youcode.reservationApp.controllers;

import java.sql.Date;
import java.time.LocalDate;
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

			

			List<Reservation> todayMatinActiveReservations = reservationRepository
					.getAllActiveReservationsToday("matin");
			List<Reservation> todaySoirActiveReservations = reservationRepository.getAllActiveReservationsToday("soir");
			List<Reservation> todayWeekendActiveReservations = reservationRepository
					.getAllActiveReservationsToday("week-end");
			
			String today = LocalDate.now().getDayOfWeek().name();


			model.addAttribute("todayMatinActiveReservations", todayMatinActiveReservations);
			model.addAttribute("todaySoirActiveReservations", todaySoirActiveReservations);
			model.addAttribute("todayWeekendActiveReservations", todayWeekendActiveReservations);

			model.addAttribute("today",today);
			

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

	@RequestMapping("/markPresent")
	public String markPresent(Model model, HttpServletRequest request) {

		Long reservationId = Long.valueOf(request.getParameter("resid"));
		Long userId = Long.valueOf(request.getParameter("userid"));

		Reservation reservation = reservationDao.getById(reservationId);
		String type = reservation.getType();

		if (reservation.getPresence().equals("absent")) {
			if (type.equals("matin")) {
				userRepository.deduceAbsence(userId, 2);
			} else if (type.equals("soir")) {
				userRepository.deduceAbsence(userId, 1);
			} else if (type.equals("week-end")) {
				userRepository.deduceAbsence(userId, 3);
			}
		} else {
			if (type.equals("matin")) {
				userRepository.addPresence(userId, 2);
			} else if (type.equals("soir")) {
				userRepository.addPresence(userId, 1);
			} else if (type.equals("week-end")) {
				userRepository.addPresence(userId, 3);
			}
		}

		reservationRepository.markPresence(reservationId, "present");

		return "redirect:/admin";
	}

	@RequestMapping("/markAbsent")
	public String markAbsent(Model model, HttpServletRequest request) {

		Long reservationId = Long.valueOf(request.getParameter("resid"));
		Long userId = Long.valueOf(request.getParameter("userid"));

		Reservation reservation = reservationDao.getById(reservationId);
		String type = reservation.getType();

		if (reservation.getPresence().equals("present")) {
			if (type.equals("matin")) {
				userRepository.addAbsence(userId, 2);
			} else if (type.equals("soir")) {
				userRepository.addAbsence(userId, 1);
			} else if (type.equals("week-end")) {
				userRepository.addAbsence(userId, 3);
			}
		} else {
			if (type.equals("matin")) {
				userRepository.addAbsence(userId, 2);
			} else if (type.equals("soir")) {
				userRepository.addAbsence(userId, 1);
			} else if (type.equals("week-end")) {
				userRepository.addAbsence(userId, 3);
			}

			if (type.equals("matin")) {
				userRepository.addPresence(userId, 2);
			} else if (type.equals("soir")) {
				userRepository.addPresence(userId, 1);
			} else if (type.equals("week-end")) {
				userRepository.addPresence(userId, 3);
			}
		}

		reservationRepository.markPresence(reservationId, "absent");

		return "redirect:/admin";
	}
	
	@RequestMapping("/users")
	public String users(Model model) {

		List<Users> users = userDao.getAllUsers();
		List<Reservation> reservations = reservationDao.getAllReservations();
		model.addAttribute("users", users);
		model.addAttribute("reservations", reservations);

		return "users";
	}
	
	@RequestMapping("/adminReservations")
	public String adminReservations(Model model) {

		List<ReservationLimit> reservationLimits = userRepository.getAllReservationLimits();

		List<Reservation> todayMatinReservations = reservationRepository.getAllReservationsTodayByType("matin");
		List<Reservation> todaySoirReservations = reservationRepository.getAllReservationsTodayByType("soir");
		List<Reservation> todayWeekendReservations = reservationRepository
				.getAllReservationsTodayByType("week-end");
		
		model.addAttribute("todayMatinReservations", todayMatinReservations);
		model.addAttribute("todaySoirReservations", todaySoirReservations);
		model.addAttribute("todayWeekendReservations", todayWeekendReservations);
		model.addAttribute("reservationLimits", reservationLimits);

		return "adminReservations";
	}
	
	
}
