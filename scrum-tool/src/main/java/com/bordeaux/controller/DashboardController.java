package com.bordeaux.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bordeaux.entity.Role.RoleType;
import com.bordeaux.entity.Sprint;
import com.bordeaux.entity.Task;
import com.bordeaux.entity.user.ProductOwner;
import com.bordeaux.entity.user.ScrumMaster;
import com.bordeaux.entity.user.ScrumTeam;
import com.bordeaux.entity.user.User;
import com.bordeaux.form.backlog.BackLogForm;
import com.bordeaux.form.backlog.UserStoryForm;
import com.bordeaux.form.task.TaskForm;
import com.bordeaux.form.team.ScrumTeamForm;
import com.bordeaux.model.BurndownChart;
import com.bordeaux.service.BackLogService;
import com.bordeaux.service.SprintService;
import com.bordeaux.service.UserStoryService;
import com.bordeaux.service.user.ProductOwnerService;
import com.bordeaux.service.user.ScrumMasterService;
import com.bordeaux.service.user.ScrumTeamService;
import com.bordeaux.service.user.UserService;

@Controller
public class DashboardController {

	@Autowired
	private UserService userService;

	// j'ai fait un autre service car le jpa ne veut pas foctionner avec la
	// g�n�ricit�
	// donc pour chaque type d'utilisateur il faut cr�er un service et un
	// repository
	@Autowired
	private ProductOwnerService productOwnerService;

	@Autowired
	private ScrumMasterService scrumMasterService;

	@Autowired
	private ScrumTeamService scrumTeamService;

	@Autowired
	private BackLogService backLogService;

	@Autowired
	private UserStoryService userStoryService;
	
	@Autowired
	private SprintService ss;
	
	
	public Sprint getCurrentSprintFromScrumMaster(int id) throws Exception{
		
		ScrumMaster sm = scrumMasterService.findUserById(id);
		Sprint res = null;
		if(sm != null){
			Collection<Sprint> listAssociatedSprint = ss.findSprintOfUser(sm);
			Iterator<Sprint> ite = listAssociatedSprint.iterator();
			Date current = new Date();
			Calendar calres = Calendar.getInstance();
			Calendar caltmp = Calendar.getInstance();
			Calendar calCurrent = Calendar.getInstance();
			calCurrent.setTime(current);
			if(ite.hasNext()){
				res = ite.next();
				calres.setTime(res.getBeginning());
			}
			Sprint tmp;
			while(ite.hasNext()){
				tmp = ite.next();
				caltmp.setTime(tmp.getBeginning());
				if(calres.after(caltmp) && calres.compareTo(calCurrent) <= 0){
					res = tmp;
					calres = caltmp;
				}
			}
			
		}
		
		return res;
		
	}
	
	@RequestMapping("/board")
	public String dashboard(Model model, Principal principal) {
		String email = principal.getName();

		// j'ai besoin de recuperer le type de l'utilisateur
		User user = userService.findUserByEmail(email);
		model.addAttribute("name", user.getFirstname() + " " + user.getLastname());

		if (user.getRole().getName().equals(RoleType.PRODUCT.toString())) {
			ProductOwner productOwner = productOwnerService.findUserByEmail(email);
			backLogService.setBackLogID(productOwner.getProject().getBackLog().getId());
			backLogService.initModelForBackLogPage(model);
		}

		else if (user.getRole().getName().equals(RoleType.MASTER.toString())) {
			ScrumMaster scrumMaster = scrumMasterService.findUserByEmail(email);
			Collection<ScrumTeam> devListWithoutTeam = scrumTeamService.findDevWithoutTeam();
			model.addAttribute("scrumMaster", scrumMaster);
			model.addAttribute("devListWithoutTeam", devListWithoutTeam);
			model.addAttribute("scrumTeamForm", new ScrumTeamForm());
		
			
			
			Sprint s;
			try {
				s = getCurrentSprintFromScrumMaster(scrumMaster.getId());
				model.addAttribute("sprint", s);
				BurndownChart b = new BurndownChart(s);
				model.addAttribute("expectedCosts", b.getExpectedCostsFor(s.getBeginning(), s.getEnd(), 1));
				model.addAttribute("effectiveCosts", b.getEffectiveCostsFor(s.getBeginning(), s.getEnd(), 1));
				model.addAttribute("dates", b.getDateStr(s.getBeginning(), s.getEnd(), 1));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}

		else if (user.getRole().getName().equals(RoleType.TEAM.toString())) {
			ScrumTeam scrumTeam = scrumTeamService.findUserByEmail(email);
			ScrumMaster scrumMaster = scrumMasterService.getScrumMasterByDevID(scrumTeam.getId());
			
			// s'il le dev est d�ja affect� � un groupe
			// sinon on affiche pas le pert (le if dans la jsp)
			if (scrumMaster != null){
				model.addAttribute("scrumMaster",scrumMaster);
			}
			
			model.addAttribute("scrumTeam",scrumTeam);
		}
		
		

		return "board";
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String redirection(BackLogForm backLogForm, Model model) {

		String type = backLogForm.getTypeForm().getType();
		int userStoryId = backLogForm.getTypeForm().getId();

		if (type.equals("add") || type.equals("edit")) {

			UserStoryForm userStoryForm = backLogService.getUserStoryForm(userStoryId);
			model.addAttribute("userStoryForm", userStoryForm);
			return type;

		} else if (type.equals("remove")) {

			try {
				backLogService.removeUserStory(userStoryId);
				backLogService.initModelForBackLogPage(model);
			} catch (Exception e) {
				backLogService.initModelForBackLogPage(model, e.getMessage());
			}

			return "board";

		}

		return "board";
	}

	@ModelAttribute("taskForm")
	public TaskForm constructTaskForm() {
		return new TaskForm();
	}

	@RequestMapping(value = "/addtask/{scrumMasterID}/{userStoryID}")
	public String addTask(@PathVariable("userStoryID") int id,Model model) {
		List<ScrumTeam> scrumTeams = scrumTeamService.getScrumTeamList();
		model.addAttribute("scrumTeams",scrumTeams);
		return "addtask";
	}

	@RequestMapping(value = "/addtask/{scrumMasterID}/{userStoryID}", method = RequestMethod.POST)
	public String addTask(@ModelAttribute("taskForm") TaskForm taskForm, @PathVariable("scrumMasterID") int scrumMasterID, @PathVariable("userStoryID") int userStoryID, Model model) {
		try {
			userStoryService.saveTaskInUserStory(userStoryID, taskForm);
		} catch (Exception e) {
			taskForm.setException(e.getMessage());
		}
		
		return "redirect:/board.html";
	}

	@RequestMapping(value = "/board/project/{id_project}/listtask/{scrumMasterID}/{userStoryID}", method = RequestMethod.GET)
	public String ListTask(Model model,@PathVariable("id_project") int id_project, @PathVariable("scrumMasterID") int scrumMasterID, @PathVariable("userStoryID") int userStoryID) {
		Collection<Task> tasks = userStoryService.getUserStory(userStoryID).getTasks();
		model.addAttribute("tasks", tasks);
		model.addAttribute("id_project", id_project);
		return "listtask";
	}
}
