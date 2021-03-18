package com.youcode.reservationApp.controllers;

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
import com.youcode.reservationApp.entities.Users;

@Controller
public class AdminController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ReservationDao reservationDao;
	
	
	@RequestMapping("/admin")
	public String showAdmin(Model model, HttpSession session){
		if (session.getAttribute("role") != null && session.getAttribute("role").equals("admin")) {
			List<Users> users = userDao.getAllUsers();
			
			List<Reservation> reservations = reservationDao.getAllReservations();
			
			model.addAttribute("reservations",reservations);
			
			model.addAttribute("users",users);
			
			return "admin";
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping("/activateUser")
	public String activateUser(Model model, HttpServletRequest request){
		
		Long userId = Long.valueOf(request.getParameter("id"));
		
		Users user = userDao.getById(userId);
		
		if (user.getState().equals("inactive")) {
			user.setState("active");
		}else {
			user.setState("inactive");			
		}
		
		userDao.addUser(user);
		
		return "redirect:/admin";
	}
	
	
}
