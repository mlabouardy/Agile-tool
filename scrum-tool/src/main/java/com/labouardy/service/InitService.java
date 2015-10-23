package com.labouardy.service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labouardy.entity.Role;

@Service
@Transactional
public class InitService {

	@Autowired
	private RoleService roleService;
	
	@PostConstruct
	public void init(){
		Role product_owner=new Role();
		product_owner.setName("Product Owner");
		
		Role scrum_master=new Role();
		scrum_master.setName("Scrum Master");
		
		Role scrum_team=new Role();
		scrum_team.setName("Scrum Team");
		
		roleService.save(product_owner);
		roleService.save(scrum_master);
		roleService.save(scrum_team);
	}
}
