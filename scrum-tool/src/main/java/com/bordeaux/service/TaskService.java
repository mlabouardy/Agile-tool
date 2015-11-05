package com.bordeaux.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.Task;
import com.bordeaux.entity.UserStory;
import com.bordeaux.form.backlog.TaskForm;
import com.bordeaux.repository.TaskRepository;
import com.bordeaux.repository.UserStoryRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserStoryRepository userStoryRepository;
	
	public void save(Task task) {
		taskRepository.save(task);
	}

	public void saveTaskInUserStory(Task task, int id) {
		 
		UserStory userStory=userStoryRepository.findOne(id);
		List<Task> tmp=userStory.getTasks();
		tmp.add(task);
		userStory.setTasks(tmp);
		userStoryRepository.save(userStory);
		taskRepository.save(task);
		 
	}
	
	public Collection<TaskForm> getTaskForms() {

		Collection<TaskForm> taskForms = new ArrayList<TaskForm>();
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
