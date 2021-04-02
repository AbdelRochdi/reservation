package com.youcode.reservationApp.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
	
	public void reservationConfirmation(List<Reservation> todayReservations, ReservationLimit todayReservationLimit) {
		todayReservations.sort(Comparator.comparing(Reservation::getDate));
		int i = 0;
		ArrayList<Long> ids = new ArrayList<Long>();
		
		int reservationLimit;

		if (todayReservationLimit.getReservationLimit() == 0 && todayReservationLimit.getDate() == null) {
			reservationLimit = 3;
		} else {
			reservationLimit = todayReservationLimit.getReservationLimit();
		}
		
		for (int k = 0; k <= 21; k++) {
			for (int j = 0; j < todayReservations.size(); j++) {
				if (todayReservations.get(j).getUser().getUserReputation().getPresence() < k && i < reservationLimit) {
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
	}


	// controller method to show the reservation page

	@RequestMapping("/reservation")
	public String reservationPage(Model theModel, HttpSession session) {
		if (session.getAttribute("role") != null && session.getAttribute("role").equals("apprenant")) {

			Long userId = (Long) session.getAttribute("id");

			List<Reservation> reservations = new ArrayList<Reservation>();

			Users user = userDao.getById(userId);

			reservations = reservationRepository.getAllReservationsById(userId);
			
			String today = LocalDate.now().getDayOfWeek().name();
			int now = LocalDateTime.now().getHour();
			
			
			
			Reservation matin = reservationRepository.getReservationByTypeById(userId, "matin");
			Reservation soir = reservationRepository.getReservationByTypeById(userId, "soir");
			Reservation weekend = reservationRepository.getReservationByTypeById(userId, "week-end");
			
			List<Reservation> todayMatinActiveReservations = reservationRepository
					.getAllActiveReservationsToday("matin");
			List<Reservation> todaySoirActiveReservations = reservationRepository.getAllActiveReservationsToday("soir");
			List<Reservation> todayWeekendActiveReservations = reservationRepository
					.getAllActiveReservationsToday("week-end");
			List<Reservation> tomorrowMatinActiveReservations = reservationRepository
					.getAllActiveReservationsTomorrow("matin");
			List<Reservation> tomorrowSoirActiveReservations = reservationRepository.getAllActiveReservationsTomorrow("soir");
			List<Reservation> tomorrowWeekendActiveReservations = reservationRepository
					.getAllActiveReservationsTomorrow("week-end");
			
			
			
			theModel.addAttribute("todayMatinActiveReservations", todayMatinActiveReservations);
			theModel.addAttribute("todaySoirActiveReservations", todaySoirActiveReservations);
			theModel.addAttribute("todayWeekendActiveReservations", todayWeekendActiveReservations);
			theModel.addAttribute("tomorrowMatinActiveReservations", tomorrowMatinActiveReservations);
			theModel.addAttribute("tomorrowSoirActiveReservations", tomorrowSoirActiveReservations);
			theModel.addAttribute("tomorrowWeekendActiveReservations", tomorrowWeekendActiveReservations);
			theModel.addAttribute("matin", matin);
			theModel.addAttribute("soir", soir);
			theModel.addAttribute("weekend", weekend);
			theModel.addAttribute("reservations", reservations);
			theModel.addAttribute("today",today);
			theModel.addAttribute("now",now);
			theModel.addAttribute("user", user);

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
		
		
		
		List<Reservation> todayMatinReservations = reservationRepository.getAllReservationsTodayByType("matin");
		List<Reservation> todaySoirReservations = reservationRepository.getAllReservationsTodayByType("soir");
		List<Reservation> todayWeekendReservations = reservationRepository.getAllReservationsTodayByType("week-end");

		ReservationLimit todayMatinReservationLimit = reservationRepository.getTodayReservationLimit("matin");
		ReservationLimit todaySoirReservationLimit = reservationRepository.getTodayReservationLimit("soir");
		ReservationLimit todayWeekendReservationLimit = reservationRepository.getTodayReservationLimit("week-end");

		reservationConfirmation(todayWeekendReservations, todayWeekendReservationLimit);
		reservationConfirmation(todayMatinReservations, todayMatinReservationLimit);
		reservationConfirmation(todaySoirReservations, todaySoirReservationLimit);

		return "redirect:/reservation";
	}
	
	@RequestMapping("/cancelReservation")
	public String cancelReservation(Model theModel, HttpSession session, HttpServletRequest request) {

		Long userId = (Long) session.getAttribute("id");
		String type = request.getParameter("type");
		
		reservationRepository.deleteReservationByTypeById(userId,type);
		
		List<Reservation> todayMatinReservations = reservationRepository.getAllReservationsTodayByType("matin");
		List<Reservation> todaySoirReservations = reservationRepository.getAllReservationsTodayByType("soir");
		List<Reservation> todayWeekendReservations = reservationRepository.getAllReservationsTodayByType("week-end");

		ReservationLimit todayMatinReservationLimit = reservationRepository.getTodayReservationLimit("matin");
		ReservationLimit todaySoirReservationLimit = reservationRepository.getTodayReservationLimit("soir");
		ReservationLimit todayWeekendReservationLimit = reservationRepository.getTodayReservationLimit("week-end");

		reservationConfirmation(todayWeekendReservations, todayWeekendReservationLimit);
		reservationConfirmation(todayMatinReservations, todayMatinReservationLimit);
		reservationConfirmation(todaySoirReservations, todaySoirReservationLimit);

		return "redirect:/reservation";
	}
	

}
