package com.bordeaux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bordeaux.entity.Project;
import com.bordeaux.entity.Sprint;
import com.bordeaux.entity.Task;
import com.bordeaux.model.BurndownChart;
import com.bordeaux.service.ProjectService;
import com.bordeaux.service.SprintService;
import com.bordeaux.service.TaskService;

@Controller
public class BurndownChartController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/burndownChart")
	public String burndownChart(Model model, int sprint_id){
		System.out.println(sprint_id);
		Sprint s = sprintService.findById(sprint_id);
		model.addAttribute("sprint", s);
		BurndownChart b = new BurndownChart(s);
		System.out.println("Debut : " + s.getBeginning().toString() + "\nFin : " + s.getEnd().toString());
		model.addAttribute("expectedCosts", b.getExpectedCostsFor(s.getBeginning(), s.getEnd(), 1));
		model.addAttribute("effectiveCosts", b.getEffectiveCostsFor(s.getBeginning(), s.getEnd(), 1));
		model.addAttribute("dates", b.getDateStr(s.getBeginning(), s.getEnd(), 1));
		//List<Task> tasks = (List<Task>) s.getTasks();
		return "burndownChart";
		
	}
	
}
