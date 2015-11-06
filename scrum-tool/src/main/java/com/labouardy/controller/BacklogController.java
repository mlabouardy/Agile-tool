package com.labouardy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BacklogController {

	@RequestMapping("/board/project/{id}/backlog")
	public String backlog(HttpSession session, @PathVariable int id){
		return "backlog";
	}
	
	@RequestMapping("/board/project/{id}/backlog/userstory/create")
	public String createUserStory(HttpSession session){
		return "create-userstory";
		
	}
}
