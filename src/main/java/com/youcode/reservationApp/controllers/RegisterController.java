package com.youcode.reservationApp.controllers;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	// controller method to show the registration form

	@RequestMapping("/register")
	public String registrationFrom(Model theModel) {

		Users users = new Users();

		theModel.addAttribute("users", users);

		return "register";
	}

	// controller method to process the registration form
	@RequestMapping("/processForm")
	public String processFrom(@Valid @ModelAttribute("users") Users users, BindingResult thBindingResult,
			HttpServletRequest request, Model model) throws MessagingException {
		
		Users oldUser = userRepository.getByEmail(users.getEmail());

		String confirm = request.getParameter("confirm");

		if (thBindingResult.hasErrors()) {
			return "register";
		} else {
			
			if (oldUser != null) {
				model.addAttribute("messageEmail", "Cet email existe déja");

				return "register";
			}else if (!confirm.equals(users.getPassword())) {
				model.addAttribute("message", "Les 2 mots de passe ne sont pas identiques");

				return "register";

			}else {

				String hashed = BCrypt.hashpw(users.getPassword(), BCrypt.gensalt(12));
				String lower = users.getEmail().toLowerCase();
				users.setPassword(hashed);
				users.setEmail(lower);

				Long idLong = userDao.addUser(users);

				userRepository.addPresence(idLong, 0);
				
				model.addAttribute("message", "Inscription Complete veuillez vous connecter pour continuer");
				
				userRepository.sendEmail(users,"Tu as été enregistré avec succes");

				return "index";
			}
		}
	}

}
