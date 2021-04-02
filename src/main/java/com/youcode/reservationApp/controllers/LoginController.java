package com.youcode.reservationApp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String login(HttpServletRequest request, HttpSession session, Model model){
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Users users = userRepository.getByEmail(email);
		
		if (users != null) {
			if (BCrypt.checkpw(password, users.getPassword())) {
				
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
						model.addAttribute("inactifMessage", "Utilisateur inactif, vérifiez avec l'administration");
						return "index";
					}
				}
			}else {
				model.addAttribute("passIncorrectMessage", "Mot de passe incorrect");
				return "index";
			}
		}else {
			model.addAttribute("noUserMessage", "Cet utilisateur n'existe pas");
			return "index";
		}
			
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
