package com.bordeaux.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bordeaux.entity.user.ScrumMaster;
import com.bordeaux.entity.user.ScrumTeam;
import com.bordeaux.form.team.ScrumTeamForm;
import com.bordeaux.service.user.ScrumMasterService;
import com.bordeaux.service.user.ScrumTeamService;

@Controller
public class ScrumTeamController {

	@Autowired
	private ScrumTeamService scrumTeamService;
	
	@Autowired
	private ScrumMasterService scrumMasterService;
	
	@RequestMapping(value = "/scrumteam", method = RequestMethod.POST)
	public String getScrumTeam(ScrumTeamForm scrumTeamForm,Model model, Principal principal){
		ScrumMaster scrumMaster = scrumMasterService.findUserByEmail(principal.getName());
		
		if (scrumTeamForm.getSelectedDev()!=null){
			Collection<ScrumTeam> newTeam = new ArrayList<ScrumTeam>();
			for (int id : scrumTeamForm.getSelectedDev()){
				newTeam.add(scrumTeamService.findByID(id));
			}
			scrumMaster.setScrumTeamList(newTeam);
			scrumMasterService.saveTeam(scrumMaster);
		}else{
			scrumMaster.setScrumTeamList(null);
			scrumMasterService.saveTeam(scrumMaster);
		}
		
		Collection<ScrumTeam> devListWithoutTeam = scrumTeamService.findDevWithoutTeam();
		model.addAttribute("scrumMaster", scrumMaster);
		model.addAttribute("devListWithoutTeam", devListWithoutTeam);
		model.addAttribute("scrumTeamForm", new ScrumTeamForm());
		return "board";
		
	}
}
