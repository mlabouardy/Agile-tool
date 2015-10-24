package com.labouardy.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.labouardy.entity.Role;
import com.labouardy.entity.User;
import com.labouardy.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public User constructUser() {
		return new User();
	}

	@RequestMapping("/login")
	public String signIn(Principal principal) {
		if (principal != null) {
			return "redirect:/board.html";
		}
		return "login";
	}

	@RequestMapping("/register")
	public String signUp() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String signUP(@ModelAttribute("user") User user, BindingResult result) {
		if (result.hasErrors()) {
			return "register";
		}
		userService.save(user);
		return "redirect:/register.html?success=true";
	}


}
