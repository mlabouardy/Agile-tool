package com.bordeaux.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.Sprint;
import com.bordeaux.entity.Task;
import com.bordeaux.repository.SprintRepository;

@Service
@Transactional
public class SprintService {

	@Autowired
	private SprintRepository sprintRepository;
	
	public void save(Sprint s){
		sprintRepository.save(s);
	}
	
	public Sprint findById(int id){
		return sprintRepository.findOne(id);
	}

	
	public Collection<Sprint> getAll(){
		
		return sprintRepository.findAll();
	}
	
}
