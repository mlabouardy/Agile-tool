package com.labouardy.service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.labouardy.entity.Backlog;
import com.labouardy.entity.Project;
import com.labouardy.entity.Role;
import com.labouardy.entity.User;
import com.labouardy.entity.UserStory;

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
	private BacklogService backlogService;
	
	@Autowired
	private UserStoryService userStoryService;
	
	@PostConstruct
	public void init(){
		Role product_owner=new Role();
		product_owner.setName("ROLE_PRODUCT_OWNER");
		
		Role scrum_master=new Role();
		scrum_master.setName("ROLE_SCRUM_MASTER");
		
		Role scrum_team=new Role();
		scrum_team.setName("ROLE_SCRUM_TEAM");
		
		roleService.save(product_owner);
		roleService.save(scrum_master);
		roleService.save(scrum_team);
		
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		
		User product=new User();
		product.setEmail("product@labouardy.com");
		product.setFirstname("Product");
		product.setLastname("Owner");
		product.setPassword(encoder.encode("product"));
		product.setRole(product_owner);
		
		User master=new User();
		master.setEmail("master@labouardy.com");
		master.setFirstname("Scrum");
		master.setLastname("Master");
		master.setPassword(encoder.encode("master"));
		master.setRole(scrum_master);
		
		User team=new User();
		team.setEmail("team@labouardy.com");
		team.setFirstname("Scrum");
		team.setLastname("Team");
		team.setPassword(encoder.encode("teamaz"));
		team.setRole(scrum_team);
		
		userService.save(product);
		userService.save(master);
		userService.save(team);
		
		Project project=new Project();
		project.setName("Gestion d'ateliers");
		project.setDescription("Bla bla bla");
		project.setUser(product);
		projectService.save(project);
		
		Backlog backlog=new Backlog();
		backlog.setProject(project);
		backlogService.save(backlog);
		
		UserStory us1=new UserStory();
		us1.setName("Med");
		us1.setTag("fd");
		us1.setPriority(1);
		us1.setDifficulty(2);
		us1.setBacklog(backlog);
		
		UserStory us2=new UserStory();
		us2.setName("AA");
		us2.setTag("fdsdsd");
		us2.setPriority(1);
		us2.setDifficulty(2);
		us2.setBacklog(backlog);
		
		userStoryService.save(us1);
		userStoryService.save(us2);
		
		
	}
}
