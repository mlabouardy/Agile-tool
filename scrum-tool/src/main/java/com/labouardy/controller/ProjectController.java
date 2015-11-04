package com.labouardy.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.labouardy.entity.Project;
import com.labouardy.entity.User;
import com.labouardy.repository.UserRepository;
import com.labouardy.service.ProjectService;
import com.labouardy.service.UserService;

@Controller
public class ProjectController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@ModelAttribute("project")
	public Project constructProject(){
		return new Project();
	}
	
	@RequestMapping("/board/create/project")
	public String project(){
		return "create-project";
	}
	
	@RequestMapping(value="/board/create/project", method=RequestMethod.POST)
	public String createProject(Principal principal, @ModelAttribute("project") Project project, BindingResult result){
		if(result.hasErrors()){
			return "create-project";
		}
		String email=principal.getName();
		User user=userService.findUserByEmail(email);
		projectService.save(user, project);
		return "board";
	}
	
	@RequestMapping(value="/board/project/{id}")
	public String viewProject(@PathVariable int id, Model model, HttpSession session){
		Project project=projectService.findOne(id);
		if(project==null){
			return "board";
		}
		model.addAttribute("project", project);
		session.setAttribute("project-id", id);
		return "view-project";
	}
}
