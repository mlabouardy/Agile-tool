package com.bordeaux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bordeaux.entity.*;
import com.bordeaux.repository.TaskRepository;

@RestController
public class GanttAPI {

	@Autowired
	private TaskRepository taskRespository;
	
	@RequestMapping(value="/tasks.json", method = RequestMethod.GET, produces = "application/json")
	public List<Task> getTasks(){
		return taskRespository.findAll();
	}
}
