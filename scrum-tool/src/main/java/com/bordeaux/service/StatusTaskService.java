package com.bordeaux.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.StatusTask;
import com.bordeaux.repository.StatusTaskRepository;

@Service
@Transactional
public class StatusTaskService {

	@Autowired
	private StatusTaskRepository statusTaskRepository;
	
	public void save(StatusTask st){
		statusTaskRepository.save(st);
	}
	
	
	public Collection<StatusTask> findAll(){
		return statusTaskRepository.findAll();
	}
	
	public StatusTask findStatusTaskByid(int id){
		return statusTaskRepository.findById(id);
	}
	
}
