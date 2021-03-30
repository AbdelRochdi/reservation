package com.youcode.reservationApp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youcode.reservationApp.entities.Users;
import com.youcode.reservationApp.repositories.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public String showPage(HttpSession session) {
		
		if (session.getAttribute("id") == null) {
			return "index";
		}else {
			if (session.getAttribute("role").equals("admin")) {
				return "redirect:/admin";
			}else {				
				return "redirect:/reservation";
			}
		}
	}
	
	@RequestMapping("/home")
	public String homePage(HttpSession session) {
		
		if (session.getAttribute("id") == null) {
			return "index";
		}else {
			if (session.getAttribute("role").equals("admin")) {
				return "redirect:/admin";
			}else {				
				return "redirect:/reservation";
			}
		}
	}
	
	@RequestMapping("/loginProcess")
	public String login(HttpServletRequest request, HttpSession session){
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Users users = userRepository.getByEmail(email);
		
		if (users != null) {
			if (password.equals(users.getPassword())) {
				
				if (users.getRole().equals("admin")) {
					session.setAttribute("id", users.getId());
					session.setAttribute("role", users.getRole());
					return "redirect:/admin";
				}else {	
					if(users.getState().equals("active")){
						session.setAttribute("id", users.getId());
						session.setAttribute("role", users.getRole());
						return "redirect:/reservation";
					}else {
						System.out.println("inactive user, check with the administration");
						return "redirect:/";
					}
				}
			}else {
				return "redirect:/";
			}
		}else {
			System.out.println("user doesn't exist");
		}
		
		return null;	
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
