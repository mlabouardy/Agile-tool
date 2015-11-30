package com.bordeaux.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import com.bordeaux.entity.Project;
import com.bordeaux.entity.user.ScrumMaster;
import com.bordeaux.entity.user.ScrumTeam;
import com.bordeaux.entity.user.User;
import com.bordeaux.repository.ProjectRepository;

@Service
@Transactional
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public void save(Project project){
		projectRepository.saveAndFlush(project);
	}
	
	public Project findById(int id){
		return projectRepository.findById(id);
	}
	
	public Collection<User> findUsersOfProject(int id){
		Project p = projectRepository.findById(id);		
		if(p != null){
			Collection<User> res = new ArrayList<User>();
			res.addAll(p.getManagers());
			res.addAll(p.getDevs());
			return res;
		}
		return null;
	}
	
	public Collection<ScrumMaster> findManagersOfProject(int id){
		Project p = projectRepository.findById(id);		
		if(p != null){
			return p.getManagers();
		
		}
		return null;
	}
	
	public Collection<ScrumTeam> findDevOfProject(int id){
		Project p = projectRepository.findById(id);		
		if(p != null){
			return p.getDevs();
		
		}
		return null;
	}
	
	public Collection<Project> findAll(){
		return projectRepository.findAll();
	}
	
	
}
