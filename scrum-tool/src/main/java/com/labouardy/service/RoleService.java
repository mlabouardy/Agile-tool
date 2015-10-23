package com.labouardy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labouardy.entity.Role;
import com.labouardy.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public void save(Role role) {
		roleRepository.save(role);
	}

}
