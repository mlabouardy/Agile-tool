package com.bordeaux.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	
	@PostConstruct
	public void init() {

		roleService.save(RoleType.PRODUCT.getRole());
		roleService.save(RoleType.MASTER.getRole());
		roleService.save(RoleType.TEAM.getRole());

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		ProductOwner product = new ProductOwner();
		product.setEmail("product@labouardy.com");
		product.setFirstname("Product");
		product.setLastname("Owner");
		product.setPassword(encoder.encode("product"));
		product.setProject(new Project());

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

		ScrumMaster master2 = new ScrumMaster();
		master2.setEmail("master2@labouardy.com");
		master2.setFirstname("Scrum2");
		master2.setLastname("Master2");
		master2.setPassword(encoder.encode("master"));
		
		ScrumMaster master3 = new ScrumMaster();
		master3.setEmail("master3@labouardy.com");
		master3.setFirstname("Scrum3");
		master3.setLastname("Master3");
		master3.setPassword(encoder.encode("master"));
		
		
		ScrumTeam team = new ScrumTeam();
		team.setEmail("team@labouardy.com");
		team.setFirstname("Scrum");
		team.setLastname("Team");
		team.setPassword(encoder.encode("teamaz"));
		
		userService.save(product);
		userService.save(product2);
		userService.save(master1);
		userService.save(master2);
		userService.save(master3);
		userService.save(team);
		
		/*Project project = new Project();*/
		Calendar cal = Calendar.getInstance();
		Date date_tmp = new Date();
		cal.setTime(date_tmp);
		Sprint sprint = new Sprint();
		sprint.setOwner(product);
		sprint.setBeginning(new Date());
		sprint.setDuration(7);
		cal.add(Calendar.DAY_OF_MONTH, sprint.getDuration());
		sprint.setEnd((Date) cal.getTime().clone());
		Task task = new Task();
		StatusTask statusTask = new StatusTask();
		cal.setTime(date_tmp);
		//DateFormat formatDate = new SimpleDateFormat("EEEE, d MMM yyyy");
		
		
		
		statusTask.setName("TO DO");
		task.setDescription("Tache de test");
		task.setDifficulty(4);
		task.setPriority(1);
		task.setTag("t");
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
		task2.setTag("t");
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
		task3.setTag("t");
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
		task4.setTag("t");
		task4.setDone(false);
		task4.setStatus(statusTask);
		task4.setBeginning((Date) cal.getTime().clone());
		cal.add(Calendar.DATE, 1);
		task4.setEffectiveEnd((Date)cal.getTime().clone()); 
		cal.add(Calendar.DATE, 1);
		task4.setExpectedEnd((Date)cal.getTime().clone());
		sprint.getTasks().add(task4);
		task4.setSprint(sprint);
		
		/*project.getSprintList().add(sprint);*/

		statusTaskService.save(statusTask);
		sprintService.save(sprint);
		taskService.save(task);
		taskService.save(task2);
		taskService.save(task3);
		taskService.save(task4);


		
		/*projectService.save(project);*/
		
	
		
		

	}
}
