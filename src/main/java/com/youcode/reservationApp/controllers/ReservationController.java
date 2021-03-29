package com.youcode.reservationApp.controllers;

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
public class ReservationController {

	@Autowired
	private ReservationDao reservationDao;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRepository userRepository;

	// controller method to show the reservation page

	@RequestMapping("/reservation")
	public String reservationPage(Model theModel, HttpSession session) {
		if (session.getAttribute("role") != null && session.getAttribute("role").equals("apprenant")) {

			Long userId = (Long) session.getAttribute("id");

			List<Reservation> reservations = new ArrayList<Reservation>();

			Users user = userDao.getById(userId);

			reservations = reservationRepository.getAllReservationsById(userId);
			
			String today = LocalDate.now().getDayOfWeek().name();


			theModel.addAttribute("reservations", reservations);
			theModel.addAttribute("today",today);
			theModel.addAttribute("name", user.getFirstName());

			return "reservation";
		} else {
			return "redirect:/";
		}

	}

	// controller method to process the reservation request
	@RequestMapping("/makeReservation")
	public String makeReservation(Model theModel, HttpSession session, HttpServletRequest request) {

		Long userId = (Long) session.getAttribute("id");
		String type = request.getParameter("type");
		
		reservationDao.addReservation(userId,type);
		
		if (type.equals("matin")) {
			userRepository.addPresence(userId, 2);
		}else if (type.equals("soir")) {
			userRepository.addPresence(userId, 1);
		}else if (type.equals("week-end")) {
			userRepository.addPresence(userId, 3);
		}
		
		ReservationLimit todayReservationLimit = reservationRepository.getTodayReservationLimit();
		List<Reservation> todayReservations = reservationRepository.getAllReservationsToday();
		
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

		return "redirect:/reservation";
	}

}
