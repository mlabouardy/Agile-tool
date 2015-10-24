package com.labouardy.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DashboardController {
	
	@RequestMapping("/board")
	public String dashboard(Model model, Principal principal){
		model.addAttribute("name", principal.getName());
		return "board";
	}
}
