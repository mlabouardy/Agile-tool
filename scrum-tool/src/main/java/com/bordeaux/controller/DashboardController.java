package com.bordeaux.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bordeaux.entity.User;
import com.bordeaux.form.backlog.BackLogForm;
import com.bordeaux.form.backlog.UserStoryForm;
import com.bordeaux.service.BackLogService;
import com.bordeaux.service.UserService;


@Controller
public class DashboardController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BackLogService backLogService;
	
	@RequestMapping("/board")
	public String dashboard(Model model, Principal principal){
		String email=principal.getName();
		User user=userService.findUserByEmail(email);
		model.addAttribute("name", user.getFirstname()+" "+user.getLastname());
		backLogService.initModelForBackLogPage(model);
		return "board";
	}
	
	@RequestMapping(value = "/board", method = RequestMethod.POST)
	public String redirection(BackLogForm backLogForm, Model model) {
		
		String type = backLogForm.getTypeForm().getType();
		int userStoryId = backLogForm.getTypeForm().getId();
		
		if (type.equals("add") || type.equals("edit")) {
		
			
			UserStoryForm userStoryForm = backLogService.getUserStoryForm(userStoryId);
			model.addAttribute("userStoryForm", userStoryForm);
			return type;
			
		}
		else if (type.equals("remove")) {
			
			try {
				backLogService.removeUserStory(userStoryId);
				backLogService.initModelForBackLogPage(model);
			} catch (Exception e) {
				backLogService.initModelForBackLogPage(model,e.getMessage());
			}
			
			return "board";
			
		}
		
		return "board";
	}
	
	
}
