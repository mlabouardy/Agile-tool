package com.bordeaux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GithubController{

	@RequestMapping("/board/{id_project}/github/create")
	public String githubForm(@PathVariable int id_project){
		return "github";
	}
}
