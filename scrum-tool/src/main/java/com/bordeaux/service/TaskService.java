package com.bordeaux.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.Task;
import com.bordeaux.entity.UserStory;
import com.bordeaux.form.task.TaskForm;
import com.bordeaux.repository.TaskRepository;
import com.bordeaux.repository.UserStoryRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserStoryRepository userStoryRepository;
	
	
	public List<Task> getUserStory(int id){
		UserStory userStory = userStoryRepository.findOne(id);
		//System.out.println("user story story "+ userStory);
        List<Task> tasks=taskRepository.findAll();
        //=userStory.getTasks();
		//System.out.println("++++++++++++++++++++"+tasks);
		return tasks;
		
	}
	
	public List<TaskForm> getTaskForms() {

		List<TaskForm> taskForms = new ArrayList<TaskForm>();
		List<Task> tasks = taskRepository.findAll();

		for (Task task : tasks) {
			TaskForm taskForm = new TaskForm();
			taskForm.setId(task.getId());
			taskForm.setTag(task.getTag());
			taskForm.setDescription(task.getDescription());
			taskForm.setColor(task.getColor());
			
			taskForms.add(taskForm);
		}

		return taskForms;

	}	
}
