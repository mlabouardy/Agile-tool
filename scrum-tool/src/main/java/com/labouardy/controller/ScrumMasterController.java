package com.labouardy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.labouardy.entity.User;
import com.labouardy.service.UserService;

@Controller
public class ScrumMasterController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/board/project/{id}/scrum-master")
	public String scrumMaster(@PathVariable int id){
		return "scrum-team";
	}
	
	@RequestMapping("/board/project/{id}/scrum-master/create")
	public String createScrumMaster(@PathVariable int id, Model model){
		List<User> scrummasters=userService.findAllScrumMaster();
		model.addAttribute("scrummasters", scrummasters);
		return "choose-master";
	}
}
