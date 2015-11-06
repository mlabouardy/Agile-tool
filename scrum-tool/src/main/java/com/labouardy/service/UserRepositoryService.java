package com.labouardy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labouardy.entity.Backlog;
import com.labouardy.entity.UserStory;
import com.labouardy.repository.BacklogRepository;
import com.labouardy.repository.UserStoryRepository;

@Service
@Transactional
public class UserRepositoryService {

	@Autowired
	private UserStoryRepository userStoryRepository;
	
	public void save(UserStory userStory){
		userStoryRepository.save(userStory);
	}
}
