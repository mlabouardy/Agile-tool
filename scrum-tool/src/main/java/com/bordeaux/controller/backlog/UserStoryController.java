package com.bordeaux.controller.backlog;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bordeaux.entity.UserStory;
import com.bordeaux.entity.user.ProductOwner;
import com.bordeaux.form.backlog.UserStoryForm;
import com.bordeaux.service.BackLogService;
import com.bordeaux.service.user.ProductOwnerService;
import com.bordeaux.service.user.ScrumMasterService;

@Controller
public class UserStoryController {

	@Autowired
	private BackLogService backLogService;
	
	@Autowired
	private ScrumMasterService scrumMasterService;
	
	@Autowired
	private ProductOwnerService productOwnerService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUserStory(@Valid UserStoryForm userStoryForm,BindingResult bindingResult, Model model, Principal principal) {
		return process(userStoryForm,bindingResult,model,"add",principal);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editUserStory(@Valid UserStoryForm userStoryForm,BindingResult bindingResult, Model model, Principal principal) {
		return process(userStoryForm,bindingResult,model,"edit",principal);
	}
	
	private String process(UserStoryForm userStoryForm,BindingResult bindingResult, Model model,String returnType, Principal principal){
		
		if (bindingResult.hasErrors()){	//erreur dans le formulaire
			
			userStoryForm.setDependencies(backLogService.getAllUserStoriesIdExceptId(userStoryForm.getId()));
			userStoryForm.setScrumMasterList(scrumMasterService.getScrumMasterList());
			userStoryForm.setException(bindingResult.getAllErrors().get(0).getDefaultMessage());
			model.addAttribute("userStoryForm", userStoryForm);
			
			return returnType;
			
		}else{
			
			String email = principal.getName();
			ProductOwner productOwner = productOwnerService.findUserByEmail(email);
			
			UserStory userStory = backLogService.generateUserStory(userStoryForm); // a partir du formulaire
			userStory = backLogService.addOrUpdateUserStory(userStory);	// reference de l'objet sauvegarde
			backLogService.addOrUpdateUserStoryDependencies(userStory.getId(),userStoryForm.getSelectedDependencies());
			scrumMasterService.addUserStoryToScrumMaster(userStoryForm.getSelectedScrumMasterId(),userStory, productOwner.getProject());
			backLogService.initModelForBackLogPage(model);
			
			return "board";
			
		}
		
	}
	
	
	
	
}
