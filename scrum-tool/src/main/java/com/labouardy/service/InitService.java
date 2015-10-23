package com.labouardy.service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InitService {

	@Autowired
	private RoleService roleService;
	
	@PostConstruct
	public void init(){
		
	}
}
