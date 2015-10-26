package com.bordeaux.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bordeaux.entity.User;
import com.bordeaux.service.UserService;


@Controller
public class DashboardController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/board")
	public String dashboard(Model model, Principal principal){
		String email=principal.getName();
		User user=userService.findUserByEmail(email);
		model.addAttribute("name", user.getFirstname()+" "+user.getLastname());
		return "board";
	}
}
