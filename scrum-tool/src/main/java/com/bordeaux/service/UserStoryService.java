package com.bordeaux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.Task;
import com.bordeaux.entity.UserStory;
import com.bordeaux.repository.UserStoryRepository;

@Service
public class UserStoryService {

	@Autowired
	private UserStoryRepository userStoryRepository;
	
	public void saveTaskInUserStory(int userStoryID,Task task) {
		UserStory userStory=userStoryRepository.findOne(userStoryID);
		userStory.getTasks().add(task);
		userStoryRepository.saveAndFlush(userStory); 
	}
	
	
	public UserStory getUserStory(int userStoryID){
		return userStoryRepository.findOne(userStoryID);
	}
}
