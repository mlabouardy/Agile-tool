package com.bordeaux.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.Role;
import com.bordeaux.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public void save(Role role) {
		roleRepository.save(role);
	}

	public Role findById(String name) {
		int id=-1;
		if(name.compareTo("Product Owner")==0)
			id=1;
		if(name.compareTo("Scrum Master")==0)
			id=2;
		if(name.compareTo("Scrum Team")==0)
			id=3;
		return roleRepository.findOne(id);
	}

}
