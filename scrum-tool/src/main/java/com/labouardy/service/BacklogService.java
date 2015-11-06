package com.labouardy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labouardy.entity.Backlog;
import com.labouardy.entity.Project;
import com.labouardy.entity.User;
import com.labouardy.repository.BacklogRepository;

@Service
@Transactional
public class BacklogService {

	@Autowired
	private BacklogRepository backlogRepository;
	
	public void save(Project project){
		Backlog backlog=new Backlog();
		backlog.setProject(project);
		backlogRepository.save(backlog);
	}

	public Backlog findOneById(int id) {
		return backlogRepository.findOne(id);
	}
}
