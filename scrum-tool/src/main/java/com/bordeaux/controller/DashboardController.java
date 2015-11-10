package com.bordeaux.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bordeaux.entity.Role.RoleType;
import com.bordeaux.entity.Task;
import com.bordeaux.entity.UserStory;
import com.bordeaux.entity.user.ProductOwner;
import com.bordeaux.entity.user.ScrumMaster;
import com.bordeaux.entity.user.ScrumTeam;
import com.bordeaux.entity.user.User;
import com.bordeaux.form.backlog.BackLogForm;
import com.bordeaux.form.backlog.TaskForm;
import com.bordeaux.form.backlog.UserStoryForm;
import com.bordeaux.service.BackLogService;
import com.bordeaux.service.TaskService;
import com.bordeaux.service.user.ProductOwnerService;
import com.bordeaux.service.user.ScrumMasterService;
import com.bordeaux.service.user.ScrumTeamService;
import com.bordeaux.service.user.UserService;

@Controller
public class DashboardController {
	
	@Autowired
	private UserService userService;
	
	// j'ai fait un autre service car le jpa ne veut pas foctionner avec la g�n�ricit�
	// donc pour chaque type d'utilisateur il faut cr�er un service et un repository
	@Autowired
	private ProductOwnerService productOwnerService;
	
	@Autowired
	private ScrumMasterService scrumMasterService;
	
	@Autowired
	private ScrumTeamService scrumTeamService;
	
	@Autowired
	private BackLogService backLogService;
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping("/board")
	public String dashboard(Model model, Principal principal){
		String email=principal.getName();
		
		//j'ai besoin de recuperer le type de l'utilisateur
		User user=userService.findUserByEmail(email);
		model.addAttribute("name", user.getFirstname()+" "+user.getLastname());
		
		if (user.getRole().getName().equals(RoleType.PRODUCT.toString())){
			ProductOwner productOwner = productOwnerService.findUserByEmail(email);
			backLogService.setBackLogID(productOwner.getProject().getBackLog().getId());
			backLogService.initModelForBackLogPage(model);
		}
		
		else if (user.getRole().getName().equals(RoleType.MASTER.toString())){
			ScrumMaster scrumMaster = scrumMasterService.findUserByEmail(email);
			Collection<UserStory> userStories= scrumMaster.getUserStories();
			model.addAttribute("userStories", userStories);
		}
		
		else if (user.getRole().getName().equals(RoleType.TEAM.toString())){
			ScrumTeam scrumTeam = scrumTeamService.findUserByEmail(email);
			//....
		}
		
		
		return "board";
	}
	
	@RequestMapping(value = "/board", method = RequestMethod.POST)
	public String redirection(BackLogForm backLogForm, Model model) {
		
		String type = backLogForm.getTypeForm().getType();
		int userStoryId = backLogForm.getTypeForm().getId();
		
		if (type.equals("add") || type.equals("edit")) {

			UserStoryForm userStoryForm = backLogService.getUserStoryForm(userStoryId);
			model.addAttribute("userStoryForm", userStoryForm);
			return type;
			
		}
		else if (type.equals("remove")) {
			
			try {
				backLogService.removeUserStory(userStoryId);
				backLogService.initModelForBackLogPage(model);
			} catch (Exception e) {
				backLogService.initModelForBackLogPage(model,e.getMessage());
			}
			
			return "board";
			
		}
		
		return "board";
	}
	
	
	
	@ModelAttribute("task")
	public Task constructTask(){
		return new Task();
	}
	
	@RequestMapping(value="/addtask/{id}",method=RequestMethod.POST)
	public String addTask(@ModelAttribute("task") Task task, @PathVariable("id") int id){
		taskService.saveTaskInUserStory(task, id);
		return "board";
	}
	
	@RequestMapping(value="/addtask/{id}")
	public String addTask(@PathVariable("id") int id){
		return "addtask";
	}
	
	@RequestMapping(value="/listtask/{id}" , method=RequestMethod.GET)
	public String ListTask(TaskForm taskForm,Model model,@PathVariable("id") int id){
		//List<TaskForm> taskForms=taskService.getTaskForms();
		List<Task> taskForms=taskService.getUserStory(id);
		System.out.println("id id id id id "+id + taskForms);
		//taskService.getUserStory(id);
	   /* for (Task tas: taskForms)
	    {
	    	System.out.println(" aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "+tas.getDescription());
	    }*/
		model.addAttribute("taskForms", taskForms);
	    return "listtask";
	}
}
