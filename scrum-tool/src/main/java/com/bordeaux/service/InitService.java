package com.bordeaux.service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.Project;
import com.bordeaux.entity.Role.RoleType;
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

		ScrumMaster master = new ScrumMaster();
		master.setEmail("master@labouardy.com");
		master.setFirstname("Scrum");
		master.setLastname("Master");
		master.setPassword(encoder.encode("master"));

		ScrumTeam team = new ScrumTeam();
		team.setEmail("team@labouardy.com");
		team.setFirstname("Scrum");
		team.setLastname("Team");
		team.setPassword(encoder.encode("teamaz"));

		userService.save(product);
		userService.save(product2);
		userService.save(master);
		userService.save(team);

	}
}
