package com.bordeaux.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.hibernate.type.CalendarDateType;import org.hibernate.type.descriptor.java.CalendarTimeTypeDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.Project;
import com.bordeaux.entity.Role.RoleType;
import com.bordeaux.entity.Sprint;
import com.bordeaux.entity.StatusTask;
import com.bordeaux.entity.Task;
import com.bordeaux.entity.TaskDependencies;
import com.bordeaux.entity.user.ProductOwner;
import com.bordeaux.entity.user.ScrumMaster;
import com.bordeaux.entity.user.ScrumTeam;
import com.bordeaux.service.user.UserService;

@Service
@Transactional
public class InitService {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private StatusTaskService statusTaskService;
	
	@Autowired
	private TaskDependencyService tds;
	
	@PostConstruct
	public void init() {

		roleService.save(RoleType.PRODUCT.getRole());
		roleService.save(RoleType.MASTER.getRole());
		roleService.save(RoleType.TEAM.getRole());
		
		Project p = new Project();
		p.setName("Projet de Test");

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		ProductOwner product = new ProductOwner();
		product.setEmail("product@labouardy.com");
		product.setFirstname("Product");
		product.setLastname("Owner");
		product.setPassword(encoder.encode("product"));
		product.setProject(p);

		ProductOwner product2 = new ProductOwner();
		product2.setEmail("product2@labouardy.com");
		product2.setFirstname("Product2");
		product2.setLastname("Owner");
		product2.setPassword(encoder.encode("product"));
		product2.setProject(new Project());

		ScrumMaster master1 = new ScrumMaster();
		master1.setEmail("master1@labouardy.com");
		master1.setFirstname("Scrum1");
		master1.setLastname("Master1");
		master1.setPassword(encoder.encode("master"));
		master1.setProject(p);

		ScrumMaster master2 = new ScrumMaster();
		master2.setEmail("master2@labouardy.com");
		master2.setFirstname("Scrum2");
		master2.setLastname("Master2");
		master2.setPassword(encoder.encode("master"));
		master2.setProject(p);
		
		ScrumMaster master3 = new ScrumMaster();
		master3.setEmail("master3@labouardy.com");
		master3.setFirstname("Scrum3");
		master3.setLastname("Master3");
		master3.setPassword(encoder.encode("master"));
		
		
		ScrumTeam team1 = new ScrumTeam();
		team1.setEmail("team1@labouardy.com");
		team1.setFirstname("Scrum1");
		team1.setLastname("Team1");
		team1.setPassword(encoder.encode("teamaz"));
		
		ScrumTeam team2 = new ScrumTeam();
		team2.setEmail("team2@labouardy.com");
		team2.setFirstname("Scrum2");
		team2.setLastname("Team2");
		team2.setPassword(encoder.encode("teamaz"));
		
		ScrumTeam team3 = new ScrumTeam();
		team3.setEmail("team3@labouardy.com");
		team3.setFirstname("Scrum3");
		team3.setLastname("Team3");
		team3.setPassword(encoder.encode("teamaz"));
		
		userService.save(team1);
		userService.save(team2);
		userService.save(team3);
		
		master1.getScrumTeamList().add(team1);
		master1.getScrumTeamList().add(team2);
		
		userService.save(product);
		userService.save(product2);
		userService.save(master1);
		userService.save(master2);
		userService.save(master3);
		
		Project project = projectService.findById(1);
		Calendar cal = Calendar.getInstance();
		Date date_tmp = new Date();
		cal.setTime(date_tmp);
		Sprint sprint = new Sprint();
		sprint.setOwner(master1);
		sprint.setBeginning(new Date());
		sprint.setDuration(7);
		cal.add(Calendar.DAY_OF_MONTH, sprint.getDuration());
		sprint.setEnd((Date) cal.getTime().clone());
		Task task = new Task();
		StatusTask statusTask = new StatusTask();
		statusTask.setName("TO DO");
		
		StatusTask statusTask2 = new StatusTask();
		statusTask2.setName("ON GOING");
		
		StatusTask statusTask3 = new StatusTask();
		statusTask3.setName("DONE");
		statusTask3.setTerminal(true);
		
		cal.setTime(date_tmp);
		//DateFormat formatDate = new SimpleDateFormat("EEEE, d MMM yyyy");
		
	
		
		
		task.setDescription("Tache de test");
		task.setDifficulty(4);
		task.setPriority(1);
		task.setTag("Task 1");
		task.setColor("#D32F2F");
		task.setDone(false);
		task.setStatus(statusTask);
		task.setBeginning((Date) cal.getTime().clone());
		cal.add(Calendar.DATE, 1);
		task.setEffectiveEnd((Date)cal.getTime().clone()); 
		cal.add(Calendar.DATE, 1);
		task.setExpectedEnd((Date)cal.getTime().clone());
		sprint.getTasks().add(task);
		task.setSprint(sprint);
		
		cal.setTime(date_tmp);
		Task task2 = new Task();
		task2.setDescription("Tache de test 2");
		task2.setDifficulty(3);
		task2.setPriority(1);
		task2.setTag("Task 2");
		task2.setColor("#FFC107");
		task2.setDone(false);
		task2.setStatus(statusTask);
		sprint.getTasks().add(task2);
		task2.setSprint(sprint);
		cal.add(Calendar.DATE, 1);
		task2.setBeginning((Date) cal.getTime().clone());
		cal.add(Calendar.DATE, 1);
		task2.setEffectiveEnd((Date)cal.getTime().clone()); 
		cal.add(Calendar.DATE, 1);
		task2.setExpectedEnd((Date)cal.getTime().clone());
		
		cal.setTime(date_tmp);
		Task task3 = new Task();
		task3.setDescription("Tache de test 3");
		task3.setDifficulty(3);
		task3.setPriority(1);
		task3.setTag("Task 3");
		task3.setColor("#009688");
		task3.setDone(false);
		task3.setStatus(statusTask);
		sprint.getTasks().add(task3);
		task3.setSprint(sprint);
		cal.add(Calendar.DATE, 2);
		task3.setBeginning((Date) cal.getTime().clone());
		cal.add(Calendar.DATE, 1);
		task3.setEffectiveEnd((Date)cal.getTime().clone()); 
		cal.add(Calendar.DATE, 1);
		task3.setExpectedEnd((Date)cal.getTime().clone());
		
		
		cal.setTime(date_tmp);
		Task task4 = new Task();
		task4.setDescription("Tache de test 4");
		task4.setDifficulty(4);
		task4.setPriority(1);
		task4.setTag("Task 4");
		task4.setColor("#E64A19");
		task4.setDone(false);
		task4.setStatus(statusTask);
		task4.setBeginning((Date) cal.getTime().clone());
		cal.add(Calendar.DATE, 1);
		task4.setEffectiveEnd((Date)cal.getTime().clone()); 
		cal.add(Calendar.DATE, 1);
		task4.setExpectedEnd((Date)cal.getTime().clone());
		sprint.getTasks().add(task4);
		task4.setSprint(sprint);
		
		project.getSprintList().add(sprint);
		
		TaskDependencies td = new TaskDependencies();
		td.setParent(task);
		td.getDependencies().add(task2);
		td.getDependencies().add(task3);

		statusTaskService.save(statusTask);
		statusTaskService.save(statusTask2);
		statusTaskService.save(statusTask3);
		sprintService.save(sprint);
		taskService.save(task);
		taskService.save(task2);
		taskService.save(task3);
		taskService.save(task4);
		projectService.save(project);
		tds.save(td);

		
	
		
		

	}
}
