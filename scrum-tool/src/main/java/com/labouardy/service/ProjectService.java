package com.labouardy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labouardy.entity.Project;
import com.labouardy.entity.User;
import com.labouardy.repository.ProjectRepository;
import com.labouardy.repository.UserRepository;

@Service
@Transactional
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public void save(User user, Project project) {
		project.setUser(user);
		projectRepository.save(project);
	}

	public Project findOne(int id) {
		return projectRepository.findOne(id);
	}

	public void save(Project project) {
		projectRepository.save(project);
	}
	
}
