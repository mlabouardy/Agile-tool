package com.bordeaux.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bordeaux.entity.Project;
import com.bordeaux.entity.Role;
import com.bordeaux.entity.Role.RoleType;
import com.bordeaux.entity.user.ProductOwner;
import com.bordeaux.entity.user.User;
import com.bordeaux.repository.RoleRepository;
import com.bordeaux.service.user.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;
	
	@ModelAttribute("user")
	public User constructUser() {
		return new User();
	}

	@RequestMapping("/login")
	public String signIn(Principal principal) {
		if (principal != null) {
			return "redirect:/board.html";
		}
		return "login";
	}

	@RequestMapping("/register")
	public String signUp() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String signUP(@ModelAttribute("user") User user, BindingResult result) {
		if (result.hasErrors()) {
			return "register";
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		// on recoit juste l'id du role, le nom du role est null, donc on le cherche dans le roleRepository
		Role userRole = roleRepository.findOne(user.getRole().getId());
		
		// ca permet d'ajouter un projet un product owner
		if (userRole.getName().equals(RoleType.PRODUCT.toString())) {
			
			ProductOwner productOwner = new ProductOwner();
			productOwner.setEmail(user.getEmail());
			productOwner.setFirstname(user.getFirstname());
			productOwner.setLastname(user.getLastname());
			productOwner.setPassword(encoder.encode(user.getPassword()));
			productOwner.setProject(new Project());
			userService.save(productOwner);
			
		} else {
			userService.save(user);
		}

		return "redirect:/register.html?success=true";
	}

	@RequestMapping("/register/available")
	@ResponseBody
	public String isAvailable(@RequestParam String email) {
		boolean available = userService.findUserByEmail(email) == null;
		return "" + available;
	}

}
