package com.labouardy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScrumMasterController {

	@RequestMapping("/board/project/{id}/scrum-master")
	public String scrumMaster(@PathVariable int id){
		return "scrum-team";
	}
}
