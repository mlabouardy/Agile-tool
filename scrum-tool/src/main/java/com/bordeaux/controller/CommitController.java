package com.bordeaux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommitController {

	@RequestMapping("/board/project/{id_project}/task/{id_task}/commits")
	public String viewCommits(@PathVariable int id_project, @PathVariable int id_task){
		return "commits";
	}
}
