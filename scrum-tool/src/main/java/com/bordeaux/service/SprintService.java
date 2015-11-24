package com.bordeaux.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.Sprint;
import com.bordeaux.entity.Task;
import com.bordeaux.entity.user.User;
import com.bordeaux.repository.SprintRepository;

@Service
@Transactional
public class SprintService {

	@Autowired
	private SprintRepository sprintRepository;
	
	public void save(Sprint s){
		sprintRepository.save(s);
	}
	
	public Sprint findSprintById(int id){
		return sprintRepository.findSprintById(id);
	}

	public Collection<Sprint> findSprintOfUser(User u){
		return  sprintRepository.findSprintsByOwner(u);
	}
	
	public Collection<Sprint> getAll(){
		
		return sprintRepository.findAll();
	}
	
}
