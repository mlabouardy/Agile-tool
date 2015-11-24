package com.bordeaux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bordeaux.entity.Project;
import com.bordeaux.service.ProjectService;

@Controller
public class CommitController {

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/board/project/{id_project}/task/{id_task}/commits")
	public String viewCommits(@PathVariable int id_project, @PathVariable int id_task, Model model){
		model.addAttribute("task_id", id_task);
		Project project=projectService.findById(id_project);
		model.addAttribute("github", project.getGithub());
		return "commits";
	}
}
