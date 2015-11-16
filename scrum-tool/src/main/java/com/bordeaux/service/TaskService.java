package com.bordeaux.service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.Sprint;
import com.bordeaux.entity.Task;
import com.bordeaux.repository.TaskRepository;

@Service
@Transactional
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public void save(Task t){
		taskRepository.save(t);
	}
	
	public Task findTaskBySprint(Sprint s){
		return taskRepository.findBySprint(s);
	}
	

}