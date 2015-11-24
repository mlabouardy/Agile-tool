package com.bordeaux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagementController {

	
	@RequestMapping("/management/sprint")
	public String manageSprint(){
		return "managementSprint";
	}
	
	@RequestMapping("/management/team")
	public String manageScrumTeam(){
		return "managementTeam";
	}
	
	
	
}
