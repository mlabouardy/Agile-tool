package com.labouardy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectController {

	@RequestMapping("/board/create/project")
	public String createProject(){
		return "create-project";
	}
}
