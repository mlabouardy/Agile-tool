package com.labouardy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String signIn(){
		return "login";
	}
	
	@RequestMapping("/register")
	public String signUp(){
		return "register";
	}
}
