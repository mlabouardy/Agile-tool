package com.bordeaux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KanbanController {

	
	@RequestMapping("/kanban")
	public String KanbanController(){
		
		return "kanban";
		
	}
}
