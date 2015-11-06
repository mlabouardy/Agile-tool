package com.labouardy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.labouardy.entity.Backlog;
import com.labouardy.entity.UserStory;
import com.labouardy.service.BacklogService;
import com.labouardy.service.UserRepositoryService;

@Controller
public class BacklogController {

	@Autowired
	private UserRepositoryService userReposityService;
	
	@Autowired
	private BacklogService backlogService;
	
	@RequestMapping("/board/project/{id}/backlog")
	public String backlog(HttpSession session, @PathVariable int id, Model model){
		Backlog backlog=backlogService.findOneById(id);
		List<UserStory> userstories=backlog.getUserstories();
		model.addAttribute("userstories", userstories);
		return "backlog";
	}
	
	@RequestMapping("/board/project/{id}/backlog/userstory/create")
	public String userStoryForm(HttpSession session){
		return "create-userstory";	
	}
	
	@ModelAttribute("userstory")
	public UserStory constructUserStory(){
		return new UserStory();
	}
	
	@RequestMapping(value="/board/project/{id}/backlog/userstory/create",method=RequestMethod.POST)
	public String createUserStory(HttpSession session, @PathVariable int id, @ModelAttribute("userstory") UserStory userstory){
		Backlog backlog=backlogService.findOneById(id);
		userReposityService.save(userstory, backlog);
		return "backlog";	
	}
}
