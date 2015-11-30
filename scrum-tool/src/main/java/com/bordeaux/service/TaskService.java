package com.bordeaux.service;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.Sprint;
import com.bordeaux.entity.StatusTask;
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
	
	public Task findTaskById(int id){
		return taskRepository.findById(id);
	}
	
	public Collection<Task> findTasksByStatus(StatusTask st){
		return taskRepository.findByStatus(st);
	}

}
