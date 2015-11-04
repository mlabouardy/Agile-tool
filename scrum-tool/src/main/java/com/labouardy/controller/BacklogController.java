package com.labouardy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BacklogController {

	@RequestMapping("/board/project/backlog")
	public String backlog(HttpSession session){
		return "backlog";
	}
}
