package com.bordeaux.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.Task;
import com.bordeaux.entity.TaskDependencies;
import com.bordeaux.repository.TaskDependenciesRepository;

@Service
@Transactional
public class TaskDependencyService {
	
	@Autowired
	private TaskDependenciesRepository tdr;
	
	public void save(TaskDependencies td){
		tdr.save(td);
	}

	public TaskDependencies findTaskDependenciesByParent(Task task){
		return tdr.findByTask(task);
	}
	
}
