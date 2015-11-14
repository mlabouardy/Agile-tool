package com.bordeaux.service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.BackLog;
import com.bordeaux.entity.Github;
import com.bordeaux.entity.Project;
import com.bordeaux.entity.Role.RoleType;
import com.bordeaux.entity.UserStory;
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
	private UserStoryService userStoryService;
	
	@Autowired
	private BackLogService backlogService;
	
	@Autowired
	private GithubService githubService;
	
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
		
		
	}
}
