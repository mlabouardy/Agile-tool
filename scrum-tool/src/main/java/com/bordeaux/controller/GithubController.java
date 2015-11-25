package com.bordeaux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bordeaux.entity.Github;
import com.bordeaux.entity.Project;
import com.bordeaux.service.GithubService;
import com.bordeaux.service.ProjectService;

@Controller
public class GithubController{

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private GithubService githubService;
	
	@ModelAttribute("github")
	public Github newGithub(){
		return new Github();
	}
	
	@RequestMapping("/board/{id_project}/github/create")
	public String githubForm(@PathVariable int id_project){
		return "github";
	}
	
	@RequestMapping(value = "/board/{id_project}/github/create", method = RequestMethod.POST)
	public String createGithub(@ModelAttribute("github") Github github,@PathVariable int id_project){
		githubService.save(github);
		Project project=projectService.findById(id_project);
		project.setGithub(github);
		projectService.save(project);
		return "redirect:/board.html";
	}
}
